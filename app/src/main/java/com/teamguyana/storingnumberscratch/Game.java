package com.teamguyana.storingnumberscratch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ryan on 12/17/2014.
 */
public class Game extends Activity {
    Boolean bFirstRoll = true;
    Button btnRoll;
    ImageView imgdice1, imgdice2;
    TextView txtPoint;
    int nSumofDices; // nSumofDices gathers the total of both dices
    int nCount = -1, nCount2 = -1;
    private int[] arDiceImages = {R.drawable.imgdice1, R.drawable.imgdice2, R.drawable.imgdice3,
            R.drawable.imgdice4, R.drawable.imgdice5, R.drawable.imgdice6};

    //https://compilr.com/eelohi/craps/Craps.java
    private enum Status {
        CONTINUE, WON, LOST
    }

    ;    // enumeration with constants that represent the game status
    Status gameStatus;  // can contain CONTINUE, WON or LOST

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        btnRoll = (Button) findViewById(R.id.btnRollDice);
        // https://www.youtube.com/watch?v=O6Tad0BlqUY&index=3&list=PLvnXjBkwUhDEfjK1pqT8LsGWPyFuzO5Zu
        imgdice1 = (ImageView) findViewById(R.id.imgdice1);
        imgdice2 = (ImageView) findViewById(R.id.imgdice2);
        txtPoint = (TextView) findViewById(R.id.txtPoint);

        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nPoint = 0;
                String sWon = "You have Won!";
                String sLost = "You have lost!";
                String sPoint = "The Point has been Made!"
                        + "Congratulations, You have Won!";

                AlertDialog.Builder builder = new AlertDialog.Builder(Game.this); // https://www.youtube.com/watch?v=UL0vaJVXYwk&list=PLvnXjBkwUhDEfjK1pqT8LsGWPyFuzO5Zu&index=5
                builder.setMessage(sWon).setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialogWin = builder.create();

                AlertDialog.Builder builder2 = new AlertDialog.Builder(Game.this);
                builder2.setMessage(sLost);
                AlertDialog dialogLose = builder2.create();

                AlertDialog.Builder builder3 = new AlertDialog.Builder(Game.this);
                builder3.setMessage(sPoint);
                AlertDialog dialogPoint = builder3.create();

                nSumofDices = rollDice();
                imgdice1.setImageResource(arDiceImages[nCount]);
                imgdice2.setImageResource(arDiceImages[nCount2]);

                if (bFirstRoll) {
                    //https://compilr.com/eelohi/craps/Craps.java
                    switch (nSumofDices) {
                        case 7: // win with 7 on first roll
                        case 11: // win with 11 on first roll
                            gameStatus = Status.WON;
                            dialogWin.show();
                            break;

                        case 2: // lose with 2 on first roll
                        case 3: // lose with 3 on first roll
                        case 12: // lose with 12 on first roll
                            gameStatus = Status.LOST;
                            dialogLose.show();
                            break;

                        default:
                            gameStatus = Status.CONTINUE; // The game is not over
                            nPoint = nSumofDices; // store the point
                            txtPoint.setText(Integer.toString(nPoint));
                            bFirstRoll = false;
                            break; // optional for default case at end of switch
                    }
                } else {
                    nSumofDices = rollDice();

                    if (nSumofDices == nPoint) {
                        gameStatus = Status.WON;

                    } else if (nSumofDices == 7) {
                        gameStatus = Status.LOST;
                    }

                }
                if (gameStatus == Status.WON) {
                    dialogPoint.show();
                }
                else if (gameStatus == Status.LOST) {
                    dialogLose.show();
                }
            }

            public int rollDice() {
                nCount = (int) Math.floor(Math.random() * arDiceImages.length);
                nCount2 = (int) Math.floor(Math.random() * arDiceImages.length);
                nSumofDices = (nCount + nCount2) + 2;
                return nSumofDices;
            }

        });
    }
}