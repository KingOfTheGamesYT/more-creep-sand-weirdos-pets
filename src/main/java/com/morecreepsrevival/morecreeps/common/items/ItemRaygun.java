package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.entity.EntityRay;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemRaygun extends CreepsItem
{
    public ItemRaygun()
    {
        super("raygun");

        setMaxStackSize(1);

        setMaxDamage(64);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nullable EnumHand hand)
    {
        player.playSound(CreepsSoundHandler.raygunSound, getSoundVolume(), getSoundPitch());

        player.getHeldItem(hand).damageItem(2, player);

        EntityRay ray = new EntityRay(world, player);

        ray.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, 1.7f, 0.0f);

        if (!world.isRemote)
        {
            world.spawnEntity(ray);
        }

        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public boolean isFull3D()
    {
        return true;
    }
}
