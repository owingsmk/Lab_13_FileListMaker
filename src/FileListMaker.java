import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileListMaker {

    static ArrayList<String> list = new ArrayList<>(); // @param args the command line arguments */
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        final String menu = "A- add, S - save, O - open, D- delete, C - clear, V-view, Q - Quit";
        boolean done = false; String cmd = "";


        do { displayList();
            cmd = SafeInput.getRegExString(console, menu, "[AaSsOoDdCcVvQq]");
            cmd = cmd.toUpperCase();
            switch(cmd) {
                case "A": Add();
                    break; case "S": Save();
                    break; case "D": Delete();
                    break; case "C": Clear();
                    break; case "V": displayList();
                    break; case "Q": Quit();
                    break; case "O": Open();
                    break; } System.out.println("cmd is " +cmd);
        } while(!done);
    }
    private static void Quit() {
        Scanner sc = new Scanner(System.in); String message = "Are you sure?";
        boolean quit = SafeInput.getYNConfirm(sc,message);
        if(quit) { System.exit(1);
        }
        return;
    }
    private static void Delete() {
        Scanner sc = new Scanner(System.in);
        String message = "Enter item number to remove";
        if(list.isEmpty()) {
            System.out.println("List is Empty");
            return;
        }
        int index = SafeInput.getRangedInt(sc, message,1, list.size());
        list.remove(index-1);
    }
    private static void Clear() {
        Scanner sc = new Scanner(System.in);
        String removedStr = list.remove(1);
        System.out.println(list);
        System.out.println(removedStr);
        if(list.isEmpty()) {
            System.out.println("List is Empty");
            return;
        }
    }
    private static void Save(){
        OutputStream out = new BufferedOutputStream(Files.newOutputStream(CREATE));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
    }
    private static void Add() {
        Scanner sc = new Scanner(System.in);
        String item = SafeInput.getNonZeroLenString(sc, "Enter item to add");
        list.add(item);
    }
    private static void displayList() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        if(list.size() !=0) { for(int i =0; i < list.size(); i++) {
            System.out.printf("%3d%35s", i+1, list.get(i) ); System.out.println();
        }
        } else
            System.out.println("+++ List is empty +++");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    private static void Open(){

    }
    }
}