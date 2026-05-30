package com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.classes.disassembler;

import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.functions.ParticleFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.recipe_box.functions.PotionInitFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.common.gui.settings.AssemblerSettings.INFO_WINDOW;
import com.qdc_mod.qdc_core_4_5.qdc_core.functions.EnchantmentFunctions;
import com.qdc_mod.qdc_core_4_5.qdc_core.functions.classes.EnchantmentItemCollection;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemContainerContents;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;

public class DisassemblerInventoryItem {

	public int displayIndex = -1;
	public int actualIndex = -1;
	public Point pos;
	public Point size;
	public ItemStack stack;
	public boolean canBeDisassembled = false;
	public boolean isHoveringOver = false;
	public ParticleCollection particles = null;
	public EnchantmentItemCollection enchantCollection = null;

	public boolean canDisassembleMulti = true;

	public String itemName = "";

	public String modName = "";
	public String displayName = null;

	public String id;

	public boolean isEmpty = false;

	public boolean isHoveringOverMainItem = false;
	private final int maxDisplayNameLen = 30;

	public DisassemblerInventoryItem(int displayIndex, int actualIndex, Point pos, Point size) {

		this.displayIndex = displayIndex;
		this.actualIndex = actualIndex;
		this.pos = pos;
		this.size = size;

	}
	
	public DisassemblerInventoryItem(ItemStack stack)
	{
		setStack(stack);
	}

	public void setDisplayName() {
		if (itemName.length() > maxDisplayNameLen) {
			displayName = itemName.substring(0, maxDisplayNameLen);
			displayName += "...";
		} else {
			displayName = itemName;
		}
	}

	public void setStack(ItemStack is) {
		stack = is;

		isEmpty = false;

		if (is.getItem() == Items.AIR) {
			isEmpty = true;
			canBeDisassembled = false;
			return;
		}

		particles = null;

		id = GlobalFuncs.generateItemDataString(is);
		modName = GlobalFuncs.getModName(GlobalFuncs.getItemModID(is.getItem()));
		itemName = GlobalFuncs.getItemName(is.getItem());

		if (GlobalFuncs.isSusStew(is)) {
			itemName += " - " + GlobalFuncs.toCamelCase(GlobalFuncs.getSusStewEffectName(is));
		}

		if (is.isDamaged()) {
			itemName += " [" + GlobalFuncs.calcDamagePercFromDamagedItem(is) + "% Damaged]";
		}
		
		if (stack.has(DataComponents.CONTAINER)) {
			
			if(GlobalFuncs.isQuantumSeed(is))
			{
				ItemContainerContents contents = stack.get(DataComponents.CONTAINER);
		        if (contents != null) {
		        	
		        	itemName = stack.getDisplayName().getString();
		        	id+=itemName;
		        	particles =  QdcApi.QDC_CORE.FUNCTIONS.getItemParticles(contents.getStackInSlot(0));
		        	canBeDisassembled = true;
		        	setDisplayName();
		        	return;
		        }
				
				
			}	
			else if (vanillaContainerHasItems()) {
				canBeDisassembled = false;
				id += "_vanilla_contianer_not_empty";
				return;
			}
		}

		IItemHandler handler = stack.getCapability(Capabilities.ItemHandler.ITEM);

		if (handler != null)
			if (checkIfItemHandlerHasItems(handler)) {
				canBeDisassembled = false;
				id += "_itemhandler_not_empty";
				return;
			}

		particles = ParticleFunctions.getItemParticles(is);

		if (GlobalFuncs.isPotion(is)) {
			itemName = is.getDisplayName().getString();
		}

		if (particles == null)
			if (GlobalFuncs.isPotion(is)) {
				particles = PotionInitFunctions.getDefaultPotionParticles(is);

			} else if (GlobalFuncs.isEnchanted(is)) {

				particles = new ParticleCollection();

				enchantCollection = EnchantmentFunctions.getEnchanmentListFromItem(is);

				ParticleCollection baseItemParticles = ParticleFunctions.getItemParticles(new ItemStack(is.getItem()));

				if (baseItemParticles != null) {

					

					particles.addOtherParticleCollection(baseItemParticles);

				}

				if (enchantCollection != null) {
					particles.addOtherParticleCollection(enchantCollection.particles);

				}

			}

		if (particles != null) {

			if (is.isDamaged()) {
				particles.multiplyOnlyBase(GlobalFuncs.calcRemainingParticlesFromDamagedItem(is));
			}
			
			
			particles.setParticleList();
			canBeDisassembled = true;

			setDisplayName();
		} else {
			canBeDisassembled = false;
		}

	}

	private boolean checkIfItemHandlerHasItems(IItemHandler handler) {
		for (int i = 0; i < handler.getSlots(); i++) {
			// If any slot contains an item, the handler is not empty
			if (!handler.getStackInSlot(i).isEmpty()) {
				return true;
			}
		}

		return false;
	}

	private boolean vanillaContainerHasItems() {
		ItemContainerContents items = stack.get(DataComponents.CONTAINER);
		int slotCount = items.getSlots();

		for (int i = 0; i < slotCount; i++) {
			if (!items.getStackInSlot(i).isEmpty())
				return true;
		}

		return false;
	}

	public boolean isSame(DisassemblerInventoryItem toCompare) {
		if (isEmpty)
			return false;

		if (this.id.equals(toCompare.id))
			return true;

		return false;
	}

	public boolean isSameDisplayIndex(int index) {
		if (this.displayIndex == index)
			return true;

		return false;
	}

	public boolean checkIfHoveringOver(Point windowPos, int x, int y) {
		isHoveringOver = GuiFunctions.isHoveringOver(windowPos, pos, size, new Point(x, y));

		isHoveringOverMainItem = GuiFunctions.isHoveringOver(windowPos, INFO_WINDOW.MAIN_ITEM.POS,
				INFO_WINDOW.MAIN_ITEM.SIZE, new Point(x, y));

		return isHoveringOver;
	}

}
