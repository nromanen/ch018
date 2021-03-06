package com.ch018.library.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateRating {
	public static double getRating(int failedOrders, int untimellyReturns, int timellyReturns) {
		int orders = timellyReturns + untimellyReturns + failedOrders;
		double rating = 1;
		if (orders < IConstants.MIN_ORDERS_FOR_RATING) {
			return rating;
		}
		rating = (double) timellyReturns/(double) orders;
		return new BigDecimal(rating).setScale(3, 
				RoundingMode.HALF_UP).doubleValue();
	}
	
	public static double getRating() {
		return 1;
	}
}
