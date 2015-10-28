/**
 * Created by Jimmy Ji on 3/7/2015 in PACKAGE_NAME
 * 109259420
 * Homework 3
 * jimmy.ji@stonybrook.edu
 * Recitation 3: Sun Lin
 */

/**
 * Class Card that contains values for suit and face/number value
 */
public class Card {
    private String actualValue;
    private char actualSuit;
    private String[] cardValue = new String[2];
    private boolean faceUp;
    private int numberValue;

    /**
     * Constructor for card
     * @param actualValue is the face value of the card
     * @param actualSuit is the suit value of the card
     */
    public Card(String actualValue, char actualSuit) {
        this.actualValue = actualValue;
        this.actualSuit = actualSuit;
        cardValue[0] = actualValue;
        cardValue[1] = Character.toString(actualSuit);

        try {
            int value = Integer.parseInt(actualValue);
            this.numberValue = value;
        } catch (Exception ex) {
            if (actualValue.equalsIgnoreCase("J"))
                numberValue = 11;
            else if (actualValue.equalsIgnoreCase("Q"))
                numberValue = 12;
            else if (actualValue.equalsIgnoreCase("K"))
                numberValue = 13;
            else if (actualValue.equalsIgnoreCase("A"))
                numberValue = 1;
        }
    }

    public Card(){}

    /**
     * Obtains the suit
     * @return the suit as a char
     */
    public char getActualSuit() {
        return actualSuit;
    }

    /**
     * sets a suit to a different suit
     * @param actualSuit is the new suit
     */
    public void setActualSuit(char actualSuit) {
        this.actualSuit = actualSuit;
    }

    /**
     * gets the actual value of the card as a string
     * @return the string value of the card
     */
    public String getActualValue() {
        return actualValue;
    }

    /**
     * sets the actual value of the card as a string
     * @param actualValue is the new value of the card as a string
     */
    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    /**
     * returns the card value in a string array
     * @return is the string array value
     */
    public String[] getCardValue() {
        return cardValue;
    }

    /**
     * returns if the card is indeed face up
     * @return true or false if the card is face up
     */
    public boolean isFaceUp() {
        return faceUp;
    }

    /**
     * Sets the card face up to be true or false
     * @param faceUp could be true or false in if the card is face up or not
     */
    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    /**
     * returns if the card is red or black
     * @return true or false for is red
     */
    public boolean isRed() {
        return actualSuit == '\u2666' || actualSuit == '\u2665';
    }

    /**
     * returns the numerical value of the card 1-13
     * @return the numerical value of the card 1-13
     */
    public int getNumberValue() {
        return this.numberValue;
    }

    /**
     * Overridden toString method
     * @return the string representation of a card
     */
    @Override
    public String toString() {
        if (isFaceUp())
            return "["+actualValue+""+actualSuit+"]";
        else
            return "[XX]";
    }
}
