package TPBank;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ebank ebank = new Ebank();
        // Display menu
        ebank.Menu();
        //input
        int choice = ebank.inputOption();
        //func base input
        switch (choice) {
            case 1:
                ebank.setLocale(new Locale("vn")); // Set locale to Vietnamese
                break;
            case 2:
                ebank.setLocale(new Locale("en")); // Set locale to English
                break;
            case 3:
                break;
        }
        ebank.Login();
    }
}
