package net.fabricmc.andermod.armor;

import net.fabricmc.andermod.AnderMod;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class ApronArmorMaterial implements ArmorMaterial {
	private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
	private static final int[] PROTECTION_VALUES = new int[] {1, 2, 3, 1}; 
 
	@Override
	public int getDurability(EquipmentSlot slot) {
		return BASE_DURABILITY[slot.getEntitySlotId()] * 1;
	}
 
	@Override
	public int getProtectionAmount(EquipmentSlot slot) {
		return PROTECTION_VALUES[slot.getEntitySlotId()];
	}
 
	@Override
	public int getEnchantability() {
		return 0;
	}
 
	@Override
	public SoundEvent getEquipSound() {
		return AnderMod.APRON_SOUND_EVENT;
	}
 
	@Override
	public Ingredient getRepairIngredient() {
		return null;
	}
 
	@Override
	public String getName() {
		return "apron";
	}
 
	@Override
	public float getToughness() {
		return 0.1F;
	}
 
	@Override
	public float getKnockbackResistance() {
		return 0.1F;
	}
}