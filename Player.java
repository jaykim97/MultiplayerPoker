public class Player{
    public String name;
    private int cash;
    private boolean active;
    private Card[] privateCard= new Card[2];
    public Player(String name,int cash){
        this.name = name;
        this.cash = cash;
        this.active = false;
    }
    public void setCash(int cash){
        this.cash = cash;
    }

    public void setCard(Card c1, Card c2){
        this.privateCard[0]=c1;
        this.privateCard[1]=c2;
    }
    public int getCash(){
        return this.cash;
    }
    public void setActive(boolean active){
        this.active = active;
    }
    public Card[] getCard(){
        return this.privateCard;
    }
    public boolean getActive(){
        return this.active;
    }
    public void printPlayer(){
        System.out.println(this.name +" $"+ this.cash);
    }
    public void printHand(){
        System.out.println(this.name+": "+this.privateCard[0].suit+this.privateCard[0].val+" "+this.privateCard[1].suit+this.privateCard[1].val);
    }
}