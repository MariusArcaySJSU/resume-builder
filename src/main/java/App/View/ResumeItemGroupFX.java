package App.View;

import java.util.ArrayList;

import App.Model.Resume.ResumeItems.ResumeItem;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public abstract class ResumeItemGroupFX<T extends ResumeItem> extends VBox {

    protected final VBox itemList;
    private final ArrayList<Button> removeButtons = new ArrayList<>();

    protected ResumeItemGroupFX(String title, String addButtonText, double itemSpacing, Insets itemPadding, double contentSpacing) {
        itemList = new VBox(itemSpacing);
        if (itemPadding != null) {
            itemList.setPadding(itemPadding);
        }

        Button addButton = new Button(addButtonText);
        addButton.setOnAction(event -> addItem(null));

        TitledPane titledPane = new TitledPane(title, new VBox(contentSpacing, itemList, addButton));
        titledPane.setCollapsible(true);
        getChildren().add(titledPane);
    }

    public final void clear() {
        itemList.getChildren().clear();
        removeButtons.clear();
    }

    protected final void addItem(T item) {
        Node itemView = createItemView(item);
        Button removeButton = new Button("Remove");
        removeButton.setFocusTraversable(false);
        removeButton.getStyleClass().add("remove-action");
        removeButton.setMinWidth(90);

        Node entry = createEntry(itemView, removeButton, itemList.getChildren().isEmpty());
        itemList.getChildren().add(entry);
        removeButtons.add(removeButton);
        updateRemoveButtons();
        removeButton.setOnAction(event -> {
            itemList.getChildren().remove(entry);
            removeButtons.remove(removeButton);
            updateRemoveButtons();
        });
    }

    private void updateRemoveButtons() {
        boolean showRemoveButtons = itemList.getChildren().size() > 1;
        for (Button removeButton : removeButtons) {
            removeButton.setVisible(showRemoveButtons);
            removeButton.setManaged(showRemoveButtons);
        }
    }

    protected final ArrayList<T> getItems() {
        ArrayList<T> items = new ArrayList<>();
        for (Node entry : itemList.getChildren()) {
            items.add(getItem(entry));
        }
        return items;
    }

    protected abstract Node createItemView(T item);

    protected abstract Node createEntry(Node itemView, Button removeButton, boolean firstItem);

    protected abstract T getItem(Node entry);
}
