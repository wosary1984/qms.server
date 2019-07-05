package qms.repository.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "T_USER")
public class ApplicationUser {
	@Id
	@Column(name = "c_userid")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_ID_GENERATOR")
	@TableGenerator(name = "USER_ID_GENERATOR", table = "T_ID_GENERATOR", pkColumnName = "pk_name", valueColumnName = "pk_value", pkColumnValue = "USER_PK", allocationSize = 1)
	private Long userid;

	@Column(name = "c_username", insertable = true, unique = true, nullable = false)
	String username;

	@Column(name = "c_password")
	String password;

	@Column(name = "c_locked")
	private boolean locked;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

}
