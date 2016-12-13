/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm331montyhall;

/**
 *
 * @author rndmorris
 */
public class Door {
    private boolean winner = false;
    private boolean opened = false;
    private int id;
    
    public Door(boolean winner, int id) {
        this.winner = winner;
        this.id = id;
    }
    
    public boolean isWinner() {
        return this.winner;
    }
    
    public boolean isOpened() {
        return this.opened;
    }
    public void setOpened(boolean opened) {
        this.opened = opened;
    }
    public int getId() {
        return this.id;
    }
}
