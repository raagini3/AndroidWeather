package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Utils;
import model.Place;
import model.Weather;

/**
 * Created by Gladdstone on 2/26/2017.
 */

public class JSONWeatherParser {

    public static Weather getWeather(String data) {
        Weather weather = new Weather();

        try {

            JSONObject jsonObject = new JSONObject(data);

            Place place = new Place();

            // coordinates
            JSONObject coordObj = Utils.getObject("coord", jsonObject);
            place.setLat(Utils.getFloat("lat", coordObj));
            place.setLon(Utils.getFloat("lon", coordObj));

            // weather
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(Utils.getInt("id", jsonWeather));
            weather.currentCondition.setCondition(Utils.getString("main", jsonWeather));
            weather.currentCondition.setDescription(Utils.getString("description", jsonWeather));
            weather.currentCondition.setIcon(Utils.getString("icon", jsonWeather));

            // wind
            JSONObject windObj = Utils.getObject("wind", jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed", windObj));
            weather.wind.setDeg(Utils.getFloat("deg", windObj));

            // clouds
            JSONObject cloudObj = Utils.getObject("clouds", jsonObject);
            weather.clouds.setPrecipitation(Utils.getInt("all", cloudObj));

            // place
            //JSONObject sysObj = Utils.getObject("sys", jsonObject);
            //place.setCountry(Utils.getString("country", sysObj));
            // place.setLastupdate(Utils.getInt("dt", jsonObject));    TODO
            //place.setSunrise(Utils.getInt("sunrise", sysObj));
            //place.setSunset(Utils.getInt("sunset", sysObj));
            // place.setCity(Utils.getString("name", jsonObject));  TODO
            //weather.place = place;

            return weather;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
