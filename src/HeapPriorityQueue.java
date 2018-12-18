import java.util.Arrays;

public class HeapPriorityQueue {

    private final Node[] queue = new Node[15];

    public void push(int value){
        Node node = putInFirstFreeSpot(value);
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
        for(int i = queue.length-1; i >= 0; --i){
            if(queue[i] != null){
                Node placeholder = queue[i];

                placeholder.setPosition(0);
                queue[0] = placeholder;
                queue[i] = null;

                return placeholder;
            }
        }
        throw new ArrayStoreException("Heap empty!");
    }

    private Node putInFirstFreeSpot(int value){
        for(int i = 0; i < queue.length; ++i){
            if(queue[i] == null){
                Node node = new Node(value, i);
                queue[i] = node;
                return node;
            }
        }
        throw new ArrayStoreException("Heap full!");
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
