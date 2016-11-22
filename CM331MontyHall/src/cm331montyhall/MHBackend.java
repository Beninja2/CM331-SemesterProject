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
public class MHBackend 
{
    List<Boolean> DoorArray;
    public int NumDoors = 3;
    
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
        DoorArray=new ArrayList<Boolean>(Arrays.asList(new Boolean[10]));
        Collections.fill(DoorArray, Boolean.TRUE);
        //DoorArray = new ArrayList<>();
        //int randVal = ThreadLocalRandom.current().nextInt(0, NumDoors + 1);
        //DoorArray(randVal) = "1";
    }
    
    public void NewMHProblem(int doors)
    {
        setNumDoors(doors);
    }
}
