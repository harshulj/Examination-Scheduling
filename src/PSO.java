package ExamScheduling;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*
 *
 * @author harshul 
 */
public class PSO {

    private Particle globalBest ;
    private Particle[]  particles;
    private int slot = 8 ;
    private int courseNum ;
    private int turnLimit = 1000 ;
    private int clashLimit = 50 ;
    /*
     *  This the constructorof the PSO.
     *  In the main only the object of PSO is created and then initialize function is called.
     *  Initialize handels all the other functions od PSO.
     */
    
    public PSO(ArrayList courses)
    {
        this.courseNum = courses.size();
        this.particles = new Particle[20] ;
        int i=0;
        for(i=0;i<20;i++)
        {
            TimeTable t = new TimeTable(courses) ;
            {
                this.particles[i] = new Particle(t);
//                System.out.println(this.particles[i].t.courses[0].slot+" " +this.particles[i].t.courses[1].slot+" " +this.particles[i].t.courses[2].slot+" " +this.particles[i].t.courses[3].slot);
            }
        }
        
//        System.out.println("here ends the initialization.");
        
        this.globalBest = this.particles[5];
        System.out.println("global fit : "+this.globalBest.getFitValue());
        
        for(i=0; i < this.turnLimit && this.clashLimit <= this.particles[i%20].getFitValue() ; i++)
        {
//            System.out.println("Slots : "+this.globalBest.t.courses[0].slot+this.globalBest.t.courses[1].slot+this.globalBest.t.courses[2].slot+this.globalBest.t.courses[3].slot);

            int y = i%20 ;
            System.out.println(i);            
            this.particles[y].setLocalBest();

//            System.out.println("global fit : "+this.globalBest.getFitValue()+" current fit"+this.particles[i%20].getFitValue()+" "+this.turnLimit);

            this.setGlobalBest(y);
//            this.sort();
            this.mutate(y);
            this.crossOver(y, true);
            this.crossOver(y, false);
            
//            System.out.println("global fit : "+this.globalBest.getFitValue()+" iteration "+i+" "+this.turnLimit);
            
        }     
//        System.out.println("global fit : "+this.globalBest.getFitValue());
        
        
    }

    /*
     *  This function sorts the candidates in ascending order according to their clashes.
     *  The candidate with minimum clashes is the best solution. 
     */
    public void sort()
    {
        Arrays.sort(particles);
    }
    
    /*
     *  This function mutates the instance of the TimeTable
     */
    public void mutate(int i)
    {
        /*
         *  PSEUDOCODE
         *      For each TimeTable instance slots must be changed
         *  
         */
            int rs, rc ;
            Random randomSlot = new Random();
            rs = randomSlot.nextInt(this.slot)+1 ;
//            System.out.print(rs);
            Random randomCourse = new Random();
            rc = randomCourse.nextInt(this.courseNum);
//            System.out.print(rc);
//            System.out.print("Course slot "+this.particles[i].t.courses[rc].slot);
            this.particles[i].t.courses[rc].slot = rs ;
//            System.out.print("Course slot "+this.particles[i].t.courses[rc].slot);
        
            
    }
    
    /*
     *  This function copies an exam slot from the local best to the new TimeTable
     *  Local best is the best of each of the 20 candidate TimeTable instances.
     */
    public void crossOver(int i , boolean isLocal )
    {
        /*
         *  PSEUDOCODE
         *      (if isLocal == true use localbest otherwise use globalbest)
         *      Randomly select a course from localbest or globalbest
         *      Copy the slot for the same course in TimeTable t
         *      Return the new TimeTable
         */
        if(isLocal == true )
        {
            int rc ;
            Random randomCourse = new Random();
            rc = randomCourse.nextInt(this.courseNum);
//            System.out.print(rc);
//            System.out.print("Course slot "+this.particles[i].t.courses[rc].slot);
           this.particles[i].t.courses[rc].slot = this.particles[i].getLocalBest().courses[rc].slot ;
//            System.out.print("Course slot "+this.particles[i].t.courses[rc].slot);
        }
        
        if(isLocal == false )
        {
            int rc ;
            Random randomCourse = new Random();
            rc = randomCourse.nextInt(this.courseNum);
//            System.out.print(rc);
//            System.out.print("Course slot "+this.particles[i].t.courses[rc].slot+"\n");
            this.particles[i].t.courses[rc].slot = this.globalBest.t.courses[rc].slot ;
//            System.out.print("Course slot "+this.particles[i].t.courses[rc].slot);
        }

    }
    
    private void setGlobalBest(int i)
    {
//        System.out.println("Setting global"+this.globalBest.getFitValue());
        if(this.particles[i].getFitValue() < this.globalBest.getFitValue())
        {
            this.globalBest = this.particles[i] ;
//            System.out.println("Set global"+this.globalBest.getFitValue());
        }
            
    }
    
    public Particle getGlobalBest()
    {
        return this.globalBest;
    }
}
