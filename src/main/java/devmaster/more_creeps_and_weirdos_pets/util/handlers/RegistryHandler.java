package devmaster.more_creeps_and_weirdos_pets.util.handlers;

import devmaster.more_creeps_and_weirdos_pets.init.ModRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ModRegistry.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : ModRegistry.ITEMS)
		{
			devmaster.more_creeps_and_weirdos_pets.MoreCreepsAndWeirdosPets.proxy.registerItemRenderer(item, 0, "inventory");
		}
			}



	

	public static void initRegistries() {
	}
	
	public static void serverRegistries(FMLServerStartingEvent event)
	{
	}
}
