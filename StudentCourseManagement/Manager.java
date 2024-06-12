package studentmanagement;

import java.util.*;

/**
 *
 * @author XIII
 */
public class Manager {

    static void menu() {
        System.out.println("===== WELCOME TO STUDENT MANAGEMENT =====");
        System.out.println("1. Create");
        System.out.println("2. Find and Sort");
        System.out.println("3. Update/Delete");
        System.out.println("4. Report");
        System.out.println("5. Exit");
    }

    static int inputChoice() {
        Scanner sc = new Scanner(System.in);
        String userInput = null;
        do {
            System.out.print("Enter Choice: ");
            userInput = sc.nextLine();
        } while (!Validate.inputLimit(1, 5, userInput));
        int choice = Integer.parseInt(userInput);
        return choice;
    }

    static void createStudent(ArrayList<Student> stdList) {
        Scanner sc = new Scanner(System.in);
        int quantity = 0;
        boolean check = false;
        String userInput = null;
        String id = null;
        String name = null;
        int semester = 0;
        String course = null;
        //loop to re-input if user input is invalid
        do {
            do {
                System.out.print("Enter number of student you want to create(at least 10): ");
                userInput = sc.nextLine();
            } while (!Validate.isNumberic(userInput));
            quantity = Integer.parseInt(userInput);
            //check quantity
            if (quantity < 2) {
                System.out.println("Must be > 10");
                check = false;
            } else {
                check = true;
            }
        } while (check == false);
        check = false;
        for (int i = 0; i < quantity; i++) {
            //loop to re-input if user input is invalid
            do {
                System.out.print("Enter id: ");
                id = sc.nextLine();
                System.out.print("Enter name: ");
                name = sc.nextLine();
            } while (!Validate.checkIdwithOneName(stdList, id, name));
            //loop to re-input if user input is invalid
            do {
                do {
                    System.out.print("Enter semester: ");
                    userInput = sc.nextLine();
                } while (!Validate.isNumberic(userInput));
                semester = Integer.parseInt(userInput);
                //check if semester is valid
                if (semester < 1) {
                    System.out.println("Must be > 0");
                    check = false;
                } else {
                    check = true;
                }
            } while (check == false);
            //loop to re-input if user input is invalid
            do {
                System.out.print("Enter course: ");
                course = sc.nextLine();
            } while (!Validate.checkOnlyOneCourseNameperSemester(stdList, id, course, semester));
            Student student = new Student(id, name, semester, course);
            stdList.add(student);
        }
    }

    static void findandsort(ArrayList<Student> stdList) {
        Scanner sc = new Scanner(System.in);
        String name;
        if (!Validate.isEmpty(stdList)) {
            System.out.print("Enter student name: ");
            name = sc.nextLine();
            for (Student std : stdList) {
                //if match name
                if (std.getStudentName().equals(name)) {
                    System.out.println(std);
                } //Not found case
                else {
                    System.out.println("Not found");
                    break;
                }
            }
        }
    }

    static void updateOrDeleteStudent(ArrayList<Student> stdList) {
        if (!stdList.isEmpty()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("===== UPDATE OR DELETE STUDENT RECORD =====");
            System.out.println("1. Update a student record");
            System.out.println("2. Delete all records of a student");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    updateStudent(stdList);
                    break;
                case 2:
                    deleteStudent(stdList);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("List empty");
        }
    }

    static void updateStudent(ArrayList<Student> stdList) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID of the student(s) to update: ");
        String id = sc.nextLine();
        boolean found = false;

        ArrayList<Student> matchingStudents = new ArrayList<>();
        for (Student std : stdList) {
            if (std.getId().equals(id)) {
                matchingStudents.add(std);
            }
        }

        if (matchingStudents.isEmpty()) {
            System.out.println("No students found with ID " + id);
            return;
        }

        System.out.println("Select student(s) to update:");
        System.out.println("0. Update all matching students");
        for (int i = 0; i < matchingStudents.size(); i++) {
            System.out.println((i + 1) + ". " + matchingStudents.get(i));
        }

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (choice == 0) {
            System.out.println("Enter new name for all matching students: ");
            String newName = sc.nextLine();
            for (Student studentToUpdate : matchingStudents) {
                studentToUpdate.setStudentName(newName);
            }
            System.out.println("All matching students' information updated successfully.");
        } else if (choice >= 1 && choice <= matchingStudents.size()) {
            Student studentToUpdate = matchingStudents.get(choice - 1);
            System.out.println("Student found. Please update the information.");
            System.out.print("Enter new name: ");
            String newName = sc.nextLine();
            studentToUpdate.setStudentName(newName);
            System.out.println("Student information updated successfully.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    static void deleteStudent(ArrayList<Student> stdList) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID of the student to delete: ");
        String id = sc.nextLine();
        boolean removed = false;

        Iterator<Student> iterator = stdList.iterator();
        while (iterator.hasNext()) {
            Student std = iterator.next();
            if (std.getId().equals(id)) {
                iterator.remove();
                removed = true;
            }
        }

        if (removed) {
            System.out.println("All students with ID " + id + " deleted successfully.");
        } else {
            System.out.println("No students with ID " + id + " found.");
        }
    }

static void report(ArrayList<Student> stdList) {
    // Duyệt qua danh sách sinh viên
    for (int i = 0; i < stdList.size(); i++) {
        Student std = stdList.get(i);
        String studentName = std.getStudentName();
        String courseName = std.getCourseName();
        
        // Kiểm tra xem cặp sinh viên và khóa học đã được in ra chưa
        boolean printed = false;
        
        // Duyệt lại danh sách sinh viên từ đầu đến sinh viên hiện tại để kiểm tra
        for (int j = 0; j < i; j++) {
            Student prevStd = stdList.get(j);
            if (prevStd.getStudentName().equals(studentName) && prevStd.getCourseName().equals(courseName)) {
                // Nếu cặp sinh viên và khóa học đã được in ra trước đó, đặt printed thành true
                printed = true;
                break;
            }
        }
        
        // Nếu cặp sinh viên và khóa học chưa được in ra, in ra và đặt printed thành true
        if (!printed) {
            int courseCount = 0;
            // Đếm số lượng khóa học của sinh viên hiện tại
            for (Student student : stdList) {
                if (student.getStudentName().equals(studentName) && student.getCourseName().equals(courseName)) {
                    courseCount++;
                }
            }
            // In báo cáo cho sinh viên với số lượng khóa học đã đăng ký
            System.out.printf("%-10s|%-10s|%-10d\n",studentName,courseName,courseCount);
        }
    }
}




    


}
