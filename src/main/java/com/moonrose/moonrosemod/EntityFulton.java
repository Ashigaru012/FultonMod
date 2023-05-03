package com.moonrose.moonrosemod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.world.World;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityFulton extends EntityLiving
{
    private int duration = 180;
    private int timer = 140;
    private boolean begin_flag = true;

    public EntityFulton(World worldIn) { super(worldIn);}

    @Override
    public void updateRidden(){
        System.out.println(444444444);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(this.begin_flag){
            this.setVelocity(0,0.1,0);
            this.begin_flag = false;
        }
        if(this.timer > 0){
            this.setVelocity(0,0,0);

            --this.timer;
        }
        else{
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

    //    @Override
//    public void onUpdate() {
////        super.onUpdate();
//        while(this.duration > 0){
//            System.out.println(this.duration);
//            this.duration--;
//        }
//        this.setPosition(0,1,0);
//    }
}
