package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.disassembler.DisassemblerInventoryItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.INFO_WINDOW;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SacrificeScreenSettinigs.MAIN_WINDOW;
import com.sun.jna.platform.linux.XAttr.size_t;

public class AssemblerSettings {

	public static enum RecipeStatus {
		NO_DISCOVERY, PARTIAL_DISCOVERY, COMPLETE_DISCOVERY

	}

	public static enum MainRecipeType {
		DISCOVERY, GENERAL, POTION, ARROW, BOOK, ROCKET, SUS_STEW, SHULKER_BOX, ANVIL, CONCRETE

	}

	public class CONSTANTS {
		public static final int ITEM_LIST_MAX = 35;
		public static final int ITEM_LIST_WIDTH = 5;

		public static final int RECIPE_ITEM_LIST_MAX = 9;
		public static final int RECIPE_ITEM_LIST_WIDTH = 4;

		public static final int PARTICLE_LIST_MAX = 6;
		public static final int PARTICLE_LIST_WIDTH = 3;

		public static final TextureColor ITEM_BORDER = TextureColor.BLACK_1;
		public static final TextureColor ITEM_BORDER_HOVER = TextureColor.GREEN_2;

		public static final TextureColor ITEM_FILL = TextureColor.RED_1;
		public static final TextureColor ITEM_FILL_ACTIVE = TextureColor.GREEN_1;

		




		public class STATUS_COLORS {

			public static final TextureColor NO_DISCOVERY = TextureColor.RED_1;
			public static final TextureColor PARTIAL_DISCOVERY = TextureColor.ORANGE_1;
			public static final TextureColor COMPLETE_DISCOVERY = TextureColor.GREEN_1;
		}

		public class BUTTON {
			public static final Point SIZE = new Point(15, 12);
		}
	}

	public class PARTICLE_STORE_WINDOW {

		public static final Point POS = new Point(-5, -18);
		public static final Point SIZE = new Point(415, 18);

		public class ITEMS {
			public static final Point SIZE = new Point(65, 18);
			public static final Point GAP_SIZE = new Point(5, 2);

			public static final List<Point> ITEM_POS_LIST = GuiFunctions.generateIconPosList(6, 6, SIZE, GAP_SIZE, POS);
		}

	}

	public class MAIN_WINDOW {

		public static final Point POS = new Point(0, 0);
		public static final Point SIZE = new Point(415, 200);

	}

	public class MAIN_MENU_BUTTON {
		public static final Point POS = new Point(0,0);
		public static final Point SIZE = new Point(104, 15);
	}
	



	public class ITEM_LIST_WINDOW {

		public static final Point POS = new Point(0, MAIN_MENU_BUTTON.POS.y+ MAIN_MENU_BUTTON.SIZE.y +3);
		public static final Point SIZE = new Point(MAIN_MENU_BUTTON.SIZE.x, 196);

		public class TITLE {

			public static final Point POS = new Point(ITEM_LIST_WINDOW.POS.x, MAIN_MENU_BUTTON.POS.y+ MAIN_MENU_BUTTON.SIZE.y +3);
			public static final Point SIZE = new Point(ITEM_LIST_WINDOW.SIZE.x, 15);

		}
		
		
		public class SEARCH_WINDOW {

			public static final Point POS = new Point(ITEM_LIST_WINDOW.POS.x + 5, TITLE.POS.y+ TITLE.SIZE.y +3);
			public static final Point SIZE = new Point(ITEM_LIST_WINDOW.SIZE.x-10, 15);

		}
		
		public class BUTTONS {

			public class PREV {
				public static final Point POS = new Point(ITEM_LIST_WINDOW.POS.x + 5, SEARCH_WINDOW.POS.y + SEARCH_WINDOW.SIZE.y+ 5);
			}

			public class NEXT {
				public static final Point POS = new Point(
						ITEM_LIST_WINDOW.POS.x + ITEM_LIST_WINDOW.SIZE.x - (CONSTANTS.BUTTON.SIZE.x + 5), PREV.POS.y);
			}

		}

		public class TAB_INDEX {

			public static final Point POS = new Point(ITEM_LIST_WINDOW.POS.x + ITEM_LIST_WINDOW.SIZE.x/2,
					BUTTONS.PREV.POS.y);
			public static final Color TEXT_COLOR = Color.white;

		}

