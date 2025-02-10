package housieticketgenerator;

import java.util.*;
import java.util.stream.*;

public class TicketGeneration {

	public static Ticket createTicket(int rows, int columns) throws TicketGenerationException {

		if (rows != 3 || columns != 9) {
			throw new TicketGenerationException("Ticket must have 3 rows and 9 columns.");
		}

		Ticket ticket = new Ticket(rows, columns);
		Random random = new Random();

		for (int col = 0; col < columns; col++) {
			List<Integer> columnRange = getColumnRange(col);
			Collections.shuffle(columnRange);

			int cellsToFill = random.nextInt(2) + 1;
			addValuesToColumn(ticket, col, columnRange.subList(0, cellsToFill), random);
		}

		ticket.printTicket();

		if (!isApproved(ticket)) {
			throw new TicketGenerationException(
					"Ticket is invalid after generation. Either a row has more than 5 values or any column has more than 2 values . Otherwise no. of values has exceeded 15, only 15 values to be fill.");
		}

		return ticket;
	}

	private static List<Integer> getColumnRange(int columnIndex) {
		int start = columnIndex * 10 + 1;
		int end = start + 9;
//		List<Integer> range = new ArrayList<>();
//		for (int i = start; i <= end; i++) {
//			range.add(i);
//		}
//		return range;
		return IntStream.range(start, end + 1).boxed().collect(Collectors.toList());
	}

	private static void addValuesToColumn(Ticket ticket, int column, List<Integer> valuesToAdd, Random random) {
		valuesToAdd.forEach(value -> {
			int row;
			do {
				row = random.nextInt(ticket.getTicket().length);
			} while (ticket.getTicket()[row][column] != 0);
			ticket.getTicket()[row][column] = value;
		});
	}

	private static boolean isApproved(Ticket ticket) {
		int filledCells = 0;

		for (int column = 0; column < 9; column++) {
			Set<Integer> columnValues = new HashSet<>();
			int columnFilledCount = 0;

			for (int row = 0; row < 3; row++) {
				int cellValue = ticket.getTicket()[row][column];
				if (cellValue != 0) {

					if (!columnValues.add(cellValue)) {
						return false;
					}
					columnFilledCount++;
					filledCells++;
				}
			}

			if (columnFilledCount > 2) {
				return false;
			}
		}

		for (int row = 0; row < 3; row++) {
			int rowFilledCount = 0;

			for (int column = 0; column < 9; column++) {
				if (ticket.getTicket()[row][column] != 0) {
					rowFilledCount++;
				}
			}

			if (rowFilledCount > 5) {
				return false;
			}
		}

		return filledCells == 15;
	}

}