package com.example.politicsgame;

import com.example.politicsgame.Events.Event;
import com.example.politicsgame.Events.EventReader;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

import static javafx.scene.paint.Color.rgb;

public class GameScreen extends Application {

    private static ArrayList<Event> events = EventReader.readEventsFromJson("src/main/resources/events.json");
    private static int currentEventIndex = 0;

    private TextArea eventTextArea;
    private ImageView nextButton;
    private StackPane root;
    private City affectedCity;

    public static Event getCurrentEvent() {
        return events.get(currentEventIndex);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Collections.shuffle(events);

        String partyName = Main.party.getName();

        Party playerParty = new Party(partyName, true);
        GameMap gameMap = GameMap.getInstance();
        GameState gameState = new GameState();

        primaryStage.setTitle(GameMap.getKingdomName());
        try {
            Font.loadFont(new FileInputStream(new File("src/main/resources/Ancient.ttf")), 10);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Font.loadFont(new FileInputStream(new File("src/main/resources/Deutsch.ttf")), 10);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Image mapImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/map.png")));

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        ImageView imageView = new ImageView(mapImage);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(screenBounds.getHeight());

        root = new StackPane();
        root.getChildren().add(imageView);

        VBox textBoxContainer = new VBox();
        textBoxContainer.setTranslateX(-564);
        textBoxContainer.setTranslateY(screenBounds.getHeight() - 10 - 200);
        textBoxContainer.setSpacing(10);
        textBoxContainer.setMaxWidth(300);

        eventTextArea = new TextArea();
        eventTextArea.setEditable(false);
        eventTextArea.setPrefWidth(10);
        eventTextArea.setPrefRowCount(10);
        eventTextArea.setWrapText(true);

        ArrayList<City> cities = GameMap.getCities();
        ArrayList<MountainRange> mountainRanges = GameMap.getMountainRanges();

        City capitol = null;
        for (City city : cities) {
            if (capitol == null || city.getPopulation() > capitol.getPopulation()) {
                capitol = city;
            }
        }
        Objects.requireNonNull(capitol).setCapitol();

        // Calculate the total population and party supports
        for (City city : GameMap.getCities()) {
            for (int i = 0; i < GameMap.parties.size(); i++) {
                int supporters = 0;
                supporters += (GameMap.parties.get(i).getSupportPercentage(city)/100) * city.getPopulation();
                GameMap.parties.get(i).setSupporters(GameMap.parties.get(i).getSupporters() + supporters);
            }
        }

        ArrayList<Party> sortedParties = new ArrayList<>(GameMap.parties);
        Collections.sort(sortedParties, (party1, party2) -> {
            double support1 = party1.getSupporters();
            double support2 = party2.getSupporters();
            return Double.compare(support2, support1); // Sort in descending order
        });

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
        DropShadow outlineEffect = new DropShadow();
        outlineEffect.setColor(sortedParties.get(0).getColor());
        outlineEffect.setRadius(1);
        outlineEffect.setSpread(0.2);
        kingdomLabel.setEffect(outlineEffect);
        root.getChildren().add(kingdomLabel);

        VBox partyContainer = new VBox();
        partyContainer.setTranslateX(10);
        partyContainer.setTranslateY(screenBounds.getHeight() / 2 - 400);
        partyContainer.setSpacing(0);

        int kingdomPopulation = 0;
        for(int i = 0; i < GameMap.getCities().size(); i++){
            kingdomPopulation += GameMap.getCities().get(i).getPopulation();
        }

        // Display party labels and support percentages
        for (int i = 0; i < sortedParties.size(); i++) {
            Party party = sortedParties.get(i);
            String partyName1 = party.getName();
            if (partyName1.startsWith("The ")) {
                partyName1 = partyName1.substring(4); // Remove the leading "The "
            }
            Label partyLabel = new Label(partyName1);

            partyLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/Deutsch.ttf"), 16));
            partyLabel.setTextFill(party.getColor());

            String supportText = String.format("%.1f%%",((float)party.getSupporters()/(float)kingdomPopulation)*100);

            // Add the support percentage text to the party label
            partyLabel.setText(partyName1 + " - " + supportText);

            // Add an outline to the party label
            partyLabel.setStyle("-fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");

            partyContainer.getChildren().add(partyLabel);
        }

        root.getChildren().add(partyContainer);

        for (City city : cities) {
            String cityName = city.getName() + " " + (city.getI()+1);

            Party mostSupportedParty = null;
            for (Party party : GameMap.parties) {
                if (mostSupportedParty == null || party.getSupportPercentage(city) > mostSupportedParty.getSupportPercentage(city)) {
                    mostSupportedParty = party;
                }
            }

            Text cityText = new Text(cityName);
            cityText.setFont(new Font("Ancient", 32));

            if (mostSupportedParty != null) {
                cityText.setFill(mostSupportedParty.getColor());
            }

            cityText.setStroke(Color.BLACK);
            cityText.setStrokeWidth(0.5);
            cityText.setTranslateX(city.getX() - screenBounds.getWidth() / 2);
            cityText.setTranslateY(city.getY() - screenBounds.getHeight() / 2);

            cityText.setOnMouseClicked(e -> new CityStatsDisplay(city).displayCityStats());

            root.getChildren().add(cityText);

            if (city == capitol) {
                Polygon star = createStar(Color.MAROON, 17, 6, 0.5);
                star.setTranslateX(city.getX() - screenBounds.getWidth() / 2);
                star.setTranslateY(city.getY() - screenBounds.getHeight() / 2 + 40);
                root.getChildren().add(star);
            }
        }

        for (MountainRange mountainRange : mountainRanges) {
            String mountainRangeName = mountainRange.getName();

            Text mountainRangeText = new Text(mountainRangeName);
            mountainRangeText.setFont(Font.loadFont(getClass().getResourceAsStream("/Deutsch.ttf"), mountainRange.getSize()));
            mountainRangeText.setFill(rgb(140,140,140));
            mountainRangeText.setStroke(Color.BLACK);
            mountainRangeText.setStrokeWidth(0.65);

            double x = mountainRange.getX(); // Get the x position of the mountain range
            double y = mountainRange.getY(); // Get the y position of the mountain range
            double rotation = mountainRange.getRotation(); // Get the rotation angle

            mountainRangeText.setTranslateX(x - screenBounds.getWidth() / 2);
            mountainRangeText.setTranslateY(y - screenBounds.getHeight() / 2);
            mountainRangeText.setRotate(rotation);

            root.getChildren().add(mountainRangeText);
        }


        Image nextButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/next.png")));
        nextButton = new ImageView(nextButtonImage);
        nextButton.setTranslateX(637);
        nextButton.setTranslateY(screenBounds.getHeight() - nextButtonImage.getHeight() + 100);
        nextButton.setFitWidth(130);
        nextButton.setFitHeight(130);
        nextButton.setOnMouseClicked(e -> {
            if (currentEventIndex < events.size()) {
                Event event = events.get(currentEventIndex);
                displayEventDescription(event);
                currentEventIndex++;
                nextButton.setVisible(false);
            }
        });
        root.getChildren().add(nextButton);

        textBoxContainer.getChildren().add(eventTextArea);
        eventTextArea.appendText("Welcome to the Kingdom of " + GameMap.getKingdomName() + "!\n");
        root.getChildren().add(textBoxContainer);

        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Polygon createStar(Color color, double radius, double innerRadius, double rotation) {
        Polygon star = new Polygon();
        double angle = 2 * Math.PI / 5;

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

        if (event.getCityName() == null && event.getLocation().equals("random_city")) {
            ArrayList<City> cities = GameMap.getCities();
            City randomCity = getRandomCity(cities);
            event.setCityName(randomCity.getName());
            event.setLocation(randomCity.getName());
            createExclamationMark(randomCity);
        }

        String location = event.getLocation();

        if (!event.getLocation().equals("whole_kingdom"))
            description += location;

        eventTextArea.appendText(description + "\n");

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e -> {
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
        exclamationMark.setTranslateY(city.getY() - root.getScene().getHeight() / 2 - 40);
        root.getChildren().add(exclamationMark);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
