package Creational.BuilderGoFStyle;

import java.util.ArrayList;
import java.util.List;

public class WarriorBuilder implements ActorBuilder {

    private String name;
    private int health;
    private int mana;
    private int strength;
    private int agility;
    private int intelligence;
    private String weapon;
    private String armor;
    private List<String> skills;

    public WarriorBuilder() {
        reset();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void buildCoreStats() {
        health = 175;
        mana = 0;
        strength = 20;
        agility = 12;
        intelligence = 8;
    }

    @Override
    public void buildEquipment() {
        weapon = "Steel Greatsword";
        armor = "Plate Mail";
    }

    @Override
    public void buildSkillSet() {
        skills.add("Shield Bash");
        skills.add("Cleave");
    }

    @Override
    public Actor getProduct() {
        Actor actor = new Actor(name, ActorType.WARRIOR, health, mana,
                new Attributes(strength, agility, intelligence), weapon, armor, skills);
        reset();
        return actor;
    }

    @Override
    public void reset() {
        name = "Unknown Warrior";
        health = 100;
        mana = 0;
        strength = 15;
        agility = 10;
        intelligence = 5;
        weapon = "Rusty Sword";
        armor = "Cloth Armor";
        skills = new ArrayList<>();
    }
}
