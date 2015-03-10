
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Heapsort {

    public static void main(String[] args) {

        List<Integer> n = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            n.add(sc.nextInt());
        }

        PQ pq = new PQHeap(n.size());
        for (int numb : n) {
            pq.insert(new Element(numb, numb));
        }

        for (int numb : n) {
            System.out.println(pq.extractMin().key);
        }
    }
}
