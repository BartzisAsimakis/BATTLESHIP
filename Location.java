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
public class Location implements Serializable {
    private int row;
    private int col;
    private Ship ship;
    private boolean marked;
    
    public Location() {
        row = -1;
        col = -1;
        ship = null;
        marked = false;
    }
    
    public Location(int row, int col, Ship ship, boolean marked) {
        this.row = row;
        this.col = col;
        this.ship = ship;
        this.marked = marked;
    }
       
    public void setRow(int row) {
        this.row = row;
    }
    
    public void setCol(int col) {
        this.col = col;
    }
    
    public void setShip(Ship ship) {
        this.ship = ship;
    }
    
    public void setMarked(boolean marked) {
        this.marked = marked;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public Ship getShip() {
        return ship;
    }
    
    public boolean isMarked() {
        return marked;
    }
    
    public boolean isEmpty() {
        return ship == null;
    }
    
    public void mark() {
        setMarked(true);
        if(!isEmpty()) {
            ship.hit();
        }          
    }
    
    public boolean isHit() {
        if(!isEmpty()) {
            return ship.getField().getLocation(row, col).isMarked();
        } 
        return false;
    }
}
