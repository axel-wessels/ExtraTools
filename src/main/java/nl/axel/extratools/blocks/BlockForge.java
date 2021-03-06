package nl.axel.extratools.blocks;


import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import nl.axel.extratools.ExtraTools;
import nl.axel.extratools.init.ModItems;
import nl.axel.extratools.tile.TileEntityForge;
import nl.axel.extratools.util.Names;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockForge extends BlockTileEntity<TileEntityForge> {

    // 0 = north false
    // |
    // V
    // 7 = west true
    public static final String PROPERTY_NAME = "facing_active";
    public static final PropertyInteger PROPERTY = PropertyInteger.create(PROPERTY_NAME, 0, 7);


    public BlockForge(){
        super(Material.ROCK, Names.Blocks.FORGE);
        this.setTickRandomly(true);
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(PROPERTY, 1));
    }

    /*
    * written on 17-01-19 by Axel and Bram on version 0.1
    *
    * this function triggers when you click on the block
    *
    * */

    /*
     * Done S T lock the inventory while working
     * Done S T lock the inventory to one bronze alloy
     * Done A T give a message when clicked while working or wrong item
     */

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

        switch (player.getHeldItem(hand).getItem().getUnlocalizedName()){
            case Names.Items.DIAMOND_PICKAXEHEAD:
                //replace item
                break;
            case Names.Items.DIAMOND_SHOVELHEAD:
                //replace item
                break;

        }


        //checks if the world is local or not
        if (!world.isRemote) {

            //gets the tile entity from the world position
            TileEntityForge tile = getTileEntity(world, pos);

            //checks if the tile is capable of handling items
            IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);

            //checks if the player is sneaking
            if (!player.isSneaking()) {
                if(!tile.getWorking()) {
                    ExtraTools.logger.error(tile.getWorking());

                    //checks if the players hand is empty
                    if (player.getHeldItem(hand).isEmpty()) {

                        //if player hand is empty and the player is not sneaking: gets the items from the tile and puts in in the hand of the player
                        player.setHeldItem(hand, itemHandler.extractItem(0, 64, false));

                    } else {
                        //checks if held items is bronze alloy
                        if (player.getHeldItem(hand).getItem() == ModItems.bronze_alloy) {
                            //if player hand is not empty, contains bronze alloy and the player is not sneaking: puts the item from the hand into the tile
                            itemHandler.insertItem(0, player.getHeldItem(hand).splitStack(1), false);
                        }else {
                            player.sendMessage(new TextComponentString("This machine only accepts bronze alloys."));
                        }
                    }

                    //makes sure the block is updated
                    tile.markDirty();
                } else {
                    ExtraTools.logger.error(tile.getWorking());
                    player.sendMessage(new TextComponentString("This forge is currently in use!"));
                }


            } else {

                //get the stack from the tileEntity
                ItemStack stack = itemHandler.getStackInSlot(0);

                //if the stack is not empty
                if (!stack.isEmpty()) {

                    //if player is sneaking and the stack is not empty: converts the name of the item and displays it in the chat
                    String localized = ExtraTools.proxy.localize(stack.getUnlocalizedName() + ".name");
                    player.sendMessage(new TextComponentString(stack.getCount() + "x " + localized));

                } else {

                    //if player is sneaking and the stack is empty: display Empty in the chat
                    player.sendMessage(new TextComponentString("Empty"));
                }
            }
        }
        return true;
    }


    /*
     * Made by Axel and Bram on 17-01-19 version 0.1
     *
     * On block Broken Drop Items
     *
     */
    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        //gets the tileEntity from the world
        TileEntityForge tile = getTileEntity(world, pos);
        //checks if the tile is capable of handling items
        IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        //gets the item from the tile
        ItemStack stack = itemHandler.getStackInSlot(0);
        //checks if the itemstack from the tile is not empty
        if (!stack.isEmpty()) {
            //if itemstack is not empty: drop new Item on the ground with same data
            EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            world.spawnEntity(item);
        }

        //breaks the block on pos
        super.breakBlock(world, pos, state);
    }



    @Override
    public Class<TileEntityForge> getTileEntityClass() {
        return TileEntityForge.class;
    }

    @Nullable
    @Override
    public TileEntityForge createTileEntity(World world, IBlockState state) {
        return new TileEntityForge();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, PROPERTY);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(PROPERTY);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(PROPERTY, meta);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        EnumFacing entityFacing = placer.getHorizontalFacing();
        int facing = 0;

        if(!world.isRemote) {

            if(entityFacing == EnumFacing.NORTH) {
                //south
                facing = 2;
            } else if(entityFacing == EnumFacing.EAST) {
                //west
                facing = 3;
            } else if(entityFacing == EnumFacing.SOUTH) {
                //north
                facing = 0;
            } else if(entityFacing == EnumFacing.WEST) {
                //east
                facing = 1;
            }

            world.setBlockState(pos, state.withProperty(PROPERTY, facing), 2);
        }
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        //if (!worldIn.isRemote) {

            TileEntityForge forge = (TileEntityForge) worldIn.getTileEntity(pos);
            int facing = worldIn.getBlockState(pos).getValue(PROPERTY);

            if (rand.nextInt(2) == 0) {
                //ExtraTools.logger.error(pos.getX() + " = X, " + pos.getY() + " = Y, " + pos.getZ() + " = Z");
                ExtraTools.logger.error(forge.getWorking());
            }

            if (rand.nextInt(8) == 0) {
                if (forge.getWorking()) {
                    ExtraTools.logger.error("Working!");
                    if (facing < 4) {
                        ExtraTools.logger.error("<4");
                        worldIn.setBlockState(pos, stateIn.withProperty(PROPERTY, facing + 4));
                    }


                    //worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D, 0.0D, 0.1D, 0.0D, new int[0]);
                    //worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D, 0.0D, 0.1D, 0.0D, new int[0]);
                    //worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D, 0.0D, 0.1D, 0.0D, new int[0]);
                } else if (facing > 3) {
                    worldIn.setBlockState(pos, stateIn.withProperty(PROPERTY, facing - 4));
                }
            }

        //}
    }

    @Override
    public boolean isTopSolid(IBlockState state) {
        return super.isTopSolid(state);
    }
}
