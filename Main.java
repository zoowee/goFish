import java.util.*;

public class Main {
  public static Scanner sc = new Scanner(System.in);
  public static int inDeck = 0;
  //public static Deck d = new Deck();
  public static int turnCount = 0;
  public static int numPairsOne = 0;
  public static int numPairsTwo = 0;
  public static boolean cardEquals;
  public static Hand player1 = new Hand();
  public static Hand player2 = new Hand();
  private static long startTime = System.currentTimeMillis();
  
  public static void main(String[] args){
    for(int i = 0; i < 5; i++){
      inDeck++;
      inDeck++;
    }
    while(inDeck <= 52){
      display();
      fishing();
      if(numPairsOne > 13 || numPairsTwo > 13){
        break;
      }
    }
  }

  public static void display(){
    if(turnCount == 0){
      System.out.println("Cards Left in Deck: " + (52 - inDeck));
      System.out.println("\n" + "Player One's Hand: " + player1);
    } else{
      System.out.println("Cards Left in Deck: " + (52 - inDeck));
      System.out.println("\n" + "Player Two's Hand: " + player2);
    }
  }
  
  public static void fishing(){
    cardEquals = false;
    if (turnCount == 0){
      player1Turn();
      cardEquals = false;
      turnCount = 1; //CHANGE TURN
    }
    else if(turnCount == 1){
      player2Turn();
      cardEquals = false;
      turnCount = 0; //CHANGE TURN
    }
    winGame();
  }
  
  public static void player1Turn(){
    int a = 0; // response and pair (player 1)
    int b = 0;
    System.out.println("What card value do you need?"); //ask card
    String response = sc.nextLine();
    for(int i = 0; i < player2.h.size(); i++){ //go through other hand
      if (response.equals(player2.h.get(i).value)){
        cardEquals = true;
        a = i;
      }
    }
    for(int i = 0; i < player1.h.size(); i++){ //go through own hand
      if (response.equals(player1.h.get(i).value)){
        b = i;
      }
    }
    if (cardEquals == true){ //if get match
      //player1.h.add(player2.h.get(a));
      player1.h.remove(b);
      player2.h.remove(a); //remove same cards from both
      numPairsOne++;
      System.out.println("You got a pair!");
      System.out.println("\n" + "Current amount of pairs: " + numPairsOne);
      System.out.println("Player One's New hand: "+ player1);
      System.out.println("\n" + "Now scanning for more pairs in hand...");
      pairsOne();
      System.out.println("No more pairs found in hand.");
      secCount();
      clearScreen();
    } else{ //if no match
      System.out.println("Go Fish!");
      player1.draw();
      System.out.println("Card drawn: " + player1.h.get(player1.h.size()-1).value);
      System.out.println("\n" + "Now scanning for pairs in hand...");
      pairsOne();
      System.out.println("No pairs found in hand.");
      inDeck++;
      System.out.println("\n" + "Player One's New hand: "+ player1);
      secCount();
      clearScreen();
    }
    
  }
  
  public static void player2Turn(){
    int a = 0;
    int b = 0;
    //response and pair (player 2)
    System.out.println("What card value do you need?"); //ask card
    String response = sc.nextLine();
    for(int i = 0; i < player1.h.size(); i++){ //go through other hand
      if (response.equals(player1.h.get(i).value)){
        cardEquals = true;
        a = i;
      }
    }
    for(int i = 0; i < player2.h.size(); i++){ //go through own hand 
      if (response.equals(player2.h.get(i).value)){
        b = i; //equal response to card index
      }
    }
    if(cardEquals == true){ //if get match
      player2.h.remove(b);
      player1.h.remove(a); //remove matched card from other hand
      numPairsTwo++;
      System.out.println("You got a pair!");
      System.out.println("\n" + "Current amount of pairs: " + numPairsTwo);
      System.out.println("Player Two's New hand: "+ player2);
      System.out.println("\n" + "Now scanning for more pairs in hand...");
      pairsTwo();
      System.out.println("No more pairs found in hand.");
      secCount();
      clearScreen();
    } else{ //if no match
      System.out.println("Go Fish!");
      player2.draw();
      System.out.println("Card drawn: " + player2.h.get(player2.h.size()-1).value);
      System.out.println("\n" + "Now scanning for pairs in hand...");
      pairsTwo();
      System.out.println("No pairs found in hand.");
      inDeck++;
      System.out.println("\n" + "Player Two's New hand: "+ player2);
      secCount();
      clearScreen();
    }
  }
    
  public static void secCount(){
    startTime = System.currentTimeMillis();
    boolean t = true;
    while(t){
      long currentTime = System.currentTimeMillis();
      if(((currentTime/1000)-3) >= (startTime/1000)){
        t = false;
      }
    }
  }

  public static void winGame(){
    if(numPairsOne > 13){
      System.out.println("Player 1 wins!");
    } else if(numPairsTwo > 13){
      System.out.println("Player 2 wins!");
    } else if(numPairsOne == 13 && numPairsTwo == 13){
      System.out.println("It's a tie!");
    }
  }

  public static void clearScreen(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  //goes through the player's hand, checks every card, removes pairs within hand
  public static void pairsOne(){
    for(int i = 0; i < player1.h.size(); i++){
      for(int j = 0; j < player1.h.size(); j++){
        if(i != j){ //diff
          if(player1.h.get(i).value.equals(player1.h.get(j).value)){ //remove if pair   
            numPairsOne++;
            System.out.println("You got a pair in your hand!");
            System.out.println("Current amount of pairs: " + numPairsOne);
            System.out.println("Pair found: " + player1.h.get(i).value + ", " + player1.h.get(j).value);
            player1.h.remove(i); 
            player1.h.remove(j-1);
            System.out.println("\n" + "New hand: "+ player1);
          }
        }
      }
    }
  }
  

  //goes through the player's hand, checks every card, removes pairs within hand
  public static void pairsTwo(){
    for(int i = 0; i < player2.h.size(); i++){
      for(int j = 0; j < player2.h.size(); j++){ //remove if pair
        if(i != j){
          if(player2.h.get(i).value.equals(player2.h.get(j).value)){
            numPairsTwo++;
            System.out.println("You got a pair in your hand!");
            System.out.println("Current amount of pairs: " + numPairsTwo);
            System.out.println("Pair found: " + player2.h.get(i).value + ", " + player2.h.get(j).value);
            player2.h.remove(i);
            player2.h.remove(j-1);
            System.out.println("\n" + "New hand: " + player2);
          }
        }
      }
    }
  }
  
}
