package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;

public class MainMenuScreenSettings {

	public static enum RecipeStatus {
		NO_DISCOVERY, PARTIAL_DISCOVERY, COMPLETE_DISCOVERY

	}

	public static enum MainRecipeType {
		DISCOVERY, GENERAL, POTION, ARROW, BOOK

	}

	public class CONSTANTS {

		public class BUTTON {
			public static final Point SIZE = new Point(150, 15);
		}
	}

	public class MAIN_WINDOW {

		public static final Point POS = new Point(0, 0);
		public static final Point SIZE = new Point(150, 200);

	}


	public class MAIN_TITLE {

		public static final Point POS = new Point(-25, 0);
		public static final Point SIZE = new Point(200, 15);


	}

	public class MENU_BUTTON {
						
			public static final Point ITEM_START_POS = new Point(0,20);
			
			
			
			public class ITEMS {

				public static final int ITEM_LIST_MAX = 10;
				public static final int ITEM_LIST_WIDTH = 1;

				public static final Point SIZE = CONSTANTS.BUTTON.SIZE;
				public static final Point GAP_SIZE = new Point(1, 2);

				public static final List<Point> ITEM_POS_LIST = GuiFunctions.generateIconPosList(ITEM_LIST_MAX,
						ITEM_LIST_WIDTH, SIZE, GAP_SIZE, ITEM_START_POS);
			}

		}
		
		
}
