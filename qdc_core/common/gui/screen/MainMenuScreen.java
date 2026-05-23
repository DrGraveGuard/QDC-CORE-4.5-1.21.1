package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.screen;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.GuiDrawFunctions;
import com.qdc_mod.qdc_core_4_5.api.ModButton;
import com.qdc_mod.qdc_core_4_5.api.ModGuiTitle;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TtleType;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.main_menu.MainMenuItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.functions.GuiSoundFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.GlobalSettings;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.MainMenuScreenSettings;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.MainMenuScreenSettings.CONSTANTS;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.MainMenuScreenSettings.MENU_BUTTON;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class MainMenuScreen extends Screen {



	private final Point WINDOW_SIZE = MainMenuScreenSettings.MAIN_WINDOW.SIZE;
	private final Point WINDOW_POS = new Point(0, 0);

	public enum btnType {

		MAIN_MENU_BUTTON

	}

	public class GuiButton extends ModButton {

		public btnType type;
		public int index = -1;
		public MainMenuItem menuItem = null;

		public GuiButton(Point pos, Point size, String text, btnType type) {
			super(pos, size, text);

			this.type = type;
		}

		public GuiButton(MainMenuItem menuItem) {
			super(CONSTANTS.BUTTON.SIZE, menuItem.displayText);

			this.menuItem = menuItem;
			this.type = btnType.MAIN_MENU_BUTTON;
		}

		public void setPos(Point pos) {
			super.setPos(pos);
		}

		public void setIndex(int index) {
			this.index = index;
		}
		
		public void openScreen()
		{
			menuItem.openScreen();
		}
		
	}

	public List<GuiButton> mainButtonList = null;
	public List<GuiButton> rawMainMenuButtonList = null;
	public List<GuiButton> finalMainMenuButtonList = null;

	private btnType curHoverButtonType = null;
	private int curHoverButtonIndex = -1;

	// --------------------------
	// MAIN BUTTONOS

	private GuiButton btnAssemblerDisassembler = new GuiButton(new MainMenuItem("QDC Assembler/Disassembler",
			"com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.screen.AssemblerScreen", Qdc.MOD_ID));

	private GuiButton btnSettings = new GuiButton(new MainMenuItem("Item Particle Settings",
			"com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.screen.SettingsScreen", Qdc.MOD_ID));

	private GuiButton btnSacrifice = new GuiButton(new MainMenuItem("QDC Quantum Sacrifice",
			"com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.screen.SacrificeScreen", Qdc.MOD_ID));

	private GuiButton btnEnchanter = new GuiButton(new MainMenuItem("QDC Quantum Enchanter",
			"com.qdc_mod.qdc_quantum_enchanter.quantum_enchanter.common.gui.screen.QuantumEnchanterScreen", "qdc_quantum_enchanter"));
	
	private GuiButton btnPotionOverload = new GuiButton(new MainMenuItem("QDC Quantum Potion Overload",
			"com.qdc_mod.qdc_quantum_potion_overload.quantum_potion_overload.common.gui.screen.PotionOverloadScreen", "qdc_quantum_potion_overload"));
	
	private List<GuiButton> initRawMainMenuButtons() {
		List<GuiButton> res = new ArrayList<MainMenuScreen.GuiButton>();

		res.add(btnAssemblerDisassembler);
		res.add(btnSettings);
		res.add(btnSacrifice);
		res.add(btnEnchanter);
		res.add(btnPotionOverload);

		return res;
	}

	private List<GuiButton> generateFinalMainMenuButtonList() {
		List<GuiButton> res = new ArrayList<MainMenuScreen.GuiButton>();

		int index = 0;

		for (GuiButton btn : rawMainMenuButtonList) {
			if (btn.menuItem.isValid()) {
				btn.setPos(MENU_BUTTON.ITEMS.ITEM_POS_LIST.get(index));
				btn.setIndex(index);
				res.add(btn);

				index++;
			}
		}

		return res;
	}

	public void checkForButtonHover(int mouseX, int mouseY) {
		
		curHoverButtonType = null;
		curHoverButtonIndex = -1;
		
		for (GuiButton b : mainButtonList) {

			if (b.isEnabled && b.isVisible)
				if (b.checkIfHoveringOver(WINDOW_POS, mouseX, mouseY)) {

					curHoverButtonType = b.type;
					curHoverButtonIndex = b.index;
				}
		}

	}

	public MainMenuScreen() {
		super(Component.literal("QDC Main Menu"));

	}

	@Override
	protected void init() {

		super.init();

		this.WINDOW_POS.x = this.width / 2 - this.WINDOW_SIZE.x / 2;
		this.WINDOW_POS.y = this.height / 2 - this.WINDOW_SIZE.y / 2;

		rawMainMenuButtonList = initRawMainMenuButtons();

		finalMainMenuButtonList = generateFinalMainMenuButtonList();

		mainButtonList = new ArrayList<GuiButton>();

		mainButtonList.addAll(finalMainMenuButtonList);

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

	}

	private final TextureColor windowFillColor = GlobalSettings.WINDOW.WINDOW_FILL_COLOR;
	private final TextureColor windowBorderColor = GlobalSettings.WINDOW.WINDOW_BORDER_COLOR;



	public void drawMainWindow(GuiGraphics gg) {
		// draw main window

		drawMainTitle(gg);

		drawButtons(gg);

	}

	private final Point MAIN_TITLE_POS = MainMenuScreenSettings.MAIN_TITLE.POS;
	private final Point MAIN_TITLE_SIZE = MainMenuScreenSettings.MAIN_TITLE.SIZE;

	private final ModGuiTitle titleObj = new ModGuiTitle(TtleType.MAIN_TITLE, "QDC Main Menu", MAIN_TITLE_POS, MAIN_TITLE_SIZE);
	
	
	public void drawMainTitle(GuiGraphics gg) {

		drawTitle(gg, titleObj);
	}
	





	public void drawButtons(GuiGraphics gg) {
		for (GuiButton button : mainButtonList) {

			drawButton(gg, button);


		}
	}
	
	private void drawTitle(GuiGraphics gg, ModGuiTitle titleObj) {

		GuiDrawFunctions.drawTitle(gg,font, WINDOW_POS, titleObj);

	}
	

	private void drawButton(GuiGraphics gg, ModButton btn) {

		GuiDrawFunctions.drawButton(gg,font, WINDOW_POS, btn);

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

			case MAIN_MENU_BUTTON:

				if(curHoverButtonIndex > -1)
				{
					finalMainMenuButtonList.get(curHoverButtonIndex).openScreen();
					playClickSound();
				}
				
				
				break;

			default:
				break;

			}

		}

		return false;
	}

	private void playClickSound() {
		GuiSoundFunctions.playClickSound(Minecraft.getInstance().player);
	}

	private void playErrorSound() {
		GuiSoundFunctions.playErrorSound(Minecraft.getInstance().player);
	}
}
