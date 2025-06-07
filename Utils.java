import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Date;

public class Utils {
    public static void showMainMenu() {
        System.out.println("Головне меню гри 'Хрестики-нулики Ultra Mega'");
        System.out.println("Грати - (1)");
        System.out.println("Налаштування - (2)");
        System.out.println("Статистика - (3)");
        System.out.println("Вихід - (4)");
    }

    public static char[][] initializeBoard(int height, int width) {
        char[][] board = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 && j > 0 && j % 2 == 0) board[i][j] = (char) ('0' + j / 2);
                else if (j == 0 && i > 0 && i % 2 == 0) board[i][j] = (char) ('0' + i / 2);
                else if (i % 2 == 0 && j % 2 == 0) board[i][j] = ' ';
                else if (i % 2 == 1 && j % 2 == 1) board[i][j] = '+';
                else if (i % 2 == 1) board[i][j] = '-';
                else board[i][j] = '|';
            }
        }
        return board;
    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) System.out.print(cell + " ");
            System.out.println();
        }
    }

    public static int getUserInput(Scanner sc, String message) {
        System.out.println(message);
        while (!sc.hasNextInt()) {
            System.out.println("помилка - введено некоректні дані.");
            sc.next();
        }
        return sc.nextInt();
    }

    public static boolean isValidMove(char[][] board, int row, int col, int height, int width) {
        return row >= 2 && row < height && col >= 2 && col < width && board[row][col] == ' ';
    }

    public static boolean checkWin(char[][] board, char symbol, int height, int width) {
        for (int i = 2; i < height; i += 2) {
            for (int j = 2; j < width; j += 2) {
                if ((j + 4 < width && board[i][j] == symbol && board[i][j + 2] == symbol && board[i][j + 4] == symbol) ||
                        (i + 4 < height && board[i][j] == symbol && board[i + 2][j] == symbol && board[i + 4][j] == symbol) ||
                        (i + 4 < height && j + 4 < width && board[i][j] == symbol && board[i + 2][j + 2] == symbol && board[i + 4][j + 4] == symbol) ||
                        (i + 4 < height && j - 4 >= 2 && board[i][j] == symbol && board[i + 2][j - 2] == symbol && board[i + 4][j - 4] == symbol)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int getGameSize(Scanner scanner) {
        System.out.println("оберіть розмір гри (3, 5, 7, 9): ");
        int newSize = scanner.nextInt();
        scanner.nextLine();
        return (newSize == 3 || newSize == 5 || newSize == 7 || newSize == 9) ? newSize : -1;
    }

    public static boolean confirmExit(Scanner scanner) {
        System.out.println("впевнені, що хочете вийти? (Так/Ні)");
        return scanner.nextLine().equalsIgnoreCase("Так");
    }

    public static void saveSettings(GameSettings settings) {
        try (PrintWriter writer = new PrintWriter("settings.txt")) {
            writer.println(settings.player1);
            writer.println(settings.player2);
            writer.println(settings.size);
        } catch (Exception e) {
            System.out.println("помилка збереження налаштувань.");
        }
    }

    public static GameSettings loadSettings() {
        try (Scanner fileScanner = new Scanner(new File("settings.txt"))) {
            String player1 = fileScanner.nextLine();
            String player2 = fileScanner.nextLine();
            int size = Integer.parseInt(fileScanner.nextLine());
            return new GameSettings(player1, player2, size);
        } catch (Exception e) {
            return new GameSettings("гравець1", "гравець2", 3);
        }
    }

    public static void saveStats(String winner, GameSettings settings) {
        try (FileWriter fw = new FileWriter("stats.txt", true)) {
            fw.write("дата: " + new Date() + ", розмір: " + settings.size +
                    ", гравець1: " + settings.player1 + ", гравець2: " + settings.player2 + ", переможець: " + winner + "\n");
        } catch (Exception e) {
            System.out.println("помилка збереження статистики.");
        }
    }

    public static void showStats() {
        try (Scanner fileScanner = new Scanner(new File("stats.txt"))) {
            System.out.println("вааш історія ігор:");
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("нема збереженої статистики.");
        }
    }
}
