public class Card{
    char suit;
    String val;
    public Card(char suit, String val){
        this.suit = suit;
        this.val = val;
    }
    public void print(){
        System.out.println(this.suit + " " + this.val);
    }
}