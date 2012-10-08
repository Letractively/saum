package br.com.meganet.facebookAPI.facebook.bean.oauth;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 8116681687675434133L;
	private String country;
	private String locale;
	private AgeRange age;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public AgeRange getAge() {
		return age;
	}

	public void setAge(AgeRange age) {
		this.age = age;
	}

}
