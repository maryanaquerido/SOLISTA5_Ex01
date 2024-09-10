package controller;
import java.util.Random;
import java.util.concurrent.*;

public class ControllerThreadSemaforo extends Thread {
	private Random random = new Random();
	private Semaphore semaforo;
	
	public ControllerThreadSemaforo (Semaphore semaforo) {
		this.semaforo = semaforo;
	}
	
	public void run () {
		Calc();
	}
	
	public void Calc() {
		int tid = (int) threadId();
		
		if (tid % 3 == 1) {
			IDRestoUm();
			
		} else 
			if (tid % 3 == 2) {
			IDRestoDois();
			
		} else 
			if (tid % 3 == 0) {
				IDRestoZero();
			}
	}
	
	private void IDRestoUm () {
		try {
			Calculos(0.2, 1.0);
			Transacao(1);
			Calculos(0.2, 1.0);
			Transacao(1);	
			
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}	
	}
	
	private void IDRestoDois () {
		try {
			Calculos(0.5, 1.5);
			Transacao(1.5);
			Calculos(0.5, 1.5);
			Transacao(1.5);
			Calculos(0.5, 1.5);
			Transacao(1.5);
			
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}			
	}
	
	private void IDRestoZero () {
		try {
			Calculos(1.0, 2.0);
			Transacao(1.5);
			Calculos(1.0, 2.0);
			Transacao(1.5);
			Calculos(1.0, 2.0);
			Transacao(1.5);
			
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}	
	}
	
	private void Calculos (double tempInicio, double tempFim) throws InterruptedException {
		double tempCalculos = (tempInicio + (tempFim - tempInicio) * random.nextDouble());
		System.out.println("Thread #" + threadId() + " - Cálculos por " + tempCalculos + "s.");
		Thread.sleep((long) (tempCalculos * 1000));		
	}
	
	private void Transacao (double temploBD) throws InterruptedException {
		semaforo.acquire();
		System.out.println("Thread #" + threadId() + " - Transação por " + temploBD + "s." );
		Thread.sleep((long) (temploBD * 1000)); 
		semaforo.release();
	}

}
