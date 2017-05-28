import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Assign5 extends JFrame
{

   public static void main(String[] args)
   {
      Card myCard = new Card('8', Card.Suit.CLUBS);

      GUICard.loadCardIcons();
      JLabel label = new JLabel(GUICard.getIcon(myCard));
      JFrame frmMyWindow = new JFrame("Card Room");
      frmMyWindow.setSize(1150, 650);
      frmMyWindow.setLocationRelativeTo(null);
      frmMyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
      frmMyWindow.add(label);
      frmMyWindow.setVisible(true);
   }

}

class GUICard
{
   private static Icon[][] iconCards = new ImageIcon[14][4];  // Holds values for icons
   private static Icon iconBack;                                              // Holds value for back of card icon
   private static boolean iconsLoaded = false;                                 // Flag to check if icons are loaded

   static public void loadCardIcons()
   {
      iconBack = new ImageIcon("img/BK.gif");

      for(int col = 0; col < 14; col++)
      {
         for(int row = 0; row < 4; row++)
         {
            iconCards[col][row] = new ImageIcon("img/" + getStrValue(col) + getCharSuit(row) + ".gif");
         }
      }
      iconsLoaded = true;
   }

   static private String getStrValue(int col)
   {
      String valChar = "";
      if(col + 1 > 1 || col + 1 < 10 ){ valChar = Integer.toString(col); }
      else if(col == 0){ valChar = "A";}
      else if(col == 9){ valChar = "T";}
      else if(col == 10){ valChar = "J";}
      else if(col == 11){ valChar = "Q";}
      else if(col == 12){ valChar = "K";}
      else if(col == 13){ valChar = "X";}
      else {valChar = "";}
      return valChar;
   }

   static private char getCharSuit(int row)
   {
      char suitChar = ' ';

      if(row == 0){ suitChar = 'C';}
      else if(row == 1){ suitChar = 'D';}
      else if(row == 2){ suitChar = 'H';}
      else if(row == 3){ suitChar = 'S';}
      else { suitChar = ' ';}

      return suitChar;
   }
   static public Icon getIcon(Card card) 
   {
      return iconCards[valueAsInt(card)][suitAsInt(card)];
   }

   static public Icon getBackCardIcon() 
   {
      return iconBack;
   }

   static public int valueAsInt(Card card)
   {
      int intVal = 0;
      char val = card.getValue();

      if(Character.isDigit(val)){ intVal = Character.getNumericValue(val); }
      else if(val == 'A'){ intVal = 1;}
      else if(val == 'T'){ intVal = 10;}
      else if(val == 'J'){ intVal = 11;}
      else if(val == 'Q'){ intVal = 12;}
      else if(val == 'K'){ intVal = 13;}
      else if(val == 'X'){ intVal = 14;}
      else {intVal = 0;}

      return intVal;

   }

   static public int suitAsInt(Card card)
   {
      int posVal = 0; 

      String suit = card.getSuit().toString();

      if(suit == "CLUBS"){ posVal = 0; }
      else if (suit == "DIAMONDS"){ posVal = 1; }
      else if (suit == "HEARTS"){ posVal = 2; }
      else if (suit == "SPADES"){ posVal = 3; }

      return posVal;
   }
}

//Simulates a card in a deck of playing cards
class Card
{
   enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES };  
   private char value;
   private Suit suit;
   private boolean errorFlag;

   // Default Constructor
   Card()
   {
      set( 'A', Suit.SPADES );
   }

   // Constructor
   Card( char value, Suit suit )
   {
      set( value, suit );
   }

   // Mutators
   public void setValue( char newValue )
   {
      value = newValue;
   }

   public void setSuit( Suit newSuit )
   {
      suit = newSuit;
   }

   public boolean set( char value, Suit suit )
   {
      setValue( value );
      setSuit( suit );
      errorFlag = isValid( value, suit );

      return true;
   }

   // Accessors
   public char getValue()
   {
      return value;
   }

   public Suit getSuit()
   {
      return suit;
   }

   public boolean getFlag()
   {
      return errorFlag;
   }

   // Public Methods
   // Returns the value and suit of a card object as a single string
   public String toString()
   {
      if ( errorFlag == true )
      {
         return "[ invalid ]";
      }
      else 
      {
         return "[" + value + ", " + suit +"]";
      }
   }

   // Returns whether a card is equivalent to the source card
   public boolean equals( Card card )
   {
      if ( getValue() == card.getValue() && getFlag() == card.getFlag() && getSuit() == card.getSuit() )
      {
         return true;
      }
      else
         return false;
   }

   // Private Methods
   // Returns whether a card has a valid value
   private boolean isValid( char value, Suit suit )
   {
      if (value == 'A' || value == '2' || value == '3' || value == '4' || value == '5' || value == '6' || value == '7'
            || value == '8' || value == '9' || value == 'T' || value == 'J' || value == 'Q' || value == 'K')
      {
         return false;
      }
      else
         return true;
   }
}

