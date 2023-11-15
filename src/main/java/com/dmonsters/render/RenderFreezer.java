package com.dmonsters.render;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.EntityFreezer;
import com.dmonsters.model.ModelFreezer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderFreezer extends RenderLiving
{
    private final ResourceLocation mobTexture = new ResourceLocation(DeadlyMonsters.MOD_ID + ":textures/entity/freezer_idle.png");
    private final ResourceLocation mobTextureAttaking = new ResourceLocation(DeadlyMonsters.MOD_ID + ":textures/entity/freezer_angry.png");

    public RenderFreezer(ModelBase modelBase, float shadowSize)
    {
        super(modelBase, shadowSize);
    }

    protected void preRenderCallback(EntityFreezer entity, float f)
    {
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {

        EntityFreezer freezer = (EntityFreezer)entity;

        if(freezer.isAttacking) {
            return mobTextureAttaking;
        }

        return mobTexture;

    }
}
