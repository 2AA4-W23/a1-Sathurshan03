package pk;

import java.util.*;

public class Deck {
    ArrayList<Cards> newDeck;
    Stack<Cards> deck;

    public Deck(){
        newDeck = new ArrayList<>();
        deck = new Stack<>();

        putAllCardDeck();
        shuffle();
    }
    private void putAllCardDeck(){
        //create 6 sea battle cards of 2 of each type
        for(int i = 0; i < 2; i++){
            newDeck.add(Cards.SEABATTLE2);
            newDeck.add(Cards.SEABATTLE3);
            newDeck.add(Cards.SEABATTLE5);
        }

        //create 29 NOP cards
        for(int i = 0; i < 29; i++){
            newDeck.add(Cards.NOP);
        }
    }

    private void shuffle(){
        //shuffle the deck of cards into a stack
        int numCards = newDeck.size();
        for (int i = 0; i < numCards; i++)
        {
            Random randomNumber = new Random();
            int pos = randomNumber.nextInt(newDeck.size());
    
            //add the cards to the final deck of card as a stack
            deck.push(newDeck.remove(pos));
            
        }
    }
    public Cards drawCard(){
        //if deck is empty, then create a new deck
        if (deck.size() == 0){
            putAllCardDeck();
            shuffle();
        }
        return deck.pop();
    }
}
