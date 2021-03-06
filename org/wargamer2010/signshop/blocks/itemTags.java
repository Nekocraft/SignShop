package org.wargamer2010.signshop.blocks;


import com.bergerkiller.bukkit.common.SafeField;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import net.minecraft.server.ItemStack;
import net.minecraft.server.NBTTagCompound;
import org.bukkit.Material;

public class itemTags implements IItemTags {
    @Override
    public org.bukkit.inventory.ItemStack copyTags(org.bukkit.inventory.ItemStack from, org.bukkit.inventory.ItemStack to) {
        if(from == null || to == null)
            return to;
        try {
            ItemStack s = ((CraftItemStack) from).getHandle();
            if(s.tag == null) {
                s.tag = new NBTTagCompound();
            }
            ((CraftItemStack) to).getHandle().setTag((NBTTagCompound)s.tag.clone());
            return to;
        } catch(ClassCastException ex) {
            return to;
        }

    }

    @Override
    public void setItemMaxSize(Material material, int maxstacksize) {
        SafeField.set(net.minecraft.server.Item.byId[material.getId()], "maxStackSize", maxstacksize);
    }

    @Override
    public org.bukkit.inventory.ItemStack[] getCraftItemstacks(int size, Material mat, int amount, short damage) {
        return getCraftItemstacks(size, new org.bukkit.inventory.ItemStack(mat, amount, damage));
    }

    @Override
    public org.bukkit.inventory.ItemStack getCraftItemstack(Material mat, Integer amount, Short damage) {
        return getCraftItemstack(new org.bukkit.inventory.ItemStack(mat, amount, damage));
    }

    @Override
    public org.bukkit.inventory.ItemStack[] getCraftItemstacks(int size, org.bukkit.inventory.ItemStack stack) {
        org.bukkit.inventory.ItemStack[] stacks = new org.bukkit.inventory.ItemStack[size];
        for(int i = 0; i < size; i++) {
            Object temp = getCraftItemstack(stack);
            if(temp != null)
                stacks[i] = (org.bukkit.inventory.ItemStack)temp;
        }
        return stacks;
    }

    @Override
    public org.bukkit.inventory.ItemStack getCraftItemstack(org.bukkit.inventory.ItemStack stack) {
        return new org.bukkit.craftbukkit.inventory.CraftItemStack(stack);
    }
}
