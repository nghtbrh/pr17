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
        fw.write("дата: " + new java.util.Date() + ", розмір: " + settings.size +
                ", гравець1: " + settings.player1 + ", гравець2: " + settings.player2 + ", переможець: " + winner + "\n");
    } catch (Exception e) {
        System.out.println("помилка збереження статистики.");
    }
}
