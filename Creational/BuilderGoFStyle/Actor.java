package Creational.BuilderGoFStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Actor {
    private final String name;
    private final ActorType actorType;
    private final int health;
    private final int mana;
    private final Attributes attributes;
    private final String weapon;
    private final String armor;
    private final List<String> skills;

    Actor(String name, ActorType actorType, int health, int mana,
            Attributes attributes, String weapon, String armor, List<String> skills) {
        this.name = name;
        this.actorType = actorType;
        this.health = health;
        this.mana = mana;
        this.attributes = attributes;
        this.weapon = weapon;
        this.armor = armor;
        this.skills = Collections.unmodifiableList(new ArrayList<>(skills));
    }

    public String getName() {
        return name;
    }

    public ActorType getActorType() {
        return actorType;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getStrength() {
        return attributes.strength();
    }

    public int getAgility() {
        return attributes.agility();
    }

    public int getIntelligence() {
        return attributes.intelligence();
    }

    public String getWeapon() {
        return weapon;
    }

    public String getArmor() {
        return armor;
    }

    public List<String> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return String.format(
                "%s the %s [HP:%d MP:%d %s] | %s, %s | skills=%s",
                name, actorType, health, mana, attributes,
                weapon, armor, skills);
    }
}
