import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    ArrayList<Card> cards;
    public Deck(){
        this.cards = new ArrayList<>();
    }

    public void genDeck(){
        // ArrayList<Card>  = new ArrayList();
        char[] suit={'S','H','D','C'};
        char[] val={'A','2','3','4','5','6','7','8','9','X','J','Q','K'};
        for(char s:suit){
            for(char v: val){
                this.cards.add(new Card(s,v));
            }
        }
        Collections.shuffle(this.cards);
    }

    public Card getDelfirstCard(){
        Card temp = this.cards.get(0);
        this.cards.remove(0);
        return temp;
    }

    public void printAll(){
        for(Card c : this.cards){
            c.print();
        }
    }
}