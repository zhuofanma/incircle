package incircle.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "accounts_roles", uniqueConstraints = @UniqueConstraint(columnNames = { "role", "account_id" }))
public class AccountRole {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "account_role_id", unique = true, nullable = false)
	private Integer userRoleId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;

	@Column(name = "role", nullable = false, length = 45)
	private String role;

	public AccountRole() {
	}

	public AccountRole(Account account, String role) {
		this.account = account;
		this.role = role;
	}


	public Integer getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	@JsonIgnore
	public Account getAccount() {
		return this.account;
	}
	@JsonProperty
	public void setAccount(Account account) {
		this.account = account;
	}


	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}