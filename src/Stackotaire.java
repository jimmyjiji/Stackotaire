import java.util.*;

/**
 * Created by Jimmy Ji on 3/7/2015 in PACKAGE_NAME
 * 109259420
 * Homework 3
 * jimmy.ji@stonybrook.edu
 * Recitation 3: Sun Lin
 */

/**
 * The class Stackotaire is initiating the game
 */
public class Stackotaire {
    private ArrayList<Card> cards = new ArrayList<Card>();
    private CardStack[] foundationList;
    private CardStack[] tableauList;
    private CardStack wasteList;
    private CardStack stockList;
    private CardStack cardStack;

    public Stackotaire() {
        initializeEverything();
    }

    /**
     * Creates an array list to hold all the cards in every possible combination
     */
    public ArrayList<Card> createCards() {
        ArrayList<Card> cardList = new ArrayList<Card>();
        for(int i = 2; i <= 10; i++) {
            for(int j = 1; j <= 4; j++) {
                String c = i+"";
                switch(j) {
                    case 1: cardList.add(new Card(c,'\u2666'));
                        break;
                    case 2: cardList.add(new Card(c,'\u2663'));
                        break;
                    case 3: cardList.add(new Card(c,'\u2665'));
                        break;
                    case 4: cardList.add(new Card(c,'\u2660'));
                        break;
                }
            }
        }
        String[] faceValues = {"J", "Q", "K", "A"};
        for(int i = 0; i < faceValues.length; i++) {
            for (int j = 1; j <= 4; j++) {
                switch (j) {
                    case 1:cardList.add(new Card(faceValues[i], '\u2666'));
                        break;
                    case 2:cardList.add(new Card(faceValues[i], '\u2663'));
                        break;
                    case 3:cardList.add(new Card(faceValues[i], '\u2665'));
                        break;
                    case 4:cardList.add(new Card(faceValues[i], '\u2660'));
                        break;
                }
            }
        }
        Collections.shuffle(cardList);
        return cardList;

    }

    /**
     * Simply shuffles the deck
     * @return if the deck has been shuffled or not
     */
    public boolean shuffle(ArrayList cardList) {
        Collections.shuffle(cardList);
        return true;
    }

    /**
     * Puts all initializer methods into one method
     */
    public void initializeEverything() {
        this.cards = createCards();
        CardStack[] foundation = new CardStack[5];
        CardStack[] tableau = new CardStack[8];
        CardStack waste = new CardStack('f');
        CardStack stock = new CardStack('s');
        CardStack cardStack = new CardStack();
        this.foundationList = foundation;
        this.tableauList = tableau;
        this.wasteList = waste;
        this.stockList = stock;
        this.cardStack = cardStack;
        initializeCardStack(cardStack);
        initializeTableauList(tableau);
        initializeFoundationList(foundation);
        initializeStockList(stock);
    }

    /**
     * Puts all the print methods into one to print out the entire board
     */
    public void displayGameBoard() {
        System.out.println();
        printFoundationList();
        printStockList();
        printWasteList();
        System.out.println();
        printTableau();
    }

    /**
     * initializes the card stack
     */
    public void initializeCardStack(CardStack cardStack) {
        while(!cardStack.isEmpty())
            cardStack.pop();

        for (int i = 0; i < 52; i++)
            cardStack.push(cards.get(i));
    }

    /**
     * initializes the tableau stack
     */
    public void initializeTableauList(CardStack[] tableauList) {
        for(int i = 1; i <= 7; i++)
            tableauList[i] = new CardStack('w');
        for(int i = 7; i >= 1; i--) {
            for (int j = i; j >= 1; j--) {
                tableauList[i].push(cardStack.pop());
            }
        }
    }

    /**
     * initializes the foundation stack
     */
    public void initializeFoundationList(CardStack[] foundationList) {

        for(int i = 1; i <=4; i++) {
            char c = (char)(i+'0');
            foundationList[i] = new CardStack('f');
            foundationList[i].push(new Card("F",c));
        }

    }

    /**
     * initializes the stock list
     */
    public void initializeStockList(CardStack stockList) {
        while(!cardStack.isEmpty()) {
            stockList.push(cardStack.pop());
        }
    }

    /**
     * prints out the tableau list
     */
    public void printTableau() {
        for (int i = 7; i >= 1; i--) {
            System.out.print("T"+i+ " ");
            tableauList[i].printStack();
            System.out.println();
        }
    }

