import java.util.ArrayList;

public class Board{
    ArrayList<Player> players;
    ArrayList<ActPlayer> ActivePlayers;
    House dealer;
    int totalPot;
    int eachPot;
    
    public static class ActPlayer extends Player{
        boolean check;
        boolean raise;
        boolean fold;
        int bet;
        public ActPlayer(String name,int cash){
            super(name, cash);
            this.check=false;
            this.raise=false;
            this.fold=false;
            this.bet = 0;
        }

        public void setBet(int n){
            if(n==0){
                this.bet=0;
            }
            else if(this.bet!=0){
             
            }
        }

        public void setRaise(int n){
            this.bet+=n;
            
        }
    }


    public Board(ArrayList<Player> players,House dealer){
        this.players = players;
        this.dealer=dealer;
        this.totalPot=0;
        this.eachPot=0;
        this.actPlayers=new ArrayList<>();
    }

    public void raise(int bet){
        this.eachPot += bet;
    }

    public void check(){
        if()
    }

}