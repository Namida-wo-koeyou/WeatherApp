package com.example.weatherexample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;

import static com.example.weatherexample.WeatherFetch.fetchWeatherByCityState;

public class WeatherApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        TextField cityField = new TextField();
        cityField.setPromptText("Enter City");
        TextField stateField = new TextField();
        stateField.setPromptText("Enter State");
        Button fetchButton = new Button("Fetch Weather");


        Label temperatureLabel = new Label();
        temperatureLabel.setText("Temp:");
        Label weatherLabel = new Label();
        weatherLabel.setText("Current Weather: ");
        Label weatherDescriptionLabel = new Label();
        weatherDescriptionLabel.setText("Description: ");
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(20));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.add(cityField, 0, 0);
        gridpane.add(stateField, 1, 0);
        gridpane.add(fetchButton, 0, 1, 2, 1);
        gridpane.add(temperatureLabel, 1, 1, 2, 1);
        gridpane.add(weatherLabel, 0, 2, 2, 1);
        gridpane.add(weatherDescriptionLabel, 0, 3, 2, 1);
        fetchButton.setOnAction(event -> {
            String city = cityField.getText();
            String state = stateField.getText();
            JSONObject weatherData = null;
            try {
                weatherData = fetchWeatherByCityState(city, state);
                double temperature = weatherData
                        .getJSONObject("current")
                        .getDouble("temp");
                String description = weatherData
                        .getJSONObject("current")
                        .getJSONArray("weather")
                        .getJSONObject(0)
                        .getString("description");
                String currentWeather = weatherData
                        .getJSONObject("current")
                        .getJSONArray("weather")
                        .getJSONObject(0)
                        .getString("main");

                weatherLabel.setText("Current Weather: " +currentWeather);



                temperatureLabel.setText("Temp: " + temperature + "°F");

                weatherDescriptionLabel.setText("description: "+ description);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        });
        Scene scene = new Scene(gridpane, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Weather App");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}