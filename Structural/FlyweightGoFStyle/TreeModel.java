package Structural.FlyweightGoFStyle;

import Structural.FlyweightGoFStyle.Heavies.Mesh;
import Structural.FlyweightGoFStyle.Heavies.Texture;

// Concrete Flyweight sınıfı. Paylaşılan (intrinsic) state'i temsil eder ve render operasyonunu icra eder.
public final class TreeModel implements TreeType {
    private final String species;
    private final Mesh mesh;
    private final Texture texture;

    public TreeModel(String species, Mesh mesh, Texture texture) {
        this.species = species;
        this.mesh = mesh;
        this.texture = texture;
    }

    @Override
    public void render(Canvas canvas, double x, double y, double scale, double rotationDegrees) {
        // mesh ve texture gibi paylaşılan (intrinsic) state ile
        // x, y, scale, rotationDegrees gibi dışsal (extrinsic) state'i kullanarak çizim
        // işleminin gerçekleştiği yer olarak düşünülebilir.
        System.out.printf(
                "Rendering tree '%s' at (%.2f, %.2f) with scale %.2f and rotation %.2f degrees using mesh '%s' and texture '%s'%n",
                species, x, y, scale, rotationDegrees, mesh.name(), texture.name());
        canvas.draw(mesh, texture, x, y, scale, rotationDegrees);
    }

}
