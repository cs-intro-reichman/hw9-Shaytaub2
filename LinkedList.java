/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index >size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node currentNode = first;
		for (int i=0; i<index; i++){
			currentNode = currentNode.next; 
		}
		return currentNode;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		Node newNode = new Node(block);
		if (index == 0) {
			if (first == null) { 
				first = newNode;
				last = newNode;
			} else {
				newNode.next = first;
				first = newNode;
			}
		}
		else if (index == size) {
			if (last == null) { 
				first = newNode;
				last = newNode;
			} else {
				last.next = newNode;
				last = newNode;
			}
		}
		else{
			Node prevNode = getNode(index - 1);
			newNode.next = prevNode.next;
			prevNode.next = newNode;
		}
			this.size++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node newNode = new Node(block);  // Create a new node with the given memory block

    	if (last == null) {  // If the list is empty (both first and last are null)
        	first = newNode;  // The new node is both the first and the last node
       		last = newNode;
    	} 
		else {
        	last.next = newNode;  // The current last node points to the new node
        	last = newNode;  // Update the last pointer to the new node
    	}
		
		size++; 
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node newNode = new Node(block);  // Create a new node with the given memory block
		if (first == null) {  // If the list is empty (both first and last are null)
        	first = newNode;  // The new node is both the first and the last node
       		last = newNode;
		}
		else {
			newNode.next = first;
			first = newNode;
		}
		size++;
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		Node iNode = getNode(index);
		return iNode.block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		for (int i=0; i<this.size; i++)
		{
			if (getNode(i).block.equals(block))
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 * 		the node that will be removed from this lisא
	 */
	public void remove(Node node) {
	    int i = indexOf(node.block);
		if (i == -1) {
			throw new IllegalArgumentException("Node not found in the list");
		}

		if (i == 0) { 
			first = first.next;
			if (first == null) { 
				last = null;
			}
		} else if (i == size - 1) { 
			Node prev = getNode(i - 1);
			prev.next = null;
			last = prev;
		} else { 
			Node prev = getNode(i - 1);
			Node next = prev.next.next;
			prev.next = next;
		}
		this.size--;
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
			if (index < 0 || index >= size) {
				throw new IllegalArgumentException("index must be between 0 and size");
			}
			if (index == 0) { 
				first = first.next;
				if (first == null) { 
					last = null;
				}
			} else if (index == size - 1) { 
				Node prev = getNode(index - 1);
				prev.next = null;
				last = prev;
			} else {
				Node prev = getNode(index - 1);
				Node current = prev.next;
				Node next = current.next;
				prev.next = next;
			}
			this.size--;
		}	

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		int index = indexOf(block);
		if (index == -1) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		remove(index);	
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		String s = "";
		Node current = first;
		while (current != null) {
		s = s + current.block + " ";
		current = current.next;
		}
		return s;
	}
}		