package me.orphey;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class ChatPacket {

    public static final Identifier CUSTOM_PAYLOAD_ID = new Identifier("ticm67896", "typing_status");

    public static void sendPacket(byte message) {
        // Create a PacketByteBuf to hold the data
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        // Write data into the buffer
        buf.writeByte(message);
        // Send the packet to the server
        ClientPlayNetworking.send(CUSTOM_PAYLOAD_ID, buf);
    }
}
