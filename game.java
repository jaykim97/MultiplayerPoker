import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
public class game{
    public static void main(String[] args){
        Card[] deck = new Card[52];
        genDeck(deck);
        ArrayList<Player> players = genPlayer();
        for(Player p: players){
            p.printPlayer();
        }
        printDeck(deck);
    }


    public static void printDeck(Card[] deck){
        int ct = 0;
        for(Card c: deck){
            System.out.println(c.suit+" "+c.val);
            ct+=1;
        }
    }
    public static void genDeck(Card[] deck){
        ArrayList<Card> temp = new ArrayList<>();
        char[] suit={'S','H','D','C'};
        String[] val={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        for(char s:suit){
            for(String v: val){
                temp.add(new Card(s,v));
            }
        }  
        Collections.shuffle(temp);
        int i=0;
        for(Card c:temp){
            deck[i]=c;
            i++;
        }
        return;
    }

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
            players.add(new Player(scanner.nextLine(),i,cash));
        }
        scanner.close();
        return players;
    }
}

