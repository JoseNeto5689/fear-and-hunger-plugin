package com.tekpix.fear_and_hunger.handlers;

import com.tekpix.fear_and_hunger.components.SoulStoneComponent;
import com.tekpix.fear_and_hunger.events.GiveSoulStoneEvent;

import java.util.function.Consumer;

public class GiveSoulStoneHandler implements Consumer<GiveSoulStoneEvent> {

    @Override
    public void accept(GiveSoulStoneEvent event) {
        if (!event.playerRef().isValid()) return;

        var store = event.playerRef().getStore();

        var funger = store.getComponent(event.playerRef(), SoulStoneComponent.getComponentType());
        if (funger == null) return;
        funger.addSoulStones(event.ammount());
    }
}
