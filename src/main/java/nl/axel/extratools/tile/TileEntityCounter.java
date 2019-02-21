package nl.axel.extratools.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCounter extends TileEntity {

    private long count;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setLong("count", count);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        count = compound.getLong("count");
        super.readFromNBT(compound);
    }

    public long getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
        markDirty();
    }

    public void decrementCount() {
        count--;
        markDirty();
    }

    public void doubleCount() {
        count = count*2;
        markDirty();
    }

    public void halfCount(){
        count = count/2;
        markDirty();
    }

}
