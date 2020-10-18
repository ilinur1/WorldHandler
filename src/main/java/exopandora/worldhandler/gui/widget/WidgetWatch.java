package exopandora.worldhandler.gui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;

import exopandora.worldhandler.config.Config;
import exopandora.worldhandler.gui.container.Container;
import exopandora.worldhandler.util.RenderUtils;
import exopandora.worldhandler.util.TextUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WidgetWatch implements IContainerWidget
{
	@Override
	public void drawScreen(MatrixStack matrix, Container container, int x, int y, int mouseX, int mouseY, float partialTicks)
	{
		final int watchX = container.getBackgroundX() + 233;
		final int watchY = container.getBackgroundY() + 5;
		
		long time = 0;
		
		if(Minecraft.getInstance().world != null)
		{
			time = Minecraft.getInstance().world.getWorldInfo().getDayTime();
		}
		
		RenderUtils.drawWatchIntoGui(matrix, container, watchX, watchY, time, Config.getSettings().smoothWatch());
		
		if(Config.getSettings().tooltips() && mouseX >= watchX && mouseX <= watchX + 9 && mouseY >= watchY && mouseY <= watchY + 9)
		{
			container.renderTooltip(matrix, new StringTextComponent(TextUtils.formatWorldTime(time)), mouseX, mouseY + 9);
		}
	}
	
	@Override
	public boolean isEnabled()
	{
		return Config.getSettings().watch();
	}
	
	@Override
	public EnumLayer getLayer()
	{
		return EnumLayer.BACKGROUND;
	}
}
