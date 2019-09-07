package duke.ui;

import duke.model.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    void printWelcomeMessage() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.sayHi(), dukeImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        Group userDialog = new Group(DialogBox.getUserDialog(input, userImage));
        Group dukeDialog = new Group(DialogBox.getDukeDialog(response, dukeImage));
        dialogContainer.getChildren().addAll(userDialog, dukeDialog);
        userInput.clear();
        if (input.equals("bye")) {
            Platform.exit();
        }
    }
}