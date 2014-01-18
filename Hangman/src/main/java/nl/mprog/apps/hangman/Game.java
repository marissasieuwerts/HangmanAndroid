package nl.mprog.apps.hangman;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game extends Activity implements View.OnClickListener{

    /**
     * Initialize a new game
     */

    // views in activity_game.xml
    private static final String TAG = "Game";
    private TextView word;
    private String mysteryWord;
    private static final Random rgenerator = new Random();

    private TextView guesses_left;

    // initialize dialog for saving purposes
    private Dialog dialog;
    private int currentDialogId;

    private TextView wrongLetters;
    private int wrongGuesses;

    static final int DIALOG_WIN_ID = 1;
    static final int DIALOG_LOSE_ID = 2;

    // variables for settings
    public static String KEY_DIFFICULTY;
    int lives; // 0 for easy, 1 for medium, 2 for hard
    int difficulty;

    TextView numGuessed;
    TextView guessAllowed;



    /*
    ** 'On Startup' function
     */

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Intent k = getIntent();
        setContentView(R.layout.activity_game);

        //getting difficulty level
     difficulty = getIntent().getIntExtra(KEY_DIFFICULTY, -1);
     lives = setDifficulty(difficulty); //set mode

    // launch 'board'
    bindViews();

     // pick a random word from words.xml
     Resources res = getResources();
     String[] mystring = res.getStringArray(R.array.words_small);
     mysteryWord = mystring[rgenerator.nextInt(mystring.length)];

     // Convert this to underscores
    initWord();

    // initialize number of wrong guesses
    initWrongGuesses();

    initGuesses();
}

    /*
    ** Initialize textviews
     */

    // set wrong guesses 0
    private void initWrongGuesses() {
        wrongGuesses = 0;
        wrongLetters.setText("");
    }

    // set secret word to underscores
    private void initWord() {
        word.setText(underscore());
    }

    // initialize number of used guesses and number of guesses allowed, make them string types instead of ints
    private void initGuesses(){
        numGuessed.setText(String.valueOf(wrongGuesses));
        guessAllowed.setText(String.valueOf(lives));

    }


    /*
    ** convert textview word to underscores
     */

    private String underscore()
    {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < mysteryWord.length(); i++) {
            result.append("_ ");
        }
        return result.toString();
    }


    /*
    ** Bind all views in activity_game.xml to the right attribute
     */

    private void bindViews() {
        word = (TextView) this.findViewById(R.id.word);
        wrongLetters = (TextView)
                this.findViewById(R.id.wrongletters);
        numGuessed = (TextView) this.findViewById(R.id.guesses_left);
        guessAllowed = (TextView) this.findViewById(R.id.guesses_allowed);

    }

    /*
    ** Initialize dialog to display messages
     */

    protected Dialog onCreateDialog(int id) {

        // variables to display
        TextView endmessage;
        Button endgame;

        switch (id)
        {
            // in case player won the game
            case DIALOG_WIN_ID:
                currentDialogId = id;
                dialog = new Dialog(Game.this);
                dialog.setContentView(R.layout.end_dialog);
                dialog.setTitle("Game Over");
                endmessage = (TextView)
                        dialog.findViewById(R.id.endmessage);
                endgame = (Button)
                        dialog.findViewById(R.id.endgame);
                endmessage.setText("You Won!");
                endgame.setText("Back to main");
                endgame.setOnClickListener(this);
                break;
            case DIALOG_LOSE_ID:
                currentDialogId = id;
                // do the work to define the LOSE Dialog
                dialog = new Dialog(Game.this);
                dialog.setContentView(R.layout.end_dialog);
                dialog.setTitle("Game Over");
                endmessage = (TextView)
                        dialog.findViewById(R.id.endmessage);
                endgame = (Button)
                        dialog.findViewById(R.id.endgame);
                endmessage.setText("You Lose!");
                endgame.setText("Back To Main");
                endgame.setOnClickListener(this);
                break;
            default:
                dialog = null;
        }
        return dialog;
    }



    /*
     ** handles button clicks in dialog
      */

    @Override
    public void onClick(View view) {
        Log.d(TAG, "clicked on " + view.getId());
        switch (view.getId()) {
            case R.id.endgame:
                finish();
                break;
        }
    }

    /*
    ** settings
     */

    // set number of guesses depending on what mode the user chose
    private int setDifficulty(int difficulty) {
        switch(difficulty) {
            // 8 guesses for easy mode
            case 0:
                return 8;
            // 6 guesses for medium mode
            case 1:
                return 6;
            // 4 guesses for hard mode
            case 2:
                return 4;
        }
        return difficulty;
    }



    // Actual game logic

    /*
    *   handles keyboard input and calls validate function for each key event
    *   Another button than A-Z will be ignored
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_A:
                validate('A');
                break;
            case KeyEvent.KEYCODE_B:
                validate('B');
                break;
            case KeyEvent.KEYCODE_C:
                validate('C');
                break;
            case KeyEvent.KEYCODE_D:
                validate('D');
                break;
            case KeyEvent.KEYCODE_E:
                validate('E');
                break;
            case KeyEvent.KEYCODE_F:
                validate('F');
                break;
            case KeyEvent.KEYCODE_G:
                validate('G');
                break;
            case KeyEvent.KEYCODE_H:
                validate('H');
                break;
            case KeyEvent.KEYCODE_I:
                validate('I');
                break;
            case KeyEvent.KEYCODE_J:
                validate('J');
                break;
            case KeyEvent.KEYCODE_K:
                validate('K');
                break;
            case KeyEvent.KEYCODE_L:
                validate('L');
                break;
            case KeyEvent.KEYCODE_M:
                validate('M');
                break;
            case KeyEvent.KEYCODE_N:
                validate('N');
                break;
            case KeyEvent.KEYCODE_O:
                validate('O');
                break;
            case KeyEvent.KEYCODE_P:
                validate('P');
                break;
            case KeyEvent.KEYCODE_Q:
                validate('Q');
                break;
            case KeyEvent.KEYCODE_R:
                validate('R');
                break;
            case KeyEvent.KEYCODE_S:
                validate('S');
                break;
            case KeyEvent.KEYCODE_T:
                validate('T');
                break;
            case KeyEvent.KEYCODE_U:
                validate('U');
                break;
            case KeyEvent.KEYCODE_V:
                validate('V');
                break;
            case KeyEvent.KEYCODE_W:
                validate('W');
                break;
            case KeyEvent.KEYCODE_X:
                validate('X');
                break;
            case KeyEvent.KEYCODE_Y:
                validate('Y');
                break;
            case KeyEvent.KEYCODE_Z:
                validate('Z');
                break;
            case KeyEvent.KEYCODE_BACK:
               return false;
        }
        return true;
    }


    /*
    ** Checks if a guess (key event) is valid
     */

    private void validate(char guesses_left)
    {
        // check if there are any guesses left
        if (mysteryWord.indexOf(guesses_left) == -1)
        {
            String wrongLetters_t = wrongLetters.getText().toString();
            // update guesses
            if (wrongLetters_t.indexOf(guesses_left) == -1)
            {
                // Letter not found in word
                if (wrongGuesses < lives)
                {

                    wrongGuesses++;
                    updatenumWrongGuesses(guesses_left);
                }
                // check for loss
                Lose();
            }

            initGuesses();
        }


        // if there aren't any guesses left, update word with the guessed letter and check if player won or lost

        else{
                if (wrongGuesses < lives)
                {
                    // update views and check for win
                    updateWord(guesses_left);
                    Win();
                }
                else
                {
                    Lose();
                }
            }
    }

    /*
    ** Update view after guessing
     */

    private void updateWord(char ch) {
        char[] updatedWord = word.getText().toString().toCharArray();
        for (int i = 0; i < mysteryWord.length(); i++) {
            if (ch == mysteryWord.charAt(i)) {
                updatedWord[i * 2] = mysteryWord.charAt(i);
            }
        }
        word.setText(new String(updatedWord));
    }


    // bind updated view of guessed letters to view
    private void updatenumWrongGuesses(char ch) {
        wrongLetters.setText(wrongLetters.getText() + Character.toString(ch));
    }


    /*
    **  Check if player won game and show message
     */
    private void Win() {
        if (word.getText().toString().indexOf("_ ") == -1) {
            showDialog(DIALOG_WIN_ID);
        }
    }

    /*
    ** Check if player lost game and show message
     */

    private void Lose()
    {
        if (wrongGuesses == lives) {
            showDialog(DIALOG_LOSE_ID);
        }
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
            View rootView = inflater.inflate(R.layout.fragment_game, container, false);
            return rootView;
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }
}