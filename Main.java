/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/*
 *  Ασημάκης Μπάρτζης - Α.Μ. : 2022202100129
 *  Ελευθέριος Πουλίτσης - A.M. : 2022202100167
 */
package battleships;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Λευτέρης - Ασημάκης
 */
public class Main implements Serializable {
    public static void main(String[] args) {
        Game game = new Game();
        String command;
        ObjectOutputStream outputStream;
        ObjectInputStream inputStream;
        Scanner input = new Scanner(System.in);
        
        System.out.println();  
        System.out.println("**************************************");
        System.out.println("* WELCOME TO THE BATTLESHIPS GAME!!! *");
        System.out.println("**************************************");
        System.out.println();
        
        System.out.println("If you want to start a new game type \"y\" to start or type \"n\" to show commands: ");
        System.out.print("Type[y/n]: ");
        String choice = input.next();
        if(choice.equalsIgnoreCase("y")) {
            game.init();
            game.placeShips();
            game.play();
            game.showResult();
        }
        else if(choice.equalsIgnoreCase("n")) {
            String TEXT_RED    = "\u001B[31m";
	    String TEXT_RESET  = "\u001B[0m";
            System.out.println("\tGAME COMMANDS: ");
            System.out.println("\t    " + Command.HELP.getCommandString() + ": " + Command.HELP.getHelpText());
            System.out.println(TEXT_RED + "\t    " + Command.SAVE.getCommandString() + ": " + Command.SAVE.getHelpText());
            System.out.print(TEXT_RESET);
            System.out.println("\t    " + Command.LOAD.getCommandString() + ": " + Command.LOAD.getHelpText());
            System.out.println("\t    " + Command.EXIT.getCommandString() + ": " + Command.EXIT.getHelpText());
            System.out.print("Type a command: ");
            String selection = input.next();
            switch(selection.toLowerCase()) {
                case "save":  
                    break;
                case "load":
                    try {
                        inputStream = new ObjectInputStream(new FileInputStream("SavedGame.bin"));
                        Game SavedGame = (Game)inputStream.readObject();
                        SavedGame.play();
                        SavedGame.showResult();
                        inputStream.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Cannot find SavedGame.bin");
                    }
                    catch (IOException e) {
                        System.out.println(e);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
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
                    System.exit(0);
            }
        }
    }
}