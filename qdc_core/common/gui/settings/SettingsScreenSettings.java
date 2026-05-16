package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.disassembler.DisassemblerInventoryItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.INFO_WINDOW;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA;

public class SettingsScreenSettings {

	public static enum RecipeStatus {
		NO_DISCOVERY, PARTIAL_DISCOVERY, COMPLETE_DISCOVERY

	}

	public static enum MainRecipeType {
		DISCOVERY, GENERAL, POTION, ARROW, BOOK

	}

	public class CONSTANTS {

		public static final int ITEM_LIST_MAX = 40;
		public static final int ITEM_LIST_WIDTH = 5;

		public static final int RECIPE_ITEM_LIST_MAX = 9;
		public static final int RECIPE_ITEM_LIST_WIDTH = 4;

		public static final int PARTICLE_LIST_MAX = 6;
		public static final int PARTICLE_LIST_WIDTH = 2;

		public static final TextureColor ITEM_BORDER = TextureColor.WHITE_2;
		public static final TextureColor ITEM_BORDER_HOVER = TextureColor.GREEN_2;
		


		public class BUTTON {
			public static final Point SIZE = new Point(15, 12);
		}
	}

	public class MAIN_WINDOW {

		public static final Point POS = new Point(0, 0);
		public static final Point SIZE = new Point(420, 200);

	}

	public class EXIT_BUTTON {

		public static final Point POS = new Point(0, -20);
		public static final Point SIZE = new Point(104, 15);

	}

	public class MAIN_TITLE {

		public static final Point POS = new Point(EXIT_BUTTON.SIZE.x + 5, -20);
		public static final Point SIZE = new Point(305, 15);
		
		public static final Point TEXT_POS = new Point(POS.x + 3, POS.y + 2);
		public static final Point DECORATION_POS = new Point(POS.x,POS.y + SIZE.y -1);
		public static final int DECORATION_WIDTH = SIZE.x;

	}

	public class SEARCH_WINDOW {

		public static final Point POS = new Point(0, 0);
		public static final Point SIZE = new Point(104, 19);

	}

	public class SEARCH_RESULT_WINDOW {

		public static final Point POS = new Point(SEARCH_WINDOW.POS.x, SEARCH_WINDOW.POS.y + SEARCH_WINDOW.SIZE.y + 5);
		public static final Point SIZE = new Point(SEARCH_WINDOW.SIZE.x, 184);

		public class BUTTONS {

			public class PREV {
				public static final Point POS = new Point(SEARCH_RESULT_WINDOW.POS.x + 5,
						SEARCH_RESULT_WINDOW.POS.y + 5);
			}

			public class NEXT {
				public static final Point POS = new Point(
						SEARCH_RESULT_WINDOW.POS.x + SEARCH_RESULT_WINDOW.SIZE.x - (CONSTANTS.BUTTON.SIZE.x + 5),
						PREV.POS.y);
			}

		}

		public class TAB_INDEX {

			public static final Point POS = new Point(SEARCH_RESULT_WINDOW.POS.x +SEARCH_RESULT_WINDOW.SIZE.x /2,
					BUTTONS.PREV.POS.y);
			public static final Color TEXT_COLOR = Color.white;

		}

		public class ITEMS {
			public static final Point POS = new Point(SEARCH_RESULT_WINDOW.POS.x + 1,
					BUTTONS.PREV.POS.y + CONSTANTS.BUTTON.SIZE.y + 3);
			public static final Point SIZE = new Point(18, 18);
			public static final Point GAP_SIZE = new Point(2, 2);

			public static final TextureColor DEFAULT_BG = TextureColor.BLUE_1;
			public static final TextureColor AUTO_SET_BG = TextureColor.ORANGE_2;
			public static final TextureColor ACTIVE_BG = TextureColor.GREEN_2;

			public static final List<Point> ITEM_POS_LIST = GuiFunctions.generateIconPosList(CONSTANTS.ITEM_LIST_MAX,
					CONSTANTS.ITEM_LIST_WIDTH, SIZE, GAP_SIZE, POS);
		}

	}

	public class INFO_WINDOW {

		public static final Point POS = new Point(SEARCH_RESULT_WINDOW.POS.x + SEARCH_RESULT_WINDOW.SIZE.x + 5, 0);
		public static final Point SIZE = new Point(MAIN_TITLE.SIZE.x, 121);

		public class SETTINGS_AREA {

			public static final Point SIZE = new Point(INFO_WINDOW.SIZE.x - 5, INFO_WINDOW.SIZE.y);
			public static final Point GAP_SIZE = new Point(1, 1);

			public static final int BUTTON_HEIGHT = 10;

			public class TITLE {

				public static final Point POS = new Point(INFO_WINDOW.POS.x + 2, INFO_WINDOW.POS.y);

				public static final int GAP = 4;

				public static final int TITLE_HEIGHT = 15;

				public static final Color TEXT_COLOR = Color.white;
				public static final TextureColor DECOR_COLOR = TextureColor.WHITE_1;
				public static final int DECOR_HEIGHT = 1;
				public static final int DECOR_GAP = 9;

				public class PARTICLE_TYPE {

