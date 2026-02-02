package com.tekpix.fear_and_hunger;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.logger.HytaleLogger;
import com.tekpix.fear_and_hunger.components.SoulStoneComponent;
import com.tekpix.fear_and_hunger.systems.PlayerJoinSystem;

import javax.annotation.Nonnull;
import java.util.logging.Level;

/**
 * FearAndHungerPlugin - A Hytale server plugin.
 *
 * @author joseneto5689
 * @version 1.0.0
 */
public class FearAndHungerPlugin extends JavaPlugin {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    private static FearAndHungerPlugin instance;

    public FearAndHungerPlugin(@Nonnull JavaPluginInit init) {
        super(init);
        instance = this;
    }

    public static FearAndHungerPlugin getInstance() {
        return instance;
    }

    @Override
    protected void setup() {
        LOGGER.at(Level.INFO).log("[FearAndHungerPlugin] Setting up...");

        // TODO: Register commands and listeners here

        var fungerType = getEntityStoreRegistry().registerComponent(
                SoulStoneComponent.class,
                "SoulStoneQuantity",
                SoulStoneComponent.CODEC
        );

        SoulStoneComponent.setComponentType(fungerType);

        getEntityStoreRegistry().registerSystem(new PlayerJoinSystem());

        LOGGER.at(Level.INFO).log("[FearAndHungerPlugin] Setup complete!");
    }

    @Override
    protected void start() {
        LOGGER.at(Level.INFO).log("[FearAndHungerPlugin] Started!");
    }

    @Override
    protected void shutdown() {
        LOGGER.at(Level.INFO).log("[FearAndHungerPlugin] Shutting down...");
        instance = null;
    }
}