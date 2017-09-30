# CS410-CardGame

## Description

This project aims to implement a simple multiplayer networked card game using Java.

 
## Classes

### Server

These are classes used only by the game server to initialize the game, hold and manipulate game state, implement game logic, and provide networking capability for interacting with the clients.

* ServerPlayer (extends util.Player)
   * holds the server representation of player state (points, cards, name, IP address and port, etc.) for an individual player
   * includes methods to allow the player to interact with the game
   * includes methods for interacting with the player over the network (awaitPlayerConnection(), notifyAndAwaitPlayerTurn(), ping(), inquireGameState(), sendGameState(), disconnect())

* Game (extends util.GameState)
   * holds information about the state of the game, as well as references to each ServerPlayer object
   * implements game logic and the main game loop responsible for running the game
   * includes methods for advancing the turn, interacting with the table, checking the sanity of player moves and executing them, and dealing with the initialization and termination of a game
   
* Server
   * sets up player connections, calls Game.initialize(), and starts the main game loop
   

### Client

These are classes used by the client to allow interacting with a GUI, hold a simpler copy of the game state (we do not need to know the flow of play or what is in other player's hands, the server will take care of that), and provide a way to communicate with the game server.

* Client (extends util.GameState)
   * holds the client representation of a game state, as well as a ClientPlayer and two other 	simple player representations
   * has methods for interacting with the client representation of the table and players,
   * responsible for all client-side game logic and communication
   * controls the GUI
   
* GUI
   * uses JavaFX library to give a graphical interface to the game
   * shows cards, buttons, etc
   * controlled by the client class
   
   
### Utility

These are basic classes used by both the client and the server for encoding/decoding the game state, representing basic Card and Player objects, and translating error messages.

* Player (extends SimplePlayer?)
   * has a hand, a pile of won cards, name, points, etc. as well as methods for interacting with those fields
   
* GameState
   * represents the current state of the table, detailed information about the selected player, and minimal information about the other two
   * contains methods for encoding and decoding the state
   
* Card
   * represents one card: suit, rank, and value
   * implements the Comparable interface so they can be easily sorted in hands
   
* SimplePlayer
   * a minimal object that only contains the amount of player information another player would need to know: how many cards in hand, how many tricks have been won, player name, etc. 
   