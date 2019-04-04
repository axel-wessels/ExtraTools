package nl.axel.extratools.blocks;


import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nl.axel.extratools.init.ModItems;
import nl.axel.extratools.tile.TileEntityForge;
import nl.axel.extratools.tile.TileEntitySmeltery;

import javax.annotation.Nullable;
import java.util.Random;


public class BlockSmeltery extends BlockTileEntity<TileEntitySmeltery> {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public BlockSmeltery(){
        super(Material.ROCK, "smeltery");

        this.setDefaultState(this.getBlockState().getBaseState().withProperty(FACING, EnumFacing.NORTH));
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
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        EnumFacing entityFacing = placer.getHorizontalFacing();

        if(!world.isRemote) {
            if(entityFacing == EnumFacing.NORTH) {
                entityFacing = EnumFacing.SOUTH;
            } else if(entityFacing == EnumFacing.EAST) {
                entityFacing = EnumFacing.WEST;
            } else if(entityFacing == EnumFacing.SOUTH) {
                entityFacing = EnumFacing.NORTH;
            } else if(entityFacing == EnumFacing.WEST) {
                entityFacing = EnumFacing.EAST;
            }

            world.setBlockState(pos, state.withProperty(FACING, entityFacing), 2);
        }
    }
}
