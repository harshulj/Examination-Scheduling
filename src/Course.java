package ExamScheduling;
import java.util.ArrayList;
import java.io.*;

/*
 * @author harshul 
 */
// This class object stores the information of different courses 

public class Course
{
        public ArrayList students ; 
        public int slot ;
        public String cid = new String() ;
	/*
	 *	Course constructor takes the following parameters
	 *	@id	:	course id which is also the name of the file which
	 *			stores enrollment ids of the students enrolled in this 
	 *			course.
	 */

        public Course(String id,int s)
	{
            this.cid = id;
            this.slot = s ;
            this.students = getStudents();
//            System.out.println("Welcome to course: "+id+". Examination slot: "+s);
	}
        
        
        private ArrayList getStudents()
        {
            ArrayList  std;
            std = readFile();
          //  ArrayList  std = {"Harshul","Jain"};
            return std ;
        }
        
        public ArrayList readFile() 
        {
           /*
            *   TO TEST THE AVAILABILITY OF FILE IN THE GIVEN DIRECTORY
             * 
            // /home/harshul/NetBeansProjects/ExamScheduling/build/classes/ExamScheduling/
            File dir = new File(".");
                String[] children = dir.list();
                if (children == null) {
                        System.out.println("nothing found");// Either dir does not exist or is not a directory
                } 
                else {
                    for (int i=0; i<children.length; i++) {
                        // Get filename of file or directory
                        String filename = children[i];
                        System.out.println(filename);
                    }
                    
                }
            * 
            */
            //  String to read each line from the file
            String line = "" ;
            //  Arraylist to keep the students for the course
            ArrayList data = new ArrayList();
            try 
            {
                //  FileReader object to read file content.
                FileReader fr = new FileReader(cid);
                BufferedReader br = new BufferedReader(fr);//Can also use a Scanner to read the file
               // System.out.println(file);
                while((line = br.readLine()) != null) 
                {
                    data.add(line);
                }
            }
            //  FileNotFoundException
            catch(FileNotFoundException fN){
                
                fN.printStackTrace();
            }
            //  IO exception
            catch(IOException e) 
            {
                System.out.println(e);
            }
        return data;
    }   
}
