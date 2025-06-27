package net.utils.betapatches;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class AntiFastBreak extends BlockListener {

    private final HashMap<String, Long> lastBreak = new HashMap<String, Long>();
    private static final long MIN_BREAK_DELAY = 150L; // 150 ms (6-7 blocks/sec max)

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        long now = System.currentTimeMillis();
        long last = lastBreak.getOrDefault(player.getName(), 0L);
        long delta = now - last;

        if (delta < MIN_BREAK_DELAY) {
            event.setCancelled(true);
            // Optional: silently cancel or log
            return;
        }

        lastBreak.put(player.getName(), now);
    }
}