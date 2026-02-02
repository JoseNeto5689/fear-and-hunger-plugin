package com.tekpix.fear_and_hunger.commands;

import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class FungerCommand extends AbstractCommandCollection {
    public FungerCommand() {
        super("funger", "Fear and Hunger commands utils");
        addSubCommand(new FungerGiveSoulStoneCommand());
    }
}
