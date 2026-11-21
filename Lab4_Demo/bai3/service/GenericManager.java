package Lab4_Demo.bai3.service;

import java.util.ArrayList;
import java.util.List;

public class GenericManager<T> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public T get(int index) {
        return items.get(index);
    }

    public int size() {
        return items.size();
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