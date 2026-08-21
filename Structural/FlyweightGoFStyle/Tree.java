package Structural.FlyweightGoFStyle;

// Bu aslında GoF'taki tarifte yer alan bir nesne değil.
// Senaryodaki görevi her Tree için konum, ölçek ve dönüş açısı gibi dışsal (extrinsic) state'i unique olarak
// tutmak ve render operasyonunu icra etmek. 
// Flyweight nesnesi olan TreeType'ı kullanarak render işlemini gerçekleştiriyor.
public final class Tree {
    private final double x, y, scale, rotationDegrees;
    private final TreeType type; // Flyweight nesnesi

    public Tree(double x, double y, double scale, double rotationDegrees, TreeType type) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.rotationDegrees = rotationDegrees;
        this.type = type;
    }

    public void render(Canvas canvas) {
        type.render(canvas, x, y, scale, rotationDegrees);
    }
}
