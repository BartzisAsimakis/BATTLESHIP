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
public enum ShipDirection implements Serializable{
    HORIZONTAL, VERTICAL;
    
    public static ShipDirection fromString(String dirString) {
        try {
            switch(dirString.toLowerCase()) {
                case "h":
                    return HORIZONTAL;
                case "v":
                    return VERTICAL;
                default :
                    throw new InputMismatchException("Must be equal to the \"h\" or \"v\" strings.");
            }
        }
        catch(InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
