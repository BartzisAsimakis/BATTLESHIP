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
public abstract class Ship implements Serializable {
    private final String letter;
    private final Field field;
    private final int points;
    private final int length;
    protected String start;
    protected ShipDirection dir;
    
    public Ship(int length, int points, String letter, Field field) {
        this.length = length;
        this.points = points;
        this.letter = letter;
        this.field = field;
    }
    
    public void setStart(String start) {
        this.start = start;        
    }
    
    public String getStart() {
        return start;
    }
    
    public void setDir(ShipDirection dir) {
        this.dir = dir;       
    }
    
    public ShipDirection getDir() {
        return dir;
    }
    
    public int getLength() {
        return length;
    }
    
    public int getPoints() {
        return points;
    }
    
    public String getLetter() {
        return letter;
    }
    
    public Field getField() {
        return field;
    }
        
    public String getHitMessage() {
        return "A ship got hit!!!";
    }
    
    public abstract String getSinkMessage();
    
    public void hit() {
        if(isSinking()) { 
            getSinkMessage();
        }    
        else {
            getHitMessage();
        }
    }  
    
    public boolean isHit() {
        String sr = start.substring(0,1);
        int c = Integer.parseInt(start.substring(1));
        
        char[] ch = sr.toCharArray();
        int r = ch[0]-'A';       
        
        if(dir == ShipDirection.HORIZONTAL) {    
            for(int i = 0; i < length; i++) {
                if(field.getLocation(r, c + i - 1).isHit()) {
                    return true;
                }
            }            
        }
        if(dir == ShipDirection.VERTICAL) {    
            for(int j = 0; j < length; j++) {
                if(field.getLocation(r + j, c - 1).isHit())
                    return true;                
            }                       
        }       
        return false; 
    }
    public boolean isSinking() {
        int numOfHits = 0;
        
        Location loc = field.getLocation(start);
        int row = loc.getRow();
        int col = loc.getCol();
        
        for(int i = 0; i < length; i++) {
            if((dir == ShipDirection.HORIZONTAL) && (field.getLocation(row, col + i).isHit())) {
                numOfHits++;
            }
            else if((dir == ShipDirection.VERTICAL) && (field.getLocation(row + i, col).isHit())) {
                numOfHits++;
            }
        }
        return numOfHits == length;
    }
    
    public abstract void threaten();
}
