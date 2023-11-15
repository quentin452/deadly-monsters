package com.dmonsters.render;

import com.dmonsters.model.ModelBloodyMaiden;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.EntityBloodyMaiden;

public class RenderBloodyMaiden extends RenderLiving
{
    private static final ResourceLocation mobTexture = new ResourceLocation(DeadlyMonsters.MOD_ID + ":textures/entity/bloody_maiden.png");
    private static final ResourceLocation mobTextureTriggered = new ResourceLocation(DeadlyMonsters.MOD_ID + ":textures/entity/bloody_maiden_triggered.png");

    public RenderBloodyMaiden(ModelBase p_i1262_1_, float p_i1262_2_) {
        super(p_i1262_1_, p_i1262_2_);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        if (((EntityBloodyMaiden)entity).getTriggered())
            return mobTextureTriggered;
        return mobTexture;
    }

    public static void registerRenderers()
    {
        RenderManager renderManager = RenderManager.instance;
        renderManager.entityRenderMap.put(EntityBloodyMaiden.class, new RenderBloodyMaiden(new ModelBloodyMaiden(), 0.5F));
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    }
}
