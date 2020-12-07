import java.util.*;
import java.time.*;
import java.io.*;
import java.text.*;

public class Semester implements Serializable
{
    private String termInfo;
    private int year;

    Calendar currentTime, registrationCutoff, dropCourseCutoff, endOfSemester;


    public Semester(String termInfo, Calendar registrationCutoff, Calendar dropCourseCutoff)
    {
        this.termInfo = termInfo;
        this.registrationCutoff = registrationCutoff;
        this.dropCourseCutoff = dropCourseCutoff;
    }

    public Calendar getCurrentDate()
    {
        currentTime = Calendar.getInstance();
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //System.out.println(formatter.format(currentTime.getTime()));
        return currentTime;
    }

    public Calendar getRegistrationCutoff()
    {
        return registrationCutoff;
    }

    public ArrayList<Course> getCourseCatalog(Semester semester)
    {
        try
        {
            // reading the course objects from Courses.txt
            FileInputStream f = new FileInputStream("Courses.txt");
            ObjectInputStream o = new ObjectInputStream(f);
            ArrayList<Course> courseCatalog = (ArrayList<Course>)o.readObject();
            o.close();
            f.close();
            // make sure that all courses are valid for the current semester
            for(int i = 0; i < courseCatalog.size(); i++)
            {
                // if the year of i is not the same year as the current termInfo, 
                // or,
                // if the termInfo of i is not the same termInfo as the current termInfo;
                // remove i from the catalog.
                if((courseCatalog.get(i).getTermInfo().compareTo(semester.getTermInfo()) != 0))
                    {
                        courseCatalog.remove(i);
                    }
            }
            return courseCatalog;
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong. Printing stack trace:");
            e.printStackTrace();
        }
        return null;
    }

    public void printCatalog(ArrayList<Course> catalog)
    {
        System.out.println("\n\n\t______________ Course Catalog ______________\n");
        for(int i = 0; i < catalog.size(); i++)
            System.out.println("--- Course " + i + " ---\n" + catalog.get(i).toString() + "\n");
    }
    
    public String getCatalog(ArrayList<Course> catalog)
    {
        String sum = "";
        
        for(int i=0; i< catalog.size(); i++)
           sum = sum.concat(catalog.get(i).toString());  
           
        return sum; 
    }

    public int getYear(Semester semester)
    {
        return year;
    }
    
    public String getTermInfo()
    {
        return termInfo;
    }
}
