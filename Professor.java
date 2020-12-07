import java.io.Serializable;
import java.util.*;
import java.io.*;

public class Professor implements Serializable
{
	
    private String name, password;
    private ArrayList<Course> profCourses = new ArrayList<Course>();
    private final int maxProfCourses = 2;
	
	
    public Professor(String name, String password) 
    {
		this.name = name;
		this.password = password;
	}
	
    void printCoursesTeaching() 
    {
        System.out.println("\nProf " + name + " is teaching:\n");
        for(int i = 0; i < getNumCoursesTeaching(); i++)
            System.out.println(profCourses.get(i) + "\n");
    }
    
    void registerProfToCourse(Course course, Professor prof)
    {
        if(getNumCoursesTeaching() < maxProfCourses)
        {
            profCourses.add(course);
            course.addProf(prof);
        }
        else   
        {
            System.out.println("[" + prof.name + "] is already teaching a full semester.");
            return;
        }
        
    }

    public String getProfName()
    {
        return name;
    }

    public int getNumCoursesTeaching()
    {
        return profCourses.size();
    }
}