import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameSettings settings = Utils.loadSettings();

        int height = settings.size * 2 + 1;
        int width = settings.size * 2 + 1;

        while (true) {
            if (height == 7) settings.size = 3;
            else if (height == 11) settings.size = 5;
            else if (height == 15) settings.size = 7;
            else if (height == 19) settings.size = 9;

            Utils.showMainMenu();
            String inputSwitch = scanner.nextLine();

            switch (inputSwitch) {
                case "1":
                    Game.playGame(height, width, settings);
                    break;
                case "2":
                    System.out.println("Введіть ім'я першого гравця:");
                    settings.player1 = scanner.nextLine();
                    System.out.println("Введіть ім'я другого гравця:");
                    settings.player2 = scanner.nextLine();
                    int newSize = Utils.getGameSize(scanner);
                    if (newSize != -1) {
                        settings.size = newSize;
                        height = newSize * 2 + 1;
                        width = newSize * 2 + 1;
                        Utils.saveSettings(settings);
                    }
                    break;
                case "3":
                    Utils.showStats();
                    break;
                case "4":
                    if (Utils.confirmExit(scanner)) return;
                    break;
                default:
                    System.out.println("Некоректний формат вводу");
            }
        }
    }
}
