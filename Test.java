public class Test {
    public static void main(String[] args) {

        System.out.println("\nCreating a PQHeap with room for 10 elements\n");      
        PQ pq = new PQHeap(10);

        System.out.println("Inserting elements with keys");
        System.out.println("  3, 5, 0, 100, -117, 1, 1, 1, 2, 3");
        System.out.println("(and corresponding Integers as data)\n");       

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

        System.out.println(pq);

        
        System.out.println("Doing 10 extractMins (showing keys and data)\n");       
        Element e;
        for (int i=0; i<10; i++){
            e = pq.extractMin();
            System.out.println(e.key + " " + e.data);
        }       
    }
}