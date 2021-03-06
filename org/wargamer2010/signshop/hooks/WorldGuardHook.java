package org.wargamer2010.signshop.hooks;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.util.Map;

public class WorldGuardHook implements Hook  {

    @Override
    public String getName() {
        return "WorldGuard";
    }

    @Override
    public Boolean canBuild(Player player, Block block) {
        if(HookManager.getHook("WorldGuard") == null)
            return true;
        WorldGuardPlugin WG = (WorldGuardPlugin)HookManager.getHook("WorldGuard");
        return WG.canBuild(player, block);
    }
}
