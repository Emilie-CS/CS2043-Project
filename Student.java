import java.util.*;
import java.io.*;
/*
Origonally our class diagram had us using a seperate array for each of
our studentCourses. But this made adding/droping studentCourses complicated (what if we
droped course 2, course 3,4 would be left hanging, let alone optional studentCourses)

So I think a possible solution could be an arraylist of size 6, to hold our corses
it handles deletions already pretty well.
*/

public class Student implements Serializable
{
   private String name, password;
   private ArrayList<Course> studentCourses;
   
   // constructor
    public Student(String name, String password)
    {
        this.name = name;
        this.password = password;
        studentCourses = new ArrayList<Course>(6);
    }

    void registerCourse(Course course, Student student, Semester semester)
    {
        if(student.studentCourses.size() < 6)
        {
            // if the cutoff date is after, or on the current date, then attempt registration
            if((semester.getRegistrationCutoff().compareTo(semester.getCurrentDate()) > 0) || 
                semester.getRegistrationCutoff().compareTo(semester.getCurrentDate()) == 0)
            {
                // if course is full, add to queue
                if(course.getStudentCount() == 10)
                {
                    course.queue.add(student);
                    System.out.println("[" + name + "] added to the waitlist for " + course.getCourseID());
                }
                // else register to course
                else if(course.getStudentCount() < 10)
                {
                    studentCourses.add(course);
                    course.registerStudent(student);
                }
            }
            else
            System.out.println("Sorry, the registration deadline has passed!\n");
        }
        else
            System.out.println("Sorry, the course load is full");
    }

    void dropCourse(Course course, Student student)
    {
        studentCourses.remove(course);
        course.removeStudent(student);
    }
//"--- Course " + i + " ---\n" + catalog.get(i).toString() + "\n");
    void printCourseList()
    {
        System.out.println("[" + name + "] is registered in [" + getNumCourses() + "] courses:");
        for(int i=0; i< getNumCourses(); i++)
        {
            System.out.println("--- Course " + i + " ---\n" + studentCourses.get(i));
        }
    }
    
    String getCourseList()
    {
        String sum = "";
        
        for(int i=0; i< getNumCourses(); i++)
           sum = sum.concat(studentCourses.get(i).toString());  
           
        return sum; 
    }

   
    double getTuitionAmount()
    {
        double sum = 0;
        for(int i=0; i< getNumCourses(); i++)
            sum += studentCourses.get(i).returnTuition();
        return sum;
    }

    void printTuition()
    {
        System.out.println("\n\n Tuition for " + name + ": " + getTuitionAmount());
    }

    int getNumCourses()
    {
        return studentCourses.size();
    }

    String getName()
    {
        return name;
    }
    
    String getPassword()
    {
      return password;
    }

    // toString added by Nathan
    public String toString()
    {
        return "[" + name + "] ";
    } 	   
}