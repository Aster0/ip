package carl.ui;

import carl.Carl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    public void setCarl(Carl carl) {
        this.bot = carl;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        System.out.println(userImage);
    }

    @FXML
    private void handleUserInput() {

        String userText = userInput.getText();

        if (userText.trim().isEmpty())
            return;

        String dukeText = bot.getResponse(userText);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getBotDialog(dukeText, botImage)
        );
        userInput.clear();
    }

}
