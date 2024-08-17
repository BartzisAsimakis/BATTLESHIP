/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

import java.io.*;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Λευτέρης - Ασημάκης
 */
public class Game implements Serializable {
    private int numOfRows;
    private int numOfCols;
    private int rounds;
    private int choiceForEnd = -1;
    private Player player1;
    private Player player2;
    private static boolean saveIsValidCommand = false;
    
    public Game() {
        numOfRows = -1;
        numOfCols = -1;
        rounds = -1;
        player1 = null;
        player2 = null;
    }
    
    public String selectCommand() {
        Scanner scan = new Scanner(System.in);
        String selection;
        
        if(saveIsValidCommand == false) {
            String TEXT_RED    = "\u001B[31m";
	    String TEXT_RESET  = "\u001B[0m";
            System.out.println("\tGAME COMMANDS: ");
            System.out.println("\t    " + Command.HELP.getCommandString() + ": " + Command.HELP.getHelpText());
            System.out.println(TEXT_RED + "\t    " + Command.SAVE.getCommandString() + ": " + Command.SAVE.getHelpText());
            System.out.print(TEXT_RESET);
            System.out.println("\t    " + Command.LOAD.getCommandString() + ": " + Command.LOAD.getHelpText());
            System.out.println("\t    " + Command.EXIT.getCommandString() + ": " + Command.EXIT.getHelpText());
            System.out.print("\tSelect a command (Or press Enter to start the game): ");
        }
        else if(saveIsValidCommand == true) {
            System.out.println("GAME COMMANDS: ");
            System.out.println("    " + Command.HELP.getCommandString() + ": " + Command.HELP.getHelpText());
            System.out.println("    " + Command.SAVE.getCommandString() + ": " + Command.SAVE.getHelpText());
            System.out.println("    " + Command.LOAD.getCommandString() + ": " + Command.LOAD.getHelpText());
            System.out.println("    " + Command.EXIT.getCommandString() + ": " + Command.EXIT.getHelpText());
            System.out.print("Select a command (Or press <Enter> to start the game): ");
        }
        //selection = scan.next();
        //Command cmd = null;
        /*char choice;
        switch(selection.toLowerCase()) {
            case "help":
                return "help";
            case "save":
                if(saveIsValidCommand) {
                    return "save";
                }
                break;
            case "load":
                return "load";
            case "exit":
                System.out.print("\tType [y/n]: ");
                choice = scan.next().charAt(0);
                if(choice == 'y') {
                    System.exit(0);
                }
                else {
                    selectCommand();
                }
                break;
        }*/
            ObjectOutputStream outputStream;
            ObjectInputStream inputStream;
            selection = scan.next();
            switch(selection.toLowerCase()) {
                case "save":  
                    try {
                        System.out.println("Inside save");
                        outputStream = new ObjectOutputStream(new FileOutputStream("SavedGame.bin"));
                        outputStream.writeObject(this);
                        outputStream.close();
                    } catch (IOException e) {
                        System.out.println("Problem with file output.");
                    }
                    break;
                case "load":
                    try {
                        inputStream = new ObjectInputStream(new FileInputStream("SavedGame.bin"));
                        inputStream.readObject();
                        inputStream.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Cannot find SavedGame.bin");
                    }
                    catch (IOException e) {
                        System.out.println("Problem with file output.");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;


                case "help":
                    System.out.println();
                    System.out.println("\t\tBATTLESHIPS GAME");
                    System.out.println("This is the traditional game called battleships");
                    System.out.println("The goal is to sink all of the ships of the opposing player or be the player");
                    System.out.println("that sunk the most ships after a certain number of moves.");
                    System.out.println();
                    System.out.println();
                    System.out.println("The game starts of with a list of commands: SAVE, LOAD, HELP, EXIT");
                    System.out.println("SAVE: saves the game in a binary file for it to get loaded later on.");
                    System.out.println("LOAD: loads the last saved game.");
                    System.out.println("HELP: produces the current text.");
                    System.out.println("EXIT: exits the game.");
                    System.out.println("After the command section is finished the player(s) will have to choose the size of the board");
                    System.out.println("and wether the ships will be placed automatically or not, then the game starts for each player");
                    System.out.println("to make a move until the game is finished.");
                    System.out.println("Good Luck!!!");
                    System.out.println();
                    break;
                case "exit":
                    System.out.print("\tType [y/n]: ");
                    char tempChoice = scan.next().charAt(0);
                    if(tempChoice == 'y') {
                        System.exit(0);
                    }
                    else {
                        selectCommand();
                    }
                    break;
            }
        return "";
    }
    
    public void init() {
        /* ROW-COLUMN COORDINATES SECTION */
        Scanner scan = new Scanner(System.in);
        boolean ready = false;
        do {
            System.out.print("Type number of rows [10,15]: ");
            if(scan.hasNextInt()) {
                numOfRows = scan.nextInt();
            }
            System.out.print("Type number of cols [10,15]: ");
            if(scan.hasNextInt()) {
                numOfCols = scan.nextInt();
            }
            if(((numOfRows >= 10) && (numOfRows <= 15)) && ((numOfCols >= 10) && (numOfCols <= 15))) {
                ready = true;
            }
        } while(!ready);
        /* END OF SECTION */
        
        /* PLAYER SECTION (HUMAN OR COMPUTER) */
        //ready = false;
        String choice1;
        
        System.out.println("Player 1 controlled by: ");
        System.out.println(" 1) Human");
        System.out.println(" 2) Computer"); 
        
        do
        {           
            System.out.print("Type 1 or 2: ");            
            choice1 = scan.next(); 
            
            if((!choice1.equals("1"))
                &&(!choice1.equals("2")))
            {
                System.out.println("Wrong Input!!\nTry again..");
            }            
           
        }while((!choice1.equals("1"))
                &&(!choice1.equals("2")));
        int choiceForPlayer1 = Integer.parseInt(choice1);
       
        String choice2;
        
        System.out.println("Player 2 controlled by: ");
        System.out.println(" 1) Human");
        System.out.println(" 2) Computer"); 
        
        do
        {           
            System.out.print("Type 1 or 2: ");            
            choice2 = scan.next(); 
            
            if((!choice2.equals("1"))
                &&(!choice2.equals("2")))
            {
                System.out.println("Wrong Input!!\nTry again..");
            }            
           
        }while((!choice2.equals("1"))
                &&(!choice2.equals("2")));
        int choiceForPlayer2 = Integer.parseInt(choice2);
        scan.nextLine();
        
        if(choiceForPlayer1 == 1) {
            player1 = new HumanPlayer("", 0);
        }
        else if(choiceForPlayer1 == 2) {
            player1 = new ComputerPlayer("PC1", 0);
        }
        
        if(choiceForPlayer2 == 1) {
            player2 = new HumanPlayer("", 0);
        }
        else if(choiceForPlayer2 == 2) {
            player2 = new ComputerPlayer("PC2", 0);
        }
        if(player1.getClass() == HumanPlayer.class) {
            System.out.print("Type player 1's name: ");
            String name = scan.nextLine();
            player1.setName(name);
        }
        
        if(player2.getClass() == HumanPlayer.class) {
            System.out.print("Type player 2's name: ");
            String name = scan.nextLine();
            player2.setName(name);
        }
        
        
        System.out.println(player1.getName());
        System.out.println(player2.getName());
        
        
        /* END OF SECTION */
        
        /* INITIALIZE FIELD SECTION */
        player1.initField(numOfRows, numOfCols);
        player2.initField(numOfRows, numOfCols);
        
        player1.getField().setPlayer(player1);
        player2.getField().setPlayer(player2);
        /* END OF SECTION */
        
        /* WHEN TO FINISH GAME */
        System.out.println();
                    
        System.out.println("Type 1 if you want the game to be played until the end.");
        System.out.println("Type 2 if you want the game to be played until a certain number of rounds.");
        System.out.println();
        
        String choiceEnd;
        do
        {
            System.out.print("Type 1 or 2: ");
            choiceEnd = scan.next();
            if((!choiceEnd.equalsIgnoreCase("1"))&&(!choiceEnd.equalsIgnoreCase("2")))
            {
                System.out.println("Wrong input\nTry again..");
            }
            
        }while((!choiceEnd.equals("1"))&&(!choiceEnd.equals("2")));
        
        choiceForEnd = Integer.parseInt(choiceEnd);
        scan.nextLine();
        
        if(choiceForEnd == 1) {
            rounds = -1;
        }
        else if(choiceForEnd == 2) {
            System.out.print("Type the number of rounds that you want for the game to finish: ");
            if(scan.hasNextInt()) {
                rounds = scan.nextInt();
            }
        }
        /* END OF SECTION */
    }
    
    public void placeShips() {
        saveIsValidCommand = true;
        player1.placeShips(player2.getField());
        player2.placeShips(player1.getField());
    }
    
    public void play() {
        //Δημιουργία αντικειμένου Random() ώστε να αποφασιστεί τυχαία ποιος 
        //παίκτης θα παίξει πρώτος
        Random rnd = new Random();
        int startPlayer = rnd.nextInt(2);
        
        Player playerA;
        Player playerB;
                   
        System.out.println("**************************************");
        System.out.println("*\tSTART OF BATLESHIP\t    *");
        System.out.println("**************************************");           
           
        if(startPlayer == 1)
        {
            playerA = player1;
            playerB = player2;
        }    
        else
        {
            playerA = player2;
            playerB = player1;
        }           
            //Οι παίκτες θα παίζουν ώσπου ο ένας από τους δύο να μείνει χωρίς
            //πλοία ή όσο ο αριθμός των γύρων είναι μεγαλύτερος του μηδέν
            while(((playerA.getNumOfShips() > 0) && (playerB.getNumOfShips() > 0)))
            {
                System.out.println(playerA.getField().toStringDebugging());
                System.out.println(playerA.getField().toString());
                try {
                    playerA.getField().processValidMove(playerA.selectMove());
                }
                catch(MoveIsCommandException e) {
                    selectCommand();
                }
                
                if(playerA.getNumOfShips() == 0) {
                    break;
                }
                
                System.out.println(playerB.getField().toStringDebugging());
                System.out.println(playerB.getField().toString());  
                try {
                    playerB.getField().processValidMove(playerB.selectMove());
                }
                catch(MoveIsCommandException e) {
                    selectCommand();
                }
                
                if(rounds != -1) {
                    rounds--;
                }
                
                if(rounds == 0) {
                    break;
                }
            }
    }
    
    
    public void showResult() {
        System.out.println(player1.getField().toStringWithShips());
        System.out.println(player2.getField().toStringWithShips());
        System.out.println(player1.getName() + "'s score: " + player1.getScore());
        System.out.println(player1.getName() + "'s ships left: " + player1.getNumOfShips());
        System.out.println(player2.getName() + "'s score: " + player2.getScore());
        System.out.println(player2.getName() + "'s ships left: " + player2.getNumOfShips());
    }
}
