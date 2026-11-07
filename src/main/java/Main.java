import java.util.Scanner;

public class Main {
    
    public static void main(String args[]) throws Exception {

        Scanner scnr = new Scanner(System.in);
        ChatGptService gpt = new ChatGptService();
        
        System.out.printf("Send Message: ");
        String message = scnr.nextLine();

        System.out.println("ChatGPT: " + gpt.sendPrompt(message) );

    }

}
