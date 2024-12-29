package me.orphey;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class ChatPacket {
    private ChatPacket() {}

    public static void register() {
        // Register the payload type for client-to-server communication
        PayloadTypeRegistry.playC2S().register(ChatPacketPayload.PAYLOAD_ID, ChatPacketPayload.CODEC);
    }
    public static void sendPacket(boolean isTyping) {
        ClientPlayNetworking.send(new ChatPacketPayload(isTyping));
    }
}
