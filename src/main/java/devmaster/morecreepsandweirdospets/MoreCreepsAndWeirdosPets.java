package devmaster.morecreepsandweirdospets;

import devmaster.morecreepsandweirdospets.proxy.CommonProxy;
import devmaster.morecreepsandweirdospets.tabs.more_creeps_and_weirdos_pets;
import devmaster.morecreepsandweirdospets.util.Reference;
import devmaster.morecreepsandweirdospets.util.handlers.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class MoreCreepsAndWeirdosPets {
	


	@Instance
	public static devmaster.morecreepsandweirdospets.MoreCreepsAndWeirdosPets instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	public static final CreativeTabs TAB = new more_creeps_and_weirdos_pets("dangerzoneitems");

	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		RegistryHandler.initRegistries();
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{

	}
	
	@EventHandler
	public static void serverInit(FMLServerStartingEvent event)
	{
		RegistryHandler.serverRegistries(event);
	}
	
}