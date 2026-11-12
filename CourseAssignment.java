import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CourseAssignment{
    public static void main (String[]args) throws IOException{
        File AllCourses = new File ("courseList.csv");
        Scanner input = new Scanner (AllCourses);
        ArrayList<String> Classes= new ArrayList<String>();

        while (input.hasNextLine()){
            Classes.add(input.nextLine());
        }
        input.close();
        Classes.remove(0);

        int credits=0;
        while(credits<20){
            HashSet<String> Required = new HashSet<>(); 
            input = new Scanner(System.in); 

            HashSet<String> checked = new HashSet<>();
            for (int i = 0; i < Classes.size(); i++) {
                

                Course Current = new Course(Classes.get(i));
                String CurrentCompare = Current.getTitle();
                
                // doesn't allow it to be geted to user multiple times
                if (Required.contains(CurrentCompare)) {
                    continue; 
                }
                if (checked.contains(CurrentCompare)) {
                    continue; 
                }

                IO.println("Is " + CurrentCompare + " a required course? (yes/no)");
                String choice = input.nextLine();
                checked.add(CurrentCompare); 

                if (choice.equalsIgnoreCase("yes")) {
                    Required.add(CurrentCompare);
                    credits += Current.getCredit();
                }

                IO.println("\nCurrent total credits: " + credits+"\n");
                
                
            }

            ArrayList<String> RequiredCourses = new ArrayList<>();
            for(int i=0; i<Classes.size(); i++){
                Course Current = new Course(Classes.get(i));
                String CurrentCompare = Current.getTitle();
                if(Required.contains(CurrentCompare)){
                    RequiredCourses.add(Classes.get(i));
                }
            }

            //banned courses: removes required courses from the checked list
            for(int i=0; i<Required.size(); i++){
                List<String> Avaliable = new ArrayList<>(Required);
                checked.remove(Avaliable.get(i));
            }
           
            for(int i= checked.size()-1; i >=0; i--) {
                List<String> Avaliable = new ArrayList<>(checked);

                IO.println("would you like to ban "+ Avaliable.get(i) +" (yes/no)? (type in done when finished)");
                String bannedCourse = input.nextLine();
                if (bannedCourse.equalsIgnoreCase("done")) {
                    break;
                }

                //removed banned courses from list of all avaliable classes
                if (bannedCourse.equalsIgnoreCase("yes")) {
                    for(int j=Classes.size()-1; j>=0; j--){
                        Course Current = new Course(Classes.get(j));
                        String CurrentCompare = Current.getTitle();
                        if (CurrentCompare.equals(Avaliable.get(i))) {
                            Classes.remove(j);
                            checked.remove(CurrentCompare);
                            break;
                        }
                    }

                }

                for(String course : checked) {
                    IO.println(course);
                }

            }

            //contain classses user prefers. but it only contains the titles
            ArrayList<String> Prefer = new ArrayList<>();
            for(int i= 0; i < checked.size(); i++) {
                List<String> Avaliable = new ArrayList<>(checked);
                IO.println("Do you prefer " + Avaliable.get(i) + "? (yes/no)");

                String preferCourse = input.nextLine();
                if (preferCourse.equalsIgnoreCase("yes")){
                    Prefer.add(Avaliable.get(i));
                }
            }
            

            IO.println();
            ArrayList<String> PreferedCourses = new ArrayList<>();
            for(int i=0; i<Classes.size(); i++){
                Course Current = new Course(Classes.get(i));
                if(Prefer.contains(Current.getTitle())){
                    PreferedCourses.add(Classes.get(i));
                }
            }

            List<String> Monday= new ArrayList<>();
            List<String> Tuesday= new ArrayList<>();
            List<String> Wednesday= new ArrayList<>();
            List<String> Thursday= new ArrayList<>();
            List<String> Friday= new ArrayList<>();

            ArrayList<String> TotalCourses= new ArrayList<>();
            int variable=0;
            while(variable<PreferedCourses.size()||variable<RequiredCourses.size()){
                if(variable<PreferedCourses.size()){
                    TotalCourses.add(PreferedCourses.get(variable));
                }
                if(variable<RequiredCourses.size()){
                    TotalCourses.add(RequiredCourses.get(variable));
                }
                variable++;
            }
            for(int i=0; i<TotalCourses.size(); i++){
                Course Current = new Course(TotalCourses.get(i));
                HashSet<Character> Days = new HashSet<>();
                for(int j=0; j<Current.getDays().length(); j++){

                    if(Current.getDays().charAt(j) == 'M'){
                        Monday.add(TotalCourses.get(i));
                    }
                    if(Current.getDays().charAt(j) == 'T'){
                        Tuesday.add(TotalCourses.get(i));
                    }
                    if(Current.getDays().charAt(j) == 'W'){
                        Wednesday.add(TotalCourses.get(i));
                    }
                    if(Current.getDays().charAt(j) == 'R'){
                        Thursday.add(TotalCourses.get(i));
                    }
                    if(Current.getDays().charAt(j) == 'F'){
                        Friday.add(TotalCourses.get(i));
                    }
                }
            }

            //Monday Schedule

                IO.println();
                IO.println("The courses avaliable to you on Monday are:\n--------------------------------\n");
                HashSet<String> UniqueMondayTime = new HashSet<>();
                for(int i=0; i<Monday.size(); i++){
                    Course CurrentClass = new Course(Monday.get(i));
                    
                    if(!(UniqueMondayTime.contains(CurrentClass.getTime()))){
                        UniqueMondayTime.add(CurrentClass.getTime());
                        IO.println(CurrentClass.getTitle() + " at " + CurrentClass.getTime()+ " tought by " + CurrentClass.getProfessor()+ " || ID: " + CurrentClass.getID());
                    }
                }

                Monday.clear();
                IO.println();

                IO.println("Which courses do you select for monday(Enter ID): ");
                String MondayInput = input.nextLine();
                String[] MondayInputArray = MondayInput.split(" ");
                for(String McourseId : MondayInputArray) {
                    for(int i=0; i<TotalCourses.size(); i++){
                        Course CurrentClass = new Course(TotalCourses.get(i));
                        if(CurrentClass.getID() == Short.parseShort(McourseId)){
                            Monday.add(TotalCourses.get(i));
                            break;
                        }
                    }
                }

                //Tuesday Schedule

                IO.println();
                IO.println("The courses avaliable to you on Tuesday are:\n--------------------------------\n");
                HashSet<String> UniqueTuesdayTime = new HashSet<>();
                for(int i=0; i<Tuesday.size(); i++){
                    Course CurrentClass = new Course(Tuesday.get(i));
                    
                    if(!(UniqueTuesdayTime.contains(CurrentClass.getTime()))){
                        UniqueTuesdayTime.add(CurrentClass.getTime());
                        IO.println(CurrentClass.getTitle() + " at " + CurrentClass.getTime()+ " tought by " + CurrentClass.getProfessor()+ " || ID: " + CurrentClass.getID());
                    }
                }

                Tuesday.clear();
                IO.println();

                IO.println("Which courses do you select for tuesday(Enter ID): ");
                String TuesdayInput = input.nextLine();
                String[] TuesdayInputArray = TuesdayInput.split(" ");
                for(String TcourseId : TuesdayInputArray) {
                    for(int i=0; i<TotalCourses.size(); i++){
                        Course CurrentClass = new Course(TotalCourses.get(i));
                        if(CurrentClass.getID() == Short.parseShort(TcourseId)){
                            Tuesday.add(TotalCourses.get(i));
                            break;
                        }
                    }
                }
                
                //Wednesday Schedule

                IO.println();
                IO.println("The courses avaliable to you on Wednesday are:\n--------------------------------\n");
                HashSet<String> UniqueWednesdayTime = new HashSet<>();
                for(int i=0; i<Wednesday.size(); i++){
                    Course CurrentClass = new Course(Wednesday.get(i));
                    
                    if(!(UniqueWednesdayTime.contains(CurrentClass.getTime()))){
                        UniqueWednesdayTime.add(CurrentClass.getTime());
                        IO.println(CurrentClass.getTitle() + " at " + CurrentClass.getTime()+ " tought by " + CurrentClass.getProfessor()+ " || ID: " + CurrentClass.getID());
                    }
                }

                Wednesday.clear();
                IO.println();

                IO.println("Which courses do you select for wednesday(Enter ID): ");
                String WednesdayInput = input.nextLine();
                String[] WednesdayInputArray = WednesdayInput.split(" ");
                for(String WcourseId : WednesdayInputArray) {
                    for(int i=0; i<TotalCourses.size(); i++){
                        Course CurrentClass = new Course(TotalCourses.get(i));
                        if(CurrentClass.getID() == Short.parseShort(WcourseId)){
                            Wednesday.add(TotalCourses.get(i));
                            break;
                        }
                    }
                }

                //Thursday Schedule

                IO.println();
                IO.println("The courses avaliable to you on Thursday are:\n--------------------------------\n");
                HashSet<String> UniqueThursdayTime = new HashSet<>();
                for(int i=0; i<Thursday.size(); i++){
                    Course CurrentClass = new Course(Thursday.get(i));
                    
                    if(!(UniqueThursdayTime.contains(CurrentClass.getTime()))){
                        UniqueThursdayTime.add(CurrentClass.getTime());
                        IO.println(CurrentClass.getTitle() + " at " + CurrentClass.getTime()+ " tought by " + CurrentClass.getProfessor()+ " || ID: " + CurrentClass.getID());
                    }
                }

                Thursday.clear();
                IO.println();

                IO.println("Which courses do you select for thursday(Enter ID): ");
                String ThursdayInput = input.nextLine();
                String[] ThursdayInputArray = ThursdayInput.split(" ");
                for(String TcourseId : ThursdayInputArray) {
                    for(int i=0; i<TotalCourses.size(); i++){
                        Course CurrentClass = new Course(TotalCourses.get(i));
                        if(CurrentClass.getID() == Short.parseShort(TcourseId)){
                            Thursday.add(TotalCourses.get(i));
                            break;
                        }
                    }
                }

                //Friday Schedule

                IO.println();
                IO.println("The courses avaliable to you on Friday are:\n--------------------------------\n");
                HashSet<String> UniqueFridayTime = new HashSet<>();
                for(int i=0; i<Friday.size(); i++){
                    Course CurrentClass = new Course(Friday.get(i));
                    
                    if(!(UniqueFridayTime.contains(CurrentClass.getTime()))){
                        UniqueFridayTime.add(CurrentClass.getTime());
                        IO.println(CurrentClass.getTitle() + " at " + CurrentClass.getTime()+ " tought by " + CurrentClass.getProfessor()+ " || ID: " + CurrentClass.getID());
                    }
                }

                Friday.clear();
                IO.println();

                IO.println("Which courses do you select for friday(Enter ID): ");
                String FridayInput = input.nextLine();
                String[] FridayInputArray = FridayInput.split(" ");
                for(String FcourseId : FridayInputArray) {
                    for(int i=0; i<TotalCourses.size(); i++){
                        Course CurrentClass = new Course(TotalCourses.get(i));
                        if(CurrentClass.getID() == Short.parseShort(FcourseId)){
                            Friday.add(TotalCourses.get(i));
                            break;
                        }
                    }
                }

                String FinalSchedule = "Your final schedule is:\n--------------------------------\n";

                IO.println();
                System.out.println("Monday: \n--------------------------------\n");
                for(int i=0; i<Monday.size(); i++){
                    Course CurrentClass = new Course(Monday.get(i));
                    IO.println(CurrentClass.getTitle() + " at " + CurrentClass.getTime()+ " tought by " + CurrentClass.getProfessor());
                }

                

            }
        }
    
}