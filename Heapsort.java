import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Uses the PQHeap structure and methods
 * to create and sort a list of numbers
 */
public class Heapsort {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<Integer>();

        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) {
            numbers.add(scan.nextInt());
        }

        PQ pq = new PQHeap(numbers.size());
        for (int number : numbers) {
            pq.insert(new Element(number, number));
        }

        for (int number: numbers) {
            System.out.println(pq.extractMin().key);
        }
    }
}
