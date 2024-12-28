package me.orphey;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Path;

public class ConfigLoader {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String CONFIG_FILE_NAME = "typinginchat-config.json";
    private static ConfigLoader instance;
    private static String modID = "[TypingInChat]";

    // Configurable options
    private boolean enableMod = true;
    private boolean ignoreCommands = false;
    private boolean debug = false;

    public boolean isEnableMod() {
        return this.enableMod;
    }
    public boolean isIgnoreCommands() {
        return this.ignoreCommands;
    }
    public boolean isDebug() {
        return this.debug;
    }

    // Load or create the config
    public static ConfigLoader load(Path configDir) {
        File configFile = configDir.resolve(CONFIG_FILE_NAME).toFile();
        if (configFile.exists()) {
            try (Reader reader = new FileReader(configFile)) {
                instance = GSON.fromJson(reader, ConfigLoader.class);
                TypingInChat.LOGGER.info("{} Config loaded successfully.", modID);
            } catch (IOException e) {
                TypingInChat.LOGGER.error("{} Config load error.", modID);
                e.printStackTrace();
            }
        }
        if (instance == null) {
            instance = new ConfigLoader();
            instance.save(configDir); // Save defaults if config file doesn't exist
        }
        return instance;
    }

    // Save the config to the file
    public void save(Path configDir) {
        File configFile = configDir.resolve(CONFIG_FILE_NAME).toFile();
        try (Writer writer = new FileWriter(configFile)) {
            GSON.toJson(this, writer);
            TypingInChat.LOGGER.info("{} Config saved successfully.", modID);
        } catch (IOException e) {
            TypingInChat.LOGGER.error("{} Failed to save config file!", modID);
            e.printStackTrace();
        }
    }

    public void reload(Path configDir) {
        File configFile = configDir.resolve(CONFIG_FILE_NAME).toFile();
        if (configFile.exists()) {
            try (Reader reader = new FileReader(configFile)) {
                ConfigLoader newConfig = GSON.fromJson(reader, ConfigLoader.class);

                // Overwrite current instance values
                this.enableMod = newConfig.enableMod;
                this.ignoreCommands = newConfig.ignoreCommands;
                this.debug = newConfig.debug;
                TypingInChat.LOGGER.info("{} Config reloaded successfully.", modID);
            } catch (IOException e) {
                TypingInChat.LOGGER.error("{} Failed to reload config", modID);
                e.printStackTrace();
            }
        } else {
            TypingInChat.LOGGER.error("{} Config file not found!", modID);
        }
    }

    public static ConfigLoader getInstance() {
        return instance;
    }
}
