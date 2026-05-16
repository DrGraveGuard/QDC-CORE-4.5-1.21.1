package com.qdc_mod.qdc_core_4_5.qdc_core.network.packets;

import com.qdc_mod.qdc_core_4_5.Qdc;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
public class myData2 {
	


	public record MyData2(double x_pos, double y_pos, double z_pos) implements CustomPacketPayload {

		public static final CustomPacketPayload.Type<MyData2> TYPE = new CustomPacketPayload.Type<>(
				ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID, "my_data2"));

		// Each pair of elements defines the stream codec of the element to
		// encode/decode and the getter for the element to encode
		// 'name' will be encoded and decoded as a string
		// 'age' will be encoded and decoded as an integer
		// The final parameter takes in the previous parameters in the order they are
		// provided to construct the payload object
		public static final StreamCodec<ByteBuf, MyData2> STREAM_CODEC = StreamCodec.composite(
	        ByteBufCodecs.DOUBLE,
	        MyData2::x_pos,
	        ByteBufCodecs.DOUBLE,
	        MyData2::y_pos,
	        ByteBufCodecs.DOUBLE,
	        MyData2::z_pos,
	        MyData2::new
	    );

		@Override
		public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
			return TYPE;
		}
	}
	
	

}
