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

        ArrayList<Card> test = cardSort(dealer.getComCards(), players.get(0).getHand());
        System.out.println("sorted");
        for(Card c : test){
            c.print();
        }

    }
    // create player for the number of players
    // modify to have create new player with each new connection
    public static Player createPlayer(Scanner sc){
        System.out.print("Player name:");
        String name = sc.nextLine();
        return new Player(name,10000);
    }

    //distribute card beginning of the round to each playesr and the dealer
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

    //sort the card for final showdown winner determination
    public static ArrayList<Card> cardSort(Card[] comCard, Card[] hand){
        ArrayList<Card> diamonds = new ArrayList<>(), 
                            clubs = new ArrayList<>(), 
                            hearts= new ArrayList<>(), 
                            spade= new ArrayList<>(),
                            temp = new ArrayList<>();
        // insert card based on suits for preprocessing 
        for(Card c: comCard){
            char suit = c.getSuit();
            switch (suit){
                case 'D':
                    diamonds.add(c);
                    break;
                case 'C':
                    clubs.add(c);
                    break;
                case 'H':
                    hearts.add(c);
                    break;
                case 'S':
                    spade.add(c);
                    break;
            }
        }
        for(Card c: hand){
            char suit = c.getSuit();
            switch (suit){
                case 'D':
                    diamonds.add(c);
                    break;
                case 'C':
                    clubs.add(c);
                    break;
                case 'H':
                    hearts.add(c);
                    break;
                case 'S':
                    spade.add(c);
                    break;
            }
        }
        //sort the each suits
        if(diamonds.size()>1)
            valSort(diamonds, 0, diamonds.size()-1);
        if(clubs.size()>1)
            valSort(clubs, 0, clubs.size()-1);
        if(hearts.size()>1)
            valSort(hearts, 0, hearts.size()-1);
        if(spade.size()>1)
            valSort(spade, 0, spade.size()-1);
        //merge all the suits in order of spade, diamonds,club,hearts
        temp.addAll(spade);
        temp.addAll(diamonds);
        temp.addAll(clubs);
        temp.addAll(hearts);
        
        return temp;

    }

    //l= first card index r = last card index
    public static void valSort(ArrayList<Card> cards, int l, int r){
        if(cards.get(l).getIntVal()<cards.get(r).getIntVal()){
            int m = (l+r)/2;
            valSort(cards, l, m);
            valSort(cards, m+1,l);
            merge(cards,l,m,r);
        }
    }

    public static void merge(ArrayList<Card> cards, int l, int m, int r){
        int n1 = m-l+1;
        int n2=r-m;

        Card L[] = new Card[n1];
        Card R[] = new Card[n2];

        for(int i=0;i<n1;++i)
            L[i]=cards.get(l+i);
        for(int j=0;j<n2;++j)
            R[j]=cards.get(m+1+j);
        
        int i=0,j=0;
        int k=l;
        while(i<n1 && j<n2){
            if(L[i].getIntVal()<=R[j].getIntVal()){
                cards.set(k,L[i]);
                i++;
            }
            else{
                cards.set(k,R[j]);
                j++;
            }
            k++;
        }
        while(i<n1){
            cards.set(k,L[i]);
            i++;
            k++;
        }
        while(j<n2){
            cards.set(k,R[j]);
            j++;
            k++;
        }
        
    }


}