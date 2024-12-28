package me.orphey;

import me.orphey.mixin.client.ChatAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.nio.file.Path;
import java.util.List;

public class TypingInChatClient implements ClientModInitializer {
	private boolean chatOpen = false;
	private boolean chatTyping = false;
	private int stoppedTypingCounter;
	private static final int STOPPED_TYPING_DELAY = 80;
	private String chatTextBuf = "";

	@Override
	public void onInitializeClient() {
		Path configDir = FabricLoader.getInstance().getConfigDir();
		ConfigLoader.load(configDir);

		// Register the reload command
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess)
				-> dispatcher.register(net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal("typinginchatmod")
				.then(net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal("reload")
						.executes(context -> {
							// Reload the config
							ConfigLoader.getInstance().reload(FabricLoader.getInstance().getConfigDir());
							context.getSource().sendFeedback(Text.literal("[TypingInChat] Config reloaded (client-side)!"));
							return 1;
						}))
		));

		// Register a tick event to monitor screen changes
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (!ConfigLoader.getInstance().isEnableMod() || client.player == null) {
				return;
			}
			Screen currentScreen = MinecraftClient.getInstance().currentScreen;
			if (currentScreen instanceof ChatScreen) {
				handleChatScreen();
			} else {
				handleScreenClose();
			}
		});
	}
	private void handleChatScreen() {
		if (!chatOpen) {
			onChatOpen();
		}
		boolean currentlyTyping = isTyping((ChatScreen) MinecraftClient.getInstance().currentScreen);
		if (currentlyTyping && playersNearby(MinecraftClient.getInstance())) {
			onTyping();
		}
		// Increment stoppedTypingCounter if the player isn't typing
		if (!currentlyTyping) {
			stoppedTypingCounter++;
		} else {
			// Reset the stoppedTypingCounter if the player is typing
			stoppedTypingCounter = 0;
		}
		if (stoppedTypingCounter >= STOPPED_TYPING_DELAY) {
			handleStoppedTyping();
		}
	}

	private void onChatOpen() {
		chatOpen = true;
		if (ConfigLoader.getInstance().isDebug()) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.of("Chat GUI opened!"));
		}
	}

	private void onTyping() {
		if (!chatTyping) {
			chatTyping = true;
			ChatPacket.sendPacket((byte) 1);
			if (ConfigLoader.getInstance().isDebug()) {
                assert MinecraftClient.getInstance().player != null;
                MinecraftClient.getInstance().player.sendMessage(Text.of("Player is Typing"));
			}
		}
	}

	private void handleStoppedTyping() {
		if (chatTyping) {
			chatTyping = false;
			stoppedTypingCounter = 0; // Reset the counter after handling
			ChatPacket.sendPacket((byte) 0);
			if (ConfigLoader.getInstance().isDebug()) {
                assert MinecraftClient.getInstance().player != null;
                MinecraftClient.getInstance().player.sendMessage(Text.of("Player stopped typing"));
			}
		}
	}

	private void handleScreenClose() {
		if (chatOpen) {
			chatOpen = false;
			chatTyping = false;
			chatTextBuf = "";
			stoppedTypingCounter = 0; // Reset stopped typing logic
			ChatPacket.sendPacket((byte) 0);
			if (ConfigLoader.getInstance().isDebug()) {
                assert MinecraftClient.getInstance().player != null;
                MinecraftClient.getInstance().player.sendMessage(Text.of("Chat GUI closed!"));
			}
		}
	}

	private boolean playersNearby(MinecraftClient client) {
		assert client.player != null;
		List<PlayerEntity> nearbyPlayers = client.player.getWorld().getEntitiesByClass(
				PlayerEntity.class, client.player.getBoundingBox().expand(25), p -> p != client.player);
		return !nearbyPlayers.isEmpty();
	}

	private boolean isTyping(ChatScreen chatScreen) {
		ChatAccessor chatScreenAccessor = (ChatAccessor) chatScreen;
		String chatText = chatScreenAccessor.getChatField().getText(); // Access chat field text
		if (ConfigLoader.getInstance().isIgnoreCommands() && isCommand(chatText)) {
			return false;
		}
		if (chatText != null && !chatText.equals(chatTextBuf)) {
			chatTextBuf = chatText; // Update previous text length
			return true;
		} else {
			return false;
		}

	}

	private boolean isCommand(String chatText) {
		if (chatText == null || chatText.isEmpty()) {
			return false; // Null or empty strings cannot start with "/"
		}
		// Trim leading spaces and check the first character
		String trimmedInput = chatText.trim();
		return !trimmedInput.isEmpty() && trimmedInput.charAt(0) == '/';
	}
}