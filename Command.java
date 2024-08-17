/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

import java.io.Serializable;
import java.util.InputMismatchException;

/**
 *
 * @author Λευτέρης - Ασημάκης
 */
public enum Command implements Serializable {
    HELP("help", "Text that provides helpful info for the game."), 
    SAVE("save", "Asks for a file name and saves the game in that file.If the file already exists permission is needed in order to override it."), 
    LOAD("load", "Loads a game that was previously saved using the name of the existing file(Interrupts current game)."), 
    EXIT("exit", "Exits game instantly after given permission.");
    
    private final String commandString;
    private final String helpText;
    
    private Command(String commandString, String helpText) {
        this.commandString = commandString;
        this.helpText = helpText;
    }
    
    public String getCommandString() { 
        return commandString; 
    }
    
    public String getHelpText() { 
        return helpText; 
    }
    
    public static Command fromString(String commandString) {
        try {
            switch(commandString.toLowerCase()) {
                case "help":
                    return HELP;
                case "save": 
                    return SAVE;
                case "load":
                    return LOAD;
                case "exit":
                    return EXIT;
                default :
                    throw new InputMismatchException();
            }
        }
        catch(InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
