package CapstoneProject;

import java.util.Iterator;
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

				while (true) {

					if (amount <= 0) {
						System.err.println("Amount charge is invalid!");
						return;

					}
					if (amount + currentCharge <= capacity) {
						currentCharge += amount;
						// System.out.println(Thread.currentThread().getName() + " charged battery by " + amount
							//	+ ", current charge: " + currentCharge);
						try {
							ProgressBar();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else {
						if (amount + currentCharge > capacity) {
							currentCharge = capacity;
							System.out.println("Battery full! Current charge: " + currentCharge);
							return;

						}
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.err.println("Interrupted!");
					}

				}
			}
		});
		
		executor.shutdown();

	}
	
	
	int pbLength = 50;
	public void ProgressBar() throws InterruptedException {
		double percentage = (currentCharge / capacity) * 100;
		double completed = percentage / (100 / pbLength);
		double remained = pbLength - completed;
		
	System.out.print("Battery is chargung...");
	while (percentage <= 100) {
		for (int i = 0; i < completed; i++) {
			System.out.print("=");
		}
		for (int i = 0; i < remained; i++) {
			System.out.print(" ");
		}
        System.out.println(percentage + "%");
        Thread.sleep(100);
        return;

		
	}
		
		
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setCurrentCharge(double currentCharge) {
		this.currentCharge = currentCharge;
	}
	public void setEnergySource(EnergySource energySource) {
		this.energySource = energySource;
	}
	public void setPbLength(int pbLength) {
		this.pbLength = pbLength;
	}
	public Battery(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
		this.currentCharge = 0;
	}

}
