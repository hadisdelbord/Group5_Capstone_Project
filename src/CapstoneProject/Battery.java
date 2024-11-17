package CapstoneProject;

import java.util.concurrent.*;

public class Battery {

	private String name;
	private int capacity; // KW
	private double currentCharge; // KW
	private EnergySource energySource;

	public void charge() {

		if (this.energySource == null) {

			System.err.println("The Energy Source does not exist!");
			return;

		}

		double amount = this.energySource.getPower() / 3600;

		ExecutorService executor = Executors.newFixedThreadPool(10);

		executor.submit(new Runnable() {

			@Override
			public void run() {
				// TODO

				while (true) {

					if (amount <= 0) {
						System.err.println("Amount charge is invalid!");
						return;

					}
					if (amount + currentCharge <= capacity) {
						currentCharge += amount;
						System.out.println(Thread.currentThread().getName() + " charged battery by " + amount
								+ ", current charge: " + currentCharge);
					} else {
						if (amount + currentCharge > capacity) {
							currentCharge = capacity;
							System.out.println("Battery full! Current charge: " + currentCharge);
							return;

						}
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						System.err.println("Interrupted!");
					}

				}
			}
		});
		
		executor.shutdown();

	}

}
