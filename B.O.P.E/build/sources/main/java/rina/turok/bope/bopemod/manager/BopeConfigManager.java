package rina.turok.bope.bopemod.manager;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.time.*;
import java.util.*;
import java.io.*;

// Json manager.
import com.google.gson.*;

// Managers.
import rina.turok.bope.bopemod.manager.BopeSettingManager;
import rina.turok.bope.bopemod.manager.BopeModuleManager;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;
import rina.turok.bope.bopemod.guiscreen.render.components.BopeFrame;
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;
import rina.turok.bope.bopemod.BopeFriend;

// Core.
import rina.turok.bope.Bope;

/**
 * @author Rina.
 *
 * Created by Rina.
 * 08/04/2020.
 *
 */
public class BopeConfigManager {
	public String tag;

	public String BOPE_FILE_COMBOBOXS = "settings_2.bin";
	public String BOPE_FILE_INTEGERS  = "settings_5.bin";
	public String BOPE_FOLDER_CONFIG  = "B.O.P.E/";
	public String BOPE_FILE_FRIENDS   = "friends.json";
	public String BOPE_FILE_BUTTONS   = "settings_1.bin";
	public String BOPE_FILE_DOUBLES   = "settings_4.bin";
	public String BOPE_FILE_CLIENT    = "client.json";
	public String BOPE_FILE_LABELS    = "settings_3.bin";
	public String BOPE_FOLDER_LOG     = "logs/";
	public String BOPE_FILE_BINDS     = "binds.json";
	public String BOPE_FILE_HUD       = "HUD.json";
	public String BOPE_FILE_LOG       = "log";

	public String BOPE_ABS_FOLDER_LOG = (BOPE_FOLDER_CONFIG + BOPE_FOLDER_LOG);
	public String BOPE_ABS_COMBOBOXS  = (BOPE_FOLDER_CONFIG + BOPE_FILE_COMBOBOXS);
	public String BOPE_ABS_INTEGERS   = (BOPE_FOLDER_CONFIG + BOPE_FILE_INTEGERS);
	public String BOPE_ABS_DOUBLES    = (BOPE_FOLDER_CONFIG + BOPE_FILE_DOUBLES);
	public String BOPE_ABS_BUTTONS    = (BOPE_FOLDER_CONFIG + BOPE_FILE_BUTTONS);
	public String BOPE_ABS_FRIENDS    = (BOPE_FOLDER_CONFIG + BOPE_FILE_FRIENDS);
	public String BOPE_ABS_LABELS     = (BOPE_FOLDER_CONFIG + BOPE_FILE_LABELS);
	public String BOPE_ABS_FOLDER     = (BOPE_FOLDER_CONFIG);
	public String BOPE_ABS_CLIENT     = (BOPE_FOLDER_CONFIG + BOPE_FILE_CLIENT);
	public String BOPE_ABS_BINDS      = (BOPE_FOLDER_CONFIG + BOPE_FILE_BINDS);
	public String BOPE_ABS_HUD        = (BOPE_FOLDER_CONFIG + BOPE_FILE_HUD);
	public String BOPE_ABS_LOG        = (BOPE_ABS_FOLDER_LOG + BOPE_FILE_LOG);

	public Path PATH_FOLDER_LOG = Paths.get(BOPE_ABS_FOLDER_LOG);
	public Path PATH_COMBOBOXS  = Paths.get(BOPE_ABS_COMBOBOXS);
	public Path PATH_INTEGERS   = Paths.get(BOPE_ABS_INTEGERS);
	public Path PATH_DOUBLES    = Paths.get(BOPE_ABS_DOUBLES);
	public Path PATH_FRIENDS    = Paths.get(BOPE_ABS_FRIENDS);
	public Path PATH_BUTTONS    = Paths.get(BOPE_ABS_BUTTONS);
	public Path PATH_CLIENT     = Paths.get(BOPE_ABS_CLIENT);
	public Path PATH_LABELS     = Paths.get(BOPE_ABS_LABELS);
	public Path PATH_FOLDER     = Paths.get(BOPE_ABS_FOLDER);
	public Path PATH_BINDS      = Paths.get(BOPE_ABS_BINDS);
	public Path PATH_HUD        = Paths.get(BOPE_ABS_HUD);

