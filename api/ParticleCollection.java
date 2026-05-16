package com.qdc_mod.qdc_core_4_5.api;

import java.util.ArrayList;
import java.util.List;

public class ParticleCollection {

	public static enum ParticleType {
		NATURE, FOOD, METAL, GEM, ENCHANTED, POTION

	}

	public static final int NATURE_INDEX = 0;
	public static final int FOOD_INDEX = 1;
	public static final int METAL_INDEX = 2;
	public static final int GEM_INDEX = 3;
	public static final int ENCHANTED_INDEX = 4;
	public static final int POTION_INDEX = 5;
	public static final int ERROR = -1;

	public double[] particles = new double[] { 0, 0, 0, 0, 0, 0 };

	public List<ParticleItem> particleList = null;

	public void addOtherParticleCollection(ParticleCollection otherCollection) {
		for (int i = 0; i < particles.length; i++) {
			particles[i] += otherCollection.particles[i];
		}

		setParticleList();
	}

	public void removeOtherParticleCollection(ParticleCollection otherCollection) {
		for (int i = 0; i < particles.length; i++) {
			particles[i] -= otherCollection.particles[i];
			if (particles[i] < 0)
				particles[i] = 0;
		}

		setParticleList();
	}

	public void multiply(double amount) {
		for (int i = 0; i < particles.length; i++) {
			particles[i] = particles[i] * amount;
		}

		setParticleList();
	}
	
	public void multiplyOnlyBase(double amount) {
		for (int i = 0; i < 4; i++) {
			
			if(particles[i] > 0)
			particles[i] = particles[i] * amount;
			
			if(particles[i] < 0)
				particles[i] = 0;
		}

		setParticleList();
	}

	public void divide(double amount) {
		for (int i = 0; i < particles.length; i++) {
			
			if(particles[i] > 0)
			particles[i] = particles[i] / amount;
		}

		setParticleList();
	}
	
	public void divideOnlyBase(double amount) {
		for (int i = 0; i < 4; i++) {
			if(particles[i] > 0)
			particles[i] = particles[i] / amount;
		}

		setParticleList();
	}

	public void setParticleList() {
		particleList = new ArrayList<ParticleItem>();

		for (ParticleType type : ParticleType.values()) {
			double curParticles = getParticles(type);

			if (curParticles > 0) {
				particleList.add(new ParticleItem(type, curParticles));
			}
		}

	}

	public boolean isEmpty() {
		for (double d : particles) {
			if (d > 0)
				return false;
		}

		return true;
	}

	public void setData(double[] particles) {
		this.particles = particles;

		setParticleList();
	}

	public ParticleCollection addNatureParticles(double particleAmount) {
		particles[NATURE_INDEX] += particleAmount;

		return this;
	}

	public ParticleCollection addFoodParticles(double particleAmount) {
		particles[FOOD_INDEX] += particleAmount;

		return this;
	}

	public ParticleCollection addMetalParticles(double particleAmount) {
		particles[METAL_INDEX] += particleAmount;

		return this;
	}

	public ParticleCollection addGemParticles(double particleAmount) {
		particles[GEM_INDEX] += particleAmount;

		return this;
	}

	public ParticleCollection addEnchantedParticles(double particleAmount) {
		particles[ENCHANTED_INDEX] += particleAmount;

		return this;
	}

	public ParticleCollection addPotionParticles(double particleAmount) {
		particles[POTION_INDEX] += particleAmount;

		return this;
	}

	public void addParticles(ParticleType particleType, double particleAmount) {
		int index = getIndexByType(particleType);

		if (index == ERROR)
			return;

		particles[index] += particleAmount;

	}

	public void removeParticles(ParticleType particleType, double particleAmount) {
		int index = getIndexByType(particleType);

		if (index == ERROR)
			return;

		particles[index] -= particleAmount;

		if (particles[index] < 0)
			particles[index] = 0;

	}

	public void setParticles(ParticleType particleType, double particleAmount) {
		int index = getIndexByType(particleType);

		if (index == ERROR)
			return;

		particles[index] = particleAmount;

	}

	public double getParticles(ParticleType particleType) {
		int index = getIndexByType(particleType);

		if (index == ERROR)
			return (double) ERROR;

		return particles[index];

	}

	private int getIndexByType(ParticleType particleType) {
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

	public ParticleCollection clone() {
		ParticleCollection newParticleObject = new ParticleCollection();

		newParticleObject.addNatureParticles(particles[NATURE_INDEX]).addFoodParticles(particles[FOOD_INDEX])
				.addMetalParticles(particles[METAL_INDEX]).addGemParticles(particles[GEM_INDEX])
				.addEnchantedParticles(particles[ENCHANTED_INDEX]).addPotionParticles(particles[POTION_INDEX]);

		return newParticleObject;
	}

}
