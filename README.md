![TiCMOD](https://github.com/user-attachments/assets/287dba0c-cfcd-4015-bcd2-4df4f6781f4e)
# Typing In Chat Mod
Client-side part for [TypingInChat-Plugin](https://github.com/Orphey98/TypingInChat-Plugin) allows server to know when you are actively typing in chat. <br><br>
![demo1](https://github.com/user-attachments/assets/ee3bb3ba-be4f-4c08-ab99-f4925e1140a0)

## Installation
You need to put typinginchat-fabric.jar into your mods folder. Also, you need to install [Fabric API](https://modrinth.com/mod/fabric-api). <br>

## Options
Mod creates small config file that you can edit.

```yml
config/typinginchat-config.json

#You can disable mod if you have problems on certain servers
"enableMod": true
#Should commands input be ignored or not
"ignoreCommands": true
#Enable debug-mode. Shows when plugin detects chat activity and players nearby.
"debug": false
```
Config values can be updated with _/typinginchatmod reload_ command.

## Related
Link for server-side plugin 
(required for servers):
[TypingInChat-Plugin](https://github.com/Orphey98/TypingInChat-Plugin)
