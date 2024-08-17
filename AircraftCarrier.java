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
public class AircraftCarrier extends Ship implements Serializable {

    public AircraftCarrier(int length, int points, String letter, Field field) {
        super(length, points, letter, field);
    }

    @Override
    public String getSinkMessage() {
        return "An AircraftCarrier is sinking...";
    }
    
    @Override
    public void threaten() {
       
    }
}
