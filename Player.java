public class Player{
    int cash;
    Card[] hand;
    String name;

    public Player(String name, int cash){
        this.cash = cash;
        this.name = name;
        this.hand = new Card[2];
    }
    
    public void setHand(Card c1, Card c2){
        this.hand[0]=c1;
        this.hand[1]=c2;
    }

    public void setCash(int n){
        this.cash=n;
    }

    public void printPlayer(){
        System.out.println(this.name);
        System.out.println(this.cash);
        this.hand[0].print();
        this.hand[1].print();
    }

    public Card[] getHand(){
        return this.hand;
    }
}