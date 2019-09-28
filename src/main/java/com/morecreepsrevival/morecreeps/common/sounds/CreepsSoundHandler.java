package com.morecreepsrevival.morecreeps.common.sounds;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = MoreCreepsAndWeirdos.modid)
public class CreepsSoundHandler
{
    public static final SoundEvent achievementSound = createSound("achievement");

    public static final SoundEvent welcomeSound = createSound("welcome");

    public static final SoundEvent tombstoneSound = createSound("tombstone");

    public static final SoundEvent mummyHurtSound = createSound("mummyhurt");

    public static final SoundEvent mummySound = createSound("mummy");

    public static final SoundEvent mummyDeathSound = createSound("mummydeath");

    public static final SoundEvent lickSound = createSound("lick");

    public static final SoundEvent blorpColaSound = createSound("blorpcola");

    public static final SoundEvent guineaPigAngrySound = createSound("ggpigangry");

    public static final SoundEvent guineaPigCriticalHitSound = createSound("guineapigcriticalhit");

    public static final SoundEvent guineaPigLevelUpSound = createSound("ggpiglevelup");

    public static final SoundEvent guineaPigSpeedDownSound = createSound("guineapigspeeddown");

    public static final SoundEvent guineaPigMountSound = createSound("ggpigmount");

    public static final SoundEvent guineaPigUnmountSound = createSound("ggpigunmount");

    public static final SoundEvent guineaPigSpeedUpSound = createSound("guineapigspeedup");

    public static final SoundEvent guineaPigArmorSound = createSound("ggpigarmor");

    public static final SoundEvent guineaPigEatSound = createSound("ggpigeat");

    public static final SoundEvent guineaPigFullSound = createSound("ggpigfull");

    public static final SoundEvent guineaPigHotelSound = createSound("guineapighotel");

    public static final SoundEvent guineaPigDeathSound = createSound("ggpigdeath");

    public static final SoundEvent guineaPigSound = createSound("ggpig");

    public static final SoundEvent guineaPigTrainSound = createSound("guineapigtrain");

    public static final SoundEvent guineaPigNoWheatSound = createSound("guineapignowheat");

    public static final SoundEvent guineaPig5LevelSound = createSound("guineapig5level");

    public static final SoundEvent guineaPig10LevelSound = createSound("guineapig10level");

    public static final SoundEvent guineaPig15LevelSound = createSound("guineapig15level");

    public static final SoundEvent guineaPig20LevelSound = createSound("guineapig20level");

    public static final SoundEvent gooGoatSound = createSound("googoat");

    public static final SoundEvent gooGoatHurtSound = createSound("googoathurt");

    public static final SoundEvent gooGoatDeathSound = createSound("googoatdead");

    public static final SoundEvent gooGoatStretchSound = createSound("googoatstretch");

    public static final SoundEvent guineaPigRadioSound = createSound("ggpigradio");

    public static final SoundEvent bandaidSound = createSound("bandaid");

    public static final SoundEvent blackSoulSound = createSound("blacksoul");

    public static final SoundEvent blackSoulHurtSound = createSound("blacksoulhurt");

    public static final SoundEvent blackSoulDeathSound = createSound("blacksouldeath");

    public static final SoundEvent babyMummySound = createSound("babymummy");

    public static final SoundEvent babyMummyDeathSound = createSound("babymummydeath");

    public static final SoundEvent babyMummyHurtSound = createSound("babymummyhurt");

    public static final SoundEvent kidSound = createSound("kid");

    public static final SoundEvent kidRideSound = createSound("kidride");

    public static final SoundEvent kidColdSound = createSound("kidcold");

    public static final SoundEvent kidHurtSound = createSound("kidhurt");

    public static final SoundEvent kidDeathSound = createSound("kiddeath");

    public static final SoundEvent kidMountSound = createSound("kidup");

    public static final SoundEvent kidUnmountSound = createSound("kiddown");

    public static final SoundEvent kidNoPickupSound = createSound("kidnontpickup");

    public static final SoundEvent lollimanSound = createSound("lolliman");

    public static final SoundEvent lollimanHurtSound = createSound("lollimanhurt");

    public static final SoundEvent lollimanDeathSound = createSound("lollimandeath");

    public static final SoundEvent lollimanTakeOffSound = createSound("lollimantakeoff");

    public static final SoundEvent lollimanExplodeSound = createSound("lollimanexplode");

    public static final SoundEvent lollySound = createSound("lolly");

    public static final SoundEvent kidFindSound = createSound("kidfind");

    public static final SoundEvent pyramidDiscoveredSound = createSound("pyramiddiscovered");

    public static final SoundEvent battleCastleSound = createSound("battlecastle");

    public static final SoundEvent pyramidCurseSound = createSound("pyramidcurse");

    public static final SoundEvent pyramidSound = createSound("pyramid");

    public static final SoundEvent pyramidHurtSound = createSound("pyramidhurt");

    public static final SoundEvent pyramidDeathSound = createSound("pyramiddeath");

    public static final SoundEvent randomBowSound = createMinecraftSound("random.bow");

