package com.tekpix.fear_and_hunger.commands;

import com.hypixel.hytale.codec.validation.Validators;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.OptionalArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.tekpix.fear_and_hunger.components.SoulStoneComponent;
import com.tekpix.fear_and_hunger.events.GiveSoulStoneEvent;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class FungerGiveSoulStoneCommand extends AbstractPlayerCommand {

    private final OptionalArg<Integer> amountArg;


    public FungerGiveSoulStoneCommand() {
        super("soul_stone", "Give soul stones to the player who use");
        this.amountArg = withOptionalArg("amount", "Soul stones amount (>0)", ArgTypes.INTEGER)
                .addValidator(Validators.greaterThan(0));
    }

    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        var amount = amountArg.get(commandContext);
        if (amount == null) amount = 1;

        // Check if player has RPG component
        if (store.getComponent(ref, SoulStoneComponent.getComponentType()) == null) {
            playerRef.sendMessage(Message.raw("No Fear and Hunger data found"));
            return;
        }

        playerRef.sendMessage(Message.raw("+%d soul stones".formatted(amount)));
        GiveSoulStoneEvent.dispatch(ref, amount);
    }
}
