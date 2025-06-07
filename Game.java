import java.util.Scanner;

public class Game {
    public static void playGame(int height, int width, GameSettings settings) {
        char[][] board = Utils.initializeBoard(height, width);
        boolean player = true;
        Scanner sc = new Scanner(System.in);
        char symbol;

        while (true) {
            Utils.printBoard(board);
            symbol = player ? 'X' : '0';
            String currentPlayer = player ? settings.player1 : settings.player2;

            int row = Utils.getUserInput(sc, currentPlayer + ", введіть номер строки (1-" + settings.size + "): ") * 2;
            int col = Utils.getUserInput(sc, currentPlayer + ", введіть номер стовпця (1-" + settings.size + "): ") * 2;

            if (Utils.isValidMove(board, row, col, height, width)) {
                board[row][col] = symbol;
                if (Utils.checkWin(board, symbol, height, width)) {
                    Utils.printBoard(board);
                    System.out.println("гравець " + currentPlayer + " (" + symbol + ") переміг у Ultra Mega Tic Tac Toes!");
                    Utils.saveStats(currentPlayer, settings);
                    break;
                }
                player = !player;
            } else {
                System.out.println("некоректний хід, спробуйте ще раз.");
            }
        }
    }
}