					public static final Point POS = new Point(TITLE.POS.x + 1, TITLE.POS.y + 2);
					public static final int WIDTH = 25;

					public static final Point TEXT_POS = new Point(POS.x + (WIDTH / 2), POS.y + 2);
					public static final Point DECORATION_POS = new Point(POS.x, TEXT_POS.y + DECOR_GAP);

				}

				public class CUR_AMOUNT {

					public static final Point POS = new Point(PARTICLE_TYPE.POS.x + PARTICLE_TYPE.WIDTH + GAP,
							TITLE.POS.y + 2);
					public static final int WIDTH = 40;

					public static final Point TEXT_POS = new Point(POS.x + (WIDTH / 2), POS.y + 2);
					public static final Point DECORATION_POS = new Point(POS.x, TEXT_POS.y + DECOR_GAP);
				}

				public class EDIT {

					public static final Point POS = new Point(CUR_AMOUNT.POS.x + CUR_AMOUNT.WIDTH + GAP,
							TITLE.POS.y + 2);
					public static final int WIDTH = 39;

					public static final Point TEXT_POS = new Point(POS.x + (WIDTH / 2), POS.y + 2);
					public static final Point DECORATION_POS = new Point(POS.x, TEXT_POS.y + DECOR_GAP);

					public static final int CANCEL_BUTTON_Y = BUTTON_HEIGHT + 1;

				}

				public class MAX {

					public static final Point POS = new Point(EDIT.POS.x + EDIT.WIDTH + GAP, TITLE.POS.y + 2);
					public static final int WIDTH = 25;

					public static final Point TEXT_POS = new Point(POS.x + (WIDTH / 2), POS.y + 2);
					public static final Point DECORATION_POS = new Point(POS.x, TEXT_POS.y + DECOR_GAP);
				}

				public class MIN {

					public static final Point POS = new Point(MAX.POS.x + MAX.WIDTH + GAP, TITLE.POS.y + 2);
					public static final int WIDTH = 25;

					public static final Point TEXT_POS = new Point(POS.x + (WIDTH / 2), POS.y + 2);
					public static final Point DECORATION_POS = new Point(POS.x, TEXT_POS.y + DECOR_GAP);
				}

				public class DEFAULT_AMOUNT {

					public static final Point POS = new Point(MIN.POS.x + MIN.WIDTH + GAP, TITLE.POS.y + 2);
					public static final int WIDTH = 40;

					public static final Point TEXT_POS = new Point(POS.x + (WIDTH / 2), POS.y + 2);
					public static final Point DECORATION_POS = new Point(POS.x, TEXT_POS.y + DECOR_GAP);
				}

				public class SET_TO_DEFAULT_AMOUNT {

					public static final Point POS = new Point(DEFAULT_AMOUNT.POS.x + DEFAULT_AMOUNT.WIDTH + GAP,
							TITLE.POS.y + 2);
					public static final int WIDTH = 75;

					public static final Point TEXT_POS = new Point(POS.x + (WIDTH / 2), POS.y + 2);
					public static final Point DECORATION_POS = new Point(POS.x, TEXT_POS.y + DECOR_GAP);
				}

			}

		}

		public class SETTINGS_ITEM {

			public static final Point SIZE = new Point(INFO_WINDOW.SIZE.x - 4, 25);

			public static final int ICON_HEIGHT = 16;
			public static final int TXT_HEIGHT = 12;
			public static final int STR_HEIGHT = 8;
			public static final int BTN_HEIGHT = SETTINGS_AREA.BUTTON_HEIGHT;

			public static final Point ICON_GAP = new Point(-8, calcGap(ICON_HEIGHT));
			public static final int TXT_GAP = calcGap(TXT_HEIGHT);
			public static final int STR_GAP = calcGap(STR_HEIGHT);
			public static final int BTN_GAP = calcGap(BTN_HEIGHT);

			public static final TextureColor AVAILBLE = TextureColor.GREEN_1;
			public static final TextureColor NOT_AVAILBLE = TextureColor.RED_1;

			public static final Color TEXT_COLOR = Color.white;
			
			public static final Color TXT_COLOR_VALID = Color.white;
			public static final Color TXT_COLOR_NOT_VALID = Color.red;
			

			public static final int GAP = 1;

			public static final int NATURE_POS = SETTINGS_AREA.TITLE.POS.y + SETTINGS_AREA.TITLE.TITLE_HEIGHT + 1;
			public static final int FOOD_POS = NATURE_POS + SIZE.y + GAP;
			public static final int METAL_POS = FOOD_POS + SIZE.y + GAP;
			public static final int GEM_POS = METAL_POS + SIZE.y + GAP;

			public static int calcGap(int height) {
				return (SIZE.y / 2) - (height / 2);
			}
		}

		public class MSG_AREA {

			public static final Point POS = new Point(INFO_WINDOW.POS.x,
					INFO_WINDOW.POS.y + SETTINGS_AREA.SIZE.y + 3);
			public static final Point SIZE = new Point(INFO_WINDOW.SIZE.x, 25);
			public static final Point TITLE_POS = new Point(POS.x + 3, POS.y + 3);
			public static final Point TEXT_DECORATION_POS = new Point(TITLE_POS.x, TITLE_POS.y + 12);
			public static final Point TEXT_POS = new Point(TEXT_DECORATION_POS.x + 20, TEXT_DECORATION_POS.y);
			
