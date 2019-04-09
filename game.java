import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
public class game{
    public static void main(String[] args){
        ArrayList<Card> deck = genDeck();
        ArrayList<Player> players = genPlayer();
        for(Player p: players){
            distCards(p, deck);
            p.printPlayer();
            p.printHand();
        }
        House house = new House(0);
        for(int i=0;i<3;i++){
            house.setHouseCard(deck);
            house.printHouse();
        }

    }

    //distribute card to player
    public static void distCards(Player player, ArrayList<Card> deck){
        player.setCard(deck.get(0),deck.get(1));
        deck.remove(0);
        deck.remove(0);
    }

    
    //print out the deck of card randomly generated
    public static void printDeck(ArrayList<Card> deck){
        int ct = 0;
        for(Card c: deck){
            System.out.println(c.suit+c.val);
            ct+=1;
        }
    }

    //random deck generated
    public static ArrayList<Card> genDeck(){
        ArrayList<Card> deck = new ArrayList<>();
        char[] suit={'S','H','D','C'};
        String[] val={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        for(char s:suit){
            for(String v: val){
                deck.add(new Card(s,v));
            }
        }  
        Collections.shuffle(deck);
        return deck;
    }

    //create players 
    public static ArrayList<Player> genPlayer(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of Players: ");
        int num = scanner.nextInt();
        System.out.print("$ amount per person: ");
        int cash = scanner.nextInt();
        ArrayList<Player> players = new ArrayList<>();
        scanner.nextLine();
        for(int i=0;i<num;i++){
            System.out.print("Player"+i+":");
            players.add(new Player(scanner.nextLine(),cash));
        }
        scanner.close();
        return players;
    }
}

