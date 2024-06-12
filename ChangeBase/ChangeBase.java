
import java.util.*;
import java.math.BigInteger;

public class ChangeBase {

    static void menu() {
        System.out.println("===== MENU =====");
        System.out.println("1. Base input");
        System.out.println("2. Base output");
        System.out.println("3. Input Value");
        System.out.println("4. Output Value");
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

    static int baseInput() {
        Scanner sc = new Scanner(System.in);
        String userInput = null;
        do {
            System.out.println("1-Binary  2-Decimal  3-Hexadecimal");
            System.out.print("Enter base input: ");
            userInput = sc.nextLine();
        } while (!Validate.inputLimit(1, 3, userInput));
        int input = Integer.parseInt(userInput);
        return input;
    }

    static int baseOutput() {
        Scanner sc = new Scanner(System.in);
        String userInput = null;
        do {
            System.out.println("1-Binary  2-Decimal  3-Hexadecimal");
            System.out.print("Enter base output: ");
            userInput = sc.nextLine();
        } while (!Validate.inputLimit(1, 3, userInput));
        int input = Integer.parseInt(userInput);
        return input;
    }

    static String inputValue(int baseIn) {
        Scanner sc = new Scanner(System.in);
        String userInput = null;
        do {
            userInput = sc.nextLine();
        } while (!Validate.inputValid(userInput, baseIn));
        return userInput;
    }

    static void displayOutputValue(int baseIn, int baseOut, String inputValue) {
        if (baseIn == 2) {
            System.out.println(fromDecimal(inputValue, baseOut));
        } else if (baseIn == 1 && baseOut == 2) {
            System.out.println(toDecimal(inputValue, baseIn));
        } else if (baseIn == 3 && baseOut == 2) {
            System.out.println(toDecimal(inputValue, baseIn));
        } 
        else if(baseIn==3&&baseOut==1){
            System.out.println(fromDecimal(toDecimal(inputValue, baseIn), baseOut));
        }
        else if(baseIn==1&&baseOut==3){
            System.out.println(fromDecimal(toDecimal(inputValue, baseIn), baseOut));
        }
        else if (baseIn == baseOut) {
            System.out.println(inputValue);
        }
    }

    static String fromDecimal(String input, int baseOut) {
        if (input == "0") {
            return "0";
        }
        String base = null;
        if (baseOut == 1) {
            base = "2";
        } else if (baseOut == 2) {
            return input;
        } else if (baseOut == 3) {
            base = "16";
        }
        BigInteger value = new BigInteger(input);
        StringBuilder result = new StringBuilder();
        while (value.compareTo(BigInteger.ZERO) > 0) {
            BigInteger remainder = value.mod(new BigInteger(base));
            if (remainder.intValue() < 10) {
                result.insert(0, remainder);
            } else {
                // Map remainder 10-15 to A-F for hexadecimal base
                char hexChar = (char) ('A' + remainder.intValue() - 10);
                result.insert(0, hexChar);
            }
            value = value.divide(new BigInteger(base));
        }
        return result.toString();
    }

    static String toDecimal(String input, int baseIn) {
        if (input == "0") {
            return "0";
        }
        int base = 0;
        switch (baseIn) {
            case 1:
                base = 2;
                break;
            case 2:
                return input;
            case 3:
                base = 16;
                break;
        }
        BigInteger value = new BigInteger("0");
        int exponent = 0;
        int digitValue=0;
        // Duyệt qua các chữ số từ phải sang trái
        for (int i = input.length() - 1; i >= 0; i--) {
            char character = input.charAt(i);
            if (character >= '0' && character <= '9') {
                digitValue = character - '0';
            } else if (character >= 'A' && character <= 'F') {
                digitValue = character - 'A' + 10;
            }
            // Inside the loop
            if (base == 16 && input.charAt(i) >= 'A' && input.charAt(i) <= 'F') {
                value = value.add(BigInteger.valueOf(digitValue).multiply(BigInteger.valueOf(base).pow(exponent)));
            } else {
                value = value.add(BigInteger.valueOf(digitValue).multiply(BigInteger.valueOf(base).pow(exponent)));
            }
            exponent++;
        }
        return value.toString();
    }
}
