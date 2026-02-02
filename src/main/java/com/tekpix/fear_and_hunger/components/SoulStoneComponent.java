package com.tekpix.fear_and_hunger.components;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class SoulStoneComponent implements Component<EntityStore> {

    public SoulStoneComponent(){}

    public SoulStoneComponent(long totalSoulStones){
        this.totalSoulStones = totalSoulStones;
    }

    private long totalSoulStones;

    private static ComponentType<EntityStore, SoulStoneComponent> TYPE;

    public static void setComponentType(ComponentType<EntityStore, SoulStoneComponent> type) {
        TYPE = type;
    }

    public static ComponentType<EntityStore, SoulStoneComponent> getComponentType(){
        return TYPE;
    }


    public static final BuilderCodec<SoulStoneComponent> CODEC = BuilderCodec
            .builder(SoulStoneComponent.class, SoulStoneComponent::new)
            .append(new KeyedCodec<>("TotalSoulStones", Codec.LONG),
                    (component, value) ->  component.totalSoulStones = value,
                    component -> component.totalSoulStones
            ).add()
            .build();



    public long getTotalSoulStones(){
        return totalSoulStones;
    }

    public boolean addSoulStones(long amount){
        if (amount <= 0){
            return false;
        }
        totalSoulStones += amount;
        return true;
    }



    @NullableDecl
    @Override
    public Component<EntityStore> clone() {
        return new SoulStoneComponent(this.totalSoulStones);
    }

    @Override
    public String toString() {
        return "SoulStoneComponent{soul_stones = " + this.totalSoulStones + "}";
    }
}
