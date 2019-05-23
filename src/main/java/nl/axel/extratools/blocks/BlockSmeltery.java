package nl.axel.extratools.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import nl.axel.extratools.ExtraTools;
import nl.axel.extratools.init.ModItems;
import nl.axel.extratools.tile.TileEntitySmeltery;
import nl.axel.extratools.util.Names;

import javax.annotation.Nullable;


public class BlockSmeltery extends BlockTileEntity<TileEntitySmeltery> {

    public static final String PROPERTY_NAME = "facing_active";
    public static final PropertyInteger PROPERTY = PropertyInteger.create(PROPERTY_NAME, 0, 7);


    public BlockSmeltery(){
        super(Material.ROCK, Names.Blocks.SMELTERY);

        this.setDefaultState(this.getBlockState().getBaseState().withProperty(PROPERTY, 0));
    }


    /*
     * TODO: Give items according to input if lava available
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            //get the smeltery tile entity from the world
            TileEntitySmeltery tile = getTileEntity(world, pos);

            //make the texture value variable
            int textureValue;

            //checks if player hand is NOT empty
            if (!player.getHeldItem(hand).isEmpty()) {

                //checks if the held items is a lava bucket
                if (player.getHeldItem(hand).getItem() == Items.LAVA_BUCKET) {

                    //do the lavacount check and remove lava if lava is added in the tile.
                    if (tile.addLavacount()) {

                        tile.markDirty();

                        //empties the hand and adds an empty bucket to the inventory
                        player.setHeldItem(hand, ItemStack.EMPTY);
                        player.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET, 1));

                        if (tile.getLavaCount() == 1 && (state.getValue(PROPERTY) < 4)) {
                            textureValue = state.getValue(PROPERTY);
                            world.setBlockState(pos, state.withProperty(PROPERTY, textureValue + 4));
                        }

                        //lavacount > 4
                    } else {
                        player.sendMessage(new TextComponentString("Smeltery is full of lava"));
                    }

                } else {
                    Item heldItem = player.getHeldItem(hand).getItem();
                    Item returnItem = null;
                    switch (heldItem.getUnlocalizedName()) {
                        case "item." + Names.Items.DIAMOND_PICKAXEHEAD:
                            returnItem = ModItems.obsidian_pickaxehead;
                            break;
                        case "item." + Names.Items.DIAMOND_AXEHEAD:
                            returnItem = ModItems.obsidian_axehead;
                            break;
                        case "item." + Names.Items.DIAMOND_HOEHEAD:
                            returnItem = ModItems.obsidian_hoehead;
                            break;
                        case "item." + Names.Items.DIAMOND_SHOVELHEAD:
                            returnItem = ModItems.obsidian_shovelhead;
                            break;
                        case "item." + Names.Items.DIAMOND_SWORDBLADE:
                            returnItem = ModItems.obsidian_swordblade;
                            break;
                    }

                    //return returnItem

                    if (tile.getLavaCount() > 0) {
                        if (returnItem != null) {//if not returnItem == null
                            ItemStack current = player.getHeldItem(hand);
                            player.setHeldItem(hand, new ItemStack(current.getItem(), current.getCount() - 1));
                            player.addItemStackToInventory(new ItemStack(returnItem, 1));
                            if (!tile.remLavacount()) {
                                ExtraTools.logger.error("Tried to remove lava while lavacount < 1");
                            }else{
                                tile.markDirty();
                            }
                            if (tile.getLavaCount() == 0) {
                                textureValue = state.getValue(PROPERTY);
                                world.setBlockState(pos, state.withProperty(PROPERTY, textureValue - 4));
                            }
                        } else {
                            player.sendMessage(new TextComponentString("Not a valid item"));
                        }
                    } else {
                        player.sendMessage(new TextComponentString("No lava in the smeltery!"));
                    }
                }
            } else {
                ExtraTools.logger.error("Empty hand: " + tile.getLavaCount());
                player.sendMessage(new TextComponentString("Lava buckets added: " + tile.getLavaCount()));

            }
        }

        return true;

    }

    @Override
    public Class<TileEntitySmeltery> getTileEntityClass() {
        return TileEntitySmeltery.class;
    }

    @Nullable
    @Override
    public TileEntitySmeltery createTileEntity(World world, IBlockState state) {
        return new TileEntitySmeltery();
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
}