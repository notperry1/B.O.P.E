package rina.turok.bope.bopemod.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.label.BopeLabel;
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
public class BopeUser extends BopePinnable {
	BopeLabel user = create_line("");

	public BopeUser() {
		super("BOPE User", "BopeUser", 1, 20, 20);
	}

	@Override
	public void render() {
		user.update(ChatFormatting.DARK_BLUE + mc.player.getName());

		this.draw();
	}
}