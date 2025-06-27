package net.utils.betapatches;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BetaPatches extends JavaPlugin {

    private final FreecamDupePatch freecamDupePatch = new FreecamDupePatch();
    private final ChunkCrashPatch chunkCrashPatch = new ChunkCrashPatch();
    private final PistonDupePatch pistonDupePatch = new PistonDupePatch();
    private final NoBedrock noBedrock = new NoBedrock();
    private final NoIllegals noIllegals = new NoIllegals();

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvent(Event.Type.PLAYER_INTERACT, freecamDupePatch, Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_BREAK, freecamDupePatch, Priority.Normal, this);

        pm.registerEvent(Event.Type.PLAYER_INTERACT, chunkCrashPatch, Priority.Normal, this);
        pm.registerEvent(Event.Type.VEHICLE_CREATE, chunkCrashPatch, Priority.Normal, this);

        pm.registerEvent(Event.Type.BLOCK_PLACE, pistonDupePatch, Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_PLACE, noBedrock, Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_PLACE, noIllegals, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_JOIN, noIllegals, Priority.Normal, this);

        getCommand("kill").setExecutor(new KillCommand());

        System.out.println("[BetaPatches] All patches enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println("[BetaPatches] Plugin disabled.");
    }
}