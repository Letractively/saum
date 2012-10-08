package br.com.meganet.facebookAPI.facebook.bean.oauth;

import java.io.Serializable;

public class AgeRange implements Serializable{

	private static final long serialVersionUID = -4999523656675455118L;
	private int min;
	private int max;

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

}
