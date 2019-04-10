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
        char cVal = this.val;
        int intVal=0;
        if(Character.isDigit(cVal)){
            intVal = Character.getNumericValue(cVal);
            System.out.println("Num value is of "+cVal+" is"+intVal);
        }
        else{
            switch(cVal){
                case 'X':
                    intVal=10;
                    break;
                case 'J':
                    intVal=11;
                    break;
                case 'Q':
                    intVal=12;
                    break;
                case 'K':
                    intVal=13;
                    break;
                case 'A':
                    intVal=14;
                    break;
            }
        }
        System.out.println("Num value is of "+cVal+" is"+intVal);
        return intVal;
    }



    public void print(){
        System.out.println(this.suit + " " + this.val);
    }
}