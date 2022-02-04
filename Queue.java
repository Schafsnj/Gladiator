//Project 3
//Jayden Schafsnitz
//3/28/2021
//Queue Class- Creates a custom linked list for the queue


public class Queue<T> {
	
	Node<T> head, tail;
	
	//*********** Node Private Class **************
	private class Node<T>
	{
		private Node<T> next;
		private T item;
		private int count;
		
		//*********** Node Method **************
		//constrructor for node
	    //@param - item1 = item being added to that node
		private Node(T item1)
		{
			next = null;
			item = item1;
			count = 0;
		}
	}
	
	//*********** Queue Method **************
	//Uses a Linked List to implement a queue
	public Queue()
	{
		head = null;
		tail = null;
	}
	
	//*********** enqueue Method **************
	//Adds a element to the rear of the queue
    //@param - item = item being added
	public void enqueue(T item)
	{
		Node<T> node = new Node<T>(item);
		
		if(isEmpty())
		{
			head = node;
		}
		else
		{
			tail.next = node;
		}
		
		tail = node;
	}
	
	//*********** enqueue Method **************
	//removes an element from the front and returns it
    //@Return - Returns the item removed
	public T dequeue()
	{
		if(isEmpty())
		{
			throw new IllegalArgumentException("The queue is empty");
		}
		
		T result = head.item; //Setting result to head
		head = head.next; //Changing the head to the value after it
		
		//Checking to see if that made the list empty
		if(isEmpty())
		{
			tail = null;
		}
		
		return result;
	}
	
	//*********** peek Method **************
	//Returns the head element but doesnt remove it
    //@Return - Returns the item at head
	public T peek()
	{
		return head.item;
	}
	
	//*********** size Method **************
	//Returns the size of the queue
    //@Return - Returns the size of queue
	public int size()
	{
		int count = 0;
		Node<T> current = head;
		
		while(current != null)
		{
			count++; 
			current = current.next;
		}
		return count;
	}
	
	//*********** isEmpty Method **************
	//Returns true if the queue is empty
    //@Return - Returns true of the queue is empty
	public boolean isEmpty()
	{
		if(head != null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	

}
