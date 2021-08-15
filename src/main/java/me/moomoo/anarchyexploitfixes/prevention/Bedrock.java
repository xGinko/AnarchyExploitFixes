package me.moomoo.anarchyexploitfixes.prevention;

import lombok.RequiredArgsConstructor;
import me.moomoo.anarchyexploitfixes.Main;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

@RequiredArgsConstructor
public class Bedrock implements Listener {
    private final Main plugin;

    @EventHandler
    private void onChunkLoadEvent(ChunkLoadEvent evt) {
        FileConfiguration config = plugin.getConfig();

        if (config.getBoolean("FillInBedrockFloor")) {
            Chunk c = evt.getChunk();
            if (!evt.isNewChunk()) {
                if (!c.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                    int cx = c.getX() << 4;
                    int cz = c.getZ() << 4;
                    for (int x = cx; x < cx + 16; x++) {
                        for (int z = cz; z < cz + 16; z++) {
                            for (int y = 0; y < 1; y++) {
                                if (c.getBlock(x, y, z).getType() != Material.BEDROCK) {
                                    c.getBlock(x, y, z).setType(Material.BEDROCK);
                                }
                            }
                        }
                    }
                }
            }
        }

        if (config.getBoolean("FillInBedrockRoof")) {
            Chunk c = evt.getChunk();
            if (!evt.isNewChunk()) {
                if (c.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                    int cx = c.getX() << 4;
                    int cz = c.getZ() << 4;
                    for (int x = cx; x < cx + 16; x++) {
                        for (int z = cz; z < cz + 16; z++) {
                            for (int y = 0; y < 128; y++) {
                                if (y == 127) {
                                    if (c.getBlock(x, y, z).getType() != Material.BEDROCK) {
                                        c.getBlock(x, y, z).setType(Material.BEDROCK);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
