package com.dmonsters.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.EntityEntrail;
import com.dmonsters.model.ModelEntrail;

public class RenderEntrail extends RenderLiving<EntityEntrail>
{
    public static final Factory FACTORY = new Factory();
    private final ResourceLocation mobTexture = new ResourceLocation(DeadlyMonsters.MOD_ID + ":textures/entity/entrail.png");

    public RenderEntrail(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn, new ModelEntrail(), 0.5F);
    }

    protected void preRenderCallback(EntityEntrail entity, float f)
    {
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityEntrail entity)
    {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityEntrail>
    {
        @Override
        public Render<? super EntityEntrail> createRenderFor(RenderManager manager)
        {
            return new RenderEntrail(manager);
        }
    }
}