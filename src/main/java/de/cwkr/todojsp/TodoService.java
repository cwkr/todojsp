package de.cwkr.todojsp;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TodoService {
    private final static TodoService INSTANCE = new TodoService();
    private final AtomicInteger sequence = new AtomicInteger(1);
    private final List<TodoItem> items = new CopyOnWriteArrayList<>();

    private TodoService() {

    }

    public void removeItem(int id) {
        items.removeIf(item -> item.getId() == id);
    }

    public void addItem(String title) {
        items.add(new TodoItem(sequence.getAndIncrement(), title));
    }

    public void toggleItem(int id) {
        items.forEach(item -> {
            if(item.getId() == id) {
                item.setCompleted(!item.isCompleted());
            }
        });
    }

    public List<TodoItem> getItems() {
        return items;
    }

    public List<TodoItem> getCompletedItems() {
        return items.stream().filter(TodoItem::isCompleted).collect(Collectors.toList());
    }

    public List<TodoItem> getActiveItems() {
        return items.stream().filter(item -> !item.isCompleted()).collect(Collectors.toList());
    }

    public int getCompletedItemsCount() {
        return (int)items.stream().filter(TodoItem::isCompleted).count();
    }

    public int getTotalItemsCount() {
        return items.size();
    }

    public int getItemsLeftCount() {
        return getTotalItemsCount() - getCompletedItemsCount();
    }

    public static TodoService getInstance() {
        return INSTANCE;
    }
}
