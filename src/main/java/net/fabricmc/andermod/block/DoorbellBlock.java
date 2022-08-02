package net.fabricmc.andermod.block;

import net.fabricmc.andermod.AnderMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

// TODO: pressed state
public class DoorbellBlock extends HorizontalFacingBlock {
    public static final EnumProperty<WallMountLocation> FACE = Properties.WALL_MOUNT_LOCATION;
    public static final BooleanProperty POWERED = BooleanProperty.of("powered");

    public DoorbellBlock(Settings settings) {
        super(settings);
        // this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(POWERED, false)).with(FACE, WallMountLocation.WALL));
        this.setDefaultState((BlockState) ((BlockState) ((BlockState) ((BlockState) this.stateManager.getDefaultState())
                .with(FACING, Direction.NORTH))).with(FACE, WallMountLocation.WALL).with(POWERED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACE, FACING, POWERED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(Properties.HORIZONTAL_FACING);
        float shapeWidth = 0.3f;
        float shapeHeight = 0.75f;
        float shapeDepth = 0.2f;
        switch (dir) {
            case NORTH:
                return VoxelShapes.cuboid(shapeWidth, 1.0f - shapeHeight, 1.0f - shapeDepth, 1.0f - shapeWidth,
                        shapeHeight, 1.0f);
            case SOUTH:
                return VoxelShapes.cuboid(shapeWidth, 1.0f - shapeHeight, 0.0f, 1.0f - shapeWidth, shapeHeight,
                        shapeDepth);
            case EAST:
                return VoxelShapes.cuboid(0.0f, 1.0f - shapeHeight, shapeWidth, shapeDepth, shapeHeight,
                        1.0f - shapeWidth);
            case WEST:
                return VoxelShapes.cuboid(1.0f - shapeDepth, 1.0f - shapeHeight, shapeWidth, 1.0f, shapeHeight,
                        1.0f - shapeWidth);
            default:
                return VoxelShapes.fullCube();
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        for (Direction direction : ctx.getPlacementDirections()) {
            BlockState blockState = direction.getAxis() == Direction.Axis.Y
                    ? (BlockState) ((BlockState) this.getDefaultState()).with(FACING,
                            ctx.getPlayerFacing().getOpposite())
                    : (BlockState) ((BlockState) this.getDefaultState().with(FACE, WallMountLocation.WALL)).with(FACING,
                            direction.getOpposite());
            if (!blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos()))
                continue;
            return blockState;
        }
        return null;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos blockPos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        if (state.get(POWERED).booleanValue()) {
            return ActionResult.CONSUME;
        }
        this.powerOn(state, world, blockPos);
        if (!world.isClient) {
            world.playSound(null, blockPos, AnderMod.DOORBELL_EVENT, SoundCategory.BLOCKS, 1f, 1f);
        }

        return ActionResult.SUCCESS;
    }

    public void powerOn(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, (BlockState)state.with(POWERED, true));
        world.createAndScheduleBlockTick(pos, this, 20);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.get(POWERED).booleanValue()) {
            return;
        }
        world.setBlockState(pos, (BlockState)state.with(POWERED, false));
        world.emitGameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
    }
}