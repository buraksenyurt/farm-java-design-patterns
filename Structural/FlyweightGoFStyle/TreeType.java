package Structural.FlyweightGoFStyle;

// Flyweight arayüzü. Extrinsic state'i (dışsal state) ele alan operasyonu tanımlar.
public interface TreeType {
    void render(Canvas canvas, double x, double y, double scale, double rotationDegrees);
}
