package Creational.Builder;

// Özellikle birden fazla aynı türden product nesnesin ihtiyaç olduğu durumlarda, 
// bu nesnelerin oluşturulma sürecini tek bir yerden yönetmek için Director nesnesini kullanabiliriz.
class ActorDirector {

    public Actor buildWarrior(String name) {
        return new Actor.Builder(name, ActorType.WARRIOR)
                .health(175).mana(0).strength(20).agility(12)
                .weapon("Steel Greatsword").armor("Plate Mail")
                .addSkill("Shield Bash").addSkill("Cleave")
                .build();
    }

    public Actor buildMage(String name) {
        return new Actor.Builder(name, ActorType.MAGE)
                .health(80).mana(200)
                .weapon("Crystal Wand").armor("Silk Robe")
                .addSkill("Frostbolt").addSkill("Arcane Shield")
                .build();
    }

    public Actor buildRogue(String name) {
        return new Actor.Builder(name, ActorType.ROGUE)
                .health(90).mana(30).agility(24).strength(14)
                .weapon("Twin Daggers").armor("Leather Armor")
                .addSkill("Backstab").addSkill("Stealth")
                .build();
    }
}
