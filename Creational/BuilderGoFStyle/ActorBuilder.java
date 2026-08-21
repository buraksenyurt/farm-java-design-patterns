package Creational.BuilderGoFStyle;

public interface ActorBuilder {
    void setName(String name);

    void buildCoreStats();

    void buildEquipment();

    void buildSkillSet();

    Actor getProduct();

    void reset();
}
