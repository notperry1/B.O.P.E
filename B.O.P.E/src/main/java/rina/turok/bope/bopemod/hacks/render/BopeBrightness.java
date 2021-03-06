package rina.turok.bope.bopemod.hacks.render;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;

/**
* @author Rina
*
* Created by Rina.
* 24/05/2020.
*
*/
public class BopeBrightness extends BopeModule {
	float old_gamme_value;	

	public BopeBrightness() {
		super(BopeCategory.BOPE_RENDER, false);

		// Info.
		this.name        = "Brightness";
		this.tag         = "Brightness";
		this.description = "Set full gamma.";

		// Release or launch the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public void disable() {
		mc.gameSettings.gammaSetting -= 1000;
	}

	@Override
	public void enable() {
		mc.gameSettings.gammaSetting += 1000;
	}
}