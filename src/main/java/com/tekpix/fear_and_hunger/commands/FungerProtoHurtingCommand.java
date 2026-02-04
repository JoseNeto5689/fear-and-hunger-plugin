package com.tekpix.fear_and_hunger.commands;

import com.hypixel.hytale.codec.validation.Validators;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.OptionalArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractTargetEntityCommand;
import com.hypixel.hytale.server.core.entity.damage.DamageDataComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.modules.entity.damage.DamageCause;
import com.hypixel.hytale.server.core.modules.entity.damage.DamageSystems;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatMap;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatValue;
import com.hypixel.hytale.server.core.modules.entitystats.asset.DefaultEntityStatTypes;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import it.unimi.dsi.fastutil.objects.ObjectList;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class FungerProtoHurtingCommand extends AbstractTargetEntityCommand {

    private final Float DAMAGE = 30f;

    public FungerProtoHurtingCommand() {
        super("hurt_prototype", "A prototype of hurting spell");
    }


    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl ObjectList<Ref<EntityStore>> objectList, @NonNullDecl World world, @NonNullDecl Store<EntityStore> store) {
        Damage.CommandSource damageSource = new Damage.CommandSource(commandContext.sender(), this.getName());
        Damage damage = new Damage(damageSource, DamageCause.COMMAND, DAMAGE);

        for (var ref : objectList) {
            DamageSystems.executeDamage(ref, store, damage);
        }

        commandContext.sendMessage(Message.raw("Hurt realized"));

    }
}
