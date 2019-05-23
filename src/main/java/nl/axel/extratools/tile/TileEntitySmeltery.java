package nl.axel.extratools.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import nl.axel.extratools.ExtraTools;


public class TileEntitySmeltery extends TileEntity {

    private int lavacounter = 0;
    private final static int maxLava = 5;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("lavacount", lavacounter);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.lavacounter = compound.getInteger("lavacount");
        super.readFromNBT(compound);
    }


    public int getLavaCount(){
        return lavacounter;
    }

    public boolean addLavacount() {
        if (lavacounter < maxLava) {
            lavacounter++;
            return true;
        } else {
            return false;
        }
    }

    public boolean remLavacount(){

        ExtraTools.logger.error("Trying to empty!");

        if (lavacounter > 0) {
            lavacounter--;
            return true;
        }else{
            return false;
        }
    }


}
