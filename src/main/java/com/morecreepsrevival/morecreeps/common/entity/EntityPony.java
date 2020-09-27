package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityPony extends EntityCreepBase
{
    private static final String[] textures = {
            "textures/entity/pony01",
            "textures/entity/pony02",
            "textures/entity/pony03",
            "textures/entity/pony04",
            "textures/entity/pony05",
            "textures/entity/pony06",
            "textures/entity/pony07",
            "textures/entity/pony08",
            "textures/entity/pony09"
    };

    private static final String[] names = {
            "Chester", "Tugbert the Terrible", "Edward", "Prancer", "Paul", "Ralph", "Captain Sparkles", "Little Mo", "Percy", "Percival the Brave", "Sammy", "Thunderhoof", "Thunderbolt", "Bolt", "Benji", "Rasberry Ron", "Peter Sprinkles", "Captain Rainbow", "Chuckles", "Trigger", "Petuna", "Matilda", "Molly the Magnificent", "Betty", "Tom", "Caronline", "Hillary Hoof", "Paula", "Chaz", "Twinkletoes", "The Fortune Hunter", "Carl C Cluxton", "George", "Betty the Beast", "Nancy Neigh", "Susan Swift", "Claire De Lune", "L.A. Sizzle", "Bunwarmer", "Dirty Dutchess", "Pilar", "Gusty Dreams", "Guts and Glory", "Wiggler", "Shakin' Bacon", "Mr. Maniac", "Little Hoof"
    };

    private static final int[] levelDamages = {
            0, 50, 100, 250, 500, 800, 1200, 1700, 2200, 2700, 3300, 3900, 4700, 5400, 6200, 7000, 7900, 8800, 9750, 10750, 12500, 17500, 22500, 30000, 40000, 50000, 60000, 75000, 90000, 105000, 120000, 140000, 160000, 180000, 200000, 225000, 250000, 275000, 300000, 325000, 350000, 375000, 400000, 450000, 500000, 550000, 600000, 650000, 700000, 800000, 900000, 1000000
    };

    public EntityPony(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Pony");

        baseHealth = 6.0f;

        baseSpeed = 0.2d;

        updateAttributes();
    }

    @Override
    protected String[] getAvailableTextures()
    {
        return textures;
    }

    @Override
    protected String[] getTamedNames()
    {
        return names;
    }

    @Override
    public int getLevelDamage()
    {
        return levelDamages[getLevel()];
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        // TODO: figure this one out

        return null;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource)
    {
        return CreepsSoundHandler.ponySound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.ponyDeathSound;
    }

    @Override
    public double getYOffset()
    {
        if (getRidingEntity() instanceof EntityPonyCloud)
        {
            return getRidingYOffset() + 5.0d;
        }

        return super.getYOffset();
    }
}
