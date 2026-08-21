package Structural.FlyweightGoFStyle;

import Structural.FlyweightGoFStyle.Heavies.Mesh;
import Structural.FlyweightGoFStyle.Heavies.Texture;

// Canvas sınıfı, çizim işlemlerini gerçekleştiren bir sınıfı temsil eder.
// Bu sınıf, Flyweight deseninde kullanılan Mesh ve Texture nesnelerini yönetir.
public final class Canvas {
    private int drawingCount = 0;

    public void draw(Mesh mesh, Texture texture, double x, double y, double scale, double rotationDegrees) {
        drawingCount++;
        // // Gerçek uygulamada, burada grafik API'lerini kullanarak çizim işlemi yapılır.
        // System.out.println("Drawing mesh '" + mesh.name() + "' with texture '" + texture.name() + "' at position (" + x
        //         + ", " + y + ") with scale " + scale + " and rotation " + rotationDegrees + " degrees   .");
    }

    public int getDrawingCount() {
        return drawingCount;
    }
}
