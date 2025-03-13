import java.util.concurrent.*;

class SemaphoreDemo {

	public static void main(String[] args) {

		Semaphore sem = new Semaphore(1);

		new Thread(new IncThread(sem, "A")).start();
		new Thread(new DecThread(sem, "B")).start();
	}
}

class Shared {
	static int count = 0;
}

// A thread of execution that increments count
class IncThread implements Runnable {

	String name;
	Semaphore sem;

	IncThread(Semaphore s, String n){
		sem = s;
		name = n;
	}

	public void run() {

		System.out.println("Starting " + name);

		try {
			// First get the permit
			System.out.println(name + " is waiting for a permit.");
			sem.acquire();
			System.out.println(name + " gets a permit");

			// Now access the shared resource
			for(int i=0; i<5; i++) {
				
				Shared.count++;
				System.out.println(name + " : " + Shared.count);

				// Now, allow the context switch -- if possible
				Thread.sleep(10);
			}
		} catch( InterruptedException exc) {
			System.out.println(exc);
		}

		// Release the permit
		System.out.println(name + " releases the permit");
		sem.release();
	}
}

// A thread of execution that decrements count
class DecThread implements Runnable {

	String name;
	Semaphore sem;

	DecThread(Semaphore s, String n){
		sem = s;
		name = n;
	}

	public void run() {

		System.out.println("Starting " + name);

		try {
			// First get the permit
			System.out.println(name + " is waiting for a permit.");
			sem.acquire();
			System.out.println(name + " gets a permit");

			// Now access the shared resource
			for(int i=0; i<5; i++) {
				
				Shared.count--;
				System.out.println(name + " : " + Shared.count);

				// Now, allow the context switch -- if possible
				Thread.sleep(10);
			}
		} catch( InterruptedException exc) {
			System.out.println(exc);
		}

		// Release the permit
		System.out.println(name + " releases the permit");
		sem.release();
	}
}



