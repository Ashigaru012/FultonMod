package com.moonrose.moonrosemod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;


public class EntityFulton extends EntityLiving
{
    private int duration = 180;
    private int timer = 45;
    private boolean begin_flag = true;
    ResourceLocation location = new ResourceLocation("moonrosemod", "fulton_up");
    SoundEvent event = new SoundEvent(location);

    public EntityFulton(World worldIn) { super(worldIn);}


    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(this.begin_flag){
            this.setVelocity(0,0.01,0);
            this.playSound(event,5.0F,1.0F);
            this.begin_flag = false;
        }
        if(this.timer > 0){
            this.setVelocity(0,0,0);
            --this.timer;
        }
        else{
            this.posY-=1.2;
            this.setVelocity(0,3,0);
        }
    }

    @Override
    public boolean isEntityAlive() {
        return super.isEntityAlive();
    }

    public int getTimer(){
        return this.timer;
    }

}
