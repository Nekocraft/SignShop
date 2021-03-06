package org.wargamer2010.signshop.util;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.material.Lever;
import org.bukkit.material.MaterialData;
import org.wargamer2010.signshop.configuration.SignShopConfig;

public class lagSetter implements Runnable{
    private final Block blockToChange;        

    public lagSetter(Block blockToChange){
        this.blockToChange = blockToChange;
    }

    @Override
    public void run(){
        if(blockToChange.getType() == Material.getMaterial("LEVER")) {
            // Best effort, load 2 chunks around the block in the hope it's enough
            itemUtil.loadChunkByBlock(blockToChange, SignShopConfig.getChunkLoadRadius());
            BlockState state = blockToChange.getState();
            MaterialData data = state.getData();
            Lever lever = (Lever)data;
            if(lever.isPowered()) {
                lever.setPowered(false);
                state.setData(lever);
                state.update();                    
            }                    
        }            
    }
}
