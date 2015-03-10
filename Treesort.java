import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Treesort {
    public static void main(String[] args) {

        List<Integer> n = new ArrayList<Integer>();
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
