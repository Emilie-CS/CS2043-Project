
// MANAGES THE PERSISTENT TEXT FILES


import java.io.*;
import java.util.*;

public class InitializeData {
    public static void main(String[] args)
    {
        System.out.println("\nWould you like to write new files? (y/n)");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        scan.close();
        if (input.compareTo("y") == 0)
        {
        // delete all files first
            try  
            {         
                File f= new File("Students.txt"); 
                f.delete();
            }  
            catch(Exception e) { e.printStackTrace(); }
            // delete professors.txt
            try  
            {         
                File f= new File("Professors.txt"); 
                f.delete();  
            }  
            catch(Exception e) { e.printStackTrace(); }
            // delete courses.txt
            try  
            {         
                File f= new File("Courses.txt"); 
                f.delete();
            }  
            catch(Exception e) { e.printStackTrace(); }
            //delete semesters.txt
            try  
            {         
                File f= new File("Semesters.txt"); 
                f.delete(); 
            }  
            catch(Exception e) { e.printStackTrace(); }

        // now write new files
            // creating and writing semesters to Semesters.txt
            Calendar winter2021RegCutoff = Calendar.getInstance();
                winter2021RegCutoff.set(Calendar.YEAR, 2021);
                winter2021RegCutoff.set(Calendar.MONTH, 1);
                winter2021RegCutoff.set(Calendar.DAY_OF_MONTH, 1);
            
            Calendar winter2021DropCutoff = Calendar.getInstance();
                winter2021DropCutoff.set(Calendar.YEAR, 2021);
                winter2021DropCutoff.set(Calendar.MONTH, 1);
                winter2021DropCutoff.set(Calendar.DAY_OF_MONTH, 5);

            Semester winter2021 = new Semester("winter2021", winter2021RegCutoff, winter2021DropCutoff);
            ArrayList<Semester> writeSemester = new ArrayList<Semester>();
            writeSemester.add(winter2021);

            try
            {
                File semTxt = new File("Semesters.txt");
                FileOutputStream f = new FileOutputStream(semTxt);
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(writeSemester);
                System.out.println(semTxt.getName() + " written");
                o.close();
                f.close();
            } 
            catch (FileNotFoundException e) 
            {
                System.out.println("File not found");
            } 
            catch (IOException e) 
            {
                System.out.println("IOException");
                e.printStackTrace();
            }

            // creating and writing courses to Courses.txt        
            String[] prereq = {"CS1073", "CS1083"};
            Course CS2043 = new Course("CS2043", "SJ01", "MWF 12:30-1:30", "HH105", "winter2021", 700.5, 3, prereq);
            Course AB2000 = new Course("AB2000", "SJ02A", "MTF 4:30-8:20", "IH202", "winter2021", 800.24, 5, prereq);
            Course CC1111 = new Course("CC1111", "SJ111A", "MTT 3:30-9:30", "HH222", "winter2021", 8888.22, 10, prereq);
            Course XY0001 = new Course("XY0001", "FR22A", "WTF 9:00-11:00", "OH101", "fall2021", 1000.5, 2, prereq);
            ArrayList<Course> writeCourses = new ArrayList<Course>();
            writeCourses.add(CS2043);
            writeCourses.add(AB2000);
            writeCourses.add(CC1111);
            writeCourses.add(XY0001);
            
            try
            {
                File courseTxt = new File("Courses.txt");
                FileOutputStream f = new FileOutputStream(courseTxt);
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(writeCourses);
                System.out.println(courseTxt.getName() + " written");
                o.close();
                f.close();
            } 
            catch (FileNotFoundException e) 
            {
                System.out.println("File not found");
            } 
            catch (IOException e) 
            {
                System.out.println("IOException");
                e.printStackTrace();
            }
            
            // creating and writing students to Students.txt
            Student joe = new Student("Joe Smith", "12345");
            Student moe = new Student("Moe Snow", "12345");
            Student fred = new Student("Fred Foo", "54321");
            Student ronan = new Student("Ronan Boyd", "15935");
            Student nathan = new Student("Nathan Young", "12546");
            Student emilie = new Student("Emilie Ouellette", "14785");
            Student josh = new Student("Joshua Hickman", "45879");
            Student syed = new Student("Syed Saeed", "12046");
            Student adia = new Student("Adia Ouellette", "88888");
            Student kami = new Student("Kami Ouellette", "64566");
            Student jasmine = new Student("Jasmine Caron", "47885");
            Student momo = new Student("Mojo Jojo", "74441");
            Student steve = new Student("Steve Stevo", "44552");
            ArrayList<Student> writeStudents = new ArrayList<Student>();
            writeStudents.add(joe);
            writeStudents.add(moe);
            writeStudents.add(fred);
            writeStudents.add(ronan);
            writeStudents.add(nathan);
            writeStudents.add(emilie);
            writeStudents.add(josh);
            writeStudents.add(syed);
            writeStudents.add(adia);
            writeStudents.add(kami);
            writeStudents.add(jasmine);
            writeStudents.add(momo);
            writeStudents.add(steve);
            
            try
            {
                File studTxt = new File("Students.txt");
                FileOutputStream f = new FileOutputStream(studTxt);
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(writeStudents);
                System.out.println(studTxt.getName() + " written");
                o.close();
                f.close();
            } 
            catch (FileNotFoundException e) 
            {
                System.out.println("File not found");
            } 
            catch (IOException e) 
            {
                System.out.println("IOException");
                e.printStackTrace();
            }

            // creating and writing professors to Professors.txt
            Professor Smith = new Professor("Dr. Smoth Smith", "12345");
            Professor Albert = new Professor("Albert Shmablbert", "0000");
            Professor Wendy = new Professor("Wendy Windy", "999");
            ArrayList<Professor> writeProfessors = new ArrayList<Professor>();
            writeProfessors.add(Smith);
            writeProfessors.add(Albert);
            writeProfessors.add(Wendy);

            try
            {
                File profTxt = new File("Professors.txt");
                FileOutputStream f = new FileOutputStream(profTxt);
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(writeProfessors);
                System.out.println(profTxt.getName() + " written");
                o.close();
                f.close();
            } 
            catch (FileNotFoundException e) 
            {
                System.out.println("File not found");
            } 
            catch (IOException e) 
            {
                System.out.println("IOException");
                e.printStackTrace();
            }
        }
    }

        
}
