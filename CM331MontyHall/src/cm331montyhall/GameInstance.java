//Author: Ben Ciummo
package cm331montyhall;

import java.util.*;
import java.util.concurrent.*;

public final class GameInstance 
{
    private ArrayList<Door> doorList;
    private int winningDoor;
    private int currentSelection;
    private boolean openedEmptyDoor = false;

    public GameInstance()
    {
        this(3);
    }
    
    public GameInstance(int numDoors)
    {
        if (numDoors < 3) {
            throw new java.lang.IllegalArgumentException();
        }
        doorList = this.randomizeResult(numDoors);
    }
    
    public int openEmptyDoor() {
        Door out;
        ArrayList<Door> validDoors = new ArrayList<Door>(doorList);
        validDoors.remove(doorList.get(winningDoor));
        validDoors.remove(doorList.get(currentSelection));
        if (validDoors.size() == 1) {
            out = validDoors.get(0);
        }
        else {
            out = validDoors.get(ThreadLocalRandom.current().nextInt(0, validDoors.size() - 1));
        }
        
        out.setOpened(true);
        this.openedEmptyDoor = true;
        return out.getId();
    }
    
    public ArrayList<Door> getDoorList() {
        return this.doorList;
    }
    public int getNumDoors()
    {
        return this.doorList.size();
    }
    public int getCurrentSelection() {
        return this.currentSelection;
    }
    public void setCurrentSelection(int currentSelection) {
        this.currentSelection = currentSelection;
    }
    public boolean getOpenedEmptyDoor(){
        return this.openedEmptyDoor;
    }
    
    public ArrayList<Door> randomizeResult(int numDoors)
    {
        ArrayList<Door> out = new ArrayList<Door>(numDoors);
        List<Boolean> DoorArray = new ArrayList<Boolean>(Arrays.asList(new Boolean[numDoors]));;
        //Fill array with false
        Collections.fill(DoorArray, Boolean.FALSE);
        
        //Create one random value based on array length
        this.winningDoor = ThreadLocalRandom.current().nextInt(0, numDoors - 1);
        DoorArray.add(winningDoor, Boolean.TRUE);
        
        for (int i = 0; i < numDoors; i++) {
            out.add(new Door(DoorArray.get(i),i));
        }
        
        return out;
    }
}