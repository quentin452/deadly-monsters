package com.dmonsters.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import com.dmonsters.entity.EntityBloodyMaiden;
import com.dmonsters.main.MainMod;
import com.dmonsters.models.ModelBloodyMaiden;

public class RenderBloodyMaiden extends RenderLiving<EntityBloodyMaiden>
{
    public static final Factory FACTORY = new Factory();
    private final ResourceLocation mobTexture = new ResourceLocation(MainMod.MODID + ":textures/entity/bloody_maiden.png");
    private final ResourceLocation mobTextureTriggered = new ResourceLocation(MainMod.MODID + ":textures/entity/bloody_maiden_triggered.png");

    public RenderBloodyMaiden(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn, new ModelBloodyMaiden(), 0.5F);
    }

    protected void preRenderCallback(EntityBloodyMaiden entity, float f)
    {
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityBloodyMaiden entity)
    {
        if (entity.getTriggered())
            return mobTextureTriggered;
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityBloodyMaiden>
    {
        @Override
        public Render<? super EntityBloodyMaiden> createRenderFor(RenderManager manager)
        {
            return new RenderBloodyMaiden(manager);
        }
    }
}