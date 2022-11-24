package com.example.catchkenny;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textScore;
    TextView textTime;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runner;


    int score;
    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textScore  = findViewById(R.id.textScore);
        textTime   = findViewById(R.id.textTime);
        imageView  = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);


        imageArray = new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        score = 0;
        time  = 10;

        hideImages();
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                time--;
                textTime.setText("Time Left : "+ time );
            }
            @Override
            public void onFinish() {
                textTime.setText("Time is off");
                handler.removeCallbacks(runner);
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Game IS OVER");
                alert.setMessage("Restart The Game Again?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Restart the game
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Close the game
                        Toast.makeText(MainActivity.this, "Game Over",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();

            }
        }.start();
    }
    public void increaseScore(View view){

        score++;
        textScore.setText("Score :" + score );

    }

    public void hideImages(){

            handler= new Handler();
            runner = new Runnable() {
                @Override
                public void run() {
                    for(ImageView image : imageArray){
                        image.setVisibility(View.INVISIBLE);
                }
                    Random random = new Random();
                    int i = random.nextInt(9);
                    imageArray[i].setVisibility(View.VISIBLE);
                    handler.postDelayed(runner,500);
            }
         };
            handler.post(runner);

    }
}