package com.dmonsters.render;

import javax.annotation.Nonnull;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.EntityEntrail;
import com.dmonsters.model.ModelEntrail;

public class RenderEntrail extends RenderLiving
{
    private final ResourceLocation mobTexture = new ResourceLocation(DeadlyMonsters.MOD_ID + ":textures/entity/entrail.png");

    public RenderEntrail(ModelBase modelBase, float shadowSize)
    {
        super(modelBase, shadowSize);
    }

    protected void preRenderCallback(EntityEntrail entity, float f)
    {
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return mobTexture;
    }
}
