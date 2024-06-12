
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

    static boolean inputValid(String input, int baseIn) {
        boolean check = false;
        if (baseIn == 1) {
            if (input.matches("^[0-1]+$")) {
                check = true;
            }
            else{
                System.out.println("Not Binary");
            }
        } else if (baseIn == 2) {
            if (input.matches("\\d+")) {
                check = true;
            }
            else{
                System.out.println("Not Decimal");
            }
        } else if (baseIn == 3) {
            if (input.matches("^[0-9A-F]+$")) {
                check = true;
            }
            else{
                System.out.println("Not Hexadecimal");
            }
        } else {
            check = false;
        }
        return check;
    }

}
