import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to MineSweeper!");

        System.out.print("Enter board size: ");
        int size = scanner.nextInt();

        System.out.print("Enter the number of bombs: ");
        int numBombs = scanner.nextInt();

        Controller controller;
        try {
            controller = new Controller(size, numBombs);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            scanner.close();
            return;
        }

        System.out.println("Your board:");
        controller.printBoard();

        while (!controller.isGameOver()) {
            System.out.print("Enter row and column : ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (row == -1 && col == -1) {
                controller.printDeveloperBoard();
                continue;
            }

            if (!controller.isValidPosition(row, col)) {
                System.out.println("Invalid position. Please enter a valid row and column.");
                continue;
            }

            try {
                controller.openBlock(row, col);
                controller.printBoard();
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
