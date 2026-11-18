package App.View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class HeaderFX extends VBox {

    private final TextField nameField;
    private final TextField phoneField;
    private final TextField emailField;
    private final VBox links;

    public HeaderFX() {
        super(10);

        Label nameLabel = new Label("Full Name:");
        nameField = new TextField();
        nameField.setPromptText("Jane Doe");

        Label phoneLabel = new Label("Phone Number:");
        phoneField = new TextField();
        phoneField.setPromptText("(408) 123-4567");

        Label emailLabel = new Label("Email Address:");
        emailField = new TextField();
        emailField.setPromptText("jane.doe@email.com");

        Label linkLabel = new Label("Links:");
        links = new VBox(5);
        addLink();

        Button addLink = new Button("Add link");
        addLink.setOnAction(event -> {
            addLink();
        });


        this.getChildren().addAll(
                nameLabel, nameField,
                phoneLabel, phoneField,
                emailLabel, emailField,
                linkLabel, links, addLink
        );
    }

    private void addLink() {

        LinkFX link = new LinkFX();
        Button removeButton = new Button("Remove");
        HBox fullLink = new HBox(10, link, removeButton);

        links.getChildren().add(
            fullLink
        );

        removeButton.setOnAction(event -> {
            links.getChildren().remove(fullLink);
        });
    }

    public String getFullName() {
        return nameField.getText();
    }

    public String getPhone() {
        return phoneField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }
    
}