    /**
     * prints out the stock list
     */
    public void printStockList() {
        System.out.print("      "+stockList.size());
        stockList.printStack();
        System.out.println();
    }

    /**
     * prints out the foundation list
     */
    public void printFoundationList() {
        for(int i = 1; i <= 4; i++) {
            foundationList[i].printStack();
        }
    }

    /**
     * prints out the waste list
     */
    public void printWasteList() {
        System.out.print("W1");
        wasteList.printStack();
    }


    /**
     * draws a card. If the card is an ace, put it in the foundation pile respectively
     */
    public void draw() {
        if(stockList.peek().getNumberValue() == 1 && stockList.peek().getActualSuit() == '\u2666')
            foundationList[1].push(stockList.pop());
        else if(stockList.peek().getNumberValue() == 1 && stockList.peek().getActualSuit() == '\u2663')
            foundationList[2].push(stockList.pop());
        else if(stockList.peek().getNumberValue() == 1 && stockList.peek().getActualSuit() == '\u2665')
            foundationList[3].push(stockList.pop());
        else if(stockList.peek().getNumberValue() == 1 && stockList.peek().getActualSuit() == '\u2660')
            foundationList[4].push(stockList.pop());
        else
            wasteList.push(stockList.pop());
    }

    /**
     * Moves a card from one stack to another
     * @param C1 is the stack a card is taken from
     * @param C2 is the stack a card is put in
     */
    public void move(CardStack C1, CardStack C2) {
        C2.push(C1.pop());
    }

    /**
     * Moves a certain number of cards
     * @param C1 is the stack a card is taken from
     * @param C2 is the stack a card is put in
     * @param n is the number of cards taken
     */
    public void moveN(CardStack C1, CardStack C2, int n) {
        for(int i = 0; i < n; i++) {
            move(C1, C2);
        }
    }

    /**
     * Restart the game by re-initializing everything
     */
    public void restart() {
        initializeEverything();
    }

