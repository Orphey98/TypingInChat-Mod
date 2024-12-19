package me.orphey;

import me.orphey.mixin.client.ChatAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import java.util.List;

public class TypingInChatClient implements ClientModInitializer {
	private boolean chatOpen = false;
	private boolean chatTyping = false;
	private int tickCounter;
	private static final int TICK_DELAY = 80;
	private String chatTextBuf = "";

	@Override
	public void onInitializeClient() {
		//ChatStatusPacket.register(); // C2S packet listener
		// Register a tick event to monitor screen changes
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player == null) {
				return;
			}
			Screen currentScreen = MinecraftClient.getInstance().currentScreen;
			if (currentScreen instanceof ChatScreen chatScreen) {
				tickCounter++;
				// If the chat is open but we haven't detected it yet
				if (!chatOpen) {
					chatOpen = true;
					client.player.sendMessage(Text.of("Chat GUI opened!"));
				}
				if (!chatTyping && isTyping(chatScreen)) {
					chatTyping = true;
					ChatPacket.sendPacket((byte) 1);
					client.player.sendMessage(Text.of("Player is Typing"));
				}
				if (tickCounter >= TICK_DELAY) {
					if (chatTyping && !isTyping(chatScreen)) {
						chatTyping = false;
						ChatPacket.sendPacket((byte) 0);
						client.player.sendMessage(Text.of("Player stopped typing"));
					}
					tickCounter = 0;
				}
			} else {
				if (chatOpen) {
					chatOpen = false;
					chatTyping = false;
					chatTextBuf = "";
					tickCounter = 0;
					ChatPacket.sendPacket((byte) 0);
					client.player.sendMessage(Text.of("Chat GUI closed!"));
				}
			}
		});
	}

	private boolean playersNearby(MinecraftClient client) {
		assert client.player != null;
		List<PlayerEntity> nearbyPlayers = client.player.getWorld().getEntitiesByClass(
				PlayerEntity.class, client.player.getBoundingBox().expand(32), p -> p != client.player);
		return !nearbyPlayers.isEmpty();
	}

	private boolean isTyping(ChatScreen chatScreen) {
		ChatAccessor chatScreenAccessor = (ChatAccessor) chatScreen;
		String chatText = chatScreenAccessor.getChatField().getText(); // Access chat field text
		if (chatText != null && !chatText.equals(chatTextBuf)) {
			chatTextBuf = chatText; // Update previous text length
			return true;
		} else {
			return false;
		}

	}
}