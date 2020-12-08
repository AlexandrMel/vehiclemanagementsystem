package com.vehicle.management.exception;

public class SaleNotMatchExcption extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SaleNotMatchExcption(String message) {
		super(message);
	}
	
	public SaleNotMatchExcption(String message, Throwable t) {
		super(message, t);
	}
}
