package edu.miracostacollege.cs134.gamersdelight;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class GameDetailsActivity extends AppCompatActivity {

    ImageView gameDetailsImageView;
    TextView gameDetailsName;
    TextView gameDetailsDescription;
    RatingBar gameDetailsRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);


        gameDetailsName = findViewById(R.id.gameDetailsNameTextView);
        gameDetailsDescription = findViewById(R.id.gameDetailsDescriptionTextView);
        gameDetailsRate = findViewById(R.id.gameDetailsRatingBar);
        gameDetailsImageView = findViewById(R.id.gameDetailsImageView);

        // Done:  Use the Intent object sent from MainActivity to populate the Views on
        Intent intent = getIntent();
        gameDetailsName.setText(intent.getStringExtra("name"));
        gameDetailsDescription.setText(intent.getStringExtra("description"));
        gameDetailsRate.setRating(intent.getFloatExtra("rating", 0));
        String image = intent.getStringExtra("imageName");

        AssetManager am = this.getAssets();

        try{
            InputStream stream = am.open(image);
            Drawable imagee = Drawable.createFromStream(stream, "Image File");

            gameDetailsImageView.setImageDrawable(imagee);


        }catch(IOException e){
            Log.e("TAG", e.getMessage());
        }



        // Done:  this layout, including the TextViews, RatingBar and ImageView with the Game details.
    }
}
