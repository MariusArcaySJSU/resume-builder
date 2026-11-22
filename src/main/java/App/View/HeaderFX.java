package App.View;

import java.util.ArrayList;

import App.Model.Resume.ResumeItems.Header;
import App.Model.Resume.ResumeItems.Link;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class HeaderFX extends VBox {

    private final TitledPane titledPane;

    private final TextField nameField;
    private final TextField phoneField;
    private final TextField emailField;
    private final VBox links;

    public HeaderFX(Header header) {
        this();
        fill(header);
    }

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

        Button addLink = new Button("Add link");
        addLink.setOnAction(event -> {
            addLink();
        });
        
        VBox nameBox = new VBox(5, nameLabel, nameField);
        VBox phoneBox = new VBox(5, phoneLabel, phoneField);
        VBox emailBox = new VBox(5, emailLabel, emailField);
        VBox linkBox = new VBox(5, linkLabel, links);

        VBox content = new VBox(10, nameBox, phoneBox, emailBox, linkBox, addLink);
        content.setPadding(new Insets(15)); // Matches EducationGroupFX padding

        titledPane = new TitledPane("Personal Information", content);
        titledPane.setCollapsible(true);

        this.getChildren().add(titledPane);
    }

    public void fill(Header header) {

        if (header == null) return;

        nameField.setText(header.name);
        phoneField.setText(header.phone);
        emailField.setText(header.email);

        for (Link l : header.links) {
            addLink(l);
        }

    }

    public Header getHeader() {
        return new Header(getFullName(), getPhone(), getEmail(), getLinks());

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
    
    public ArrayList<Link> getLinks() {

        ArrayList<Link> result = new ArrayList<>();

        for (Node node : links.getChildren()) {
            HBox linkBox = (HBox) node;
            LinkFX linkFX = (LinkFX) linkBox.getChildren().get(0);
            
            if (!linkFX.getUrl().isBlank()) {   // Only add if field is not blank
                if (linkFX.getLabel().isEmpty()) {
                    result.add(new Link(linkFX.getUrl()));
                } else {
                    result.add(new Link(linkFX.getUrl(), linkFX.getLabel()));
                }
            }
        }

        return result;

    }

    private void addLink() {
       addLink(new Link("", ""));
    }

    private void addLink(Link link) {

        LinkFX linkFX = new LinkFX(link);
        Button removeButton = new Button("Remove");
        removeButton.setFocusTraversable(false);

        HBox fullLink = new HBox(10, linkFX, removeButton);

        links.getChildren().add(
            fullLink
        );

        removeButton.setOnAction(event -> {
            links.getChildren().remove(fullLink);
        });
    }

}
