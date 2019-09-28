package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.capabilities.ILawyerFine;
import com.morecreepsrevival.morecreeps.common.capabilities.LawyerFineProvider;
import com.morecreepsrevival.morecreeps.common.config.MoreCreepsConfig;
import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import com.morecreepsrevival.morecreeps.common.world.JailManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityLawyerFromHell extends EntityCreepBase
{
    private static final DataParameter<Boolean> undead = EntityDataManager.createKey(EntityLawyerFromHell.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> lawyerState = EntityDataManager.createKey(EntityLawyerFromHell.class, DataSerializers.VARINT);

    public EntityLawyerFromHell(World world)
    {
        super(world);

        setCreepTypeName("Lawyer From Hell");

        baseHealth = 40.0f;

        baseSpeed = 0.3d;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(undead, false);

        dataManager.register(lawyerState, 0);
    }

    @Override
    protected void initEntityAI()
    {
        clearAITasks();

        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        nodeProcessor.setCanEnterDoors(true);

        tasks.addTask(1, new EntityAISwimming(this));

        tasks.addTask(2, new EntityAIBreakDoor(this));

        tasks.addTask(3, new EntityAIAttackMelee(this, 1.0d, true));

        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(6, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

        if (getLawyerState() > 0)
        {
            targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true, true));
        }
    }

    @Override
    protected void updateTexture()
    {
        if (getUndead())
        {
            setTexture("textures/entity/lawyerfromhellundead.png");

            return;
        }

        setTexture("textures/entity/lawyerfromhell.png");
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (getUndead())
        {
            setLawyerState(1);
        }
        else
        {
            EntityLivingBase entity = getRevengeTarget();

            if (entity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer)entity;

                ILawyerFine capability = player.getCapability(LawyerFineProvider.capability, null);

                if (capability != null)
                {
                    int fine = capability.getFine();

                    if (fine >= 2500)
                    {
                        setLawyerState(5);
                    }
                    else if (fine > 0)
                    {
                        setLawyerState(1);
                    }
                }
            }
        }
    }

    public void setUndead(boolean b)
    {
        if (getUndead() == b)
        {
            return;
        }

        dataManager.set(undead, b);

        if (b)
        {
            setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BONE));
        }
        else
        {
            setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
        }

        updateAttributes();
    }

    public boolean getUndead()
    {
        return dataManager.get(undead);
    }

    protected void setLawyerState(int i)
    {
        if (getLawyerState() == i)
        {
            return;
        }

        dataManager.set(lawyerState, i);

        initEntityAI();
    }

    protected int getLawyerState()
    {
        return dataManager.get(lawyerState);
    }

    @Override
    public boolean isEntityInsideOpaqueBlock()
    {
        if (getUndead() && !isNotColliding())
        {
            return false;
        }

        return super.isEntityInsideOpaqueBlock();
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (getUndead())
        {
            return CreepsSoundHandler.undeadLawyerSound;
        }

        return CreepsSoundHandler.lawyerSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        if (getUndead())
        {
            return CreepsSoundHandler.undeadLawyerHurtSound;
        }

        return CreepsSoundHandler.lawyerHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        if (getUndead())
        {
            return CreepsSoundHandler.undeadLawyerDeathSound;
        }

        return CreepsSoundHandler.lawyerDeathSound;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }

    @Override
    protected double getMoveSpeed()
    {
        if (getUndead())
        {
            return 0.24d;
        }

        return super.getMoveSpeed();
    }

    @Override
    protected double getAttackDamage()
    {
        if (getUndead())
        {
            return 2.0d;
        }

        return super.getAttackDamage();
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        if (entity instanceof EntityPlayer)
        {
            ILawyerFine capability = entity.getCapability(LawyerFineProvider.capability, null);

            if (capability != null)
            {
                EntityPlayer player = (EntityPlayer)entity;

                int fine = capability.getFine();

                if (fine < 1 && !getUndead())
                {
                    setAttackTarget(null);

                    setRevengeTarget(null);

                    return false;
                }
                else if (rand.nextInt(50) == 0)
                {
                    suckMoney(player);
                }

                if (getLawyerState() == 5 && !getUndead() && MoreCreepsConfig.jailActive && rand.nextInt(10) == 0 && fine >= 2500)
                {
                    capability.setFine(0);

                    if (!world.isRemote)
                    {
                        JailManager.buildJail(player, this, world, rand);
                    }
                }
            }
        }

        return super.attackEntityAsMob(entity);
    }

    @Override
    public boolean attackEntityFrom(@Nullable DamageSource damageSource, float amt)
    {
        if (!getUndead())
        {
            EntityPlayer playerTarget = null;

            Entity entity = damageSource.getTrueSource();

            if (entity instanceof EntityPlayer)
            {
                playerTarget = (EntityPlayer)entity;

                if (rand.nextInt(5) == 0)
                {
                    int randInt = rand.nextInt(20) + 5;

                    for (int i = 0; i < randInt; i++)
                    {
                        ILawyerFine capability = playerTarget.getCapability(LawyerFineProvider.capability, null);

                        if (capability != null)
                        {
                            capability.addFine(25);
                        }

                        playSound(CreepsSoundHandler.lawyerMoneyHitSound, getSoundVolume(), getSoundPitch());

                        EntityMoney money = new EntityMoney(world, posX, posY, posZ);

                        money.setDefaultPickupDelay();

                        money.setNoDespawn();

                        if (!world.isRemote)
                        {
                            world.spawnEntity(money);
                        }
                    }
                }

                if (rand.nextInt(5) == 0)
                {
                    int randInt = rand.nextInt(3) + 1;

                    for (int i = 0; i < randInt; i++)
                    {
                        ILawyerFine capability = playerTarget.getCapability(LawyerFineProvider.capability, null);

                        if (capability != null)
                        {
                            capability.addFine(10);
                        }

                        playSound(CreepsSoundHandler.lawyerMoneyHitSound, getSoundVolume(), getSoundPitch());

                        if (!world.isRemote)
                        {
                            dropItem(Items.PAPER, 1);
                        }
                    }
                }
            }
            else if (entity instanceof EntityCreepBase)
            {
                EntityCreepBase creep = (EntityCreepBase)entity;

                if (creep.isTamed())
                {
                    playerTarget = creep.getOwner();
                }
            }

            if (playerTarget != null)
            {
                ILawyerFine capability = playerTarget.getCapability(LawyerFineProvider.capability, null);

                if (capability != null)
                {
                    capability.addFine(50);
                }

                if (getLawyerState() == 0)
                {
                    setLawyerState(1);
                }

                setRevengeTarget(playerTarget);
            }
        }

        return super.attackEntityFrom(damageSource, amt);
    }

    private void suckMoney(EntityPlayer player)
    {
        int invSize = player.inventory.mainInventory.size();

        boolean take = false;

        boolean isUndead = getUndead();

        for (int i = 0; i < invSize; i++)
        {
            ItemStack itemStack = player.inventory.mainInventory.get(i);

            if (itemStack.getItem() == CreepsItemHandler.money)
            {
                if (!isUndead)
                {
                    playSound(CreepsSoundHandler.lawyerSuckSound, getSoundVolume(), getSoundPitch());
                }

                int stackSize = itemStack.getCount();

                itemStack.shrink(Math.min(stackSize, rand.nextInt(stackSize) + 1));

                take = true;
            }
        }

        if (take && !isUndead)
        {
            playSound(CreepsSoundHandler.lawyerTakeSound, getSoundVolume(), getSoundPitch());
        }
    }

    @Override
    public void onDeath(@Nullable DamageSource cause)
    {
        if (!getUndead())
        {
            Entity entity = cause.getTrueSource();

            if (rand.nextInt(3) == 0 && entity instanceof EntityPlayer)
            {
                int randAmt = rand.nextInt(4) + 3;

                for (int i = 0; i < randAmt; i++)
                {
                    smoke2();

                    if (!world.isRemote)
                    {
                        EntityLawyerFromHell lawyer = new EntityLawyerFromHell(world);

                        lawyer.setLocationAndAngles(entity.posX + (double)(rand.nextInt(4) - rand.nextInt(4)), entity.posY - 1.5d, entity.posZ + (double)(rand.nextInt(4) - rand.nextInt(4)), rotationYaw, 0.0f);

                        lawyer.setUndead(true);

                        lawyer.determineBaseTexture();

                        lawyer.setInitialHealth();

                        world.spawnEntity(lawyer);
                    }
                }
            }
            else if (rand.nextInt(5) == 0)
            {
                // TODO: spawn bum
            }
            else if (rand.nextInt(10) == 0)
            {
                int randInt = rand.nextInt(40) + 10;

                for (int i = 0; i < randInt; i++)
                {
                    dropItem(CreepsItemHandler.money, 1);
                }
            }
        }

        smoke2();

        super.onDeath(cause);
    }
}