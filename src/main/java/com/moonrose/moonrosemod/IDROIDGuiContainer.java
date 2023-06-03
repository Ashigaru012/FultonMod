package com.moonrose.moonrosemod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonToggle;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.client.GuiConfirmation;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.minecraftforge.fml.client.config.GuiCheckBox;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import java.io.IOException;

public class IDROIDGuiContainer extends GuiContainer{
    private static final ResourceLocation TEXTURE = new ResourceLocation("<DomainName>", "textures/gui/gui_texture.png");
    private static final ResourceLocation GUITEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
    private GuiButtonExt button1;
    private GuiTextField textfieldX;
//    private GuiTextField textfieldY;
    private GuiTextField textfieldZ;
    private boolean isFocused;

    SingletonCoordinate coordinate = SingletonCoordinate.getInstance();

    @Override
    public void initGui() {
        super.initGui();
        button1 = new GuiButtonExt(0,width/2-50,height/2+80,100,20,"DONE");
        buttonList.add(button1);
        this.textfieldX = new GuiTextField(2,fontRenderer,width/2-70,height / 2,50,15);
//        this.textfieldY = new GuiTextField(3,fontRenderer,width/2-20,height / 2,50,15);
        this.textfieldZ = new GuiTextField(4,fontRenderer,width/2+30,height / 2,50,15);



        textfieldX.setMaxStringLength(15);
        this.textfieldX.setFocused(false);

//        textfieldY.setMaxStringLength(15);
//        this.textfieldY.setFocused(false);

        textfieldZ.setMaxStringLength(15);
        this.textfieldZ.setFocused(false);

    }
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar,keyCode);
        this.textfieldX.textboxKeyTyped(typedChar,keyCode);
//        this.textfieldY.textboxKeyTyped(typedChar,keyCode);
        this.textfieldZ.textboxKeyTyped(typedChar,keyCode);
    }
    @Override
    public void updateScreen(){
        super.updateScreen();
        textfieldX.updateCursorCounter();
//        textfieldY.updateCursorCounter();
        textfieldZ.updateCursorCounter();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        super.drawScreen(mouseX, mouseY, partialTicks);

        renderHoveredToolTip(mouseX, mouseY);
        this.textfieldX.drawTextBox();
//        this.textfieldY.drawTextBox();
        this.textfieldZ.drawTextBox();
    }
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.textfieldX.mouseClicked(mouseX, mouseY, mouseButton);
//        this.textfieldY.mouseClicked(mouseX, mouseY, mouseButton);
        this.textfieldZ.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button == button1){
            //数字ならばキャストしてset、そうでないならそのまま何もせずにGUI閉じる
            if (isNumeric(this.textfieldX.getText())){
                coordinate.setX(Double.parseDouble(this.textfieldX.getText()));
            }
//            if (isNumeric(this.textfieldY.getText())){
//                coordinate.setY(Double.parseDouble(this.textfieldY.getText()));
//            }
            if (isNumeric(this.textfieldZ.getText())){
                coordinate.setZ(Double.parseDouble(this.textfieldZ.getText()));
            }
            mc.displayGuiScreen(null);

        }
    }

    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }




    public IDROIDGuiContainer(int x, int y, int z) {
        super(new IDROIDContainer(x, y, z));
    }

    /*GUIの文字等の描画処理*/
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseZ) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseZ);

    }

    /*GUIの背景の描画処理*/
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseZ) {
//        this.mc.renderEngine.bindTexture(TEXTURE);
//        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, xSize, ySize);
    }

    /*GUIが開いている時にゲームの処理を止めるかどうか。*/
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
