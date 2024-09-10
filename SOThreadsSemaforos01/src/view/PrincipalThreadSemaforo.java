package view;
import java.util.concurrent.Semaphore;
import controller.ControllerThreadSemaforo;

public class PrincipalThreadSemaforo {
	public static void main (String[]args) {
		
		Semaphore semaforo = new Semaphore(1);
		
		for (int i = 0; i < 21; i++) {
			ControllerThreadSemaforo thread = new ControllerThreadSemaforo(semaforo);
			thread.start();
		}
	}

}
