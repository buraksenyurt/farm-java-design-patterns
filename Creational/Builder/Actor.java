package Creational.Builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    Builder pattern'de Product olarak ifade edilen ve immutable(değiştirilemez) nesne rolünü 
    aşağıdaki Actor sınıfı üstleniyor. Burada bir oyun sahasındaki karakteri temsil edeceğiz.

    Görüldüğü gibi bir aktörün çok sayıda parametresi var. Bir kez oluşturulduğunda değiştirilmesini istemiyoruz.
*/
public final class Actor {
    private final String name;
    private final ActorType actorType;
    private final int health;
    private final int mana;
    private final Attributes attributes;
    private final String weapon;
    private final String armor;
    private final List<String> skills;

    // Constructor private yapıldı. Dolayısıyla Actor nesnesi oluşturmanın tek yolu
    // Builder üzerinden olacak.
    private Actor(Builder builder, Attributes attributes) {
        this.name = builder.name;
        this.actorType = builder.actorType;
        this.health = builder.health;
        this.mana = builder.mana;
        this.attributes = attributes;
        this.weapon = builder.weapon;
        this.armor = builder.armor;
        this.skills = Collections.unmodifiableList(new ArrayList<>(builder.skills));
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

    // İyi bir pratik olarak Builder sınıfını Actor sınıfının içinde statik bir
    // inner type olarak kullanabiliriz.
    static class Builder {
        // Bunlar mutlaka olması gereken parametreler olsun
        private final String name;
        private final ActorType actorType;

        // Bunları da varsayılan değerleri atayarak opsiyonel yapalım.
        private int health = 100;
        private int mana = 50;
        private int strength = 10;
        private int agility = 10;
        private int intelligence = 10;
        private String weapon = "Sword";
        private String armor = "Wooden Armor";
        private final List<String> skills = new ArrayList<>();

        public Builder(String name, ActorType actorType) {
            this.name = name;
            this.actorType = actorType;
        }

        // Dikkat: her setter yine Builder referansı dönüyor. Böylece zincirleme
        // çağrılar mümkün hale gelecektir.
        public Builder health(int health) {
            this.health = health;
            return this;
        }

        public Builder mana(int mana) {
            this.mana = mana;
            return this;
        }

        public Builder strength(int strength) {
            this.strength = strength;
            return this;
        }

        public Builder agility(int agility) {
            this.agility = agility;
            return this;
        }

        public Builder weapon(String weapon) {
            this.weapon = weapon;
            return this;
        }

        public Builder armor(String armor) {
            this.armor = armor;
            return this;
        }

        public Builder intelligence(int intelligence) {
            this.intelligence = intelligence;
            return this;
        }

        public Builder addSkill(String skill) {
            this.skills.add(skill);
            return this;
        }

        public Actor build() {
            validate();
            Attributes attributes = new Attributes(strength, agility, intelligence);
            return new Actor(this, attributes);
        }

        private void validate() {
            if (name == null || name.trim().isEmpty())
                throw new IllegalStateException("Actor must have a name");
            if (actorType == null)
                throw new IllegalStateException("Actor must have a type");
            if (health <= 0)
                throw new IllegalStateException("Health must be positive, got " + health);
            if (mana < 0)
                throw new IllegalStateException("Mana cannot be negative, got " + mana);
        }
    }
}
