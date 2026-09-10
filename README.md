# Java ile Tasarım Kalıpları

Java programlama dili ile temel tasarım kalıplarının ele alındığı bireysel gelişim
ve eğitim reposudur. Proje, **Apache NetBeans**, **IntelliJ IDEA**, **Eclipse** ve
**VS Code** gibi ortamlarda doğrudan açılabilen standart bir **Maven** projesi
olarak yapılandırılmıştır.

## Platform

- **JDK 21** (LTS) veya üzeri
- **Apache Maven 3.9+**

Çalışma ortamı olarak emektar **Ubuntu** sistemim tercih edilmiştir, ancak proje
platform bağımsızdır (Windows / macOS / Linux).

## Kurulumlar

```bash
# Ubuntu / Debian
sudo apt update
sudo apt install openjdk-21-jdk maven

# Kontrol
java -version
mvn -version
```

IDE tarafında ekstra bir kuruluma gerek yoktur:

- **NetBeans / IntelliJ IDEA / Eclipse**: `File > Open Project` ile kök dizindeki
  `pom.xml` dosyasını açmanız yeterlidir.
- **VS Code**: `vscjava.vscode-java-pack` eklentisi ile kök klasörü açın.

```bash
code --install-extension vscjava.vscode-java-pack
```

## Proje Yapısı

Her tasarım kalıbı, `com.buraksenyurt.designpatterns` kök paketi altında kendi
kategorisi ve kendi alt paketinde yer alır. Her örneğin, tek başına
çalıştırılabilen bir demo (`main`) sınıfı vardır.

```text
farm-java-design-patterns/
├── pom.xml
├── images/                         # UML diyagramları ve çıktı görselleri
└── src/main/java/com/buraksenyurt/designpatterns/
    ├── creational/
    │   ├── builder/                 # Builder (sadeleştirilmiş) — Main
    │   ├── buildergofstyle/         # Builder (GoF stili) — Main
    │   ├── prototype/               # Prototype — Main
    │   ├── prototypegofstyle/       # Prototype (GoF stili) — Main
    │   └── prototypeingame/         # Prototype (oyun senaryosu) — Main
    ├── structural/
    │   ├── decorator/               # Decorator — Main
    │   ├── flyweight/               # Flyweight — StockMarketDemo
    │   └── flyweightgofstyle/       # Flyweight (GoF stili) — GameDemo
    │       └── heavies/
    ├── behavioral/
    │   ├── command/                 # Command — Client
    │   ├── commandingame/           # Command (oyun senaryosu) — Game
    │   ├── commandsimple/           # Command (sade) — HomeClient
    │   ├── memento/                 # Memento — Main
    │   ├── observer/                # Observer — Main
    │   └── strategy/                # Strategy — Main
    └── bonus/
        ├── actormodelbasic/         # Actor Model (temel) — Main
        └── actormodelstockcontrol/  # Actor Model (stok kontrol) — Main
            ├── actors/
            └── messages/
```

## Derleme

```bash
mvn clean compile
```

## Örnekleri Çalıştırma

Her örnek, `exec-maven-plugin` üzerinden `main` sınıfı belirtilerek çalıştırılır.
`-D` argümanını **tırnak içinde** vermek hem PowerShell hem de bash/CMD için
sorunsuz çalışır:

```bash
mvn -q compile exec:java "-Dexec.mainClass=<main-class-name>"

# Örneğin
mvn -q compile exec:java "-Dexec.mainClass=com.buraksenyurt.designpatterns.behavioral.observer.Main"
```

> **PowerShell notu:** Tırnak kullanılmazsa (`-Dexec.mainClass=...`) PowerShell argümanı `.` ve `=` üzerinden böler ve Maven `Unknown lifecycle phase".mainClass=..."` hatası verir. Tırnak bu sorunu çözer.

Argümansız çağrıldığında `pom.xml` içindeki `exec.mainClass` varsayılanı
(Builder örneği) çalışır:

```bash
mvn -q compile exec:java
```

IDE üzerinde ise ilgili `Main` / `Client` / `Demo` sınıfına sağ tıklayıp
**Run** demeniz yeterlidir.

### Demo sınıfları

| **Kategori**    | **Kalıp**                         | **Çalıştırılacak sınıf** |
|-------------|-------------------------------|----------------------|
| Creational  | Builder                       | `com.buraksenyurt.designpatterns.creational.builder.Main` |
| Creational  | Builder (GoF)                 | `com.buraksenyurt.designpatterns.creational.buildergofstyle.Main` |
| Creational  | Prototype                     | `com.buraksenyurt.designpatterns.creational.prototype.Main` |
| Creational  | Prototype (GoF)               | `com.buraksenyurt.designpatterns.creational.prototypegofstyle.Main` |
| Creational  | Prototype (oyun)              | `com.buraksenyurt.designpatterns.creational.prototypeingame.Main` |
| Structural  | Decorator                     | `com.buraksenyurt.designpatterns.structural.decorator.Main` |
| Structural  | Flyweight                     | `com.buraksenyurt.designpatterns.structural.flyweight.StockMarketDemo` |
| Structural  | Flyweight (GoF)               | `com.buraksenyurt.designpatterns.structural.flyweightgofstyle.GameDemo` |
| Behavioral  | Command                       | `com.buraksenyurt.designpatterns.behavioral.command.Client` |
| Behavioral  | Command (oyun)                | `com.buraksenyurt.designpatterns.behavioral.commandingame.Game` |
| Behavioral  | Command (sade)                | `com.buraksenyurt.designpatterns.behavioral.commandsimple.HomeClient` |
| Behavioral  | Memento                       | `com.buraksenyurt.designpatterns.behavioral.memento.Main` |
| Behavioral  | Observer                      | `com.buraksenyurt.designpatterns.behavioral.observer.Main` |
| Behavioral  | Strategy                      | `com.buraksenyurt.designpatterns.behavioral.strategy.Main` |
| Bonus       | Actor Model (temel)           | `com.buraksenyurt.designpatterns.bonus.actormodelbasic.Main` |
| Bonus       | Actor Model (stok kontrol)    | `com.buraksenyurt.designpatterns.bonus.actormodelstockcontrol.Main` |

![Sample Runtime](./images/SampleRuntime_00.png)

## Yardımcı Diyagramlar

### Creational Patterns - Builder

![Builder](./images/BuilderPatternUml.png)

### Creational Patterns - Prototype

![Prototype Pattern](./images/PrototypePatternUml.png)

### Structural Patterns - Flyweight

![Flyweight Pattern](./images/FlyweightPatternUml.png)

### Structural Patterns - Decorator

![Decorator Pattern](./images/DecoratorPatternUml.png)

### Behavioral Patterns - Command

![Command Pattern](./images/CommandPatternUml.png)

### Behavioral Patterns - Strategy

![Strategy Pattern](./images/StrategyPatternUml.png)

### Behavioral Patterns - Observer

![Observer Pattern](./images/ObserverPatternUml.png)

### Behavioral Patterns - Memento

![Memento Pattern](./images/MementoPatternUml.png)

## Yardımcı Kaynaklar

- [Java Programming Cheatsheet - Princeton University](https://introcs.cs.princeton.edu/java/11cheatsheet/)
- [Maven Central Repository](https://central.sonatype.com/)
- [Awesome Java](https://github.com/akullpp/awesome-java)
- [Useful Java Links](https://github.com/Vedenin/useful-java-links/)
