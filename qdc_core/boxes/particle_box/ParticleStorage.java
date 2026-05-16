package com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box;

import java.math.BigDecimal;
import java.util.function.Supplier;

import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;
import com.qdc_mod.qdc_core_4_5.qdc_core.boxes.particle_box.classes.ParticleStorageItem;
import com.qdc_mod.qdc_core_4_5.qdc_core.core.init.AttachmentInit;

import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.AttachmentType;

public class ParticleStorage {

	public static final int NATURE_INDEX = 0;
	public static final int FOOD_INDEX = 1;
	public static final int METAL_INDEX = 2;
	public static final int GEM_INDEX = 3;
	public static final int ENCHANTED_INDEX = 4;
	public static final int POTION_INDEX = 5;
	public static final int ERROR = -1;

	public static ParticleStorageItem[] particles = new ParticleStorageItem[] {
			new ParticleStorageItem(ParticleType.NATURE), new ParticleStorageItem(ParticleType.FOOD),
			new ParticleStorageItem(ParticleType.METAL), new ParticleStorageItem(ParticleType.GEM),
			new ParticleStorageItem(ParticleType.ENCHANTED), new ParticleStorageItem(ParticleType.POTION) };

	
	
	public static int countCanMakeAmount(ParticleCollection itemParticles) {
		
		int min = 0;
		
		for (ParticleStorageItem storageItem : particles) {
			double particleAmount = itemParticles.getParticles(storageItem.type);

			if (particleAmount > 0) {
				int temp = storageItem.countCanMake(particleAmount);
				
				if(temp <1)
					return 0;
				
				if(min == 0)
				{
					min = temp;
				}
				else
				{
					if(min > temp)
					{
						min = temp;
					}
				}
			}
		}

		
		
		return min;
	}
	
	public static void setParticleAmount(ParticleType particleType, String amountStr) {
		int index = getIndexByType(particleType);

		if (index == ERROR)
			return;

		if (amountStr.isBlank())
			particles[index].setParticles("0");
		else
			particles[index].setParticles(amountStr);
	}

	public static String getParticlesString(ParticleType particleType) {
		int index = getIndexByType(particleType);

		if (index == ERROR)
			return null;

		return particles[index].strVal;

	}

	public static  double getParticlesDouble(ParticleType particleType) {
		return getParticlesBigDecimal(particleType).doubleValue();
	}
	
	public static  BigDecimal getParticlesBigDecimal(ParticleType particleType) {
		int index = getIndexByType(particleType);

		if (index == ERROR)
			return null;

		return particles[index].particles;

	}

	private static int getIndexByType(ParticleType particleType) {
		switch (particleType) {
		case NATURE:
			return NATURE_INDEX;
		case FOOD:
			return FOOD_INDEX;
		case METAL:
			return METAL_INDEX;
		case GEM:
			return GEM_INDEX;
		case POTION:
			return POTION_INDEX;
		case ENCHANTED:
			return ENCHANTED_INDEX;

		default:
			return ERROR;

		}
	}

	public static void addParticleCollection(ParticleCollection toAdd) {
		for (ParticleStorageItem storageItem : particles) {
			double particleAmount = toAdd.getParticles(storageItem.type);

			if (particleAmount > 0) {
				storageItem.addParticles(particleAmount);
			}
		}
	}

	public static boolean removeParticleCollection(ParticleCollection toRemove) {
		if (!hasEnoughParticles(toRemove))
			return false;

		for (ParticleStorageItem storageItem : particles) {
			double particleAmount = toRemove.getParticles(storageItem.type);

			if (particleAmount > 0) {
				storageItem.removeParticles(particleAmount);
			}
		}

		return true;
	}

	public static boolean hasEnoughParticles(ParticleCollection toRemove) {
		for (ParticleStorageItem storageItem : particles) {
			double particleAmount = toRemove.getParticles(storageItem.type);

			if (particleAmount > 0) {
				if (!storageItem.hasEnoughParticles(particleAmount)) {
					return false;
				}
			}
		}

		return true;
	}
	

	public static boolean hasEnoughParticles(ParticleType type, double amount) {
		for (ParticleStorageItem storageItem : particles) {
			
			if(storageItem.type == type)
			{
				return storageItem.hasEnoughParticles(amount);
			}
			
		}

		return false;
	}
	
	public static void loadPlayerParticles(Player player) {
		String natureString = getPlayerParticle(player, AttachmentInit.NATURE_PATICLES);
		String foodString = getPlayerParticle(player, AttachmentInit.FOOD_PATICLES);
		String metalString = getPlayerParticle(player, AttachmentInit.METAL_PATICLES);
		String gemString = getPlayerParticle(player, AttachmentInit.GEM_PATICLES);
		String enchatedString = getPlayerParticle(player, AttachmentInit.ENCHANTED_PARTICLES);
		String potionString = getPlayerParticle(player, AttachmentInit.POTION_PARTICLES);

		setParticleAmount(ParticleType.NATURE, natureString);
		setParticleAmount(ParticleType.FOOD, foodString);
		setParticleAmount(ParticleType.METAL, metalString);
		setParticleAmount(ParticleType.GEM, gemString);
		setParticleAmount(ParticleType.ENCHANTED, enchatedString);
		setParticleAmount(ParticleType.POTION, potionString);
	}
	
	private static String getPlayerParticle(Player player, Supplier<AttachmentType<String>> attachment) {
		if (player.hasData(attachment)) {
			String particlesString = player.getData(attachment);

			return particlesString;
		} else {
			player.setData(attachment, "0");
		}

		return "0";
	}
	
	public static void saveData(Player player) {

		if (particles[NATURE_INDEX].particles != null) {
			player.setData(AttachmentInit.NATURE_PATICLES, particles[NATURE_INDEX].particles.toString());
		}
		
		if (particles[FOOD_INDEX].particles != null) {
			player.setData(AttachmentInit.FOOD_PATICLES, particles[FOOD_INDEX].particles.toString());
		}
		
		if (particles[METAL_INDEX].particles != null) {
			player.setData(AttachmentInit.METAL_PATICLES, particles[METAL_INDEX].particles.toString());
		}
		
		if (particles[GEM_INDEX].particles != null) {
			player.setData(AttachmentInit.GEM_PATICLES, particles[GEM_INDEX].particles.toString());
		}
		
		if (particles[ENCHANTED_INDEX].particles != null) {
			player.setData(AttachmentInit.ENCHANTED_PARTICLES, particles[ENCHANTED_INDEX].particles.toString());
		}
		
		if (particles[POTION_INDEX].particles != null) {
			player.setData(AttachmentInit.POTION_PARTICLES, particles[POTION_INDEX].particles.toString());
		}


	}
}
