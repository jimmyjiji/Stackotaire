import java.util.Stack;

/**
 * Created by Jimmy Ji on 3/7/2015 in PACKAGE_NAME
 * 109259420
 * Homework 3
 * jimmy.ji@stonybrook.edu
 * Recitation 3: Sun Lin
 */

/**
 * The card stack class extends the stack so all of the methods are implemented from the stack class
 */
public class CardStack extends Stack<Card> {
    private char type;

    public CardStack(){}
    public CardStack(char type) {
        this.type = type;
    }

    /**
     * This is the overridden method from the super class Stack
     * @param newCard is the new card to be put into the stack
     * @return the new card
     */
    @Override
    public Card push(Card newCard) {
        return super.push(newCard);
    }

    /**
     * This is the overridden method from the super class Stack
     * @return the top of the stack
     */
    @Override
    public Card pop() {
        return super.pop();
    }

    /**
     * This is the overridden method from the super class Stack
     * @return if the stack is empty
     */
    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    /**
     * This is the overridden method from the super class Stack
     * @return the size of the stack
     */
    @Override
    public int size() {
        return super.size();
    }

    /**
     * This is the overridden method from the super class Stack
     * @return the top of the stack without removing it
     */
    @Override
    public Card peek() {
        return super.peek();
    }

    /**
     * Print method for printing the stack
     */
    public void printStuff() {
        if(!isEmpty()) {
            CardStack print = new CardStack(); // temporary stack
            while (!isEmpty()) { // pushes all elements of current stack into temporary stack
                print.push(pop());
            }
            while (!print.isEmpty()) { // prints elements of temporary stack and pushes back into current stack
                System.out.print("" + push(print.pop()) + "");
            }
        } else {
            System.out.print("[ ]");
        }
    }

    /**
     * Actually prints the stack using printStuff()
     */
    public void printStack() {
        switch(type) {
            case 's':
                if(!isEmpty()) {
                    peek().setFaceUp(false);
                    System.out.print(peek());
                    break;
                }
            case 'w':
                if(!isEmpty())
                    peek().setFaceUp(true);
                printStuff();break;
            case 'f':
                if(!isEmpty()) {
                    peek().setFaceUp(true);
                    System.out.print(peek());
                    break;
                }
            case 't': printStuff();break;
        }
    }
}