    public static final SoundEvent raygunSound = createSound("raygun");

    public static final SoundEvent evilEggCluckSound = createSound("evileggcluck");

    public static final SoundEvent evilCreatureSound = createSound("evilcreature");

    public static final SoundEvent evilCreatureHurtSound = createSound("evilcreaturehurt");

    public static final SoundEvent evilCreatureDeathSound = createSound("evilcreaturedeath");

    public static final SoundEvent evilCreatureJumpSound = createSound("evilcreaturejump");

    public static final SoundEvent trophySmashSound = createSound("trophysmash");

    public static final SoundEvent earthGemSound = createSound("earthgem");

    public static final SoundEvent miningGemSound = createSound("mininggem");

    public static final SoundEvent miningGemBadSound = createSound("mininggembad");

    public static final SoundEvent skyGemUpSound = createSound("skygemup");

    public static final SoundEvent skyGemDownSound = createSound("skygemdown");

    public static final SoundEvent healingGemSound = createSound("healinggem");

    public static final SoundEvent castleCritterSound = createSound("castlecritter");

    public static final SoundEvent castleCritterHurtSound = createSound("castlecritterhurt");

    public static final SoundEvent castleCritterDeathSound = createSound("castlecritterdeath");

    public static final SoundEvent castleGuardSound = createSound("castleguard");

    public static final SoundEvent castleGuardHurtSound = createSound("castleguardhurt");

    public static final SoundEvent castleGuardDeathSound = createSound("castleguarddeath");

    public static final SoundEvent castleGuardMadSound = createSound("castleguardmad");

    public static final SoundEvent castleKingSound = createSound("castleking");

    public static final SoundEvent castleKingHurtSound = createSound("castlekinghurt");

    public static final SoundEvent castleKingDeathSound = createSound("castlekingdeath");

    public static final SoundEvent fireGemSound = createSound("firegem");

    public static final SoundEvent chewSound = createSound("chew");

    public static final SoundEvent gemSwordSound = createSound("gemsword");

    public static final SoundEvent shrinkRaySound = createSound("shrinkray");

    public static final SoundEvent horseHeadGemSound = createSound("horseheadgem");

    public static final SoundEvent gSound = createSound("g");

    public static final SoundEvent gHurtSound = createSound("ghurt");

    public static final SoundEvent gDeathSound = createSound("gdeath");

    public static final SoundEvent tedInsultSound = createSound("tedinsult");

    public static final SoundEvent robotHurtSound = createSound("robothurt");

    public static final SoundEvent tedDeadSound = createSound("teddead");

    public static final SoundEvent toddInsultSound = createSound("toddinsult");

    public static final SoundEvent toddDeadSound = createSound("todddead");

    public static final SoundEvent lawyerSound = createSound("lawyer");

    public static final SoundEvent undeadLawyerSound = createSound("lawyerundead");

    public static final SoundEvent lawyerHurtSound = createSound("lawyerhurt");

    public static final SoundEvent undeadLawyerHurtSound = createSound("lawyerundeadhurt");

    public static final SoundEvent lawyerDeathSound = createSound("lawyerdeath");

    public static final SoundEvent undeadLawyerDeathSound = createSound("lawyerundeaddeath");

    public static final SoundEvent lawyerMoneyHitSound = createSound("lawyermoneyhit");

    public static final SoundEvent lawyerBustedSound = createSound("lawyerbusted");

    public static final SoundEvent lawyerSuckSound = createSound("lawyersuck");

    public static final SoundEvent lawyerTakeSound = createSound("lawyertake");

    public static final SoundEvent lawyerBumSound = createSound("lawyerbum");

    public static final SoundEvent bigBabySound = createSound("bigbaby");

    public static final SoundEvent bigBabyHurtSound = createSound("bigbabyhurt");

    public static final SoundEvent babyTakeHomeSound = createSound("babytakehome");

    public static final SoundEvent babyShrinkSound = createSound("babyshrink");

    public static final SoundEvent shrinkKillSound = createSound("shrinkkill");

    public static final SoundEvent schlumpSound = createSound("schlump");

    public static final SoundEvent schlumpHurtSound = createSound("schlumphurt");

    public static final SoundEvent schlumpDeathSound = createSound("schlumpdeath");

    public static final SoundEvent schlumpOverloadSound = createSound("schlump-overload");

    public static final SoundEvent schlumpIndoorsSound = createSound("schlump-indoors");

    public static final SoundEvent schlumpBrightSound = createSound("schlump-bright");

    public static final SoundEvent schlumpRoomSound = createSound("schlump-room");

    public static final SoundEvent schlumpBigSound = createSound("schlump-big");

    public static final SoundEvent schlumpSucksSound = createSound("schlump-sucks");

    public static final SoundEvent schlumpRewardSound = createSound("schlump-reward");

    public static final SoundEvent schlumpOkSound = createSound("schlump-ok");

    public static final SoundEvent barfSound = createSound("barf");

    public static final SoundEvent bulletSound = createSound("bullet");

    public static final SoundEvent extinguisherSound = createSound("extinguisher");

