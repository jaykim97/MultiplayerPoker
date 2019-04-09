import java.util.ArrayList;

public class House{
    ArrayList<Card> house= new ArrayList<>();
    int phase;
    public House(int phase){
        this.phase = phase;
    }
    //phase used to either dist 1 cards or 3 cards
    public void setHouseCard(ArrayList<Card> deck){
        if (this.phase==0){
            for(int i=0;i<3;i++){
                this.house.add(deck.get(0));
                deck.remove(0);
            }
            this.phase+=1;
        }
        else if (this.phase==1){
            this.house.add(deck.get(0));
            deck.remove(0);
            this.phase+=1;
        }
        else{
            this.house.add(deck.get(0));
            deck.remove(0);
            this.phase+=1;
        }
    }
    public void printHouse(){
        System.out.println("Current house");
        for(Card c: this.house){
            System.out.println(c.suit+c.val);
        }
    }
}
