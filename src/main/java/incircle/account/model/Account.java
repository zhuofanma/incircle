package incircle.account.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String username;
	@Column(name = "password", nullable = false, length = 60)
	private String password;
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
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

}
