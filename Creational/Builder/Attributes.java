package Creational.Builder;

public record Attributes(int strength, int agility, int intelligence) {
    public int powerLevel() {
        return strength * 2 + agility * 1 + intelligence * 3;
    }

    @Override
    public String toString() {
        return String.format("STR:%d AGI:%d INT:%d (Power Level: %d)",
                strength, agility, intelligence, powerLevel());
    }
}
