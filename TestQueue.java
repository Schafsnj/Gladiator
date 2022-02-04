import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestQueue {

	
	//Testing the enqueue and dequeue and peek
	@Test
	void test() {
		Queue<Integer> a = new Queue<Integer>();
		a.enqueue(10); //Checking enqueue
		a.enqueue(5);
		assertEquals(a.size(), 2);
		
		assertEquals(a.peek(), 10); //Testing if 10 is in the head position
		assertEquals(a.dequeue(), 10); //Checking if 10 is removed
		assertEquals(a.peek(), 5); //Now testing if 5 is in the head position
		assertEquals(a.dequeue(), 5);
		
		//Checking for dequeue empty exception
		assertThrows(IllegalArgumentException.class, () -> {
		a.dequeue(); // Exception
		});	
	}
	
	@Test
	void test2() {
		Queue<Creature> a = new Queue<Creature>();
		Creature b = new Creature(50,50);
		a.enqueue(b);
		assertEquals(a.size(), 1);
	}

}
