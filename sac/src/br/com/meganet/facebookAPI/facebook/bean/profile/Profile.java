package br.com.meganet.facebookAPI.facebook.bean.profile;

import java.io.Serializable;
import java.util.List;

public class Profile implements Serializable {

	private static final long serialVersionUID = 6387865319942106520L;
	private String id;
	private String name;
	private String first_name;
	private String last_name;
	private String link;
	private String bio;
	private String gender;
	private String email;
	private int timezone;
	private String locale;
	private boolean verified;
	private String updated_time;

	private String username;
	private String third_party_id;
	private String about;
	private String birthday;

	private List<String> interested_in;
	private String political;
	private String quotes;
	private String relationship_status;
	private String religion;
	private String website;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String lastName) {
		this.last_name = lastName;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getUpdated_time() {
		return updated_time;
	}

	public void setUpdated_time(String updated_time) {
		this.updated_time = updated_time;
	}

	public String getUpdatedTime() {
		return updated_time;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updated_time = updatedTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getThird_party_id() {
		return third_party_id;
	}

	public void setThird_party_id(String third_party_id) {
		this.third_party_id = third_party_id;
	}
	
	public String getThirdPartyId() {
		return third_party_id;
	}

	public void setThirdPartyId(String thirdPartyId) {
		this.third_party_id = thirdPartyId;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public List<String> getInterested_in() {
		return interested_in;
	}

	public void setInterested_in(List<String> interested_in) {
		this.interested_in = interested_in;
	}
	
	public List<String> getInterestedIn() {
		return interested_in;
	}

	public void setInterestedIn(List<String> interestedIn) {
		this.interested_in = interestedIn;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public String getQuotes() {
		return quotes;
	}

	public void setQuotes(String quotes) {
		this.quotes = quotes;
	}

	public String getRelationship_status() {
		return relationship_status;
	}

	public void setRelationship_status(String relationship_status) {
		this.relationship_status = relationship_status;
	}
	
	public String getRelationshipStatus() {
		return relationship_status;
	}

	public void setRelationshipStatus(String relationshipStatus) {
		this.relationship_status = relationshipStatus;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}
