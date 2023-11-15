package com.dmonsters.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import com.dmonsters.entity.EntityMutantSteve;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelMutantSteve extends ModelBase
{
    //fields
    ModelRenderer backspine;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer leftarmHammer;

    public ModelMutantSteve()
    {
        textureWidth = 64;
        textureHeight = 64;

        backspine = new ModelRenderer(this, 0, 35);
        backspine.addBox(-1F, 0F, 0F, 2, 7, 1);
        backspine.setRotationPoint(0F, 1F, 2F);
        backspine.setTextureSize(64, 64);
        backspine.mirror = true;
        setRotation(backspine);
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-5F, -8F, -7F, 10, 8, 8);
        head.setRotationPoint(0F, 0F, -1F);
        head.setTextureSize(64, 64);
        head.mirror = true;
        setRotation(head);
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 8, 14, 4);
        body.setRotationPoint(0F, 0F, 0F);
        body.setTextureSize(64, 64);
        body.mirror = true;
        setRotation(body);
        body.addChild(backspine);
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-1F, -2F, -1F, 2, 8, 2);
        rightarm.setRotationPoint(-5F, 2F, 0F);
        rightarm.setTextureSize(64, 64);
        rightarm.mirror = true;
        setRotation(rightarm);
        leftarm = new ModelRenderer(this, 49, 16);
        leftarm.addBox(-1F, -2F, -1F, 2, 6, 2);
        leftarm.setRotationPoint(5F, 2F, 0F);
        leftarm.setTextureSize(64, 64);
        leftarm.mirror = true;
        setRotation(leftarm);
        rightleg = new ModelRenderer(this, 12, 35);
        rightleg.addBox(-1.5F, 0F, -1.5F, 3, 10, 3);
        rightleg.setRotationPoint(-2F, 14F, 0F);
        rightleg.setTextureSize(64, 64);
        rightleg.mirror = true;
        setRotation(rightleg);
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.addBox(-1.5F, 0F, -1.5F, 3, 10, 3);
        leftleg.setRotationPoint(2F, 14F, 0F);
        leftleg.setTextureSize(64, 64);
        leftleg.mirror = true;
        setRotation(leftleg);
        leftarmHammer = new ModelRenderer(this, 39, 0);
        leftarmHammer.addBox(0F, 0F, -2.466667F, 5, 7, 5);
        leftarmHammer.setRotationPoint(-2, 4, 0F);
        leftarmHammer.setTextureSize(64, 64);
        leftarmHammer.mirror = true;
        setRotation(leftarmHammer);
        leftarm.addChild(leftarmHammer);
    }

    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

        if (this.isChild)
        {
            GL11.glPushMatrix();
            this.head.render(scale);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            this.body.render(scale);
            this.rightarm.render(scale);
            this.leftarm.render(scale);
            this.rightleg.render(scale);
            this.leftleg.render(scale);
            this.leftarmHammer.render(scale);
            GL11.glPopMatrix();
        }
        else
        {
            this.head.render(scale);
            this.body.render(scale);
            this.rightarm.render(scale);
            this.leftarm.render(scale);
            this.rightleg.render(scale);
            this.leftleg.render(scale);
        }
    }
    private float swingProgress;

    public void setSwingProgress(float swingProgress) {
        this.swingProgress = swingProgress;
    }
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        boolean flag = entityIn instanceof EntityMutantSteve;
        float f = MathHelper.sin(this.swingProgress * (float) Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);
        this.rightarm.rotateAngleZ = 0.0F;
        this.leftarm.rotateAngleZ = 0.0F;
        this.rightarm.rotateAngleY = -(0.1F - f * 0.6F);
        this.leftarm.rotateAngleY = 0.1F - f * 0.6F;
        float f2 = -(float) Math.PI / (flag ? 1.5F : 2.25F);
        this.rightarm.rotateAngleX = f2;
        this.leftarm.rotateAngleX = f2;
        this.rightarm.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.leftarm.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.rightarm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.leftarm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.rightarm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftarm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

        this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;
    }

    private void setRotation(ModelRenderer model)
    {
        model.rotateAngleX = (float) 0.0;
        model.rotateAngleY = (float) 0.0;
        model.rotateAngleZ = (float) 0.0;
    }
}
