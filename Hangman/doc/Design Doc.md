# Native App: Hangman

####Design doc Thurs 09-01-2014!

Goal: Implement the silly, well-known classic game *Hangman* for Android

###Model: What to display (the classes that implement the application logic)

There are basically 4 main classes: startup.java handles the menu including onclick functions and passes them to game.java, which handles the game logic through various functions.
About.java shows the about page, settings.java shows the settings

1. Welcome screen after startup and start menu
	* depends on what button is tapped
	
	| Action | Response |
	| ----------|----------- |
	| if new game | onclick function in startup.java -> function startGame in game.java |
	| if continue | onclick function in startup.java -> function loadsaveGame in game.java |
	| if exit | onclick function in startup.java -> function finishGame in game.java|
	| if about | onclick function in startup.java -> about.java |
	| if settings (in action bar) | settings.java |

 

2. Hangman
	* game.java

3. Settings
	* settings.java

		 
###View: How to display (layout, resources and built-in classes)
1. Welcome screen after startup and start menu
	* main.xml with associated views for each button, as explained in the table below:
	
	| Action | View |
	| -------|----------- |
	| new game| game.xml |
	| if continue| game.xml |
	| if exit| back to phone's home screen|
	| if about| about.xml |

![alt text](https://github.com/marissasieuwerts/Hangman/blob/master/Hangman/doc/screenshot1.png?raw=true "Mockup1")
	
2. Hangman
	* game.xml
	shows the game graphics: must include something that represents the state of the game in a visual way, the word that we're looking for and its right guesses so far, wrong guesses/invalid guesses (used letters)
	
3. Settings
	* settings.xml
	shows the game settings: tap settings icon in action bar (should always be visible throughout the entire game)

###Controller: Events, user input (activities)
1. Welcome screen after startup
	* onClick functions responding to click/tap events, pass to (onCreate) function in game.java class that starts the activity
	
2. Hangman functions that I need implement in game.java
	* on startup (handle in one start function?): launch 'board' + set number of wrong guesses 0  + pick a random word based on the default preferences + convert this word to blank spaces and underscores + initialize start hangman image 
	* Represent state of game visually by a hangman image
	* show keyboard / enable users to hide keyboard
	* Various cases handling keyboard input --> check for invalid input
	* Validate input through validGuess function
	* Update the hangman image depending on how many guesses are still left
	* Update the underscores representing the word we're looking for with valid guesses
	* Update the view for wrong guesses
	* Load saved game (continue button)
	* Check if player won
	* Check if player lose
	* Exit game
	
3. Settings --> *can I make this an option between easy/hard mode with a predetermined word length and number of guesses allowed?*
	* change word length
	* change number of guesses allowed 