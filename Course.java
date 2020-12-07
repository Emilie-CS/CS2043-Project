import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import java.io.Serializable;

public class Course implements Serializable 
{
	private String courseID, section, schedule, roomNumber, termInfo;
    private int profID, creditHours;
	private double tuitionCost;
	
    private String[] prerequisites;
    private Professor thisProfessor = null;
    private ArrayList<Student> registeredStudents;
    Queue<Student> queue = new LinkedList<Student>();		
	 private boolean isCancelled;						//program cancellation method within semester class
	
	 private final int minStudents = 3;
    private final int maxStudents = 10;
    

	
    public Course(String courseID, String section, String schedule, 
                  String roomNumber, String termInfo, double tuitionCost, 
                  int creditHours, String[] prerequisites) 
	{	
		this.courseID = courseID;
		this.section = section;
		this.schedule = schedule;
		this.roomNumber = roomNumber;
      this.termInfo = termInfo;
		this.tuitionCost = tuitionCost;
		this.creditHours = creditHours;
      this.prerequisites = prerequisites;
		registeredStudents = new ArrayList<Student>(10);
	}
    
    public void registerStudent(Student student)
    {
        if(getStudentCount() < maxStudents)
            registeredStudents.add(student);
    }

    public void removeStudent(Student student)
    {
        registeredStudents.remove(student);
        if(getStudentCount() < 10 && !queue.isEmpty())
        // pop queue to the free space in the course
        {
            registeredStudents.add(queue.remove());
            Student waitlistStudent = queue.remove();
            registeredStudents.add(waitlistStudent);
            System.out.println(waitlistStudent.getName() +" added to course " + courseID +
            " from waitlist");
        }
    }
    
    
    public void addProf(Professor prof)
    {
        thisProfessor = prof;
    }

    public void printCourseInfo(Course course)
    {
        System.out.println("\n\n\t--- " + courseID + " info ---\n");
        if(thisProfessor == null)
            System.out.println("The professor is to be determined");
        else
            System.out.println("\n The professor is: " + thisProfessor.getProfName());
        if(getStudentCount() > 0)
        {
            System.out.println("\nRegistered students for " + course.getCourseID() + ":\n");
            for(int i = 0; i < getStudentCount(); i++)
                System.out.println("Student " + i + "  -> " + registeredStudents.get(i) + "\n");
        }
        else
            System.out.println("No students in " + courseID);
    }

    public double returnTuition()
    {
        return tuitionCost;
    }

    public int getStudentCount()
    {
        return registeredStudents.size();
    }

    public String getCourseID()
    {
        return courseID;
    }

    public String getTermInfo()
    {
        return termInfo;
    }

	public String toString() 
	{	
		return "\tCourse ID  ->   " + courseID + "\n\tSection    ->   " + section + "\n\tSchedule   ->   " + schedule + "\n\tTerm Info  ->   " + termInfo + "\n\n";
	}
	
}
