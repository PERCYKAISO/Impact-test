import java.util.Collection;
import java.util.Scanner;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        //allows user to enter comma delimited list of numbers
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a comma delimited list of numbers: ");
            String input = scanner.nextLine();

            NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();
            Collection<Integer> numbers = summarizer.collect(input);
            String summary = summarizer.summarizeCollection(numbers);
            System.out.println("Summarized list: " + summary);
        }
    }
}
