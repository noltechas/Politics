package com.example.politicsgame;

import com.example.politicsgame.Events.Event;
import com.example.politicsgame.Events.EventReader;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class GameScreen extends Application {

    private static ArrayList<Event> events = EventReader.readEventsFromJson("src/main/resources/events.json");
    private static int currentEventIndex = 0;

    private TextArea eventTextArea; // Updated: Declared as an instance variable
    private ImageView nextButton; // Updated: Declared as an instance variable
    private StackPane root; // Updated: Declared as an instance variable
    private City affectedCity; // Updated: Declared as an instance variable

    @Override
    public void start(Stage primaryStage) throws Exception {
        Collections.shuffle(events);

        // Retrieve the party name from the main class
        String partyName = Main.party.getName();

        // Initialize the player's party
        Party playerParty = new Party(partyName);
        GameMap gameMap = GameMap.getInstance();
        GameState gameState = new GameState();

        primaryStage.setTitle("Map Display");
        try {
            Font.loadFont(new FileInputStream(new File("src/main/resources/Ancient.ttf")), 10);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Load the image
        Image mapImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/map.png")));

        // Get screen size
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Create an ImageView to display the image
        ImageView imageView = new ImageView(mapImage);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(screenBounds.getHeight());

        // Create a layout and add the ImageView to it
        root = new StackPane();
        root.getChildren().add(imageView);

        // Create a VBox container for the text area
        VBox textBoxContainer = new VBox();
        textBoxContainer.setTranslateX(-564); // Adjust the X coordinate as necessary
        textBoxContainer.setTranslateY(screenBounds.getHeight() - 10 - 200); // Adjust the Y coordinate as necessary
        textBoxContainer.setSpacing(10); // Adjust the spacing between elements as necessary
        // Set the maximum width of the textBoxContainer
        textBoxContainer.setMaxWidth(300); // Adjust the width as necessary

        // Create a TextArea for displaying current events
        eventTextArea = new TextArea();
        eventTextArea.setEditable(false);
        eventTextArea.setPrefWidth(10); // Adjust the width as necessary
        eventTextArea.setPrefRowCount(10); // Set the number of visible rows
        eventTextArea.setWrapText(true); // Enable text wrapping

        // Get cities from GameMap
        ArrayList<City> cities = GameMap.getCities();

        // Find the capitol
        City capitol = null;
        for (City city : cities) {
            if (capitol == null || city.getPopulation() > capitol.getPopulation()) {
                capitol = city;
            }
        }
        Objects.requireNonNull(capitol).setCapitol();

        Label genLabel = new Label("Generation " + GameState.getGeneration());
        genLabel.setFont(new Font("Ancient", 60));
        genLabel.setTranslateX(1300 - screenBounds.getWidth() / 2);
        genLabel.setTranslateY(40 - screenBounds.getHeight() / 2);
        root.getChildren().add(genLabel);
        Label kingdomLabel = new Label(GameMap.getKingdomName());
        kingdomLabel.setFont(new Font("Ancient", 60));
        kingdomLabel.setTranslateX(721 - screenBounds.getWidth() / 2);
        kingdomLabel.setTranslateY(40 - screenBounds.getHeight() / 2);
        kingdomLabel.setTextAlignment(TextAlignment.LEFT);
        root.getChildren().add(kingdomLabel);

        // Add city names to the map
        for (City city : cities) {
            String cityName = city.getName();

            // Create a label for the city name
            Label cityLabel = new Label(cityName);
            cityLabel.setFont(new Font("Ancient", 32));

            // Get the party with most support in the city
            Party mostSupportedParty = null;
            for (Party party : GameMap.parties)
                if (mostSupportedParty == null || party.getSupportPercentage(city) > mostSupportedParty.getSupportPercentage(city)) {
                    mostSupportedParty = party;
                }

            /// Create a text for the city name instead of a label
            Text cityText = new Text(cityName);
            cityText.setFont(new Font("Ancient", 32));

            // Set the color of the text to the color of the party with most support
            if (mostSupportedParty != null) {
                cityText.setFill(mostSupportedParty.getColor());
            }

            // Add a stroke
            cityText.setStroke(Color.BLACK);
            cityText.setStrokeWidth(0.7);  // Adjust the stroke width as necessary

            // Position the text according to the city's coordinates
            cityText.setTranslateX(city.getX() - screenBounds.getWidth() / 2);
            cityText.setTranslateY(city.getY() - screenBounds.getHeight() / 2);

            // Set on click event
            cityText.setOnMouseClicked(e -> new CityStatsDisplay(city).displayCityStats());

            // Add the text to the root pane
            root.getChildren().add(cityText);

            // Draw a red star for the capitol
            if (city == capitol) {
                Polygon star = createStar(Color.MAROON, 17, 6, 0.5);
                star.setTranslateX(city.getX() - screenBounds.getWidth() / 2);
                star.setTranslateY(city.getY() - screenBounds.getHeight() / 2 + 40); // Adjust as necessary
                root.getChildren().add(star);
            }
        }

        Image nextButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/next.png")));
        nextButton = new ImageView(nextButtonImage);
        nextButton.setTranslateX(637); // Adjust the X coordinate as necessary
        nextButton.setTranslateY(screenBounds.getHeight() - nextButtonImage.getHeight() + 100); // Adjust the Y coordinate as necessary
        nextButton.setFitWidth(130);
        nextButton.setFitHeight(130);
        nextButton.setOnMouseClicked(e -> {
            if (currentEventIndex < events.size()) {
                Event event = events.get(currentEventIndex);
                displayEventDescription(event); // Call the updated method
                currentEventIndex++;
                nextButton.setVisible(false); // Hide the button after clicking
            }
        });
        root.getChildren().add(nextButton);

        textBoxContainer.getChildren().add(eventTextArea);
        root.getChildren().add(textBoxContainer);

        // Create a scene and add the layout to it
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Polygon createStar(Color color, double radius, double innerRadius, double rotation) {
        Polygon star = new Polygon();
        double angle = 2 * Math.PI / 5; // Five points

        for (int i = 0; i < 10; i++) {
            double r = (i % 2 == 0) ? radius : innerRadius;
            double theta = angle * i + rotation;
            double x = r * Math.cos(theta);
            double y = r * Math.sin(theta);
            star.getPoints().addAll(x, y);
        }

        star.setFill(color);
        return star;
    }

    private void displayEventDescription(Event event) {
        String description = event.getDescription();

        // Check if the cityName field is empty
        if (event.getCityName() == null && event.getLocation().equals("random_city")) {
            // Choose a random city (not a capitol city) and assign it to cityName
            ArrayList<City> cities = GameMap.getCities();
            City randomCity = getRandomCity(cities);
            event.setCityName(randomCity.getName());
            event.setLocation(randomCity.getName()); // Update the location string with the random city's name
            createExclamationMark(randomCity); // Create exclamation mark for random city
        }

        // Append the location to the description if applicable
        String location = event.getLocation();
        if (location != null) {
            description += location;
        }

        // Display the updated description
        eventTextArea.appendText(description + "\n");

        // Create a pause transition for the delay
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e -> {
            // Display the event window after the delay
            EventWindow eventWindow = new EventWindow(event);
            eventWindow.displayWindow();
        });
        delay.play();
    }

    private City getRandomCity(ArrayList<City> cities) {
        ArrayList<City> nonCapitolCities = new ArrayList<>(cities);
        nonCapitolCities.removeIf(City::isCapitol);

        Random random = new Random();
        return nonCapitolCities.get(random.nextInt(nonCapitolCities.size()));
    }

    private void createExclamationMark(City city) {
        Text exclamationMark = new Text("!");
        exclamationMark.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        exclamationMark.setFill(Color.MAROON);
        exclamationMark.setTranslateX(city.getX() - root.getScene().getWidth() / 2);
        exclamationMark.setTranslateY(city.getY() - root.getScene().getHeight() / 2 - 40); // Adjust as necessary
        root.getChildren().add(exclamationMark);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
