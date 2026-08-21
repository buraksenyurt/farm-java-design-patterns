package Creational.BuilderGoFStyle;

public class ActorDirector {

    private ActorBuilder builder;

    public ActorDirector(ActorBuilder builder) {
        this.builder = builder;
    }

    public void setBuilder(ActorBuilder builder) {
        this.builder = builder;
    }

    public Actor buildStandard(String name) {
        builder.setName(name);
        builder.buildCoreStats();
        builder.buildEquipment();
        builder.buildSkillSet();
        return builder.getProduct();
    }

    public Actor buildMinimal(String name) {
        builder.setName(name);
        return builder.getProduct();
    }
}
