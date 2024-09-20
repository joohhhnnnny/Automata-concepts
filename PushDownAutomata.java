package machineLearning;

import java.util.Stack;
import java.util.Scanner;

public class PushDownAutomata {
    
    private Stack<Character> stack;

    public PushDownAutomata() {
        stack = new Stack<>();
    }

    public String transition(String state, char symbol) throws InterruptedException {
        switch (state) {
            case "q0":
                if (symbol == 'a') {
                    stack.push('X'); // Push 'X' to the stack
                    System.out.println("Transition: " + state + " -- 'a' --> " + state + " (push 'X' to stack)");
                    Thread.sleep(2000);
                    return "q0";
                } else if (symbol == 'b' && !stack.isEmpty()) {
                    stack.pop(); // Pop from stack
                    System.out.println("Transition: " + state + " -- 'b' --> q1 (pop from stack)");
                    Thread.sleep(2000);
                    return "q1";
                } else {
                    System.out.println("Transition: " + state + " -- " + symbol + " --> reject (invalid symbol or empty stack)");
                    Thread.sleep(2000);
                    return "reject";
                }
                
            case "q1":
                if (symbol == 'b' && !stack.isEmpty()) {
                    stack.pop(); // Continue popping for 'b's
                    System.out.println("Transition: " + state + " -- 'b' --> " + state + " (pop from stack)");
                    Thread.sleep(2000);
                    return "q1";
                } else if (stack.isEmpty() && symbol == '\0') { // epsilon transition
                    System.out.println("Transition: " + state + " -- epsilon --> accept (stack is empty)");
                    Thread.sleep(2000);
                    return "accept";
                } else {
                    System.out.println("Transition: " + state + " -- " + symbol + " --> reject (invalid symbol or stack not empty)");
                    Thread.sleep(2000);
                    return "reject";
                }
                
            default:
                return "reject";
        }
    }

    public String run(String inputString) throws InterruptedException {
        String state = "q0";
        
        for (char symbol : inputString.toCharArray()) {
            state = transition(state, symbol);
            if (state.equals("reject")) {
                // Continue processing even if rejected
            }
        }
        
        // After consuming all input symbols, check for empty stack
        String finalState = transition(state, '\0'); // epsilon transition
        return finalState.equals("accept") ? "Accepted" : "Rejected";
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        
        do {
            System.out.println(" ");
            System.out.println("(1) Perform PushDownAutomata");
            System.out.println("(2) Exit the Program ");
            System.out.print("Your input: ");
            int choice = scanner.nextInt();
            
            if (choice == 2) {
                System.out.println(" ");
                System.out.println("Terminating the program..");
                Thread.sleep(2000);
                System.out.println("The program has been terminated.");
                break;
            }
            
            switch(choice) {
                case 1:
                    System.out.print("Enter the number of N (max number of 'a's and 'b's): ");
                    int N = scanner.nextInt();
                    System.out.print("Enter a string of 'a's and 'b's: ");
                    String inputString = scanner.next();
                    
                    // Check if the string contains only 'a's and 'b's
                    if (!inputString.matches("[ab]*")) {
                        System.out.println("Error: Input can only contain 'a's and 'b's. Input rejected.");
                        break;
                    }

                    // Check if the counts of 'a' and 'b' are within limits
                    if (inputString.length() > 2 * N) {
                        System.out.println("Error: The length of the input string exceeds the maximum limit (2 * N). Input rejected.");
                        break;
                    }
                    
                    PushDownAutomata pda = new PushDownAutomata();
                    System.out.println("\nProcessing transitions for input: " + inputString + "\n");
                    String result = pda.run(inputString);
                    System.out.println("\nFinal Result: " + result);
                    break;
                    
                default:
                    break;
            }
        } while(true);
    }
}
