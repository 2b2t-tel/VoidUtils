package net.utils.betapatches;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.EnumSet;

public class NoIllegals implements Listener {

    private static final EnumSet<Material> illegalBlocks = EnumSet.of(
            Material.PORTAL,
            Material.FIRE,
            Material.BEDROCK,
            Material.LAVA,
            Material.STATIONARY_LAVA,
            Material.WATER,
            Material.STATIONARY_WATER,
            Material.MOB_SPAWNER,
            Material.DISPENSER,
            Material.PISTON_BASE,
            Material.PISTON_STICKY_BASE
    );

    public void onBlockPlace(BlockPlaceEvent event) {
        if (illegalBlocks.contains(event.getBlockPlaced().getType())) {
            event.setCancelled(true);
        }
    }

    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemStack[] contents = player.getInventory().getContents();
        for (int i = 0; i < contents.length; i++) {
            if (contents[i] != null && illegalBlocks.contains(contents[i].getType())) {
                contents[i] = null;
            }
        }
        player.getInventory().setContents(contents);
    }
}

