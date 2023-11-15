package com.dmonsters.render;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.EntityUnbornBaby;
import com.dmonsters.model.ModelUnbornBaby;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderUnbornBaby extends RenderLiving
{
    private final ResourceLocation mobTexture = new ResourceLocation(DeadlyMonsters.MOD_ID + ":textures/entity/unborn_baby.png");

    public RenderUnbornBaby(ModelBase modelBase, float shadowSize)
    {
        super(modelBase, shadowSize);
    }

    protected void preRenderCallback(EntityUnbornBaby entity, float f)
    {
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return mobTexture;
    }
}
