package TPBank;

import java.util.*;

public class Ebank {

    public ResourceBundle message;

    void setLocale(Locale locale) {
        message = ResourceBundle.getBundle("TPbank.lang", locale);
    }

    void Menu() {
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");
        System.out.print("Select language: ");
    }
    
    int inputOption(){
        Scanner sc = new Scanner(System.in);
        int choice=0;
        int min=1;
        int max=3;
        String input=null;
        boolean check = false;
        do{
        try {
            input=sc.nextLine();
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
        }while(check==false);
        return choice;
    }
    
    void Login(){
        Scanner sc = new Scanner(System.in);
        String accountNumber=null;
        String password=null;
        String captcha=null;
        String generatedCaptcha=null;
        do{
            System.out.print(message.getString("account")+": ");
            accountNumber=sc.nextLine();
        }while(!checkAccountNumber(accountNumber));
        do{
            System.out.print(message.getString("password")+": ");
            password=sc.nextLine();
        }while(!checkPassword(password));
        generatedCaptcha=genCaptcha(5);
        System.out.println(message.getString("captcha")+": "+generatedCaptcha);
        do{
        System.out.print(message.getString("inputCaptcha")+": ");
        captcha=sc.nextLine();
        }while(!checkCaptcha(generatedCaptcha, captcha));
        if(accountNumber.equals("0123456789")&&password.equals("123456ab")&&captcha.equals(generatedCaptcha)){
            System.out.println("Login successfully");
        }
        else{
            System.out.println("Login fail");
        }
    }
    
    String genCaptcha(int length){
        Random random = new Random();
        String captcha = "";
        String lowerCase="qwertyuiopasdfghjklzxcvbnm";
        String upperCase="QWERTYUIOPASDFGHJKLZXCVBNM";
        String number="1234567890";
        String allChar=lowerCase+upperCase+number;
        captcha+=lowerCase.charAt(random.nextInt(26));
        captcha+=upperCase.charAt(random.nextInt(26));
        captcha+=number.charAt(random.nextInt(10));
        for(int i=0;i<length-3;i++){
            captcha+=allChar.charAt(random.nextInt(62));
        }
        char captchaArr[] = captcha.toCharArray();
        for(int i=0;i<captchaArr.length;i++){
            int j=random.nextInt(captchaArr.length);
            char temp=captchaArr[j];
            captchaArr[j]=captchaArr[i];
            captchaArr[i]=temp;
        }
        return new String(captchaArr);
    }
    
    boolean checkAccountNumber(String accountNumber) {
        if (accountNumber.matches("^[0-9]{10}$")) {
            return true;
        } else {
            System.out.println(message.getString("wrongAccount"));
            return false;
        }
    }

    boolean checkPassword(String password) {
        if (password.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,31}$")) {
            return true;
        } else {
            System.out.println(message.getString("wrongPassword"));
            return false;
        }
    }

    boolean checkCaptcha(String captcha, String input) {
        //if(captcha.contains(input)){
        if (captcha.matches(input)) {
            return true;
        } else {
            System.out.println(message.getString("wrongCaptcha"));
            return false;
        }
    }
}
