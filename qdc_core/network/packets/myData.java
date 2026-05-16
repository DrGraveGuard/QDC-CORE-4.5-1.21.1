package com.qdc_mod.qdc_core_4_5.qdc_core.network.packets;

import com.qdc_mod.qdc_core_4_5.Qdc;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class myData {
	
	public record MyData(int slotIndex, int itemID, int count) implements CustomPacketPayload {

		public static final CustomPacketPayload.Type<MyData> TYPE = new CustomPacketPayload.Type<>(
				ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID, "my_data"));

		// Each pair of elements defines the stream codec of the element to
		// encode/decode and the getter for the element to encode
		// 'name' will be encoded and decoded as a string
		// 'age' will be encoded and decoded as an integer
		// The final parameter takes in the previous parameters in the order they are
		// provided to construct the payload object
		public static final StreamCodec<ByteBuf, MyData> STREAM_CODEC = StreamCodec.composite(
	        ByteBufCodecs.VAR_INT,
	        MyData::slotIndex,
	        ByteBufCodecs.VAR_INT,
	        MyData::itemID,
	        ByteBufCodecs.VAR_INT,
	        MyData::count,
	        MyData::new
	    );

		@Override
		public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
			return TYPE;
		}
	}
	
	

}
