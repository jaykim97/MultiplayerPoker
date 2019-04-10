import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

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

        ArrayList<Card> test = suitSort(dealer.getComCards(), players.get(0).getHand());
        System.out.println("sorted");
        for(Card c : test){
            c.print();
        }

        ArrayList<Card> test2 = valSort(dealer.getComCards(), players.get(0).getHand());
        System.out.println("value sorted");
        for(Card c : test2){
            c.print();
        }

        ArrayList<Integer> dups = sameKinds(test2);
        System.out.println("Print dups");
        for (int i : dups){
            System.out.println(i);
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

    public static ArrayList<Card> valSort(Card[] comCard, Card[] hand){
        ArrayList<Card> whole = new ArrayList<>();
        for(Card c : comCard)
            whole.add(c);
        for(Card c : hand)
            whole.add(c);
        whole=mergeSort(whole);
        return whole;
    }

    //sort the card for final showdown winner determination
    public static ArrayList<Card> suitSort(Card[] comCard, Card[] hand){
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
        diamonds = mergeSort(diamonds);
        clubs=mergeSort(clubs);
        hearts=mergeSort(hearts);
        spade=mergeSort(spade);
        //merge all the suits in order of spade, diamonds,club,hearts
        temp.addAll(spade);
        temp.addAll(diamonds);
        temp.addAll(clubs);
        temp.addAll(hearts);
        
        return temp;

    }
    public static ArrayList<Card> mergeSort(ArrayList<Card> cards){
        ArrayList<Card> left = new ArrayList<>();
        ArrayList<Card> right = new ArrayList<>();
        int center;
        if(cards.size()<=1){
            return cards;
        }
        else{
            center = cards.size()/2;
            for(int i =0;i<center;i++){
                left.add(cards.get(i));
            }
            for(int i=center; i<cards.size();i++){
                right.add(cards.get(i));
            }
            left = mergeSort(left);
            right = mergeSort(right);
            merge(left,right,cards);
        }
        return cards;
    }

    private static void merge(ArrayList<Card> left, ArrayList<Card> right, ArrayList<Card> cards){
        int leftIndex = 0, rightIndex=0, cardsIndex=0;

        while (leftIndex<left.size() && rightIndex < right.size()){
            if( (left.get(leftIndex).getIntVal() > right.get(rightIndex).getIntVal())){
                cards.set(cardsIndex, left.get(leftIndex));
                leftIndex++;
            }
            else{
                cards.set(cardsIndex,right.get(rightIndex));
                rightIndex++;
            }
            cardsIndex++;
        }
        ArrayList<Card> rest;
        int restIndex;
        if(leftIndex>=left.size()){
            rest=right;
            restIndex=rightIndex;
        }
        else{
            rest=left;
            restIndex=leftIndex;
        }
        for(int i=restIndex; i<rest.size(); i++){
            cards.set(cardsIndex,rest.get(i));
            cardsIndex++;
        }
    }
    
    //5 of same suit 
    public static boolean flushCheck(ArrayList<Card> cards){
        int sc=0, hc=0,dc=0,cc=0;
        for(Card c : cards){
            switch(c.getSuit()){
                case 'S':
                    sc++;
                    break;
                case 'H':
                    hc++;
                    break;
                case 'D':
                    dc++;
                    break;
                case 'C':
                    cc++;
                    break;
            }
        }

        if( sc>=5 || hc>=5 || dc>=5 || cc>= 5 )
            return true;
        return false;
    }
    //pass val sorted list
    public static ArrayList<Integer> sameKinds(ArrayList<Card> cards){
        int temp[] = new int[cards.size()];
        ArrayList<Integer> dupNums = new ArrayList<>();
        int i =0;
        for(Card c : cards){
            temp[i]=c.getIntVal();
            i++;
        }
        for(i=0; i<temp.length; i++){
            for(int j=i+1;j<temp.length;j++){
                if(temp[i]==temp[j]){
                    int cDup=temp[i];
                    if(dupNums.size()==0){
                        dupNums.add(cDup);
                    }
                    else if (dupNums.get(dupNums.size()-1) != cDup){
                        dupNums.add(cDup);
                    }
                }
            }
        }
    
        return dupNums;
    }

    //count occurance of the dupNums
    public static int countOccur(ArrayList<card> cards, int x){
        int res = 0;
        for(int i=0; i<cards.size(); i ++){
            if(x == cards.get(i).getIntVal()){
                res++;
            }
        }
        return res;
    }


}