package cardflipping;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import static javafx.scene.media.AudioClip.INDEFINITE;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class is view for Card Flipping Game.
 * 
 * I, Jungwon Lim, student number 000772198,
 * certify that all code submitted is my own work;
 * that I have not copied it from any other source. 
 * I also certify that I have not allowed my work to be copied by others.
 * March 3rd, 2019
 * 
 * @author Jungwon Lim, #000772198
 */
public class CardFlipping extends Application {

    // *** name constants
    /** Canvas Width **/
    final double CANVAS_WIDTH = 505;
    /** Canvas Height **/
    final double CANVAS_HEIGHT = 305;
    /** Maximum width of the elements on the left pane **/
    final double LEFT_MAX_WIDTH = 100;
    /** DELAY for pause in millisecond **/
    final int DELAY = 500;
    /** card icon images size **/
    final double ICON_SIZE = 50;
    /** PADDING between graphic elements **/
    final int PADDING = 5;
    /** Images for button **/
    final Image IMG_BACK = new Image("back.png", ICON_SIZE, ICON_SIZE, true, false); // unusing 0 index (in CardSet class, 0 index is used to determine none assigned card kind)
    final Image IMG_FRONT1 = new Image("fruit1.png", ICON_SIZE, ICON_SIZE, true, false);
    final Image IMG_FRONT2 = new Image("fruit2.png", ICON_SIZE, ICON_SIZE, true, false);
    final Image IMG_FRONT3 = new Image("fruit3.png", ICON_SIZE, ICON_SIZE, true, false);
    final Image IMG_FRONT4 = new Image("fruit4.png", ICON_SIZE, ICON_SIZE, true, false);
    final Image IMG_FRONT5 = new Image("fruit5.png", ICON_SIZE, ICON_SIZE, true, false);
    final Image IMG_FRONT6 = new Image("fruit6.png", ICON_SIZE, ICON_SIZE, true, false);
    final Image IMG_FRONT7 = new Image("fruit7.png", ICON_SIZE, ICON_SIZE, true, false);
    final Image IMG_FRONT8 = new Image("fruit8.png", ICON_SIZE, ICON_SIZE, true, false);
    final Image[] IMGS_FRONT = {IMG_BACK, IMG_FRONT1, IMG_FRONT2, IMG_FRONT3, IMG_FRONT4, IMG_FRONT5, IMG_FRONT6, IMG_FRONT7, IMG_FRONT8};
    /** ticktock sound **/
    final AudioClip SOUND_TICKTOCK = new AudioClip(getClass().getResource("ticktock.wav").toString());
    /** fanfare sound **/
    final AudioClip SOUND_FANFARE = new AudioClip(getClass().getResource("tada.wav").toString());
    
    // *** Instance Variables for View Components and Model
    /** root pane for the GUI **/
    private BorderPane root;
    
    // for center (cards)
    /** grid pane for card layout **/
    private GridPane pnlCard;
    /** first clicked button **/
    private Button btnFirst;
    /** second clicked button **/
    private Button btnSecond;
    /** array of card button objects **/
    private Button[] btnCards;
    /** CardSet model object **/
    private CardSet set;

    // for left pane
    /** text for instruction card number field **/
    private Label lblCardNum;
    /** card number entering field **/
    private TextField txtCardNumInput;
    /** submit button for card number entering field **/
    private Button btnCardNumSubmit;

    // for right pane
    /** Player model object **/
    private Player player;
    /** text for current record **/
    private Label lblCurrentRecord;
    /** text for best record **/
    private Label lblBestRecord;
    /** replay button **/
    private Button replay;
    /** exit button **/
    private Button exit;
    /** value of current record **/
    private double currentRecord;
    /** value of best record of all time **/
    private double bestRecord;
    
    // for bottom pane
    /** display message **/
    private Label lblMessage;
    
    // extra variables
    int numOfCards;
    int i;

    
    
    /** timer for reflipping cards when matching failed **/
    final Timeline timer = new Timeline(
                    new KeyFrame(
                            Duration.millis(DELAY),
                            event -> {
                                player.lap();
                                lblCurrentRecord.setText("" + player.getElapsedRecord() / 1000);
                            }
                    )
            );
    
    // *** Private Event Handlers and Helper Methods
    /** Inner class for handling multiple card buttons **/
    private class HandlerCard implements EventHandler<ActionEvent> {

        // *** instance variables for inner handler class
        /** card index of the event button **/
        private int cardIndex;
        /** index of first clicked card **/
        private int firstCardIndex;
        /** index of second clicked card **/
        private int secondCardIndex;
        /** number(kind) of current card button **/
        private int cardNum; 
       /** number(kind) of first clicked card **/
        private int firstCardNum;
        /** number(kind) of second clicked card **/
        private int secondCardNum;

