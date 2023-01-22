package pk;

import java.util.*;

public class Deck {
    ArrayList<Cards> newDeck = new ArrayList<>();
    Stack<Cards> deck = new Stack<>();

    public Deck(){
        //create 6 sea battle cards
        for(int i = 0; i < 6; i++){
            newDeck.add(Cards.SEABATTLE);
        }

        //create 29 NOP cards
        for(int i = 0; i < 29; i++){
            newDeck.add(Cards.NOP);
        }

        shuffle();
    }
    private void shuffle(){
        //shuffle the deck of cards into a stack
        for (int i = 0; i < newDeck.size(); i++)
        {
            Random randomNumber = new Random();
            int pos = randomNumber.nextInt(deck.size());
    
            //add the cards to the final deck of card as a stack
            deck.push(newDeck.remove(pos));
            
        }
    }
}
