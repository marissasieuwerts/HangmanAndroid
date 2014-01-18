# Native App: Hangman

####Update proposal Thurs 09-01-2014!

Goal: Implement the silly, well-known classic game *Hangman* for Android

###*Features:*

1. Welcome screen after startup 
	* New game button, to start a new game
	* Continue game button, to resume a game
	* Exit button, to close the app (to completely stop its tasks) !not sure yet!
	* About button, explains the game

2. Start menu
	* Activity: start a new game (launch, load 'board', load words somehow, pick a random word that has to be guessed)
	* Activity: resume a game	(launch, load 'board' and state out of memory)
	* Activity: read 'about' page (show about.xml view)
	* Activity: exit game (close application and kill it in task manager so it doesn't continue using phone memory)
	
3.	Hangman
	* Enable user to play the game through selecting letters on his touch screen keyboard (apply game rules)
	   
3. Settings
	* Activity: enable user to adjust the game's settings; adjust the length of words to be guessed and adjust the number of incorrect guesses allowed
             
###*Frameworks, languages, libraries or other technologies:*
* Java source code for all activities, XML code for all views
* AndroidStudio as development environment
* My own Android device (LG L5 with Android 4.1.2) for testing purposes