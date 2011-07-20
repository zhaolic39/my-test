package KXT303.W2;

class MyList {

	int printed; // How many numbers have been printed
	protected Node head, // Reference to the first node in the list
			searchPoint; // Where on the list we will start

	// searching next to place next number

	MyList(long x) {
		head = new Node(0, new Node(x, new Node(Long.MAX_VALUE, null)));
		printed = 0;
	}

	void sayDone() {
		searchPoint = null;
		// Next search to begin at the start of the list
	}

	protected Node search(long number, Node searchPoint) {
		// Locates the node on or after which the number should
		// sit Search begins at searchPoint if it not null.

		while (searchPoint.getNext() != null) {
			if (searchPoint.getNext().getSeqNumber() > number) {
				break;
			}

			searchPoint = (Node) searchPoint.getNext();
		}
		return searchPoint;
	}

	void addNumber(long n) {
		// addNumber implements function addToSet of pseudo-code.

		Node where = search(n, searchPoint);

		if (where.getSeqNumber() == n) {
			searchPoint = where;
			return;
		}

		where.setNext(new Node(n, where.getNext()));
		searchPoint = where.getNext();
		return;
	}

	long takeFirst() {
		// Method takeFirst implements
		// pseudo function removeSmallest
		// used in pseudo-code. This so because the list
		// maintains the nodes in sorted order.

		if (head.getNext() == null) {
			return 0;
		}

		long n = head.getNext().getSeqNumber();

		// we do not want to print too much output
		if (printed % 100 == 99)
			System.out.print(".");
		if (printed % 10000 == 9999)
			System.out.println("Seq[" + printed + "] = " + n);

		printed++;
		head.setNext(head.getNext().getNext());
		searchPoint = head;
		return n;
	}
}
