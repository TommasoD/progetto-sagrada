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

        @Override
        public String toString() {
            String s = this.color;
            return s + " [" + value + "]";
        }

        void dump(){
            System.out.println(this);
        }

}