		public class ITEMS {
			public static final Point POS = new Point(ITEM_LIST_WINDOW.POS.x + 1,
					BUTTONS.PREV.POS.y + CONSTANTS.BUTTON.SIZE.y + 3);
			public static final Point SIZE = new Point(18, 18);
			public static final Point GAP_SIZE = new Point(2, 2);

			public static final TextureColor BORDER_COLOR = TextureColor.WHITE_1;
			public static final TextureColor BORDER_COLOR_HOVER = TextureColor.GREEN_2;
			public static final TextureColor BORDER_COLOR_ACTIVE = TextureColor.ORANGE_2;

			public static final List<Point> ITEM_POS_LIST = GuiFunctions.generateIconPosList(CONSTANTS.ITEM_LIST_MAX,
					CONSTANTS.ITEM_LIST_WIDTH, SIZE, GAP_SIZE, POS);
		}

	}
	


	public class RECIPE_WINDOW {

		public static final Point POS = new Point(INFO_WINDOW.POS.x + INFO_WINDOW.SIZE.x + 5, INFO_WINDOW.POS.y);
		public static final Point SIZE = new Point(88, 184);

		public static final Point WINDOW_GAP = new Point(1, 2);

		public class DISCOVERY {

			public static final Point TITLE_GAP = new Point(20, 4);
			public static final Point SIZE = new Point(RECIPE_WINDOW.SIZE.x, 20);
		}

		public class RECIPE_TITLE {
			public static final Color COLOR = Color.white;
			public static final Point TITLE_GAP = new Point(2, 1);
			public static final Point SIZE = new Point(RECIPE_WINDOW.SIZE.x, 10);
		}

		public class ITEMS {
			public static final Point POS = new Point(RECIPE_WINDOW.POS.x + 1, RECIPE_WINDOW.POS.y);
			public static final Point SIZE = new Point(18, 18);
			public static final Point GAP_SIZE = new Point(1, 1);

			public static final List<Point> ITEM_POS_LIST = GuiFunctions.generateIconPosList(
					CONSTANTS.RECIPE_ITEM_LIST_MAX, CONSTANTS.RECIPE_ITEM_LIST_WIDTH, SIZE, GAP_SIZE, POS);
		}

	}

	public class INFO_WINDOW {

		public static final Point POS = new Point(ITEM_LIST_WINDOW.POS.x + ITEM_LIST_WINDOW.SIZE.x + 5, 0);
		public static final Point SIZE = new Point(215, 122);

		

		public class ACTION_TITLE {

			
			public static final Point POS = INFO_WINDOW.POS;
			public static final Point SIZE = new Point(INFO_WINDOW.SIZE.x, 12);
		}

		public class MAIN_ITEM {

			public static final Point POS = new Point(INFO_WINDOW.POS.x + 5, ACTION_TITLE.POS.y + ACTION_TITLE.SIZE.y + 1);
			public static final Point SIZE = new Point(INFO_WINDOW.SIZE.x - 10, 20);

			public static final TextureColor COLOR_DEFAULT = TextureColor.PURPLE_1;
			
			public class ICON {
				public static final Point POS = new Point(MAIN_ITEM.POS.x + 2, MAIN_ITEM.POS.y + 2);
				public static final Point SIZE = new Point(20, 20);

			}

			public class NAME {
				public static final Point POS = new Point(MAIN_ITEM.POS.x + ICON.SIZE.x, MAIN_ITEM.POS.y);

				public static final int HEIGHT = 9;
				public static final Point TEXT_POS = new Point(POS.x, POS.y + 2);
				public static final Color TEXT_COLOR = Color.white;
			}

			public class MOD_NAME {
				public static final Point POS = new Point(NAME.POS.x, NAME.POS.y + NAME.HEIGHT);

				public static final Point TEXT_POS = new Point(POS.x, POS.y + 2);
				public static final Color TEXT_COLOR = Color.gray;

			}

		}

