package Personal;
import java.util.Scanner;

public class DFA {

	public static void main(String[] args) throws InterruptedException {
		
		Scanner sc = new Scanner(System.in);
		String input = "";
		do {
			System.out.print("Press [d] for DFA, press [n] for NDFA, and press [q] to quit: ");
			
			if (sc.hasNextLine()) {
				input = sc.nextLine().trim();
			} else {
				System.out.println("No input found.. Terminating..");
				break;
			}
			
		switch (input) {
		case "d":
			
			System.out.println("=================================");
			System.out.println("Deterministic Finite Automata");
			System.out.println("=================================");
			
			System.out.println("L = {Set of All Strings that starts with 0}");
			System.out.print("Please Enter a Combinations of ZERO and ONE: ");
			String input1 = sc.nextLine().trim();
			
			try {
				
				if (input1.isEmpty()) {
					System.out.println("Input is Empty. Not accepted.");
					return;
				}
				
				char firstChar = input1.charAt(0);
				if (firstChar != '0') {
					System.out.println(" ");
					System.out.println("Starting Program...");
					Thread.sleep(3000);
					System.out.println("We are currently at State A.");
					Thread.sleep(2000);
					System.out.println("Transitioning to another state...");
					Thread.sleep(2000);
					System.out.println("We are now in a Dead State!");
					Thread.sleep(3000);
					System.out.println("Final State: Not Accepted");
		            break;
				}
				
				System.out.println(" ");
				System.out.println("Starting Program...");
				Thread.sleep(3000);
				
				boolean isAccepted = true;
				System.out.println("We are currently at State A.");
				Thread.sleep(2000);
				System.out.println("Transitioning to another state...");
				Thread.sleep(2000);
				System.out.println("We are now at State B.");
				
				for (char ch : input1.substring(1).toCharArray()) {
					Thread.sleep(2000);
					System.out.println("Checking the next Character...");
				
					if (ch != '0' && ch != '1') {
	                    System.out.println("Invalid Character Detected. Only '0' and '1' are allowed.");
	                    isAccepted = false;
	                    break;
	                }
					
					Thread.sleep(3000);
					System.out.println("We're still in State B.");	
				}
				
				System.out.println("Final State: Accepted!");
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		break;
		
		case "n":
			System.out.println("=================================");
			System.out.println("Non-Deterministic Finite Automata");
			System.out.println("=================================");
			
			System.out.println("L = {Set of All Strings that ends with 11}");
			System.out.print("Please Enter a Combinations of ZERO and ONE: ");
			String input2 = sc.nextLine().trim();
			
			System.out.println("We are currently at State A..");
			
			try {
				if (input2.isEmpty()) {
					System.out.println("Input is Empty. Not accepted.");
					return;
					}
				Thread.sleep(2000);
				
				if (isAccepted(input2)) {
					System.out.println("We are now at state C.");
					Thread.sleep(2000);
					System.out.println("Input is accepted.");
					Thread.sleep(5000);
				} else {
					System.out.println("Input is not accepted.");
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				}
			
		break;
		
		case "q":
			System.out.println("Terminating the program...");
			Thread.sleep(3000);
			System.out.println("The program has been terminated :(");
		break;
		
		default: System.out.println("Invalid Input, Please Try again.");
		break;
		}
		
		
		} while (!input.equals("q"));
		
		sc.close();
	}
	
	private static boolean isAccepted(String input) throws InterruptedException {
		
		int length = input.length();
		
		int state = 0;
		
		for (int i = 0; i < length; i++) {
			char ch = input.charAt(i);
			
			switch(state) {
			case 0:
				if (ch == '1') {
					state = 1;
					System.out.println("Transitioning to state B...");
					Thread.sleep(2000);
				} break;
			case 1:
				System.out.println("We are now at state B.");
				Thread.sleep(2000);
				
				if (ch == '1') {
					state = 2;
					System.out.println("Transitioning to state C...");
					Thread.sleep(2000);
				} else {
					state = 0;
					System.out.println("No Transition.");
					Thread.sleep(2000);
				} break;
			case 2:
				// state C
				break;
			}
		}
		
		
		return state == 2;
	}

}
