/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

import java.io.Serializable;
/**
 *
 * @authors Λευτέρης - Ασημάκης
 */
public class Destroyer extends Ship implements Serializable {
    public Destroyer(int length, int points, String letter, Field field) {
        super(length, points, letter, field);
    }

    @Override
    public String getSinkMessage() {
        return "A Destroyer is sinking...";
    }

    @Override
    public void threaten() {
        System.out.println("Inside threatened..");
        int threatDistance = 4;
        if(!isHit())
        {
            int r = getField().getLocation(start).getRow();
            int c = getField().getLocation(start).getCol();
            Location locOut = getField().getLocation(start);
            System.out.println(locOut.getRow()+" "+locOut.getCol());
            
            for(int i = 1; i <= threatDistance; i++) {
                if(r + i < getField().getNumRows()) {
                    if(getField().getLocation(r+i, c).isMarked()) {
                        System.out.println("Destroyer threatened..");
                        getField().removeShip(locOut.getShip());
                        getField().placeShipRandomly(this, 1, true);
                        System.out.println(locOut.getRow()+" "+locOut.getCol());
                        break;
                    } 
                }
                
                if(r - i >= 0) {
                    if(getField().getLocation(r - i, c).isMarked()) {
                        System.out.println("Destroyer threatened..");
                        getField().removeShip(locOut.getShip());
                        getField().placeShipRandomly(this, 1, true);
                        System.out.println(locOut.getRow() + " " + locOut.getCol());
                        break;
                    }     
                }
                
                if(c + i < getField().getNumCols()) {               
                    if(getField().getLocation(r, c + i).isMarked()) {
                        System.out.println("Destroyer threatened..");
                        getField().removeShip(locOut.getShip());
                        getField().placeShipRandomly(this, 1, true);
                        System.out.println(locOut.getRow() + " " + locOut.getCol());
                        break;
                    }     
                }
                
                if(c - i >= 0) {
                    if(getField().getLocation(r, c - i).isMarked()) {
                        System.out.println("Destroyer threatened..");
                        getField().removeShip(locOut.getShip());
                        getField().placeShipRandomly(this, 1, true);
                        System.out.println(locOut.getRow() + " " + locOut.getCol());
                        break;
                    }    
                }
            }  
        }
    }
}
