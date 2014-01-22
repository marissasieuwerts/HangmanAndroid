package nl.mprog.apps.hangman;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Game extends Activity implements View.OnClickListener
{

    /**
     * Initialize a new game
     */

    // views in activity_game.xml
    private static final String TAG = "Game";
    private TextView word;
    private String secretWord;
    private static final Random rgenerator = new Random();

    // initialize dialog for alerts
    private Dialog dialog;
    private int currentDialogId;
    static final int DIALOG_WIN_ID = 1;
    static final int DIALOG_LOSE_ID = 2;

    // variables to display information in game
    private TextView wrongLetters;
    private int wrongGuesses;
    TextView numGuessed;
    TextView guessAllowed;

    // variables for settings and saving
    public static String KEY_DIFFICULTY;
    int lives;
    // -1 for load, 0 for easy, 1 for medium, 2 for hard
    private int difficulty;

    // variables for saving purposes
    public static final String SAVE_WRONGLETTERS = "wrongletters";
    public static final String SAVE_SECRETWORD = "secretWord";
    public static final String SAVE_WRONGGUESSES = "wrongGuesses";
    public static final String SAVE_WORD = "word";
    public static final String SAVE_LIVES = "lives";


    /*
    ** 'On Startup' function
     */

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

     // getting difficulty level
     difficulty = getIntent().getIntExtra(KEY_DIFFICULTY, -1);
     lives = setDifficulty(difficulty); //set mode

    // launch 'board'
    bindViews();

    // pick a random word from words.xml
    Resources res = getResources();
    String[] mystring = res.getStringArray(R.array.words_small);
    secretWord = mystring[rgenerator.nextInt(mystring.length)];

     // Convert this to underscores
    initWord();

    // initialize number of wrong guesses
    initWrongGuesses();

    // initialize number of guesses used
    initGuesses();

    // load previously saved game
    loadGame();
}

    /*
    ** Initialize textviews
     */

    // set wrong guesses 0
    private void initWrongGuesses()
    {
        wrongGuesses = 0;
        wrongLetters.setText("");
    }

    // set secret word to underscores
    private void initWord()
    {
        word.setText(underscore());
    }

    // initialize number of used guesses and number of guesses allowed, make them string types instead of ints
    private void initGuesses()
    {
        numGuessed.setText(String.valueOf(wrongGuesses));
        guessAllowed.setText(String.valueOf(lives));

    }


    /*
    ** convert textview word to underscores using StringBuffer method
     */

    private String underscore()
    {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < secretWord.length(); i++)
        {
            result.append("_ ");
        }
        return result.toString();
    }


    /*
    ** Bind all views in activity_game.xml to the right attribute
     */

    private void bindViews()
    {
        word = (TextView) this.findViewById(R.id.word);
        wrongLetters = (TextView)
                this.findViewById(R.id.wrongletters);
        numGuessed = (TextView) this.findViewById(R.id.guesses_left);
        guessAllowed = (TextView) this.findViewById(R.id.guesses_allowed);

    }


    /*
    ** Settings
     */

    // set number of guesses depending on what mode the user chose
    private int setDifficulty(int difficulty)
    {
        switch(difficulty)
        {
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


    /*
    ** loads game if there is a save file
     */

    private void loadGame()
    {
        // difficulty = -1 loads saved data
        if(difficulty == -1)
        {
            // get preferences from intent and update textviews
            secretWord = getPreferences(MODE_PRIVATE).getString(SAVE_SECRETWORD, secretWord);
            word.setText(getPreferences(MODE_PRIVATE).getString(SAVE_WORD, underscore()));
            wrongLetters.setText(getPreferences(MODE_PRIVATE).getString(SAVE_WRONGLETTERS, ""));
            wrongGuesses = getPreferences(MODE_PRIVATE).getInt(SAVE_WRONGGUESSES, wrongGuesses);
            lives = getPreferences(MODE_PRIVATE).getInt(SAVE_LIVES, lives);
        }
        // restore empty spaces
        else
        {
            word.setText(underscore());
        }

        // restore wrong guesses and lives count
        initGuesses();
        getIntent().putExtra(KEY_DIFFICULTY, -1);
    }


    // Actual game logic

    /*
    *   handles keyboard input and calls validate function for each key event
    *   Another button than A-Z will be (silently) ignored
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch (keyCode)
        {
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
        if (secretWord.indexOf(guesses_left) == -1)
        {
            String wrongLetters_t = wrongLetters.getText().toString();
            // update guesses
            if (wrongLetters_t.indexOf(guesses_left) == -1)
            {
                // Letter not found in word
                if (wrongGuesses < lives)
                {
                    // update count
                    wrongGuesses++;
                    updatenumWrongGuesses(guesses_left);
                }
                // check for loss
                Lose();
            }

            // update views
            initGuesses();
        }

        // if there aren't any guesses left, update word with the guessed letter and check if player won or lost
        else
        {
                if (wrongGuesses < lives)
                {
                    // update views and check for win
                    updateWord(guesses_left);
                    Win();
                }
                else
                {
                    // call lose function
                    Lose();
                }
        }
    }


    /*
    ** Update view of the secret word after guessing
     */

    private void updateWord(char ch)
    {
        // set to char
        char[] updatedWord = word.getText().toString().toCharArray();
        for (int i = 0; i < secretWord.length(); i++)
        {
            if (ch == secretWord.charAt(i)) {
                updatedWord[i * 2] = secretWord.charAt(i);
            }
        }
        word.setText(new String(updatedWord));
    }

    // bind updated view of guessed letters to view
    private void updatenumWrongGuesses(char ch)
    {
        wrongLetters.setText(wrongLetters.getText() + Character.toString(ch));
    }


    /*
    **  Check if player won game and show message
     */
    private void Win()
    {
        if (word.getText().toString().indexOf("_ ") == -1)
        {
            showDialog(DIALOG_WIN_ID);
        }
    }


    /*
    ** Check if player lost game and show message
     */

    private void Lose()
    {
        if (wrongGuesses == lives)
        {
            showDialog(DIALOG_LOSE_ID);
        }
    }


    /*
    ** called when application goes to 'background' or when player hits home button
     */

    protected void onPause()
    {
        super.onPause();
        Log.d(TAG, "onPause");

        // save current secret word
        getPreferences(MODE_PRIVATE).edit().putString(SAVE_SECRETWORD, secretWord).commit();
        // save the state of the secret word
        getPreferences(MODE_PRIVATE).edit().putString(SAVE_WORD, word.getText().toString()).commit();
        // save the letters that were guessed and were wrong
        getPreferences(MODE_PRIVATE).edit().putString(SAVE_WRONGLETTERS, wrongLetters.getText().toString()).commit();
        // save the number of wrong guesses
        getPreferences(MODE_PRIVATE).edit().putInt(SAVE_WRONGGUESSES, wrongGuesses).commit();
        // save the number of lives
        getPreferences(MODE_PRIVATE).edit().putInt(SAVE_LIVES, lives).commit();

        // intent is used when player clicks the continue button on the home screen
        getIntent().putExtra(KEY_DIFFICULTY, -1);
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
                endmessage.setText("You Win!");
                endgame.setText("Back to main");
                endgame.setOnClickListener(this);
                break;
            // lose
            case DIALOG_LOSE_ID:
                currentDialogId = id;
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
    public void onClick(View view)
    {
        Log.d(TAG, "clicked on " + view.getId());
        switch (view.getId())
        {
            case R.id.endgame:
                finish();
                break;
        }
    }
}
