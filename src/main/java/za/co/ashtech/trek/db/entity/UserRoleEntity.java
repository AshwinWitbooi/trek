package za.co.ashtech.trek.db.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the trek_roles database table.
 * 
 */
@Entity
@Table(name="trek_roles")
@NamedQuery(name="UserRoleEntity.findAll", query="SELECT t FROM UserRoleEntity t")
public class UserRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TREK_ROLES_ID")
	private long trekRolesId;

	private String authority;

	//bi-directional many-to-one association to TrekUserEntity
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UserEntity trekUser;

	public UserRoleEntity() {
	}

	public long getTrekRolesId() {
		return this.trekRolesId;
	}

	public void setTrekRolesId(int trekRolesId) {
		this.trekRolesId = trekRolesId;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public UserEntity getTrekUser() {
		return this.trekUser;
	}

	public void setTrekUser(UserEntity trekUser) {
		this.trekUser = trekUser;
	}

}