/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardflipping;

/**
 * This class is a grope of Cards object for one game in Card Flipping.
 * 
 * @author Jungwon Lim
 */
public class CardSet {
    // *** declare instants
    /** the number of Card objects in the CardSet **/
    private int howManyCards;
    /** the total kind of possible cardNum (kinds) **/
    private int howManyCardNums;
    /** the number of the cards in the set unshuffled **/
    private int[] unshuffledCardNums;
    /** the number of the cards in the set shuffled **/
    private int[] shuffledCardNums;
    /** the array of Card objects **/
    private Card[] cards;
    /** return true if first card has been flipped **/
    private boolean isFirstFlipped = false;
    /** recode which is the first and second card that are flipped **/
    private int first = howManyCards, second = howManyCards;    // if not flipped, the value is howManyCards (out of index)
    
    //** declare variables
    int i;
    
    // *** creat methods
    /**
     * Constructor for the CardSet Class
     * precondition: howManyCards should be a even number
     * @param howManyCards the number of card object that the class is going to contain
     */
    public CardSet(int howManyCards) {
        this.howManyCards = howManyCards;
        howManyCardNums = howManyCards / 2;
        unshuffledCardNums = new int[howManyCards];
        for (i = 0; i < howManyCardNums; i++) {
            unshuffledCardNums[i] = i + 1;   // unassigned card will remain as 0, the rest has value between 1-howManyCardNums  
            unshuffledCardNums[i + howManyCardNums] = i + 1;   // assign a pair of card for howManyCardNums
        }
        shuffledCardNums = new int[howManyCards];
        shuffle();
        cards = new Card[howManyCards];
        for (i = 0; i < howManyCards; i++) {
            cards[i] = new Card(shuffledCardNums[i]);
        }
    }
    
    public void shuffle() {
        for (i = 0; i < howManyCards; i++) {
            int randomIndex = (int)(Math.random() * howManyCards);
            while (shuffledCardNums[randomIndex] != 0) { // if the rendomIndex is already assigned, go on to the next one until emty on is found
                randomIndex++;
                randomIndex %= howManyCards;
            }
            shuffledCardNums[randomIndex] = unshuffledCardNums[i];
        }
    }
    
    /**
     * get the nth card's number(kind)
     * @param cardNum the index of card
     * @return nth card's number(kind)
     */
    public int getNthCardNum(int cardIndex) {
        return cards[cardIndex].getCardNum();
    }
    
    /**
     * set the current flip status  true
     * @param cardIndex the index of the card to select
     */
    public void setNthCardIsFlippedTrue(int cardIndex) {
        cards[cardIndex].setIsFlippedTrue();
    }
    
    /**
     * set the current flip status  false
     * @param cardIndex the index of the card to select
     */
    public void setNthCardIsFlippedFalse(int cardIndex) {
        cards[cardIndex].setIsFlippedFalse();
    }
    
    /**
     * return all the value of the instants and the current condition of the cards on the set as a string
     * @return all instants as a string
     */
    @Override
    public String toString() {
        String result = "";
        result += "howManyCards: " + howManyCards +
                "\nhowManyCardNums: " + howManyCardNums +
                "\nunshuffledCardNums: ";
        for (i = 0; i < howManyCards; i++) {
            result += unshuffledCardNums[i];
        }
        result += "\nshuffledCardNums: ";
        for (i = 0; i < howManyCards; i++) {
            result += shuffledCardNums[i];
        }
        result += "\nfirst: " + first + "\nsecond: " + second + "\n";
        
        for (int i = 0; i < howManyCards; i++) {
            result += i + "th card: " + cards[i].toString() + "\n";
        }
        return result;
    }

    /**
     * @return the isFirstFlipped
     */
    public boolean isFirstFlipped() {
        return isFirstFlipped;
    }
    
    /**
     * check if nth card is flipped or not
     * @param cardIndex index of the card
     * @return true if the card is flipped, false if not
     */
    public boolean isNthCardFlipped(int cardIndex) {
        return cards[cardIndex].isFlipped();
    }
    
    /**
     * check if current first and second cards are matching
     * @return true if first and second card has same number(kind)
     */
    public boolean isMatching() {
        return cards[first].isMatchWith(cards[second]);
    }

    /**
     * @param isFirstFlipped the isFirstFlipped to set
     */
    public void setIsFirstFlipped(boolean isFirstFlipped) {
        this.isFirstFlipped = isFirstFlipped;
    }
    
    /**
     * check if all card in CardSet is flipped
     * @return true if all flipped, false if not
     */
    public boolean isAllMatched() {
        for (Card card: cards) {
            if (!card.isFlipped()) {
                return false;   // if there is any unflipped card, return false
            }
        }
        return true;    // if all card is flipped, return true
    }

    /**
     * @return the first
     */
    public int getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(int first) {
        this.first = first;
    }

    /**
     * @return the second
     */
    public int getSecond() {
        return second;
    }

    /**
     * @param second the second to set
     */
    public void setSecond(int second) {
        this.second = second;
    }
}
