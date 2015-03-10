import java.util.LinkedList;
import java.util.Scanner;

public class Heapsort {
    public static void main(String[] args) {

        LinkedList<Integer> n = new LinkedList<Integer>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            n.add(sc.nextInt());
        }

        PQ pq = new PQHeap(n.size()-1);
        for (int numb : n){
            pq.insert(new Element(numb,numb));
        }

        for (int numb : n){
            System.out.println(pq.extractMin().key);
        }
    }
}
