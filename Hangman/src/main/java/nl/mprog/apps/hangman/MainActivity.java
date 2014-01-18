package nl.mprog.apps.hangman;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;
import android.app.Activity;
import android.view.MenuInflater;


public class MainActivity extends Activity{

    Button newbtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newbtn = (Button) findViewById(R.id.newgamebtn);

        // called when the user clicks the 'new button'
        newbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new AlertDialog.Builder(MainActivity.this).setTitle("Select Difficulty")
                .setItems(R.array.difficulty, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialoginterface, int i) {
                        startGame(i);
                    }
                 })
             .show();
            }
        });
    }

    private void startGame(int i) {
        Intent intent = new Intent(MainActivity.this, Game.class);
        intent.putExtra(Game.KEY_DIFFICULTY, i);
        startActivity(intent);
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
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