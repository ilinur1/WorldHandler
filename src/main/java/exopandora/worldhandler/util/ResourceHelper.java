package exopandora.worldhandler.util;

import javax.annotation.Nullable;

import exopandora.worldhandler.Main;
import exopandora.worldhandler.config.Config;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class ResourceHelper
{
	private static final ResourceLocation BACKGROUND = new ResourceLocation("textures/gui/demo_background.png");
	private static final ResourceLocation BACKGROUND_VANILLA = new ResourceLocation(Main.MODID, "textures/skins/vanilla/vanilla.png");
	private static final ResourceLocation BUTTON = new ResourceLocation("textures/gui/widgets.png");
	
	@Nullable
	public static ResourceLocation stringToResourceLocation(@Nullable String resource)
	{
		if(resource != null && !resource.isEmpty())
		{
			return ResourceLocation.tryParse(resource.toLowerCase().replaceAll("[^a-z0-9/._\\-:]", "_"));
		}
		
		return null;
	}
	
	public static boolean isRegistered(ResourceLocation resource, IForgeRegistry<?> registry)
	{
		return resource != null && registry != null && registry.containsKey(resource);
	}
	
	@Nullable
	public static ResourceLocation assertRegistered(ResourceLocation resource, IForgeRegistry<?> registry)
	{
		if(resource != null && ResourceHelper.isRegistered(resource, registry))
		{
			return resource;
		}
		
		return null;
	}
	
	public static ResourceLocation backgroundTexture()
	{
		if(Config.getSkin().getTextureType().equals("resourcepack"))
		{
			return BACKGROUND;
		}
		
		return BACKGROUND_VANILLA;
	}
	
	public static ResourceLocation iconTexture()
	{
		return new ResourceLocation(Main.MODID, "textures/icons/icons_" + Config.getSkin().getIconSize().name() + ".png");
	}
	
	public static ResourceLocation buttonTexture()
	{
		if(Config.getSkin().getTextureType().equals("resourcepack"))
	    {
			return BUTTON;
	    }
		
		return new ResourceLocation(Main.MODID, "textures/skins/" + Config.getSkin().getTextureType() + "/" + Config.getSkin().getTextureType() + "_buttons.png");
	}
}
