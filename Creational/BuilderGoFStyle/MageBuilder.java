package Creational.BuilderGoFStyle;

import java.util.ArrayList;
import java.util.List;

public class MageBuilder implements ActorBuilder {

    private String name;
    private int health;
    private int mana;
    private int strength;
    private int agility;
    private int intelligence;
    private String weapon;
    private String armor;
    private List<String> skills;

    public MageBuilder() {
        reset();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void buildCoreStats() {
        health = 80;
        mana = 200;
        strength = 6;
        agility = 10;
        intelligence = 24;
    }

    @Override
    public void buildEquipment() {
        weapon = "Crystal Wand";
        armor = "Silk Robe";
    }

    @Override
    public void buildSkillSet() {
        skills.add("Frostbolt");
        skills.add("Arcane Shield");
    }

    @Override
    public Actor getProduct() {
        Actor actor = new Actor(name, ActorType.MAGE, health, mana,
                new Attributes(strength, agility, intelligence), weapon, armor, skills);
        reset();
        return actor;
    }

    @Override
    public void reset() {
        name = "Unknown Mage";
        health = 60;
        mana = 100;
        strength = 5;
        agility = 8;
        intelligence = 18;
        weapon = "Wooden Staff";
        armor = "Plain Robe";
        skills = new ArrayList<>();
    }
}
