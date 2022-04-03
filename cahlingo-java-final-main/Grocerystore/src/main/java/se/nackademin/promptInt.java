package se.nackademin;

public class promptInt {

    public static int promptInts(String message) {
        while (true) {
            System.out.print(message);
            String input = Menu.scanner.nextLine();
            if (isInteger(input))
                return Integer.valueOf(input);
            System.out.println("Invalid input.");
        }
    }

    private static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String promptString(String message) {
        while (true) {
            System.out.print(message);
            String input = Menu.scanner.nextLine();
            return input;
        }
    }
}