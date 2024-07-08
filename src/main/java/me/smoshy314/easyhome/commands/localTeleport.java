package me.smoshy314.easyhome.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.chunk.Chunk;
import org.jetbrains.annotations.NotNull;


public class localTeleport {
    public static void register(@NotNull CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("top").executes(localTeleport::top));
    }


    public static int top(@NotNull CommandContext<ServerCommandSource> context)
    {
        if (context.getSource().getPlayer() != null)
        {
            ServerPlayerEntity player = context.getSource().getPlayer();

            if (player.hasPermissionLevel(3)){
                ServerWorld world = player.getServerWorld();
                BlockPos playerPos = player.getBlockPos();
                Chunk playerChunk = world.getChunk(playerPos);
                int x = playerPos.getX();
                int z = playerPos.getZ();
                long topY =  playerChunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE, x, z);

                player.teleport(world, x, topY, z, player.getYaw(), player.getPitch());
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}
