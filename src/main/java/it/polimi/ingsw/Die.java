package it.polimi.ingsw;
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

        public Die(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        public String getValue() {
            return value;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void roll() {
            Random rand = new Random();
            int bound = values.length;
            int i = rand.nextInt(bound);
            this.value = values[i];
        }

        public void setValue (String value) {
            this.value = value;
        }

        public int getValueAsInt() {
            if(value.equals("1")) return 1;
            if(value.equals("2")) return 2;
            if(value.equals("3")) return 3;
            if(value.equals("4")) return 4;
            if(value.equals("5")) return 5;
            if(value.equals("6")) return 6;
            return 0;
        }

        @Override
        public String toString() {
            String s = this.color;
            return s + " [" + value + "]";
        }

        void dump(){
            System.out.println(this);
        }

}