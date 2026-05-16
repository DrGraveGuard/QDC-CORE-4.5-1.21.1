package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.info_window;

import java.awt.Color;
import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;
import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings;

import net.minecraft.world.item.Item;

public class ParticleGuiItem {

	public ParticleType type;
	public double amount;
	public Item icon;
	public Point pos;
	public TextureColor bgColor;
	public Color textColor;
	public String strVal = "";

	public boolean isHoveringOver = false;

	public ParticleGuiItem(ParticleType type, double amount, Point pos, boolean isDisassembler) {

		this.type = type;
		this.amount = amount;
		this.pos = pos;

		this.strVal = GlobalFuncs.fixDouble(amount);

		this.icon = QdcApi.QDC_CORE.FUNCTIONS.getParticleIconItem(type);

		checkForColor(isDisassembler);
	}

	private void checkForColor(boolean isDisassembler) {
		if (isDisassembler) {
			bgColor = AssemblerSettings.INFO_WINDOW.PARTICLES.COLOR_DEFAULT;
			textColor = AssemblerSettings.INFO_WINDOW.PARTICLES.TEXT_COLOR_DEFAULT;
		} else {

			if (QdcApi.QDC_CORE.FUNCTIONS.hasEnoughParticles(type, amount)) {
				bgColor = AssemblerSettings.INFO_WINDOW.PARTICLES.COLOR_HAVE_ENOUGH;
				textColor = AssemblerSettings.INFO_WINDOW.PARTICLES.TEXT_COLOR_HAVE;
			} else {
				bgColor = AssemblerSettings.INFO_WINDOW.PARTICLES.COLOR_NOT_HAVE_ENOUGH;
				textColor = AssemblerSettings.INFO_WINDOW.PARTICLES.TEXT_COLOR_NOT_HAVE;
			}
		}
	}

	public ParticleGuiItem checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		isHoveringOver = GuiFunctions.isHoveringOver(windowPos, pos, AssemblerSettings.INFO_WINDOW.PARTICLES.SIZE,
				new Point(mouseX, mouseY));

		if (isHoveringOver)
			return this;

		return null;
	}
}
