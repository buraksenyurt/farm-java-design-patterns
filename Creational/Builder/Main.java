package Creational.Builder;

// Builde tasarım kalıbının basit bir implementasyonu.
// Bu örnekte Concrete Builder uyarlamaları mevcut değil. Genel desenin biraz daha basitleştirilmiş 
// bir versiyonu ele alınıyor. Product rolünde Actor sınıfı, Builder rolünde Actor sınıfı içindeki Builder 
// sınıfı ve Director rolünde ActorDirector sınıfı kullanılıyor.
public class Main {
    public static void main() {
        // Detaylı özelliklere sahip bir aktör oluşturalım.
        Actor hero = new Actor.Builder("Anna De Armas", ActorType.MAGE)
                .health(95)
                .mana(200)
                .strength(18)
                .weapon("Sharpened Staff")
                .intelligence(18)
                .armor("Enchanted Robe")
                .addSkill("Fireball")
                .addSkill("Teleport")
                .addSkill("Arcane Blast")
                .build();
        System.out.println(hero);

        // Minimum özelliklerde bir aktör oluşturalım. Geri kalan özellikler varsayılan
        // değerleriyle dolacaktır.
        Actor goblin = new Actor.Builder("Goblin Grunt", ActorType.WARRIOR)
                .build();
        System.out.println(goblin);

        // Director nesnesini de kullanarak önceden tanımlanmış karakter şablonları
        // oluşturalım.
        ActorDirector director = new ActorDirector();
        System.out.println(director.buildWarrior("Busy Thore"));
        System.out.println(director.buildRogue("Shadow Warrior"));

        // Birde validation örneği görelim. Geçersiz bir karakter oluşturmaya çalışalım
        // ve hatayı yakalayalım.
        try {
            new Actor.Builder("", ActorType.FARMER).health(-5).build();
        } catch (IllegalStateException e) {
            System.out.println("Geçersiz bir aktör oluşturulmaya çalışıldı: " + e.getMessage());
        }
    }
}
