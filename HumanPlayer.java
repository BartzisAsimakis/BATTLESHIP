/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;


import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author Λευτέρης - Ασημάκης
 */
public class HumanPlayer extends Player implements Serializable{

    public HumanPlayer(String name, int score) {
        super(name, score);
    }
   
    @Override
    public void placeShips(Field otherField) {
        AircraftCarrier a1 = new AircraftCarrier(5, 5, "A", otherField);
        AircraftCarrier a2 = new AircraftCarrier(5, 5, "A", otherField);
        
        Destroyer d1 = new Destroyer(3, 2, "D", otherField);
        Destroyer d2 = new Destroyer(3, 2, "D", otherField);
        Destroyer d3 = new Destroyer(3, 2, "D", otherField);
        
        Submarine s1 = new Submarine(1, 3, "S", otherField);
        Submarine s2 = new Submarine(1, 3, "S", otherField);
        
        Scanner input = new Scanner(System.in);
        Location checkStart;
        char choice;
        System.out.println("Do you want to place the ships by yourself?");  
        System.out.print("Type [y/n]: ");
        choice = input.nextLine().charAt(0);
        
        if(choice == 'y') {
            System.out.println("AIRCRAFTCARRIERS");
        
            System.out.print("Enter the start position of the ship: ");
            String startPos = input.nextLine();
            checkStart = getField().getLocation(startPos);
            System.out.println(checkStart.getRow());
            System.out.println(checkStart.getCol());
            if(checkStart != null) {
                a1.setStart(startPos);
            }
        
            System.out.print("Enter the ship direction('h'(horizontal) or 'v'(vertical)): ");
            String dirS = input.nextLine();
            a1.setDir(ShipDirection.fromString(dirS));
            
            otherField.placeShip(a1, false);
        
            System.out.print("Enter the start position of the ship: ");
            startPos = input.nextLine();
            checkStart = getField().getLocation(startPos);
            if(checkStart != null) {
                a2.setStart(startPos);
            }
            a2.setStart(startPos);        
        
            System.out.print("Enter the ship direction: ");
            dirS = input.nextLine();
            a2.setDir(ShipDirection.fromString(dirS));
            
            while(!otherField.placeShip(a2, false)) {
                System.out.println("Wrong input retype the coordinates of the ship.");
                System.out.print("Enter the start position of the ship: ");
                startPos = input.nextLine();
                checkStart = getField().getLocation(startPos);
                if(checkStart != null) {
                    a2.setStart(startPos);
                }
                System.out.print("Enter the ship direction('h'(horizontal) or 'v'(vertical)): ");
                dirS = input.nextLine();
                a2.setDir(ShipDirection.fromString(dirS));
            }
        
            System.out.println("DESTROYERS");
        
            System.out.print("Enter the start position of the ship: ");
            startPos = input.nextLine();
            checkStart = getField().getLocation(startPos);
            if(checkStart != null) {
                d1.setStart(startPos);
            }
            d1.setStart(startPos);        
        
            System.out.print("Enter the ship direction: ");
            dirS = input.nextLine();
            d1.setDir(ShipDirection.fromString(dirS));
            
            while(!otherField.placeShip(d1, false)) {
                System.out.println("Wrong input retype the coordinates of the ship.");
                System.out.print("Enter the start position of the ship: ");
                startPos = input.nextLine();
                checkStart = getField().getLocation(startPos);
                if(checkStart != null) {
                    d1.setStart(startPos);
                }
                System.out.print("Enter the ship direction('h'(horizontal) or 'v'(vertical)): ");
                dirS = input.nextLine();
                d1.setDir(ShipDirection.fromString(dirS));
            }
        
            System.out.print("Enter the start position of the ship: ");
            startPos = input.nextLine();
            checkStart = getField().getLocation(startPos);
            if(checkStart != null) {
                d2.setStart(startPos);
            }
            d2.setStart(startPos);        
        
            System.out.print("Enter the ship direction: ");
            dirS = input.nextLine();
            d2.setDir(ShipDirection.fromString(dirS));
            
            while(!otherField.placeShip(d2, false)) {
                System.out.println("Wrong input retype the coordinates of the ship.");
                System.out.print("Enter the start position of the ship: ");
                startPos = input.nextLine();
                checkStart = getField().getLocation(startPos);
                if(checkStart != null) {
                    d2.setStart(startPos);
                }
                System.out.print("Enter the ship direction('h'(horizontal) or 'v'(vertical)): ");
                dirS = input.nextLine();
                d2.setDir(ShipDirection.fromString(dirS));
            }
        
            System.out.print("Enter the start position of the ship: ");
            startPos = input.nextLine();
            checkStart = getField().getLocation(startPos);
            if(checkStart != null) {
                d3.setStart(startPos);
            }
            d3.setStart(startPos);        
        
            System.out.print("Enter the ship direction: ");
            dirS = input.nextLine();
            d3.setDir(ShipDirection.fromString(dirS));
            
            while(!otherField.placeShip(d3, false)) {
                System.out.println("Wrong input retype the coordinates of the ship.");
                System.out.print("Enter the start position of the ship: ");
                startPos = input.nextLine();
                checkStart = getField().getLocation(startPos);
                if(checkStart != null) {
                    d3.setStart(startPos);
                }
                System.out.print("Enter the ship direction('h'(horizontal) or 'v'(vertical)): ");
                dirS = input.nextLine();
                d3.setDir(ShipDirection.fromString(dirS));
            }

            System.out.println("SUBMARINES");

            System.out.print("Enter the start position of the ship: ");
            startPos = input.nextLine();
            checkStart = getField().getLocation(startPos);
            if(checkStart != null) {
                s1.setStart(startPos);
            }
            s1.setStart(startPos);        

            System.out.print("Enter the ship direction: ");
            dirS = input.nextLine();
            s1.setDir(ShipDirection.fromString(dirS));

            while(!otherField.placeShip(s1, false)) {
                System.out.println("Wrong input retype the coordinates of the ship.");
                System.out.print("Enter the start position of the ship: ");
                startPos = input.nextLine();
                checkStart = getField().getLocation(startPos);
                if(checkStart != null) {
                    s1.setStart(startPos);
                }
                System.out.print("Enter the ship direction('h'(horizontal) or 'v'(vertical)): ");
                dirS = input.nextLine();
                s1.setDir(ShipDirection.fromString(dirS));
            }

            System.out.print("Enter the start position of the ship: ");
            startPos = input.nextLine();
            checkStart = getField().getLocation(startPos);
            System.out.println(checkStart.getRow());
            System.out.println(checkStart.getCol());
            if(checkStart != null) {
                s2.setStart(startPos);
            }
            s2.setStart(startPos);        

            System.out.print("Enter the ship direction: ");
            dirS = input.nextLine();
            s2.setDir(ShipDirection.fromString(dirS));

            while(!otherField.placeShip(s2, false)) {
                System.out.println("Wrong input retype the coordinates of the ship.");
                System.out.print("Enter the start position of the ship: ");
                startPos = input.nextLine();
                checkStart = getField().getLocation(startPos);
                if(checkStart != null) {
                    s2.setStart(startPos);
                }
                System.out.print("Enter the ship direction('h'(horizontal) or 'v'(vertical)): ");
                dirS = input.nextLine();
                s2.setDir(ShipDirection.fromString(dirS));
            }
        }
        else {
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
        }
    }
    
    @Override
    public Location selectMove() throws MoveIsCommandException {
        Scanner scan = new Scanner(System.in);
        String move;
            do {
                System.out.print("Select a move: ");
                move = scan.nextLine();
            } while((move.length() > 4) || (move.length() < 2));
            
            switch(move.toLowerCase()) {
                case "help":
                case "save":
                case "load":
                case "exit":
                    throw new MoveIsCommandException(Command.fromString(move));
            }
            
            if(move.charAt(0) >= 'a' && move.charAt(0) <= 'o') {
                move = move.toUpperCase();
            }
            return getField().getLocation(move);
        }
    }

