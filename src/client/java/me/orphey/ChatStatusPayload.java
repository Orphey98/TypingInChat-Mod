package me.orphey;

//import net.minecraft.network.RegistryByteBuf;
//import net.minecraft.network.codec.PacketCodec;
//import net.minecraft.network.codec.PacketCodecs;
//import net.minecraft.network.packet.CustomPayload;
//import net.minecraft.util.Identifier;
//
//public record ChatStatusPayload(boolean isTyping) implements CustomPayload {
//    // Define a unique identifier for the packet
//    public static final CustomPayload.Id<ChatStatusPayload> ID = new CustomPayload.Id<>(Identifier.of("aytm","typing_status"));
//
//    // Define the codec for encoding and decoding the packet
//    public static final PacketCodec<RegistryByteBuf, ChatStatusPayload> CODEC =
//            PacketCodec.tuple(
//                    PacketCodecs.BOOL, // Use the built-in codec for booleans
//                    ChatStatusPayload::isTyping, // How to extract the field
//                    ChatStatusPayload::new // How to create the payload object
//            );
//
//    @Override
//    public Id<? extends CustomPayload> getId() {
//        return ID;
//    }
//}
