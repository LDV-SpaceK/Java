
public class Main {

    public static void main(String[] args) {
        int choice = 0;int baseIn=0;int baseOut=0;String inputValue=null;
        while (choice != 5) {
            //Step 1: Menu
            ChangeBase.menu();
            //Step 2: Input choice
            choice = ChangeBase.inputChoice();
            //Step 3: Function base on choice
            switch (choice) {
                case 1:
                    baseIn=ChangeBase.baseInput();
                    break;
                case 2:
                    baseOut=ChangeBase.baseOutput();
                    break;
                case 3:
                    inputValue=ChangeBase.inputValue(baseIn);
                    break;
                case 4:
                    ChangeBase.displayOutputValue(baseIn,baseOut,inputValue);
                    break;
                case 5:
                    break;
            }
        }
    }

}
