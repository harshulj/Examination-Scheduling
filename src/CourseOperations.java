package ExamScheduling;
import java.util.Collection;
import java.util.ArrayList;

/*
 *  This class applies various operations on two courses.
 *  @author harshul 
 */
public class CourseOperations {
    /*
     *  Constructor for this class.
     *  @
     */
    private Course c1, c2 ;
    public CourseOperations(Course course1, Course course2)
    {
        this.c1 = course1 ;
        this.c2 = course2  ; 
        
    }
    
    /*
     *  This function return the clash value after analyzing the number of same students
     *  in the two courses. 
     *  NOTE : This is not the total clash value of the current timetable state.
     */
    private int stdClash()
    {
        //  Create two collections from the given array of strings.
        Collection list1 = new ArrayList(this.c1.students);
        Collection list2 = new ArrayList(this.c2.students);
        //  Initital size of one collection
        int size1 = list1.size();
        //  Remove the students who are in both, from collection1
        list1.removeAll( list2 );
        //  Return the number of clashes between the two courses.
        return ( size1 - list1.size() );
    }
    
   
    /*
     *  This functions calls all the functions which give penalty to 
     * 
     */
    public int cPenalty()
    {
          
          // To test the slot for each course
          // System.out.println(this.c1.slot);
          // System.out.println(this.c2.slot);
                
        int penalty = 0, weight = 0;
        if(this.c1.slot == this.c2.slot )
            weight = 20;
        
        else if( (this.c2.slot - this.c1.slot == 1) && (this.c1.slot%4 == 0 || this.c1.slot%4 == 2))
            weight = 5;
        
        else if( this.c2.slot - this.c1.slot == 2 && (this.c1.slot%4 == 0 || this.c1.slot%4 == 2))
            weight = 3;
        
        else if( this.c2.slot - this.c1.slot == 2 && this.c1.slot%4 == 1)
            weight = 2;
         
        else if( this.c2.slot - this.c1.slot == 3 && this.c1.slot%4 == 0)
            weight = 1;
        
         //  To test whether the weight is calculated correctly or not.
         //  System.out.print(weight);
        int clash = stdClash();
         //  To test whether the stdClash() is calculated correctly or not.
        penalty = weight*clash;
        return penalty;
    }
}
