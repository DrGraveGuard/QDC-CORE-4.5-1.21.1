package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.screen;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GuiDrawFunctions;
import com.qdc_mod.qdc_core_4_5.api.ModButton;
import com.qdc_mod.qdc_core_4_5.api.ModGuiTitle;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TtleType;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.loot_box.LootBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.loot_box.classes.LootBoxItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.functions.GuiSoundFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.GlobalSettings;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.MainMenuScreenSettings;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SacrificeScreenSettinigs;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SacrificeScreenSettinigs.CONSTANTS;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SacrificeScreenSettinigs.LOOT_ITEM;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SacrificeScreenSettinigs.SACRIFICE_INFO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SacrificeScreen extends Screen {

	private final Point WINDOW_SIZE = SacrificeScreenSettinigs.MAIN_WINDOW.SIZE;
	private final Point WINDOW_POS = new Point(0, 0);

	public enum btnType {

		EXIT_BUTTON

	}

	public class GuiButton extends ModButton {

		public btnType type;

		public GuiButton(Point pos, Point size, String text, btnType type) {
			super(pos, size, text);

			this.type = type;
		}

	}

	private btnType curHoverButtonType = null;
	
	private ItemStack curHoverItem = null;

	public List<GuiButton> mainButtonList = null;

	private GuiButton btnExit = new GuiButton(SacrificeScreenSettinigs.EXIT_BUTTON.POS,
			SacrificeScreenSettinigs.EXIT_BUTTON.SIZE, "Main Menu", btnType.EXIT_BUTTON);

	private List<GuiButton> generateButtonList() {
		List<GuiButton> res = new ArrayList<GuiButton>();

		res.add(btnExit);

		return res;
	}

	public void checkForButtonHover(int mouseX, int mouseY) {

		curHoverButtonType = null;

		for (GuiButton b : mainButtonList) {

			if (b.isEnabled && b.isVisible)
				if (b.checkIfHoveringOver(WINDOW_POS, mouseX, mouseY)) {

					curHoverButtonType = b.type;
				}
		}

	}

	public SacrificeScreen() {
		super(Component.literal("QDC Quantum Sacrifice"));

	}

	@Override
	protected void init() {

		super.init();

		this.WINDOW_POS.x = this.width / 2 - this.WINDOW_SIZE.x / 2;
		this.WINDOW_POS.y = this.height / 2 - this.WINDOW_SIZE.y / 2;

		mainButtonList = generateButtonList();

		LootBox.initLootBox();
		LootBox.setupForScreen();

	}

	public void removed() {
		super.removed();
	}

	@Override
	public boolean isPauseScreen() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void render(GuiGraphics GuiGraphics, int x, int y, float partialTicks) {
		super.render(GuiGraphics, x, y, partialTicks);
		// TODO Auto-generated method stub

		drawMainWindow(GuiGraphics);

		checkForButtonHover(x, y);
		checkForItemHover(x, y);
		drawToolTip(GuiGraphics, x, y);
	}

	private void drawToolTip(GuiGraphics gg, int x, int y) {
		
			if (curHoverItem != null) {
				drawToolTipWindow(gg, curHoverItem, x, y);
			}
	}
	
	private void checkForItemHover(int mouseX, int mouseY) {
		curHoverItem = LootBox.checkIfHoveringOver(WINDOW_POS, mouseX, mouseY);
	}
	private final TextureColor windowFillColor = GlobalSettings.WINDOW.WINDOW_FILL_COLOR;
	private final TextureColor windowBorderColor = GlobalSettings.WINDOW.WINDOW_BORDER_COLOR;
	


	public void drawMainWindow(GuiGraphics gg) {
		// draw main window

		drawMainTitle(gg);
		drawInfoWindow(gg);
		drawSubTitles(gg);
		drawLootItems(gg);
		drawButtons(gg);

	}

	private final Point MAIN_TITLE_POS = SacrificeScreenSettinigs.MAIN_TITLE.POS;
	private final Point MAIN_TITLE_SIZE = SacrificeScreenSettinigs.MAIN_TITLE.SIZE;

	private final ModGuiTitle mainTitleObject = new ModGuiTitle(TtleType.MAIN_TITLE, "Quantum Sacrifice",
			MAIN_TITLE_POS, MAIN_TITLE_SIZE);
	
	public void drawMainTitle(GuiGraphics gg) {

		drawTitle(gg, mainTitleObject);
		
	}
	
	
	private final Point INFO_WINDOW_POS = SacrificeScreenSettinigs.SACRIFICE_INFO.POS;
	private final Point INFO_WINDOW_SIZE = SacrificeScreenSettinigs.SACRIFICE_INFO.SIZE;
	
	private final Point INFO_WINDOW_LINE_ONE_POS = SacrificeScreenSettinigs.SACRIFICE_INFO.LINE_ONE_POS;
	private final Point INFO_WINDOW_LINE_TWO_POS = SacrificeScreenSettinigs.SACRIFICE_INFO.LINE_TWO_POS;
	private final Point INFO_WINDOW_LINE_THREE_POS = SacrificeScreenSettinigs.SACRIFICE_INFO.LINE_THREE_POS;
	

	private final Color INFO_WINDOW_TEXT_COLOR = SacrificeScreenSettinigs.SACRIFICE_INFO.TEXT_COLOR;
	private final TextureColor INFO_WINDOW_BG_COLOR = SacrificeScreenSettinigs.SACRIFICE_INFO.BG_COLOR;

	public void drawInfoWindow(GuiGraphics gg) {

		
		drawRectangleWithBorder(gg, INFO_WINDOW_POS, INFO_WINDOW_SIZE, INFO_WINDOW_BG_COLOR, windowBorderColor);

		writeString(gg, SACRIFICE_INFO.LINE_ONE_TEXT, INFO_WINDOW_LINE_ONE_POS.x, INFO_WINDOW_LINE_ONE_POS.y, INFO_WINDOW_TEXT_COLOR);
		
		writeString(gg, SACRIFICE_INFO.LINE_TWO_TEXT, INFO_WINDOW_LINE_TWO_POS.x, INFO_WINDOW_LINE_TWO_POS.y, INFO_WINDOW_TEXT_COLOR);
		
		writeString(gg, SACRIFICE_INFO.LINE_THREETEXT, INFO_WINDOW_LINE_THREE_POS.x, INFO_WINDOW_LINE_THREE_POS.y, INFO_WINDOW_TEXT_COLOR);
		
	}
	

	private final Color SUB_TITLE_TEXT_COLOR = SacrificeScreenSettinigs.SUB_TITLE.TEXT_COLOR;
	private final TextureColor SUB_TITLE_DECORATION_COLOR = SacrificeScreenSettinigs.SUB_TITLE.DECOR_COLOR;
	private final int SUB_TITLE_DECORATION_HEIGHT = SacrificeScreenSettinigs.SUB_TITLE.DECOR_HEIGHT;

	private final Point SUB_TITLE_DROP_ITEM_TEXT_POS = SacrificeScreenSettinigs.SUB_TITLE.DROP_ITEM.TEXT_POS;
	private final Point SUB_TITLE_DROP_ITEM_DECORATION_POS = SacrificeScreenSettinigs.SUB_TITLE.DROP_ITEM.DECORATION_POS;
	private final int SUB_TITLE_DROP_ITEM_TDECORATION_WIDTH = SacrificeScreenSettinigs.SUB_TITLE.DROP_ITEM.WIDTH;

	private final Point SUB_TITLE_CUR_AMOUNT_TEXT_POS = SacrificeScreenSettinigs.SUB_TITLE.CUR_AMOUNT.TEXT_POS;
	private final Point SUB_TITLE_CUR_AMOUNT_DECORATION_POS = SacrificeScreenSettinigs.SUB_TITLE.CUR_AMOUNT.DECORATION_POS;
	private final int SUB_TITLE_CUR_AMOUNT_TDECORATION_WIDTH = SacrificeScreenSettinigs.SUB_TITLE.CUR_AMOUNT.WIDTH;

	private final Point SUB_TITLE_AMOUNT_LEFT_TEXT_POS = SacrificeScreenSettinigs.SUB_TITLE.AMOUNT_LEFT.TEXT_POS;
	private final Point SUB_TITLE_AMOUNT_LEFT_DECORATION_POS = SacrificeScreenSettinigs.SUB_TITLE.AMOUNT_LEFT.DECORATION_POS;
	private final int SUB_TITLE_AMOUNT_LEFT_TDECORATION_WIDTH = SacrificeScreenSettinigs.SUB_TITLE.AMOUNT_LEFT.WIDTH;

	private final Point SUB_TITLE_PROGRESS_TEXT_POS = SacrificeScreenSettinigs.SUB_TITLE.PROGRESS.TEXT_POS;
	private final Point SUB_TITLE_PROGRESS_DECORATION_POS = SacrificeScreenSettinigs.SUB_TITLE.PROGRESS.DECORATION_POS;
	private final int SUB_TITLE_PROGRESS_TDECORATION_WIDTH = SacrificeScreenSettinigs.SUB_TITLE.PROGRESS.WIDTH;

	private final String SUB_TITLE_DROP_ITEM_TEXT = "Drop to get:";
	private final String SUB_TITLE_CUR_AMOUNT_TEXT = "Cur Damage:";
	private final String SUB_TITLE_AMOUNT_LEFT_TEXT = "Damage Left:";
	private final String SUB_TITLE_PROGRESS_TEXT = "Progress:";

	public void drawSubTitles(GuiGraphics gg) {
		// draw main window

		
		// DROP_ITEM
		writeStringCentred(gg, SUB_TITLE_DROP_ITEM_TEXT, SUB_TITLE_DROP_ITEM_TEXT_POS.x, SUB_TITLE_DROP_ITEM_TEXT_POS.y,
				SUB_TITLE_TEXT_COLOR);
		drawRectangle(gg, SUB_TITLE_DROP_ITEM_DECORATION_POS.x, SUB_TITLE_DROP_ITEM_DECORATION_POS.y,
				SUB_TITLE_DROP_ITEM_TDECORATION_WIDTH, SUB_TITLE_DECORATION_HEIGHT, SUB_TITLE_DECORATION_COLOR);
		
		
		
		
		// CUR_AMOUNT
		writeStringCentred(gg, SUB_TITLE_CUR_AMOUNT_TEXT, SUB_TITLE_CUR_AMOUNT_TEXT_POS.x, SUB_TITLE_CUR_AMOUNT_TEXT_POS.y,
				SUB_TITLE_TEXT_COLOR);
		drawRectangle(gg, SUB_TITLE_CUR_AMOUNT_DECORATION_POS.x, SUB_TITLE_CUR_AMOUNT_DECORATION_POS.y,
				SUB_TITLE_CUR_AMOUNT_TDECORATION_WIDTH, SUB_TITLE_DECORATION_HEIGHT, SUB_TITLE_DECORATION_COLOR);

		
		
		
		// AMOUNT_LEFT
		writeStringCentred(gg, SUB_TITLE_AMOUNT_LEFT_TEXT, SUB_TITLE_AMOUNT_LEFT_TEXT_POS.x, SUB_TITLE_AMOUNT_LEFT_TEXT_POS.y,
				SUB_TITLE_TEXT_COLOR);
		drawRectangle(gg, SUB_TITLE_AMOUNT_LEFT_DECORATION_POS.x, SUB_TITLE_AMOUNT_LEFT_DECORATION_POS.y,
				SUB_TITLE_AMOUNT_LEFT_TDECORATION_WIDTH, SUB_TITLE_DECORATION_HEIGHT, SUB_TITLE_DECORATION_COLOR);

		
		
		
		// PROGRESS
		writeStringCentred(gg, SUB_TITLE_PROGRESS_TEXT, SUB_TITLE_PROGRESS_TEXT_POS.x, SUB_TITLE_PROGRESS_TEXT_POS.y,
				SUB_TITLE_TEXT_COLOR);
		drawRectangle(gg, SUB_TITLE_PROGRESS_DECORATION_POS.x, SUB_TITLE_PROGRESS_DECORATION_POS.y,
				SUB_TITLE_PROGRESS_TDECORATION_WIDTH, SUB_TITLE_DECORATION_HEIGHT, SUB_TITLE_DECORATION_COLOR);


	}
	private final Color LOOT_ITEM_TEXT_COLOR = SacrificeScreenSettinigs.LOOT_ITEM.TEXT_COLOR;
	private final TextureColor LOOT_ITEM_BG = LOOT_ITEM.BG_COLOR;


	private final Point BAR_BORDER_SIZE = SacrificeScreenSettinigs.LOOT_ITEM.BAR_BORDER_SIZE;
	private final int BAR_FILL_HEIGHT = SacrificeScreenSettinigs.LOOT_ITEM.BAR_FILL_HEIGHT;
	private final TextureColor BAR_BODER_BG = LOOT_ITEM.BAR_BG;
	private final TextureColor BAR_BODER_BORDER = LOOT_ITEM.BAR_BORDER;
	private final TextureColor BAR_FILL_BG= LOOT_ITEM.BAR_FILL;
	
	public void drawLootItems(GuiGraphics gg) {
		// draw main window

		for (LootBoxItem item : LootBox.lootItemList) {

			drawRectangleWithBorder(gg, item.pos, item.size, LOOT_ITEM_BG, windowBorderColor);
			drawItemIcon(gg, item.toDrop, item.iconPos.x, item.iconPos.y);
			
			writeStringCentred(gg, item.curDamageStr, item.curDamagePos.x,item.curDamagePos.y,
					LOOT_ITEM_TEXT_COLOR);
			
			writeStringCentred(gg, item.damageLeftStr, item.damageLeftPos.x,item.damageLeftPos.y,
					LOOT_ITEM_TEXT_COLOR);
			
			
			drawRectangleWithBorder(gg, item.progressBarBorderPos, BAR_BORDER_SIZE, BAR_BODER_BG, BAR_BODER_BORDER);
			drawRectangle(gg, item.progressBarFillPos.x, item.progressBarFillPos.y,item.progressBarFillWidth,BAR_FILL_HEIGHT , BAR_FILL_BG);
			
			writeStringCentred(gg, item.progressStr, item.progressStrPos.x,item.progressStrPos.y,
					LOOT_ITEM_TEXT_COLOR);
			
		}

	}

	private void drawTitle(GuiGraphics gg, ModGuiTitle titleObj) {

		GuiDrawFunctions.drawTitle(gg, font, WINDOW_POS, titleObj);

	}

	public void drawButtons(GuiGraphics gg) {
		for (GuiButton button : mainButtonList) {

			drawButton(gg, button);
		}
	}
	private void drawButton(GuiGraphics gg, ModButton btn) {

		GuiDrawFunctions.drawButton(gg,font, WINDOW_POS, btn);

	}


	public void drawWindowTitle(GuiGraphics gg, Point pos, Point size, String text) {
		GuiDrawFunctions.drawWindowTitle(gg, WINDOW_POS, pos, size, font, text);
	}

	private void drawItemIcon(GuiGraphics gg, Item item, int x, int y) {
		GuiDrawFunctions.drawItemIcon(gg, font, WINDOW_POS, item, x, y);
	}

	private void drawItemIcon(GuiGraphics gg, ItemStack stack, int x, int y) {
		GuiDrawFunctions.drawItemIcon(gg, font, WINDOW_POS, stack, x, y);
	}

//	private void drawItemIcon(GuiGraphics gg, Item item, int x, int y, int count) {
//		GuiDrawFunctions.drawItemIcon(gg, font, WINDOW_POS, item, x, y, count);
//	}

	private void drawItemIcon(GuiGraphics gg, ItemStack stack, int x, int y, int count) {
		GuiDrawFunctions.drawItemIcon(gg, font, WINDOW_POS, stack, x, y, count);
	}

	private void drawToolTipWindow(GuiGraphics gg, ItemStack itemStack, int x, int y) {

		if (itemStack.getItem() != Items.AIR)
			GuiDrawFunctions.drawToolTipWindow(gg, this.font, itemStack, x, y);
	}

	private void drawRectangleWithBorder(GuiGraphics gg, Point pos, Point size, TextureColor fillColor,
			TextureColor borderColor) {
		GuiDrawFunctions.drawRectangleWithBorder(gg, WINDOW_POS, pos, size, fillColor, borderColor);
	}

	private void drawRectangle(GuiGraphics gg, int xPos, int yPos, int width, int height, TextureColor color) {

		GuiDrawFunctions.drawRectangle(gg, WINDOW_POS, xPos, yPos, width, height, color);

	}

	private void writeString(GuiGraphics gg, String text, int x, int y, Color color) {
		GuiDrawFunctions.writeString(gg, font, WINDOW_POS, text, x, y, color);

	}

	private void writeStringCentred(GuiGraphics gg, String text, int x, int y, Color color) {
		GuiDrawFunctions.writeStringCentred(gg, font, WINDOW_POS, text, x, y, color);

	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {

		if (curHoverButtonType != null) {
			switch (curHoverButtonType) {

			case EXIT_BUTTON:

				getMinecraft().setScreen(new MainMenuScreen());
				playClickSound();

				break;

			default:
				break;

			}

		}

		return false;
	}

	private boolean isInCreative() {
		return Minecraft.getInstance().player.isCreative();
	}

	private void playClickSound() {
		GuiSoundFunctions.playClickSound(Minecraft.getInstance().player);
	}

	private void playErrorSound() {
		GuiSoundFunctions.playErrorSound(Minecraft.getInstance().player);
	}
}
