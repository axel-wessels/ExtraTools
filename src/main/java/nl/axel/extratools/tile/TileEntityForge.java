package nl.axel.extratools.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import nl.axel.extratools.ExtraTools;
import nl.axel.extratools.init.ModItems;

import javax.annotation.Nullable;

public class TileEntityForge extends TileEntity implements ITickable {

    private boolean isWorking = false;
    private int smeltingTime = 1200;
    private int progress = 0;

    /*
     * Done S T save the progress
     */


    private ItemStackHandler inventory = new ItemStackHandler(1);

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setInteger("progress", progress);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        progress = compound.getInteger("progress");
        if(progress > 0){
            isWorking = true;
        }
        super.readFromNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
    }

    /*
     * Updates the block every tick
     * Converts bronze alloys into bronze ingots
     */

    @Override
    public void update() {
        //Checks if the Item in the slot is a bronze alloy
        if(inventory.getStackInSlot(0).getItem() == ModItems.bronze_alloy){
            //Checks if the forge is processing already
            if(isWorking){
                //Checks if finished, if so set the inventory to 4 bronze ingots and resets
                if(progress < 1){
                    inventory.setStackInSlot(0, new ItemStack(ModItems.bronze_ingot, 4));
                    isWorking = false;
                    progress = 0;
                    ExtraTools.logger.debug("finished!");

                    //else progresses
                }else {
                    progress--;
                   //Todo paticle spawning
                }

                //else starting the progress
            }else{
                isWorking = true;
                progress = smeltingTime;
                ExtraTools.logger.debug("start!");
            }

            //resets if there is no item and progress is being made
        } else if (isWorking) {
            isWorking = false;
            progress = 0;
        }
    }


    //public function to check if the tile is busy
    public boolean getWorking(){
        return isWorking;
    }

    @SideOnly(Side.CLIENT)
    private void SpawnParticle(){
        world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)pos.getX() + 0.5D, (double)pos.getY() + 1.2D, (double)pos.getZ() + 0.5D, (double)1 / 24.0D, 0.0D, 0.0D);
    }


}
