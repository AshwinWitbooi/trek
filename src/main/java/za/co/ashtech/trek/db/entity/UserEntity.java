package za.co.ashtech.trek.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the trek_user database table.
 * 
 */
@Entity
@Table(name="trek_user")
@NamedQuery(name="UserEntity.findAll", query="SELECT t FROM UserEntity t")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TREK_USER_ID")
	private long trekUserId;

	private byte enabled;

	private String password;

	private String username;

	//bi-directional many-to-one association to TrekRoleEntity
	@OneToMany(mappedBy="trekUser")
	private List<UserRoleEntity> trekRoles;

	public UserEntity() {
	}

	public long getTrekUserId() {
		return this.trekUserId;
	}

	public void setTrekUserId(long trekUserId) {
		this.trekUserId = trekUserId;
	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserRoleEntity> getTrekRoles() {
		return this.trekRoles;
	}

	public void setTrekRoles(List<UserRoleEntity> trekRoles) {
		this.trekRoles = trekRoles;
	}

	public UserRoleEntity addTrekRole(UserRoleEntity trekRole) {
		getTrekRoles().add(trekRole);
		trekRole.setTrekUser(this);

		return trekRole;
	}

	public UserRoleEntity removeTrekRole(UserRoleEntity trekRole) {
		getTrekRoles().remove(trekRole);
		trekRole.setTrekUser(null);

		return trekRole;
	}

}