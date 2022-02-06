package com.dmonsters.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.EntityUnbornBaby;
import com.dmonsters.model.ModelUnbornBaby;

public class RenderUnbornBaby extends RenderLiving<EntityUnbornBaby>
{
    public static final Factory FACTORY = new Factory();
    private final ResourceLocation mobTexture = new ResourceLocation(DeadlyMonsters.MOD_ID + ":textures/entity/unborn_baby.png");

    public RenderUnbornBaby(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn, new ModelUnbornBaby(), 0.5F);
    }

    protected void preRenderCallback(EntityUnbornBaby entity, float f)
    {
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityUnbornBaby entity)
    {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityUnbornBaby>
    {
        @Override
        public Render<? super EntityUnbornBaby> createRenderFor(RenderManager manager)
        {
            return new RenderUnbornBaby(manager);
        }
    }
}