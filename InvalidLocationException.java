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
public class InvalidLocationException extends Exception implements Serializable{
    
    public InvalidLocationException() {
        //super();
    }
    
    public InvalidLocationException(String message) {
        super(message);
    }
}
