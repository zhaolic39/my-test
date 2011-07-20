package KXT303.W1;
public class Node extends AbstractNode {

	private long seqNumber;
	private Node next;
	static int nodeCreated = 0;
	
	public Node(long seq, Node n) {
		nodeCreated++;
		seqNumber = seq;
		next = n;
	}

	public long getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(long seqNumber) {
		this.seqNumber = seqNumber;
	}

	public Node getNext() {
		return (Node)next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public static void printNode() {
		System.out.println("Nodes created = " + nodeCreated);
	}

}
