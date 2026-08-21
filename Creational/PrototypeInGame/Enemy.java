package Creational.PrototypeInGame;

import java.util.ArrayList;
import java.util.List;

// Prototype sınıfı
public class Enemy {

    private static final int MESH_DATA_SIZE = 10_000; // Örnek olarak büyük bir boyut

    private String name;
    private int health;
    private int attackPower;
    private List<String> abilities;
    private int[] meshData; // 3D model verisi

    public void addAbility(String ability) {
        this.abilities.add(ability);
    }

    public Enemy(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.abilities = new ArrayList<>(List.of("Slash Attack"));

        this.meshData = new int[MESH_DATA_SIZE]; // Büyük veri yükleniyor
        for (int i = 0; i < MESH_DATA_SIZE; i++) {
            this.meshData[i] = (int) (Math.sin(i) * Math.sqrt(i) * 1000); // Sembolik bir 3D model verisi oluşturma
        }
    }

    // Copy-Constructor for deep copy
    public Enemy(Enemy other) {
        this.name = other.name;
        this.health = other.health;
        this.attackPower = other.attackPower;
        this.abilities = new ArrayList<>(other.abilities); // Deep copy
        this.meshData = other.meshData; // Shallow copy zira meshData büyük ve değişmeyecek
    }

    // Copy mutator metodumuz
    public Enemy copy() {
        return new Enemy(this);
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", attackPower=" + attackPower +
                ", abilities=" + abilities +
                '}';
    }
}
