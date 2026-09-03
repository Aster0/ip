package carl.ui;

import carl.Carl;
import carl.commands.CommandResult;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI view of the application.
 * Handles user interactions, displays chat dialogues, and renders bot responses.
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

    private Carl bot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));

    /**
     * Sets the instance of Carl to be used by the controller and displays the welcome message.
     *
     * @param carl The chatbot instance to bind to this controller.
     */
    public void setCarl(Carl carl) {
        this.bot = carl;
        addMessage(bot.getWelcomeMessage());
    }
    /**
     * Initializes the controller after its root element has been completely processed.
     * Binds the scroll pane's vertical position to the height of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    private void addMessage(String message) {
        dialogContainer.getChildren().addAll(
                DialogBox.getBotDialog(message, botImage)
        );
    }

    @FXML
    private void handleUserInput() {

        String userText = userInput.getText();

        if (userText.trim().isEmpty()) {
            return;
        }

        CommandResult commandResult = bot.getResponse(userText);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getBotDialog(commandResult.message(), botImage)
        );

        userInput.clear();

        if (commandResult.isExited()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }

}
