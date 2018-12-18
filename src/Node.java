public class Node {
    private final int value;
    private int position;

    public Node(int value, int position) {
        this.value = value;
        this.position = position;
    }

    public int getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Node{" +
                "v=" + value +
                ", p=" + position +
                '}';
    }
}
