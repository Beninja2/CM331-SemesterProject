package cm331montyhall;

import static java.nio.file.Files.*;
import static java.rmi.Naming.*;
import java.util.*;
import java.util.concurrent.*;
import static java.util.Collections.*;
/**
 *
 * @author Ben Ciummo
 */
public final class Doors 
{
    public List<Boolean> DoorArray;
    public int NumDoors = 3;

    public Doors()
    {
        this.randomizeResult();
    }
    
    public Doors(int numDoors)
    {
        this.setNumDoors(numDoors);
        this.randomizeResult();
    }
    
    public void setNumDoors(int numDoors)
    {
        NumDoors = numDoors;
    }
    
    public int getNumDoors()
    {
        return NumDoors;
    }
    
    public void randomizeResult()
    {
        //Fill array with false
        DoorArray=new ArrayList<Boolean>(Arrays.asList(new Boolean[NumDoors]));
        Collections.fill(DoorArray, Boolean.FALSE);
        
        //Create one random value based on array length
        int randVal = ThreadLocalRandom.current().nextInt(0, NumDoors + 1);
        DoorArray.add(randVal, Boolean.TRUE);
    }
}
