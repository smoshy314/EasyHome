package me.smoshy314.easyhome;

import com.mojang.brigadier.Command;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EasyHome implements ModInitializer {

    public static final String MOD_ID = "fabric-docs-reference";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Hello Fabric world!");

        Command<ServerCommandSource> command = context -> {
            ServerCommandSource source = context.getSource();
            return 0;
        };

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("top").executes(context -> {
                context.getSource().sendFeedback(() -> Text.literal("Called /top with no arguments."), false);
                return 1;
            }));
        });
    }
}
