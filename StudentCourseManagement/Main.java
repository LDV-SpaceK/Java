package studentmanagement;
import java.util.*;
/**
 *
 * @author XIII
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Student> stdList = new ArrayList<>();
        stdList.add(new Student("123","abc", 4, "Java"));
        stdList.add(new Student("123","abc", 3, "Java"));
        stdList.add(new Student("123","abc", 3, ".Net"));
        stdList.add(new Student("123","abc", 2, "Java"));
        stdList.add(new Student("123","abc", 3, "C"));
        stdList.add(new Student("234","def", 4, "Java"));
        stdList.add(new Student("345","rty", 4, "Java"));
        int choice = 0;
        while (choice != 5) {
            //Step 1: Menu
            Manager.menu();
            //Step 2: Input choice
            choice = Manager.inputChoice();
            //Step 3: Function base on choice
            switch (choice) {
                case 1:Manager.createStudent(stdList);
                    break;
                case 2:Manager.findandsort(stdList);
                    break;
                case 3:Manager.updateOrDeleteStudent(stdList);
                    break;
                case 4:Manager.report(stdList);
                    break;
                case 5:
                    break;
            }
        }
    }
    
}
