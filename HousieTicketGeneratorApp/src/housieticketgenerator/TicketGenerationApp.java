package housieticketgenerator;

import java.util.Scanner;

public class TicketGenerationApp {

	public static void main(String[] args) throws TicketGenerationException{
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Enter number of rows for the ticket (should be 3): ");
			int rows = scanner.nextInt();
			System.out.print("Enter number of columns for the ticket (should be 9): ");
			int columns = scanner.nextInt();
			
			scanner.close();
			Ticket ticket = TicketGeneration.createTicket(rows, columns);
			
			System.out.println();
			
			ticket.printTicket();

			System.out.println("Ticket generated successfully.");
		} catch (TicketGenerationException e) {
			System.out.println("Error: " + e.getMessage());
		}catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e);
		}
	}
}