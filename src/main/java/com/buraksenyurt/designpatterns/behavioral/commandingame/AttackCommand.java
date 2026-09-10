package com.buraksenyurt.designpatterns.behavioral.commandingame;

public class AttackCommand implements Command {
    @Override
    public void execute(Player actor) {
        System.out.println("AttackCommand oluşturuldu...");
        actor.attack();
    }    
}
