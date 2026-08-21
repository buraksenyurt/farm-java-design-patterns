package Structural.FlyweightGoFStyle;

import java.util.Random;

import Structural.FlyweightGoFStyle.Heavies.Mesh;
import Structural.FlyweightGoFStyle.Heavies.Texture;

/*
    Robert Nystrom'un "Game Programming Patterns" kitabındaki Flyweight örneğinin Java'ya uyarlanmış versiyonu.
    Oyunlarda grafik motorlarınca üretim maliyeti yüksek olan nesnelerin farklı nesnelerce paylaşıldığı durumlarda
    bellek kullanımını optimize etmek için kullanılan bir tasarım deseni olarak düşünebiliriz.

    Bu senaryoda, bir ormanda binlerce ağaç olduğunu varsayalım. Her ağacın türü, modeli ve dokusu birer özellik olsun.
    Aynı türdeki ağaçlar doğal olarak aynı modele ve dokuya sahip olacaklardır. Böyle bir durumda,
    her ağacın kendi model ve dokusunu tutması yerine, aynı türdeki ağaçların aynı model ve dokuyu paylaşması bellek kullanımını önemli ölçüde azaltır.

    - TreeType: Flyweight nesnesi, ağacın türüne özgü bilgileri içerir. 
        Örneğin, tür adı, model ve doku gibi bilgileri tutar.
    - TreeModel: TreeType arayüzünü uygulayan somut Flyweight sınıfıdır. 
      Paylaşılan (intrinsic) state'i temsil eder ve render operasyonunu icra eder.
    - Tree: Her ağaç için konum, ölçek ve dönüş açısı gibi dışsal (extrinsic) state'i unique olarak tutar ve 
    render operasyonunu icra eder. Render işlemi için TreeType nesnesini kullanır.
    - TreeTypeFactory: Flyweight Factory. Flyweight nesnelerini yönetir ve paylaşımını sağlar. 
      Burada üretimi maliyetli olan TreeType nesnelerini önbelleğe alarak, aynı türdeki ağaçların aynı TreeType nesnesini paylaşmasını sağlıyoruz.
    - Forest: Ağaçları yönetir ve render işlemini gerçekleştirir. 
      Yeni bir ağaç eklerken, mevcut TreeType nesnelerini kontrol eder ve gerekiyorsa yeni bir TreeType oluşturur.
    - Canvas: Çizim işlemlerini gerçekleştirir ve yapılan çizim sayısını takip eder.

    Programı çalıştırdıktan sonra çıktıdaki toplam ağaç, çizim sayısı ve bellekten kullanılan model sayısına dikka edelim.
    3 tür ağaç olmasına rağmen, binlerce ağaç oluşturulmuş ve sadece 3 model kullanılmıştır.
    Her ağaç için ayrı bir model oluşturulmadığı için bellek kullanımı önemli ölçüde azaltılmıştır.
*/

public class GameDemo {
    public static void main(String[] args) {
        Forest forest = new Forest();
        Canvas canvas = new Canvas();
        Random random = new Random(10);

        record Species(String name, Mesh mesh, Texture texture) {
        }

        Species[] species = {
                new Species("Oak", new Mesh("OakMesh", 1000), new Texture("OakTexture", 512, 512)),
                new Species("Pine", new Mesh("PineMesh", 800), new Texture("PineTexture", 256, 256)),
                new Species("Birch", new Mesh("BirchMesh", 600), new Texture("BirchTexture", 128, 128))
        };

        for (int i = 0; i < 1000; i++) {
            double x = random.nextDouble() * 1000;
            double y = random.nextDouble() * 1000;
            double scale = 0.5 + random.nextDouble(); // 0.5 ile 1.5 arasında rastgele ölçek
            double rotation = random.nextDouble() * 360; // 0 ile 360 derece arasında rastgele dönüş

            // Rastgele bir ağaç türü seçelim
            Species s = species[random.nextInt(species.length)];
            forest.plantTree(x, y, scale, rotation, s.name, s.mesh, s.texture);
        }

        forest.render(canvas);

        System.out.println("Total trees: " + forest.getTreeCount());
        System.out.println("Models in memory: " + forest.distinctModels());
        System.out.println("Total draw calls: " + canvas.getDrawingCount());
    }
}