    public static final SoundEvent thiefSound = createSound("thief");

    public static final SoundEvent thiefHurtSound = createSound("thiefhurt");

    public static final SoundEvent thiefDeathSound = createSound("thiefdeath");

    public static final SoundEvent thiefFindPlayerSound = createSound("thieffindplayer");

    public static final SoundEvent thiefStealSound = createSound("thiefsteal");

    public static final SoundEvent floobSound = createSound("floob");

    public static final SoundEvent floobHurtSound = createSound("floobhurt");

    public static final SoundEvent floobDeathSound = createSound("floobdeath");

    public static final SoundEvent floobShipSound = createSound("floobship");

    public static final SoundEvent floobShipDeathSound = createSound("floobshipexplode");

    public static final SoundEvent floobShipSpawnSound = createSound("floobshipspawn");

    public static SoundEvent createSound(String soundName)
    {
        return (new SoundEvent(new ResourceLocation(MoreCreepsAndWeirdos.modid, soundName))).setRegistryName(soundName);
    }

    public static SoundEvent createMinecraftSound(String soundName)
    {
        return (new SoundEvent(new ResourceLocation("minecraft", soundName))).setRegistryName(soundName);
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event)
    {
        event.getRegistry().registerAll(
                achievementSound,
                welcomeSound,
                tombstoneSound,
                mummyHurtSound,
                mummySound,
                mummyDeathSound,
                lickSound,
                blorpColaSound,
                guineaPigAngrySound,
                guineaPigCriticalHitSound,
                guineaPigLevelUpSound,
                guineaPigSpeedDownSound,
                guineaPigMountSound,
                guineaPigUnmountSound,
                guineaPigSpeedUpSound,
                guineaPigArmorSound,
                guineaPigEatSound,
                guineaPigFullSound,
                guineaPigHotelSound,
                guineaPigDeathSound,
                guineaPigSound,
                guineaPigTrainSound,
                guineaPigNoWheatSound,
                guineaPig5LevelSound,
                guineaPig10LevelSound,
                guineaPig15LevelSound,
                guineaPig20LevelSound,
                gooGoatSound,
                gooGoatHurtSound,
                gooGoatDeathSound,
                gooGoatStretchSound,
                guineaPigRadioSound,
                bandaidSound,
                blackSoulSound,
                blackSoulHurtSound,
                blackSoulDeathSound,
                babyMummySound,
                babyMummyDeathSound,
                babyMummyHurtSound,
                kidSound,
                kidRideSound,
                kidColdSound,
                kidHurtSound,
                kidDeathSound,
                kidMountSound,
                kidUnmountSound,
                kidNoPickupSound,
                lollimanSound,
                lollimanHurtSound,
                lollimanDeathSound,
                lollimanTakeOffSound,
                lollimanExplodeSound,
                kidFindSound,
                pyramidDiscoveredSound,
                battleCastleSound,
                pyramidCurseSound,
                pyramidSound,
                pyramidHurtSound,
                pyramidDeathSound,
                raygunSound,
                evilEggCluckSound,
                evilCreatureSound,
                evilCreatureHurtSound,
                evilCreatureDeathSound,
                evilCreatureJumpSound,
                trophySmashSound,
                earthGemSound,
                miningGemSound,
                miningGemBadSound,
                skyGemUpSound,
                skyGemDownSound,
                castleCritterSound,
                castleCritterHurtSound,
                castleCritterDeathSound,
                castleGuardSound,
                castleGuardHurtSound,
                castleGuardDeathSound,
                castleGuardMadSound,
                castleKingSound,
                castleKingHurtSound,
                castleKingDeathSound,
                fireGemSound,
                chewSound,
                gemSwordSound,
                shrinkRaySound,
                horseHeadGemSound,
                gSound,
                gHurtSound,
                gDeathSound,
                tedInsultSound,
                robotHurtSound,
                tedDeadSound,
                toddInsultSound,
                toddDeadSound,
                lawyerSound,
                undeadLawyerSound,
                lawyerHurtSound,
                undeadLawyerHurtSound,
                lawyerDeathSound,
                undeadLawyerDeathSound,
                lawyerMoneyHitSound,
                lawyerBustedSound,
                lawyerSuckSound,
                lawyerTakeSound,
                lawyerBumSound,
                bigBabySound,
                bigBabyHurtSound,
                babyTakeHomeSound,
                babyShrinkSound,
                shrinkKillSound,
                schlumpSound,
                schlumpHurtSound,
                schlumpDeathSound,
                schlumpOverloadSound,
                schlumpIndoorsSound,
                schlumpBrightSound,
                schlumpRoomSound,
                schlumpBigSound,
                schlumpSucksSound,
                schlumpRewardSound,
                schlumpOkSound,
                barfSound,
                bulletSound,
                extinguisherSound,
                thiefSound,
                thiefHurtSound,
                thiefDeathSound,
                thiefFindPlayerSound,
                thiefStealSound,
                floobSound,
                floobHurtSound,
                floobDeathSound,
                floobShipSound,
                floobShipDeathSound,
                floobShipSpawnSound
        );
    }
}