        /**
         * Constructor for Inner handler class
         * @param cardIndex position of the card button
         */
        public HandlerCard(int cardIndex) {
            this.cardIndex = cardIndex;
        }

        /**
         * actual handler method for Inner handler class
         * @param e
         */
        @Override
        public void handle(ActionEvent e) {
            cardNum = set.getNthCardNum(cardIndex);
            if (!set.isNthCardFlipped(cardIndex)) {
                
                
                if (player.getStartTime() == 0 && !set.isFirstFlipped()) {   // if it is the first time to play game, start timer
                    
                    player.start();
                    lblMessage.setText("");
                    
                    timer.setCycleCount( Animation.INDEFINITE );
                    timer.play();
                }
                
                if (!set.isFirstFlipped()) {  // if no card is flipped yet and it is the first one, set the first card
                    firstCardIndex = cardIndex;
                    firstCardNum = cardNum;
                    set.setIsFirstFlipped(true);
                    set.setFirst(cardIndex);
                    set.setNthCardIsFlippedTrue(cardIndex);  // flip the first card

                    btnFirst = (Button) e.getSource();
                    btnFirst.setGraphic(new ImageView(IMGS_FRONT[firstCardNum]));  // set the img at the clicked card as the number of the card (front)

                } else {    // if first card is already flipped and it is the second one
                    secondCardIndex = cardIndex;
                    secondCardNum = cardNum;
                    set.setSecond(cardIndex);
                    set.setNthCardIsFlippedTrue(cardIndex);  // flip the second card

                    btnSecond = (Button) e.getSource();
                    btnSecond.setGraphic(new ImageView(IMGS_FRONT[secondCardNum]));  // set the img at the clicked card as the number of the card (front)
                    
                    if (set.isMatching()) { // if the two flipped card are same number
                        if (set.isAllMatched()) {   // if all card is matched,
                            lblMessage.setText("Congraturation! Well Done!!");
                            timer.stop();
                            SOUND_TICKTOCK.stop();
                            SOUND_FANFARE.play();
                            player.finish();
                            currentRecord = player.getFinishRecord() / 1000;
                            bestRecord = player.getBestRecord() / 1000;
                            lblCurrentRecord.setText("Current Record:\n" + currentRecord);
                            lblBestRecord.setText("Best Record:\n" + bestRecord);
                            if (player.getIsNewBestRecord()) {
                                lblMessage.setText("New Best Record! Congraturation!!");
                            }
                            replay.setVisible(true);
                            exit.setVisible(true);
                        }
                    } else {
                        // if wrong, pause one second(show the front) and set to back again
                        for (Button btnCard : btnCards) {
                            btnCard.setDisable(true);       // disable all buttons until pausing to prevent multple trys are happning together
                        }
                        // pause to show the cards when try was wrong
                        PauseTransition pause = new PauseTransition(Duration.millis(DELAY));
                        pause.setOnFinished(event -> {
                            // if wrong, return back the cards
                            btnFirst.setGraphic(new ImageView(IMG_BACK));
                            btnSecond.setGraphic(new ImageView(IMG_BACK));
                            for (Button btnCard : btnCards) {
                                btnCard.setDisable(false);  // enable all buttons after pausing
                            }
                        });
                        pause.play();
                        set.setNthCardIsFlippedFalse(set.getFirst());
                        set.setNthCardIsFlippedFalse(set.getSecond());
                    }
                    // either right or wrong, reset this try
                    set.setIsFirstFlipped(false);
                    set.setFirst(numOfCards);
                    set.setSecond(numOfCards);

                }
            }
        }

    }

