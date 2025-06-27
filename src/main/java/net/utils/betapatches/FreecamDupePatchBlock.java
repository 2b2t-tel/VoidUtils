package net.utils.betapatches;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;

import java.util.HashMap;

public class FreecamDupePatchBlock extends BlockListener {

    private final HashMap<String, Long> lastChestUse;

    public FreecamDupePatchBlock(HashMap<String, Long> lastChestUse) {
        this.lastChestUse = lastChestUse;
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        if (block.getType() == Material.CHEST) {
            Long lastUse = lastChestUse.get(player.getName());
            if (lastUse != null) {
                long diff = System.currentTimeMillis() - lastUse;
                if (diff < 500) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
