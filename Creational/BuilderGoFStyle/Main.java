package Creational.BuilderGoFStyle;

/*
    Bu örnekte ise diğerinden farklı olarak Gangs of Four'un tarifine uyan bir yaklaşım sergilenmektedir.

    Farkları şöyle özetleyebiliriz;

      - ActorBuilder soyut arayüzü gerekli inşa adımlarını (buildCoreStats, buildEquipment,
        buildSkillSet) tanımlar.
      - WarriorBuilder / MageBuilder / RogueBuilder söz konusu inşa adımlarını kendi tiplerine
        özel değerler ile doldurur.
      - ActorDirector yalnızca ActorBuilder arayüzü üzerinden çalışır. Hangi Concrete
        Builder kullandığını bilmez ve böylece Liskov ile Dependency Inversion prensiplerine uyar.
      - Client (yani burası) Director nesnesine farklı Concrete Builder örnekleri vererek farklı ürünler üretir.
*/
public class Main {
  public static void main() {

    ActorDirector director = new ActorDirector(new WarriorBuilder());

    Actor warrior = director.buildStandard("Busy Thore");
    System.out.println(warrior);

    director.setBuilder(new MageBuilder());
    Actor mage = director.buildStandard("Anna De Armas");
    System.out.println(mage);

    director.setBuilder(new RogueBuilder());
    Actor rogue = director.buildStandard("Shadow Warrior");
    System.out.println(rogue);

    director.setBuilder(new WarriorBuilder());
    Actor goblin = director.buildMinimal("Goblin Grunt");
    System.out.println(goblin);

    MageBuilder mageBuilder = new MageBuilder();
    mageBuilder.setName("Astra The Wise");
    mageBuilder.buildCoreStats();
    mageBuilder.buildEquipment();
    mageBuilder.buildSkillSet();
    Actor customMage = mageBuilder.getProduct();
    System.out.println(customMage);

    director.setBuilder(new RogueBuilder());
    Actor rogue2 = director.buildStandard("Crimson Shadow");
    System.out.println(rogue2);
  }
}
