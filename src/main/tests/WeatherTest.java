import org.junit.jupiter.api.Assertions;
import com.example.weatherexample.WeatherFetch;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class    WeatherTest {
   @Test
   void testFetchWeather() {
       try {
           double latitude = 40.7128;
           double longitude = -74.0060;
           JSONObject weatherData = WeatherFetch.fetchWeather(latitude, longitude);
           Assertions.assertNotNull(weatherData, "Weather data should not be null");
           assertEquals(weatherData.getDouble("lat"), latitude, 0.1);
           assertEquals(weatherData.getDouble("lon"), longitude, 0.1);

       } catch (IOException e) {
           e.printStackTrace();
       }
     //  @Test
           //    void testConvertCityStateToLatLon() {
      //     try {

      // }
       }
   }

