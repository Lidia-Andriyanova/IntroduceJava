import java.util.ArrayList;
import java.util.Scanner;

public class programm{

    public static void menu() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("Критерии фильтрации ноутбуков:");
        System.out.println("\t1 - все модели");
        System.out.println("\t2 - по производителю");
        System.out.println("\t3 - по объему памяти");
        System.out.println("\t4 - по объему диска");
        System.out.println("\t5 - по длине диагонали");
        System.out.println("\t6 - по операционной системе");
        System.out.println("\t7 - по цвету");
        System.out.println("-----------------------------------------");
        System.out.println();
    }

    public static void strAtr(ArrayList<Notebook> notebookList, String menuChoice, Scanner scanner) {
        String s = "";
        if (menuChoice.equals("2")) s = "Введите производителя: ";
        else if (menuChoice.equals("6")) s = "Введите операционную систему: ";
        else if (menuChoice.equals("7")) s = "Введите цвет: ";
        System.out.print(s);

        String strFilter = scanner.nextLine();

        if (menuChoice.equals("2")) s = "производителя";
        else if (menuChoice.equals("6")) s = "с операционной системой";
        else if (menuChoice.equals("7")) s = "цвета";
        
        Notebook.getNotebookByStrAtr(notebookList, menuChoice, strFilter, s);
    }

    public static void intAtr(ArrayList<Notebook> notebookList, String menuChoice, Scanner scanner) {
        String s = "";
        System.out.print("Введите диапазон (минимальное и максимальное значение через пробел): ");

        String strFilter = scanner.nextLine();
        try {
            String[] minMax = strFilter.split(" ");
            int minChoice = Integer.parseInt(minMax[0]);
            int maxChoice = Integer.parseInt(minMax[1]);

            if (menuChoice.equals("3")) s = "с объемом памяти";
            else if (menuChoice.equals("4")) s = "с объемом диска";
            else if (menuChoice.equals("5")) s = "с длиной диагонали";    
            
            Notebook.getNotebookByIntAtr(notebookList, menuChoice, minChoice, maxChoice, s);

        } catch (Exception e) {
            System.out.println("Некорректный ввод");
        }
    }    

    public static void main(String[] args) {

        ArrayList<Notebook> notebookList = Notebook.loadFromFile("");

        Scanner scanner = new Scanner(System.in);
        String menuChoice, more;

        while (true) { 
            menu();

            System.out.print("Выберите критерий фильтрации: ");
            menuChoice = scanner.nextLine();
            if (menuChoice.equals("1")) Notebook.getAllNotebook(notebookList);
            else if (menuChoice.equals("2") || menuChoice.equals("6") || menuChoice.equals("7")) strAtr(notebookList, menuChoice, scanner);
            else if (menuChoice.equals("3") || menuChoice.equals("4") || menuChoice.equals("5")) intAtr(notebookList, menuChoice, scanner);
            else System.out.printf("Неправильный критерий\n\n");
            
            System.out.print("Продолжить? (1 - да, 0 - нет): ");
            more = scanner.nextLine();
            if (more.equals("0")) break;
        }
    }
}
