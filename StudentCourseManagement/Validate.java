package studentmanagement;
import java.util.*;
/**
 *
 * @author XIII
 */
public class Validate {
    static boolean inputLimit(int min, int max, String input) {
        boolean check = false;
        int choice;
        try {
            choice = Integer.parseInt(input);
            if (choice < min || choice > max) {
                System.out.printf("Must in range %d to %d\n", min, max);
                check = false;
            } else {
                check = true;
            }
        } catch (Exception e) {
            System.out.println("Must be number");
            check = false;
        }
        return check;
    }
    
    static boolean isNumberic(String input){
        boolean check=false;
        try{
            Integer.parseInt(input);
            check=true;
        }catch(Exception e){
            System.out.println("Not Integer");
            check=false;
        }
        return check;
    }
    
    static boolean checkIdwithOneName(ArrayList<Student> stdList, String id, String name) {
    for (Student std : stdList) {
        // Kiểm tra nếu có sinh viên sử dụng cùng một ID với tên khác
        if (std.getId().equals(id) && !std.getStudentName().equals(name)) {
            System.out.println("This ID has been used by another person.");
            return false;
        }
    }
    return true;
}

    
    static boolean checkOnlyOneCourseNameperSemester(ArrayList<Student> stdList,String id,String course,int semester){
        boolean check=false;
        int count=1;
        for(Student std : stdList){
            if(std.getId().equals(id)){
                if(std.getSemester()==semester&&std.getCourseName().equals(course)){
                    count++;
                }
            }
        }
        if(count<=1){
            check=true;
        }
        else{
            System.out.println("One person can not learn 1 course twice per semester");
            check=false;
        }
        return check;
    }
    
    static boolean isEmpty(ArrayList<Student> stdList){
        int count=0;
        for(Student std : stdList){
            count++;
        }
        if(count==0){
            System.out.println("List is empty");
            return true;
        }
        else{
            return false;
        }
    }
}
