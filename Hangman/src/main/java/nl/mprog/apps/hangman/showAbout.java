package nl.mprog.apps.hangman;

import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;


public class showAbout extends Activity
{

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            Intent intent = getIntent();
            setContentView(R.layout.activity_about);
        }
}
