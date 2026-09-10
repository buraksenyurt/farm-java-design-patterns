package com.buraksenyurt.designpatterns.behavioral.commandsimple;

// Command arayüzü
public interface SmartHomeCommand {
    void execute();
    void undo();
}
