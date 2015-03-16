import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Uses the DictBinTree structure ad methods
 * to create and sort a list of numbers
 */
public class Treesort {
    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<Integer>();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) {
            numbers.add(scan.nextInt());
        }

        Dict dict = new DictBinTree();

        for (int number : numbers) {
            dict.insert(number);
        }

        for (int number : dict.orderedTraversal()) {
            System.out.println(number);
        }
    }
}
