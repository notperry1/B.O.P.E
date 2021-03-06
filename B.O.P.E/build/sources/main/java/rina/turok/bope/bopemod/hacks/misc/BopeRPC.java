package rina.turok.bope.bopemod.hacks.misc;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Events.
import rina.turok.bope.bopemod.events.BopeEventPacket;
import rina.turok.bope.bopemod.events.BopeEventMove;

// Util.
import static rina.turok.bope.bopemod.util.BopeUtilMath.movement_speed;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
 * @author Rina
 *
 * Created by Rina.
 * 15/06/2020.
 *
 **/
public class BopeRPC extends BopeModule {
	BopeSetting message = create("Custom", "RPCCustom", "");

	public BopeRPC() {
		super(BopeCategory.BOPE_MISC);

		// Info.
		this.name        = "RPC";
		this.tag         = "RPC";
		this.description = "Enable Discord rich presence library to work with client B.O.P.E.";

		// Release or launch the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public void enable() {
		Bope.get_rpc().run();
	}

	@Override
	public void disable() {
		Bope.get_rpc().stop();
	}

	@Override
	public void update() {
		Bope.get_rpc().state_option_1 = message.get_value("");
	}
}