	public Path PATH_LOG;

	public StringBuilder log;

	public BopeConfigManager(String tag) {
		this.tag  = tag;
		this.log = new StringBuilder();

		Date   hora = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy' - 'HH:mm:ss:").format(hora);

		send_log("****** Files have started. ******");
		send_log("- The author of this log is SrRina.");
		send_log("- Any crash or problem its here.");
		send_log("- (GoT) Rina#8637");
		send_log("****** File information. ******");
		send_log("- Client name: " + Bope.get_name());
		send_log("- Client version: " + Bope.get_version());
		send_log("- File created in: " + data);
		send_log("- ");
		send_log("- >");

	}

	public void BOPE_VERIFY_FOLDER(Path path) throws IOException {
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}
	}

	public void BOPE_VERIFY_FILES(Path path) throws IOException {
		if (!Files.exists(path)) {
			Files.createFile(path);
		}
	}

	public void BOPE_DELETE_FILES(String abs_path) throws IOException {
		File file = new File(abs_path);

		file.delete();
	}

	public void BOPE_SAVE_BUTTONS() throws IOException {
		Gson       BOPE_GSON         = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER       = new JsonParser(); 
		JsonObject BOPE_MAIN_JSON    = new JsonObject();
		JsonObject BOPE_MAIN_BUTTONS = new JsonObject();

		for (BopeSetting buttons : Bope.get_setting_manager().get_array_settings()) {
			boolean button = false;

			JsonObject BOPE_BUTTON_SETTING = new JsonObject();

			if (!(is(buttons, "button"))){
				continue;
			}

			BOPE_BUTTON_SETTING.add("master", new JsonPrimitive(buttons.get_master().get_tag()));
			BOPE_BUTTON_SETTING.add("name",   new JsonPrimitive(buttons.get_name()));
			BOPE_BUTTON_SETTING.add("tag",    new JsonPrimitive(buttons.get_tag()));
			BOPE_BUTTON_SETTING.add("value",  new JsonPrimitive(buttons.get_value(button)));
			BOPE_BUTTON_SETTING.add("type",   new JsonPrimitive(buttons.get_type()));

			BOPE_MAIN_BUTTONS.add(buttons.get_tag(), BOPE_BUTTON_SETTING);
		}

		BOPE_MAIN_JSON.add("buttons", BOPE_MAIN_BUTTONS);

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_FILES(BOPE_ABS_BUTTONS);
		BOPE_VERIFY_FILES(PATH_BUTTONS);

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_BUTTONS), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
	}

	public void BOPE_SAVE_COMBOBOXS() throws IOException {
		Gson       BOPE_GSON           = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER         = new JsonParser(); 
		JsonObject BOPE_MAIN_JSON      = new JsonObject();
		JsonObject BOPE_MAIN_COMBOBOXS = new JsonObject();

		for (BopeSetting comboboxs : Bope.get_setting_manager().get_array_settings()) {
			JsonObject BOPE_COMBOBOX_SETTING = new JsonObject();

			if (!(is(comboboxs, "combobox"))) {
				continue;
			}

			BOPE_COMBOBOX_SETTING.add("master", new JsonPrimitive(comboboxs.get_master().get_tag()));
			BOPE_COMBOBOX_SETTING.add("name",   new JsonPrimitive(comboboxs.get_name()));
			BOPE_COMBOBOX_SETTING.add("tag",    new JsonPrimitive(comboboxs.get_tag()));
			BOPE_COMBOBOX_SETTING.add("value",  new JsonPrimitive(comboboxs.get_current_value()));
			BOPE_COMBOBOX_SETTING.add("type",   new JsonPrimitive(comboboxs.get_type()));

			BOPE_MAIN_COMBOBOXS.add(comboboxs.get_tag(), BOPE_COMBOBOX_SETTING);
		}

		BOPE_MAIN_JSON.add("comboboxs", BOPE_MAIN_COMBOBOXS);

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_FILES(BOPE_ABS_COMBOBOXS);
		BOPE_VERIFY_FILES(PATH_COMBOBOXS);

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_COMBOBOXS), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
	}

	public void BOPE_SAVE_LABELS() throws IOException {
		Gson       BOPE_GSON        = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER      = new JsonParser(); 
		JsonObject BOPE_MAIN_JSON   = new JsonObject();
		JsonObject BOPE_MAIN_LABELS = new JsonObject();

		for (BopeSetting labels : Bope.get_setting_manager().get_array_settings()) {
			String label = "ue";

			JsonObject BOPE_LABELS_SETTING = new JsonObject();

			if (!(is(labels, "label"))) {
				continue;
			}

			BOPE_LABELS_SETTING.add("master", new JsonPrimitive(labels.get_master().get_tag()));
			BOPE_LABELS_SETTING.add("name",   new JsonPrimitive(labels.get_name()));
			BOPE_LABELS_SETTING.add("tag",    new JsonPrimitive(labels.get_tag()));
			BOPE_LABELS_SETTING.add("value",  new JsonPrimitive(labels.get_value(label)));
			BOPE_LABELS_SETTING.add("type",   new JsonPrimitive(labels.get_type()));

			BOPE_MAIN_LABELS.add(labels.get_tag(), BOPE_LABELS_SETTING);
		}

		BOPE_MAIN_JSON.add("labels", BOPE_MAIN_LABELS);

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_FILES(BOPE_ABS_LABELS);
		BOPE_VERIFY_FILES(PATH_LABELS);

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_LABELS), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
	}

	public void BOPE_SAVE_DOUBLES() throws IOException {
		Gson       BOPE_GSON           = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER         = new JsonParser(); 
		JsonObject BOPE_MAIN_JSON      = new JsonObject();
		JsonObject BOPE_MAIN_SLIDERS_D = new JsonObject();

		for (BopeSetting slider_doubles : Bope.get_setting_manager().get_array_settings()) {
			double double_ = 1.2;

			if (!(is(slider_doubles, "doubleslider"))) {
				continue;
			}

			JsonObject BOPE_SLIDER_DOUBLES_SETTING = new JsonObject();			

			BOPE_SLIDER_DOUBLES_SETTING.add("master", new JsonPrimitive(slider_doubles.get_master().get_tag()));
			BOPE_SLIDER_DOUBLES_SETTING.add("name",   new JsonPrimitive(slider_doubles.get_name()));
			BOPE_SLIDER_DOUBLES_SETTING.add("tag",    new JsonPrimitive(slider_doubles.get_tag()));
			BOPE_SLIDER_DOUBLES_SETTING.add("value",  new JsonPrimitive(slider_doubles.get_value(double_)));
			BOPE_SLIDER_DOUBLES_SETTING.add("type",   new JsonPrimitive(slider_doubles.get_type()));

			BOPE_MAIN_SLIDERS_D.add(slider_doubles.get_tag(), BOPE_SLIDER_DOUBLES_SETTING);
		}

		BOPE_MAIN_JSON.add("sliders", BOPE_MAIN_SLIDERS_D);

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_FILES(BOPE_ABS_DOUBLES);
		BOPE_VERIFY_FILES(PATH_DOUBLES);

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_DOUBLES), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
	}

	public void BOPE_SAVE_INTEGERS() throws IOException {
		Gson       BOPE_GSON           = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER         = new JsonParser(); 
		JsonObject BOPE_MAIN_JSON      = new JsonObject();
		JsonObject BOPE_MAIN_SLIDERS_I = new JsonObject();

		for (BopeSetting slider_integers : Bope.get_setting_manager().get_array_settings()) {
			double integer = 1;

			JsonObject BOPE_SLIDER_INTEGERS_SETTING = new JsonObject();

			if (!(is(slider_integers, "integerslider"))) {
				continue;
			}

			BOPE_SLIDER_INTEGERS_SETTING.add("master", new JsonPrimitive(slider_integers.get_master().get_tag()));
			BOPE_SLIDER_INTEGERS_SETTING.add("name",   new JsonPrimitive(slider_integers.get_name()));
			BOPE_SLIDER_INTEGERS_SETTING.add("tag",    new JsonPrimitive(slider_integers.get_tag()));
			BOPE_SLIDER_INTEGERS_SETTING.add("value",  new JsonPrimitive((int) Math.round(slider_integers.get_value(integer))));
			BOPE_SLIDER_INTEGERS_SETTING.add("type",   new JsonPrimitive(slider_integers.get_type()));

			BOPE_MAIN_SLIDERS_I.add(slider_integers.get_tag(), BOPE_SLIDER_INTEGERS_SETTING);
		}

		BOPE_MAIN_JSON.add("sliders", BOPE_MAIN_SLIDERS_I);

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_FILES(BOPE_ABS_INTEGERS);
		BOPE_VERIFY_FILES(PATH_INTEGERS);

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_INTEGERS), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
	}

	public void BOPE_LOAD_BUTTONS() throws IOException {
		InputStream BOPE_JSON_FILE    = Files.newInputStream(PATH_BUTTONS);
		JsonObject  BOPE_JSON         = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
		JsonObject  BOPE_MAIN_BUTTONS = BOPE_JSON.get("buttons").getAsJsonObject();

		for (BopeSetting buttons : Bope.get_setting_manager().get_array_settings()) {
			if (!(is(buttons, "button"))) {
				continue;
			}

			JsonObject BOPE_BUTTONS_INFO = BOPE_MAIN_BUTTONS.get(buttons.get_tag()).getAsJsonObject();
			
			BopeSetting button_requested = Bope.get_setting_manager().get_setting_with_tag(BOPE_BUTTONS_INFO.get("master").getAsString(), BOPE_BUTTONS_INFO.get("tag").getAsString());

			if (button_requested == null) {
				continue;
			}

			button_requested.set_value(BOPE_BUTTONS_INFO.get("value").getAsBoolean());
		}

		BOPE_JSON_FILE.close();
	}

	public void BOPE_LOAD_COMBOBOXS() throws IOException {
		InputStream BOPE_JSON_FILE      = Files.newInputStream(PATH_COMBOBOXS);
		JsonObject  BOPE_JSON           = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
		JsonObject  BOPE_COMBOBOXS_MAIN = BOPE_JSON.get("comboboxs").getAsJsonObject();

		for (BopeSetting comboboxs : Bope.get_setting_manager().get_array_settings()) {
			if (!(is(comboboxs, "combobox"))) {
				continue;
			}

			JsonObject BOPE_COMBOBOX_INFO = BOPE_COMBOBOXS_MAIN.get(comboboxs.get_tag()).getAsJsonObject();

			BopeSetting combobox_requested = Bope.get_setting_manager().get_setting_with_tag(BOPE_COMBOBOX_INFO.get("master").getAsString(), BOPE_COMBOBOX_INFO.get("tag").getAsString());

			if (combobox_requested == null) {
				continue;
			}

			combobox_requested.set_current_value(BOPE_COMBOBOX_INFO.get("value").getAsString());
		}

		BOPE_JSON_FILE.close();
	}

	public void BOPE_LOAD_LABELS() throws IOException {
		InputStream BOPE_JSON_FILE   = Files.newInputStream(PATH_LABELS);
		JsonObject  BOPE_JSON        = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
		JsonObject  BOPE_LABELS_MAIN = BOPE_JSON.get("labels").getAsJsonObject();

		for (BopeSetting labels : Bope.get_setting_manager().get_array_settings()) {
			if (!(is(labels, "label"))) {
				continue;
			}

			JsonObject BOPE_LABELS_INFO = BOPE_LABELS_MAIN.get(labels.get_tag()).getAsJsonObject();

			BopeSetting label_requested = Bope.get_setting_manager().get_setting_with_tag(BOPE_LABELS_INFO.get("master").getAsString(), BOPE_LABELS_INFO.get("tag").getAsString());

			if (label_requested == null) {
				continue;
			}

			label_requested.set_value(BOPE_LABELS_INFO.get("value").getAsString());
		}

		BOPE_JSON_FILE.close();
	}

	public void BOPE_LOAD_DOUBLES() throws IOException {
		InputStream BOPE_JSON_FILE     = Files.newInputStream(PATH_DOUBLES);
		JsonObject  BOPE_JSON          = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
		JsonObject  BOPE_SLIDER_D_MAIN = BOPE_JSON.get("sliders").getAsJsonObject();

		for (BopeSetting slider_doubles : Bope.get_setting_manager().get_array_settings()) {
			if (!(is(slider_doubles, "doubleslider"))) {
				continue;
			}

			JsonObject BOPE_SLIDER_D_INFO = BOPE_SLIDER_D_MAIN.get(slider_doubles.get_tag()).getAsJsonObject();

			BopeSetting slider_double_requested = Bope.get_setting_manager().get_setting_with_tag(BOPE_SLIDER_D_INFO.get("master").getAsString(), BOPE_SLIDER_D_INFO.get("tag").getAsString());

			if (slider_double_requested == null) {
				continue;
			}

			slider_double_requested.set_value(BOPE_SLIDER_D_INFO.get("value").getAsDouble());
		}

		BOPE_JSON_FILE.close();
	}

	public void BOPE_LOAD_INTEGERS() throws IOException {
		InputStream BOPE_JSON_FILE     = Files.newInputStream(PATH_INTEGERS);
		JsonObject  BOPE_JSON          = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
		JsonObject  BOPE_SLIDER_I_MAIN = BOPE_JSON.get("sliders").getAsJsonObject();

		for (BopeSetting slider_integers : Bope.get_setting_manager().get_array_settings()) {
			if (!(is(slider_integers, "integerslider"))) {
				continue;
			}

			JsonObject BOPE_SLIDER_I_INFO = BOPE_SLIDER_I_MAIN.get(slider_integers.get_tag()).getAsJsonObject();

			BopeSetting slider_integer_requested = Bope.get_setting_manager().get_setting_with_tag(BOPE_SLIDER_I_INFO.get("master").getAsString(), BOPE_SLIDER_I_INFO.get("tag").getAsString());

			if (slider_integer_requested == null) {
				continue;
			}

			slider_integer_requested.set_value(BOPE_SLIDER_I_INFO.get("value").getAsInt());
		}

		BOPE_JSON_FILE.close();
	}

	public void BOPE_SAVE_BINDS() throws IOException {
		Gson       BOPE_GSON   = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER = new JsonParser();

		JsonObject BOPE_MAIN_JSON   = new JsonObject();
		JsonObject BOPE_MODULE_JSON = new JsonObject();

		for (BopeModule modules : Bope.get_module_manager().get_array_modules()) {
			JsonObject BOPE_MODULE_INFO = new JsonObject();

			BOPE_MODULE_INFO.add("name",   new JsonPrimitive(modules.get_name()));
			BOPE_MODULE_INFO.add("tag",    new JsonPrimitive(modules.get_tag()));
			BOPE_MODULE_INFO.add("int",    new JsonPrimitive(modules.get_bind(0)));
			BOPE_MODULE_INFO.add("string", new JsonPrimitive(modules.get_bind("0")));
			BOPE_MODULE_INFO.add("state",  new JsonPrimitive(modules.is_active()));
			BOPE_MODULE_INFO.add("alert",  new JsonPrimitive(modules.can_send_message_when_toggle()));

			BOPE_MODULE_JSON.add(modules.get_tag(), BOPE_MODULE_INFO);
		}

		BOPE_MAIN_JSON.add("modules", BOPE_MODULE_JSON);

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_FILES(BOPE_ABS_BINDS);
		BOPE_VERIFY_FILES(PATH_BINDS);

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_BINDS), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
	}

	public void BOPE_LOAD_BINDS() throws IOException {
		InputStream BOPE_JSON_FILE      = Files.newInputStream(PATH_BINDS);
		JsonObject  BOPE_JSON           = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
		JsonObject  BOPE_MAIN_MODULES   = BOPE_JSON.get("modules").getAsJsonObject();

		for (BopeModule modules : Bope.get_module_manager().get_array_modules()) {
			JsonObject BOPE_MODULES_JSON = BOPE_MAIN_MODULES.get(modules.get_tag()).getAsJsonObject();

			BopeModule module_requested = Bope.get_module_manager().get_module_with_tag(BOPE_MODULES_JSON.get("tag").getAsString());

			if (module_requested == null) {
				continue;
			}

			module_requested.set_bind(BOPE_MODULES_JSON.get("int").getAsInt());
			module_requested.set_active(BOPE_MODULES_JSON.get("state").getAsBoolean());
			module_requested.set_if_can_send_message_toggle(BOPE_MODULES_JSON.get("alert").getAsBoolean());
		}

		BOPE_JSON_FILE.close();
	}

	public void BOPE_SAVE_CLIENT() throws IOException {
		Gson       BOPE_GSON   = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER = new JsonParser();

		JsonObject BOPE_MAIN_JSON = new JsonObject();

		JsonObject BOPE_MAIN_CONFIGS  = new JsonObject();
		JsonObject BOPE_MAIN_GUI      = new JsonObject();

		BOPE_MAIN_CONFIGS.add("name",    new JsonPrimitive(Bope.get_name()));
		BOPE_MAIN_CONFIGS.add("version", new JsonPrimitive(Bope.get_version()));
		BOPE_MAIN_CONFIGS.add("user",    new JsonPrimitive(Bope.get_actual_user()));
		BOPE_MAIN_CONFIGS.add("prefix",  new JsonPrimitive(Bope.get_command_manager().get_prefix()));

		for (BopeFrame frames_gui : Bope.click_gui.get_array_frames()) {
			JsonObject BOPE_FRAMES_INFO = new JsonObject();

			BOPE_FRAMES_INFO.add("name", new JsonPrimitive(frames_gui.get_name()));
			BOPE_FRAMES_INFO.add("tag",  new JsonPrimitive(frames_gui.get_tag()));
			BOPE_FRAMES_INFO.add("x",    new JsonPrimitive(frames_gui.get_x()));
			BOPE_FRAMES_INFO.add("y",    new JsonPrimitive(frames_gui.get_y()));

			BOPE_MAIN_GUI.add(frames_gui.get_tag(), BOPE_FRAMES_INFO);
		}

		BOPE_MAIN_JSON.add("configuration", BOPE_MAIN_CONFIGS);
		BOPE_MAIN_JSON.add("gui",           BOPE_MAIN_GUI);

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_FILES(BOPE_ABS_CLIENT);
		BOPE_VERIFY_FILES(PATH_CLIENT);

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_CLIENT), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
	}

	public void BOPE_LOAD_CLIENT() throws IOException {
		InputStream BOPE_JSON_FILE          = Files.newInputStream(PATH_CLIENT);
		JsonObject  BOPE_MAIN_CLIENT        = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
		JsonObject  BOPE_MAIN_CONFIGURATION = BOPE_MAIN_CLIENT.get("configuration").getAsJsonObject();
		JsonObject  BOPE_MAIN_GUI           = BOPE_MAIN_CLIENT.get("gui").getAsJsonObject();

		Bope.get_command_manager().set_prefix(BOPE_MAIN_CONFIGURATION.get("prefix").getAsString());

		for (BopeFrame frames : Bope.click_gui.get_array_frames()) {
			JsonObject BOPE_FRAME_INFO = BOPE_MAIN_GUI.get(frames.get_tag()).getAsJsonObject();

			BopeFrame frame_requested = Bope.click_gui.get_frame_with_tag(BOPE_FRAME_INFO.get("tag").getAsString());

			frame_requested.set_x(BOPE_FRAME_INFO.get("x").getAsInt());
			frame_requested.set_y(BOPE_FRAME_INFO.get("y").getAsInt());
		}

		BOPE_JSON_FILE.close();
	}

	public void BOPE_SAVE_HUD() throws IOException {
		Gson       BOPE_GSON   = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER = new JsonParser();

		JsonObject BOPE_MAIN_JSON = new JsonObject();

		JsonObject BOPE_MAIN_FRAME = new JsonObject();
		JsonObject BOPE_MAIN_HUD   = new JsonObject();

		BOPE_MAIN_FRAME.add("name", new JsonPrimitive(Bope.click_hud.get_frame_hud().get_name()));
		BOPE_MAIN_FRAME.add("tag",  new JsonPrimitive(Bope.click_hud.get_frame_hud().get_tag()));
		BOPE_MAIN_FRAME.add("x",    new JsonPrimitive(Bope.click_hud.get_frame_hud().get_x()));
		BOPE_MAIN_FRAME.add("y",    new JsonPrimitive(Bope.click_hud.get_frame_hud().get_y()));

		for (BopePinnable pinnables_hud : Bope.get_hud_manager().get_array_huds()) {
			JsonObject BOPE_FRAMES_INFO = new JsonObject();

			BOPE_FRAMES_INFO.add("title", new JsonPrimitive(pinnables_hud.get_title()));
			BOPE_FRAMES_INFO.add("tag",   new JsonPrimitive(pinnables_hud.get_tag()));
			BOPE_FRAMES_INFO.add("state", new JsonPrimitive(pinnables_hud.is_active()));
			BOPE_FRAMES_INFO.add("dock",  new JsonPrimitive(pinnables_hud.get_dock()));
			BOPE_FRAMES_INFO.add("x",     new JsonPrimitive(pinnables_hud.get_x()));
			BOPE_FRAMES_INFO.add("y",     new JsonPrimitive(pinnables_hud.get_y()));

			BOPE_MAIN_HUD.add(pinnables_hud.get_tag(), BOPE_FRAMES_INFO);
		}

		BOPE_MAIN_JSON.add("frame", BOPE_MAIN_FRAME);
		BOPE_MAIN_JSON.add("hud",   BOPE_MAIN_HUD);

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_FILES(BOPE_ABS_HUD);
		BOPE_VERIFY_FILES(PATH_HUD);

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_HUD), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
	}

	public void BOPE_LOAD_HUD() throws IOException {
		InputStream BOPE_JSON_FILE  = Files.newInputStream(PATH_HUD);
		JsonObject  BOPE_MAIN_HUD   = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
		JsonObject  BOPE_MAIN_FRAME = BOPE_MAIN_HUD.get("frame").getAsJsonObject();
		JsonObject  BOPE_MAIN_HUDS  = BOPE_MAIN_HUD.get("hud").getAsJsonObject();

		Bope.click_hud.get_frame_hud().set_x(BOPE_MAIN_FRAME.get("x").getAsInt());
		Bope.click_hud.get_frame_hud().set_y(BOPE_MAIN_FRAME.get("y").getAsInt());

		for (BopePinnable pinnables : Bope.get_hud_manager().get_array_huds()) {
			JsonObject BOPE_HUD_INFO = BOPE_MAIN_HUDS.get(pinnables.get_tag()).getAsJsonObject();

			BopePinnable pinnable_requested = Bope.get_hud_manager().get_pinnable_with_tag(BOPE_HUD_INFO.get("tag").getAsString());

			pinnable_requested.set_active(BOPE_HUD_INFO.get("state").getAsBoolean());
			pinnable_requested.set_dock(BOPE_HUD_INFO.get("dock").getAsBoolean());

			pinnable_requested.set_x(BOPE_HUD_INFO.get("x").getAsInt());
			pinnable_requested.set_y(BOPE_HUD_INFO.get("y").getAsInt());
		}

		BOPE_JSON_FILE.close();
	}

	public void BOPE_SAVE_FRIEND() throws IOException {
		Gson       BOPE_GSON   = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER = new JsonParser();

		JsonObject BOPE_MAIN_JSON = new JsonObject();
		JsonArray  BOPE_ARRAY_FRD = new JsonArray();

		for (BopeFriend friends : Bope.get_friend_manager().get_array_friends()) {
			BOPE_ARRAY_FRD.add(friends.get_name());
		}

		BOPE_MAIN_JSON.add("friends", BOPE_ARRAY_FRD);

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_FILES(BOPE_ABS_FRIENDS);
		BOPE_VERIFY_FILES(PATH_FRIENDS);

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_FRIENDS), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
	}

	public void BOPE_LOAD_FRIENDS() throws IOException {
		InputStream BOPE_JSON_FILE    = Files.newInputStream(PATH_FRIENDS);
		JsonObject  BOPE_MAIN_FRD     = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
		JsonArray   BOPE_MAIN_FRIENDS = BOPE_MAIN_FRD.get("friends").getAsJsonArray();

		for (JsonElement friends_name : BOPE_MAIN_FRIENDS) {
			Bope.get_friend_manager().add_friend(friends_name.getAsString());
		}

		BOPE_JSON_FILE.close();
	}

	public void BOPE_SAVE_LOG() throws IOException {
		Date hora = new Date();

		String cache = "-";
		String year  = new SimpleDateFormat("yyyy").format(hora);
		String month = new SimpleDateFormat("MM").format(hora);
		String day   = new SimpleDateFormat("dd").format(hora);
		String hour  = new SimpleDateFormat("HH").format(hora);
		String mm    = new SimpleDateFormat("mm").format(hora);
		String ss    = new SimpleDateFormat("ss").format(hora);
		String path  = (BOPE_ABS_LOG + cache + year + cache + month + cache + day + cache + hour + cache + mm + cache + ss + cache + ".txt");

		PATH_LOG = Paths.get(path);

		BOPE_VERIFY_FILES(PATH_LOG);

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
		file.write(this.log.toString());

		file.close();
	}

	public void save_values() {
		try {
			BOPE_VERIFY_FOLDER(PATH_FOLDER);
			BOPE_VERIFY_FILES(PATH_BUTTONS);
			BOPE_VERIFY_FILES(PATH_COMBOBOXS);
			BOPE_VERIFY_FILES(PATH_LABELS);
			BOPE_VERIFY_FILES(PATH_DOUBLES);
			BOPE_VERIFY_FILES(PATH_INTEGERS);

			BOPE_SAVE_BUTTONS();
			BOPE_SAVE_COMBOBOXS();
			BOPE_SAVE_LABELS();
			BOPE_SAVE_DOUBLES();
			BOPE_SAVE_INTEGERS();
		} catch (IOException exc) {
			exc.printStackTrace();
		}		
	}

	public void save_binds() {
		try {
			BOPE_VERIFY_FOLDER(PATH_FOLDER);

			BOPE_SAVE_BINDS();
		} catch (IOException exc) {
			exc.printStackTrace();
		}		
	}

	public void save_client() {
		try {
			BOPE_VERIFY_FOLDER(PATH_FOLDER);

			BOPE_SAVE_CLIENT();
			BOPE_SAVE_HUD();
		} catch (IOException exc) {
			exc.printStackTrace();
		}		
	}

	public void save_friends() {
		try {
			BOPE_VERIFY_FOLDER(PATH_FOLDER);

			BOPE_SAVE_FRIEND();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}

	public void save_log() {
		try {
			BOPE_VERIFY_FOLDER(PATH_FOLDER);
			BOPE_VERIFY_FOLDER(PATH_FOLDER_LOG);

			BOPE_SAVE_LOG();
		} catch (IOException exc) {
			exc.printStackTrace();
		}		
	}

	public void load() {
		try {
			BOPE_LOAD_BUTTONS();
		} catch (Exception exc) {}

		try {
			BOPE_LOAD_COMBOBOXS();
		} catch (Exception exc) {}

		try {
			BOPE_LOAD_LABELS();
		} catch (Exception exc) {}

		try {
			BOPE_LOAD_DOUBLES();
		} catch (Exception exc) {}

		try {
			BOPE_LOAD_INTEGERS();
		} catch (Exception exc) {}

		try {
			BOPE_LOAD_BINDS();
		} catch (Exception exc) {}

		try {
			BOPE_LOAD_CLIENT();
		} catch (Exception exc) {}

		try {
			BOPE_LOAD_HUD();
		} catch (Exception exc) {}


		try {
			BOPE_LOAD_FRIENDS();
		} catch (Exception exc) {}
	}

	public boolean is(BopeSetting setting, String type) {
		return setting.get_type().equalsIgnoreCase(type);
	}

	public void send_log(String log) {
		this.log.append(log + "\n");
	}
}