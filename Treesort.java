import java.util.LinkedList;
import java.util.Scanner;

public class Treesort {
    public static void main(String[] args) {

        LinkedList<Integer> n = new LinkedList<Integer>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            n.add(sc.nextInt());
        }

        Dict dict = new DictBinTree();
        for (int numb : n){
            dict.insert(numb);
        }

        System.out.println(dict.orderedTraversal());
    }
}
