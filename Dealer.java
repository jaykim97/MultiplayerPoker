public class Dealer{
    Card[] communityCard;
    int phase;
    public Dealer(){
        this.communityCard = new Card[5];
        this.phase=0;
    }

    public void setComCard(Card c1, Card c2, Card c3, Card c4, Card c5){
        this.communityCard[0]=c1;
        this.communityCard[1]=c2;
        this.communityCard[2]=c3;
        this.communityCard[3]=c4;
        this.communityCard[4]=c5;
    }

    public void incPhase(){
        this.phase+=1;
    }

    //reveal Card depend on phase later change to send data through protocol
    public void revealCard(){
        if(phase==0){
            for(int i=0;i<3;i++){
                this.communityCard[i].print();
            }
        }
        else if(phase==1){
            this.communityCard[3].print();
        }
        else{
            this.communityCard[4].print();
        }
        incPhase();
    }
}