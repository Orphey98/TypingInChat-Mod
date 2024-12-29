package me.orphey;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ChatPacketPayload(boolean isTyping) implements CustomPayload {
    // Define a unique identifier for the packet
    public static final CustomPayload.Id<ChatPacketPayload> PAYLOAD_ID = new CustomPayload.Id<>(Identifier.of("typinginchatmod","typing_status"));

    // Define the codec for encoding and decoding the packet
    public static final PacketCodec<RegistryByteBuf, ChatPacketPayload> CODEC =
            PacketCodec.tuple(
                    PacketCodecs.BOOLEAN, // Use the built-in codec for booleans
                    ChatPacketPayload::isTyping, // How to extract the field
                    ChatPacketPayload::new // How to create the payload object
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return PAYLOAD_ID;
    }
}
