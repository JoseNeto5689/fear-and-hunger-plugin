package com.tekpix.fear_and_hunger.events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.event.IEvent;
import com.hypixel.hytale.event.IEventDispatcher;
import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public record GiveSoulStoneEvent(@Nonnull Ref<EntityStore> playerRef, long ammount) implements IEvent<Void> {

    public static void dispatch(Ref<EntityStore> playerRef, long amount){
        IEventDispatcher<GiveSoulStoneEvent, GiveSoulStoneEvent> dispatcher = HytaleServer.get().getEventBus().dispatchFor(GiveSoulStoneEvent.class);

        if (dispatcher.hasListener()){
            dispatcher.dispatch(new GiveSoulStoneEvent(playerRef, amount));
        }
    }
}
