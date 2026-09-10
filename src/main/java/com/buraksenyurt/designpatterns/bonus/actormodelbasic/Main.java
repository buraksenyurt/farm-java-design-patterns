/*
Concurrent Programming tasarım kalıplarından birisi olan `Actor Model`
in ele alındığı giriş seviyesindeki örnek uygulama.

Bu prensipte temel hesaplama birimi aktör(actor) olarak adlandırılır.
Nesnelerin birbirlerinin metotlarını doğrudan çağırmaları yerine(bu sayede
race condition durumlarından da kaçınılır), aktörler kendi iç durumlarını 
değiştirir ve diğer aktörlerle iletişimini asenkron ve değiştirilemez
(Asynchronous / Immutable) mesajlar aracılığıyla sağlar.

Bu kurguda her şey bir aktördür. Bir aktör state(veri), behavior(davranış) 
ve mailbox(mesaj kuyruğu) bileşenlerinden oluşur.

Modelin temel prensipleri şöyle özetlenebilir:

1. Private State: Her aktör kendi verisini saklar. Başka bir thread
veya aktör doğrudan bu veriye erişemez, değiştiremez.
2. Mailbox: Her aktörün kendine ait bir mesaj kuyruk sistemi vardır. 
Her aktör kendi kuyruğundaki mesajları sırasıyla işler (single-threaded).
3. Behavior: Her aktörün belirli bir davranışı vardır ve bu davranış, 
aktörün aldığı mesajlara nasıl tepki vereceğini tanımlar. Bir aktör
diğer aktörlere mesaj gönderebilir, yeni aktörler yaratabilir.

Bir aktör mesaj aldığında üç şeyden birisini ya da birkaçını yapabilir;

- Kendi state'ini değiştirir
- Diğer aktörlere mesaj gönderir
- Yeni aktörler yaratır (spawn olarak da geçiyor)

Not: Bu giriş seviyesindeki örnekte Aktör hiyerarşisi ve supervision (denetim) mekanizması ele alınmamıştır.
 */
package com.buraksenyurt.designpatterns.bonus.actormodelbasic;

public class Main {

    public static void main(String[] args) {
        System.out.println("Actor Model Basic Example");

        // Aktör nesnesi oluşturuluyor.
        BankAccountActor bankAccount = new BankAccountActor();

        // ve ayrı bir thread içinde çalıştırılıyor.
        Thread.ofVirtual().start(bankAccount);

        // Dikkat edileceği üzere aktörün sahip olduğu bakiye bilgisine
        // doğrudan erişmek mümkün değil. Bunun yerine her aksiyon
        // bir mesaj olarak aktöre iletilmekte.
        bankAccount.send(new Deposit(100.0));
        bankAccount.send(new Withdraw(50.0));
        bankAccount.send(new Withdraw(15.0));
        bankAccount.send(new Deposit(200.0));
        bankAccount.send(new Balance());
        // Main thread'i biraz bekletmek lazım zira actör kendi 
        // thread'i içinde çalışırken main hemen sonlanabilir.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
