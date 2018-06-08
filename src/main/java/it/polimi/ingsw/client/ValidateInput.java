package it.polimi.ingsw.client;

public class ValidateInput {


   // check if the die index form the roundtrack is correct
   public boolean checkDieInArray (int index, int size){
       if ((0 <= index) && (index <= size )) return true;
           else {
           System.out.println("Invalid entry");
           return false;
       }
   }


    // check if the die index of the column is correct
   public boolean checkColumnIndex(int index) {
       if ((0 <= index) && (index < 5 )) return true;
       else {
           System.out.println("Invalid entry");
           return false;
       }
    }


    // check if the die index of the row is correct
    public boolean checkRowIndex(int index) {
        if ((0 <= index) && (index < 4 )) return true;
        else {
            System.out.println("Invalid entry");
            return false;
        }
    }

    //the name entered must match exactly one window name
    public boolean checkWindowName(String w1, String w2, String w3, String w4, String name) {
       if (w1.equals(name)) return  true;
       if (w2.equals(name)) return  true;
       if (w3.equals(name)) return  true;
       if (w4.equals(name)) return  true;
       return false;
    }

    // check if the toolcard index is correct (between 0 and 11)
    public boolean checkToolCardInArray (int index){
        if ((0 <= index) && (index < 12 )) return true;
        else return false;
    }

}
