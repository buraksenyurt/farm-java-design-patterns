package Creational.BuilderGoFStyle;

import java.util.ArrayList;
import java.util.List;

public class RogueBuilder implements ActorBuilder {

    private String name;
    private int health;
    private int mana;
    private int strength;
    private int agility;
    private int intelligence;
    private String weapon;
    private String armor;
    private List<String> skills;

    public RogueBuilder() {
        reset();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void buildCoreStats() {
        health = 90;
        mana = 30;
        strength = 14;
        agility = 24;
        intelligence = 12;
    }

    @Override
    public void buildEquipment() {
        weapon = "Twin Daggers";
        armor = "Leather Armor";
    }

    @Override
    public void buildSkillSet() {
        skills.add("Backstab");
        skills.add("Stealth");
    }

    @Override
    public Actor getProduct() {
        Actor actor = new Actor(name, ActorType.ROGUE, health, mana,
                new Attributes(strength, agility, intelligence), weapon, armor, skills);
        reset();
        return actor;
    }

    @Override
    public void reset() {
        name = "Unknown Rogue";
        health = 75;
        mana = 20;
        strength = 10;
        agility = 18;
        intelligence = 10;
        weapon = "Small Knife";
        armor = "Tattered Cloak";
        skills = new ArrayList<>();
    }
}
