package pk;

import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;

public class Deck {
    List<Cards> newDeck;
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

        //create 4 monkey business, Gold, diamond, sorceress, Tressure Chest and captain cards
        for(int i = 0; i < 4; i++){
            newDeck.add(Cards.MONKEYBUSINESS);
            newDeck.add(Cards.GOLD);
            newDeck.add(Cards.DIAMOND);
            newDeck.add(Cards.SORCERESS);
            newDeck.add(Cards.TRESSURECHEST);
            newDeck.add(Cards.CAPTAIN);
        }

        //Create 2 skulls 2 cards
        newDeck.add(Cards.SKULL2);
        newDeck.add(Cards.SKULL2);

        //create 3 skulls 1 cards
        newDeck.add(Cards.SKULL1);
        newDeck.add(Cards.SKULL1);
        newDeck.add(Cards.SKULL1);

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
