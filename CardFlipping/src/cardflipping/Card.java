/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardflipping;

/**
 * This class is One single Card object for Card Flipping.
 * 
 * @author Jungwon Lim
 */
public class Card {
    // *** declare instants
    /** decide which card it is **/
    private int cardNum;
    /** true if card is flipped(picture), false if not flipped(back) **/
    private boolean isFlipped = false;
    
    
    
    // *** creating methods
    /**
     * Constructor for the Card class
     * @param the number(picture kind) of the card
     */
    public Card(int cardNum) {
        this.cardNum = cardNum;
    }
    
    /**
     * accessor for cardNum
     * @return cardNum (picture kind) of the card
     */
    public int getCardNum() {
        return cardNum;
    }
    
    /**
     * accessor for isFlipped
     * @return isFllipped true if card is flipped(picture), false if not (back)
     */
    public boolean isFlipped() {
        return isFlipped;
    }
    
    /**
     * set isFlipped as true
     */
    public void setIsFlippedTrue() {
        isFlipped = true;
    }
    
    /**
     * set isFlipped as false
     */
    public void setIsFlippedFalse() {
        isFlipped = false;
    }
    
    /**
     * check if two cards are on same picture(kind)
     * @param cardB card to compare
     * @return true if two card are same, false if not
     */
    public boolean isMatchWith(Card cardB) {
        return cardNum == cardB.getCardNum();
    }
    
    /**
     * show the instants of the Card as a string
     * @return all instants as a string
     */
    @Override
    public String toString() {
        return "cardNum: " + cardNum + "\tisFlipped: " + isFlipped;
    }
}
