import java.util.*;

public class Deck{

  public ArrayList<Card> cards = new ArrayList<Card>();

  public Deck(){
    //int c = 0;
    for(int i = 0; i < 52; i++){
      Card c = new Card(i);
      cards.add(c);    
    }
  }

  public void shuffle(){
    for(int i = 0; i < 52; i++){
      int ranNum = (int)(Math.random()*52);
      Card car = new Card(ranNum);
      cards.add(car);
    }
  }
}
