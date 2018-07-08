# progetto-sagrada

Java implementation of "Sagrada", a board game for 2-4 players by Cranio Creations.

<code>http://www.craniocreations.it/prodotto/sagrada/</code>

## Team

| ID| NAME |
|--|--|
| 10525797 | Dal Cortivo Davide |
| 10495562 | Disarò Edoardo |
| 10529287 | Dolci Tommaso |

## Getting started

Download the zip files from the <code>Deliverables</code> folder or clone the repository:
 <code>git clone https://github.com/TommasoD/progetto-sagrada.git</code>

Unzip files <code>LM_50_server.zip</code> and <code>LM_50_client.zip</code>

## Run

Run the server from command line:
  <code>java -jar LM50Server.jar</code>
  
  Run clients from command line:
   <code>java -jar LM50Client.jar</code>
   
Windows users are recommended to install Bash on Ubuntu on Windows (BUW) and select a font that allows to print special Unicode Characters.

## Configuration files
It is possible to set some parameters from the external XML file <code>setup.xml</code> :
- <code>ip</code>: port number;
- <code>address</code>: IP address of the server;
- <code>connection</code>: countdown during the connection phase at the start of the game (in milliseconds);
- <code>move</code>: time given to each player to complete his turn (in milliseconds).

A single <code>setup.xml</code> is sufficient to play, as both the server and the client reads from the same file. Two are needed in case the server and the client are located in different folders, of course.

## CLI
You can play directly from the command line.

First off, you will need to insert the correct IP address; in case of error the application will be closed.
Once the game is started, you need to provide a username (<code>login</code> command) and choose a personal window pattern (<code>window</code> command). Those who will not select a username and/or a window pattern will play with a default/random one.

In-game commands:
 
- <code>place</code> to place a die inside the window pattern;
- <code>tool card</code> to use a tool card;
- <code>show table</code> to check your private objective, public objectives and tool cards status;
- <code>end</code> to end your turn;
- <code>reconnect</code> to rejoin the game when suspended;
- <code>help</code> to show command list.
