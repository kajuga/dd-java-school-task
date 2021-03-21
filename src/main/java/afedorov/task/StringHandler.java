package afedorov.task;

import java.util.LinkedList;
import java.util.Stack;

public class StringHandler {

    private final String input;

    private Stack<Character> brackets = new Stack<Character>();
    private LinkedList<String> parts = new LinkedList<String>();
    private Stack<Integer> counts = new Stack<Integer>();
    private int start;
    private boolean beforeWasClose = false;

    public StringHandler(String input) {
        this.input = input;
    }

    public String handle() {
        parseAndCheckInput();
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            result.append(part);
        }
        return result.toString();
    }

    private void parseAndCheckInput() {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '[') {
                getCount(i);
            }
            if (input.charAt(i) == ']') {
                unpackPart(i);
            }
        }

        if (start < input.length()) {
            parts.add(input.substring(start));
        }

        if (!brackets.isEmpty()) {
            markNotValid();
        }
    }

    private void getCount(int index) {
        brackets.push(input.charAt(index));
        try {
            Integer count = Integer.parseInt(input.substring(start, index));
            counts.push(count);
            start = index + 1;
            beforeWasClose = false;
        } catch (Exception e) {
            markNotValid();
        }
    }

    private void unpackPart(int index) {
        if (brackets.isEmpty() || counts.isEmpty()) {
            markNotValid();
        } else {
            String part = input.substring(start, index);
            isValidPart(part);
            brackets.pop();
            if (beforeWasClose) {
                StringBuilder result = new StringBuilder();
                while (!parts.isEmpty()){
                    result.append(parts.removeFirst());
                }
                part = result.append(part).toString();
            }

            int count = counts.pop();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < count; i++) {
                builder.append(part);
            }
            parts.addLast(builder.toString());
            beforeWasClose = true;

            start = index + 1;
        }
    }

    private void isValidPart(String part) {
        for (char symbol : part.toCharArray()) {
            if (!((symbol >= 'A' && symbol <= 'Z') || (symbol >= 'a' && symbol <= 'z'))) {
                markNotValid();
            }
        }
    }

    private void markNotValid() {
        throw new IllegalArgumentException("Input string is not valid!");
    }

}
