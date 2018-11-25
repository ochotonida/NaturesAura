package de.ellpeck.naturesaura.blocks.tiles;

import de.ellpeck.naturesaura.api.aura.chunk.IAuraChunk;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;

public class TileEntityAuraDetector extends TileEntityImpl implements ITickable {

    public int redstonePower;

    @Override
    public void update() {
        if (!this.world.isRemote && this.world.getTotalWorldTime() % 80 == 0) {
            int amount = IAuraChunk.getAuraInArea(this.world, this.pos, 30);
            int power = MathHelper.clamp(MathHelper.ceil(amount / (IAuraChunk.DEFAULT_AURA * 2F) * 15F), 0, 15);
            if (this.redstonePower != power) {
                this.redstonePower = power;
                this.world.updateComparatorOutputLevel(this.pos, this.getBlockType());
            }
        }
    }
}
