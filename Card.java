public class Card{
    char suit;
    char val;
    public Card(char suit, char val){
        this.suit = suit;
        this.val = val;
    }

    public char getSuit(){
        return this.suit;
    }

    public char getVal(){
        return this.val;
    }


    public int getIntVal(){
        char cSuit = this.suit;
        int intVal=0;
        if(Character.isDigit(cSuit)){
            intVal = Character.getNumericValue(cSuit);
        }
        else{
            switch(cSuit){
                case 'X':
                    intVal=10;
                case 'J':
                    intVal=11;
                case 'Q':
                    intVal=12;
                case 'K':
                    intVal=13;
                case 'A':
                    intVal=14;
            }
        }
        return intVal;
    }



    public void print(){
        System.out.println(this.suit + " " + this.val);
    }
}