    /**
     * Check if the it is a winning board by getting all the references from the original card list
     * @return if the board is a winning board or not
     */
    public boolean winningBoard() {
        for (int i = 0; i < cards.size(); i++) {
            if(!cards.get(i).isFaceUp())
                return false;
        }
        return true;
    }
    /**
     * Play the game!
     * This throws all the exceptions that may occur during play time
     * as well as determine what is or is not a valid move
     * if the move is valid, the method will execute the corresponding command.
     * @return int values for whatever condition is triggered during the game
     * @throws IllegalArgumentException for when there is an invalid move in the game
     */
    public int play() throws IllegalArgumentException, NullPointerException{
        displayGameBoard();
        autoMove();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a command:");
        String expression = input.next();
        expression.toLowerCase();

        /**
         * This is one big "switch" statement.
         */
        if(!winningBoard()) {
            if (expression.equals("draw")) {
                if (!stockList.isEmpty())
                    draw();
                else {
                    while (!wasteList.isEmpty()) {
                        stockList.push(wasteList.pop());
                    }
                }
                return 0;
            } else if (expression.equals("move")) {
                String firstStack = input.next();
                String secondStack = input.next();
                char firstChar1 = firstStack.charAt(0);
                char secondChar1 = secondStack.charAt(0);
                int firstChar2 = firstStack.charAt(1) - '0';
                int secondChar2 = secondStack.charAt(1) - '0';

                if (!recognize(secondChar1, secondChar2).isEmpty()) {
                    if (firstChar1 == 'f' && secondChar1 == 't') {
                        if (recognize(firstChar1, firstChar2).peek().isRed() && recognize(secondChar1, secondChar2).peek().isRed())
                            throw new IllegalArgumentException("Illegal move");
                        if (!recognize(firstChar1, firstChar2).peek().isRed() && !recognize(secondChar1, secondChar2).peek().isRed())
                            throw new IllegalArgumentException("Illegal move");
                        if (recognize(firstChar1, firstChar2).peek().getNumberValue() > recognize(secondChar1, secondChar2).peek().getNumberValue())
                            throw new IllegalArgumentException("Illegal move");

                        move(recognize(firstChar1, firstChar2), recognize(secondChar1, secondChar2));
                        return 0;
                    }
                    if (firstChar1 == 't' && secondChar1 == 'f') {
                        if (recognize(secondChar1, secondChar2).size() > 1) {
                            if (recognize(firstChar1, firstChar2).peek().getActualSuit() != recognize(secondChar1, secondChar2).peek().getActualSuit())
                                throw new IllegalArgumentException("Illegal move");
                            if (recognize(firstChar1, firstChar2).peek().getNumberValue() - 1 != recognize(secondChar1, secondChar2).peek().getNumberValue())
                                throw new IllegalArgumentException("Illegal move");
                            move(recognize(firstChar1, firstChar2), recognize(secondChar1, secondChar2));
                        } else {
                            if (recognize(firstChar1, firstChar2).peek().getNumberValue() == 1)
                                move(recognize(firstChar1, firstChar2), recognize(secondChar1, secondChar2));
                            else
                                throw new IllegalArgumentException("Illegal move");
                        }
                        return 0;
                    }
                    if (firstChar1 == 'w' || (firstChar1 == 't' && secondChar1 == 't')) {
                        if (recognize(firstChar1, firstChar2).peek().isRed() && recognize(secondChar1, secondChar2).peek().isRed())
                            throw new IllegalArgumentException("Illegal move"); // why isn't this caught
                        if (!recognize(firstChar1, firstChar2).peek().isRed() && !recognize(secondChar1, secondChar2).peek().isRed())
                            throw new IllegalArgumentException("Illegal move");
                        if (recognize(firstChar1, firstChar2).peek().getNumberValue() + 1 != recognize(secondChar1, secondChar2).peek().getNumberValue())
                            throw new IllegalArgumentException("Illegal move"); // this isn't caught
                        move(recognize(firstChar1, firstChar2), recognize(secondChar1, secondChar2));
                        return 0;
                    }

                } else {
                    if (recognize(firstChar1, firstChar2).peek().getNumberValue() != 13)
                        throw new IllegalArgumentException("Illegal move");
                    move(recognize(firstChar1, firstChar2), recognize(secondChar1, secondChar2));
                    return 0;
                }

            } else if (expression.equals("moven")) {
                String firstStack = input.next();
                String secondStack = input.next();
                int n = input.nextInt();
                char firstChar1 = firstStack.charAt(0);
                int firstChar2 = firstStack.charAt(1) - '0';
                char secondChar1 = secondStack.charAt(0);
                int secondChar2 = secondStack.charAt(1) - '0';

                if (!recognize(secondChar1, secondChar2).isEmpty()) {
                    CardStack temp = new CardStack();
                    for (int i = 0; i < n; i++) {
                        temp.push(recognize(firstChar1, firstChar2).pop());
                    }
                    if (temp.peek().getNumberValue() + 1 != recognize(secondChar1, secondChar2).peek().getNumberValue()) {
                        moveN(temp, recognize(firstChar1, firstChar2), n);
                        throw new IllegalArgumentException("Invalid move");
                    }
                    if ((temp.peek().isRed() && recognize(secondChar1, secondChar2).peek().isRed())) {
                        moveN(temp, recognize(firstChar1, firstChar2), n);
                        throw new IllegalArgumentException("Invalid move");
                    }
                    if (!temp.peek().isRed() && !recognize(secondChar1, secondChar2).peek().isRed()) {
                        moveN(temp, recognize(firstChar1, firstChar2), n);
                        throw new IllegalArgumentException("Invalid move");
                    }
                    moveN(temp, recognize(secondChar1, secondChar2), n);
                } else {
                    CardStack temp = new CardStack();
                    for (int i = 0; i < n; i++) {
                        temp.push(recognize(firstChar1, firstChar2).pop());
                    }
                    if (temp.peek().getNumberValue() != 13) {
                        moveN(temp, recognize(firstChar1, firstChar2), n);
                        throw new IllegalArgumentException("Invalid move");
                    } else {
                        moveN(temp, recognize(secondChar1, secondChar2), n);
                    }
                }
                return 0;
            } else if (expression.equals("restart")) {
                System.out.println("Do you want to restart? (Y/N)");
                String s = input.next();
                if (s.equalsIgnoreCase("y")) {
                    restart();
                    System.out.println("Sorry you lose. Starting new game");
                }
                return 0;
            } else if (expression.equals("quit")) {
                System.out.println("Do you want to quit? (Y/N)");
                String s = input.next();
                if (s.equalsIgnoreCase("y")) {
                    System.out.println("Sorry you lose");
                    System.exit(1);

                }

            }
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * This returns the correct stack which a card is removed from or put into to
     * @param c is the character value of the stack
     * @param n is the numerical value of the stack
     * @return the CardStack that the user intends to put a new card into or take away from
     * @throws IllegalArgumentException when there is an invalid input
     */
    public CardStack recognize(char c, int n) throws IllegalArgumentException {
        if(c == 'w') {
            return wasteList;
        }
        else if (c == 't') {
            switch(n) {
                case 1: return tableauList[1];
                case 2: return tableauList[2];
                case 3: return tableauList[3];
                case 4: return tableauList[4];
                case 5: return tableauList[5];
                case 6: return tableauList[6];
                case 7: return tableauList[7];
                default:
                    throw new IllegalArgumentException();
            }
        } else if (c == 'f') {
            switch(n) {
                case 1: return foundationList[1];
                case 2: return foundationList[2];
                case 3: return foundationList[3];
                case 4: return foundationList[4];
            }
        } else {
            throw new IllegalArgumentException();
        }
        return null;
    }

    /**
     * automatically puts a card into a foundation list
     */
    public void autoMove() {

        for(int j = 1; j <= 4; j++) {
            if(!foundationList[j].isEmpty()) {
                for (int i = 1; i <= 7; i++) {
                    if(!tableauList[i].isEmpty()) {
                        if (tableauList[i].peek().getNumberValue() - 1 == foundationList[j].peek().getNumberValue() && tableauList[i].peek().getActualSuit() == foundationList[j].peek().getActualSuit())
                            move(tableauList[i], foundationList[j]);
                    }
                }
                if(!wasteList.isEmpty()) {
                    if (wasteList.peek().getNumberValue() - 1 == foundationList[j].peek().getNumberValue() && wasteList.peek().getActualSuit() == foundationList[j].peek().getActualSuit())
                        move(wasteList, foundationList[j]);
                }
            }
        }

        if(foundationList[1].size() == 1) {
                for (int i = 1; i <= 7; i++) {
                    if(!tableauList[i].isEmpty()) {
                        if (tableauList[i].peek().getNumberValue() == 1 && tableauList[i].peek().getActualSuit() == '\u2666')
                            move(tableauList[i], foundationList[1]);
                }
            }
        }
        if(foundationList[2].size() == 1) {
            for (int i = 1; i <= 7; i++) {
                if(!tableauList[i].isEmpty()) {
                    if (tableauList[i].peek().getNumberValue() == 1 && tableauList[i].peek().getActualSuit() == '\u2663')
                        move(tableauList[i], foundationList[2]);
                }
            }
        }
        if(foundationList[3].size() == 1) {
            for (int i = 1; i <= 7; i++) {
                if(!tableauList[i].isEmpty()) {
                    if (tableauList[i].peek().getNumberValue() == 1 && tableauList[i].peek().getActualSuit() == '\u2665')
                        move(tableauList[i], foundationList[3]);
                }
            }
        }
        if(foundationList[4].size() == 1) {
            for (int i = 1; i <= 7; i++) {
                if(!tableauList[i].isEmpty()) {
                    if (tableauList[i].peek().getNumberValue() == 1 && tableauList[i].peek().getActualSuit() == '\u2660')
                        move(tableauList[i], foundationList[4]);
                }
            }
        }
    }
    /**
     * Run the game!
     * @param game is the Stackotaire object that is run
     */
    public static void run(Stackotaire game) {
        /**
         * Error will always be true and the only thing that stops the loop is if you win or if you quit.
         */
        game = new Stackotaire();
        game.initializeEverything();
        boolean error = true;
        int x = 0;
        do {
            try {
                 x = game.play();
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid input");
                System.out.println();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input");
                System.out.println();
            } catch (NullPointerException ex) {
                System.out.println("Invalid position");
                System.out.println();
            } catch (Exception ex) {
                System.out.println("Invalid input");
                System.out.println();
            }
        } while (x == 0 && error);

    }

    /**
     * Main method
     * @param args is the main string arguments
     */
    public static void main(String[] args) {
        Stackotaire game = new Stackotaire();
        run(game);
        System.out.println("You Win!");
    }

}
