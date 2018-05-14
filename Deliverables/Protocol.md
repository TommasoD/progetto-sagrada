## login

**login request**

Insert username

**login message**

<username>

**login answer**

Welcome

**login answer in case of error**

Username <username> already used


## windows

**show windows**

windows <config1> <config2> <config3> <config4>

**windows choice**

windows <index>



## positioning

**notification**

Insert move

**placement request**

place <username> <positionX> <positionY> <dieIndex>

**placement answer**

Die placed

**placement answer in case of error**

Invalid placement



## toolcard usage

**notification**

toolcard <index>



## move

**move request**

move <username> <positionX> <positionY> <positionX'> <positionY'>

**move answer**

Die moved

**move answer in case of error**

Invalid move


## toolcard 1

**change request**

change <dieIndex> <increase/decrease/oppositeFace>

**answer**

Change done

**answer in case of error**

Invalid change



## toolcard 5

**switch request**

switch <dieIndex> <trackIndex>

**answer**

Switch done

**answer in case of error**

Invalid switch



## toolcard 6

**roll request**

roll <dieIndex>

**answer**

Roll done



## logout

**logout message**

quit