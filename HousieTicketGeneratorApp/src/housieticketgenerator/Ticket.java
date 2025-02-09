package com.edu.housieticketgenerator;

public class Ticket {
    private final int[][] ticket;

    public Ticket(int rows, int columns) {
        this.ticket = new int[rows][columns];
    }

    public int[][] getTicket() {
        return ticket;
    }

    public void printTicket() {
        for (int[] row : ticket) {
            for (int cell : row) {
                System.out.print(cell == 0 ? " 0 " : String.format("%2d", cell) + " ");
            }
            System.out.println();
        }
    }
}