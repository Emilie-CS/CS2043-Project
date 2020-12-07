import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.TilePane;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Register_Front extends Application
{
	Button startupStudentBtn, startupProfBtn, startupAdminBtn,            //startup page buttons                                     
          studentLoginBtn, studentRegBtn, studentLoginBackBtn,           //student login buttons
          stuRegBtn, stuViewCoursesBtn, showTuition, stuHomeBackBtn,     //student home buttons 
          stuRegBackBtn, stuRegRegBtn;                                   //register for course scene buttons
         
          
   TextField studentIDField, studentPWField;
   Label studentIDLabel, studentPWLabel, startupLabel;
   GridPane stuLoginGrid;
   
   int studentNumber;
   String password;
   
   public void start(Stage primaryStage)
   {	 
     ArrayList<Professor> allProfessors = getEveryProfessor();
     ArrayList<Semester> semesterList = getSemesterList();
     ArrayList<Course> courseCatalog = semesterList.get(0).getCourseCatalog(semesterList.get(0));
     ArrayList<Student> allStudents = getEveryStudent();
     allStudents.get(0).registerCourse(courseCatalog.get(0), allStudents.get(0), semesterList.get(0));
     
     createButtons();

     studentIDLabel = new Label("    Your Student ID number:");
     studentPWLabel = new Label("    Your password:");
     startupLabel = new Label();
     studentIDField = new TextField(); 
     studentPWField = new TextField();
     stuLoginGrid = new GridPane();
     
     stuLoginGrid.addRow(0, studentIDLabel, studentIDField);
     stuLoginGrid.addRow(1, studentPWLabel, studentPWField);
      
     //tuition popup button
     ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
     Dialog<String> dialog = new Dialog<String>();
     
     showTuition.setOnAction(e -> 
     {
         dialog.setContentText("Tuition for " + allStudents.get(studentNumber).getName() + ": $" + allStudents.get(studentNumber).getTuitionAmount());
         dialog.getDialogPane().getButtonTypes().add(type);
         dialog.showAndWait();
     });
     
     //course list popup button
     Dialog<String> dialog2 = new Dialog<String>();
     
     stuViewCoursesBtn.setOnAction(e -> 
     {
          dialog2.setContentText("Registered courses for " + allStudents.get(studentNumber).getName() + ": \n" + allStudents.get(studentNumber).getCourseList());
          dialog2.getDialogPane().getButtonTypes().add(type);          
          dialog2.showAndWait();
     });
      
     //set scenes/panes
     FlowPane startupPane = new FlowPane(startupStudentBtn, startupProfBtn, startupAdminBtn, startupLabel);     
     FlowPane studentLoginPane = new FlowPane(studentLoginBtn, studentRegBtn, studentLoginBackBtn, stuLoginGrid);
     FlowPane studentHomePane = new FlowPane(stuRegBtn, stuViewCoursesBtn, showTuition, stuHomeBackBtn);
     TilePane stuRegPane = new TilePane();
          
     Scene startupScene = new Scene(startupPane, 700, 500); 
     Scene studentLoginScene = new Scene(studentLoginPane, 700, 500); 
     Scene studentHomeScene = new Scene(studentHomePane, 700, 500);
     Scene regCourseScene = new Scene(stuRegPane, 700, 500);
     
     //switch to register courses scene button
     ArrayList<RadioButton> buttonArray = new ArrayList<RadioButton>(courseCatalog.size());
     ToggleGroup group = new ToggleGroup();
         
     for(int i = 0; i < courseCatalog.size(); i++)
     {
         buttonArray.add(new RadioButton(courseCatalog.get(i).toString()));
         stuRegPane.getChildren().add(buttonArray.get(i));
         buttonArray.get(i).setToggleGroup(group);
     }  

     stuRegBtn.setOnAction(e -> primaryStage.setScene(regCourseScene));   
     stuRegPane.getChildren().add(stuRegRegBtn);             
     stuRegPane.getChildren().add(stuRegBackBtn);
     
     //register for courses button functionality
     stuRegRegBtn.setOnAction(e ->
     {
         for(int i = 0; i < buttonArray.size(); i++)
         {
            if(buttonArray.get(i).isSelected())
            {
               allStudents.get(studentNumber).registerCourse(courseCatalog.get(i), allStudents.get(studentNumber), semesterList.get(0));
               System.out.println("line 111");
            }
         }
     });

     //startup screen button functionality
     startupStudentBtn.setOnAction(e -> primaryStage.setScene(studentLoginScene));
     startupProfBtn.setOnAction(e -> startupLabel.setText("  Professor tools coming soon™"));
     startupAdminBtn.setOnAction(e -> startupLabel.setText("  Administrator tools coming soon™"));
             
     //student login screen button functionality        
     studentLoginBackBtn.setOnAction(e -> primaryStage.setScene(startupScene));
     
     //register for course back button functionality
     stuRegBackBtn.setOnAction(e -> primaryStage.setScene(studentHomeScene));
     
     //process text fields
     studentIDField.setOnAction(e -> studentNumber = Integer.parseInt(studentIDField.getText()));
     studentPWField.setOnAction(e -> password = studentPWField.getText());
     
     studentLoginBtn.setOnAction(e -> {
         if(studentNumber<allStudents.size() && studentNumber>=0)
         {
            System.out.println("Valid id");
            String targetPassword = allStudents.get(studentNumber).getPassword();  
                   
            if(password.equals(targetPassword))   
            {
               System.out.println("Valid password");
               primaryStage.setScene(studentHomeScene);
            }
            else
            {
               System.out.println("inValid password");
            }
         }
         else
         {
            System.out.println("inValid id");
         }
     });
     
     //student home buttons  
     stuHomeBackBtn.setOnAction(e -> 
     {
         primaryStage.setScene(startupScene);
         startupLabel.setText("");
     });    
                  
     primaryStage.setTitle("Registration Management System");
     primaryStage.setScene(startupScene);
     primaryStage.show();
   }  
   
   
   public void createButtons()
   {
     Font mainFont = new Font("courrier", 15);  
     
     //student login buttons
     studentLoginBackBtn = new Button("Back");
     studentLoginBackBtn.setFont(mainFont);
     studentLoginBackBtn.setMinWidth(100);
     
     studentLoginBtn = new Button("Login");	
	  studentLoginBtn.setFont(mainFont);
     studentLoginBtn.setMinWidth(100); 
     
     studentRegBtn = new Button("Register");	
	  studentRegBtn.setFont(mainFont);
     studentRegBtn.setMinWidth(100); 

   
     //startup screen buttons
     startupStudentBtn = new Button("Student");	
	  startupStudentBtn.setFont(mainFont);
     startupStudentBtn.setMinWidth(100);
     
     startupProfBtn = new Button("Professor");	
	  startupProfBtn.setFont(mainFont);
     startupProfBtn.setMinWidth(100);
     
     startupAdminBtn = new Button("Administrator");	
	  startupAdminBtn.setFont(mainFont);
     startupAdminBtn.setMinWidth(100);
     
     //student home buttons  
     stuViewCoursesBtn = new Button("View registered courses");
     stuViewCoursesBtn.setFont(mainFont);
     stuViewCoursesBtn.setMinWidth(100);
     
     stuRegBtn = new Button("Register for Courses");
     stuRegBtn.setFont(mainFont);
     stuRegBtn.setMinWidth(100);
     
     //tuition button style
     showTuition = new Button("Display tuition balance");
     showTuition.setFont(mainFont);
     showTuition.setMinWidth(100);

     //student home back button
     stuHomeBackBtn = new Button("Sign out");
     stuHomeBackBtn.setFont(mainFont);
     stuHomeBackBtn.setMinWidth(100);
     
     //student register for course scene back button
     stuRegBackBtn = new Button("Back");
     stuRegBackBtn.setFont(mainFont);
     stuRegBackBtn.setMinWidth(100);
     
     //student register for course button
     stuRegRegBtn = new Button("Register for selected");
     stuRegRegBtn.setFont(mainFont);
     stuRegRegBtn.setMinWidth(100);
   } 

   //main
   public static void main(String[] args)
   {
      launch(args);
   }
   
   //(former) driver methods
    static ArrayList<Student> getEveryStudent()
    {
        try
        {
            // reading the student objects from Students.txt
            FileInputStream f = new FileInputStream("Students.txt");
            ObjectInputStream o = new ObjectInputStream(f);
            ArrayList<Student> studentList = (ArrayList<Student>)o.readObject();
            o.close();
            f.close();
            return studentList;
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong. Printing stack trace:");
            e.printStackTrace();
        }
        return null;
    }

    static ArrayList<Semester> getSemesterList()
    {
        try
        {
            // reading the semester objects from Semesters.txt
            FileInputStream f = new FileInputStream("Semesters.txt");
            ObjectInputStream o = new ObjectInputStream(f);
            ArrayList<Semester> semesterList = (ArrayList<Semester>)o.readObject();
            o.close();
            f.close();
            return semesterList;
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong. Printing stack trace:");
            e.printStackTrace();
        }
        return null;
    }

    static ArrayList<Professor> getEveryProfessor()
    {
        try
        {
            // reading the professor objects from Professors.txt
            FileInputStream f = new FileInputStream("Professors.txt");
            ObjectInputStream o = new ObjectInputStream(f);
            ArrayList<Professor> profList = (ArrayList<Professor>)o.readObject();
            o.close();
            f.close();
            return profList;
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong. Printing stack trace:");
            e.printStackTrace();
        }
        return null;
    }

    static void writeFiles(ArrayList<Student> allStudents, ArrayList<Course> allCourses, ArrayList<Semester> allSemesters, ArrayList<Professor> allProfessors)
    {
        System.out.println("\nWould you like to overwrite files with latest data? (y/n)");
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
            try
            {
                File semTxt = new File("Semesters.txt");
                FileOutputStream f = new FileOutputStream(semTxt);
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(allSemesters);
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
            try
            {
                File courseTxt = new File("Courses.txt");
                FileOutputStream f = new FileOutputStream(courseTxt);
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(allCourses);
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
            try
            {
                File studTxt = new File("Students.txt");
                FileOutputStream f = new FileOutputStream(studTxt);
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(allStudents);
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
            try
            {
                File profTxt = new File("Professors.txt");
                FileOutputStream f = new FileOutputStream(profTxt);
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(allProfessors);
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
        else if(input.compareTo("n") == 0)
        {
            System.out.println("Changes discarded. Exiting...");
        }
        
    }
 
}
   
