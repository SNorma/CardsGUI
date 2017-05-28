
// This class will control the positioning of the panels and cards of the GUI. 
class CardTable extends JFrame {

    // Class variables: 
    static int MAX_CARDS_PER_HAND = 56;
    static int MAX_PLAYERS = 2;
    
    // Instance Variables: 
    private int numCardsPerHand;
    private int numPlayers;
    
    public JPanel playerHand, computerHand, playing;
    
    // Constructor: filters input, adds any panels to the JFrame, and establishes layouts
    public CardTable(String title, int numCardsPerHand, int numPlayers)  {
       
        this.numPlayers = numPlayers;
        this.numCardsPerHand = numCardsPerHand;
       
        // Creating new JPanels and using gridLayout to place components in a grid of cells : (row, col, hor gap, vert gap)
        playerHand = new JPanel(new GridLayout(1,1,10,10));
        computerHand = new JPanel(new GridLayout(1,1,10,10));
        playing = new JPanel (new GridLayout (2,2,20,20));
        
        // Setting layout manager: 
        setLayout (new BorderLayout(10,10));
       
        // Adding components: 
        add(computerHand,BorderLayout.NORTH);
        add(playing,BorderLayout.CENTER);
        add(playerHand,BorderLayout.SOUTH);
        
        // Adding arbitrary borders with a String titles: 
        playerHand.setBorder(new TitledBorder("Player"));
        computerHand.setBorder(new TitledBorder("Computer"));
        playing.setBorder(new TitledBorder("Playing"));

    }
    
   // ACCESSORS ----------------------------------------------------------------------------------------------------------
    public int getNumPlayers() {

        return numPlayers;
    }

    public int getNumCardsPerHand() {

        return numCardsPerHand;
    }


}


