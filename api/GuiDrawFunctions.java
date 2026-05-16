package com.qdc_mod.qdc_core_4_5.api;

import java.awt.Color;
import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TtleType;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GuiDrawFunctions {

	private static final ResourceLocation BG_GRAY_1 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/gray_1.png");
	private static final ResourceLocation BG_GRAY_2 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/gray_2.png");

	private static final ResourceLocation BG_WHITE_1 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/white_1.png");
	private static final ResourceLocation BG_WHITE_2 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/white_2.png");

	private static final ResourceLocation BG_BLACK_1 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/black_1.png");
	private static final ResourceLocation BG_BLACK_2 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/black_2.png");

	private static final ResourceLocation BG_BLUE_1 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/blue_1.png");
	private static final ResourceLocation BG_BLUE_2 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/blue_2.png");

	private static final ResourceLocation BG_GREEN_1 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/green_1.png");
	private static final ResourceLocation BG_GREEN_2 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/green_2.png");

	private static final ResourceLocation BG_RED_1 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/red_1.png");
	private static final ResourceLocation BG_RED_2 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/red_2.png");

	private static final ResourceLocation BG_YELLOW_1 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/yellow_1.png");
	private static final ResourceLocation BG_YELLOW_2 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/yellow_2.png");

	private static final ResourceLocation BG_PURPLE_1 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/purple_1.png");
	private static final ResourceLocation BG_PURPLE_2 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/purple_2.png");

	private static final ResourceLocation BG_ORANGE_1 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/orange_1.png");
	private static final ResourceLocation BG_ORANGE_2 = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/orange_2.png");

	private static final ResourceLocation BTN_FILL = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/button/button_fill.png");
	private static final ResourceLocation BTN_BORDER = BG_BLUE_2;
	private static final ResourceLocation BTN_BORDER_UPPER = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/button/button_border_upper.png");
	private static final ResourceLocation BTN_BORDER_LOWER = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/button/button_border_lower.png");

	private static final ResourceLocation BTN_FILL_ACTIVE = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/button/button_fill_active.png");
	private static final ResourceLocation BTN_BORDER_ACTIVE = BG_WHITE_2;
	private static final ResourceLocation BTN_BORDER_UPPER_ACTIVE = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/button/button_border_upper_active.png");
	private static final ResourceLocation BTN_BORDER_LOWER_ACTIVE = ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID,
			"textures/gui/bg/button/button_border_lower_active.png");

	private static final Color BUTTON_TEXT_COLOR = Color.white;
	private static final Color BUTTON_TEXT_COLOR_HOVER = Color.white;
	private static final Color BUTTON_TEXT_COLOR_DISABLED = Color.gray;

	private static final ResourceLocation BTN_BORDER_DIASBLED = BG_GRAY_1;
	private static final ResourceLocation BTN_FILL_DIASBLED = BG_BLACK_1;

	private static final ResourceLocation TITLE_FILL = BG_BLUE_1;
	private static final ResourceLocation TITLE_BORDER = BG_WHITE_1;
	private static final ResourceLocation TITLE_DECORATION = BG_BLUE_2;
	private static final Color TITLE_TEXT_COLOR = Color.white;
	private static final Color SUB_TITLE_TEXT_COLOR = Color.cyan;

	public static void drawTitle(GuiGraphics gg, Font font, Point windowPos, ModGuiTitle titleObj) {

		if (titleObj.type == TtleType.MAIN_TITLE) {

			drawRectangle(gg, windowPos, titleObj.borderPos, titleObj.borderSize, TITLE_BORDER);
			drawRectangle(gg, windowPos, titleObj.fillPos, titleObj.fillSize, TITLE_FILL);
			drawRectangle(gg, windowPos, titleObj.decorationPos, titleObj.decorationSize, TITLE_DECORATION);
			writeString(gg, font, windowPos, titleObj.text, titleObj.textPos.x, titleObj.textPos.y, TITLE_TEXT_COLOR);
		} else {

			drawRectangle(gg, windowPos, titleObj.decorationPos, titleObj.decorationSize, TITLE_DECORATION);
			writeString(gg, font, windowPos, titleObj.text, titleObj.textPos.x, titleObj.textPos.y,
					SUB_TITLE_TEXT_COLOR);
		}
	}

	public static void drawToolTipWindow(GuiGraphics gg, Font font, ItemStack itemStack, int x, int y) {

		gg.renderTooltip(font, itemStack, x, y);
	}

	public static void drawRectangleWithBorder(GuiGraphics gg, Point windowPos, Point pos, Point size,
			TextureColor fillColor, TextureColor borderColor) {
		drawRectangle(gg, windowPos, pos.x, pos.y, size.x, size.y, borderColor);
		drawRectangle(gg, windowPos, pos.x + 1, pos.y + 1, size.x - 2, size.y - 2, fillColor);
	}

	public static void drawRectangle(GuiGraphics gg, Point windowPos, int xPos, int yPos, int width, int height,
			TextureColor color) {
		gg.blit(getImageFromColor(color), windowPos.x + xPos, windowPos.y + yPos, 0, 0, width, height, 600, 400);
	}

	public static void drawRectangle(GuiGraphics gg, Point windowPos, Point pos, Point size,
			ResourceLocation colorTexture) {
		gg.blit(colorTexture, windowPos.x + pos.x, windowPos.y + pos.y, 0, 0, size.x, size.y, 600, 400);
	}

	private static int button_texture_width = 300;
	private static int button_texture_height = 100;

	public static void drawButton(GuiGraphics gg, Font font, Point windowPos, ModButton btn) {

		if (btn.isVisible)
			if (btn.isEnabled) {
				drawButton(gg, font, windowPos, btn.pos, btn.size, btn.text, btn.textPos, btn.isHoveringOver);
			} else {
				drawDisabledButton(gg, font, windowPos, btn.pos, btn.size, btn.text, btn.textPos);
			}
	}

	private static void drawButton(GuiGraphics gg, Font font, Point windowPos, Point pos, Point size, String text,
			Point textPos, boolean isHoveringOver) {
		if (isHoveringOver) {
			gg.blit(BTN_BORDER_ACTIVE, windowPos.x + pos.x, windowPos.y + pos.y, 0, 0, size.x, size.y,
					button_texture_width, button_texture_height);

			gg.blit(BTN_FILL_ACTIVE, windowPos.x + pos.x + 1, windowPos.y + pos.y + 1, 0, 0, size.x - 2, size.y - 2,
					button_texture_width, button_texture_height);

			gg.blit(BTN_BORDER_UPPER_ACTIVE, windowPos.x + pos.x + 1, windowPos.y + pos.y + 1, 0, 0, size.x - 2, 1,
					button_texture_width, button_texture_height);
			gg.blit(BTN_BORDER_UPPER_ACTIVE, windowPos.x + pos.x + 1, windowPos.y + pos.y + 1, 0, 0, 1, size.y - 2,
					button_texture_width, button_texture_height);

			gg.blit(BTN_BORDER_LOWER_ACTIVE, windowPos.x + pos.x + 1, windowPos.y + pos.y + size.y - 2, 0, 0,
					size.x - 2, 1, button_texture_width, button_texture_height);
			gg.blit(BTN_BORDER_LOWER_ACTIVE, windowPos.x + pos.x + size.x - 2, windowPos.y + pos.y + 1, 0, 0, 1,
					size.y - 2, button_texture_width, button_texture_height);

			writeStringCentred(gg, font, windowPos, text, textPos.x, textPos.y, BUTTON_TEXT_COLOR_HOVER);

		} else {
			gg.blit(BTN_BORDER, windowPos.x + pos.x, windowPos.y + pos.y, 0, 0, size.x, size.y, button_texture_width,
					button_texture_height);

			gg.blit(BTN_FILL, windowPos.x + pos.x + 1, windowPos.y + pos.y + 1, 0, 0, size.x - 2, size.y - 2,
					button_texture_width, button_texture_height);

			gg.blit(BTN_BORDER_UPPER, windowPos.x + pos.x + 1, windowPos.y + pos.y + 1, 0, 0, size.x - 2, 1,
					button_texture_width, button_texture_height);
			gg.blit(BTN_BORDER_UPPER, windowPos.x + pos.x + 1, windowPos.y + pos.y + 1, 0, 0, 1, size.y - 2,
					button_texture_width, button_texture_height);

			gg.blit(BTN_BORDER_LOWER, windowPos.x + pos.x + 1, windowPos.y + pos.y + size.y - 2, 0, 0, size.x - 2, 1,
					button_texture_width, button_texture_height);
			gg.blit(BTN_BORDER_LOWER, windowPos.x + pos.x + size.x - 2, windowPos.y + pos.y + 1, 0, 0, 1, size.y - 2,
					button_texture_width, button_texture_height);

			writeStringCentred(gg, font, windowPos, text, textPos.x, textPos.y, BUTTON_TEXT_COLOR);

		}

	}

	private static void drawDisabledButton(GuiGraphics gg, Font font, Point windowPos, Point pos, Point size,
			String text, Point textPos) {

		gg.blit(BTN_BORDER_DIASBLED, windowPos.x + pos.x, windowPos.y + pos.y, 0, 0, size.x, size.y, 600, 400);

		gg.blit(BTN_FILL_DIASBLED, windowPos.x + pos.x + 1, windowPos.y + pos.y + 1, 0, 0, size.x - 2, size.y - 2, 600,
				400);

		writeStringCentred(gg, font, windowPos, text, textPos.x, textPos.y, BUTTON_TEXT_COLOR_DISABLED);

	}

	public static void drawWindowTitle(GuiGraphics gg, Point windowPos, Point pos, Point size, Font font, String text) {
		gg.blit(TITLE_BORDER, windowPos.x + pos.x, windowPos.y + pos.y, 0, 0, size.x, size.y, 600, 400);
		gg.blit(TITLE_FILL, windowPos.x + pos.x + 1, windowPos.y + pos.y + 1, 0, 0, size.x - 2, size.y - 2, 600, 400);
		writeString(gg, font, windowPos, text, pos.x + 3, pos.y + 3, TITLE_TEXT_COLOR);
	}

	public static void drawItemIcon(GuiGraphics gg, Font font, Point windowPos, ItemStack item, int x, int y) {
		gg.renderItem(item, windowPos.x + x, windowPos.y + y, 3);
	}

	public static void drawItemIcon(GuiGraphics gg, Font font, Point windowPos, Item item, int x, int y) {
		gg.renderItem(new ItemStack(item), windowPos.x + x, windowPos.y + y, 3);
	}

