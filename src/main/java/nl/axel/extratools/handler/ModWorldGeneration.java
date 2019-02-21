package nl.axel.extratools.handler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import nl.axel.extratools.ExtraTools;
import nl.axel.extratools.init.ModBlocks;

import java.util.Random;

public class ModWorldGeneration implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()){
            case 0:
                //ModToolMaterials.logger.error("CASE 0 TRUE");

                generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
                break;

        }
    }

    private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){
        //ModToolMaterials.logger.error("GENERATING COPPER");
        generateOre(ModBlocks.copperOre.getDefaultState(), world, random, chunkX*16, chunkZ*16, 16, 64, 4 + random.nextInt(3), 20);
        //ModToolMaterials.logger.error("GENERATING TIN");
        generateOre(ModBlocks.tinOre.getDefaultState(), world, random, chunkX*16, chunkZ*16, 16, 64, 2 + random.nextInt(3), 20);
        //ModToolMaterials.logger.error("DONE!");

    }

    private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) {

        for (int i = 0; i < chances; i++) {
            BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(maxY-minY), z + random.nextInt(16));

            WorldGenMinable generator = new WorldGenMinable(ore, size, BlockMatcher.forBlock(Blocks.STONE));
            generator.generate(world, random, pos);
        }
    }


}
