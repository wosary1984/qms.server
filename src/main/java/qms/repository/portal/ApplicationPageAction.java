package qms.repository.portal;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_PAGE_ACTION")
public class ApplicationPageAction implements Serializable {

	private static final long serialVersionUID = -897328162408178590L;

	@Id
	@Column(name = "c_action_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long actionid;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pageid", insertable = true, nullable = false)
	ApplicationPage page;

	@Column(name = "c_action", unique = false, nullable = true)
	String action;

	@Column(name = "c_sequence_number", nullable = true)
	int sequenceNumber;

	@Column(name = "c_icon", nullable = true)
	String icon;

	@Column(name = "c_privilege", nullable = true)
	String privilege;

	public Long getActionid() {
		return actionid;
	}

	public void setActionid(Long actionid) {
		this.actionid = actionid;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public ApplicationPage getPage() {
		return page;
	}

	@JsonIgnore
	public void setPage(ApplicationPage page) {
		this.page = page;
	}

}
