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
public class Field implements Serializable {
    private final int numRows;
    private final int numCols;
    private final Location[][] locations;
    private Player player;
    
    public Field(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        
        locations = new Location[numRows][numCols];
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                locations[i][j] = new Location(i, j, null, false);
            }    
        }
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public int getNumRows() {
        return numRows;
    }
    
    public int getNumCols() {
        return numCols;
    }
    
    public Location getLocation(int r, int c) {
        return locations[r][c];
    }
    
    public Location getLocation(String locString) {
        String rowStr = locString.substring(0, 1);
        String colStr = locString.substring(1);
        
        int col = Integer.parseInt(colStr) - 1;
        char[] ch = rowStr.toCharArray();
        int row = ch[0] - 'A';

        try {
            if((row >= 0 && row <= numRows - 1) && (col >= 0 && col <= numCols - 1)) {
                return getLocation(row, col);
            }
            else {
                throw new InvalidLocationException("Invalid coordinates");
            }
        }
        catch(InvalidLocationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public boolean checkShipLocation(Ship s, int startRow, int startCol, ShipDirection dir) {
        int row = startRow;
        int col = startCol;
        Location loc;
        int counter = 0;   
        if(dir.equals(ShipDirection.HORIZONTAL)) {
            for(int i = 0; i < s.getLength(); i++) {
                loc = getLocation(row, col);
                if(loc.isEmpty() && col < numCols) {
                    col++;
                    counter++;
                }
            }       
        }    
        else if(dir.equals(ShipDirection.VERTICAL)) {
            for(int i = 0; i < s.getLength(); i++) {
                loc = getLocation(row, col);
                if(loc.isEmpty() && row < numRows) {
                    row++;
                    counter++;
                }
            }
        }
        
        if(counter == s.getLength()) {
            if(dir.equals(ShipDirection.VERTICAL)) {
                for(int i = 0; i < s.getLength(); i++) {
                    getLocation(startRow + i, startCol).setShip(s);
                }    
            }
            else if(dir.equals(ShipDirection.HORIZONTAL)) {
                for(int i = 0; i < s.getLength(); i++) {
                    getLocation(startRow, startCol + i).setShip(s);
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean placeShipRandomly(Ship s, int maxTries, boolean checkMarked) {
        Random rand = new Random();
        int row;
        int col;
        ShipDirection[] dirs = ShipDirection.values();
        ShipDirection dir;
        
        if((maxTries == 0) && (checkMarked == false)) {
            dir = dirs[rand.nextInt(dirs.length)];
            if(dir.equals(ShipDirection.HORIZONTAL)) {
                do {
                    row = rand.nextInt(numRows);
                    col = rand.nextInt(numCols - s.getLength());
                } while(!checkShipLocation(s, row, col, dir));
                if(!locations[row][col].isEmpty()) {
                    locations[row][col].getShip().setStart((char)('A' + row) + "" + (col + 1) + "");
                    locations[row][col].getShip().setDir(dir);
                }
                return !locations[row][col].isEmpty();
            }
            else if(dir.equals(ShipDirection.VERTICAL)){
                do {
                    row = rand.nextInt(numRows - s.getLength());
                    col = rand.nextInt(numCols);
                } while(!checkShipLocation(s, row, col, dir));
                if(!locations[row][col].isEmpty()) {
                    locations[row][col].getShip().setStart((char)('A' + row) + "" + (col + 1) + "");
                    locations[row][col].getShip().setDir(dir);
                }
                return !locations[row][col].isEmpty();
            }
        }
        else if((maxTries == 0) && (checkMarked == true)) {
            dir = dirs[rand.nextInt(dirs.length)];
            if(dir.equals(ShipDirection.HORIZONTAL)) {
                do {
                    row = rand.nextInt(numRows);
                    col = rand.nextInt(numCols - s.getLength());
                } while(!checkShipLocation(s, row, col, dir) && locations[row][col].isMarked());
                if(!locations[row][col].isEmpty()) {
                    locations[row][col].getShip().setStart((char)('A' + row) + "" + (col + 1) + "");
                    locations[row][col].getShip().setDir(dir);
                }
                return !locations[row][col].isEmpty();
            }
            else if(dir.equals(ShipDirection.VERTICAL)){
                do {
                    row = rand.nextInt(numRows - s.getLength());
                    col = rand.nextInt(numCols);
                } while(!checkShipLocation(s, row, col, dir) && locations[row][col].isMarked());
                if(!locations[row][col].isEmpty()) {
                    locations[row][col].getShip().setStart((char)('A' + row) + "" + (col + 1) + "");
                    locations[row][col].getShip().setDir(dir);
                }
                return !locations[row][col].isEmpty();
            }
        }
        else if((maxTries > 0) && (checkMarked == true)) {
            dir = dirs[rand.nextInt(dirs.length)];
            do {
                row = rand.nextInt(numRows);
                col = rand.nextInt(numCols - s.getLength());
                checkShipLocation(s, row, col, dir);
                maxTries--;
            } while(maxTries > 0 && locations[row][col].isMarked());
            if(!locations[row][col].isEmpty()) {
                locations[row][col].getShip().setStart((char)('A' + row) + "" + (col + 1) + "");
                locations[row][col].getShip().setDir(dir);
            }
            return !locations[row][col].isEmpty(); // true
        }
        return false;
    }
            
    public boolean placeShip(Ship s, boolean checkMarked) {
        String start = s.getStart();
        int row = start.charAt(0) - 'A';
        int col = Integer.parseInt(start.substring(1)) - 1;
        
        if(checkMarked) {
            return checkShipLocation(s, row, col, s.getDir()) && !locations[row][col].isMarked();
        }
        else {
            return checkShipLocation(s, row, col, s.getDir());
        }
    }
    
    public void removeShip(Ship s) {
        ShipDirection dir = s.getDir();
        String start = s.getStart();
        int row = start.charAt(0) - 'A';
        int col = Integer.parseInt(start.substring(1)) - 1;
        
        if(dir.equals(ShipDirection.HORIZONTAL)) {
            for(int i = 0; i < s.getLength(); i++) {
                locations[row][col].setShip(null);
                col++;
            }
        }
        else if(dir.equals(ShipDirection.VERTICAL)) {
            for(int i = 0; i < s.getLength(); i++) {
                locations[row][col].setShip(null);
                row++;
            }
        }
    }
    
    public void isShipThreatened() {
        for(int i=0; i<numRows; i++) {
            for(int j=0; j<numCols; j++) {
                if(!locations[i][j].isEmpty()) {
                    locations[i][j].getShip().threaten();                  
                } 
            }
        }
    }
    
    public void processValidMove(Location moveLoc) {
        if(moveLoc != null) {
            moveLoc.mark();
            isShipThreatened();
        
            if(!moveLoc.isEmpty()) {
                if(moveLoc.getShip().isSinking()) {
                    player.incrementScore(moveLoc.getShip());
                    System.out.println(moveLoc.getShip().getSinkMessage());
                }
                else if(moveLoc.isHit()) {
                    System.out.println(moveLoc.getShip().getHitMessage());
                }
            }
            else {
                System.out.println("Oops you missed...");
            }
            System.out.println(toString());
        }
    }
     
    @Override
    public String toString() {
        String array = "   ";
        char letter = 'A';
        
        for(int i = 0; i < numCols; i++) {
            if(i < 9) {
                array += "  ";
                array += (i+1);
            }
            else {
                array += " ";
                array += (i+1);
            }
        }
        array += "\n    ";
        
        //array += "  ";
        for(int i = 0; i < numCols; i++) {
            array += "---";
        }
        array += "\n";
        
        for(int i = 0; i < numRows; i++) {
            array += letter + " |  ";
            letter++;
            for(int j = 0; j < numCols; j++) {
                if(!locations[i][j].isMarked()) {
                    array += ".  ";
                }
                else if(locations[i][j].isMarked() && !locations[i][j].isEmpty() && locations[i][j].getShip().isSinking()) {
                    array += "x" + locations[i][j].getShip().getLetter() + " ";
                }
                else if(locations[i][j].isMarked() && !(locations[i][j].isEmpty())) {
                    if(locations[i][j].getShip().isHit()) {
                        array += "x  ";
                    }
                }
                /*else if(locations[i][j].isMarked() && locations[i][j].isEmpty()){
                    if(locations[i][j].getShip().isSinking()) {
                        array += "x" + locations[i][j].getShip().getLetter() + "  ";
                    }
                }*/
                else if(locations[i][j].isMarked() && locations[i][j].isEmpty()) {
                    array += "o  ";
                }
            }
            array += "\n";
        }
        return array;
    }
    
    public String toStringWithShips() {
        String array;
        if(player.hasWon()) {
            System.out.println();
            System.out.println();
            System.out.println("!!!" + player.getName() + " HAS WON!!!\n\n");
        }
        
        array = "" + player.getName() + "'s Field: \n\n";
        char letter = 'A';
        
        array += "   ";
        for(int i = 0; i < numCols; i++) {
            if(i < 9) {
                array += "  ";
                array += (i+1);
            }
            else {
                array += " ";
                array += (i+1);
            }
        }
        array += "\n    ";
        
        for(int i = 0; i < numCols; i++) {
            array += "---";
        }
        array += "\n";
        
        for(int i = 0; i < numRows; i++) {
            array += letter + " |  ";
            letter++;
            for(int j = 0; j < numCols; j++) {
                if(!locations[i][j].isMarked()) {
                    array += ".  ";
                }
                else if(locations[i][j].isMarked() && locations[i][j].isEmpty()) {
                    array += "o  ";
                }
                else if(locations[i][j].isMarked() && locations[i][j].getShip().isHit()) {
                    array += "x" + locations[i][j].getShip().getLetter() + " ";
                }
                else if(!locations[i][j].isEmpty()){
                    array += locations[i][j].getShip().getLetter() + "  ";
                }
            }
            array += "\n";
        }
        return array;
    }
    
    public String toStringDebugging() {
        
        String array = "" + player.getName() + "'s Field: \n\n";
        char letter = 'A';
        
        array += "   ";
        
        for(int i = 0; i < numCols; i++) {
            if(i < 9) {
                array += "  ";
                array += (i+1);
            }
            else {
                array += " ";
                array += (i+1);
            }
        }
        array += "\n    ";
        
        //array += "  ";
        for(int i = 0; i < numCols; i++) {
            array += "---";
        }
        array += "\n";
        
        for(int i = 0; i < numRows; i++) {
            array += letter + " |  ";
            letter++;
            for(int j = 0; j < numCols; j++) {
                if(locations[i][j].isEmpty()) {
                    array += "o  ";
                }
                else if(!locations[i][j].isEmpty()){
                    array += locations[i][j].getShip().getLetter() + "  ";
                }
            }
            array += "\n";
        }
        return array;
    }
}
