public class Main {
    public static int sum(int number) {
        // If input is 0 return 0
        if (number == 0) {
            return 0;
        }
        // Using mod we can divide by 10 and get the last number in the sequence as a remainder
        // So 1234 % 10 = 4
        // 123 % 10 = 3, etc
        return number % 10 + sum(number / 10);
    }

    public static void main(String[] args) {
        // Check if an argument is provided
        if (args.length != 1) {
            System.out.println("Please provide a single integer as input.");
            return;
        }

        try {
            int inputNumber = Integer.parseInt(args[0]);
            int result = sum(inputNumber);
            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer");
        }
    }
}
