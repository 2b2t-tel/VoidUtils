package net.utils.voidutils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

import java.util.HashMap;

public class FreecamDupePatchPlayer extends PlayerListener {

    private final HashMap<String, Long> lastChestUse;

    public FreecamDupePatchPlayer(HashMap<String, Long> lastChestUse) {
        this.lastChestUse = lastChestUse;
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event == null || event.getClickedBlock() == null) return;

        Block block = event.getClickedBlock();
        Player player = event.getPlayer();

        if (block.getType() == Material.CHEST) {
            double distance = player.getLocation().distance(block.getLocation());
            if (distance > 6.0) {
                event.setCancelled(true);
                return;
            }
            lastChestUse.put(player.getName(), System.currentTimeMillis());
        }
    }
}
