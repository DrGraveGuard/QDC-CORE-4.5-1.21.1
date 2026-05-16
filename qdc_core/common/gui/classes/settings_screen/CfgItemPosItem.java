package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.settings_screen;

import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SettingsScreenSettings.INFO_WINDOW;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.SettingsScreenSettings.INFO_WINDOW.SETTINGS_AREA;

public class CfgItemPosItem {

	public Point windowPos;
	public Point iconPos;
	public Point iconHoverPos;
	public Point curAmountPos;
	public Point txtPos;
	public Point editPos;
	public Point savePos;
	public Point cancelPos;
	public Point maxPos;
	public Point minPos;
	public Point defaultValPos;
	public Point setToDefaultPos;
	
	
	
	
	
	
	public CfgItemPosItem(int yPos)
	{
		setupPositions(yPos);
	}
	
	private void setupPositions(int yPos)
	{
		
		Point ICON_GAP = INFO_WINDOW.SETTINGS_ITEM.ICON_GAP;
		int TXT_GAP = INFO_WINDOW.SETTINGS_ITEM.TXT_GAP;
		int STR_GAP = INFO_WINDOW.SETTINGS_ITEM.STR_GAP;
		int BTN_GAP = INFO_WINDOW.SETTINGS_ITEM.BTN_GAP;
		
		
		windowPos = new Point(SETTINGS_AREA.TITLE.POS.x, yPos);
		
		iconPos = new Point(SETTINGS_AREA.TITLE.PARTICLE_TYPE.TEXT_POS.x + ICON_GAP.x,yPos + ICON_GAP.y);
		iconHoverPos = new Point(iconPos.x-2,iconPos.y-2);
		
		
		// strings
		curAmountPos = new Point(SETTINGS_AREA.TITLE.CUR_AMOUNT.TEXT_POS.x,yPos + STR_GAP);
		maxPos = new Point(SETTINGS_AREA.TITLE.MAX.TEXT_POS.x,yPos + STR_GAP);
		minPos = new Point(SETTINGS_AREA.TITLE.MIN.TEXT_POS.x,yPos + STR_GAP);
		defaultValPos = new Point(SETTINGS_AREA.TITLE.DEFAULT_AMOUNT.TEXT_POS.x,yPos + STR_GAP);
		
		// textbox
		txtPos = new Point(SETTINGS_AREA.TITLE.CUR_AMOUNT.DECORATION_POS.x-2,yPos + TXT_GAP);
		
		//buttons
		editPos = new Point(SETTINGS_AREA.TITLE.EDIT.DECORATION_POS.x,yPos+ BTN_GAP);
		savePos = new Point(SETTINGS_AREA.TITLE.EDIT.DECORATION_POS.x,yPos+2);
		cancelPos = new Point(SETTINGS_AREA.TITLE.EDIT.DECORATION_POS.x,SETTINGS_AREA.TITLE.EDIT.CANCEL_BUTTON_Y+ yPos+2);
		setToDefaultPos = new Point(SETTINGS_AREA.TITLE.SET_TO_DEFAULT_AMOUNT.DECORATION_POS.x,yPos + BTN_GAP);
		
	}
	
}
