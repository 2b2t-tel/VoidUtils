package net.utils.voidutils;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import java.io.File;
import java.util.HashMap;

public class VoidUtils extends JavaPlugin {
    public static VoidUtils instance;
    public static long serverStartTime;

    public static boolean BOAT_ONLY_WATER = true;
    public static boolean PATCH_NO_BEDROCK = true;
    public static boolean PATCH_NO_ILLEGALS = true;
    public static boolean PATCH_PISTON_DUPE = true;
    public static boolean ENABLE_KILL_COMMAND = true;
    public static boolean PATCH_NEGATIVE_ITEMS = true;
    public static boolean PATCH_SCAN_NEGATIVE_CONTAINERS = true;
    public static boolean PATCH_ANTI_VOID_FALL = true;

    public static boolean WEBSITE_ENABLED = true;
    public static int WEBSITE_PORT = 8080;
    public static String WEBSITE_TITLE = "2b2t";
    public static String WEBSITE_MESSAGE = "";

    private Configuration config;

    @Override
    public void onEnable() {
        instance = this; // ← Add this line
        loadConfiguration();

        PluginManager pm = getServer().getPluginManager();
        HashMap<String, Long> lastChestUse = new HashMap<String, Long>();

        serverStartTime = System.currentTimeMillis();
        getCommand("uptime").setExecutor(new UptimeCommand());


        pm.registerEvent(Event.Type.PLAYER_INTERACT, new FreecamDupePatchPlayer(lastChestUse), Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_BREAK, new FreecamDupePatchBlock(lastChestUse), Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_BREAK, new AntiFastBreak(), Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_JOIN, new NegativeItemScan(), Priority.Normal, this);

        if (WEBSITE_ENABLED) {
            Website.startServer(WEBSITE_PORT);
        }

        if (BOAT_ONLY_WATER) {
            pm.registerEvent(Event.Type.PLAYER_INTERACT, new ChunkCrashPatch(), Priority.Normal, this);
        }

        if (PATCH_PISTON_DUPE) {
            pm.registerEvent(Event.Type.BLOCK_PLACE, new PistonDupePatch(), Priority.Normal, this);
        }

        if (PATCH_NO_BEDROCK) {
            pm.registerEvent(Event.Type.BLOCK_PLACE, new NoBedrock(), Priority.Normal, this);
        }

        if (PATCH_NO_ILLEGALS) {
            pm.registerEvent(Event.Type.BLOCK_PLACE, new NoIllegals.PlaceListener(), Priority.Normal, this);
            pm.registerEvent(Event.Type.PLAYER_JOIN, new NoIllegals.JoinListener(), Priority.Normal, this);
        }

        if (ENABLE_KILL_COMMAND) {
            getCommand("kill").setExecutor(new KillCommand());
        }

        if (PATCH_NEGATIVE_ITEMS) {
            pm.registerEvent(Event.Type.BLOCK_PLACE, new NoNegativeItems.PlaceListener(), Priority.Normal, this);
            pm.registerEvent(Event.Type.PLAYER_JOIN, new NoNegativeItems.JoinListener(), Priority.Normal, this);
        }

        if (PATCH_SCAN_NEGATIVE_CONTAINERS) {
            pm.registerEvent(Event.Type.PLAYER_INTERACT, new NegativeItemScan(), Priority.Normal, this);
        }

        if (PATCH_ANTI_VOID_FALL) {
            pm.registerEvent(Event.Type.PLAYER_MOVE, new AntiVoidFall(), Event.Priority.Lowest, this);
        }

        System.out.println("[VoidUtils] Enabled patches: " +
                (BOAT_ONLY_WATER ? "BoatWater " : "") +
                (PATCH_NO_BEDROCK ? "NoBedrock " : "") +
                (PATCH_NO_ILLEGALS ? "NoIllegals " : "") +
                (PATCH_PISTON_DUPE ? "PistonDupe " : "") +
                (ENABLE_KILL_COMMAND ? "KillCommand " : "") +
                (PATCH_NEGATIVE_ITEMS ? "NegativeItems" : "")
        );


    }

    private void loadConfiguration() {
        config = getConfiguration();
        config.load();

        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            config.setProperty("patches.boat-placement-only-water", true);
            config.setProperty("patches.no-bedrock", true);
            config.setProperty("patches.no-illegals", true);
            config.setProperty("patches.piston-dupe", true);
            config.setProperty("patches.kill-command", true);
            config.setProperty("patches.negative-items", true);
            config.setProperty("scan-negative-container-items", true);
            config.setProperty("patches.anti-void-fall", true);

            // Website settings
            config.setProperty("website.enabled", true);
            config.setProperty("website.port", 8080);
            config.setProperty("website.title", "2b2t");
            config.setProperty("website.message", "");

            config.save();
        }

        BOAT_ONLY_WATER    = config.getBoolean("patches.boat-placement-only-water", true);
        PATCH_NO_BEDROCK   = config.getBoolean("patches.no-bedrock", true);
        PATCH_NO_ILLEGALS  = config.getBoolean("patches.no-illegals", true);
        PATCH_PISTON_DUPE  = config.getBoolean("patches.piston-dupe", true);
        ENABLE_KILL_COMMAND = config.getBoolean("patches.kill-command", true);
        PATCH_NEGATIVE_ITEMS = config.getBoolean("patches.negative-items", true);
        PATCH_SCAN_NEGATIVE_CONTAINERS = config.getBoolean("patches.scan-negative-container-items", true);
        PATCH_ANTI_VOID_FALL = config.getBoolean("patches.anti-void-fall", true);

        WEBSITE_ENABLED = config.getBoolean("website.enabled", true);
        WEBSITE_PORT = config.getInt("website.port", 8080);
        WEBSITE_TITLE = config.getString("website.title", "2b2t");
        WEBSITE_MESSAGE = config.getString("website.message", "");
    }

    @Override
    public void onDisable() {
        System.out.println("[VoidUtils] Plugin disabled.");
    }
}
