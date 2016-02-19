package db;

import java.util.ArrayList;
import java.util.List;

import model.Restaurant;

import org.json.JSONArray;
import org.json.JSONObject;

import yelp.YelpAPI;

public class DBConnection {
    public JSONArray SearchRestaurants(String userId, double lat, double lon) {
   	 try {
   		 YelpAPI api = new YelpAPI();
   		 JSONObject response = new JSONObject(
   				 api.searchForBusinessesByLocation(lat, lon));
   		 JSONArray array = (JSONArray) response.get("businesses");

   		 List<JSONObject> list = new ArrayList<>();

   		 for (int i = 0; i < array.length(); i++) {
   			 JSONObject object = array.getJSONObject(i);
   			 Restaurant restaurant = new Restaurant(object);
   			 JSONObject obj = restaurant.toJSONObject();
   			 list.add(obj);
   		 }
   		 return new JSONArray(list);
   	 } catch (Exception e) {
   		 System.out.println(e.getMessage());
   	 }
   	 return null;
    }
}

