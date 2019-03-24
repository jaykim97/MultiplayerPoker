public class Player{
    public String name;
    public int order;
    private int cash;
    private boolean active;
    private Card[] privateCard= new Card[2];
    public Player(String name, int order,int cash){
        this.name = name;
        this.order = order;
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
        System.out.println(this.name +" "+this.order+" $"+ this.cash);
        
    }
}