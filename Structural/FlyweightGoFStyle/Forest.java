package Structural.FlyweightGoFStyle;

import java.util.ArrayList;
import java.util.List;

import Structural.FlyweightGoFStyle.Heavies.Mesh;
import Structural.FlyweightGoFStyle.Heavies.Texture;

public final class Forest {
    private final List<Tree> trees = new ArrayList<>();
    private final TreeTypeFactory treeTypeFactory = new TreeTypeFactory();

    public void plantTree(double x, double y, double scale, double rotationDegrees, String species, Mesh mesh,
            Texture texture) {
        TreeType treeType = treeTypeFactory.getTreeType(species, mesh, texture);
        Tree tree = new Tree(x, y, scale, rotationDegrees, treeType);
        trees.add(tree);
    }

    public void render(Canvas canvas) {
        for (Tree tree : trees) {
            tree.render(canvas);
        }
    }

    public int getTreeCount() {
        return trees.size();
    }

    public int distinctModels() {
        return treeTypeFactory.createModels();
    }
}
