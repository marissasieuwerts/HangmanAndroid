package nl.mprog.apps.hangman;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.app.Activity;


public class MainActivity extends Activity
{

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newbtn = (Button) findViewById(R.id.newgamebtn);
        Button btncontinue = (Button)  findViewById(R.id.continuebtn);

        // called when the user clicks the 'new button'
        newbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                new AlertDialog.Builder(MainActivity.this).setTitle("Select Difficulty")
                .setItems(R.array.difficulty, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        startGame(i);
                    }
                 })
             .show();
            }
        });

        // called when the user clicks the continue button

        btncontinue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // load data from preferences
                startGame(-1);
            } });
    }

    // called when user clicks 'new' button
    // prompts for choosing a difficulty level and passes this to the game class using an intent
    private void startGame(int i)
    {
        Intent intent = new Intent(MainActivity.this, Game.class);
        intent.putExtra(Game.KEY_DIFFICULTY, i);
        startActivity(intent);
    }

    // called when the user clicks the about button
    public void showAbout (View view)
    {
        Intent intent = new Intent(this, showAbout.class);
        startActivity(intent);
    }


    // called when the user clicks the exit button
    public void Exit (View view)
    {
        finish();
    }

}