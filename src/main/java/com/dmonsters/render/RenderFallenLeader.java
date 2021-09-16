package com.dmonsters.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import com.dmonsters.entity.EntityFallenLeader;
import com.dmonsters.main.MainMod;
import com.dmonsters.model.ModelFallenLeader;

public class RenderFallenLeader extends RenderLiving<EntityFallenLeader>
{
    public static final Factory FACTORY = new Factory();
    private final ResourceLocation mobTexture = new ResourceLocation(MainMod.MODID + ":textures/entity/fallen_leader.png");

    public RenderFallenLeader(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn, new ModelFallenLeader(), 0.5F);
    }

    protected void preRenderCallback(EntityFallenLeader entity, float f)
    {
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityFallenLeader entity)
    {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityFallenLeader>
    {
        @Override
        public Render<? super EntityFallenLeader> createRenderFor(RenderManager manager)
        {
            return new RenderFallenLeader(manager);
        }
    }
}