/*
 * Test program exercising PQHeap.java and DictBinTree.java. Move to
 * the directory containing the project java files, compile and run.
 */

class TestProjectPartI {

    public static void main(String[] args) {

    System.out.println();
    System.out.println("Creating a PQHeap with room for 10 elements");
    System.out.println();
    PQ pq = new PQHeap(10);

    System.out.println("Inserting elements with keys");
    System.out.println("  3, 5, 0, 100, -117, 1, 1, 1, 2, 3");
    System.out.println("(and corresponding Integers as data)");
    System.out.println();

    pq.insert(new Element(3,new Integer(3)));
    pq.insert(new Element(5,new Integer(5)));
    pq.insert(new Element(0,new Integer(0)));
    pq.insert(new Element(100,new Integer(100)));
    pq.insert(new Element(-117,new Integer(-117)));
    pq.insert(new Element(1,new Integer(1)));
    pq.insert(new Element(1,new Integer(1)));
    pq.insert(new Element(1,new Integer(1)));
    pq.insert(new Element(2,new Integer(2)));
    pq.insert(new Element(3,new Integer(3)));

    System.out.println("Doing 10 extractMins (showing keys and data)");
    System.out.println();
    Element e;
    for (int i=0; i<10; i++){
        e = pq.extractMin();
        System.out.println(e.key + " " + e.data);
    }


    System.out.println();
    System.out.println();
    System.out.println("Creating a DictBinTree");
    Dict d = new DictBinTree();

    System.out.println();
    System.out.println("Inserting 10,20,11,19,12,18,13,17,14,16");        
    int[] insertValues = {10,20,11,19,12,18,13,17,14,16};
    for (int i=0; i<insertValues.length; i++) {
        d.insert(insertValues[i]);
    }

    
    System.out.println();
    System.out.println("Testing search()");
    String answer;
    int[] searchValues = {2, 12, 0, 15, 20, 21};
    for (int i=0; i<searchValues.length; i++) {
        System.out.print("Searching for " + searchValues[i]);
        if (d.search(searchValues[i])) {answer = "PRESENT";} else {answer = "ABSENT";}
        System.out.println(" - searching for value " + searchValues[i] + " reported " + answer);
    }/*

    System.out.println();
    System.out.println("Calling orderedTraversal");
    int[] list = d.orderedTraversal();
    System.out.println("Output is:");   
    for (int i=0; i<list.length; i++) {
        System.out.print(" " + list[i]);
    }
    System.out.println();

    System.out.println();
    System.out.println("Now testing empty tree");

    System.out.println();
    System.out.println("Creating a DictBinTree");
        d = new DictBinTree();

    System.out.println();
    System.out.println("Testing search()");
    for (int i=0; i<searchValues.length; i++) {
        System.out.print("Searching for " + searchValues[i]);
        if (d.search(searchValues[i])) {answer = "PRESENT";} else {answer = "ABSENT";}
        System.out.println(" - searching for value " + searchValues[i] + " reported " + answer);
    }

    System.out.println();
    System.out.println("Calling orderedTraversal");
    list = d.orderedTraversal();
    System.out.println("Output is:");   
    for (int i=0; i<list.length; i++) {
        System.out.print(" " + list[i]);
    }

    System.out.println();
    */
    }
}
