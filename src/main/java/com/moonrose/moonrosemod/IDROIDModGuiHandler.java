package com.moonrose.moonrosemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class IDROIDModGuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Robby.GUI_ID) {
            return new IDROIDContainer(x, y, z);
        }
        return null;
    }

    /*クライアント側の処理*/
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Robby.GUI_ID) {
            return new IDROIDGuiContainer(x, y, z);
        }
        return null;
    }

}
