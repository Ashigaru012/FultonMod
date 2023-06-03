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
    private boolean post_flag = true;

    SingletonCoordinate coordinate = SingletonCoordinate.getInstance();
    private double post_x = coordinate.getX();
    private double post_y = coordinate.getY();
    private double post_z = coordinate.getZ();
    ResourceLocation location = new ResourceLocation("moonrosemod", "fulton_up");
    SoundEvent event = new SoundEvent(location);


    public EntityFulton(World worldIn) { super(worldIn);}


    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(this.begin_flag){
            this.setNoGravity(true);
            this.setCustomNameTag("fulton");
            this.setVelocity(0,0.01,0);
            this.playSound(event,5.0F,1.0F);
            this.begin_flag = false;
        }
        if(this.timer > 0){
            this.setVelocity(0,0,0);
            --this.timer;
        }
        else if(this.timer <= 0 && post_flag){
            this.posY-=1.2;
            this.setVelocity(0,4,0);
            post_flag=false;
        }
        //yが100越えたら指定の場所に飛ばす
        if(this.posY > 120.0){
            this.setVelocity(0,0,0);
            this.setPosition(this.post_x,100,this.post_z);
//            this.setPosition(-901.306,100,1278.422);

//            this.posY=100.0;
            this.setNoGravity(false);
            this.hurtResistantTime = 10000;
//            this.setVelocity(0,-2,0);
            System.out.println(this.posY);

        }

        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.8D;
        }

//        if (this.posX==this.post_x && this.posZ==this.post_z){
//            this.posY--;
//        }

//        if (!post_flag){
//            this.posY--;
//        }


    }

    @Override
    public boolean isEntityAlive() {
        return super.isEntityAlive();
    }

    public int getTimer(){
        return this.timer;
    }


}
