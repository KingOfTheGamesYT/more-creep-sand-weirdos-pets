package com.morecreepsrevival.morecreeps.common;

import com.morecreepsrevival.morecreeps.common.entity.*;
import com.morecreepsrevival.morecreeps.common.networking.message.MessageDismountEntity;
import com.morecreepsrevival.morecreeps.proxy.IProxy;
import com.morecreepsrevival.morecreeps.common.capabilities.CreepsCapabilityHandler;
import com.morecreepsrevival.morecreeps.common.networking.message.MessagePlayWelcomeSound;
import com.morecreepsrevival.morecreeps.common.config.MoreCreepsConfig;
import com.morecreepsrevival.morecreeps.common.networking.CreepsPacketHandler;
import com.morecreepsrevival.morecreeps.common.world.WorldGenStructures;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.*;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Mouse;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Mod(modid = MoreCreepsAndWeirdos.modid, name = MoreCreepsAndWeirdos.name, version = MoreCreepsAndWeirdos.version, updateJSON = MoreCreepsAndWeirdos.updateJSON, useMetadata = true) @EventBusSubscriber(modid = MoreCreepsAndWeirdos.modid)
public class MoreCreepsAndWeirdos
{
    public static final String modid = "morecreeps";

    public static final String name = "More Creeps And Weirdos Revival";

    public static final String version = "1.0.12";

    public static final String updateJSON = "https://www.morecreepsrevival.com/update.json";

    @SidedProxy(clientSide = "com.morecreepsrevival.morecreeps.proxy.ClientProxy", serverSide = "com.morecreepsrevival.morecreeps.proxy.ServerProxy")
    public static IProxy proxy;

    @Instance(modid)
    public static MoreCreepsAndWeirdos instance;

    private static int entityId = 0;

    private static final Random rand = new Random();

