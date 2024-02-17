package edu.pasadena.cs.cs03b;

import java.util.Scanner;

public class GuessBirthday {
    static Scanner input = new Scanner(System.in); // Scanner for user input
    String answer = ""; // Stores the final guessed answer in binary form
    int num = 0; // Stores the final guessed answer in decimal form
    int choice = 0; // User's choice: 1 for guessing birthday, 2 for guessing a number
    int number = 0; // Array to store the numbers for guessing
    int[][] day; // 2D array to store sets of numbers for guessing
    
    public int[][] getDay() {
        return day;
    }
    public GuessBirthday() {
    	
    }

    // Finds the first empty index in a set to insert a new number
    public int Index(int[] Birthday) {
        int index = 0;
        for (int i = 0; i < Birthday.length; i++) {
            if (Birthday[i] == 0) {
                index = i;
                break;
            }
        }
        return index;
    }

    // Builds the M sets of numbers based on binary representation
    public void Builder(int number, int[][] day) {
        for (int i = 1; i <= number; i++) {
        	int num = i;
            int temp = num;
            for (int j = day.length - 1; j >= 0; j--) {
                if (temp >= (int) Math.pow(2, j)) {
                    temp -= (int) Math.pow(2, j);
                    int k = Index(day[j]);
                    day[j][k] = num;
                }
            }
        }
    }

    // Prompts the user to guess if their number is in each set
    public String Guess(int[][] day) {
        String temp = ""; // Temporary variable to store user input
        for (int i = 0; i < day.length; i++) { // Iterate through each set
            do {
                // Display error message if input is not 1 or 0
                if (!temp.equals("1") && !temp.equals("0") && !temp.equals("")) {
                    System.out.println("Input error, please re-enter");
                }
                // Display the set of numbers
                for (int j = 0; j < day[i].length; j++) {
                    printFormattedNumber(day, i, j);
                }
                System.out.println('\n');
                System.out.println(choice == 1 ? "Is your birthday in it?" : "Is your number in it?");
                System.out.println("Enter 1 for Yes and 0 for No:");
                temp = input.nextLine(); // Read user's response
            } while (!temp.equals("1") && !temp.equals("0"));
            
            answer = temp +  answer; // Append user's response to the answer
        }
        return answer;
    }

    // Prints numbers with appropriate formatting based on the choice
    private void printFormattedNumber(int[][] Birthday, int i, int j) {
        if (choice == 1) {
            if (j % 4 != 0) {
                System.out.print(Birthday[i][j] < 10 ? " " + Birthday[i][j] + " " : Birthday[i][j] + " ");
            } else {
                System.out.println();
                System.out.print(Birthday[i][j] < 10 ? " " + Birthday[i][j] + " " : Birthday[i][j] + " ");
            }
        } else if (choice == 2) {
            if (j % 16 != 0) {
                System.out.print(formatNumber(Birthday[i][j]));
            } else {
                System.out.println();
                System.out.print(formatNumber(Birthday[i][j]));
            }
        }
    }

    // Formats the number for printing based on its value
    private String formatNumber(int number) {
        if (number < 10) {
            return "  " + number + " ";
        } else if (number >= 10 && number < 100) {
            return " " + number + " ";
        } else {
            return number + " ";
        }
    }

    // Prints the final result based on user's choice
    public void prit() {
        if (answer.equals("")) {
            System.out.println("Cannot calculate binary");
        } else {
            System.out.println(choice == 1 ? "Your birthday is: " + num : "Your number is: " + num);
        }
        System.out.println("Matching sets: ");
        BitwiseSearch(answer);
    }

    // Converts the binary answer to decimal to determine the guessed number
    public int BinaryToDecimal(String str) {
        int temp = 0;
        int index = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            int decimal = (int) Math.pow(2, index++);
            if (str.charAt(i) == '1') {
                temp += decimal;
            }
        }
        return temp;
    }

    // Converts a decimal number to its binary representation, padding to a specified length
    public String DecimalToBinary(int integer, int length) {
        if (integer == 0) {
            return "0";
        }
        String binaryString = "";
        while (integer > 0) {
            int lastBit = integer & 1;
            binaryString = lastBit + binaryString;
            integer = integer >> 1;
        }
        while (binaryString.length() < length) {
            binaryString = "0" + binaryString;
        }
        return binaryString;
    }

    // Searches and prints which sets contain the guessed number based on the binary answer
    public void BitwiseSearch(String answer) {  	
        for (int i = 1; i <= day.length; i++) {
            String temp = DecimalToBinary((int)Math.pow(2, i - 1), answer.length());
            for (int j = answer.length() - 1; j >= 0; j--) {
                if (answer.charAt(j) == '1' && temp.charAt(j) == '1') {
                    System.out.println("Your number is in the set " + (i));
                    break;
                }
            }
        }
    }

    // Constructor to initialize the game based on user's choice
    public GuessBirthday(int choice) {
        this.choice = choice;
        if(choice == 1)
        	number = 31;
        else
        	number = 255;
      
        int roundedResult = (int) Math.round(Math.log(number + 1) / Math.log(2));
        
        day = new int[roundedResult][(number + 1) / 2];
        System.out.println("Your number is in the set: " + day.length);
        Builder(number, day);
        answer = Guess(day);
        num = BinaryToDecimal(answer);
    }

    // Main method to run the guessing game
    public static void main(String[] args) {
        char ch;
        do {
            System.out.println("Enter 1: guess the birthday, enter 2 to guess the number.");
            int choice = input.nextInt();
            input.nextLine(); // Clear the newline character
            GuessBirthday guess = new GuessBirthday(choice);
            guess.prit();
            System.out.println("\nEnter 'Y' or 'y' to continue using the program, enter else to exit the program.");
            ch = input.next().charAt(0);
        } while (Character.toUpperCase(ch) == 'Y');
        System.out.println("Thanks for using, byebye.");
        input.close();
    }

    public static int dummy() {
		return 1;
	}
}
