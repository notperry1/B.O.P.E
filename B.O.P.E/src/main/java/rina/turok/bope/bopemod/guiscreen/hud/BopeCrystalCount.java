package rina.turok.bope.bopemod.guiscreen.hud;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

import com.mojang.realmsclient.gui.ChatFormatting;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 06/05/20.
*
*/
public class BopeCrystalCount extends BopePinnable {
	int crystals = 0;

	public BopeCrystalCount() {
		super("Crystal Count", "CrystalCount", 1, 0, 0);
	}

	@Override
	public void render() {
		if (mc.player != null) {
			if (is_on_gui()) {
				background();
			}

			GlStateManager.pushMatrix();
			RenderHelper.enableGUIStandardItemLighting();

			crystals = mc.player.inventory.mainInventory.stream().filter(stack -> stack.getItem() == Items.END_CRYSTAL).mapToInt(ItemStack::getCount).sum();

			int off = 0;

			for (int i = 0; i < 45; i++) {
				ItemStack stack = mc.player.inventory.getStackInSlot(i);
				ItemStack off_h = mc.player.getHeldItemOffhand();

				if (off_h.getItem() == Items.END_CRYSTAL) {
					off = off_h.stackSize;
				} else {
					off = 0;
				}

				if (stack.getItem() == Items.END_CRYSTAL) {
					// Docking (defaul, width);
					mc.getRenderItem().renderItemAndEffectIntoGUI(stack, get_x() + docking(0, 16), get_y());

					create_line(Integer.toString(crystals + off), 16 + 2, 14 - get(Integer.toString(crystals + off), "height"));
				}
			}

			mc.getRenderItem().zLevel = 0.0f;

			RenderHelper.disableStandardItemLighting();		
			
			GlStateManager.popMatrix();

			set_width(16 + get(Integer.toString(crystals + off), "width") + 2);
			set_height(16);
		}
	}
}