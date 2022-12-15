import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Notebook {
    private int id;
    private String name;
    private String producer;
    private int memory;
    private int disk;
    private int monitor;
    private String os;
    private String colour;

    public Notebook(int id, String name, String producer, int memory, int disk, int monitor, String os, String colour) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.memory = memory;
        this.disk = disk;
        this.monitor = monitor;
        this.os = os;
        this.colour = colour;
    }

    public String getProducer() {
        return producer;
    }

    public String getOS() {
        return os;
    }    

    public String getColour() {
        return colour;
    }    

    public String getStrAtr(String menuChoice) {
        if (menuChoice.equals("2")) return producer;
        else if (menuChoice.equals("6")) return os;
        else if (menuChoice.equals("7")) return colour;
        else return "";
    }    

    public int getIntAtr(String menuChoice) {
        if (menuChoice.equals("3")) return memory;
        else if (menuChoice.equals("4")) return disk;
        else if (menuChoice.equals("5")) return monitor;
        else return 0;
    }       

    @Override
    public String toString() {
        return name + ":" +
                " производитель " + producer +
                ", память " + memory + " Гб" +
                ", диск " + disk + " Гб" +
                ", диагональ " + monitor + "'" +
                ", " + os +
                ", цвет " + colour;
    }

    public static ArrayList<Notebook>  loadFromFile(String filename) {

        ArrayList<Notebook> notebookList = new ArrayList<>(); 

        File file = new File("notebooks.txt");
        try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8)) {
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] attr = line.split(";");
            
                Notebook nb = new Notebook( Integer.parseInt(attr[0]), attr[1], attr[2], Integer.parseInt(attr[3]), 
                                            Integer.parseInt(attr[4]), Integer.parseInt(attr[5]), attr[6], attr[7]);
                notebookList.add(nb);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return notebookList;       
    }

    public static void getAllNotebook(ArrayList<Notebook> notebookList) {
        System.out.println();
        System.out.println("Все модели ноутбуков:");
        for (Notebook nb: notebookList) {
            System.out.println(nb);
        }
        System.out.println();
    }

    public static void getNotebookByStrAtr(ArrayList<Notebook> notebookList, String menuChoice, String filter, String title) {
        System.out.println();
        System.out.printf("Ноутбуки %s %s:", title, filter);
        System.out.println();
        boolean find = false;

        for (Notebook nb: notebookList) {
            if (nb.getStrAtr(menuChoice).toLowerCase().contains(filter.toLowerCase())) {
                System.out.println(nb);
                find = true;
            }
        }
        if (!find) System.out.println("Не найдено");
        System.out.println();
    }      
    
    public static void getNotebookByIntAtr(ArrayList<Notebook> notebookList, String menuChoice, int minChoice, int maxChoice, String title) {
        System.out.println();
        System.out.printf("Ноутбуки %s от %d до %d:", title, minChoice, maxChoice);
        System.out.println();
        boolean find = false;

        int intAtr;
        for (Notebook nb: notebookList) {
            intAtr = nb.getIntAtr(menuChoice); 
            if (intAtr >= minChoice && intAtr <= maxChoice) {
                System.out.println(nb);
                find = true;
            }
        }
        if (!find) System.out.println("Не найдено");
        System.out.println();
    }         

}
