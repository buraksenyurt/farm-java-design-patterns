package com.buraksenyurt.designpatterns.structural.flyweightgofstyle.heavies;

// Tutulması pahalı olan bir nesne örneği. Örneğin, büyük bir 3D modelin vertex verisini içerir.
public record Mesh(String name,int vertexCount) {
    
}
