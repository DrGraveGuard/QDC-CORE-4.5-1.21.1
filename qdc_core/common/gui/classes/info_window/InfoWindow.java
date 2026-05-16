package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.info_window;

import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.assembler.AssemblerItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.disassembler.DisassemblerInventoryItem;

import net.minecraft.world.item.Items;

public class InfoWindow {

	public DisassemblerInventoryItem curDisasemblyItem = null;
	public AssemblerItem curAssemblyItem = null;
	public ParticleGuiItemBox particleGuiItemBox = null;

	public boolean btnDisassembleVisibile = false;
	public boolean btnDisassembleActive = false;

	public boolean btnAssembleVisibile = false;
	public boolean btnAssembleActive = false;

	public void updateCurDisassemblyItem() {
		if (curDisasemblyItem != null) {
			if (curDisasemblyItem.stack.getItem() == Items.AIR) {
				deactivateAllDisassemblerButtons();
				curDisasemblyItem = null;
				particleGuiItemBox = null;
			}
		}
	}

	public void resetButtons() {
		deactivateAllAssemblerButtons();
		deactivateAllDisassemblerButtons();
	}

	public void refreshParticles() {

		if (particleGuiItemBox == null)
			return;

		if (curDisasemblyItem != null) {

			this.particleGuiItemBox = new ParticleGuiItemBox(curDisasemblyItem.particles, true);
		} else if (curAssemblyItem != null) {
			this.particleGuiItemBox = new ParticleGuiItemBox(curAssemblyItem.itemData.itemParticles, false);
		}

		handleAssemblerButtons();
	}

	public void setAssemblyWindow(AssemblerItem curAssemblyItem) {
		this.curDisasemblyItem = null;
		deactivateAllDisassemblerButtons();
		this.curAssemblyItem = curAssemblyItem;
		this.particleGuiItemBox = new ParticleGuiItemBox(curAssemblyItem.itemData.itemParticles, false);
		handleAssemblerButtons();
	}

	public void setDisassemblyWindow(DisassemblerInventoryItem curDisasemblyItem) {
		this.curAssemblyItem = null;
		deactivateAllAssemblerButtons();
		this.curDisasemblyItem = curDisasemblyItem;
		this.particleGuiItemBox = new ParticleGuiItemBox(curDisasemblyItem.particles, true);

		handleDisassemblerButtons();

	}

	public void checkIfHoveringOverParticles(Point windowPos, int mouseX, int mouseY) {
		if (particleGuiItemBox != null) {
			particleGuiItemBox.checkIfHoveringOver(windowPos, mouseX, mouseY);
		}
	}

	public void handleAssemblerButtons() {
		deactivateAllAssemblerButtons();

		if (curAssemblyItem == null)
			return;

		if (curAssemblyItem.itemData == null)
			return;

		if (curAssemblyItem.itemData.canCreate) {
			if (curAssemblyItem.itemData.canCreateAmount > 0) {
				btnAssembleVisibile = true;
				btnAssembleActive = true;
			} else {
				btnAssembleVisibile = true;
				btnAssembleActive = false;
			}

		} else {
			btnAssembleVisibile = true;
			btnAssembleActive = false;

		}

	}

	public void handleDisassemblerButtons() {

		btnDisassembleVisibile = true;
		btnDisassembleActive = true;

	}

	private void deactivateAllDisassemblerButtons() {

		btnDisassembleVisibile = false;
		btnDisassembleActive = false;

	}

	private void deactivateAllAssemblerButtons() {

		btnAssembleVisibile = false;
		btnAssembleActive = false;
	}

}
