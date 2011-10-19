package ExamScheduling;
import java.util.*;

/**
 *  This is the Main class for this project. It is the classed to be called by the compiler.
 *  @author harshul 
 */
public class Main {
    
    public static void main(String[] args)
    {
        int argc = args.length, slots ;
        ArrayList courses = new ArrayList(Arrays.asList(args));
        
        PSO pso = new PSO(courses);        
        System.out.print(pso.getGlobalBest().getFitValue());
        System.out.println("\n");
        for( Course c : pso.getGlobalBest().t.courses)
            System.out.println("Course : "+c.cid+" Slot : "+c.slot);
        
    }
}
