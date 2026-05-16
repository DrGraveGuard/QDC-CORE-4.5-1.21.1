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
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModIngredientSlot;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.classes.ModRecipe;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.assembler.AssemblerBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.assembler.AssemblerItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.assembler.InventoryBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.disassembler.DisassemblerDiscoveryItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.disassembler.DisassemblerDiscoveryItemCollection;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.disassembler.DisassemblerInventoryItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.info_window.InfoWindow;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.info_window.ParticleGuiItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.store_particle_display.ParticleStoreGuiItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.store_particle_display.ParticleStoreGuiItemBox;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.functions.AssemblerFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.functions.DisassemblerFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.functions.GuiSoundFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.GlobalSettings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class AssemblerScreen extends Screen {

	private AssemblerBox assemblerBox = null;

	private InventoryBox inventoryBox = null;

	private InfoWindow infoWindow = new InfoWindow();

	private final Point WINDOW_SIZE = AssemblerSettings.MAIN_WINDOW.SIZE;
	private final Point WINDOW_POS = new Point(0, 0);

	private DisassemblerDiscoveryItemCollection discoveredItems = new DisassemblerDiscoveryItemCollection();

	ParticleStoreGuiItemBox particleStoreGuiItemBox = new ParticleStoreGuiItemBox();

	public boolean isShowingDiscoveredItems = true;

	public enum btnType {

		ITEM_LIST_PREV, ITEM_LIST_NEXT, ASSEMBLER_ONE, ASSEMBLER_STACK, ASSEMBLER_ALL, DISASSEMBLER_ONE,
		DISASSEMBLER_STACK, DISASSEMBLER_ALL, ASSEMBLER_NEXT, BTN_MAIN_MENU

	}

	public class GuiButton extends ModButton {

		public btnType type;

		public GuiButton(Point pos, Point size, String text, btnType type) {
			super(pos, size, text);

			this.type = type;
		}

	}

	public List<GuiButton> buttonList = null;
	public List<GuiButton> assemblerDisassemblerButtonList = null;
	private btnType curHoverItem = null;

	private GuiButton btnPrev = new GuiButton(AssemblerSettings.ITEM_LIST_WINDOW.BUTTONS.PREV.POS,
			AssemblerSettings.CONSTANTS.BUTTON.SIZE, "<", btnType.ITEM_LIST_PREV);

	private GuiButton btnNext = new GuiButton(AssemblerSettings.ITEM_LIST_WINDOW.BUTTONS.NEXT.POS,
			AssemblerSettings.CONSTANTS.BUTTON.SIZE, ">", btnType.ITEM_LIST_NEXT);

	private GuiButton btnMainMenu = new GuiButton(AssemblerSettings.MAIN_MENU_BUTTON.POS,
			AssemblerSettings.MAIN_MENU_BUTTON.SIZE, "Main Menu", btnType.BTN_MAIN_MENU);

	private GuiButton btnDisassembleOne = new GuiButton(AssemblerSettings.INFO_WINDOW.BUTTONS.ONE.POS,
			AssemblerSettings.INFO_WINDOW.BUTTONS.ONE.SIZE, "One Item", btnType.DISASSEMBLER_ONE);

	private GuiButton btnDisassembleStack = new GuiButton(AssemblerSettings.INFO_WINDOW.BUTTONS.STACK.POS,
			AssemblerSettings.INFO_WINDOW.BUTTONS.STACK.SIZE, "One Stack", btnType.DISASSEMBLER_STACK);

	private GuiButton btnDisassembleAll = new GuiButton(AssemblerSettings.INFO_WINDOW.BUTTONS.ALL.POS,
			AssemblerSettings.INFO_WINDOW.BUTTONS.ALL.SIZE, "All Items", btnType.DISASSEMBLER_ALL);

	private GuiButton btnAssembleOne = new GuiButton(AssemblerSettings.INFO_WINDOW.BUTTONS.ONE.POS,
			AssemblerSettings.INFO_WINDOW.BUTTONS.ONE.SIZE, "One Item", btnType.ASSEMBLER_ONE);

	private GuiButton btnAssembleStack = new GuiButton(AssemblerSettings.INFO_WINDOW.BUTTONS.STACK.POS,
			AssemblerSettings.INFO_WINDOW.BUTTONS.STACK.SIZE, "One Stack", btnType.ASSEMBLER_STACK);

	private GuiButton btnAssembleAll = new GuiButton(AssemblerSettings.INFO_WINDOW.BUTTONS.ALL.POS,
			AssemblerSettings.INFO_WINDOW.BUTTONS.ALL.SIZE, "All Items", btnType.ASSEMBLER_ALL);

	private List<GuiButton> initGuiButtons() {
		List<GuiButton> res = new ArrayList<AssemblerScreen.GuiButton>();

		res.add(btnPrev);

		res.add(btnNext);

		res.add(btnMainMenu);

		return res;
	}

	private List<GuiButton> initAssemblerDisassemblerButtons() {
		List<GuiButton> res = new ArrayList<AssemblerScreen.GuiButton>();

		res.add(btnDisassembleOne);
		res.add(btnDisassembleStack);
		res.add(btnDisassembleAll);

		res.add(btnAssembleOne);
		res.add(btnAssembleStack);
		res.add(btnAssembleAll);

		return res;
	}

	private void updateButtonGuiData() {
		if (infoWindow != null) {
			btnDisassembleOne.setEnabledState(infoWindow.btnDisassembleActive);
			btnDisassembleOne.setVisibleState(infoWindow.btnDisassembleVisibile);

			btnDisassembleStack.setEnabledState(infoWindow.btnDisassembleActive);
			btnDisassembleStack.setVisibleState(infoWindow.btnDisassembleVisibile);

			btnDisassembleAll.setEnabledState(infoWindow.btnDisassembleActive);
			btnDisassembleAll.setVisibleState(infoWindow.btnDisassembleVisibile);

			btnAssembleOne.setEnabledState(infoWindow.btnAssembleActive);
			btnAssembleOne.setVisibleState(infoWindow.btnAssembleVisibile);

			btnAssembleStack.setEnabledState(infoWindow.btnAssembleActive);
			btnAssembleStack.setVisibleState(infoWindow.btnAssembleVisibile);

			btnAssembleAll.setEnabledState(infoWindow.btnAssembleActive);
			btnAssembleAll.setVisibleState(infoWindow.btnAssembleVisibile);
		}
	}

	public void checkIfHoveringOverButton(int mouseX, int mouseY) {

		curHoverItem = null;

		for (GuiButton b : buttonList) {

			if (b.checkIfHoveringOver(WINDOW_POS, mouseX, mouseY)) {
				curHoverItem = b.type;
			}

		}

	}

	public AssemblerScreen() {
		super(Component.literal("QDC Main Screen"));

	}

	private final Point TXT_POS = AssemblerSettings.ITEM_LIST_WINDOW.SEARCH_WINDOW.POS;
	private final Point TXT_SIZE = AssemblerSettings.ITEM_LIST_WINDOW.SEARCH_WINDOW.SIZE;
	EditBox txtSearch;

	@Override
	protected void init() {

		super.init();

		this.WINDOW_POS.x = this.width / 2 - this.WINDOW_SIZE.x / 2;
		this.WINDOW_POS.y = this.height / 2 - this.WINDOW_SIZE.y / 2;

		assemblerBox = new AssemblerBox(btnPrev, btnNext);

		this.txtSearch = new EditBox(this.font, this.WINDOW_POS.x + TXT_POS.x, this.WINDOW_POS.y + TXT_POS.y,
				TXT_SIZE.x, TXT_SIZE.y, Component.literal("txtSearch"));
		this.txtSearch.setCanLoseFocus(false);
		this.txtSearch.setTextColor(Color.white.getRGB());
		this.txtSearch.setBordered(true);
		this.txtSearch.setMaxLength(20);
		this.txtSearch.setResponder(this::onSearchChanged);
		this.txtSearch.setValue(Qdc.AssemblerVariables.searchString);
		this.addRenderableWidget(this.txtSearch);
		this.txtSearch.setEditable(true);

		assemblerDisassemblerButtonList = initAssemblerDisassemblerButtons();

		buttonList = initGuiButtons();
		buttonList.addAll(assemblerDisassemblerButtonList);

		disableAssemblerDisassemblerButtons();

		isShowingDiscoveredItems = true;

		if (inventoryBox == null) {
			refreshInventory();
		}

		discoveredItems.populate();

		clear();

	}

	private void clear() {
		infoWindow.curAssemblyItem = null;
		assemblerBox.curItem = null;
		infoWindow.curDisasemblyItem = null;
		inventoryBox.curClickedItem = null;
		infoWindow.particleGuiItemBox = null;
		infoWindow.resetButtons();
	}

	private void disableAssemblerDisassemblerButtons() {
		for (GuiButton b : assemblerDisassemblerButtonList) {
			b.setEnabledState(false);
			b.setVisibleState(false);

		}
	}

	private void refreshInventory() {
		inventoryBox = new InventoryBox(Qdc.curPlayer);
	}

	public void onSearchChanged(String search) {
		Qdc.AssemblerVariables.searchString = search;

		assemblerBox.doSearch();
	}

	@Override
	protected void setInitialFocus() {
		this.setInitialFocus(this.txtSearch);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (keyCode == 256) {
			this.minecraft.player.closeContainer();
		}

		return !this.txtSearch.keyPressed(keyCode, scanCode, modifiers) && !this.txtSearch.canConsumeInput()
				? super.keyPressed(keyCode, scanCode, modifiers)
				: true;
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

		this.WINDOW_POS.x = this.width / 2 - this.WINDOW_SIZE.x / 2;
		this.WINDOW_POS.y = this.height / 2 - this.WINDOW_SIZE.y / 2;

		drawStorageParticlesWindow(GuiGraphics);
		drawMainWindow(GuiGraphics);

		checkIfHoveringOverButton(x, y);

		assemblerBox.checkIfHoveringOver(WINDOW_POS, x, y);
		inventoryBox.checkIfHoveringOver(WINDOW_POS, x, y);
		infoWindow.checkIfHoveringOverParticles(WINDOW_POS, x, y);

		if (infoWindow.curAssemblyItem != null) {
			infoWindow.curAssemblyItem.checkIfHoveringOver(WINDOW_POS, x, y);
		}

		discoveredItems.checkIfHoveringOver(WINDOW_POS, x, y);
		particleStoreGuiItemBox.checkIfHoveringOver(WINDOW_POS, x, y);
		drawToolTip(GuiGraphics, x, y);

		updateButtonGuiData();

	}

	private void drawStorageParticlesWindow(GuiGraphics gg) {

		for (ParticleStoreGuiItem item : particleStoreGuiItemBox.particleList) {
			writeString(gg, item.strVal, item.pos.x + 17, item.pos.y + 1, Color.white);

			drawItemIcon(gg, item.icon, item.pos.x + 1, item.pos.y - 3);
		}

	}

	private final TextureColor windowFillColor = GlobalSettings.WINDOW.WINDOW_FILL_COLOR;
	private final TextureColor windowBorderColor = GlobalSettings.WINDOW.WINDOW_BORDER_COLOR;

	public void drawMainWindow(GuiGraphics gg) {
		// draw main window

		drawAssemblerSection(gg);
		drawSearchSection(gg);

		if (infoWindow.curAssemblyItem == null && infoWindow.curDisasemblyItem == null)
			drawAssemblerDssassemblerInfoWindow(gg);

		drawRecipeSection(gg);
		drawInfoSection(gg);
		drawItemParticlesWindow(gg);
		drawInventorySection(gg);
		drawButtons(gg);

		tickCurDataItem();
	}

	private void tickCurDataItem() {
		if (assemblerBox.curItem != null) {
			if (assemblerBox.curItem.itemData != null) {
				if (assemblerBox.curItem.itemData.itemRecipeCollection != null) {
					assemblerBox.curItem.itemData.itemRecipeCollection.tick();
				}
			}
		}
	}

	private void drawToolTip(GuiGraphics gg, int x, int y) {
		if (assemblerBox.curWindowTab != null)
			if (assemblerBox.curWindowTab.curItemHover != null) {
				drawToolTipWindow(gg, assemblerBox.curWindowTab.curItemHover.itemData.itemStack, x, y);
			}

		if (infoWindow.curAssemblyItem != null)
			if (infoWindow.curAssemblyItem.curHoverRecipeItem != null) {
				drawToolTipWindow(gg, assemblerBox.curItem.curHoverRecipeItem, x, y);
			}

		if (inventoryBox.curItemHover != null) {
			drawToolTipWindow(gg, inventoryBox.curItemHover.stack, x, y);
		}

		if (infoWindow != null) {
			if (infoWindow.particleGuiItemBox != null) {
				if (infoWindow.particleGuiItemBox.curHoverItem != null) {
					drawToolTipWindow(gg, new ItemStack(infoWindow.particleGuiItemBox.curHoverItem.icon), x, y);
				}
			}
		}

		if (isShowingDiscoveredItems) {
			if (discoveredItems.curHoverItem != null) {
				drawToolTipWindow(gg, discoveredItems.curHoverItem.stack, x, y);
			}
		}

		if (particleStoreGuiItemBox.curHoverItem != null) {
			drawToolTipWindow(gg, new ItemStack(particleStoreGuiItemBox.curHoverItem.icon), x, y);
		}

		if (infoWindow != null) {
			if (infoWindow.curAssemblyItem != null) {
				if (infoWindow.curAssemblyItem.isHoveringOverMainItem) {
					drawToolTipWindow(gg, infoWindow.curAssemblyItem.itemData.itemStack, x, y);
				}
			} else if (infoWindow.curDisasemblyItem != null) {
				if (infoWindow.curDisasemblyItem.isHoveringOverMainItem) {
					drawToolTipWindow(gg, infoWindow.curDisasemblyItem.stack, x, y);
				}
			}
		}

	}

	public void drawButtons(GuiGraphics gg) {
		for (GuiButton button : buttonList) {

			drawButton(gg, button);
		}
	}

	private final Point SEARCH_POS = AssemblerSettings.ITEM_LIST_WINDOW.SEARCH_WINDOW.POS;
	private final Point SEARCH_SIZE = AssemblerSettings.ITEM_LIST_WINDOW.SEARCH_WINDOW.SIZE;

	public void drawSearchSection(GuiGraphics gg) {
		// draw main window
		drawRectangleWithBorder(gg, SEARCH_POS, SEARCH_SIZE, windowFillColor, windowBorderColor);

	}

	private final Point ITEM_LIST_WINDOW_TITLE_POS = AssemblerSettings.ITEM_LIST_WINDOW.TITLE.POS;
	private final Point ITEM_LIST_WINDOW_TITLE_SIZE = AssemblerSettings.ITEM_LIST_WINDOW.TITLE.SIZE;

	private final Point ITEM_LIST_WINDOW_WINDOW_POS = AssemblerSettings.ITEM_LIST_WINDOW.POS;
	private final Point ITEM_LIST_WINDOW_WINDOW_SIZE = AssemblerSettings.ITEM_LIST_WINDOW.SIZE;

	private final Point ITEM_SIZE = AssemblerSettings.ITEM_LIST_WINDOW.ITEMS.SIZE;

	private final Point ITEM_LIST_WINDOW_TAB_INDEX_POS = AssemblerSettings.ITEM_LIST_WINDOW.TAB_INDEX.POS;
	private final Color ITEM_LIST_WINDOW_TAB_INDEX_TEXT_COLOR = AssemblerSettings.ITEM_LIST_WINDOW.TAB_INDEX.TEXT_COLOR;

	private final TextureColor ITEM_LIST_WINDOW_ITEM_BORDER_COLOR = AssemblerSettings.ITEM_LIST_WINDOW.ITEMS.BORDER_COLOR;
	private final TextureColor ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_HOVER = AssemblerSettings.ITEM_LIST_WINDOW.ITEMS.BORDER_COLOR_HOVER;
	private final TextureColor ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_ACTIVE = AssemblerSettings.ITEM_LIST_WINDOW.ITEMS.BORDER_COLOR_ACTIVE;

	private final ModGuiTitle mainTitleObj = new ModGuiTitle(TtleType.MAIN_TITLE, "Assembly Items",
			ITEM_LIST_WINDOW_TITLE_POS, ITEM_LIST_WINDOW_TITLE_SIZE);

	public void drawAssemblerSection(GuiGraphics gg) {
		// draw main window
		drawRectangleWithBorder(gg, ITEM_LIST_WINDOW_WINDOW_POS, ITEM_LIST_WINDOW_WINDOW_SIZE, windowFillColor,
				windowBorderColor);

		// draw title

		drawTitle(gg, mainTitleObj);

		// draw tab index
		writeStringCentred(gg, assemblerBox.curWindowTabIndex + "/" + assemblerBox.windowTabCount,
				ITEM_LIST_WINDOW_TAB_INDEX_POS.x, ITEM_LIST_WINDOW_TAB_INDEX_POS.y,
				ITEM_LIST_WINDOW_TAB_INDEX_TEXT_COLOR);

		if (assemblerBox.curWindowTab != null) {
			if (assemblerBox.curWindowTab.itemList != null) {

				for (AssemblerItem ai : assemblerBox.curWindowTab.itemList) {

					if (infoWindow.curAssemblyItem != null) {
						if (ai.isSame(infoWindow.curAssemblyItem)) {
							drawRectangle(gg, ai.pos.x - 2, ai.pos.y - 2, ITEM_SIZE.x + 4, ITEM_SIZE.y + 4,
									ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_ACTIVE);

							drawRectangle(gg, ai.pos.x - 1, ai.pos.y - 1, ITEM_SIZE.x + 2, ITEM_SIZE.y + 2,
									ITEM_LIST_WINDOW_ITEM_BORDER_COLOR);
						}
					}

					if (assemblerBox.isSameHoverItem(ai)) {

						{
							drawRectangleWithBorder(gg, ai.pos, ITEM_SIZE, ai.itemData.itemRecipeCollection.statusColor,
									ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_HOVER);
						}

					} else {

						{
							drawRectangleWithBorder(gg, ai.pos, ITEM_SIZE, ai.itemData.itemRecipeCollection.statusColor,
									ITEM_LIST_WINDOW_ITEM_BORDER_COLOR);
						}

					}

					drawItemIcon(gg, ai.itemData.itemStack, ai.pos.x + 1, ai.pos.y + 1);
				}
			}
		}

	}

	private final Point ASS_DISS_INFO_WINDOW_POS = AssemblerSettings.ASSEMBLER_DIASSEMBLER_INFO.POS;
	private final Point ASS_DISS_INFO_WINDOW_SIZE = AssemblerSettings.ASSEMBLER_DIASSEMBLER_INFO.SIZE;

	private final Point ASS_DISS_INFO_WINDOW_LINE_ONE_POS = AssemblerSettings.ASSEMBLER_DIASSEMBLER_INFO.LINE_ONE_POS;
	private final Point ASS_DISS_INFO_WINDOW_LINE_TWO_POS = AssemblerSettings.ASSEMBLER_DIASSEMBLER_INFO.LINE_TWO_POS;
	private final Point ASS_DISS_INFO_WINDOW_LINE_THREE_POS = AssemblerSettings.ASSEMBLER_DIASSEMBLER_INFO.LINE_THREE_POS;
	private final Point ASS_DISS_INFO_WINDOW_LINE_FOUR_POS = AssemblerSettings.ASSEMBLER_DIASSEMBLER_INFO.LINE_FOUR_POS;
	private final Point ASS_DISS_INFO_WINDOW_LINE_FIVE_POS = AssemblerSettings.ASSEMBLER_DIASSEMBLER_INFO.LINE_FIVE_POS;

	private final Color ASS_DISS_INFO_WINDOW_TEXT_COLOR = AssemblerSettings.ASSEMBLER_DIASSEMBLER_INFO.TEXT_COLOR;
	private final TextureColor ASS_DISS_INFO_WINDOW_BG_COLOR = AssemblerSettings.ASSEMBLER_DIASSEMBLER_INFO.BG_COLOR;

	public void drawAssemblerDssassemblerInfoWindow(GuiGraphics gg) {

		drawRectangleWithBorder(gg, ASS_DISS_INFO_WINDOW_POS, ASS_DISS_INFO_WINDOW_SIZE, ASS_DISS_INFO_WINDOW_BG_COLOR,
				windowBorderColor);

		writeString(gg, AssemblerSettings.ASSEMBLER_DIASSEMBLER_INFO.LINE_ONE_TEXT, ASS_DISS_INFO_WINDOW_LINE_ONE_POS.x,
				ASS_DISS_INFO_WINDOW_LINE_ONE_POS.y, ASS_DISS_INFO_WINDOW_TEXT_COLOR);

		writeString(gg, AssemblerSettings.ASSEMBLER_DIASSEMBLER_INFO.LINE_TWO_TEXT, ASS_DISS_INFO_WINDOW_LINE_TWO_POS.x,
				ASS_DISS_INFO_WINDOW_LINE_TWO_POS.y, ASS_DISS_INFO_WINDOW_TEXT_COLOR);

		writeString(gg, AssemblerSettings.ASSEMBLER_DIASSEMBLER_INFO.LINE_THREE_TEXT,
				ASS_DISS_INFO_WINDOW_LINE_THREE_POS.x, ASS_DISS_INFO_WINDOW_LINE_THREE_POS.y,
				ASS_DISS_INFO_WINDOW_TEXT_COLOR);

		writeString(gg, AssemblerSettings.ASSEMBLER_DIASSEMBLER_INFO.LINE_FOUR_TEXT,
				ASS_DISS_INFO_WINDOW_LINE_FOUR_POS.x, ASS_DISS_INFO_WINDOW_LINE_FOUR_POS.y,
				ASS_DISS_INFO_WINDOW_TEXT_COLOR);

		writeString(gg, AssemblerSettings.ASSEMBLER_DIASSEMBLER_INFO.LINE_FIVE_TEXT,
				ASS_DISS_INFO_WINDOW_LINE_FIVE_POS.x, ASS_DISS_INFO_WINDOW_LINE_FIVE_POS.y,
				ASS_DISS_INFO_WINDOW_TEXT_COLOR);

	}

	private final Point RECIPE_WINDOW_WINDOW_POS = AssemblerSettings.RECIPE_WINDOW.POS;
	private final Point RECIPE_WINDOW_WINDOW_SIZE = AssemblerSettings.RECIPE_WINDOW.SIZE;

	private final Color RECIPE_TITLE_COLOR = AssemblerSettings.RECIPE_WINDOW.RECIPE_TITLE.COLOR;

	public void drawRecipeSection(GuiGraphics gg) {
		// draw main window

		if (infoWindow.curAssemblyItem != null)
			if (assemblerBox.curItem != null) {
				if (assemblerBox.curItem.itemData != null) {
					if (assemblerBox.curItem.itemData.itemRecipeCollection != null) {

						for (ModRecipe recipe : assemblerBox.curItem.itemData.itemRecipeCollection.recipeList) {

							drawRecipe(gg, recipe);

						}

					}
				}
			}

	}

	public void drawRecipe(GuiGraphics gg, ModRecipe recipe) {

		drawRectangleWithBorder(gg, recipe.windowPos, recipe.windowSize, recipe.recipeColor, windowBorderColor);

		writeString(gg, recipe.titleText, recipe.titlePos.x + 1, recipe.titlePos.y + 1, RECIPE_TITLE_COLOR);

		for (ModIngredientSlot slot : recipe.slotList) {
			if (assemblerBox.curItem.curHoverRecipeItem == null) {
				drawRectangleWithBorder(gg, slot.pos, ITEM_SIZE, slot.slotColor, ITEM_LIST_WINDOW_ITEM_BORDER_COLOR);
			} else {
				if (slot.curDisplayItem != null)
					if (assemblerBox.curItem.curHoverRecipeItemID.equals(slot.curDisplayItemID)) {
						drawRectangleWithBorder(gg, slot.pos, ITEM_SIZE, slot.slotColor,
								ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_HOVER);
					} else {
						drawRectangleWithBorder(gg, slot.pos, ITEM_SIZE, slot.slotColor,
								ITEM_LIST_WINDOW_ITEM_BORDER_COLOR);
					}
			}

			if (slot.curDisplayItem != null)
				drawItemIcon(gg, slot.curDisplayItem, slot.pos.x + 1, slot.pos.y + 1);
		}
	}

	private final Point DISCOVERY_TITLE_BG_POS = AssemblerSettings.DISCOVERY_WINDOW.TITLE.POS;
	private final Point DISCOVERY_TITLE_BG_SIZE = AssemblerSettings.DISCOVERY_WINDOW.TITLE.SIZE;

	private final ModGuiTitle discoveryTitleObject = new ModGuiTitle(TtleType.MAIN_TITLE, "New Discoveries",
			DISCOVERY_TITLE_BG_POS, DISCOVERY_TITLE_BG_SIZE);

	public void drawDiscoverySection(GuiGraphics gg) {

		drawTitle(gg, discoveryTitleObject);

		for (DisassemblerDiscoveryItem item : discoveredItems.lastDiscoveredItemList) {
			if (discoveredItems.curHoverItem == null) {
				drawRectangleWithBorder(gg, item.pos, ITEM_SIZE, TextureColor.GRAY_1,
						ITEM_LIST_WINDOW_ITEM_BORDER_COLOR);
			} else {
				if (discoveredItems.curHoverItem.isSame(item)) {
					drawRectangleWithBorder(gg, item.pos, ITEM_SIZE, TextureColor.WHITE_1,
							ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_HOVER);
				} else {
					drawRectangleWithBorder(gg, item.pos, ITEM_SIZE, TextureColor.GRAY_1,
							ITEM_LIST_WINDOW_ITEM_BORDER_COLOR);
				}

			}

			drawItemIcon(gg, item.stack, item.pos.x + 1, item.pos.y + 1);
		}

	}

	private final Point INFO_WINDOW_POS = AssemblerSettings.INFO_WINDOW.POS;
	private final Point INFO_WINDOW_SIZE = AssemblerSettings.INFO_WINDOW.SIZE;

	public void drawInfoSection(GuiGraphics gg) {
		// draw main window

		if (infoWindow.curAssemblyItem != null) {
			if (assemblerBox.curItem != null) {

//				drawRectangleWithBorder(gg, INFO_WINDOW_POS, INFO_WINDOW_SIZE,
//						assemblerBox.curItem.itemData.itemRecipeCollection.statusColor, windowBorderColor);

				drawRectangleWithBorder(gg, INFO_WINDOW_POS, INFO_WINDOW_SIZE, windowFillColor, windowBorderColor);

				drawInfoActionSection(gg);
				drawMainItem(gg);
				drawButtonTitleSection(gg);
				drawCanMakeSection(gg);
				isShowingDiscoveredItems = false;

			}
		} else if (infoWindow.curDisasemblyItem != null) {
			if (inventoryBox.curClickedItem != null) {

				drawRectangleWithBorder(gg, INFO_WINDOW_POS, INFO_WINDOW_SIZE, windowFillColor, windowBorderColor);

				drawInfoActionSection(gg);
				drawMainItem(gg);
				drawButtonTitleSection(gg);
				drawDiscoverySection(gg);
				drawCanMakeSection(gg);
				isShowingDiscoveredItems = true;
			}
		} else {
			isShowingDiscoveredItems = true;
			drawDiscoverySection(gg);
		}

	}

	private final Point INFO_ITEM_MAIN_ITEM_WINDOW_POS = AssemblerSettings.INFO_WINDOW.MAIN_ITEM.POS;
	private final Point INFO_ITEM_MAIN_ITEM_WINDOW_SIZE = AssemblerSettings.INFO_WINDOW.MAIN_ITEM.SIZE;
	private final Point INFO_ITEM_NAME_POS = AssemblerSettings.INFO_WINDOW.MAIN_ITEM.NAME.TEXT_POS;
	private final Color INFO_ITEM_NAME_COLOR = AssemblerSettings.INFO_WINDOW.MAIN_ITEM.NAME.TEXT_COLOR;

	private final Point INFO_ITEM_ICON_POS = AssemblerSettings.INFO_WINDOW.MAIN_ITEM.ICON.POS;

	private final Point MOD_NAME_VALUE_POS = AssemblerSettings.INFO_WINDOW.MAIN_ITEM.MOD_NAME.TEXT_POS;
	private final TextureColor INFO_ITEM_MAIN_ITEM_DEFAULT_BG = AssemblerSettings.INFO_WINDOW.MAIN_ITEM.COLOR_DEFAULT;

	public void drawMainItem(GuiGraphics gg) {
		if (infoWindow.curAssemblyItem != null) {
			if (assemblerBox.curItem != null) {

				drawRectangleWithBorder(gg, INFO_ITEM_MAIN_ITEM_WINDOW_POS, INFO_ITEM_MAIN_ITEM_WINDOW_SIZE,
						assemblerBox.curItem.itemData.itemRecipeCollection.statusColor, windowBorderColor);

				drawItemIcon(gg, assemblerBox.curItem.itemData.itemStack, INFO_ITEM_ICON_POS.x, INFO_ITEM_ICON_POS.y);

				writeString(gg, assemblerBox.curItem.itemData.displayName, INFO_ITEM_NAME_POS.x, INFO_ITEM_NAME_POS.y,
						INFO_ITEM_NAME_COLOR);

				writeString(gg, assemblerBox.curItem.itemData.modName, MOD_NAME_VALUE_POS.x, MOD_NAME_VALUE_POS.y,
						Color.white);

			}
		} else if (infoWindow.curDisasemblyItem != null) {
			if (inventoryBox.curClickedItem != null) {

				drawRectangleWithBorder(gg, INFO_ITEM_MAIN_ITEM_WINDOW_POS, INFO_ITEM_MAIN_ITEM_WINDOW_SIZE,
						INFO_ITEM_MAIN_ITEM_DEFAULT_BG, windowBorderColor);

				drawItemIcon(gg, inventoryBox.curClickedItem.stack, INFO_ITEM_ICON_POS.x, INFO_ITEM_ICON_POS.y);

				writeString(gg, inventoryBox.curClickedItem.displayName, INFO_ITEM_NAME_POS.x, INFO_ITEM_NAME_POS.y,
						INFO_ITEM_NAME_COLOR);

				writeString(gg, inventoryBox.curClickedItem.modName, MOD_NAME_VALUE_POS.x, MOD_NAME_VALUE_POS.y,
						Color.white);
			}
		}
	}

	private final Point INFO_WINDOW_PARTICLES_TITLE_POS = AssemblerSettings.INFO_WINDOW.PARTICLES.TITLE.POS;
	private final Point INFO_WINDOW_PARTICLES_TITLE_SIZE = AssemblerSettings.INFO_WINDOW.PARTICLES.TITLE.SIZE;

	private final ModGuiTitle particleTitleObj_assembler = new ModGuiTitle(TtleType.SUB_TITLE, "Item Particles Needed:",
			INFO_WINDOW_PARTICLES_TITLE_POS, INFO_WINDOW_PARTICLES_TITLE_SIZE);
	private final ModGuiTitle particleTitleObj_disassembler = new ModGuiTitle(TtleType.SUB_TITLE, "Item Particles Get:",
			INFO_WINDOW_PARTICLES_TITLE_POS, INFO_WINDOW_PARTICLES_TITLE_SIZE);

	private void drawItemParticlesWindow(GuiGraphics gg) {
		if (infoWindow.particleGuiItemBox != null) {

			if (infoWindow.curAssemblyItem != null) {
				if (assemblerBox.curItem != null) {
					drawTitle(gg, particleTitleObj_assembler);

				}
			} else if (infoWindow.curDisasemblyItem != null) {
				if (inventoryBox.curClickedItem != null) {
					
					drawTitle(gg, particleTitleObj_disassembler);
				}
			}

			for (ParticleGuiItem item : infoWindow.particleGuiItemBox.particleList) {
				drawParticleSegment(gg, item);
			}
		}

	}

	private void drawParticleSegment(GuiGraphics gg, ParticleGuiItem particleItem) {

		drawRectangleWithBorder(gg, particleItem.pos, AssemblerSettings.INFO_WINDOW.PARTICLES.SIZE,
				particleItem.bgColor, windowBorderColor);

		writeString(gg, particleItem.strVal, particleItem.pos.x + 18, particleItem.pos.y + 5, particleItem.textColor);

		drawItemIcon(gg, particleItem.icon, particleItem.pos.x + 1, particleItem.pos.y + 1);
	}


	
	private final Point CAN_MAKE_SECTION_TEXT_POS = AssemblerSettings.INFO_WINDOW.CAN_MAKE_SECTION.TEXT_POS;
	private final Point CAN_MAKE_SECTION_TEXT_AMOUNT_POS = AssemblerSettings.INFO_WINDOW.CAN_MAKE_SECTION.TEXT_AMOUNT_POS;

	private final Point CAN_MAKE_SECTION_TITLE_POS = AssemblerSettings.INFO_WINDOW.CAN_MAKE_SECTION.POS;
	private final Point CAN_MAKE_SECTION_TITLE_SIZE = AssemblerSettings.INFO_WINDOW.CAN_MAKE_SECTION.SIZE;
	
	private final ModGuiTitle canMakeTitleObject = new ModGuiTitle(TtleType.SUB_TITLE, "Can Make: ",
			CAN_MAKE_SECTION_TITLE_POS, CAN_MAKE_SECTION_TITLE_SIZE);
	
	public void drawCanMakeSection(GuiGraphics gg) {

		if (infoWindow.curAssemblyItem != null) {
			if (assemblerBox.curItem != null) {
				if (!assemblerBox.curItem.itemData.canCreate) {
					writeString(gg, "[x] Can Not Assemble Yet!!", CAN_MAKE_SECTION_TEXT_POS.x + 3,
							CAN_MAKE_SECTION_TEXT_POS.y + 3, Color.pink);
				} else {

					if (assemblerBox.curItem.itemData.canCreateAmount > 0) {

						drawTitle(gg, canMakeTitleObject);

						writeString(gg, "" + assemblerBox.curItem.itemData.canCreateAmount,
								CAN_MAKE_SECTION_TEXT_AMOUNT_POS.x + 3, CAN_MAKE_SECTION_TEXT_AMOUNT_POS.y + 3,
								Color.white);

						
					} else {
						writeString(gg, "[x] Not Enough Particles in Storage!!", CAN_MAKE_SECTION_TEXT_POS.x + 3,
								CAN_MAKE_SECTION_TEXT_POS.y + 3, Color.pink);
					}
				}

			}
		} else if (infoWindow.curDisasemblyItem != null) {
			if (inventoryBox.curClickedItem != null) {

			}

		}

	}




	private final Point INFO_WINDOW_ACTION_TITLE_POS = AssemblerSettings.INFO_WINDOW.ACTION_TITLE.POS;
	private final Point INFO_WINDOW_ACTION_TITLE_SIZE = AssemblerSettings.INFO_WINDOW.ACTION_TITLE.SIZE;
	
	private final ModGuiTitle infoWindowActionTitleObject_assembler = new ModGuiTitle(TtleType.MAIN_TITLE, "Item Assembler Window",
			INFO_WINDOW_ACTION_TITLE_POS, INFO_WINDOW_ACTION_TITLE_SIZE);
	private final ModGuiTitle infoWindowActionTitleObject_disassembler = new ModGuiTitle(TtleType.MAIN_TITLE, "Item Disassembler Window",
			INFO_WINDOW_ACTION_TITLE_POS, INFO_WINDOW_ACTION_TITLE_SIZE);
	
	
	
	public void drawInfoActionSection(GuiGraphics gg) {

		if (infoWindow.curAssemblyItem != null) {
			if (assemblerBox.curItem != null) {

				drawTitle(gg, infoWindowActionTitleObject_assembler);
			}
		} else if (infoWindow.curDisasemblyItem != null) {
			if (inventoryBox.curClickedItem != null) {

				drawTitle(gg, infoWindowActionTitleObject_disassembler);

			}

		}
	}


	
	private final Point INFO_WINDOW_BUTTON_TITLE_POS = AssemblerSettings.INFO_WINDOW.BUTTONS.TITLE.POS;
	private final Point INFO_WINDOW_BUTTON_TITLE_SIZE = AssemblerSettings.INFO_WINDOW.BUTTONS.TITLE.SIZE;
	
	private final ModGuiTitle infoWindowButtonTitleObject_assembler = new ModGuiTitle(TtleType.SUB_TITLE, "Assembly Button Options:",
			INFO_WINDOW_BUTTON_TITLE_POS, INFO_WINDOW_BUTTON_TITLE_SIZE);
	private final ModGuiTitle infoWindowButtonTitleObject_disassembler = new ModGuiTitle(TtleType.SUB_TITLE, "Disassembly Button Options:",
			INFO_WINDOW_BUTTON_TITLE_POS, INFO_WINDOW_BUTTON_TITLE_SIZE);
	
	
	public void drawButtonTitleSection(GuiGraphics gg) {

		if (infoWindow.curAssemblyItem != null) {
			if (assemblerBox.curItem != null) {

				drawTitle(gg, infoWindowButtonTitleObject_assembler);

			}
		} else if (infoWindow.curDisasemblyItem != null) {
			if (inventoryBox.curClickedItem != null) {

				drawTitle(gg, infoWindowButtonTitleObject_disassembler);
			}

		}

	}

	private final Point INVENTORY_WINDOW_POS = AssemblerSettings.INVENTORY.POS;
	private final Point INVENTORY_WINDOW_SIZE = AssemblerSettings.INVENTORY.SIZE;

	private final Point INVENTORY_TITLE_POS = AssemblerSettings.INVENTORY.TITLE.POS;
	private final Point INVENTORY_TITLE_SIZE = AssemblerSettings.INVENTORY.TITLE.SIZE;

	private final ModGuiTitle inventoryTitleObject = new ModGuiTitle(TtleType.MAIN_TITLE, "Inventory",
			INVENTORY_TITLE_POS, INVENTORY_TITLE_SIZE);
	
	public void drawInventorySection(GuiGraphics gg) {
		// draw main window
		drawRectangleWithBorder(gg, INVENTORY_WINDOW_POS, INVENTORY_WINDOW_SIZE, windowFillColor, windowBorderColor);

		drawTitle(gg, inventoryTitleObject);

		for (DisassemblerInventoryItem item : inventoryBox.inventoryItemList) {

			if (!item.canBeDisassembled && !item.isEmpty) {

				drawRectangleWithBorder(gg, item.pos, ITEM_SIZE, TextureColor.RED_2, TextureColor.RED_1);
			} else {

				if (inventoryBox.curItemHover != null) {
					if (item.isSame(inventoryBox.curItemHover)) {

						if (inventoryBox.curClickedItem != null) {
							if (inventoryBox.curClickedItem.isSameDisplayIndex(item.displayIndex)) {
								drawRectangleWithBorder(gg, item.pos, ITEM_SIZE, TextureColor.GREEN_1,
										ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_HOVER);
							} else {

								drawRectangleWithBorder(gg, item.pos, ITEM_SIZE, TextureColor.GRAY_1,
										ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_HOVER);
							}

						} else {
							drawRectangleWithBorder(gg, item.pos, ITEM_SIZE, TextureColor.GRAY_1,
									ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_HOVER);
						}
					} else {
						if (inventoryBox.curClickedItem != null) {
							if (inventoryBox.curClickedItem.isSameDisplayIndex(item.displayIndex)) {
								drawRectangleWithBorder(gg, item.pos, ITEM_SIZE, TextureColor.GREEN_1,
										ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_HOVER);
							}
						}
					}

				} else {
					if (inventoryBox.curClickedItem != null) {
						if (inventoryBox.curClickedItem.isSameDisplayIndex(item.displayIndex)) {
							drawRectangleWithBorder(gg, item.pos, ITEM_SIZE, TextureColor.GREEN_1,
									ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_HOVER);
						}
					}
				}
			}

			drawItemIcon(gg, item.stack, item.pos.x + 1, item.pos.y + 1, item.stack.getCount());
		}

	}

	private void drawTitle(GuiGraphics gg, ModGuiTitle titleObj) {

		GuiDrawFunctions.drawTitle(gg, font, WINDOW_POS, titleObj);

	}

	private void drawButton(GuiGraphics gg, ModButton btn) {

		GuiDrawFunctions.drawButton(gg, font, WINDOW_POS, btn);

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

	public void doDisassemblerRefresh() {
		discoveredItems.populate();
		infoWindow.updateCurDisassemblyItem();
		infoWindow.refreshParticles();
		inventoryBox.updateCurDisassemblyItem();
		inventoryBox.refresh(Qdc.curPlayer);
		particleStoreGuiItemBox.update();
		assemblerBox.updateCurWindow();
	}

	public void doAssemblerRefresh() {
		if (infoWindow.curAssemblyItem != null) {
			infoWindow.curAssemblyItem.itemData.calcCanCreateAmount();
		}

		infoWindow.refreshParticles();

		inventoryBox.refresh(Qdc.curPlayer);
		particleStoreGuiItemBox.update();
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {

		if (curHoverItem != null) {
			switch (curHoverItem) {

			case BTN_MAIN_MENU:
				getMinecraft().setScreen(new MainMenuScreen());
				playClickSound();

				break;

			case ITEM_LIST_NEXT:
				if (assemblerBox.handleNextClick()) {
					playClickSound();
				}

				break;
			case ITEM_LIST_PREV:
				if (assemblerBox.handlePrevClick()) {
					playClickSound();
				}
				break;

			case DISASSEMBLER_ONE:
				if (btnDisassembleOne.isEnabled && btnDisassembleOne.isVisible)
					if (infoWindow.curDisasemblyItem != null) {
						DisassemblerFunctions.disassembleSingleItem(infoWindow.curDisasemblyItem);
						doDisassemblerRefresh();
						playClickSound();
					}
				break;

			case DISASSEMBLER_STACK:
				if (btnDisassembleStack.isEnabled && btnDisassembleStack.isVisible)
					if (infoWindow.curDisasemblyItem != null) {
						DisassemblerFunctions.disassembleStack(infoWindow.curDisasemblyItem);
						doDisassemblerRefresh();
						playClickSound();
					}
				break;

			case DISASSEMBLER_ALL:
				if (btnDisassembleAll.isEnabled && btnDisassembleAll.isVisible)
					if (infoWindow.curDisasemblyItem != null) {
						DisassemblerFunctions.disassembleAllStacks(infoWindow.curDisasemblyItem,
								inventoryBox.inventoryItemList);
						doDisassemblerRefresh();
						playClickSound();
					}
				break;

			case ASSEMBLER_ONE:
				if (btnAssembleOne.isEnabled && btnAssembleOne.isVisible)
					if (infoWindow.curAssemblyItem != null) {
						AssemblerFunctions.assembleSingleItem(infoWindow.curAssemblyItem.itemData);
						doAssemblerRefresh();
						playClickSound();
					}
				break;

			case ASSEMBLER_STACK:
				if (btnAssembleStack.isEnabled && btnAssembleStack.isVisible)
					if (infoWindow.curAssemblyItem != null) {
						AssemblerFunctions.assembleStack(infoWindow.curAssemblyItem.itemData);
						doAssemblerRefresh();
						playClickSound();
					}
				break;

			case ASSEMBLER_ALL:
				if (btnAssembleAll.isEnabled && btnAssembleAll.isVisible)
					if (infoWindow.curAssemblyItem != null) {
						AssemblerFunctions.assembleAll(infoWindow.curAssemblyItem.itemData);
						doAssemblerRefresh();
						playClickSound();
					}
				break;

			default:
				break;

			}

		}

		// handle category click
		if (assemblerBox.curWindowTab != null) {
			if (assemblerBox.curWindowTab.curItemHover != null) {

				inventoryBox.curClickedItem = null;

				assemblerBox.handleItemClick(RECIPE_WINDOW_WINDOW_POS);
				infoWindow.setAssemblyWindow(assemblerBox.curWindowTab.curItemHover);

				playClickSound();
			}
		}

		if (inventoryBox.curItemHover != null) {
			if (inventoryBox.curItemHover.stack.getItem() != Items.AIR) {
				if (inventoryBox.curItemHover.canBeDisassembled) {
					inventoryBox.curClickedItem = inventoryBox.curItemHover;
					infoWindow.setDisassemblyWindow(inventoryBox.curClickedItem);
					playClickSound();
				} else {
					playErrorSound();
				}
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