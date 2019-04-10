import java.util.ArrayList;
import java.util.Scanner;

public class Server{
    
    public static void main(String[] args){
         int numPlayers;
         ArrayList<Player> players= new ArrayList<>(); 
        Deck deck = new Deck();
        deck.genDeck();
        deck.printAll();
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of player: ");
        numPlayers = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i<numPlayers; i++){
            players.add(createPlayer(sc));
        }
        distCards(players, deck);
        for(Player p : players){
            p.printPlayer();
        }


    }    
    public static Player createPlayer(Scanner sc){
        System.out.print("Player name:");
        String name = sc.nextLine();
        return new Player(name,10000);
    }

    public static void distCards(ArrayList<Player> players, Deck deck){
        for(Player p : players){
            deck.cards.remove(0);
            p.setHand(deck.cards.get(0), deck.cards.get(2));
            deck.cards.remove(0);
            deck.cards.remove(1);
        }
    }
}