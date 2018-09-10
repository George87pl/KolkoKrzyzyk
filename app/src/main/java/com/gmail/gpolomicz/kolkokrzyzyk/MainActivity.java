package com.gmail.gpolomicz.kolkokrzyzyk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    int[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[][] winningPosition = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean activePlayer = true;
    boolean gameOver = false;
    TextView wynik;
    Button nowaGra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nowaGra = findViewById(R.id.newGame);
        nowaGra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }

    public void dropIn(View view) {

        if(!gameOver) {
            ImageView counter = (ImageView) view;

            int tappedCounter = Integer.parseInt(counter.getTag().toString());

            if (gameState[tappedCounter] == 0) {
                counter.setAlpha(0f);

                if (activePlayer) {
                    counter.setImageResource(R.drawable.x);
                    activePlayer = false;
                    gameState[tappedCounter] = 1; // wstawia X
                } else {
                    counter.setImageResource(R.drawable.o);
                    activePlayer = true;
                    gameState[tappedCounter] = 2; // wstawia O
                }
                counter.animate().alpha(1f).setDuration(500);


                for (int[] winning : winningPosition) {
                    if (gameState[winning[0]] == gameState[winning[1]] && gameState[winning[1]] == gameState[winning[2]] && gameState[winning[0]] != 0) {
                        //Someone has won

                        wynik = findViewById(R.id.wynik);
                        wynik.setVisibility(View.VISIBLE);
                        nowaGra.setVisibility(View.VISIBLE);
                        gameOver = true;

                        if (activePlayer) {
                            wynik.setText("WYGRAŁ \"O\"");
                        } else {
                            wynik.setText("WYGRAŁ \"X\"");
                        }

                    }
                }
            }
        }
    }
}
