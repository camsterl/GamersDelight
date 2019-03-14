package edu.miracostacollege.cs134.gamersdelight.model;

import android.content.Context;
import android.util.Log;
import android.widget.ListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.miracostacollege.cs134.gamersdelight.GameListAdapter;

/**
 * Class loads Game data from a formatted JSON (JavaScript Object Notation) file.
 * Populates data model (Game) with data.
 */

public class JSONLoader {

    public static final String TAG = JSONLoader.class.getSimpleName();

    /**
     * Loads JSON data from a file in the assets directory.
     * @param context The activity from which the data is loaded.
     * @throws IOException If there is an error reading from the JSON file.
     */
    public static List<Game> loadJSONFromAsset(Context context) throws IOException {
        List<Game> allGames = new ArrayList<>();
        String json;
            InputStream is = context.getAssets().open("Games.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        try {
            JSONObject jsonRootObject = new JSONObject(json);
            //Done: Complete the name of the root object in the JSON file

            JSONArray allGamesJSON = jsonRootObject.getJSONArray("Games");

            // Done: Loop through the root object array and
            int length = allGamesJSON.length();

            Game game;
            JSONObject gamesJSON;
            String name;
            String description;
            double rating;
            String imageName;

            for(int i =0; i< length; i++)
            {
                gamesJSON = allGamesJSON.getJSONObject(i);
                name = gamesJSON.getString("Name");
                description = gamesJSON.getString("Description");
                imageName = gamesJSON.getString("ImageName");
                rating = gamesJSON.getDouble("Rating");

                game = new Game(name, description, (float) rating, imageName);
                allGames.add(game);

            }
            // Done: Extract each single Game from the JSON file.
            // Done: Create an object to represent each game, then
            // Done: add each Game to the list.
        }
        catch (JSONException e)
        {
            Log.e(TAG, e.getMessage());
        }

        return allGames;
    }
}
