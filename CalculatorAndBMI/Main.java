
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Menu: 
        Menu();
        //Choose base on option
        OptionChoosen();
    }

    public static void Menu() {
        System.out.println("1. Normal Calculator");
        System.out.println("2. BMI Calculator");
        System.out.println("3. Exit");
        System.out.print("Your option: ");
    }

    public static void OptionChoosen() {
        int choice = 0;
        String input = null;
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        do {
            do {
                try {
                    input = sc.nextLine();
                    choice = Integer.parseInt(input);
                    if (choice <= 0 || choice > 3) {
                        System.out.println("Choice must be integer 1->3");
                        check = false;
                    } else {
                        check = true;
                    }
                } catch (Exception e) {
                    System.out.println("Choice must be integer");
                    check = false;
                }
            } while (check == false);
            switch (choice) {
                case 1:
                    NormalCalculator();
                    break;
                case 2:
                    BMICalculator();
                    break;
                case 3:
                    break;
            }
            if (choice > 0 && choice < 3) {
                System.out.println("");
                Menu();
            }
        } while (choice != 3);
    }

    public static void NormalCalculator() {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String operator = null;
        String input;
        float memo = 0;
        float num1 = 0;
        boolean check = false;
        //Input first number
        do {
            //check numeric type
            try {
                System.out.print("Number: ");
                input = sc1.nextLine();
                memo = Float.parseFloat(input);
                check = true;
            } catch (Exception e) {
                System.out.println("Input must be numberic type");
                check = false;
            }
        } while (check == false);
        //calculate
        do {
            //check operator type
            do {
                System.out.print("Operator: ");
                operator = sc2.nextLine();
                check = "+".equals(operator)
                        || "-".equals(operator)
                        || "*".equals(operator)
                        || "/".equals(operator)
                        || "^".equals(operator)
                        || "=".equals(operator);
                if (check == false) {
                    System.out.println("Operator must be +,-,*,/,^");
                }
            } while (check == false);
            //end task with operator "="
            if ("=".equals(operator)) {
                break;
            }
            //check numberic type 
            do {
                try {
                    System.out.print("Number: ");
                    input = sc1.nextLine();
                    num1 = Float.parseFloat(input);
                    check = true;
                } catch (Exception e) {
                    System.out.println("Input must be numberic type");
                    check = false;
                }
            } while (check == false);
            //calculation base on case of operator
            switch (operator) {
                case "+":
                    memo += num1;
                    System.out.println("Memory: " + memo);
                    break;
                case "-":
                    memo -= num1;
                    System.out.println("Memory: " + memo);
                    break;
                case "*":
                    if (num1 == 0) {
                        memo = 0;
                        System.out.println("Memory: " + memo);
                        break;
                    } else {
                        memo *= num1;
                        System.out.println("Memory: " + memo);
                        break;
                    }
                case "^":
                    memo = (float) Math.pow(memo, num1);
                    System.out.println("Memory: " + memo);
                    break;
                case "/":
                    //check denominator=0
                    if (num1 == 0) {
                        System.out.println("Denominator must not be 0");
                        break;
                    } else {
                        memo /= num1;
                        System.out.println("Memory: " + memo);
                        break;
                    }
            }
        } while (!"=".equals(operator));
        //display last memory
        System.out.println("Answer: " + memo);
    }

    public static void BMICalculator() {
        Scanner sc=new Scanner(System.in);
        String input=null;
        float weight=0;
        float height=0;
        boolean check=false;
        //check input weight
        do{
            try{
            System.out.print("Weight(kg): ");
            input=sc.nextLine();
            weight=Float.parseFloat(input);
            if(weight<=0){ 
                System.out.println("Weight must be > 0");
                check=false;
            }
            else check=true;
            }catch(Exception e){
                System.out.println("Weight must be numberic");
                check=false;
            }
        }while(check==false);
        //check input height
        do{
            try{
            System.out.print("Height(cm): ");
            input=sc.nextLine();
            height=Float.parseFloat(input);
            if(height<=0){ 
                System.out.println("Height must be > 0");
                check=false;
            }
            else check=true;
            }catch(Exception e){
                System.out.println("Height must be numberic");
                check=false;
            }
        }while(check==false);
        //parse from centimeter to meter
        height/=100;
        float BMI=weight/((float)Math.pow(height, height));
        System.out.printf("BMI Score: %.2f\n",BMI);
        System.out.print("Status: ");
        if(BMI<20){System.out.println("Thieu can");}
        if(BMI>=20&&BMI<30){System.out.println("Can bang");}
        if(BMI>=30){System.out.println("Beo phi");}
    }
}