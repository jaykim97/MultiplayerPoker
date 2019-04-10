import java.util.ArrayList;
import java.util.Scanner;

public class Server{
    
    public static void main(String[] args){
        int numPlayers;
        ArrayList<Player> players= new ArrayList<>(); 
        Dealer dealer= new Dealer();
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
        distCards(players, deck, dealer);
        for(Player p : players){
            p.printPlayer();
        }
        System.out.println("Phase"+dealer.phase);
        dealer.revealCard();
        System.out.println("Phase"+dealer.phase);
        dealer.revealCard();
        System.out.println("Phase"+dealer.phase);
        dealer.revealCard();



    }
    // create player for the number of players
    // modify to have create new player with each new connection
    public static Player createPlayer(Scanner sc){
        System.out.print("Player name:");
        String name = sc.nextLine();
        return new Player(name,10000);
    }

    //distribute card beginning of the round
    public static void distCards(ArrayList<Player> players, Deck deck, Dealer dealer){
        for(Player p : players){
            deck.cards.remove(0);
            p.setHand(deck.cards.get(0), deck.cards.get(2));
            deck.cards.remove(0);
            deck.cards.remove(0);
            deck.cards.remove(0);
        }

        dealer.setComCard(deck.cards.get(0), deck.cards.get(2), 
                            deck.cards.get(4), deck.cards.get(6), deck.cards.get(8));
    }
}