		public class PARTICLES {
			public static final TextureColor COLOR_HAVE_ENOUGH = TextureColor.GREEN_1;
			public static final TextureColor COLOR_NOT_HAVE_ENOUGH = TextureColor.RED_2;
			public static final TextureColor COLOR_DEFAULT = TextureColor.GRAY_1;
			public static final Color TEXT_COLOR_HAVE = Color.white;
			public static final Color TEXT_COLOR_NOT_HAVE = Color.white;
			public static final Color TEXT_COLOR_DEFAULT = Color.black;

			public static final Point SIZE = new Point(65, 18);
			public static final Point GAP_SIZE = new Point(1, 1);

			public class TITLE {

				public static final Point POS = new Point(INFO_WINDOW.POS.x + 5,
						MAIN_ITEM.POS.y + MAIN_ITEM.SIZE.y);
				public static final Point SIZE = new Point(INFO_WINDOW.SIZE.x - 10, 12);

			}

			public static final Point WINDOW_SIZE = new Point(TITLE.SIZE.x, 45);

			public static final Point START_POS = new Point(TITLE.POS.x + 1, TITLE.POS.y + TITLE.SIZE.y);

			public static final List<Point> PARTICLE_POS_LIST = GuiFunctions.generateIconPosList(
					CONSTANTS.PARTICLE_LIST_MAX, CONSTANTS.PARTICLE_LIST_WIDTH, SIZE, GAP_SIZE, START_POS);
		}

		public class BUTTONS {

			public static final Point GAP_SIZE = new Point(2, 2);
			public static final Point SIZE = new Point(55, 12);

			public class TITLE {

				public static final Point POS = new Point(INFO_WINDOW.POS.x + 5,
						PARTICLES.TITLE.POS.y + PARTICLES.TITLE.SIZE.y + PARTICLES.WINDOW_SIZE.y + 5);
				public static final Point SIZE = new Point(INFO_WINDOW.SIZE.x - 10, 12);

			}

			public static final Point WINDOW_SIZE = new Point(TITLE.SIZE.x, 24);

			public static final Point START_POS = new Point(TITLE.POS.x + 3, TITLE.POS.y + TITLE.SIZE.y);

			public class ONE {
				public static final Point POS = new Point(BUTTONS.START_POS.x, BUTTONS.START_POS.y);
				public static final Point SIZE = BUTTONS.SIZE;
			}

			public class STACK {
				public static final Point POS = new Point(ONE.POS.x + ONE.SIZE.x + BUTTONS.GAP_SIZE.x,
						BUTTONS.START_POS.y);
				public static final Point SIZE = BUTTONS.SIZE;
			}

			public class ALL {
				public static final Point POS = new Point(STACK.POS.x + STACK.SIZE.x + BUTTONS.GAP_SIZE.x,
						BUTTONS.START_POS.y);
				public static final Point SIZE = BUTTONS.SIZE;
			}

		}

		public class CAN_MAKE_SECTION {
			public static final Point POS = new Point(INFO_WINDOW.POS.x + 5,
					PARTICLES.TITLE.POS.y + PARTICLES.WINDOW_SIZE.y + 5);
			
			
			
			public static final int DECORATION_WIDTH = 51;
			
			public static final Point SIZE = new Point(DECORATION_WIDTH,12);
			
			public static final Point TEXT_POS = new Point(POS.x, POS.y);
			public static final Point TEXT_AMOUNT_POS = new Point(POS.x+DECORATION_WIDTH, POS.y);
			public static final Point DECORATION_POS = new Point(POS.x, POS.y + 10);
			
		}

	}
	
	public class ASSEMBLER_DIASSEMBLER_INFO {
		
		public static final Point POS = new Point(INFO_WINDOW.POS.x,ITEM_LIST_WINDOW.SEARCH_WINDOW.POS.y);
		public static final Point SIZE = new Point(INFO_WINDOW.SIZE.x,56);
		
		public static final Color TEXT_COLOR = Color.white;
		public static final TextureColor BG_COLOR = TextureColor.BLUE_1;
		
		public static final Point LINE_ONE_POS = new Point(POS.x+3,POS.y+3);
		public static final Point LINE_TWO_POS = new Point(LINE_ONE_POS.x,LINE_ONE_POS.y + 10);
		public static final Point LINE_THREE_POS = new Point(LINE_ONE_POS.x,LINE_TWO_POS.y + 20);
		public static final Point LINE_FOUR_POS = new Point(LINE_ONE_POS.x,LINE_THREE_POS.y + 10);
		public static final Point LINE_FIVE_POS = new Point(LINE_ONE_POS.x,LINE_FOUR_POS.y + 10);

