package net.utils.betapatches;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class BetaPatches extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();

        HashMap<String, Long> lastChestUse = new HashMap<String, Long>();
        pm.registerEvent(Event.Type.PLAYER_INTERACT, new FreecamDupePatchPlayer(lastChestUse), Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_BREAK, new FreecamDupePatchBlock(lastChestUse), Priority.Normal, this);

        pm.registerEvent(Event.Type.PLAYER_INTERACT, new ChunkCrashPatch(), Priority.Normal, this);

        pm.registerEvent(Event.Type.BLOCK_PLACE, new PistonDupePatch(), Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_PLACE, new NoBedrock(), Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_PLACE, new NoIllegals.PlaceListener(), Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_JOIN, new NoIllegals.JoinListener(), Priority.Normal, this);

        getCommand("kill").setExecutor(new KillCommand());

        System.out.println("[BetaPatches] All patches enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println("[BetaPatches] Plugin disabled.");
    }
}