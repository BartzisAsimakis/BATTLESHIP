/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Λευτέρης - Ασημάκης
 */
public class ComputerPlayer extends Player implements Serializable {

    public ComputerPlayer(String name, int score) {
        super(name, score);
    }

    @Override
    public void placeShips(Field otherField) {
        //Δημιουργία δύο (2) αντικειμένων αεροπλανοφόρων
        AircraftCarrier a1 = new AircraftCarrier(5, 5, "A", otherField);
        AircraftCarrier a2 = new AircraftCarrier(5, 5, "A", otherField);
        
        //Δημιουργία τριών (3) αντικειμένων αντιτορπιλικών
        Destroyer d1 = new Destroyer(3, 2, "D", otherField);
        Destroyer d2 = new Destroyer(3, 2, "D", otherField);
        Destroyer d3 = new Destroyer(3, 2, "D", otherField);
        
        //Δημιουργία δύο (2) αντικειμένων υποβρυχίων
        Submarine s1 = new Submarine(1, 3, "S", otherField);
        Submarine s2 = new Submarine(1, 3, "S", otherField);
        
        int maxT = 0;
        boolean placeShip = false;
        while(!placeShip)
        {                
            if(otherField.placeShipRandomly(a1, maxT, false))
            {                    
                placeShip=true;
            }
        }        
             
        placeShip = false;

        while(!placeShip)
        {                
            if(otherField.placeShipRandomly(a2, maxT, false))
            {                    
                placeShip=true;
            }
        }        
        System.out.println("Aircraftcarriers placement OK");
            
              
        placeShip = false;

        while(!placeShip)
        {                
            if(otherField.placeShipRandomly(d1, maxT, false))
            {                    
                placeShip=true;
            }
        }           
        placeShip = false;
        while(!placeShip)
        {                
            if(otherField.placeShipRandomly(d2, maxT, false))
            {                    
                placeShip=true;
            }
        }
        placeShip = false;
        while(!placeShip)
        {                
            if(otherField.placeShipRandomly(d3, maxT, false))
            {                    
                placeShip=true;
            }
        }
            
        System.out.println("Destroyers placement OK");
               
        placeShip = false;
        while(!placeShip)
        {                
            if(otherField.placeShipRandomly(s1, maxT, false))
            {                    
                    placeShip=true;
            }
        }
        placeShip = false;
        while(!placeShip)
        {                
            if(otherField.placeShipRandomly(s2, maxT, false))
            {                    
                placeShip=true;
            }
        }
            
        System.out.println("Submarines placement OK");
        
        System.out.println(a1.getStart() + " " + a1.getDir());
        System.out.println(a2.getStart() + " " + a2.getDir());
        System.out.println(d1.getStart() + " " + d1.getDir());
        System.out.println(d2.getStart() + " " + d2.getDir());
        System.out.println(d3.getStart() + " " + d3.getDir());
        System.out.println(s1.getStart() + " " + s1.getDir());
        System.out.println(s2.getStart() + " " + s2.getDir());
    }

    @Override
    public Location selectMove() {
        Random rand = new Random();
        int randRow;
        int randCol;
        
        do {
            randRow = rand.nextInt(0, getField().getNumRows());
            randCol = rand.nextInt(0, getField().getNumCols());
        } while(getField().getLocation(randRow, randCol).isMarked());
        
        return getField().getLocation(randRow, randCol);
    }
    
}
