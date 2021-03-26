// Used instead of System.out... & System.in...

import java.util.Scanner;

public class UI {
    
    private static Scanner input = new Scanner(System.in); 

    public static void say(String text) {
        System.out.print(text);
    }
    
    public static String ask(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }
} 