    /**
     * handler for card number submit button on left pane
     * @param e
     */
    private void handlerBtnCardNumSubmit(ActionEvent e) {
        try {
            numOfCards = Integer.parseInt(txtCardNumInput.getText());
        } catch (NumberFormatException ee) {
            lblMessage.setText("Enter a even number between 4-16!");
        }
        
        if (numOfCards == 4 || numOfCards == 6 || numOfCards == 8
                || numOfCards == 12 || numOfCards == 16) {

            // once card number is selected, disable the button and field, to prevent changing
            btnCardNumSubmit.setDisable(true);
            txtCardNumInput.setDisable(true);
            lblMessage.setText("Game Start!");
            SOUND_TICKTOCK.play();

            // *** Card section
            pnlCard = new GridPane(); // grid pane for card layout
            pnlCard.setPadding(new Insets(PADDING, 0, 0, 0));
            pnlCard.setHgap(PADDING);
            pnlCard.setVgap(PADDING);
            root.setCenter(pnlCard);

            // 1. Create the model
            set = new CardSet(numOfCards);
            set.setFirst(numOfCards);
            set.setSecond(numOfCards);

            // 2. Create the GUI components
            btnCards = new Button[numOfCards];
            for (i = 0; i < numOfCards; i++) {
                btnCards[i] = new Button();
                btnCards[i].setGraphic(new ImageView(IMG_BACK));
            }

            // 3. Add components to the root
            for (i = 0; i < numOfCards; i++) {
                pnlCard.add(btnCards[i], i % 4, i / 4);
            }

            // 5. Add Event Handlers and do final setup
            for (i = 0; i < numOfCards; i++) {
                btnCards[i].setOnAction(new HandlerCard(i));
            }

        }

    }

    /**
     * handler for replay button on right pane
     * @param e
     */
    private void handlerReplay(ActionEvent e) {
        // Create the new CardSet for new replay
        set = new CardSet(numOfCards);
        for (i = 0; i < numOfCards; i++) {
            btnCards[i].setGraphic(new ImageView(IMG_BACK)); // set the cards to the back again
        }
        set.setFirst(numOfCards);
        set.setSecond(numOfCards);
        lblMessage.setText("");
        replay.setVisible(false);   // after clicked, make replay, exit button disappeared
        exit.setVisible(false);
        SOUND_TICKTOCK.play();
    }

    /**
     * handler for exit button on right pane
     * @param e
     */
    private void handlerExit(ActionEvent e) {
        // after show message for a second, exit the program
        lblMessage.setText("Bye!");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            System.exit(0);
        });
        pause.play();
        
    }

    /**
     * method where components and the model are created and events and handlers are added
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        // *** creat window
        root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT); // set the size here
        stage.setTitle("Card Flipping"); // set the window title here
        stage.setScene(scene);

        
        
        // *** card numbers selecting section (left)
        VBox pnlLeft = new VBox();
        root.setLeft(pnlLeft);
        pnlLeft.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        pnlLeft.setSpacing(PADDING);

        // 2. Create the GUI components
        lblCardNum = new Label("Please enter number of cards you want to play in a even number between 4-16");
        txtCardNumInput = new TextField("");
        btnCardNumSubmit = new Button("Start!");

        // 3. Add components to the root
        pnlLeft.getChildren()
                .addAll(lblCardNum, txtCardNumInput, btnCardNumSubmit);

        // 4. Configure the components (colors, fonts, size, location)
        lblCardNum.setMaxWidth(LEFT_MAX_WIDTH);
        lblCardNum.setWrapText(
                true);
        lblCardNum.setTextFill(Color.WHITE);
        txtCardNumInput.setMaxWidth(LEFT_MAX_WIDTH);

        // 5. Add Event Handlers and do final setup
        btnCardNumSubmit.setOnAction(
                this::handlerBtnCardNumSubmit);

        
        
        // *** user status section (right)
        VBox pnlRight = new VBox();
        root.setRight(pnlRight);
        pnlRight.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        pnlRight.setSpacing(PADDING);

        // 1. Create the model
        player = new Player();

        // 2. Create the GUI components
        lblCurrentRecord = new Label("Current Time:");
        lblBestRecord = new Label("Best Record:");
        replay = new Button("Replay!");
        exit = new Button("Exit!");

        // 3. Add components to the root
        pnlRight.getChildren().addAll(lblCurrentRecord, lblBestRecord, replay, exit);

        // 4. Configure the components (colors, fonts, size, location)
        lblCurrentRecord.setTextFill(Color.WHITE);
        lblBestRecord.setTextFill(Color.WHITE);
        replay.setVisible(false);   // first program launched, make buttons invisible
        exit.setVisible(false);

        // 5. Add Event Handlers and do final setup
        replay.setOnAction(this::handlerReplay);
        exit.setOnAction(this::handlerExit);
        
        
        
        // *** message bar (bottom)
        Pane pnlBottom = new Pane();
        root.setBottom(pnlBottom);
        lblMessage = new Label("");
        lblMessage.setFont(new Font("Impact", 36));
        lblMessage.setTextFill(Color.YELLOW);
        lblMessage.setMinWidth(CANVAS_WIDTH);
        lblMessage.setAlignment(Pos.CENTER);
        pnlBottom.getChildren().add(lblMessage);
        
        SOUND_TICKTOCK.setCycleCount(INDEFINITE);    // loop ticktock sound until game ends
        
        
        
        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     * @param args unused
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }
}
