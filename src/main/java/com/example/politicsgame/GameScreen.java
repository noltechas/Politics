package com.example.politicsgame;

import com.example.politicsgame.CityStatsDisplay;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

public class GameScreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Retrieve the party name from the main class
        String partyName = Main.partyName;

        // Initialize the player's party
        Party playerParty = new Party(partyName);
        GameMap gameMap = new GameMap();
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
        StackPane root = new StackPane();
        root.getChildren().add(imageView);

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

            // Position the label according to the city's coordinates
            cityLabel.setTranslateX(city.getX() - screenBounds.getWidth() / 2);
            cityLabel.setTranslateY(city.getY() - screenBounds.getHeight() / 2);

            // Set on click event
            cityLabel.setOnMouseClicked(e -> new CityStatsDisplay(city).displayCityStats());

            // Add the label to the root pane
            root.getChildren().add(cityLabel);

            // Draw a red star for the capitol
            if (city == capitol) {
                Polygon star = createStar(Color.MAROON, 17, 6, 0.5);
                star.setTranslateX(city.getX() - screenBounds.getWidth() / 2);
                star.setTranslateY(city.getY() - screenBounds.getHeight() / 2 + 40); // Adjust as necessary
                root.getChildren().add(star);
            }
        }

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

    public static void main(String[] args) {
        launch(args);
    }
}
