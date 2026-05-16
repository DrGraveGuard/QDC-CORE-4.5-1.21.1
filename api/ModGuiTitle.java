package com.qdc_mod.qdc_core_4_5.api;

import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TtleType;

public class ModGuiTitle {

	public TtleType type;
	public String text;
	public Point borderPos;
	public Point borderSize;
	
	public Point fillPos;
	public Point fillSize;
	
	public Point textPos;
	public Point decorationPos;
	public Point decorationSize;

	private final int TITLE_LEFT_GAP = 3;
	private final int MAIN_TITLE_DECOR_HEIGHT = 2;
	private final int SUB_TITLE_DECOR_HEIGHT = 1;
	


	public ModGuiTitle(TtleType type, String text, Point pos, Point size) {

		this.type = type;
		this.text = text;
		this.borderPos = pos;
		this.borderSize = size;
		
		setData();
	}

	private void setData() {
		
		this.fillPos = new Point(borderPos.x + 1, borderPos.y + 1);
		this.fillSize = new Point(borderSize.x -2, borderSize.y -2);
		
		this.textPos = new Point(borderPos.x + TITLE_LEFT_GAP, borderPos.y + (borderSize.y / 2) - 4);
		this.decorationPos = new Point(borderPos.x + 1, borderPos.y + borderSize.y - 2);

		if (type == TtleType.MAIN_TITLE)
			this.decorationSize = new Point(borderSize.x - 2, MAIN_TITLE_DECOR_HEIGHT);
		else
			this.decorationSize = new Point(borderSize.x - 2, SUB_TITLE_DECOR_HEIGHT);
	}

}
