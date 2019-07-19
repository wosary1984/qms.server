package qms.repository.authorization.privilege;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PRIVILEGE")
public class ApplicationPrivilege implements Serializable{

	private static final long serialVersionUID = 969928656354580677L;

	@Id
	@Column(name = "c_privilegeid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long privilegeid;

	@Column(name = "c_privilege", unique = true, nullable = false)
	String privilege;

	@Column(name = "c_object")
	String object;

	public Long getPrivilegeid() {
		return privilegeid;
	}

	public void setPrivilegeid(Long privilegeid) {
		this.privilegeid = privilegeid;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

}
