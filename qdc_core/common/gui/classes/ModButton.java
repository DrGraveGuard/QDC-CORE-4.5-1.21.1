package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes;

import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.functions.GuiFunctions;

public class ModButton {

	public String text;
	public Point pos;
	public Point textPos;
	public Point size;
	public boolean isHoveringOver = false;
	public boolean isEnabled = true;
	public boolean isVisible = true;
	
	public ModButton(Point pos, Point size, String text)
	{
		this.pos = pos;
		this.size = size;
		this.text = text;
		setTextPos();
	}
	
	public ModButton(Point size, String text)
	{
		this.size = size;
		this.text = text;
	}
	
	public void setPos(Point pos)
	{
		this.pos = pos;
		setTextPos();
	}
	
	public ModButton(Point pos, Point size, String text, boolean isEnabled)
	{
		this.pos = pos;
		this.size = size;
		this.text = text;

		setTextPos();
		this.isEnabled = isEnabled;
	}
	
	public boolean isClickable()
	{
		return isEnabled && isVisible;
	}
	
	public void setEnabledState(boolean isEnabled)
	{
		this.isEnabled = isEnabled;
	}
	
	public void setVisibleState(boolean isVisible)
	{
		this.isVisible = isVisible;
	}
	
	private void setTextPos()
	{
		this.textPos = new Point(pos.x + (size.x/2), pos.y + (size.y/2) - 4);
	}
	
	public boolean checkIfHoveringOver(Point windowPos, int mouseX, int mouseY)
	{
		isHoveringOver = GuiFunctions.isHoveringOver(windowPos, pos, size, new Point(mouseX,mouseY));
		return isHoveringOver;
	}
	
}
