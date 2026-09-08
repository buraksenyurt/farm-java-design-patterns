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
package Bonus.ActorModelBasic;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

interface Message {
}

// Concrete mesaj sınıfları. Her biri Message arayüzünü uyguluyor
record Deposit(double amount) implements Message {

}

record Withdraw(double amount) implements Message {

}

record Balance() implements Message {

}

// Actor Base Class'ımız
abstract class Actor implements Runnable {

    protected final BlockingQueue<Message> mailbox = new LinkedBlockingQueue<>();
    private volatile boolean running = true;

    // Mesaj gönderenlerin mesajları alıcı aktörün kuyruğuna eklenir.
    public void send(Message message) {
        mailbox.offer(message);
    }

    @Override
    public void run() {
        while (running) {
            try {
                Message message = mailbox.take();
                onReceive(message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                running = false;
            }
        }
    }

    protected abstract void onReceive(Message message);
}

// Concrete Actor sınıfımız
// Dikkat edileceği üzere Actor base class'tan türer
class BankAccountActor extends Actor {

    private double balance = 0.0;

    @Override
    protected void onReceive(Message message) {
        // Gelen mesajın hangi türde olduğuna göre bir işlem yapılır
        // İşlem dikkat edileceği üzere actor'ün kendi state'ini değiştirmesi şeklindedir.
        if (message instanceof Deposit deposit) {
            balance += deposit.amount();
        } else if (message instanceof Withdraw withdraw) {
            if (balance >= withdraw.amount()) {
                balance -= withdraw.amount();
            }
        } else if (message instanceof Balance) {
            System.out.println("Current balance: " + balance);
        }
    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println("Actor Model Basic Example");

        BankAccountActor bankAccount = new BankAccountActor();

        Thread.ofVirtual().start(bankAccount);

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