			public static final Point DECORATION_POS = new Point(POS.x + 5, TITLE_POS.y + 9);
			public static final Point DECORATION_SIZE = new Point(SIZE.x-10, 1);
			public static final TextureColor DECORATION_COLOR = TextureColor.BLUE_2;
			
			
			public static final TextureColor BG_COLOR = TextureColor.BLACK_1;

			public static final Color TITLE_TEXT_COLOR = Color.cyan;
			public static final Color TEXT_COLOR_ERROR = Color.red;
			public static final Color TEXT_COLOR_SUCCESS = Color.green;
			public static final Color TEXT_COLOR_DEFAULT = Color.green;
		}

		public class ITEMS_AFFECTED_AREA {

			public static final Point POS = new Point(INFO_WINDOW.POS.x, MSG_AREA.POS.y + MSG_AREA.SIZE.y + 2);
			public static final Point SIZE = new Point(INFO_WINDOW.SIZE.x, 52);
			public static final Point TITLE_POS = new Point(POS.x + 3, POS.y+3);

			public static final TextureColor BG_COLOR = TextureColor.BLACK_1;

			public static final Color TITLE_COLOR = Color.red;
			
			public static final Point DECORATION_POS = new Point(POS.x + 5, TITLE_POS.y + 9);
			public static final Point DECORATION_SIZE = new Point(SIZE.x-10, 1);
			public static final TextureColor DECORATION_COLOR = TextureColor.RED_1;

			public class BUTTONS {

				public static final Point SIZE = new Point(10, 33);
				public static final int GAP = 3;
				
				public class PREV {
					public static final Point POS = new Point(ITEMS_AFFECTED_AREA.POS.x + GAP,
							ITEMS_AFFECTED_AREA.DECORATION_POS.y + 5);
				}

				public class NEXT {
					public static final Point POS = new Point(ITEMS_AFFECTED_AREA.POS.x + ITEMS_AFFECTED_AREA.SIZE.x - (BUTTONS.SIZE.x + GAP)
							,PREV.POS.y);
				}

			}
			
						
			public static final Point ITEM_START_POS = new Point(POS.x + BUTTONS.SIZE.x + BUTTONS.GAP, DECORATION_POS.y + 3);
			
			
			
			public class ITEMS {

				public static final int ITEM_LIST_MAX = 32;
				public static final int ITEM_LIST_WIDTH = 16;

				public static final Point SIZE = new Point(16, 16);
				public static final Point GAP_SIZE = new Point(1, 1);

				public static final List<Point> ITEM_POS_LIST = GuiFunctions.generateIconPosList(ITEM_LIST_MAX,
						ITEM_LIST_WIDTH, SIZE, GAP_SIZE, ITEM_START_POS);
			}

		}
		
		
		public class CREATIVE_ERROR
		{
			public static final Point POS = new Point(SEARCH_RESULT_WINDOW.POS.x + SEARCH_RESULT_WINDOW.SIZE.x + 5, 0);
			public static final Point SIZE = new Point(MAIN_TITLE.SIZE.x, 35);
			

			public static final TextureColor BG = TextureColor.RED_1;
			public static final Color TEXT_COLOR = Color.white;
			
			
			
			public static final String LINE_1_TEXT = "This feature is only available in Creative Mode!";
			public static final Point LINE_1_POS = new Point(POS.x + 5, POS.y+5);
			
			public static final String LINE_2_TEXT = "To edit particles of ingredients, make another Creative";
			public static final Point LINE_2_POS = new Point(POS.x + 5, POS.y+15);
			
			public static final String LINE_3_TEXT = "World, the particle settings will apply to all worlds.";
			public static final Point LINE_3_POS = new Point(POS.x + 5, POS.y+25);
			
		}
		
		public class INFO
		{
			public static final Point POS = new Point(SEARCH_RESULT_WINDOW.POS.x + SEARCH_RESULT_WINDOW.SIZE.x + 5, 0);
			public static final Point SIZE = new Point(MAIN_TITLE.SIZE.x, 50);
			

			public static final TextureColor BG = TextureColor.BLUE_1;
			public static final Color TEXT_COLOR = Color.white;
			
			
			
			public static final String LINE_1_TEXT = "In this GUI one can edit the particles of 'Ingredients',";
			public static final Point LINE_1_POS = new Point(POS.x + 5, POS.y+5);
			
			public static final String LINE_2_TEXT = "items that are not created from a recipe.";
			public static final Point LINE_2_POS = new Point(POS.x + 5, POS.y+15);
			
			public static final String LINE_3_TEXT = "Items created from recipes will have their particles";
			public static final Point LINE_3_POS = new Point(POS.x + 5, POS.y+30);
			
			public static final String LINE_4_TEXT = "calculated from 'Ingredients'";
			public static final Point LINE_4_POS = new Point(POS.x + 5, POS.y+40);
			
		}
	}

}
