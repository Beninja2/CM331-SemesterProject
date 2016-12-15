/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm331montyhall;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author rndmorris
 */
public class GameAIManager {
    private int maxCycles = 0;
    private int percentToSwitch = 0;
    private StringBuilder output = new StringBuilder();
    private AtomicInteger gamesWon = new AtomicInteger(0);
    private int numberOfDoors = 3;
    
    public GameAIManager(int maxCycles, int percentToSwitch, int numberOfDoors) {
        setMaxCycles(maxCycles);
        setPercentToSwitch(percentToSwitch);
        setNumberOfDoors(numberOfDoors);
    }
    
    private void setMaxCycles(int maxCycles) {
        if (maxCycles < 0) {
            throw new IllegalArgumentException();
        }
        this.maxCycles = maxCycles;
    }
    
    private void setPercentToSwitch(int percentToSwitch) {
        if (percentToSwitch < 0 || 100 < percentToSwitch) {
            throw new IllegalArgumentException();
        }
        this.percentToSwitch = percentToSwitch;
    }
    
    private void setNumberOfDoors(int numberOfDoors) {
        if (numberOfDoors < 3) {
            throw new IllegalArgumentException();
        }
        this.numberOfDoors = numberOfDoors;
    }
    
    private class GameAIWorker implements Runnable{
        
        private AtomicInteger gamesWon = null;
        private int cycleCount = 0;
        private int switchCount = 0;
        private GameInstance currentGame = null;
        private int numberOfDoors = 3;
        
        public GameAIWorker(int cycleCount, int switchCount, int numberOfDoors, AtomicInteger gamesWon) {
            this.cycleCount = cycleCount;
            this.switchCount = switchCount;
            this.numberOfDoors = numberOfDoors;
            this.gamesWon = gamesWon;
        }
        
        @Override
        public void run() {
            while (0 < this.cycleCount) {
                currentGame = new GameInstance(numberOfDoors);
                
            }
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
