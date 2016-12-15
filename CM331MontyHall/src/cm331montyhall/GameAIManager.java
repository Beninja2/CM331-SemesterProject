/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm331montyhall;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 *
 * @author rndmorris
 */
public class GameAIManager extends Thread{
    private int maxCycles = 0;
    private int percentToSwitch = 0;
    private AtomicInteger gamesWon = new AtomicInteger(0);
    private int numberOfDoors = 3;
    private int threadCount = 1;
    private boolean initialized = false;
    private boolean hasRan = false;
    private TextArea outputArea;
    private Button startBtn = null;
    private Button stopBtn = null;
    private Date startTime = null;
    private long executionTime = -1;
    
    private GameAIWorker[] workers = null;
    
    public void run() {
        if (this.startBtn != null) {
            this.startBtn.setDisable(true);
            this.stopBtn.setDisable(false);
        }
        this.startTime = new Date();
        this.initilize();
        this.play();
        executionTime = new Date().getTime() - this.startTime.getTime();
        if (this.outputArea != null) {
            this.outputArea.appendText(this.getResults());
        }
        if (this.startBtn != null) {
            this.startBtn.setDisable(false);
            this.stopBtn.setDisable(true);
        }
    }
    
    public GameAIManager(int maxCycles, int percentToSwitch, int numberOfDoors) {
        setMaxCycles(maxCycles);
        setPercentToSwitch(percentToSwitch);
        setNumberOfDoors(numberOfDoors);
    }
    public GameAIManager(int maxCycles, int percentToSwitch, int numberOfDoors, int threadCount) {
        this(maxCycles,percentToSwitch,numberOfDoors);
        setThreadCount(threadCount);
    }
    
    public void setOutputArea(TextArea outputArea) {
        if (outputArea == null) {
            throw new NullPointerException();
        }
        this.outputArea = outputArea;
    }
    public void setStartBtn(Button startBtn) {
        if (startBtn == null) {
            throw new NullPointerException();
        }
        this.startBtn = startBtn;
    }
    public void setStopBtn(Button stopBtn) {
        if (stopBtn == null) {
            throw new NullPointerException();
        }
        this.stopBtn = stopBtn;
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
    private void setThreadCount (int threadCount) {
        if (threadCount < 0) {
            throw new IllegalArgumentException();
        }
        this.threadCount = threadCount;
    }

    public void initilize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int cyclesPerWorker = this.maxCycles / this.threadCount;
        int leftoverCycles = this.maxCycles % this.threadCount;
        int totalSwitches = (int)Math.floor(this.maxCycles * (this.percentToSwitch / 100.0));
        int switchesPerWorker = totalSwitches / this.threadCount;
        int leftoverSwitches = totalSwitches % this.threadCount;
                
        workers = new GameAIWorker[this.threadCount];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new GameAIWorker(this.gamesWon);
            workers[i].setCycleCount(cyclesPerWorker);
            if (0 < leftoverCycles) {
                workers[i].setCycleCount(workers[i].getCycleCount() + 1);
                leftoverCycles--;
            }
            workers[i].setSwitchCount(switchesPerWorker);
            if (0 < leftoverSwitches) {
                workers[i].setSwitchCount(workers[i].getSwitchCount()+1);
                leftoverSwitches--;
            }
            workers[i].setNumberOfDoors(this.numberOfDoors);
        }
        this.initialized = true;
        this.hasRan = false;
    }
    
    public void play() {
        if (!initialized) {
            throw new java.lang.IllegalStateException("GameAIManager has not been initilized");
        }
        
        for (GameAIWorker worker : workers) {
            worker.start();
        }
        for (GameAIWorker worker : workers) {
            try {
                worker.join();
            }catch (InterruptedException ie) {
                ie.printStackTrace(System.err);
            }
        }
        this.hasRan = true;
        this.initialized = false;
    }
    
    public String getResults() {
        if (!hasRan) {
            throw new java.lang.IllegalStateException("GameAIManager has not been run to generate any results!");
        }
        StringBuilder output = new StringBuilder();
        output.append("Won ")
                .append(this.gamesWon.get())
                .append(" out of ")
                .append(this.maxCycles)
                .append(" games (")
                .append(String.format("%1.3f", (this.gamesWon.get() * 1.0) / (this.maxCycles * 1.0) * 100 ))
                .append("%) with ")
                .append(this.numberOfDoors)
                .append(" doors, while switching selections ")
                .append(this.percentToSwitch)
                .append("% of the time. Used ")
                .append(this.threadCount)
                .append(" thread(s). Executed in ")
                .append(this.executionTime)
                .append(" milliseconds.\n");
        return output.toString();
    }

    
    
    private class GameAIWorker extends java.lang.Thread{
        
        private AtomicInteger winCounter = null;
        private int cycleCount = 0;
        private int switchCount = 0;
        private int numberOfDoors = 3;
        private GameInstance currentGame = null;
        
        
        
        public GameAIWorker(AtomicInteger winCounter) {
            super();
            this.winCounter = winCounter;
        }
        
        public void setCycleCount(int cycleCount) {
            if (cycleCount < 0) {
                throw new IllegalArgumentException("cycleCount must be greater than or equal to 0.");
            }
            this.cycleCount = cycleCount;
        }
        public void setSwitchCount(int switchCount) {
            if (switchCount < 0) {
                throw new IllegalArgumentException("switchCOunt must be greater than or equal to 0.");
            }
            this.switchCount = switchCount;
        }
        public void setNumberOfDoors(int numberOfDoors) {
            if (numberOfDoors < 3) {
                throw new IllegalArgumentException("numberOfDoors must be greater than or equal to 3.");
            }
            this.numberOfDoors = numberOfDoors;
        }
        public int getCycleCount() {
            return this.cycleCount;
        }
        public int getSwitchCount() {
            return this.switchCount;
        }
        public int getNumberOfDoors() {
            return this.numberOfDoors;
        }
        
        @Override
        public void run() {
            int removedDoor;
            while (0 < this.cycleCount) {
                currentGame = new GameInstance(numberOfDoors);
                currentGame.setCurrentSelection(ThreadLocalRandom.current().nextInt(0,numberOfDoors));
                removedDoor =  currentGame.openEmptyDoor();
                if (0 < switchCount) {
                    ArrayList<Door> tempDoorList = new ArrayList<>(currentGame.getDoorList());
                    tempDoorList.remove(currentGame.getDoorList().get(removedDoor));
                    tempDoorList.remove(currentGame.getDoorList().get(currentGame.getCurrentSelection()));
                    if (tempDoorList.size() == 1) {
                        currentGame.setCurrentSelection(tempDoorList.get(0).getId());
                    } else {
                        currentGame.setCurrentSelection(tempDoorList.get(ThreadLocalRandom.current().nextInt(0,tempDoorList.size())).getId());
                    }
                    this.switchCount--;
                }
                if (currentGame.getWinningDoor() == currentGame.getCurrentSelection()) {
                    winCounter.incrementAndGet();
                }
                this.cycleCount--;
            }
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
