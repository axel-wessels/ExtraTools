package nl.axel.extratools.blocks;


import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import nl.axel.extratools.init.ModItems;
import nl.axel.extratools.tile.TileEntitySmeltery;
import nl.axel.extratools.util.Names;

import javax.annotation.Nullable;


public class BlockSmeltery extends BlockTileEntity<TileEntitySmeltery> {

    public static final String PROPERTY_NAME = "facing";
    public static final PropertyInteger PROPERTY = PropertyInteger.create(PROPERTY_NAME, 0, 3);


    public BlockSmeltery(){
        super(Material.ROCK, Names.Blocks.SMELTERY);

        this.setDefaultState(this.getBlockState().getBaseState().withProperty(PROPERTY, 0));
    }


    /*
     * TODO: Give items according to input if lava available
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

        //get the smeltery tile entity from the world
        TileEntitySmeltery tile = getTileEntity(world, pos);

        //checks if player hand is NOT empty
        if (!player.getHeldItem(hand).isEmpty()) {


            //LAVA CHECK
            //checks if the held items is a lava bucket
            if (player.getHeldItem(hand).getItem() == Items.LAVA_BUCKET) {

                //empties the hand and adds an empty bucket to the inventory
                player.setHeldItem(hand, ItemStack.EMPTY);
                player.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET, 1));

                tile.addLavacount();
                //ToDo right item
            }else if(player.getHeldItem(hand).getItem() == Items.DIAMOND_PICKAXE && tile.getLavaCount() > 0){
                player.setHeldItem(hand, new ItemStack(ModItems.obsidian_pickaxehead, 1));
                tile.remLavacount();
            }else{
                player.sendMessage(new TextComponentString("Not a valid item" ));
            }
        } else {
            player.sendMessage(new TextComponentString("Lava buckets added: " + tile.getLavaCount() ));

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
