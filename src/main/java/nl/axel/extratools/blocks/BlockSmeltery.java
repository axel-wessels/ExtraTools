package nl.axel.extratools.blocks;


import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import nl.axel.extratools.tile.TileEntitySmeltery;


public class BlockSmeltery extends BlockTileEntity<TileEntitySmeltery> {

    public BlockSmeltery(){ super(Material.ROCK, "smeltery"); }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

        TileEntitySmeltery tile = getTileEntity(world, pos);

        if (player.getHeldItem(hand).getItem() == Items.LAVA_BUCKET) {
            tile.addLavacount();

        }else{
            player.sendMessage(new TextComponentString("Only takes lavabuckets!"));
        }

    }
}
