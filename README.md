# smo-practice-server
 This is a cross-platform Java server for the [practice mod](https://github.com/fruityloops1/smo-practice/) for Super Mario Odyssey, made by Fruityloops.
 
 ## Commands
 ### tp &lt;x&gt; &lt;y&gt; &lt;z&gt;
 Specify three  `float` values to teleport Mario within the stage to the given location.
 
 Example: `tp 100 20.4 4000`
 
 ### go &lt;stage name&gt; &lt;scenario&gt; &lt;entrance&gt; &lt;start script&gt;
 Teleport Mario to a different stage. 
 - The stage name has to be the file name of a file from within the `StageData`-romfs directory (without `Map.szs`). This input will be validated before sending to the server, throwing an error message when trying to teleport to an invalid stage. Be careful when teleporting directly to a subarea of a different kingdom, the game will likely crash after a few seconds.
 - The scenario has to be a number between `1` and `15`, being the one-indexed scenario number. See [here](https://docs.google.com/spreadsheets/d/1jZLsqrkyUCxXHCVHuWsm2-Ec1gbJwLCLKTw8iMBzbUk/) for a list of common scenario numbers.
 - The entrance has to be an entry of `ChangeStageId` from the stage file. Check [here](https://cdn.discordapp.com/attachments/829263827727286292/941074444988854272/entrances.txt) for a semi-complete list of entrances (does not contain nested entries).
 - `start script` has to be either `true` or `false` (defaulting to `false`), and shows whether to start a loaded script instantly after warping. The script has to be loaded using `script` beforehand.
 
 Example: `go SnowWorldHomeStage 3 Come true` (enter post-game snow world through the painting next to snow dram)

 ### script &lt;file&gt;
 The specified file will be loaded as script file and sent to the server, including the file name. It can either denote a relative path to the working directory, or an absolute path.
 
 Example: `script script0-1.txt`
 
 ### page &lt;index&gt;
 Change to the specified page number, being 0-indexed.
 
 Example: `page 5` (switch to TAS information, 6/9)
 
