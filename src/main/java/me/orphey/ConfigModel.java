package me.orphey;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;

@Modmenu(modId = "typinginchatmod")
@Config(name = "tcm-config", wrapperName = "TCMConfig")
public class ConfigModel {
    public boolean ignoreCommands = true;
    public boolean ignoreChat = true;
}
