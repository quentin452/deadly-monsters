package com.dmonsters.render;

import com.dmonsters.DeadlyMonsters;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderZombieChicken extends RenderLiving
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(DeadlyMonsters.MOD_ID + ":textures/entity/zombie_chicken.png");

    public RenderZombieChicken(ModelBase modelBase, float shadowSize) {
        super(modelBase, shadowSize);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f) {
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }

}
