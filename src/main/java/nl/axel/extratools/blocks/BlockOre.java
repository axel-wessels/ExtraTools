package nl.axel.extratools.blocks;

import net.minecraft.block.material.Material;

public class BlockOre extends BlockBasic {
    public BlockOre(String name) {
        super(Material.ROCK, name);

        setHardness(1.5f);
        setResistance(15f);

    }
}
