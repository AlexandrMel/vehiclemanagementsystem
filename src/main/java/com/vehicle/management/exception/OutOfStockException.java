package com.vehicle.management.exception;

public class OutOfStockException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public OutOfStockException(String message) {
		super(message);
	}
	
	public OutOfStockException(String message, Throwable t) {
		super(message, t);
	}
}
