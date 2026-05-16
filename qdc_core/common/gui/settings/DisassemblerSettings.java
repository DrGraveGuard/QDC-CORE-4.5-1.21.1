package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;

public class DisassemblerSettings {

	public class ITEM {
		public static final Point SIZE = new Point(20, 27);
		public static final Point GAP_SIZE = new Point(2, 2);
	}
	

	
	
	public class PARTICLE_SEGEMENT {
		public static final Point SIZE = new Point(60, 18);
		public static final Point GAP_SIZE = new Point(2, 2);

		public static final Point START_POS = new Point(INFO_WINDOW.POS.x + 3, 24);
	}

	public class INFO_WINDOW {
		
		public static final Point POS = new Point(200, 0);
		public static final Point SIZE = new Point(195, 65);
		
		public class NAME
		{
			public static final Point POS = new Point(INFO_WINDOW.POS.x + 30,INFO_WINDOW.POS.y+5);
			public static final Color COLOR = Color.white;
			public static final Point SIZE = new Point(INFO_WINDOW.SIZE.x, 22);
		}
		
		public class ICON
		{
			public static final Point POS = new Point(INFO_WINDOW.POS.x + 10,INFO_WINDOW.POS.y+2);
			//public static final Point SIZE = new Point(170, 22);
		}
	
	}
	


	
	
	

	public static final List<Point> inventoryItemsPosList = GuiFunctions.generateIconPosList(36, 9, ITEM.SIZE,
			ITEM.GAP_SIZE, new Point(0, 0));
	
	public static final List<Point> particlesPosList = GuiFunctions.generateIconPosList(6, 3, PARTICLE_SEGEMENT.SIZE,
			PARTICLE_SEGEMENT.GAP_SIZE, PARTICLE_SEGEMENT.START_POS);

	


	
}