    private static final String[] welcomeMessages = {
            "Now, go out there and have some fun!",
            "Don't let those stinky Floobs push you around!",
            "Give a diamond to a level 25 HotDog for a special reward!",
            "Urinating Bums can help with landscaping. Try one today!",
            "You're doing something right!",
            "Watch out for grumpy G's!",
            "Guinea Pigs make nice pets.",
            "Bring a lost Kid back to a Lolliman for a nice reward.",
            "Robot Ted thinks Robot Todd is a dirty chicken wing.",
            "Sneaky Sal changes his prices. Check back for bargains.",
            "Power your HotDog with redstone for a fire attack!",
            "You want money? Punch a Lawyer From Hell!",
            "Equip your HotDogs with Redstone for fire attacks!",
            "Guinea Pigs eat Wheat and Apples.",
            "A Floob Ship will spit out Floobs until it is destroyed.",
            "Drop a BubbleScum 100 blocks for the MERCILESS achievement!",
            "Throw a BubbleScum down a DigBug hole for a cookie fountain!",
            "Feed lots of cake to a Hunchback and he will stay loyal.",
            "The longer you ride a RocketPony, the more tame it will be.",
            "Visit Sneaky Sal for those hard to find items.",
            "Hitting a Caveman will turn him/her evil!",
            "SNEAK KEY + RIGHT CLICK on creeps for info or to name them.",
            "Give a level 20 Guinea Pig a diamond to build a Hotel!",
            "If you hear disco music - RUN!",
            "Raising your pets ATTACK skill will help them level faster.",
            "Robot Ted and Todd will sometimes drop dirty chicken wings",
            "Killing a Lawyer may result in a Bum or Undead Lawyers",
            "Shrink a BigBaby down and put him in a jar to create a Schlump",
            "The older your Schlump gets, the more valuable gifts he gives!",
            "Do not throw eggs at Ponies! You have been warned!",
            "Some Prisoners are friendly and will reward you upon release!",
            "Some Prisoners are just evil and will attack you on sight!",
            "Evil Scientists will conduct experiments that sometimes backfire.",
            "Your pet loses a level if resurrected with a LifeGem.",
            "Sneaky Sal will sometimes sell goods at a discount.",
            "Stan BTS!"
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MoreCreepsConfig.preInit(event);

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        CreepsPacketHandler.registerMessages();

        CreepsCapabilityHandler.registerCapabilities();

        GameRegistry.registerWorldGenerator(new WorldGenStructures(), 0);

        proxy.init(event);
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event)
    {
        event.getRegistry().registerAll(
                createEntity(EntityGuineaPig.class, "guineapig", MoreCreepsConfig.guineaPigSpawnAmt, 1, 4, EnumCreatureType.CREATURE, 0xA38447, 0xF7F0E1, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityTombstone.class, "tombstone", 0, 0, 0, EnumCreatureType.AMBIENT),
                createEntity(EntityTrophy.class, "trophy", 0, 0, 0, EnumCreatureType.AMBIENT),
                createEntity(EntityBabyMummy.class, "babymummy", MoreCreepsConfig.babyMummySpawnAmt, 2, 4, EnumCreatureType.MONSTER, 0xDCDEA8, 0xB1F080, getBiomesForType(Type.DRY, Type.SAVANNA, Type.SANDY)),
                createEntity(EntityBlackSoul.class, "blacksoul", MoreCreepsConfig.blackSoulSpawnAmt, 2, 4, EnumCreatureType.MONSTER, 0x335D29, 0x000000, getBiomesForType(Type.DRY, Type.SAVANNA, Type.SANDY, Type.NETHER)),
                createEntity(EntityMummy.class, "mummy", MoreCreepsConfig.mummySpawnAmt, 1, 5, EnumCreatureType.MONSTER, 0xD5C76E, 0x756918, getBiomesForType(Type.DRY, Type.SAVANNA, Type.SANDY)),
                createEntity(EntityGooGoat.class, "googoat", MoreCreepsConfig.gooGoatSpawnAmt, 1, 4, EnumCreatureType.CREATURE, 0x24F50F, 0xC5FFDE, getBiomesForType(Type.FOREST, Type.PLAINS, Type.HILLS, Type.MOUNTAIN, Type.SAVANNA)),
                createEntity(EntityKid.class, "kid", MoreCreepsConfig.kidSpawnAmt, 1, 1, EnumCreatureType.CREATURE, 0xFF5B4D, 0x9E9E9E, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityLolliman.class, "lolliman", MoreCreepsConfig.lollimanSpawnAmt, 1, 1, EnumCreatureType.CREATURE, 0xFF0000, 0x00FFEC, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityPyramidGuardian.class, "pyramid_guardian", 0, 0, 0, EnumCreatureType.CREATURE),
                createEntity(EntityEvilCreature.class, "evilcreature", 0, 1, 1, EnumCreatureType.MONSTER, 0x3BBD62, 0x90702D),
                createEntity(EntityCastleGuard.class, "castleguard", 0, 1, 2, EnumCreatureType.MONSTER, 0xFB91F2, 0xF691FB),
                createEntity(EntityCastleCritter.class, "castlecritter", 0, 1, 2, EnumCreatureType.MONSTER, 0xE32C2C, 0x000000),
                createEntity(EntityCastleKing.class, "castleking", 0, 0, 0, EnumCreatureType.CREATURE),
                createEntity(EntityG.class, "g", MoreCreepsConfig.gSpawnAmt, 1, 1, EnumCreatureType.MONSTER, 0xFF9700, 0x00FF08, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityRobotTed.class, "robot_ted", MoreCreepsConfig.robotTedSpawnAmt, 1, 1, EnumCreatureType.MONSTER, 0x0068FF, 0xA4A4A4, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityRobotTodd.class, "robot_todd", MoreCreepsConfig.robotToddSpawnAmt, 1, 1, EnumCreatureType.MONSTER, 0xA4A4A4, 0xFFC500, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityLawyerFromHell.class, "lawyer_from_hell", MoreCreepsConfig.lawyerFromHellSpawnAmt, 1, 4, EnumCreatureType.MONSTER, 0x7A7D7B, 0x000000, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityMoney.class, "money", 0, 0, 0, EnumCreatureType.AMBIENT),
                createEntity(EntityBigBaby.class, "bigbaby", MoreCreepsConfig.bigBabySpawnAmt, 1, 1, EnumCreatureType.MONSTER, 0xC2B76E, 0xF8A9FF, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityShrink.class, "shrink", 0, 0, 0, EnumCreatureType.AMBIENT),
                createEntity(EntitySchlump.class, "schlump", 0, 0, 0, EnumCreatureType.CREATURE, 0x69572A, 0x000000),
                createEntity(EntityThief.class, "thief", MoreCreepsConfig.thiefSpawnAmt, 1, 1, EnumCreatureType.MONSTER, 0xDC9E22, 0x000000, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityFloob.class, "floob", MoreCreepsConfig.floobSpawnAmt, 1, 1, EnumCreatureType.MONSTER, 0x29FF17, 0xE5E7E4, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityRay.class, "ray", 0, 0, 0, EnumCreatureType.AMBIENT),
                createEntity(EntityFloobShip.class, "floobship", MoreCreepsConfig.floobShipSpawnAmt, 1, 1, EnumCreatureType.MONSTER, 0xF9C41C, 0xEAF72A, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityHorseHead.class, "horsehead", MoreCreepsConfig.horseHeadSpawnAmt, 1, 1, EnumCreatureType.CREATURE, 0xFF07F3, 0x84653A, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityHotdog.class, "hotdog", MoreCreepsConfig.hotdogSpawnAmt, 1, 2, EnumCreatureType.CREATURE, 0x7C5C32, 0x000000, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityDigBug.class, "digbug", MoreCreepsConfig.digBugSpawnAmt, 1, 1, EnumCreatureType.CREATURE, 0x58BA4C, 0xE6DD58, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityBubbleScum.class, "bubblescum", MoreCreepsConfig.bubbleScumSpawnAmt, 1, 1, EnumCreatureType.CREATURE, 0xCE51BE, 0x67E6ED, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntitySneakySal.class, "sneakysal", MoreCreepsConfig.sneakySalSpawnAmt, 1, 1, EnumCreatureType.CREATURE, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityRatMan.class, "ratman", 0, 1, 2, EnumCreatureType.MONSTER),
                createEntity(EntityPrisoner.class, "prisoner", 0, 1, 1, EnumCreatureType.CREATURE),
                createEntity(EntityBullet.class, "bullet", 0, 0, 0, EnumCreatureType.AMBIENT),
                createEntity(EntitySnowDevil.class, "snowdevil", MoreCreepsConfig.snowDevilSpawnAmt, 1, 2, EnumCreatureType.MONSTER, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityEvilChicken.class, "evilchicken", 0, 0, 0, EnumCreatureType.MONSTER),
                createEntity(EntityEvilPig.class, "evilpig", 0, 0, 0, EnumCreatureType.MONSTER),
                createEntity(EntityDogHouse.class, "doghouse", 0, 0, 0, EnumCreatureType.AMBIENT),
                createEntity(EntityBlorp.class, "blorp", MoreCreepsConfig.blorpSpawnAmt, 1, 3, EnumCreatureType.CREATURE, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END)),
                createEntity(EntityCamel.class, "camel", MoreCreepsConfig.camelSpawnAmt, 1, 4, EnumCreatureType.CREATURE, getBiomesNotType(Type.COLD, Type.SNOWY, Type.NETHER, Type.END))
        );
    }

    public static EntityEntry createEntity(Class<? extends Entity> classz, String name, int weight, int min, int max, EnumCreatureType creatureType, int primaryColor, int secondaryColor, Biome... biomes)
    {
        EntityEntryBuilder<?> builder = EntityEntryBuilder.create().entity(classz).name(name).id(new ResourceLocation(modid, name), entityId++).tracker(40, 1, true);

        if (EntityCreepBase.class.isAssignableFrom(classz))
        {
            builder.spawn(creatureType, weight, min, max, biomes);
        }

        if (primaryColor > -1 && secondaryColor > -1)
        {
            builder.egg(primaryColor, secondaryColor);
        }

        return builder.build();
    }

    public static EntityEntry createEntity(Class<? extends Entity> classz, String name, int weight, int min, int max, EnumCreatureType creatureType, int primaryColor, int secondaryColor)
    {
        return createEntity(classz, name, weight, min, max, creatureType, primaryColor, secondaryColor, Biomes.VOID);
    }

    public static EntityEntry createEntity(Class<? extends Entity> classz, String name, int weight, int min, int max, EnumCreatureType creatureType, Biome... biomes)
    {
        return createEntity(classz, name, weight, min, max, creatureType, -1, -1, biomes);
    }

    public static EntityEntry createEntity(Class<? extends Entity> classz, String name, int weight, int min, int max, EnumCreatureType creatureType)
    {
        return createEntity(classz, name, weight, min, max, creatureType, -1, -1, Biomes.VOID);
    }

    public static Biome[] getBiomesForType(Type... types)
    {
        ArrayList<Biome> biomes = new ArrayList<>();

        for (Type type : types)
        {
            biomes.addAll(BiomeDictionary.getBiomes(type));
        }

        int size = biomes.size();

        Biome[] biomesArray = new Biome[size];

        for (int i = 0; i < size; i++)
        {
            biomesArray[i] = biomes.get(i);
        }

        return biomesArray;
    }

    public static Biome[] getBiomesNotType(Type... types)
    {
        ArrayList<Biome> biomes = new ArrayList<>();

        HashSet<Type> typesHash = new HashSet<>(Arrays.asList(types));

        for (Type type : BiomeDictionary.Type.getAll())
        {
            if (!typesHash.contains(type))
            {
                biomes.addAll(BiomeDictionary.getBiomes(type));
            }
        }

        int size = biomes.size();

        Biome[] biomesArray = new Biome[size];

        for (int i = 0; i < size; i++)
        {
            biomesArray[i] = biomes.get(i);
        }

        return biomesArray;
    }

    public static Biome[] getAllBiomes()
    {
        Collection<Biome> biomes = ForgeRegistries.BIOMES.getValuesCollection();

        Biome[] biomesArray = new Biome[biomes.size()];

        int i = 0;

        for (Biome biome : biomes)
        {
            biomesArray[i++] = biome;
        }

        return biomesArray;
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }

    @SubscribeEvent
    public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        if (MoreCreepsConfig.sendVersionInfo)
        {
            event.player.sendMessage(new TextComponentString("\2476" + name + " \247ev" + version + " [BETA] \2476loaded."));
        }

        if (MoreCreepsConfig.sendWelcomeMessage)
        {
            event.player.sendMessage(new TextComponentString(welcomeMessages[rand.nextInt(welcomeMessages.length)]));
        }

        if (MoreCreepsConfig.sendDiscordLink)
        {
            event.player.sendMessage(new TextComponentString("Come join us on Discord! https://discord.gg/r3kdyTy"));
        }

        if (MoreCreepsConfig.playWelcomeSound)
        {
            CreepsPacketHandler.INSTANCE.sendTo(new MessagePlayWelcomeSound(), (EntityPlayerMP)event.player);
        }
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event)
    {
        if (event.side != Side.CLIENT)
        {
            return;
        }

        if (proxy.isJumpKeyDown())
        {
        }
        else
        {
        }
    }

    @SubscribeEvent
    public static void mouseInputEvent(InputEvent.MouseInputEvent event)
    {
        Minecraft minecraft = Minecraft.getMinecraft();

        if (Mouse.getEventButton() == 1)
        {
            for (Entity entity : minecraft.player.getPassengers())
            {
                if (entity instanceof EntityBubbleScum && ((EntityBubbleScum)entity).getUnmountTimer() < 1)
                {
                    entity.dismountRidingEntity();

                    CreepsPacketHandler.INSTANCE.sendToServer(new MessageDismountEntity(entity.getEntityId()));

                    break;
                }
            }
        }
    }

    public static boolean isBlackFriday()
    {
        LocalDate now = LocalDate.now();

        return LocalDate.of(now.getYear(), 11, 1).with(TemporalAdjusters.dayOfWeekInMonth(4, DayOfWeek.THURSDAY)).with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).equals(now);
    }
}