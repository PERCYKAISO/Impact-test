import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//collects numbers from a comma delimited input string and returns them as a Collection of integers.
public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {
    @Override
    public Collection<Integer> collect(String input) {
        String[] tokens = input.split(",");
        List<Integer> numbers = new ArrayList<>(tokens.length);
        for (String token : tokens) {
            try {
                numbers.add(Integer.parseInt(token.trim()));
            } catch (NumberFormatException e) {
                // ignores non-integer values
            }
        }
        return numbers;
    }

    //groups consecutive numbers to summarize a collection of integers.
    @Override
    public String summarizeCollection(Collection<Integer> input) {
        List<Integer> sortedList = new ArrayList<>(input);
        Collections.sort(sortedList);
        StringBuilder result = new StringBuilder();
        Integer start = null;
        Integer prev = null;
        for (Integer num : sortedList) {
            if (start == null) {
                start = num;
                prev = num;
            } else if (num == prev + 1) {
                prev = num;
            } else {
                if (result.length() > 0) {
                    result.append(", ");
                }
                result.append(start);
                if (!prev.equals(start)) {
                    result.append("-").append(prev);
                }
                start = num;
                prev = num;
            }
        }
        if (start != null) {
            if (result.length() > 0) {
                result.append(", ");
            }
            result.append(start);
            if (!prev.equals(start)) {
                result.append("-").append(prev);
            }
        }
        return result.toString();
    }
}
