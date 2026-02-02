package com.tekpix.fear_and_hunger.systems;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.RefSystem;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.tekpix.fear_and_hunger.components.SoulStoneComponent;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class PlayerJoinSystem extends RefSystem<EntityStore> {
    @Override
    public void onEntityAdded(@NonNullDecl Ref<EntityStore> ref, @NonNullDecl AddReason addReason, @NonNullDecl Store<EntityStore> store, @NonNullDecl CommandBuffer<EntityStore> commandBuffer) {
        System.out.println();
        if (addReason != AddReason.LOAD) {
            return;
        }

        var playerRef = store.getComponent(ref, PlayerRef.getComponentType());
        if (playerRef == null) return;

        var fungerType = SoulStoneComponent.getComponentType();
        var funger = store.getComponent(ref, fungerType);

        if (funger != null){

            playerRef.sendMessage(Message.raw("You have: " + funger.getTotalSoulStones() + " soul stones" ));
        }

        else {
            commandBuffer.addComponent(ref, fungerType, new SoulStoneComponent());
            playerRef.sendMessage(Message.raw("You're so fucked"));
        }

    }

    @Override
    public void onEntityRemove(@NonNullDecl Ref<EntityStore> ref, @NonNullDecl RemoveReason removeReason, @NonNullDecl Store<EntityStore> store, @NonNullDecl CommandBuffer<EntityStore> commandBuffer) {}

    @NullableDecl
    @Override
    public Query<EntityStore> getQuery() {
        return Archetype.of(PlayerRef.getComponentType());
    }
}
