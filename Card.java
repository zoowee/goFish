public class Card{
  public static String numSym = "23456789TJQKA23456789TJQKA23456789TJQKA23456789TJQKA";
  public String value;
  
  public Card(){
    int index = randomInt(0, 13);
    value = ""+numSym.charAt(index);
  }

  public Card(int val){
    value = ""+numSym.charAt(val);
  }

  public String toString(){
    return "" + value;
  }

  public int randomInt(int high, int low){
    return (int)(Math.random()*(high - low) + low);
  }
  
}
