package Lab4_Demo.bai4.service;

import java.util.ArrayList;
import java.util.List;
import Lab4_Demo.bai4.exception.EmptyListException;

public class GenericManager<T> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public T get(int index) {
        return items.get(index);
    }

    public int size() {
        if (items.isEmpty()) {
            System.out.println("=> Danh sách đang trống.");
            return 0;
        }
        return items.size();
    }

    public T getFirst() throws EmptyListException {
        if (items.isEmpty()) {
            throw new EmptyListException("Danh sách đang rỗng, không thể lấy phần tử đầu tiên!");
        }
        return items.get(0);
    }

    public T getLast() throws EmptyListException {
        if (items.isEmpty()) {
            throw new EmptyListException("Danh sách đang rỗng, không thể lấy phần tử cuối cùng!");
        }
        return items.get(items.size() - 1);
    }

    public void display() {
        if (items.isEmpty()) {
            System.out.println("=> Danh sách đang trống.");
            return;
        }
        for (T item : items) {
            System.out.println(item);
        }
    }
}
