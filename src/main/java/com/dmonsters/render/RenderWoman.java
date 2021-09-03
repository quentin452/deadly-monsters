package com.dmonsters.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import com.dmonsters.entity.EntityWoman;
import com.dmonsters.main.MainMod;
import com.dmonsters.models.ModelWoman;

public class RenderWoman extends RenderLiving<EntityWoman>
{
    public static final Factory FACTORY = new Factory();
    private final ResourceLocation mobTexture = new ResourceLocation(MainMod.MODID + ":textures/entity/woman.png");
    private final ResourceLocation mobTextureTriggered = new ResourceLocation(MainMod.MODID + ":textures/entity/womanTriggered.png");

    public RenderWoman(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn, new ModelWoman(), 0.5F);
    }

    protected void preRenderCallback(EntityWoman entity, float f)
    {
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityWoman entity)
    {
        if (entity.getTriggered())
            return mobTextureTriggered;
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityWoman>
    {
        @Override
        public Render<? super EntityWoman> createRenderFor(RenderManager manager)
        {
            return new RenderWoman(manager);
        }
    }
}