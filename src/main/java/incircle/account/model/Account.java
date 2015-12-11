package incircle.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import incircle.domain.model.Connection;
import incircle.domain.model.Education;
import incircle.domain.model.Work;
import org.hibernate.annotations.*;

import javax.persistence.*;
import org.hibernate.annotations.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ACCOUNT_ID", unique = true, nullable = false)
	private Long id;
	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String username;
	@Column(name = "password", nullable = false, length = 60)
	private String password;
	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	private String firstname;

	private String lastname;

	private String picture;

	private String video;

	private String workemail;

	private String personalemail;

	private String phone;

	private boolean jumpwill;

	@Temporal(TemporalType.TIMESTAMP)
	private Date jumpdate;

	@OneToMany(mappedBy = "account")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Set<AccountRole> accountRole = new HashSet<AccountRole>(0);

	@OneToMany(mappedBy = "account")
	private Set<Work> work = new HashSet<Work>(0);

	@OneToMany(mappedBy = "account")
	private Set<Education> education = new HashSet<Education>(0);

	@OneToMany(mappedBy = "account1")
	private Set<Connection> connection1 = new HashSet<Connection>(0);

	@OneToMany(mappedBy = "account2")
	private Set<Connection> connection2 = new HashSet<Connection>(0);

	public Account() {
	}

	public Account(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public Account(String username, String password, boolean enabled, Set<AccountRole> accountRole) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.accountRole = accountRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getWorkemail() {
		return workemail;
	}

	public void setWorkemail(String workemail) {
		this.workemail = workemail;
	}

	public String getPersonalemail() {
		return personalemail;
	}

	public void setPersonalemail(String personalemail) {
		this.personalemail = personalemail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getJumpdate() {
		return jumpdate;
	}

	public void setJumpdate(Date jumpdate) {
		this.jumpdate = jumpdate;
	}

	public boolean isJumpwill() {
		return jumpwill;
	}

	public void setJumpwill(boolean jumpwill) {
		this.jumpwill = jumpwill;
	}

	@JsonIgnore
	public Set<AccountRole> getAccountRole() {
		return this.accountRole;
	}
	@JsonProperty
	public void setAccountRole(Set<AccountRole> accountRole) {
		this.accountRole = accountRole;
	}
	@JsonIgnore
	public Set<Work> getWork() {
		return work;
	}
	@JsonProperty
	public void setWork(Set<Work> work) {
		this.work = work;
	}
}