		//public static final String LINE_ONE_TEXT   "This is a Mob Drop Loot System, based on ";
		public static final String LINE_ONE_TEXT =   "Click on an item in the Assembly Items";
		public static final String LINE_TWO_TEXT =   "Window, to show the Item Assembly Window.";
		public static final String LINE_THREE_TEXT = "Click on an item in the Inventory Window,";
		public static final String LINE_FOUR_TEXT =  "to show the Item Disassembly Window.";
		public static final String LINE_FIVE_TEXT =  "";
	}
	
	

	public class DISCOVERY_WINDOW {

		public static final Point POS = new Point(RECIPE_WINDOW.POS.x, RECIPE_WINDOW.POS.y);
		public static final Point SIZE = new Point(RECIPE_WINDOW.SIZE);
		public static final Point START_POS = new Point(POS.x + 3, POS.y + 17);

		public class TITLE {

			public static final Point POS = DISCOVERY_WINDOW.POS;
			public static final Point SIZE = new Point(DISCOVERY_WINDOW.SIZE.x,12);
			

			
			

		}

		public class DISCOVERY_ITEM {
			public static final Point SIZE = new Point(20, 20);
			public static final Point GAP_SIZE = new Point(2, 2);
			public static final Point START_POS = new Point(DISCOVERY_WINDOW.POS.x, DISCOVERY_WINDOW.POS.y + 24);
		}

		public static final List<Point> discoveryPosList = GuiFunctions.generateIconPosList(24, 4, DISCOVERY_ITEM.SIZE,
				DISCOVERY_ITEM.GAP_SIZE, DISCOVERY_WINDOW.START_POS);
	}

	public class INVENTORY {

		public static final Point POS = new Point(INFO_WINDOW.POS.x, INFO_WINDOW.POS.y + INFO_WINDOW.SIZE.y + 2);
		public static final Point SIZE = new Point(180, 90);

		public class TITLE {
			public static final Point POS = new Point(INFO_WINDOW.POS.x, INVENTORY.POS.y);
			public static final Point SIZE = new Point(INVENTORY.SIZE.x,12);


		}

		public class ITEM {
			public static final Point POS = new Point(INVENTORY.POS.x + 3, INVENTORY.TITLE.POS.y + INVENTORY.TITLE.SIZE.y+ 0);
			public static final Point SIZE = new Point(18, 18);
			public static final Point GAP_SIZE = new Point(1, 1);
		}

		public static final List<Integer> INVENTORY_ACTUAL_INDEX_LIST = getInventoryActualIndex();

		public static final List<Point> INVENTORY_ITEMS_POS_LIST = GuiFunctions.generateIconPosList(36, 9,
				INVENTORY.ITEM.SIZE, INVENTORY.ITEM.GAP_SIZE, INVENTORY.ITEM.POS);

		public static List<DisassemblerInventoryItem> generateInventoryItemList() {
			List<DisassemblerInventoryItem> res = new ArrayList<DisassemblerInventoryItem>();

			int index = 9;

			int listIndex = 0;

			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 9; col++) {

					res.add(new DisassemblerInventoryItem(listIndex, index, INVENTORY_ITEMS_POS_LIST.get(listIndex),
							ITEM.SIZE));

					index++;
					listIndex++;
				}
			}

			index = 0;

			for (int col = 0; col < 9; col++) {

				res.add(new DisassemblerInventoryItem(listIndex, index, INVENTORY_ITEMS_POS_LIST.get(listIndex),
						ITEM.SIZE));

				index++;
				listIndex++;
			}

			return res;
		}

		private static List<Integer> getInventoryActualIndex() {
			List<Integer> res = new ArrayList<Integer>();

			int index = 9;

			int listIndex = 0;

			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 9; col++) {

					res.add(index);

					index++;
					listIndex++;
				}
			}

			index = 0;

			for (int col = 0; col < 9; col++) {

				res.add(index);

				index++;
				listIndex++;
			}

			return res;
		}
	}

}