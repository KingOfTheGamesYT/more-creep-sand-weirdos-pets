package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityCastleGuard extends EntityCreepBase
{
    private static final DataParameter<Boolean> attacked = EntityDataManager.createKey(EntityCastleGuard.class, DataSerializers.BOOLEAN);

    private static final String[] textures = {
            "textures/entity/castleguard1",
            "textures/entity/castleguard2",
            "textures/entity/castleguard3",
            "textures/entity/castleguard4",
            "textures/entity/castleguard5"
    };

    public EntityCastleGuard(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Castle Guard");

        baseHealth = 20.0f;

        baseSpeed = 0.35d;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(attacked, false);
    }

    @Override
    public void initEntityAI()
    {
        clearAITasks();

        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        tasks.addTask(1, new EntityAISwimming(this));

        tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, true));

        tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(5, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true, true));
    }

    @Override
    protected float getSoundPitch()
    {
        return ((rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f + (1.0f - getModelSize()) * 2.0f);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (dataManager.get(attacked) && rand.nextInt(5) == 0)
        {
            return CreepsSoundHandler.castleGuardMadSound;
        }
        else if (rand.nextInt(12) == 0)
        {
            return CreepsSoundHandler.castleGuardSound;
        }

        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.castleGuardHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.castleGuardDeathSound;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(3) == 0)
        {
            dropItem(CreepsItemHandler.donut, rand.nextInt(2) + 1);
        }
    }

    @Override
    protected String[] getAvailableTextures()
    {
        return textures;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }

    @Override
    public boolean attackEntityFrom(@Nullable DamageSource damageSource, float amt)
    {
        if (damageSource != null && damageSource.getTrueSource() instanceof EntityPlayer)
        {
            dataManager.set(attacked, true);
        }

        return super.attackEntityFrom(damageSource, amt);
    }
}