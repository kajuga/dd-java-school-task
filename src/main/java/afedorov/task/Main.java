package afedorov.task;

public class Main {

    public static void main(String[] args) {
        if (args.length == 1) {
            String input = args[0];
            System.out.println(new StringHandler(input).handle());
        }
    }
}
