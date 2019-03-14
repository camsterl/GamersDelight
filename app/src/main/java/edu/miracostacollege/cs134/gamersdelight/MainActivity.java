package edu.miracostacollege.cs134.gamersdelight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;

import java.io.IOException;
import java.util.List;

import edu.miracostacollege.cs134.gamersdelight.model.Game;
import edu.miracostacollege.cs134.gamersdelight.model.JSONLoader;

public class MainActivity extends AppCompatActivity {


    private List<Game> gamesList;
    private GameListAdapter gamesListAdapter;
    private ListView gamesListView;

    EditText nameEditText;
    EditText descriptionEditText;
    RatingBar gameRatingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameEditText = findViewById(R.id.nameEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        gameRatingBar = findViewById(R.id.gameRatingBar);
        gamesListView = findViewById(R.id.gameListView);



        try {
            gamesList = JSONLoader.loadJSONFromAsset(this);
        } catch (IOException e) {
            Log.e("TAG", e.getMessage());
        }

    gamesListAdapter = new GameListAdapter(this, R.layout.game_list_item, gamesList);
        gamesListView.setAdapter(gamesListAdapter);



        // TODO: Connect the ListView with the layout
        // TODO:  Populate all games list using the JSONLoader
        // TODO:  Create a new ListAdapter connected to the correct layout file and list
        // TODO:  Connect the ListView with the ListAdapter
    }

    public void viewGameDetails(View view) {

        // TODO: Use an Intent to start the GameDetailsActivity with the data it needs to correctly inflate its views.
        int pos = (int) view.getTag();
        Game selectedGame = gamesList.get(pos);


        Intent intent = new Intent(this, GameDetailsActivity.class);
        intent.putExtra("name", selectedGame.getName());
        intent.putExtra("description", selectedGame.getDescription());
        intent.putExtra("rating", selectedGame.getRating());
        intent.putExtra("imageName", selectedGame.getImageName());


        startActivity(intent);
    }


    public void addGame(View view) {
        // TODO:  Read information from EditTexts and RatingBar,
       String name =  nameEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        double rate = gameRatingBar.getRating();


        Game game = new Game(name, description, (float)rate);
        // TODO:  Create a new Game object then add it to the list
        gamesList.add(game);
        // TODO:  Make sure the list adapter is notified
        gamesListAdapter.notifyDataSetChanged();
        // TODO:  Clear all entries the user made (edit text and rating bar)
        //gamesListAdapter.clear();
    }


}




