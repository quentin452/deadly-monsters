package com.dmonsters.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.EntityClimber;
import com.dmonsters.model.ModelClimber;
import org.lwjgl.opengl.GL11;

public class RenderClimber extends RenderLiving
{
    private final ResourceLocation mobTexture = new ResourceLocation(DeadlyMonsters.MOD_ID + ":textures/entity/climber.png");

    public RenderClimber(ModelBase modelBase, float shadowSize)
    {
        super(modelBase, shadowSize);
    }

  /*  @Override
    protected void preRenderCallback(EntityClimber entity, float f)
    {
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

   */

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return mobTexture;
    }
}
