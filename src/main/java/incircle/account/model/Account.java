package incircle.account.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String username;
	@Column(name = "password", nullable = false, length = 60)
	private String password;
	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	private String firstname;

	private String lastname;

	@Column(name = "primaryemail", unique = true, nullable = false)
	private String primaryemail;

	private String workemail;

	private String personalemail;

	private String phone;

	@Enumerated(EnumType.STRING)
	@Column(name = "willingness")
	private Willingness willingness;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	private Set<AccountRole> accountRole = new HashSet<AccountRole>(0);

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

	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	public Set<AccountRole> getAccountRole() {
		return this.accountRole;
	}

	public void setAccountRole(Set<AccountRole> accountRole) {
		this.accountRole = accountRole;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setPersonalemail(String personalemail) {
		this.personalemail = personalemail;
	}

	public String getPersonalemail() {
		return personalemail;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPrimaryemail(String primaryemail) {
		this.primaryemail = primaryemail;
	}

	public String getPrimaryemail() {
		return primaryemail;
	}

	public void setWillingness(Willingness willingness) {
		this.willingness = willingness;
	}

	public String getWorkemail() {
		return workemail;
	}

	public void setWorkemail(String workemail) {
		this.workemail = workemail;
	}

	public Willingness getWillingness() {
		return willingness;
	}
}
