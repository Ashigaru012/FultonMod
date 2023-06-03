package com.moonrose.moonrosemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.Sys;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import org.lwjgl.input.Keyboard;

import java.lang.annotation.*;

import static com.moonrose.moonrosemod.Robby.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Mod(modid = MODID,name = NAME, version = VERSION,acceptedMinecraftVersions = MCVER)
public class Robby
{
    public static final String MODID = "moonrosemod";
    public static final String NAME = "MoonroseMod";
    public static final String VERSION = "1.0";
    public static final String MCVER = "1.12";



    public static Item ruby;
    public static Item fulton;
    public static Item greenapple;
    public static Item idroid;
    public int ticks;
    public boolean moving;
    public boolean opened;

    public KeyBinding[] keyBindings;

    @Mod.Instance
    public static Robby INSTANCE;
    public static final int GUI_ID = 0;



    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        System.out.println("DIRT BLOCK >> "+Blocks.DIRT.getUnlocalizedName());
        EntityRegistry.registerModEntity(new ResourceLocation("Fulton"),EntityFulton.class,"Fulton",0,this,250, 1, true);
        NetworkRegistry.INSTANCE.registerGuiHandler(this.INSTANCE, new IDROIDModGuiHandler());


    }

    @EventHandler
    public void preInt(FMLPreInitializationEvent event)
    {
        ruby = new Item()
                //アイテムの名前
                .setUnlocalizedName("ruby")
                //プレイヤーが持てるアイテムの数の最大数
                .setMaxStackSize(64)
                //クリエイティブタブの設定　今回はバケツに入れる
                .setCreativeTab(CreativeTabs.MISC)
                .setRegistryName(new ResourceLocation(MODID,"ruby"));

        ForgeRegistries.ITEMS.register(ruby);
        ModelLoader.setCustomModelResourceLocation(ruby,0,new ModelResourceLocation(ruby.getRegistryName(),"inventory"));


        fulton = new ItemFulton()
                .setUnlocalizedName("fulton")
                .setMaxStackSize(64)
                .setCreativeTab(CreativeTabs.MISC)
                .setRegistryName(new ResourceLocation(MODID,"fulton"));
        ForgeRegistries.ITEMS.register(fulton);
        ModelLoader.setCustomModelResourceLocation(fulton, 0, new ModelResourceLocation(MODID + ":fulton","inventory"));

        idroid = new ItemiDROID()
                .setUnlocalizedName("idroid")
                .setMaxStackSize(1)
                .setCreativeTab(CreativeTabs.MISC)
                .setRegistryName(new ResourceLocation(MODID,"idroid"));
        ForgeRegistries.ITEMS.register(idroid);
        ModelLoader.setCustomModelResourceLocation(idroid,0,new ModelResourceLocation(MODID + ":idroid","inventory"));


        if(event.getSide().isClient()){
            RenderingRegistry.registerEntityRenderingHandler(EntityFulton.class, new IRenderFactory<EntityFulton>() {
                @Override
                public Render<? super EntityFulton> createRenderFor(RenderManager manager){
                    return new RenderFulton(manager,new ModelFulton(),0.3f);
                }
            });
        }

    }



    class ItemFulton extends Item
    {
        @SideOnly(Side.CLIENT)
        public boolean isFull3D()
        {
            return true;
        }

        public boolean isUsing=false;

        int counter=0;

        private int duration = 5 * 60000;

        public  final ResourceLocation sound = new ResourceLocation("moonrosemod:sounds/fulton_up.ogg");


        public boolean onUpdate(EntityLivingBase entityIn) {
//            entityIn.setVelocity(0,1,0);
            this.duration--;
            return this.duration > 0;
        }

        int pressTime = 0;

        public void onPlayerTick(TickEvent.ClientTickEvent event){

            if(this.pressTime == 20){

                //do one line of code

            }else if(this.pressTime == 40){

                //do another line of code

            }

            pressTime++;

        }
        //共通化用メソッド
        public void fulton_up(EntityPlayer playerIn, Entity target)
        {

            World worldIn = playerIn.world;
                if (target instanceof Entity)
                {
                    if (!worldIn.isRemote) {
                        double motionY = target.motionY + 0.5;
                        EntityFulton entityfulton = new EntityFulton(worldIn);
                        System.out.println(target.getPosition());
                        entityfulton.setPosition(target.posX + 0.5, target.posY + 0.5, target.posZ + 0.5);
                        worldIn.spawnEntity(entityfulton);
                        target.startRiding(entityfulton);
                        target.setEntityInvulnerable(true);
                        target.setCustomNameTag("target");
                    }
                }
        }

        @SubscribeEvent(priority= EventPriority.HIGHEST, receiveCanceled=true)
        public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
        {
            if(playerIn.isSneaking() || !playerIn.isSneaking())
            {
                fulton_up(playerIn,target);
            }
            return true;
        }

        public boolean onLeftClickEntity(ItemStack stack, EntityPlayer playerIn, Entity target)
        {
            if(playerIn.isSneaking())
            {
                fulton_up(playerIn,target);
                return true;
            }
            else {
                return false;
            }
        }
    }

}

