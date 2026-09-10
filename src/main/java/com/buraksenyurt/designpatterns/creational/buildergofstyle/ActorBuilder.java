package com.buraksenyurt.designpatterns.creational.buildergofstyle;

public interface ActorBuilder {
    void setName(String name);

    void buildCoreStats();

    void buildEquipment();

    void buildSkillSet();

    Actor getProduct();

    void reset();
}
