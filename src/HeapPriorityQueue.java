import java.util.Arrays;

public class HeapPriorityQueue {

    private final Node[] queue;
    private int heapSize = 0;

    public HeapPriorityQueue(int levels) {
        int nodeSum = 1;
        while(levels > 1){
            nodeSum += Math.pow(2, levels-1);
            --levels;
        }
        this.queue = new Node[nodeSum];
    }

    public void push(int value){
        Node node = insertNode(value);
        if(node.getPosition() != 0){
            Node parentNode = queue[(node.getPosition() - 1) / 2];
            while (value > parentNode.getValue()){
                swapNodes(node, parentNode);
                if(node.getPosition() == 0){
                    break;
                } else {
                    parentNode = queue[(node.getPosition() - 1) / 2];
                }
            }
        }
    }

    public int pop(){
        Node poppedNode = queue[0];
        Node lastNode = setLastNodeToRoot();

        Node greaterChild = getGreaterChild(lastNode);
        if(greaterChild == null){
            return poppedNode.getValue();
        }
        while(greaterChild.getValue() > lastNode.getValue()){
            swapNodes(greaterChild, lastNode);

            greaterChild = getGreaterChild(lastNode);
            if(greaterChild == null){
                return poppedNode.getValue();
            }
        }
        return poppedNode.getValue();
    }

    private Node getGreaterChild(Node parent){
        Node leftChild = queue[(parent.getPosition() * 2) + 1];
        Node rightChild = queue[(parent.getPosition() * 2) + 2];

        if(rightChild == null){
            return leftChild;
        } else {
            return leftChild.getValue() > rightChild.getValue() ? leftChild : rightChild;
        }
    }

    private Node setLastNodeToRoot(){
        if(heapSize == 1){
            heapSize--;
            queue[0] = queue[heapSize];
            queue[0].setPosition(0);
            return queue[0];
        } else if(heapSize > 0){
            heapSize--;
            queue[0] = queue[heapSize];
            queue[0].setPosition(0);
            queue[heapSize] = null;
            return queue[0];
        }else {
            throw new ArrayStoreException("Heap empty!");
        }
    }

    private Node insertNode(int value){
        if(heapSize < queue.length){
            Node node = new Node(value, heapSize);
            queue[heapSize] = node;
            heapSize++;
            return node;
        } else {
        throw new ArrayStoreException("Heap full!");
        }
    }

    private void swapNodes(Node nodeOne, Node nodeTwo){
        int nodeOnePositionPlaceholder = nodeOne.getPosition();
        nodeOne.setPosition(nodeTwo.getPosition());
        nodeTwo.setPosition(nodeOnePositionPlaceholder);
        queue[nodeTwo.getPosition()] = nodeTwo;
        queue[nodeOne.getPosition()] = nodeOne;
    }

    @Override
    public String toString() {
        return "HeapPriorityQueue{" +
                Arrays.toString(queue) +
                '}';
    }
}
