package it.polimi.ingsw.model;
import java.util.Random;

public class Die {

        private String color;
        private String value;
        private static final String[] values = {
                "1",
                "2",
                "3",
                "4",
                "5",
                "6"
        };

        /*
            constructor
         */

        public Die(String color) {
            this.color = color;
        }

        /*
            getter and setter
         */

        public void setColor(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        /*
            rolls the Die assigning a random value
         */

        public void roll() {
            Random rand = new Random();
            int bound = values.length;
            int i = rand.nextInt(bound);
            this.value = values[i];
        }

        /*
            methods to handle colors and values as int tyes
         */

        public void setValue(int value) {
            if(value == 1) this.value = "1";
            if(value == 2) this.value = "2";
            if(value == 3) this.value = "3";
            if(value == 4) this.value = "4";
            if(value == 5) this.value = "5";
            if(value == 6) this.value = "6";
        }

        public int getValueAsInt() {
            if(value.equals("1")) return 1;
            if(value.equals("2")) return 2;
            if(value.equals("3")) return 3;
            if(value.equals("4")) return 4;
            if(value.equals("5")) return 5;
            if(value.equals("6")) return 6;
            return -1;
        }

        public int getColorAsInt() {
            if(color.equals("RED")) return 0;
            if(color.equals("GREEN")) return 1;
            if(color.equals("BLUE")) return 2;
            if(color.equals("YELLOW")) return 3;
            if(color.equals("PURPLE")) return 4;
            return -1;
        }

        /*
            toString methods:
                valueToString() handles values
                colorToString() handles colors returning the corresponding color code
                toString() combines the two methods above
         */

        private String valueToString(){
            if (value.equals("1")) return "\u2680";
            if (value.equals("2")) return "\u2681";
            if (value.equals("3")) return "\u2682";
            if (value.equals("4")) return "\u2683";
            if (value.equals("5")) return "\u2684";
            if (value.equals("6")) return "\u2685";
            return "\u25a1";
        }

        private String colorToString() {
            if (color.equals("RED")) return "\u001B[31m";
            if (color.equals("GREEN")) return "\u001B[32m";
            if (color.equals("YELLOW")) return "\u001B[33m";
            if (color.equals("BLUE")) return "\u001B[34m";
            if (color.equals("PURPLE")) return "\u001B[35m";
            return "\u001B[0m";
        }

        public void flipDie() {
            this.setValue(7 - this.getValueAsInt());
        }

        @Override
        public String toString() {
            return colorToString() + valueToString();
        }

        /*
        print the class
         */

        void dump(){
            System.out.println(this);
        }

        public void increaseValue() {
            if (this.getValueAsInt()!=6) this.setValue(this.getValueAsInt()+1);
        }

        public void decreaseValue() {
            if (this.getValueAsInt()!=1) this.setValue(this.getValueAsInt()-1);
         }



}