//	public static void drawItemIcon(GuiGraphics gg, Font font, Point windowPos, Item item, int x, int y, int count) {
//		gg.renderItem(new ItemStack(item), windowPos.x + x, windowPos.y + y, 3);
//		gg.renderItemDecorations(font, new ItemStack(item, count), windowPos.x + x, windowPos.y + y, count + "");
//	}

	public static void drawItemIcon(GuiGraphics gg, Font font, Point windowPos, ItemStack stack, int x, int y,
			int count) {
		gg.renderItem(stack, windowPos.x + x, windowPos.y + y, 3);

		if (count > 1)
			gg.renderItemDecorations(font, stack, windowPos.x + x, windowPos.y + y, count + "");
		else
			gg.renderItemDecorations(font, stack, windowPos.x + x, windowPos.y + y, "");

	}

	public static void writeStringCentred(GuiGraphics gg, Font font, Point windowPos, String text, int x, int y,
			Color color) {

		gg.drawCenteredString(font, text, windowPos.x + x, windowPos.y + y, color.getRGB());
	}

	public static void writeString(GuiGraphics gg, Font font, Point windowPos, String text, int x, int y, Color color) {
		gg.drawString(font, text, windowPos.x + x, windowPos.y + y, color.getRGB(), false);
	}

	private static ResourceLocation getImageFromColor(TextureColor color) {

		switch (color) {
		case BLACK_1:
			return BG_BLACK_1;
		case BLACK_2:
			return BG_BLACK_2;
		case BLUE_1:
			return BG_BLUE_1;
		case BLUE_2:
			return BG_BLUE_2;
		case GRAY_1:
			return BG_GRAY_1;
		case GRAY_2:
			return BG_GRAY_2;
		case GREEN_1:
			return BG_GREEN_1;
		case GREEN_2:
			return BG_GREEN_2;
		case ORANGE_1:
			return BG_ORANGE_1;
		case ORANGE_2:
			return BG_ORANGE_2;
		case PURPLE_1:
			return BG_PURPLE_1;
		case PURPLE_2:
			return BG_PURPLE_2;
		case RED_1:
			return BG_RED_1;
		case RED_2:
			return BG_RED_2;
		case WHITE_1:
			return BG_WHITE_1;
		case WHITE_2:
			return BG_WHITE_2;
		case YELLOW_1:
			return BG_YELLOW_1;
		case YELLOW_2:
			return BG_YELLOW_2;
		default:
			return BG_PURPLE_1;

		}

	}
}
