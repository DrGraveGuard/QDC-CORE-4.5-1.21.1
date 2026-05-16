package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.screen;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.Qdc;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.GuiDrawFunctions;
import com.qdc_mod.qdc_core_4_5.api.ModButton;
import com.qdc_mod.qdc_core_4_5.api.ModGuiTitle;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TtleType;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.MainBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.settings_screen.CfgDisplayItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.settings_screen.CfgItemPosItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.settings_screen.AffectedItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.settings_screen.SearchResultBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.settings_screen.SearchResultItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.settings_screen.SettingsWindow;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.functions.GuiSoundFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.GlobalSettings;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SettingsScreenSettings;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SettingsScreenSettings.CONSTANTS;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SettingsScreen extends Screen {

	private SearchResultBox searchResultBox = new SearchResultBox();

	private SettingsWindow settingsWindow = null;

	private final Point WINDOW_SIZE = SettingsScreenSettings.MAIN_WINDOW.SIZE;
	private final Point WINDOW_POS = new Point(0, 0);

	public enum btnType {

		EXIT_BUTTON, ITEM_LIST_PREV, ITEM_LIST_NEXT,

		EDIT_NATURE, SAVE_NATURE, CANCEL_NATURE, DEFAULT_NATURE,

		EDIT_FOOD, SAVE_FOOD, CANCEL_FOOD, DEFAULT_FOOD,

		EDIT_METAL, SAVE_METAL, CANCEL_METAL, DEFAULT_METAL,

		EDIT_GEM, SAVE_GEM, CANCEL_GEM, DEFAULT_GEM,

		AFFECTED_ITEMS_PREV, AFFECTED_ITEMS_NEXT,

	}

	public class GuiButton extends ModButton {

		public btnType type;

		public GuiButton(Point pos, Point size, String text, btnType type) {
			super(pos, size, text);

			this.type = type;
		}

		public GuiButton(Point pos, int width, int height, String text, btnType type) {
			super(pos, new Point(width, height), text);

			this.type = type;
		}

	}

	public static final String EDIT_TEXT = "Edit";
	public static final String SAVE_TEXT = "Save";
	public static final String CANCEL_TEXT = "Cancel";
	public static final String DEFAULT_TEXT = "Set to Default";

	public List<CfgItemPosItem> cfgPosItemList = null;

	private CfgItemPosItem cfgPosItemNature = new CfgItemPosItem(
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_ITEM.NATURE_POS);
	private CfgItemPosItem cfgPosItemFood = new CfgItemPosItem(
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_ITEM.FOOD_POS);
	private CfgItemPosItem cfgPosItemMetal = new CfgItemPosItem(
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_ITEM.METAL_POS);
	private CfgItemPosItem cfgPosItemGem = new CfgItemPosItem(SettingsScreenSettings.INFO_WINDOW.SETTINGS_ITEM.GEM_POS);

	private List<CfgItemPosItem> initCfgPosItemList() {
		List<CfgItemPosItem> res = new ArrayList<CfgItemPosItem>();

		res.add(cfgPosItemNature);
		res.add(cfgPosItemFood);
		res.add(cfgPosItemMetal);
		res.add(cfgPosItemGem);

		return res;
	}

	public List<GuiButton> mainButtonList = null;
	public List<GuiButton> nature_ButtonList = null;
	public List<GuiButton> food_ButtonList = null;
	public List<GuiButton> metal_ButtonList = null;
	public List<GuiButton> gem_ButtonList = null;
	public List<GuiButton> affectedItemsButtonList = null;

	private btnType curHoverItem = null;

	// --------------------------
	// MAIN BUTTONOS

	private GuiButton btnExit = new GuiButton(SettingsScreenSettings.EXIT_BUTTON.POS,
			SettingsScreenSettings.EXIT_BUTTON.SIZE, "Main Menu", btnType.EXIT_BUTTON);

	private GuiButton btnPrev = new GuiButton(SettingsScreenSettings.SEARCH_RESULT_WINDOW.BUTTONS.PREV.POS,
			SettingsScreenSettings.CONSTANTS.BUTTON.SIZE, "<", btnType.ITEM_LIST_PREV);

	private GuiButton btnNext = new GuiButton(SettingsScreenSettings.SEARCH_RESULT_WINDOW.BUTTONS.NEXT.POS,
			SettingsScreenSettings.CONSTANTS.BUTTON.SIZE, ">", btnType.ITEM_LIST_NEXT);

	private List<GuiButton> initGuiButtons() {
		List<GuiButton> res = new ArrayList<SettingsScreen.GuiButton>();

		res.add(btnExit);

		res.add(btnPrev);

		res.add(btnNext);

		return res;
	}

	// --------------------------
	// cfg buttons

	// --------------------------
	// CFG BUTTONS NATURE

	private GuiButton btnEditNature = new GuiButton(cfgPosItemNature.editPos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.EDIT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, EDIT_TEXT, btnType.EDIT_NATURE);

	private GuiButton btnSaveNature = new GuiButton(cfgPosItemNature.savePos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.EDIT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, SAVE_TEXT, btnType.SAVE_NATURE);

	private GuiButton btnCancelNature = new GuiButton(cfgPosItemNature.cancelPos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.EDIT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, CANCEL_TEXT, btnType.CANCEL_NATURE);

	private GuiButton btnDefaultNature = new GuiButton(cfgPosItemNature.setToDefaultPos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.SET_TO_DEFAULT_AMOUNT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, DEFAULT_TEXT, btnType.DEFAULT_NATURE);

	private List<GuiButton> initGuiButtons_nature() {
		List<GuiButton> res = new ArrayList<SettingsScreen.GuiButton>();

		res.add(btnEditNature);
		res.add(btnSaveNature);
		res.add(btnCancelNature);
		res.add(btnDefaultNature);

		// disbleButtonsInList(res);

		return res;
	}

	// --------------------------
	// CFG BUTTONS FOOD

	private GuiButton btnEditFood = new GuiButton(cfgPosItemFood.editPos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.EDIT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, EDIT_TEXT, btnType.EDIT_FOOD);

	private GuiButton btnSaveFood = new GuiButton(cfgPosItemFood.savePos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.EDIT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, SAVE_TEXT, btnType.SAVE_FOOD);

	private GuiButton btnCancelFood = new GuiButton(cfgPosItemFood.cancelPos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.EDIT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, CANCEL_TEXT, btnType.CANCEL_FOOD);

	private GuiButton btnDefaultFood = new GuiButton(cfgPosItemFood.setToDefaultPos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.SET_TO_DEFAULT_AMOUNT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, DEFAULT_TEXT, btnType.DEFAULT_FOOD);

	private List<GuiButton> initGuiButtons_food() {
		List<GuiButton> res = new ArrayList<SettingsScreen.GuiButton>();

		res.add(btnEditFood);
		res.add(btnSaveFood);
		res.add(btnCancelFood);
		res.add(btnDefaultFood);

		// disbleButtonsInList(res);

		return res;
	}

	// --------------------------
	// CFG BUTTONS METAL

	private GuiButton btnEditMetal = new GuiButton(cfgPosItemMetal.editPos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.EDIT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, EDIT_TEXT, btnType.EDIT_METAL);

	private GuiButton btnSaveMetal = new GuiButton(cfgPosItemMetal.savePos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.EDIT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, SAVE_TEXT, btnType.SAVE_METAL);

	private GuiButton btnCancelMetal = new GuiButton(cfgPosItemMetal.cancelPos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.EDIT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, CANCEL_TEXT, btnType.CANCEL_METAL);

	private GuiButton btnDefaultMetal = new GuiButton(cfgPosItemMetal.setToDefaultPos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.SET_TO_DEFAULT_AMOUNT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, DEFAULT_TEXT, btnType.DEFAULT_METAL);

	private List<GuiButton> initGuiButtons_metal() {
		List<GuiButton> res = new ArrayList<SettingsScreen.GuiButton>();

		res.add(btnEditMetal);
		res.add(btnSaveMetal);
		res.add(btnCancelMetal);
		res.add(btnDefaultMetal);

		// disbleButtonsInList(res);

		return res;
	}

	// --------------------------
	// CFG BUTTONS GEM

	private GuiButton btnEditGem = new GuiButton(cfgPosItemGem.editPos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.EDIT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, EDIT_TEXT, btnType.EDIT_GEM);

	private GuiButton btnSaveGem = new GuiButton(cfgPosItemGem.savePos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.EDIT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, SAVE_TEXT, btnType.SAVE_GEM);

	private GuiButton btnCancelGem = new GuiButton(cfgPosItemGem.cancelPos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.EDIT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, CANCEL_TEXT, btnType.CANCEL_GEM);

	private GuiButton btnDefaultGem = new GuiButton(cfgPosItemGem.setToDefaultPos,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.SET_TO_DEFAULT_AMOUNT.WIDTH,
			SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.BUTTON_HEIGHT, DEFAULT_TEXT, btnType.DEFAULT_GEM);

	private List<GuiButton> initGuiButtons_gem() {
		List<GuiButton> res = new ArrayList<SettingsScreen.GuiButton>();

		res.add(btnEditGem);
		res.add(btnSaveGem);
		res.add(btnCancelGem);
		res.add(btnDefaultGem);

		return res;
	}

	// --------------------------
	// AFFECTED ITEMS BUTTONS

	private GuiButton btnAffectedItemsPrev = new GuiButton(
			SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.BUTTONS.PREV.POS,
			SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.BUTTONS.SIZE, "<", btnType.AFFECTED_ITEMS_PREV);

	private GuiButton btnAffectedItemsNext = new GuiButton(
			SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.BUTTONS.NEXT.POS,
			SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.BUTTONS.SIZE, ">", btnType.AFFECTED_ITEMS_NEXT);

	private List<GuiButton> initAffectedItemsButtonList() {
		List<GuiButton> res = new ArrayList<SettingsScreen.GuiButton>();

		res.add(btnAffectedItemsPrev);
		res.add(btnAffectedItemsNext);

		return res;
	}

	public btnType isHoveringOverButton(int mouseX, int mouseY) {
		for (GuiButton b : mainButtonList) {

			if (b.isEnabled && b.isVisible)
				if (b.checkIfHoveringOver(WINDOW_POS, mouseX, mouseY)) {

					return b.type;
				}
		}

		return null;
	}

	private void disbleButtonsInList(List<GuiButton> buttons) {
		for (GuiButton b : buttons) {
			b.setEnabledState(false);
			b.setVisibleState(false);
		}

	}

	public SettingsScreen() {
		super(Component.literal("QDC Particle Settings Screen"));

	}

	private final Point TXT_POS = SettingsScreenSettings.SEARCH_WINDOW.POS;
	private final Point TXT_SIZE = SettingsScreenSettings.SEARCH_WINDOW.SIZE;
	EditBox txtSearch, txtNature, txtFood, txtMetal, txtGem;

	private List<EditBox> txtList;

	private String txtDefaultText = "0.00";

	public String msg_text_title = "";
	public Color msg_color_title = SettingsScreenSettings.INFO_WINDOW.MSG_AREA.TITLE_TEXT_COLOR;

	public String msg_text = "";
	public Color msg_color = SettingsScreenSettings.INFO_WINDOW.MSG_AREA.TEXT_COLOR_DEFAULT;

	public void setTitleMsg(String msg) {
		msg_text_title = msg;
	}

	public void setMsg(String msg, Color color) {
		msg_text = msg;
		msg_color = color;
	}

	public void setDefaultMsg() {
		msg_text = "";
		msg_color = SettingsScreenSettings.INFO_WINDOW.MSG_AREA.TEXT_COLOR_DEFAULT;
	}

	private void resetData() {

		if (needsUpdate) {

			setMsg("Reloading data!!", Color.red);

			MainBox.clearModData();
			MainBox.processItems(Qdc.serverLevel);
		}
	}

	private boolean needsUpdate = false;

	private void setNeedsUpdate() {
		needsUpdate = true;
	}

	@Override
	protected void init() {

		super.init();

		this.WINDOW_POS.x = this.width / 2 - this.WINDOW_SIZE.x / 2;
		this.WINDOW_POS.y = this.height / 2 - this.WINDOW_SIZE.y / 2;

		settingsWindow = null;

		this.WINDOW_POS.x = this.width / 2 - this.WINDOW_SIZE.x / 2;
		this.WINDOW_POS.y = this.height / 2 - this.WINDOW_SIZE.y / 2;

		this.txtSearch = new EditBox(this.font, this.WINDOW_POS.x + TXT_POS.x, this.WINDOW_POS.y + TXT_POS.y,
				TXT_SIZE.x, TXT_SIZE.y, Component.literal("txtSearch"));
		this.txtSearch.setCanLoseFocus(true);
		this.txtSearch.setTextColor(Color.white.getRGB());
		this.txtSearch.setBordered(true);
		this.txtSearch.setMaxLength(20);
		this.txtSearch.setResponder(this::onSearchChanged);
		this.txtSearch.setValue(Qdc.SettingsScreenVariables.searchString);
		this.addRenderableWidget(this.txtSearch);
		this.txtSearch.setEditable(true);

		// --------------------------
		// TXT Nature

		this.txtNature = new EditBox(this.font, this.WINDOW_POS.x + cfgPosItemNature.txtPos.x,
				this.WINDOW_POS.y + cfgPosItemNature.txtPos.y,
				SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.CUR_AMOUNT.WIDTH + 5,
				SettingsScreenSettings.INFO_WINDOW.SETTINGS_ITEM.TXT_HEIGHT, Component.literal("txtNature"));
		this.txtNature.setCanLoseFocus(true);
		this.txtNature.setTextColor(Color.white.getRGB());
		this.txtNature.setBordered(true);
		this.txtNature.setMaxLength(Qdc.SettingsScreenVariables.txtLenNature);

		this.txtNature.setValue(txtDefaultText);
		this.txtNature.setFilter((string) -> string.isEmpty() || string.matches("^\\d*\\.?\\d*$"));
		this.txtNature.setResponder(s -> {
			if (!s.isEmpty()) {
				if (!s.matches("^\\d*\\.?\\d{0,2}$")) {
					String corrected = s.substring(0, s.indexOf(".") + 3);
					this.txtNature.setValue(corrected);
				}
				if (s.length() == Qdc.SettingsScreenVariables.txtLenNature && s.endsWith(".")) {
					String corrected = s.substring(0, s.indexOf("."));
					this.txtNature.setValue(corrected);
				}
				if (s.startsWith(".")) {
					String corrected = "0.";
					this.txtNature.setValue(corrected);
				}
			}

			onNatureChanged(s);
		});
		this.addRenderableWidget(this.txtNature);

		// --------------------------
		// TXT Food

		this.txtFood = new EditBox(this.font, this.WINDOW_POS.x + cfgPosItemFood.txtPos.x,
				this.WINDOW_POS.y + cfgPosItemFood.txtPos.y,
				SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.CUR_AMOUNT.WIDTH + 5,
				SettingsScreenSettings.INFO_WINDOW.SETTINGS_ITEM.TXT_HEIGHT, Component.literal("txtFood"));
		this.txtFood.setCanLoseFocus(true);
		this.txtFood.setTextColor(Color.white.getRGB());
		this.txtFood.setBordered(true);
		this.txtFood.setMaxLength(Qdc.SettingsScreenVariables.txtLenFood);

		this.txtFood.setValue(txtDefaultText);
		this.txtFood.setFilter((string) -> string.isEmpty() || string.matches("^\\d*\\.?\\d*$"));
		this.txtFood.setResponder(s -> {
			if (!s.isEmpty()) {
				if (!s.matches("^\\d*\\.?\\d{0,2}$")) {
					String corrected = s.substring(0, s.indexOf(".") + 3);
					this.txtFood.setValue(corrected);
				}
				if (s.length() == Qdc.SettingsScreenVariables.txtLenFood && s.endsWith(".")) {
					String corrected = s.substring(0, s.indexOf("."));
					this.txtFood.setValue(corrected);
				}
				if (s.startsWith(".")) {
					String corrected = "0.";
					this.txtFood.setValue(corrected);
				}
			}

			onFoodChanged(s);
		});
		this.addRenderableWidget(this.txtFood);

		// --------------------------
		// TXT Metal

		this.txtMetal = new EditBox(this.font, this.WINDOW_POS.x + cfgPosItemMetal.txtPos.x,
				this.WINDOW_POS.y + cfgPosItemMetal.txtPos.y,
				SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.CUR_AMOUNT.WIDTH + 5,
				SettingsScreenSettings.INFO_WINDOW.SETTINGS_ITEM.TXT_HEIGHT, Component.literal("txtMetal"));
		this.txtMetal.setCanLoseFocus(true);
		this.txtMetal.setTextColor(Color.white.getRGB());
		this.txtMetal.setBordered(true);
		this.txtMetal.setMaxLength(Qdc.SettingsScreenVariables.txtLenMetal);

		this.txtMetal.setValue(txtDefaultText);
		this.txtMetal.setFilter((string) -> string.isEmpty() || string.matches("^\\d*\\.?\\d*$"));
		this.txtMetal.setResponder(s -> {
			if (!s.isEmpty()) {
				if (!s.matches("^\\d*\\.?\\d{0,2}$")) {
					String corrected = s.substring(0, s.indexOf(".") + 3);
					this.txtMetal.setValue(corrected);
				}
				if (s.length() == Qdc.SettingsScreenVariables.txtLenMetal && s.endsWith(".")) {
					String corrected = s.substring(0, s.indexOf("."));
					this.txtMetal.setValue(corrected);
				}
				if (s.startsWith(".")) {
					String corrected = "0.";
					this.txtMetal.setValue(corrected);
				}
			}

			onMetalChanged(s);
		});
		this.addRenderableWidget(this.txtMetal);

		// --------------------------
		// TXT Gem

		this.txtGem = new EditBox(this.font, this.WINDOW_POS.x + cfgPosItemGem.txtPos.x,
				this.WINDOW_POS.y + cfgPosItemGem.txtPos.y,
				SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.CUR_AMOUNT.WIDTH + 5,
				SettingsScreenSettings.INFO_WINDOW.SETTINGS_ITEM.TXT_HEIGHT, Component.literal("txtGem"));
		this.txtGem.setCanLoseFocus(true);
		this.txtGem.setTextColor(Color.white.getRGB());
		this.txtGem.setBordered(true);
		this.txtGem.setMaxLength(Qdc.SettingsScreenVariables.txtLenGem);

		this.txtGem.setValue(txtDefaultText);
		this.txtGem.setFilter((string) -> string.isEmpty() || string.matches("^\\d*\\.?\\d*$"));
		this.txtGem.setResponder(s -> {
			if (!s.isEmpty()) {
				if (!s.matches("^\\d*\\.?\\d{0,2}$")) {
					String corrected = s.substring(0, s.indexOf(".") + 3);
					this.txtGem.setValue(corrected);
				}
				if (s.length() == Qdc.SettingsScreenVariables.txtLenGem && s.endsWith(".")) {
					String corrected = s.substring(0, s.indexOf("."));
					this.txtGem.setValue(corrected);
				}
				if (s.startsWith(".")) {
					String corrected = "0.";
					this.txtGem.setValue(corrected);
				}
			}

			onGemChanged(s);
		});
		this.addRenderableWidget(this.txtGem);

		if (cfgPosItemList == null)
			cfgPosItemList = initCfgPosItemList();

		nature_ButtonList = initGuiButtons_nature();

		food_ButtonList = initGuiButtons_food();

		metal_ButtonList = initGuiButtons_metal();

		gem_ButtonList = initGuiButtons_gem();

		affectedItemsButtonList = initAffectedItemsButtonList();

		mainButtonList = initGuiButtons();

		mainButtonList.addAll(nature_ButtonList);
		mainButtonList.addAll(food_ButtonList);
		mainButtonList.addAll(metal_ButtonList);
		mainButtonList.addAll(gem_ButtonList);

		mainButtonList.addAll(affectedItemsButtonList);

		txtList = generateTextBoxList();

		setDefaultMsg();

		setGuiComponentsToDefault();

	}

	private void setGuiComponentsToDefault() {
		setTextBoxesToDefault();

		disbleButtonsInList(nature_ButtonList);
		disbleButtonsInList(food_ButtonList);
		disbleButtonsInList(metal_ButtonList);
		disbleButtonsInList(gem_ButtonList);

		disbleButtonsInList(affectedItemsButtonList);
	}

	private void setTextBoxesToDefault() {
		disableAllTextBoxes();

		txtSearch.setEditable(true);
		txtSearch.setFocused(true);
		txtSearch.setVisible(true);
		this.setFocused(txtSearch);
	}

	private void disableAllTextBoxes() {
		for (EditBox txt : txtList) {
			txt.setEditable(false);
			txt.setFocused(false);
			txt.setVisible(false);
		}
	}

	private List<EditBox> generateTextBoxList() {
		List<EditBox> res = new ArrayList<EditBox>();

		res.add(txtNature);
		res.add(txtFood);
		res.add(txtMetal);
		res.add(txtGem);
		res.add(txtSearch);

		return res;
	}

	public void onSearchChanged(String search) {
		Qdc.SettingsScreenVariables.searchString = search;

		searchResultBox.doSearch();
	}

	public void onNatureChanged(String str) {

		if (settingsWindow != null)
			settingsWindow.natureDisplayItem.onTxtChange();

	}

	public void onFoodChanged(String str) {

		if (settingsWindow != null)
			settingsWindow.foodDisplayItem.onTxtChange();
	}

	public void onMetalChanged(String str) {
		if (settingsWindow != null)
			settingsWindow.metalDisplayItem.onTxtChange();
	}

	public void onGemChanged(String str) {
		if (settingsWindow != null)
			settingsWindow.gemDisplayItem.onTxtChange();
	}

	@Override
	protected void setInitialFocus() {
		this.setInitialFocus(this.txtSearch);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (keyCode == 256) {

			if (isInCreative()) {
				resetData();
			}
			this.minecraft.player.closeContainer();
		}

		if (txtNature.isFocused()) {
			return !this.txtNature.keyPressed(keyCode, scanCode, modifiers) && !this.txtNature.canConsumeInput()
					? super.keyPressed(keyCode, scanCode, modifiers)
					: true;
		} else if (txtFood.isFocused()) {
			return !this.txtFood.keyPressed(keyCode, scanCode, modifiers) && !this.txtFood.canConsumeInput()
					? super.keyPressed(keyCode, scanCode, modifiers)
					: true;
		} else if (txtMetal.isFocused()) {
			return !this.txtMetal.keyPressed(keyCode, scanCode, modifiers) && !this.txtMetal.canConsumeInput()
					? super.keyPressed(keyCode, scanCode, modifiers)
					: true;
		} else if (txtGem.isFocused()) {
			return !this.txtGem.keyPressed(keyCode, scanCode, modifiers) && !this.txtGem.canConsumeInput()
					? super.keyPressed(keyCode, scanCode, modifiers)
					: true;
		} else {
			return !this.txtSearch.keyPressed(keyCode, scanCode, modifiers) && !this.txtSearch.canConsumeInput()
					? super.keyPressed(keyCode, scanCode, modifiers)
					: true;
		}

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

		curHoverItem = isHoveringOverButton(x, y);

		searchResultBox.checkIfHoveringOver(WINDOW_POS, x, y);

		if (settingsWindow != null)
			settingsWindow.checkIfHoveringOver(WINDOW_POS, x, y);

		drawToolTip(GuiGraphics, x, y);

	}

	private final TextureColor windowFillColor = GlobalSettings.WINDOW.WINDOW_FILL_COLOR;
	private final TextureColor windowBorderColor = GlobalSettings.WINDOW.WINDOW_BORDER_COLOR;




	public void drawMainWindow(GuiGraphics gg) {
		// draw main window

		drawMainTitle(gg);
		drawSearchSection(gg);
		drawSearchResultSection(gg);

		if (isInCreative())

			if (settingsWindow != null)
				drawInfoSection(gg);
			else
				drawInfoWindow(gg);
		else
			drawNotCreativeErrorWindow(gg);

		drawButtons(gg);

		tickCurDataItem();
	}

	private void tickCurDataItem() {
		searchResultBox.tick();
	}

	private void drawToolTip(GuiGraphics gg, int x, int y) {
		if (searchResultBox.curWindowTab != null)
			if (searchResultBox.curWindowTab.curItemHover != null) {
				drawToolTipWindow(gg, searchResultBox.curWindowTab.curItemHover.itemData.curDisplayItem, x, y);
			}

		if (settingsWindow != null) {
			if (settingsWindow.affectedItemBox.curWindowTab.curItemHover != null) {

				drawToolTipWindow(gg, settingsWindow.affectedItemBox.curWindowTab.curItemHover.stack, x, y);

			}
		}
		if (settingsWindow != null) {
			if (settingsWindow.curHoverParticle != null) {

				drawToolTipWindow(gg, settingsWindow.curHoverParticle, x, y);

			}
		}

	}

	private final Point MAIN_TITLE_POS = SettingsScreenSettings.MAIN_TITLE.POS;
	private final Point MAIN_TITLE_SIZE = SettingsScreenSettings.MAIN_TITLE.SIZE;

	private final ModGuiTitle mainTitleObject = new ModGuiTitle(TtleType.MAIN_TITLE, "Item Particle Configuration Screen",
			MAIN_TITLE_POS, MAIN_TITLE_SIZE);
	
	public void drawMainTitle(GuiGraphics gg) {

		drawTitle(gg, mainTitleObject);

	}

	public void drawButtons(GuiGraphics gg) {
		for (GuiButton button : mainButtonList) {

			drawButton(gg, button);
		}
	}

	private final Point SEARCH_POS = SettingsScreenSettings.SEARCH_WINDOW.POS;
	private final Point SEARCH_SIZE = SettingsScreenSettings.SEARCH_WINDOW.SIZE;

	public void drawSearchSection(GuiGraphics gg) {
		// draw main window
		drawRectangleWithBorder(gg, SEARCH_POS, SEARCH_SIZE, windowFillColor, windowBorderColor);

	}

	private final Point SEARCH_RESULT_WINDOW_WINDOW_POS = SettingsScreenSettings.SEARCH_RESULT_WINDOW.POS;
	private final Point SEARCH_RESULT_WINDOW_WINDOW_SIZE = SettingsScreenSettings.SEARCH_RESULT_WINDOW.SIZE;

	private final Point ITEM_SIZE = SettingsScreenSettings.SEARCH_RESULT_WINDOW.ITEMS.SIZE;

	private final Point SEARCH_RESULT_WINDOW_TAB_INDEX_POS = SettingsScreenSettings.SEARCH_RESULT_WINDOW.TAB_INDEX.POS;
	private final Color SEARCH_RESULT_WINDOW_TAB_INDEX_TEXT_COLOR = SettingsScreenSettings.SEARCH_RESULT_WINDOW.TAB_INDEX.TEXT_COLOR;

	private final TextureColor SEARCH_RESULT_WINDOW_ITEM_BORDER_COLOR = SettingsScreenSettings.CONSTANTS.ITEM_BORDER;
	private final TextureColor SEARCH_RESULT_WINDOW_ITEM_BORDER_COLOR_HOVER = SettingsScreenSettings.CONSTANTS.ITEM_BORDER_HOVER;

	private final TextureColor SEARCH_RESULT_WINDOW_ITEM_BG_COLOR_ACTIVE = SettingsScreenSettings.SEARCH_RESULT_WINDOW.ITEMS.ACTIVE_BG;

	public void drawSearchResultSection(GuiGraphics gg) {
		// draw main window
		drawRectangleWithBorder(gg, SEARCH_RESULT_WINDOW_WINDOW_POS, SEARCH_RESULT_WINDOW_WINDOW_SIZE, windowFillColor,
				windowBorderColor);

		writeStringCentred(gg, searchResultBox.curWindowTabIndex + "/" + searchResultBox.windowTabCount,
				SEARCH_RESULT_WINDOW_TAB_INDEX_POS.x, SEARCH_RESULT_WINDOW_TAB_INDEX_POS.y,
				SEARCH_RESULT_WINDOW_TAB_INDEX_TEXT_COLOR);

		if (searchResultBox.curWindowTab != null) {
			if (searchResultBox.curWindowTab.itemList != null) {

				for (SearchResultItem ai : searchResultBox.curWindowTab.itemList) {

					if (settingsWindow == null) {

						if (searchResultBox.isSameHoverItem(ai)) {

							drawRectangleWithBorder(gg, ai.pos, ITEM_SIZE, ai.itemData.bgColor,
									SEARCH_RESULT_WINDOW_ITEM_BORDER_COLOR_HOVER);

						} else {

							drawRectangleWithBorder(gg, ai.pos, ITEM_SIZE, ai.itemData.bgColor,
									SEARCH_RESULT_WINDOW_ITEM_BORDER_COLOR);

						}
					} else {
						if (searchResultBox.isSameHoverItem(ai)) {

							if (settingsWindow.itemData.isSameID(ai.itemData.id)) {
								drawRectangleWithBorder(gg, ai.pos, ITEM_SIZE,
										SEARCH_RESULT_WINDOW_ITEM_BG_COLOR_ACTIVE,
										SEARCH_RESULT_WINDOW_ITEM_BORDER_COLOR_HOVER);
							} else {
								drawRectangleWithBorder(gg, ai.pos, ITEM_SIZE, ai.itemData.bgColor,
										SEARCH_RESULT_WINDOW_ITEM_BORDER_COLOR_HOVER);
							}

						} else {

							if (settingsWindow.itemData.isSameID(ai.itemData.id)) {
								drawRectangleWithBorder(gg, ai.pos, ITEM_SIZE,
										SEARCH_RESULT_WINDOW_ITEM_BG_COLOR_ACTIVE,
										SEARCH_RESULT_WINDOW_ITEM_BORDER_COLOR);
							} else {
								drawRectangleWithBorder(gg, ai.pos, ITEM_SIZE, ai.itemData.bgColor,
										SEARCH_RESULT_WINDOW_ITEM_BORDER_COLOR);
							}

						}
					}

					drawItemIcon(gg, ai.itemData.curDisplayItem, ai.pos.x + 1, ai.pos.y + 1);
				}
			}
		}

	}

	private final Point ERROR_WINDOW_POS = SettingsScreenSettings.INFO_WINDOW.CREATIVE_ERROR.POS;
	private final Point ERROR_WINDOW_SIZE = SettingsScreenSettings.INFO_WINDOW.CREATIVE_ERROR.SIZE;

	public static final TextureColor ERROR_WINDOW_BG = SettingsScreenSettings.INFO_WINDOW.CREATIVE_ERROR.BG;

	private final Color ERROR_WINDOW_TEXT_COLOR = SettingsScreenSettings.INFO_WINDOW.CREATIVE_ERROR.TEXT_COLOR;

	public static final Point LINE_1_POS = SettingsScreenSettings.INFO_WINDOW.CREATIVE_ERROR.LINE_1_POS;
	public static final String LINE_1_TEXT = SettingsScreenSettings.INFO_WINDOW.CREATIVE_ERROR.LINE_1_TEXT;

	public static final Point LINE_2_POS = SettingsScreenSettings.INFO_WINDOW.CREATIVE_ERROR.LINE_2_POS;
	public static final String LINE_2_TEXT = SettingsScreenSettings.INFO_WINDOW.CREATIVE_ERROR.LINE_2_TEXT;

	public static final Point LINE_3_POS = SettingsScreenSettings.INFO_WINDOW.CREATIVE_ERROR.LINE_3_POS;
	public static final String LINE_3_TEXT = SettingsScreenSettings.INFO_WINDOW.CREATIVE_ERROR.LINE_3_TEXT;

	public void drawNotCreativeErrorWindow(GuiGraphics gg) {
		drawRectangleWithBorder(gg, ERROR_WINDOW_POS, ERROR_WINDOW_SIZE, ERROR_WINDOW_BG, windowBorderColor);

		writeString(gg, LINE_1_TEXT, LINE_1_POS.x, LINE_1_POS.y, ERROR_WINDOW_TEXT_COLOR);
		writeString(gg, LINE_2_TEXT, LINE_2_POS.x, LINE_2_POS.y, ERROR_WINDOW_TEXT_COLOR);
		writeString(gg, LINE_3_TEXT, LINE_3_POS.x, LINE_3_POS.y, ERROR_WINDOW_TEXT_COLOR);

	}

	private final Point INFO_TEXT_WINDOW_POS = SettingsScreenSettings.INFO_WINDOW.INFO.POS;
	private final Point INFO_TEXT_WINDOW_SIZE = SettingsScreenSettings.INFO_WINDOW.INFO.SIZE;

	public static final TextureColor INFO_WINDOW_BG = SettingsScreenSettings.INFO_WINDOW.INFO.BG;

	private final Color INFO_TEXT_WINDOW_TEXT_COLOR = SettingsScreenSettings.INFO_WINDOW.INFO.TEXT_COLOR;

	public static final Point INFO_TEXT_LINE_1_POS = SettingsScreenSettings.INFO_WINDOW.INFO.LINE_1_POS;
	public static final String INFO_TEXT_LINE_1_TEXT = SettingsScreenSettings.INFO_WINDOW.INFO.LINE_1_TEXT;

	public static final Point INFO_TEXT_LINE_2_POS = SettingsScreenSettings.INFO_WINDOW.INFO.LINE_2_POS;
	public static final String INFO_TEXT_LINE_2_TEXT = SettingsScreenSettings.INFO_WINDOW.INFO.LINE_2_TEXT;

	public static final Point INFO_TEXT_LINE_3_POS = SettingsScreenSettings.INFO_WINDOW.INFO.LINE_3_POS;
	public static final String INFO_TEXT_LINE_3_TEXT = SettingsScreenSettings.INFO_WINDOW.INFO.LINE_3_TEXT;

	public static final Point INFO_TEXT_LINE_4_POS = SettingsScreenSettings.INFO_WINDOW.INFO.LINE_4_POS;
	public static final String INFO_TEXT_LINE_4_TEXT = SettingsScreenSettings.INFO_WINDOW.INFO.LINE_4_TEXT;

	public void drawInfoWindow(GuiGraphics gg) {
		drawRectangleWithBorder(gg, INFO_TEXT_WINDOW_POS, INFO_TEXT_WINDOW_SIZE, INFO_WINDOW_BG, windowBorderColor);

		writeString(gg, INFO_TEXT_LINE_1_TEXT, INFO_TEXT_LINE_1_POS.x, INFO_TEXT_LINE_1_POS.y,
				INFO_TEXT_WINDOW_TEXT_COLOR);
		writeString(gg, INFO_TEXT_LINE_2_TEXT, INFO_TEXT_LINE_2_POS.x, INFO_TEXT_LINE_2_POS.y,
				INFO_TEXT_WINDOW_TEXT_COLOR);
		writeString(gg, INFO_TEXT_LINE_3_TEXT, INFO_TEXT_LINE_3_POS.x, INFO_TEXT_LINE_3_POS.y,
				INFO_TEXT_WINDOW_TEXT_COLOR);
		writeString(gg, INFO_TEXT_LINE_4_TEXT, INFO_TEXT_LINE_4_POS.x, INFO_TEXT_LINE_4_POS.y,
				INFO_TEXT_WINDOW_TEXT_COLOR);

	}

	private final Point INFO_WINDOW_POS = SettingsScreenSettings.INFO_WINDOW.POS;
	private final Point INFO_WINDOW_SIZE = SettingsScreenSettings.INFO_WINDOW.SIZE;

	private final Point CFG_DISPLAY_ITEM_SIZE = SettingsScreenSettings.INFO_WINDOW.SETTINGS_ITEM.SIZE;

	private final Color CFG_DISPLAY_ITEM_TEXT_COLOR = SettingsScreenSettings.INFO_WINDOW.SETTINGS_ITEM.TEXT_COLOR;

	public void drawInfoSection(GuiGraphics gg) {
		// draw main window

		if (settingsWindow != null) {

			drawRectangleWithBorder(gg, INFO_WINDOW_POS, INFO_WINDOW_SIZE, TextureColor.BLACK_1, windowBorderColor);

			drawSettingsWindowTitles(gg);

			for (CfgDisplayItem item : settingsWindow.displayItemList) {
				drawRectangleWithBorder(gg, item.posData.windowPos, CFG_DISPLAY_ITEM_SIZE, item.bg, windowBorderColor);

				drawItemIcon(gg, item.icon, item.posData.iconPos.x, item.posData.iconPos.y);

				if (item.showCurValString)
					writeStringCentred(gg, item.curStr, item.posData.curAmountPos.x, item.posData.curAmountPos.y,
							CFG_DISPLAY_ITEM_TEXT_COLOR);

				writeStringCentred(gg, item.maxStr, item.posData.maxPos.x, item.posData.maxPos.y,
						CFG_DISPLAY_ITEM_TEXT_COLOR);

				writeStringCentred(gg, item.minStr, item.posData.minPos.x, item.posData.minPos.y,
						CFG_DISPLAY_ITEM_TEXT_COLOR);

				writeStringCentred(gg, item.defaultStr, item.posData.defaultValPos.x, item.posData.defaultValPos.y,
						CFG_DISPLAY_ITEM_TEXT_COLOR);
			}

			drawMsgWindow(gg);
			drawAffectedItemsWindow(gg);

		}

	}

	private final Point MSG_WINDOW_POS = SettingsScreenSettings.INFO_WINDOW.MSG_AREA.POS;
	private final Point MSG_WINDOW_SIZE = SettingsScreenSettings.INFO_WINDOW.MSG_AREA.SIZE;
	private final Point MSG_WINDOW_TITLE_POS = SettingsScreenSettings.INFO_WINDOW.MSG_AREA.TITLE_POS;
	private final Point MSG_WINDOW_TEXT_DECORATION_POS = SettingsScreenSettings.INFO_WINDOW.MSG_AREA.TEXT_DECORATION_POS;
	private final Point MSG_WINDOW_TEXT_POS = SettingsScreenSettings.INFO_WINDOW.MSG_AREA.TEXT_POS;
	private final TextureColor MSG_WINDOW_BG_COLOR = SettingsScreenSettings.INFO_WINDOW.MSG_AREA.BG_COLOR;

	private final Point MSG_WINDOW_DECORATION_POS = SettingsScreenSettings.INFO_WINDOW.MSG_AREA.DECORATION_POS;
	private final Point MSG_WINDOW_DECORATION_SIZE = SettingsScreenSettings.INFO_WINDOW.MSG_AREA.DECORATION_SIZE;
	private final TextureColor MSG_WINDOW_DECORATION_COLOR = SettingsScreenSettings.INFO_WINDOW.MSG_AREA.DECORATION_COLOR;

	boolean showMsgDecor = true;
	private String curMsgDecor = "";
	private String msgDecor_active = "--> ";
	private String msgDecor_not_active = "    ";

	public void drawMsgWindow(GuiGraphics gg) {

		drawRectangleWithBorder(gg, MSG_WINDOW_POS, MSG_WINDOW_SIZE, MSG_WINDOW_BG_COLOR, windowBorderColor);

		// write title
		writeString(gg, msg_text_title, MSG_WINDOW_TITLE_POS.x, MSG_WINDOW_TITLE_POS.y, msg_color_title);

		// draw decoration
		drawRectangle(gg, MSG_WINDOW_DECORATION_POS.x, MSG_WINDOW_DECORATION_POS.y, MSG_WINDOW_DECORATION_SIZE.x,
				MSG_WINDOW_DECORATION_SIZE.y, MSG_WINDOW_DECORATION_COLOR);

		animateMsgDecoration();
		// write msg
		writeString(gg, curMsgDecor, MSG_WINDOW_TEXT_DECORATION_POS.x, MSG_WINDOW_TEXT_DECORATION_POS.y, msg_color);

		writeString(gg, msg_text, MSG_WINDOW_TEXT_POS.x, MSG_WINDOW_TEXT_POS.y, msg_color);

	}

	int tickCount = 0;
	int maxTickCount = 50;

	private void animateMsgDecoration() {
		tickCount++;

		if (tickCount >= maxTickCount) {
			tickCount = 0;

			showMsgDecor = !showMsgDecor;
		}

		if (showMsgDecor) {
			curMsgDecor = msgDecor_active;
		} else {
			curMsgDecor = msgDecor_not_active;
		}
	}

	private final Point ITEMS_AFFECTED_WINDOW_POS = SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.POS;
	private final Point ITEMS_AFFECTED_WINDOW_SIZE = SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.SIZE;
	private final Point ITEMS_AFFECTED_WINDOW_TITLE_POS = SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.TITLE_POS;
	private final Color ITEMS_AFFECTED_WINDOW_TITLE_COLOR = SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.TITLE_COLOR;

	private final TextureColor ITEMS_AFFECTED_WINDOW_BG_COLOR = SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.BG_COLOR;

	private final Point ITEMS_AFFECTED_WINDOW_DECORATION_POS = SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.DECORATION_POS;
	private final Point ITEMS_AFFECTED_WINDOW_DECORATION_SIZE = SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.DECORATION_SIZE;
	private final TextureColor ITEMS_AFFECTED_WINDOW_DECORATION_COLOR = SettingsScreenSettings.INFO_WINDOW.ITEMS_AFFECTED_AREA.DECORATION_COLOR;

	public void drawAffectedItemsWindow(GuiGraphics gg) {

		drawRectangleWithBorder(gg, ITEMS_AFFECTED_WINDOW_POS, ITEMS_AFFECTED_WINDOW_SIZE,
				settingsWindow.itemData.bgColor, windowBorderColor);

		// write title
		writeString(gg, "Items Affected", ITEMS_AFFECTED_WINDOW_TITLE_POS.x, ITEMS_AFFECTED_WINDOW_TITLE_POS.y,
				ITEMS_AFFECTED_WINDOW_TITLE_COLOR);

		// draw decoration
		drawRectangle(gg, ITEMS_AFFECTED_WINDOW_DECORATION_POS.x, ITEMS_AFFECTED_WINDOW_DECORATION_POS.y,
				ITEMS_AFFECTED_WINDOW_DECORATION_SIZE.x, ITEMS_AFFECTED_WINDOW_DECORATION_SIZE.y,
				ITEMS_AFFECTED_WINDOW_DECORATION_COLOR);

		for (AffectedItem item : settingsWindow.affectedItemBox.curWindowTab.itemList) {
			drawItemIcon(gg, item.stack, item.pos.x, item.pos.y);
		}

	}

	private final Point CFG_TITLE_PARTICLE_TYPE_TITLE_POS = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.PARTICLE_TYPE.TEXT_POS;
	private final Point CFG_TITLE_PARTICLE_TYPE_DECOR_POS = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.PARTICLE_TYPE.DECORATION_POS;
	private final int CFG_TITLE_PARTICLE_TYPE_DECOR_WIDTH = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.PARTICLE_TYPE.WIDTH;

	private final Point CFG_TITLE_CUR_AMOUNT_TITLE_POS = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.CUR_AMOUNT.TEXT_POS;
	private final Point CFG_TITLE_CUR_AMOUNT_DECOR_POS = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.CUR_AMOUNT.DECORATION_POS;
	private final int CFG_TITLE_CUR_AMOUNT_DECOR_WIDTH = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.CUR_AMOUNT.WIDTH;

	private final Point CFG_TITLE_EDIT_TITLE_POS = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.EDIT.TEXT_POS;
	private final Point CFG_TITLE_EDIT_DECOR_POS = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.EDIT.DECORATION_POS;
	private final int CFG_TITLE_EDIT_DECOR_WIDTH = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.EDIT.WIDTH;

	private final Point CFG_TITLE_MAX_TITLE_POS = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.MAX.TEXT_POS;
	private final Point CFG_TITLE_MAX_DECOR_POS = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.MAX.DECORATION_POS;
	private final int CFG_TITLE_MAX_DECOR_WIDTH = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.MAX.WIDTH;

	private final Point CFG_TITLE_MIN_TITLE_POS = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.MIN.TEXT_POS;
	private final Point CFG_TITLE_MIN_DECOR_POS = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.MIN.DECORATION_POS;
	private final int CFG_TITLE_MIN_DECOR_WIDTH = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.MIN.WIDTH;

	private final Point CFG_TITLE_DEFAULT_AMOUNT_TITLE_POS = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.DEFAULT_AMOUNT.TEXT_POS;
	private final Point CFG_TITLE_DEFAULT_AMOUNT_DECOR_POS = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.DEFAULT_AMOUNT.DECORATION_POS;
	private final int CFG_TITLE_DEFAULT_AMOUNT_DECOR_WIDTH = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.DEFAULT_AMOUNT.WIDTH;

	private final Point CFG_TITLE_SET_TO_DEFAULT_AMOUNT_TITLE_POS = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.SET_TO_DEFAULT_AMOUNT.TEXT_POS;
	private final Point CFG_TITLE_SET_TO_DEFAULT_AMOUNT_DECOR_POS = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.SET_TO_DEFAULT_AMOUNT.DECORATION_POS;
	private final int CFG_TITLE_SET_TO_DEFAULT_AMOUNT_DECOR_WIDTH = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.SET_TO_DEFAULT_AMOUNT.WIDTH;

	private final Color CFG_TITLE_TEXT_COLOR = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.TEXT_COLOR;
	private final TextureColor CFG_TITLE_DECOR_COLOR = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.DECOR_COLOR;

	private final int CFG_TITLE_DECOR_HEIGHT = SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA.TITLE.DECOR_HEIGHT;

	public void drawSettingsWindowTitles(GuiGraphics gg) {

		drawTitleSegment(gg, CFG_TITLE_PARTICLE_TYPE_TITLE_POS, CFG_TITLE_PARTICLE_TYPE_DECOR_POS,
				CFG_TITLE_PARTICLE_TYPE_DECOR_WIDTH, "Type");

		drawTitleSegment(gg, CFG_TITLE_CUR_AMOUNT_TITLE_POS, CFG_TITLE_CUR_AMOUNT_DECOR_POS,
				CFG_TITLE_CUR_AMOUNT_DECOR_WIDTH, "Cur Val");

		drawTitleSegment(gg, CFG_TITLE_EDIT_TITLE_POS, CFG_TITLE_EDIT_DECOR_POS, CFG_TITLE_EDIT_DECOR_WIDTH, "Edit");

		drawTitleSegment(gg, CFG_TITLE_MAX_TITLE_POS, CFG_TITLE_MAX_DECOR_POS, CFG_TITLE_MAX_DECOR_WIDTH, "Max");

		drawTitleSegment(gg, CFG_TITLE_MIN_TITLE_POS, CFG_TITLE_MIN_DECOR_POS, CFG_TITLE_MIN_DECOR_WIDTH, "Min");

		drawTitleSegment(gg, CFG_TITLE_DEFAULT_AMOUNT_TITLE_POS, CFG_TITLE_DEFAULT_AMOUNT_DECOR_POS,
				CFG_TITLE_DEFAULT_AMOUNT_DECOR_WIDTH, "Default");

		drawTitleSegment(gg, CFG_TITLE_SET_TO_DEFAULT_AMOUNT_TITLE_POS, CFG_TITLE_SET_TO_DEFAULT_AMOUNT_DECOR_POS,
				CFG_TITLE_SET_TO_DEFAULT_AMOUNT_DECOR_WIDTH, "Set to Default");

	}

	private void drawTitleSegment(GuiGraphics gg, Point textPos, Point decorPos, int decorWidth, String titleText) {

		writeStringCentred(gg, titleText, textPos.x, textPos.y, CFG_TITLE_TEXT_COLOR);

		drawRectangle(gg, decorPos.x, decorPos.y, decorWidth, CFG_TITLE_DECOR_HEIGHT, CFG_TITLE_DECOR_COLOR);

	}


	private void drawTitle(GuiGraphics gg, ModGuiTitle titleObj) {

		GuiDrawFunctions.drawTitle(gg, font, WINDOW_POS, titleObj);

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
//
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

	private void handleEditClick() {
		disableAllTextBoxes();

	}

	private void handleCancelClick(ParticleType type) {

	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {

		if (curHoverItem != null) {
			switch (curHoverItem) {

			case EXIT_BUTTON:

				if (isInCreative())
					resetData();

				getMinecraft().setScreen(new MainMenuScreen());
				playClickSound();

				break;

			case ITEM_LIST_NEXT:
				if (searchResultBox.handleNextClick()) {
					playClickSound();
				}

				break;
			case ITEM_LIST_PREV:
				if (searchResultBox.handlePrevClick()) {
					playClickSound();
				}
				break;

			// =====================
			// Nature Section

			case EDIT_NATURE:
				if (btnEditNature.isClickable()) {
					settingsWindow.handleEditNature();
					playClickSound();
				}
				break;

			case SAVE_NATURE:
				if (btnSaveNature.isClickable()) {
					settingsWindow.handleSavelNature();
					setNeedsUpdate();
					playClickSound();
				}
				break;

			case CANCEL_NATURE:
				if (btnCancelNature.isClickable()) {
					settingsWindow.handleCancelNature();
					playClickSound();
				}
				break;

			case DEFAULT_NATURE:
				if (btnDefaultNature.isClickable()) {
					settingsWindow.handleSetDefaultlNature();
					playClickSound();
				}
				break;

			// =====================
			// Food Section

			case EDIT_FOOD:
				if (btnEditFood.isClickable()) {
					settingsWindow.handleEditFood();
					playClickSound();
				}
				break;

			case SAVE_FOOD:
				if (btnSaveFood.isClickable()) {
					settingsWindow.handleSavelFood();
					setNeedsUpdate();
					playClickSound();
				}
				break;

			case CANCEL_FOOD:
				if (btnCancelFood.isClickable()) {
					settingsWindow.handleCancelFood();
					playClickSound();
				}
				break;

			case DEFAULT_FOOD:
				if (btnDefaultFood.isClickable()) {
					settingsWindow.handleSetDefaultlFood();
					playClickSound();
				}
				break;

			// =====================
			// Metal Section

			case EDIT_METAL:
				if (btnEditMetal.isClickable()) {
					settingsWindow.handleEditMetal();
					playClickSound();
				}
				break;

			case SAVE_METAL:
				if (btnSaveMetal.isClickable()) {
					settingsWindow.handleSavelMetal();
					setNeedsUpdate();
					playClickSound();
				}
				break;

			case CANCEL_METAL:
				if (btnCancelMetal.isClickable()) {
					settingsWindow.handleCancelMetal();
					playClickSound();
				}
				break;

			case DEFAULT_METAL:
				if (btnDefaultMetal.isClickable()) {
					settingsWindow.handleSetDefaultlMetal();
					playClickSound();
				}
				break;

			// =====================
			// Gem Section

			case EDIT_GEM:
				if (btnEditGem.isClickable()) {
					settingsWindow.handleEditGem();
					playClickSound();
				}
				break;

			case SAVE_GEM:
				if (btnSaveGem.isClickable()) {
					settingsWindow.handleSavelGem();
					setNeedsUpdate();
					playClickSound();
				}
				break;

			case CANCEL_GEM:
				if (btnCancelGem.isClickable()) {
					settingsWindow.handleCancelGem();
					playClickSound();
				}
				break;

			case DEFAULT_GEM:
				if (btnDefaultGem.isClickable()) {
					settingsWindow.handleSetDefaultlGem();
					playClickSound();
				}
				break;

			// =====================
			// Affected Items Section

			case AFFECTED_ITEMS_PREV:
				if (btnAffectedItemsPrev.isClickable()) {
					if (settingsWindow.affectedItemBox.handlePrevClick()) {
						playClickSound();
					}
				}
				break;

			case AFFECTED_ITEMS_NEXT:
				if (btnAffectedItemsNext.isClickable()) {
					if (settingsWindow.affectedItemBox.handleNextClick()) {
						playClickSound();
					}
				}
				break;

			default:
				break;

			}

		}

		// handle category click
		if (searchResultBox.curWindowTab != null) {
			if (searchResultBox.curWindowTab.curItemHover != null) {

				if (isInCreative()) {
					setGuiComponentsToDefault();

					settingsWindow = new SettingsWindow(searchResultBox.curWindowTab.curItemHover.itemData,
							cfgPosItemList, nature_ButtonList, food_ButtonList, metal_ButtonList, gem_ButtonList,
							affectedItemsButtonList, txtList, this);

					setDefaultMsg();

					playClickSound();
				} else {
					playErrorSound();
				}
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
