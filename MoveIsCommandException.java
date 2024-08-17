/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

import java.io.Serializable;

/**
 *
 * @author Λευτέρης - Ασημάκης
 */
public class MoveIsCommandException extends InvalidLocationException implements Serializable{
    private Command command;
    
    public MoveIsCommandException(Command command) {
        this.command = command;
    }
    
    public Command getCommand() {
        return command;
    }
}
