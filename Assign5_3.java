class Deck {

   // Final constant ( 6 packs maximum ): 
   public static final int MAX_CARDS = 6*53; 

   // Private static member data: 
   private static Card [] masterPack; // Array, containing exactly 52 card references.

   // Private member data: 
   private Card [] cards; 
   private int topCard; 
   private int numPacks; 

   // Constructor : Populates the arrays and assigns initial values to members. 
   public Deck ( int numPacks ) {
      allocateMasterPack (); // Lupe's Method (needs to be called in constructor).  
      this.cards = masterPack; 
      init ( numPacks );
      topCard = 53*numPacks;
   }

   // Overloaded Constructor : If no parameters are passed, 1 pack is assumed (default). 
   public Deck () {
      this(1); 
   }

   public void sort() 
   {
      init (numPacks);
   }

   public int getNumCards()
   {
      return topCard;
   }

   public boolean addCard(Card card)
   {
      boolean isValidCard = false;
      int instances = 0;

      for ( int i = 0; i <= topCard; i++ )
      {
         if(cards[i] == card)
         {
            instances++;
         }
      }
      if( instances < numPacks && topCard <= MAX_CARDS)
      {
         isValidCard = true;
         topCard++;
         cards[topCard] = card;
      }

      return isValidCard;
   }
   //Removes a specific card from the deck.  
   //Puts the current top card into its place.  
   //Checks to Be sure the card you need is actually still in the deck, if not return false.
   public boolean removeCard(Card card) 
   {
      boolean isValidCard = false;
      int instances = 0;
      int lastInst = 0;

      for ( int i = 0; i <= topCard; i++ )
      {
         if(cards[i] == card)
         {
            instances++;
            lastInst = i;
         }
      }
      if( instances > 0 && instances <= numPacks )
      {
         isValidCard = true;
         cards[lastInst] = cards[topCard];
         cards[topCard] = new Card();
         topCard--;
      }

      return isValidCard;
   }
   // Re-populates Card array with 52 * numPacks. ( Doesn't re-populate masterPack ). 
   void init ( int numPacks ) {
      this.cards = masterPack;
      this.numPacks = numPacks;
      this.topCard = 52*numPacks;
   }

   // Mix up cards with the help of standard random num generator: 
   public void shuffle () {   
      Random randObj = new Random(); 
      int a; 

      for (a = cards.length -1 ; a > 0; a--) {

         // nextInt() method to get a pseudorandom value from 0 to cards.length. 
         int randNum = randObj.nextInt( cards.length - 1);

         // Changing the cards value: 
         Card temp = cards [randNum];
         cards [randNum] = cards [a]; 
         cards [a] = temp; 
      }
   } 

   // Decrements card from the deck
   public Card dealCard(){
      Card nextCard = new Card();
      if ( topCard != 0 )
      {
         nextCard = cards[topCard];
         cards[topCard] = new Card();
         topCard--;
      }

      return nextCard;
   }

   // Accessor for topCard
   public int getTopCard(){
      return topCard;
   }

   // Accessor for an individual card. Returns a card with errorFlag = true if k is bad. 
   public Card inspectCard ( int k ) {   
      return cards[k];    
   }

   // Defines masterpack
   private static void allocateMasterPack()
   { 
      int x, y;
      Card.Suit cardSuit;
      char cardValue;

      // allocate 
      masterPack = new Card[52];
      for (y = 0; y < 52; y++)
         masterPack[y] = new Card();

      // loop for the suits 
      // set values for suit
      for (y = 0; y < 4; y++)
      {
         cardSuit = Card.Suit.values()[y];
         masterPack[13*y].set( 'A', cardSuit );  

         for ( cardValue='2', x = 1; cardValue<='9'; cardValue++, x++ )
         {
            masterPack[13*y + x].set( cardValue, cardSuit );
         }

         masterPack[13*y+9].set( 'T', cardSuit );
         masterPack[13*y+10].set( 'J', cardSuit );
         masterPack[13*y+11].set( 'Q', cardSuit );
         masterPack[13*y+12].set( 'K', cardSuit );
      }
   }
}
