![TiCMOD](https://github.com/user-attachments/assets/77c4c5f7-5780-4447-aa75-701b246576f6)
# Typing In Chat Mod
Client-side part for [TypingInChat-Plugin](https://github.com/Orphey98/TypingInChat-Plugin) allows server to know when you are actively typing in chat. Plugin should be installed on server for chat indicators to work. Mod required to show **your** chat indicator to other players and **not** required to see indicators from other players. <br><br>
![demo1](https://github.com/user-attachments/assets/ee3bb3ba-be4f-4c08-ab99-f4925e1140a0)

## Installation
**This is Fabric & Quilt repository.** </br>
For Forge version go [HERE](https://github.com/Orphey98/TypingInChat-Mod-Forge)

|  | Download appropriate version for your Fabric or Quilt client |
| ------ | ------ |
| 1.19 | [1.19.4](https://github.com/Orphey98/TypingInChat-Mod/releases/download/1.19/typinginchat-fabric-1.19.4-1.0.0.jar) |
| 1.20 | [1.20](https://github.com/Orphey98/TypingInChat-Mod/releases/download/1.20/typinginchat-fabric-1.20-1.0.0.jar), [1.20.1](https://github.com/Orphey98/TypingInChat-Mod/releases/download/1.20.1/typinginchat-fabric-1.20.1-1.0.0.jar), [1.20.2](https://github.com/Orphey98/TypingInChat-Mod/releases/download/1.20.2/typinginchat-fabric-1.20.2-1.0.0.jar), [1.20.3](https://github.com/Orphey98/TypingInChat-Mod/releases/download/1.20.3/typinginchat-fabric-1.20.3-1.0.0.jar), [1.20.4](https://github.com/Orphey98/TypingInChat-Mod/releases/download/1.20.4/typinginchat-fabric-1.20.4-1.0.0.jar), [1.20.5](https://github.com/Orphey98/TypingInChat-Mod/releases/download/1.20.5/typinginchat-fabric-1.20.5-1.0.0.jar), [1.20.6](https://github.com/Orphey98/TypingInChat-Mod/releases/download/1.20.6/typinginchat-fabric-1.20.6-1.0.0.jar) |
| 1.21 | [1.21](https://github.com/Orphey98/TypingInChat-Mod/releases/download/1.21/typinginchat-fabric-1.21-1.0.0.jar), [1.21.1](https://github.com/Orphey98/TypingInChat-Mod/releases/download/1.21.1/typinginchat-fabric-1.21.1-1.0.0.jar), [1.21.2](https://github.com/Orphey98/TypingInChat-Mod/releases/download/1.21.2/typinginchat-fabric-1.21.2-1.0.0.jar), [1.21.3](https://github.com/Orphey98/TypingInChat-Mod/releases/download/1.21.3/typinginchat-fabric-1.21.3-1.0.0.jar), [1.21.4](https://github.com/Orphey98/TypingInChat-Mod/releases/download/1.21.4/typinginchat-fabric-1.21.4-1.0.0.jar) |

Copy mod jar file into your mods folder. Also, you need to install [Fabric API](https://modrinth.com/mod/fabric-api). <br>

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
