package ExamScheduling;
import java.util.ArrayList;
import java.util.Random;
/**
 *  This class holds all the functions that are applied on the timetable.
 *  In this project PSO considers single Timetable object to be a single state.
 *  @author harshul 
 */

public class TimeTable {
    
    private int slots = 8 ;
    public Course[] courses ;
    /*
     *  Constructor to the TimeTable initializes all the courses
     */
    public TimeTable(ArrayList args)
    {
        this.courses = new Course[args.size()];
        int i = 0;
        for( Object c: args )
        {
            this.courses[i] = new Course(c.toString(),getRandomSlot());
            i++;
        }
    }

    /*
     *  This function generates the random slots for each course of the 
     *  current timetable instance.
     */
    private int getRandomSlot()
    {
        int i =0 ;
        Random random = new Random();
        return (random.nextInt(this.slots)+1) ;
    }
    
    public int fitValue()
    {
        /*
         *  PSEUDOCODE
         *  value = 0
         *  Foreach( course as c1)
         *      Foreach( course as c2)
         *          if c1 < c2
         *              CourseOperations co = new CourseOperations(c1, c2);
         *              value += co.penalty()
         */
        int totalClash = 0;
        for(Course c1 : this.courses)
            for(Course c2 : this.courses)
            {
                if( c1.cid.compareTo(c2.cid) < 0 )
                {
                    CourseOperations co = new CourseOperations(c1, c2);
                    totalClash += co.cPenalty();
                }
            }
        return totalClash;
    }
}
