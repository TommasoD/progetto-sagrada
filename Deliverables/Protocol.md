## Network Protocol

*All the messages are written in JSON and represents message classes. These message classes contain the open source library Gson to be serialized and deserialized to (and from) JSON, to be efficiently sent across the network.*


## Login phase

**S-->C login request right at the start of the game**

{\"id\":\"login\"}

**C-->S login message containing the username**

{\"id\":\"login\",\"username\":\<username\>}

**S-->C answer in case of username already used**

{\"id\":\"error\",\"type\":1}


## Windows phase (right after the login phase)

**S-->C four randomly selected windows are shown to each player**

{\"id\":\"windows\",\"w1\":\<window1\>,\"w2\":\<window2\>,\"w3\":\<window3\>,\"w4\":\<window4\>}

**C-->S each player selects the window he'll play with**

{\"id\":\"window\",\"windowName\":\<windowName\>"}


## Turn phase (one player at a time)

**S-->C the player is notified of the start of his turn**

{\"id\":\"turn\"}

### Placing a die in the window

**C-->S placement request**

{\"id\":\"place\",\"x\":\<coordinateX\>,\"y\":\<coordinateY\>,\"index\":\<dieIndex\>}

**S-->C in case the move is valid**

*The player who made the move receives a positive answer:*

{\"id\":\"ok\"}

*Everyone receives an update on the status of the game:*

{\"id\":\"update\",\"round\":\<currentRound\>,\"players\":\<playerList\>,\"draft\":\<draftPool\>,\"roundTrack\":\<roundTrack\>}

**S-->C in case of invalid placement**

{\"id\":\"error\",\"type\":2} 

### Using tool card 1, 5, 6 or 10

*All those cards act on one die from the draft pool; the player chooses which action to perform on it, like increasing or decreasing the value.*

**C-->S the player asks to use the card**

{\"id\":\"toolcardA\",\"num\":\<numberOfCard\>,\"dieIndex\":\<dieIndex\>,\"action\":\<someAction\>}

**S-->C in case the move is valid**

*The player who made the move receives a positive answer:*

{\"id\":\"ok\"}

*Everyone receives an update on the status of the game:*

{\"id\":\"update\",\"round\":\<currentRound\>,\"players\":\<playerList\>,\"draft\":\<draftPool\>,\"roundTrack\":\<roundTrack\>}

**S-->C answer in case of error (not enough tokens or card already used)**

{\"id\":\"error\",\"type\":4}

**S-->C answer in case of error (invalid move)**

{\"id\":\"error\",\"type\":2}

### Using tool card 2 or 3

*All those cards let the player move a die inside the window.*

**C-->S the player asks to use the card**

{\"id\":\"toolcardB\",\"num\":\<numberOfCard\>,\"x\":\<coordinateX\>,\"y\":\<coordinateY\>,\"a\":\<coordinateA\>,\"b\":\<coordinateB\>}

**S-->C in case the move is valid**

*The player who made the move receives a positive answer:*

{\"id\":\"ok\"}

*Everyone receives an update on the status of the game:*

{\"id\":\"update\",\"round\":\<currentRound\>,\"players\":\<playerList\>,\"draft\":\<draftPool\>,\"roundTrack\":\<roundTrack\>}

**S-->C answer in case of error (not enough tokens or card already used)**

{\"id\":\"error\",\"type\":4}

**S-->C answer in case of error (invalid move)**

{\"id\":\"error\",\"type\":2}

### Using tool card 4 or 12

*All those cards let the player move two dice inside the window.*

**C-->S the player asks to use the card**

{\"id\":\"toolcardC\",\"num\":\<numberOfCard\>,\"x\":\<coordinateX\>,\"y\":\<coordinateY\>,\"a\":\<coordinateA\>,\"b\":\<coordinateB\>,\"x2\":\<coordinateX2\>,\"y2\":\<coordinateY2\>,\"a2\":\<coordinateA2\>,\"b2\":\<coordinateB2\>}

**S-->C in case the move is valid**

*The player who made the move receives a positive answer:*

{\"id\":\"ok\"}

*Everyone receives an update on the status of the game:*

{\"id\":\"update\",\"round\":\<currentRound\>,\"players\":\<playerList\>,\"draft\":\<draftPool\>,\"roundTrack\":\<roundTrack\>}

**S-->C answer in case of error (not enough tokens or card already used)**

{\"id\":\"error\",\"type\":4}

**S-->C answer in case of error (invalid move)**

{\"id\":\"error\",\"type\":2}

### Using tool card 8 or 9

*All those cards let the player place a die ignoring some restrictions.*

**C-->S the player asks to use the card**

{\"id\":\"toolcardD\",\"num\":\<numberOfCard\>,\"dieIndex\":\<dieIndex\>,\"x\":\<coordinateX\>,\"y\":\<coordinateY\>}

**S-->C in case the move is valid**

*The player who made the move receives a positive answer:*

{\"id\":\"ok\"}

*Everyone receives an update on the status of the game:*

{\"id\":\"update\",\"round\":\<currentRound\>,\"players\":\<playerList\>,\"draft\":\<draftPool\>,\"roundTrack\":\<roundTrack\>}

**S-->C answer in case of error (not enough tokens or card already used)**

{\"id\":\"error\",\"type\":4}

**S-->C answer in case of error (invalid move)**

{\"id\":\"error\",\"type\":2}

### Using tool card 7

**C-->S the player asks to use the card**

{\"id\":\"toolcardE\",\"num\":7}

**S-->C in case the move is valid**

*The player who made the move receives a positive answer:*

{\"id\":\"ok\"}

*Everyone receives an update on the status of the game:*

{\"id\":\"update\",\"round\":\<currentRound\>,\"players\":\<playerList\>,\"draft\":\<draftPool\>,\"roundTrack\":\<roundTrack\>}

**S-->C answer in case of error (not enough tokens or card already used)**

{\"id\":\"error\",\"type\":4}

**S-->C answer in case of error (invalid move)**

{\"id\":\"error\",\"type\":2}

### Using tool card 11

**C-->S the player asks to use the card**

{\"id\":\"toolcardA\",\"num\":11,\"dieIndex\":\<dieIndex\>,\"action\":0}

**S-->C in case the move is valid the player is notified of the color of the new die**

{\"id\":\"die_color\",\"color\":\<color\>}

**C-->S the player assigns a value to the die**

{\"id\":\"toolcardA\",\"num\":11,\"dieIndex\":\<dieIndex\>,\"action\":\<value\>}

**S-->C in case the move is valid**

*The player who made the move receives a positive answer:*

{\"id\":\"ok\"}

*Everyone receives an update on the status of the game:*

{\"id\":\"update\",\"round\":\<currentRound\>,\"players\":\<playerList\>,\"draft\":\<draftPool\>,\"roundTrack\":\<roundTrack\>}

**S-->C answer in case of error (not enough tokens or card already used)**

{\"id\":\"error\",\"type\":4}

**S-->C answer in case of error (invalid move)**

{\"id\":\"error\",\"type\":2}

### Looking at objectives and tool cards

**C-->S asking to give a look at objectives and tool cards**

{\"id\":\"show_table\"}

**S-->C answer**

{\"id\":\"show_table\",\"privateObjective"\:\<privateObjective\>,\"toolCards"\:\<toolCards\>,\"publicObjective"\:\<publicObjectives\>}

### Passing the turn

**C-->S passing**

{\"id\":\"pass\"}

**S-->C notification of turn ended**

{\"id\":\"end\"}


## End of the game

**S-->C telling the name of the winner**

{\"id\":\"game_over\",\"winner\":\<username\>}


## Disconnection and suspension

*Whenever a player disconnects or is suspended, everyone gets a notification.*

**S-->C when a player disconnects**

{\"id\":\"notification\",\"username\":\<username\>,\"event\":\"disconnect\"}

**S-->C when a player is suspended for inactivity**

{\"id\":\"notification\",\"username\":\<username\>,\"event\":\"suspended\"}


## Reconnection

*Whenever a player reconnects from inactivity, everyone gets a notification.*

**C-->S trying reconnecting**

{\"id\":\"reconnect\"}

**S-->C when a player successfully reconnects**

{\"id\":\"notification\",\"username\":\<username\>,\"event\":\"reconnect\"}

**S-->C in case the player tries to reconnect but it was not suspended**

{\"id\":\"error\",\"type\":3}

## End