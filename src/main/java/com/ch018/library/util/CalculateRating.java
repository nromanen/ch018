package com.ch018.library.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateRating {
	public static double getRating(int failedOrders, int untimellyReturns, int timellyReturns) {
		int orders = timellyReturns + untimellyReturns;
		double rating = 1;
		if (orders < 5) {
			return rating;
		}
		rating = ((timellyReturns + 1)/(untimellyReturns + 1))/(failedOrders+1);
		return new BigDecimal(rating).setScale(3, RoundingMode.HALF_UP).doubleValue();
	}
	
	public static double getRating() {
		return 1;
	}
}
