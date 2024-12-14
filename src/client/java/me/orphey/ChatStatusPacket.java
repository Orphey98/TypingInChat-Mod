package me.orphey;

//import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
//import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
//import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
//import net.minecraft.network.PacketByteBuf;
//import net.minecraft.util.Identifier;
//
//public class ChatStatusPacket {
//    public static final Identifier CHAT_STATUS_PACKET_ID = Identifier.of("aytm", "chat_packet");
//    public static void register() {
//        // Register the payload type for client-to-server communication
//        PayloadTypeRegistry.playC2S().register(ChatStatusPayload.ID, ChatStatusPayload.CODEC);
//    }
//    public static void sendTypingStatus(boolean isTyping) {
//        ClientPlayNetworking.send(new ChatStatusPayload(isTyping));
//    }
//}
