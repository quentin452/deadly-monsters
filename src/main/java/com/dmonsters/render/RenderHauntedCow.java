package com.dmonsters.render;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.EntityHauntedCow;
import com.dmonsters.model.ModelHauntedCow;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderHauntedCow extends RenderLiving
{
    private final ResourceLocation mobTexture = new ResourceLocation(DeadlyMonsters.MOD_ID + ":textures/entity/haunted_cow.png");

    public RenderHauntedCow(ModelBase modelBase, float shadowSize)
    {
        super(modelBase, shadowSize);
    }

    protected void preRenderCallback(EntityHauntedCow entity, float f)
    {
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture( Entity entity)
    {
        return mobTexture;
    }
}
