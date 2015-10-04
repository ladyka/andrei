package by.ladyka.pass.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the webSite database table.
 * 
 */
@Entity
@Table(name="webSite")
@NamedQuery(name="WebSite.findAll", query="SELECT w FROM WebSite w")
public class WebSite implements Serializable {

	private static final long serialVersionUID = 201510041737011231L;

	public static final String COL_ID = "id";
	public static final String COL_EMAIL = "email";
	public static final String COL_LOGIN = "login";
	public static final String COL_PASSWORD = "mypassword";
	public static final String COL_OTHER_INFO = "otherInfo";
	public static final String COL_SITE_URL = "siteUrl";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

    @JsonIgnore
	private Timestamp changeTime;

	private String email;

	private String login;

	private String mypassword;

	@Lob
	private String otherInfo;

	private String siteUrl;

	public WebSite() {
	}

    public int getId() {
		return this.id;
	}

	public WebSite setId(int id) {
		this.id = id;
		return this;
	}

	public Timestamp getChangeTime() {
		return this.changeTime;
	}

	public void setChangeTime(Timestamp changeTime) {
		this.changeTime = changeTime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMypassword() {
		return this.mypassword;
	}

	public void setMypassword(String mypassword) {
		this.mypassword = mypassword;
	}

	public String getOtherInfo() {
		return this.otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getSiteUrl() {
		return this.siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

    @JsonProperty("time")
    public String getTime() {
        return changeTime.toString();
    }

	@Override
	public String toString() {
		return "WebSite{" +
				"id=" + id +
				", changeTime=" + changeTime +
				", email='" + email + '\'' +
				", login='" + login + '\'' +
				", mypassword='" + mypassword + '\'' +
				", otherInfo='" + otherInfo + '\'' +
				", siteUrl='" + siteUrl + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		WebSite webSite = (WebSite) o;

		if (id != webSite.id) return false;
		if (changeTime != null ? !changeTime.equals(webSite.changeTime) : webSite.changeTime != null) return false;
		if (email != null ? !email.equals(webSite.email) : webSite.email != null) return false;
		if (login != null ? !login.equals(webSite.login) : webSite.login != null) return false;
		if (mypassword != null ? !mypassword.equals(webSite.mypassword) : webSite.mypassword != null) return false;
		if (otherInfo != null ? !otherInfo.equals(webSite.otherInfo) : webSite.otherInfo != null) return false;
		return !(siteUrl != null ? !siteUrl.equals(webSite.siteUrl) : webSite.siteUrl != null);

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (changeTime != null ? changeTime.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (mypassword != null ? mypassword.hashCode() : 0);
		result = 31 * result + (otherInfo != null ? otherInfo.hashCode() : 0);
		result = 31 * result + (siteUrl != null ? siteUrl.hashCode() : 0);
		return result;
	}
}