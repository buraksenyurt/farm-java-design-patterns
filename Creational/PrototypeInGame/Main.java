package Creational.PrototypeInGame;

import java.util.ArrayList;
import java.util.List;

/*
    Bir başka prototype senaryosu.
    Oyun sahansında yüzlerce Goblin karakteri örneklediğimizi düşünelim.
*/

public class Main {
    public static void main(String[] args) {

        int spawnCount = 10_000;

        // Desen kullanmadığımız senaryo: 10_000 x new Enemy() -> 10_000 kez pahalı
        // yapıcı metod çağrısı demek
        long naiveStart = System.nanoTime();
        List<Enemy> naiveEnemies = new ArrayList<>();
        for (int i = 0; i < spawnCount; i++) {
            naiveEnemies.add(new Enemy("Goblin", 40, 5));
        }
        long naiveMs = (System.nanoTime() - naiveStart) / 1_000_000;

        // Prototype Uyguladığımızda: 1 x pahalı yapıcı + (spawnCount - 1) x ucuz kopya
        long protoStart = System.nanoTime();
        Enemy goblinPrototype = new Enemy("Goblin", 40, 5);
        List<Enemy> protoEnemies = new ArrayList<>();
        protoEnemies.add(goblinPrototype);
        for (int i = 1; i < spawnCount; i++) {
            protoEnemies.add(goblinPrototype.copy());
        }
        long protoMs = (System.nanoTime() - protoStart) / 1_000_000;

        System.out.println("Naive Working Time (N x new):                     " + naiveMs + " ms");
        System.out.println("Prototype Working Time (1 x new + (N-1) x clone):  " + protoMs + " ms");

        // abilities'in gerçekten deep copy olduğunu kanıtlayalım: sadece belirli
        // klonlara yetenek ekleyip diğerlerinin (ve prototype'ın) etkilenmediğini
        // görelim.
        protoEnemies.get(10).addAbility("Poison Bite");
        protoEnemies.get(20).addAbility("Fire Breath");

        System.out.println("\nPrototype (değişmedi): " + goblinPrototype);
        System.out.println("protoEnemies[10] (Poison Bite eklendi): " + protoEnemies.get(10));
        System.out.println("protoEnemies[11] (etkilenmedi): " + protoEnemies.get(11));
        System.out.println("protoEnemies[20] (Fire Breath eklendi): " + protoEnemies.get(20));
    }
}
