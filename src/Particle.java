package ExamScheduling;
import java.util.ArrayList;
/**
 *
 * @author harshul 
 */
public class Particle implements Comparable{
    public TimeTable t ;
    private TimeTable localBest;
    private int fitValue;
    
    public Particle(TimeTable tt)
    {
        this.t = tt ;
        this.fitValue = t.fitValue();
    }
    
    @Override
    public int compareTo(Object o) 
    {
        Particle p = (Particle) o;
        if(this.fitValue < p.fitValue)
            return 1 ;
        return -1 ;
    }
    
    public TimeTable getLocalBest()
    {
        return this.localBest ;
    }
    
    public void setLocalBest()
    {
        if(this.localBest == null)
        {
//            System.out.println("first localbest");
            this.localBest = this.t ;
            //System.out.println(this.fitValue +""+this.localBest.fitValue());
        }
           
        else
        {
            if(this.fitValue < this.localBest.fitValue())
                this.localBest = this.t ;
        }
    }
    public int getFitValue()
    {
        return this.fitValue ;
    }
}
