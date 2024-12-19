package me.orphey;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;

@Modmenu(modId = "typinginchat")
@Config(name = "tcm-config", wrapperName = "TCMConfig")
public class ConfigModel {
    public boolean enableMod = true;
    public boolean showCommands = true;
}
