package Behavioral.Memento;

import java.util.Stack;

/*
    Desende Caretaker rolünü üstlenen sınıf.
    Burada hafifsiklet bir şekilde OrderDraftMemento nesneleri saklanır.
    Ancak bu nesne sakladığı OrderDraftMemento nesnelerinin içeriğini bilmez. Sadece saklar ve geri yükler.
    Bir stack gibi çalışabilir. En son kaydedilen OrderDraftMemento nesnesi geri yüklenir.
    Hatta burada redo operasyonu da söz konusudur. Yani bir önceki OrderDraftMemento nesnesi geri yüklenebilir.

    Caretaker artık Originator'a (OrderDraft) doğrudan bir referans tutuyor. Böylece checkpoint alma
    ve undo/redo sırasında canlı durumu doğrudan Originator'dan isteyebiliyor; Client (Main) her
    çağrıda orderDraft.save() sonucunu elle taşımak zorunda kalmıyor.
*/
public class OrderHistory {
    private final OrderDraft originator;
    private Stack<OrderDraftMemento> history = new Stack<>();
    private Stack<OrderDraftMemento> redoHistory = new Stack<>();

    public OrderHistory(OrderDraft originator) {
        this.originator = originator;
    }

    public void save() {
        history.push(originator.save());
        redoHistory.clear(); // Yeni bir checkpoint alındığında redo geçmişi anlamını yitirir.
    }

    public void undo() {
        if (history.isEmpty()) {
            return;
        }
        redoHistory.push(originator.save());
        originator.restore(history.pop());
    }

    public void redo() {
        if (redoHistory.isEmpty()) {
            return;
        }
        history.push(originator.save());
        originator.restore(redoHistory.pop());
    }
}
