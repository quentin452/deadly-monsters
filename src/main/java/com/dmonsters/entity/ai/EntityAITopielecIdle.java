package com.dmonsters.entity.ai;

import com.dmonsters.entity.EntityTopielec;
import net.minecraft.util.MathHelper;

public class EntityAITopielecIdle extends DeadlyMonsterAIBase
{
    private final EntityTopielec topielec;
    private int idleTime;

    public EntityAITopielecIdle(EntityTopielec topielec)
    {
        this.topielec = topielec;
    }

    public boolean shouldExecute()
    {
        return true;
    }

    public void updateTask()
    {
        if (this.topielec.onGround) {
            idleTime++;
        } else {
            idleTime = 0;
        }

        if (idleTime > 100)
        {
            this.topielec.setMovementVector(0.0F, 0.0F, 0.0F);
        }
        else if (this.topielec.getRNG().nextInt(50) == 0 || !this.topielec.isInWater() || !this.topielec.hasMovementVector())
        {
            float f = this.topielec.getRNG().nextFloat() * ((float) Math.PI * 2F);
            float f1 = MathHelper.cos(f) * 0.2F;
            float f2 = -0.1F + this.topielec.getRNG().nextFloat() * 0.2F;
            float f3 = MathHelper.sin(f) * 0.2F;
            this.topielec.setMovementVector(f1, f2, f3);
        }
    }
}
