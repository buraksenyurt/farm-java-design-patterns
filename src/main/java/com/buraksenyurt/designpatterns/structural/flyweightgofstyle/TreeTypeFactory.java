package com.buraksenyurt.designpatterns.structural.flyweightgofstyle;

import java.util.HashMap;
import java.util.Map;

import com.buraksenyurt.designpatterns.structural.flyweightgofstyle.heavies.Mesh;
import com.buraksenyurt.designpatterns.structural.flyweightgofstyle.heavies.Texture;

// Flyweight Factory. Flyweight nesnelerini yönetir ve paylaşımını sağlar.
// Burada üretimi maliyetli olan TreeType nesnelerini önbelleğe alarak, 
// aynı türdeki ağaçların aynı TreeType nesnesini paylaşmasını sağlıyoruz.
public final class TreeTypeFactory {
    private final Map<String, TreeType> pool = new HashMap<>();

    public TreeType getTreeType(String species, Mesh mesh, Texture texture) {
        String key = species + "_" + mesh.name() + "_" + texture.name();
        return pool.computeIfAbsent(key, k -> new TreeModel(species, mesh, texture));
    }

    public int createModels() {
        return pool.size();
    }
}
