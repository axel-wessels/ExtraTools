package nl.axel.extratools.tile;

import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;


public class TileEntitySmeltery extends TileEntity {

    private int lavacounter = 0;
    private final static int maxLava = 5;


    public int getLavaCount(){
        return lavacounter;
    }

    public void addLavacount(){
        lavacounter++;
    }

    public void remLavacount(){
        lavacounter--;
    }


}
