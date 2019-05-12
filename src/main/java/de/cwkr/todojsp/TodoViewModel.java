package de.cwkr.todojsp;

import java.util.Collections;
import java.util.List;

public class TodoViewModel {
    private final String mode;
    private final List<TodoItem> items;
    private final int numberOfTotalItems;
    private final int numberOfItemsLeft;
    private final int numberOfCompletedItems;

    public TodoViewModel(String mode, List<TodoItem> items,
                         int numberOfTotalItems,
                         int numberOfItemsLeft,
                         int numberOfCompletedItems) {
        this.mode = mode;
        this.items = Collections.unmodifiableList(items);
        this.numberOfTotalItems = numberOfTotalItems;
        this.numberOfItemsLeft = numberOfItemsLeft;
        this.numberOfCompletedItems = numberOfCompletedItems;
    }

    public String getMode() {
        return mode;
    }

    public List<TodoItem> getItems() {
        return items;
    }

    public int getNumberOfTotalItems() {
        return numberOfTotalItems;
    }

    public int getNumberOfItemsLeft() {
        return numberOfItemsLeft;
    }

    public int getNumberOfCompletedItems() {
        return numberOfCompletedItems;
    }
}
