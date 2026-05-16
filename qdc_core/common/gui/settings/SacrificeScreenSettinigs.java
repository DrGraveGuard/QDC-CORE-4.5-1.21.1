package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings;

import java.awt.Color;
import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;

public class SacrificeScreenSettinigs {


	public class CONSTANTS {




		

		
		public class BUTTON {
			public static final Point SIZE = new Point(15, 12);
		}
		


	}

	public class MAIN_WINDOW {

		public static final Point POS = new Point(0, 0);
		public static final Point SIZE = new Point(400, 200);

	}

	public class EXIT_BUTTON {

		public static final Point POS = new Point(0, -20);
		public static final Point SIZE = new Point(104, 15);

	}
	public class MAIN_TITLE {

		public static final Point POS = new Point(EXIT_BUTTON.SIZE.x + 5, -20);
		public static final Point SIZE = new Point(MAIN_WINDOW.SIZE.x - (EXIT_BUTTON.SIZE.x + 5), 15);
		
		public static final Point TEXT_POS = new Point(POS.x + 3, POS.y + 2);
		public static final Point DECORATION_POS = new Point(POS.x,POS.y + SIZE.y -1);
		public static final int DECORATION_WIDTH = SIZE.x;

	}
	
	public class SACRIFICE_INFO {
		
		public static final Point POS = new Point(0,0);
		public static final Point SIZE = new Point(MAIN_WINDOW.SIZE.x,36);
		
		public static final Color TEXT_COLOR = Color.white;
		public static final TextureColor BG_COLOR = TextureColor.BLUE_1;
		
		public static final Point LINE_ONE_POS = new Point(POS.x+3,POS.y+3);
		public static final Point LINE_TWO_POS = new Point(LINE_ONE_POS.x,LINE_ONE_POS.y + 10);
		public static final Point LINE_THREE_POS = new Point(LINE_ONE_POS.x,LINE_TWO_POS.y + 10);
		
		
		
		
		
		public static final String LINE_ONE_TEXT = "This is a Mob Drop Loot System, based on damage done to any mob.";
		public static final String LINE_TWO_TEXT = "When progress reaches 100% the item will be dropped on next mob kill.";
		public static final String LINE_THREETEXT = "The last item in list, will be a Random Undiscovered Item.";
	}
	
	
	
	public class SUB_TITLE {

		public static final Point POS = new Point(0,SACRIFICE_INFO.SIZE.y+5);

		public static final int GAP = 6;

		public static final int TITLE_HEIGHT = 15;

		public static final Color TEXT_COLOR = Color.white;
		public static final TextureColor DECOR_COLOR = TextureColor.WHITE_1;
		public static final int DECOR_HEIGHT = 1;
		public static final int DECOR_GAP = 9;

		public class DROP_ITEM {

			public static final Point POS = new Point(SUB_TITLE.POS.x + 1, SUB_TITLE.POS.y + 2);
			public static final int WIDTH = 65;

			public static final Point TEXT_POS = new Point(POS.x + (WIDTH / 2), POS.y + 2);
			public static final Point DECORATION_POS = new Point(POS.x, TEXT_POS.y + DECOR_GAP);

		}

		public class CUR_AMOUNT {

			public static final Point POS = new Point(DROP_ITEM.POS.x + DROP_ITEM.WIDTH + GAP,
					SUB_TITLE.POS.y + 2);
			public static final int WIDTH = 65;

			public static final Point TEXT_POS = new Point(POS.x + (WIDTH / 2), POS.y + 2);
			public static final Point DECORATION_POS = new Point(POS.x, TEXT_POS.y + DECOR_GAP);
		}

		public class AMOUNT_LEFT {

			public static final Point POS = new Point(CUR_AMOUNT.POS.x + CUR_AMOUNT.WIDTH + GAP,
					SUB_TITLE.POS.y + 2);
			public static final int WIDTH = 65;

			public static final Point TEXT_POS = new Point(POS.x + (WIDTH / 2), POS.y + 2);
			public static final Point DECORATION_POS = new Point(POS.x, TEXT_POS.y + DECOR_GAP);


		}

		public class PROGRESS {

			public static final Point POS = new Point(AMOUNT_LEFT.POS.x + AMOUNT_LEFT.WIDTH + GAP, SUB_TITLE.POS.y + 2);
			public static final int WIDTH = 160;

			public static final Point TEXT_POS = new Point(POS.x + (WIDTH / 2), POS.y + 2);
			public static final Point DECORATION_POS = new Point(POS.x, TEXT_POS.y + DECOR_GAP);
		

			
			
		}


	}
	
	
	
	
	

	public class LOOT_ITEM {
						
			public static final Point ITEM_START_POS = new Point(0,SUB_TITLE.POS.y + SUB_TITLE.TITLE_HEIGHT);
			public static final int GAP = 2;
			
			public static final Point SIZE = new Point(MAIN_WINDOW.SIZE.x, 20);
			
			public static final TextureColor BG_COLOR = TextureColor.BLUE_1;
			
			public static final TextureColor BAR_BORDER = TextureColor.BLACK_2;
			public static final TextureColor BAR_BG = TextureColor.RED_2;
			public static final TextureColor BAR_FILL = TextureColor.GREEN_1;
			
			public static final Color TEXT_COLOR = Color.white;
			
			public static final Point BAR_BORDER_SIZE = new Point(SUB_TITLE.PROGRESS.WIDTH - 10, 14);
			
			
			public static final int BAR_FILL_HEIGHT = 12;
			
			public static final int BAR_FILL_MAX_WIDTH= SUB_TITLE.PROGRESS.WIDTH -12;
		

		}
		
		
}
