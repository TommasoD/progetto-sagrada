## login

**S-->C login request**

Insert username

**C-->S login message**

'username'

**S-->C login answer**

Welcome

**S-->C login answer in case of error**

Username 'username' already used


## windows

**S-->C show windows**

windows 'config1' 'config2' 'config3' 'config4'

**C-->S windows choice**

windows 'index'



## positioning

**S-->C notification**

Insert move

**C-->S placement request**

place 'positionX' 'positionY' 'dieIndex'

**S-->C placement answer**

Die placed

**S-->C placement answer in case of error**

Invalid placement



## toolcard 1

**C-->S client request**

toolcard 1 'dieIndex' 'increase/decrease'

**S-->C answer**

Action done

**S-->C answer in case of error**

Invalid action



## toolcard 2

**C-->S client request**

toolcard 2 'positionX1' 'positionY1' 'positionX2' 'positionY2'

**S-->C answer**

Action done

**S-->C answer in case of error**

Invalid action



## toolcard 3

**C-->S client request**

toolcard 3 'positionX1' 'positionY1' 'positionX2' 'positionY2'

**S-->C answer**

Action done

**S-->C answer in case of error**

Invalid action



## toolcard 4

**C-->S client request**

toolcard 4 'positionX1' 'positionY1' 'positionX2' 'positionY2' 'secondPositionX1' 'secondPositionY1' 'secondPositionX2' 'secondPositionY2'

**S-->C answer**

Action done

**S-->C answer in case of error**

Invalid action



## toolcard 5

**C-->S client request**

toolcard 5 'dieIndex' 'roundIndex'

**S-->C answer**

Action done

**S-->C answer in case of error**

Invalid action



## toolcard 6

**C-->S client request**

toolcard 6 'dieIndex'

**S-->C answer**

Action done

**S-->C answer in case of error**

Invalid action



## toolcard 7

**C-->S client request**

toolcard 7

**S-->C answer**

Action done

**S-->C answer in case of error**

Invalid action



## toolcard 8

**C-->S client request**

toolcard 8 'positionX' 'positionY' 'dieIndex'

**S-->C answer**

Action done

**S-->C answer in case of error**

Invalid action



## toolcard 9

**C-->S client request**

toolcard 9 'positionX' 'positionY' 'dieIndex'

**S-->C answer**

Action done

**S-->C answer in case of error**

Invalid action



## toolcard 10

**C-->S client request**

toolcard 10 'dieIndex'

**S-->C answer**

Action done

**S-->C answer in case of error**

Invalid action


## toolcard 11

**C-->S client request**

toolcard 11 'dieIndex'

**S-->C answer**

'dieColor'

**C-->S client value setting**

'dieValue' 'positionX' 'positionY'

**S-->C answer**

Action done

**S-->C answer in case of error**

Invalid action



## toolcard 12

**C-->S client request**

toolcard 12 'positionX1' 'positionY1' 'positionX2' 'positionY2' 'secondPositionX1' 'secondPositionY1' 'secondPositionX2' 'secondPositionY2'

**S-->C answer**

Action done

**S-->C answer in case of error**

Invalid action



## logout

**C-->S logout message**

quit
