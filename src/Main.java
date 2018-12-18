public class Main {

    public static void main(String[] args) {
        HeapPriorityQueue queue = new HeapPriorityQueue();

        queue.push(15);
        queue.push(20);
        queue.push(10);

        queue.push(5);
        queue.push(14);
        queue.push(34);

        queue.push(52);
        queue.push(12);
        queue.push(35);

        queue.push(51);

        System.out.println(queue);
        for(int i=0; i < 10; ++i){
            System.out.print(queue.pop() + " ");
        }
    }
}
