package weekfour;

import java.util.Scanner;

public class Airplane {

	static Scanner input = new Scanner(System.in);
	static char[][] seats = new char[26][10];         //array which holds the seats values (O - open, X - booked)
	static final int aisle1 = 2, aisle2 = 6;          //variables which holds the index of the columns following by aisles
	
	
	public static void main(String[] args) {          //main method
		
		
		for (int i = 0; i < 26; i++) {                //assign to all the seats default value O (open)
			for (int j = 0; j < 10; j++) 
				seats[i][j] = 'O';
		}
		                                              //invoke the method that reassigns the values of seats 
		takenSeats();                                 //which are already booked to X according to input
		printSeats();                                 //invoke the method to print the plane seats
		numberOfFamilies();                           //invoke the method to calculate the total number of families 
                                                      //who can seat together
		input.close();
	}

	
	public static void printSeats() {                 //method to print the plane seats

		for (int i = 0; i < 26; i++) {                //print the rows 
			for (int j = 0; j < 10; j++) {            //print the columns
				System.out.print((char)(i+65) + "" + (j+1) + '-' + seats[i][j] + "  ");
				
				if (j == aisle1 || j == aisle2)       //print the aisles after third and seventh columns
					System.out.print("      ");
			}
		System.out.println();
		}
	}
	
	
	public static void takenSeats() {                 //method to change the status of seats which are already booked
		
		System.out.println("Please enter the numbers of already booked seats separated by comma: ");
		String takenSeats = input.nextLine();         //example: A4, A10, C5, D3, E6, F8
		
		takenSeats = takenSeats.replaceAll("\\s",""); //remove all white spaces to make sure there are only commas between the seat numbers 
		String[] arr = takenSeats.split(",");         //convert string into array of strings
		
		for (int i=0; i < arr.length; i++) {          //convert seat number (row letter and seat number) 
		                                              //to integers (row index and column index
			int a = (int)(arr[i].charAt(0))-65;       //convert 1st element (row number) to integer
			
			String str = arr[i].substring(1);         //convert 2nd element (column number) to integer
			int b = (Integer.parseInt(str))-1;
			
			seats[a][b] = 'X';                        //assign X to the elements of seats that are already booked
		}
	}
	
	
	public static void numberOfFamilies() { //method to calculate the total number of families who can seat together
		
		int tickets, families = 0;          //declaring the variables to hold the number of families and the number of tickets
		
		for (int i = 0; i < 26; i++) 
			    for (int j = 0; j < 8;) {
			    	
				   if (seats[i][j] == 'O' && seats[i][j+1] == 'O'&& seats[i][j+2] == 'O'  //check all the seats to find out 
						   &&  j != aisle1 && j != aisle2      //the number of families having 3 members wants to seat together 
					       && (j+1) != aisle1 && (j+1) != aisle2){      //in adjacent seats but not separated by an aisle
					   
					   families++;          //if conditions has been met we increment the number of families seating together
	
				       j=j+3;  }            //if conditions has been met and we book three seats - we need to skip them
				   else 
			    	   j++;                 //if conditions has not been met - we need to check the next adjacent seat
		}
		
		tickets = families * 3;             //find the total number of tickets for families (3 people each) 
		
		System.out.println("\nThe number of families who can seat together: " + families); //print the number of families and tickets
		System.out.println("The number of tickets: " + tickets + "\n");
	}
}

