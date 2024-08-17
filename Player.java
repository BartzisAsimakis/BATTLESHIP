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
public abstract class Player implements Serializable{
    private int numOfShips;
    private String name;
    private int score;
    protected Field field;
    
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
        numOfShips = 7;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void initScore(int score) {
        score = 0;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public Field getField() {
        return field;
    }
    
    public int getNumOfShips() {
        return numOfShips;
    }
    
    public void initField(int r, int c) {
        field = new Field(r, c);
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                field.getLocation(i, j).setShip(null);
                field.getLocation(i, j).setMarked(false);
            }
        }
    }
    
    public abstract void placeShips(Field otherField);
    
    public boolean hasWon() {
        return numOfShips == 0;
    }
    
    public void incrementScore(Ship ship)
    {
        switch (ship.getLetter()) {
            case "A":
                score += 5;
                break;
            case "D":
                score += 2;
                break;
            case "S":
                score += 3;
                break;
        }
        //field.removeShip(ship);
        numOfShips--;
    }
    
    public abstract Location selectMove() throws MoveIsCommandException;
}
