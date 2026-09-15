# carl.Carl project template

This is a project template for a greenfield Java project. It's named carl.Carl. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 25, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 25** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/carl.Carl.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
      ____    _    ____  _     
    / ___|  / \  |  _ \| |    
    | |     / _ \ | |_) | |    
    | |___ / ___ \|  _ <| |___
    \____/_/   \_\_| \_\____|

   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.


Gemini & ChatGPT AI was used for the following:
- JavaDocs
- JavaFX UI
- A-BetterGUI's [commits](https://github.com/Aster0/ip/commit/31484754b8bc298961c3d77eac9fe492ee3f6edd)
- A-Personality's [commits](https://github.com/Aster0/ip/commit/4f126cef58584a7c64b191d1ef9977ce5283b599)
- A-MoreErrorHandling's [commits](https://github.com/Aster0/ip/commit/ec72e8d23a3112303c1e8efa07dacb43795d5641#diff-03039d29ad2982471a56e9044e757297ffb5e4eea4101e23c16894b53604517f) - used to create more errors, snapshots recovering, save file issues.