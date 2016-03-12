package com.mhatre.sagar.threads.tutorial;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class G_ProducerConsumerPattern {

	private static boolean fairenss = true;
	/*
	 * a queue constructed with fairness set to true grants threads access in
	 * FIFO order. Fairness generally decreases throughput but reduces
	 * variability and avoids starvation.
	 */
	private static int queueCapacity = 10;
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(queueCapacity, fairenss);
	private static volatile boolean running = true;

	public static void shutdown() {
		running = false;
	}

	private static void produce() {
		Random random = new Random();
		long producerSleepTime = 0;
		int data = 0;
		while (running) {
			try {
				producerSleepTime = random.nextInt(200);
				Thread.sleep(producerSleepTime);
				data = random.nextInt(100);
				System.out.println("Producer will put to queue  :  " + data);
				// queue.add(data);
				/*
				 * Inserts the specified element at the tail of this queue if it
				 * is possible to do so immediately without exceeding the
				 * queue's capacity, returning true upon success and throwing an
				 * IllegalStateException if this queue is full.
				 */
				queue.put(data);
				System.out.println("Producer has put to queue  :  " + data);

			} catch (InterruptedException e) {
				e.printStackTrace();

			} // If queue.add(data); is used, it will throw a
				// java.lang.IllegalStateException when the queue is full
			/*
			catch (java.lang.IllegalStateException ille) {
				System.out.println("Producer will sleep for 1 sec  :  " + ille.getMessage());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}*/

		}
	}

	private static void consume() {
		Random random = new Random();
		Set<Integer> consumedEntries = new HashSet<Integer>();
		long consumerSleepTime = 1000;	
		int queueSize = 0;
		while (running) {
			try {
				Thread.sleep(consumerSleepTime);
				queueSize = queue.size();
				if (queueSize > 0) {
					for (int i = 0; i < queueSize; i++) {
						consumedEntries.add(queue.take());
					}
					System.out.println("\t\t\t\t\t\t Consumer found entries in queue. Entries  :  " + consumedEntries);
					consumedEntries.clear();
				}

				/*
				 * // The iterator allows you to iterate through the queue
				 * contents without taking them off the queue
				 * java.util.Iterator<Integer> iterator = queue.iterator(); if
				 * (iterator.hasNext()) { iterator = queue.iterator(); do {
				 * consumedEntries.add(iterator.next()); }while
				 * (iterator.hasNext()); System.out.println(
				 * "\t\t\t\t\t\t Consumer found entries in queue. Entries  :  "
				 * + consumedEntries); consumedEntries.clear(); }
				 */
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Thread producerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				produce();
			}
		});
		Thread consumerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				consume();
			}
		});
		System.out.println("Press Enter to Stop the threads");

		producerThread.start();
		consumerThread.start();

		try (Scanner scanner = new Scanner(System.in)) {
			scanner.nextLine();
		}
		System.out.println("Stopping the threads ...");
		shutdown();

	}

}
