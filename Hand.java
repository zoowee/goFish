import java.util.*;

public class Hand{
  public ArrayList<Card> h = new ArrayList<Card>();
  public static Deck deck = new Deck();
  public int twos;
  
  public Hand(){
    for(int i = 0; i < 5; i++){
      int c = randInt(0, deck.cards.size());
      Card d = deck.cards.get(c);
      h.add(d);
      deck.cards.remove(c);
    }
  }

  public void draw(){
    int c = randInt(0, deck.cards.size());
    Card d = deck.cards.get(c);
    h.add(d);
    deck.cards.remove(c);
  }

  public int randInt(int low, int high){
    return (int)(Math.random()*(high - low) + low);
  }

  public String toString(){
    String output = "";
    for(int i = 0; i < h.size(); i++){
      output += h.get(i) + " ";
    }
    return output;
  }
}
