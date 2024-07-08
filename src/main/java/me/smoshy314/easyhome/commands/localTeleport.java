package me.smoshy314.easyhome.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class localTeleport {
    public static void register(@NotNull CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("top").executes(context -> {
            context.getSource().sendFeedback(() -> Text.literal("Called /top with no arguments."), false);
            return 1;
        }));
    }
}
