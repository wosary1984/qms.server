package qms.repository.portal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_PAGE")
public class ApplicationPage implements Serializable {

	private static final long serialVersionUID = -897328162408178590L;

	@Id
	@Column(name = "c_page_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pageid;

	@Column(name = "c_page_name", unique = true, nullable = true)
	String pagename;

	@Column(name = "c_has_deleted", nullable = true)
	boolean hasDeleted;

	@Column(name = "c_has_child", nullable = true)
	boolean hasChild;

	@Column(name = "c_sequence_number", nullable = true)
	int sequenceNumber;

	@Column(name = "c_path", nullable = true)
	String path;

	@Column(name = "c_icon", nullable = true)
	String icon;

	@Column(name = "c_privilege", nullable = true)
	String privilege;

	@Column(name = "c_parent_page_name")
	String parentPageName;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "parentPageName")
	List<ApplicationPage> childs = new ArrayList<ApplicationPage>();

	@OneToMany(mappedBy = "page", orphanRemoval = true, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	Set<ApplicationPageAction> actions = new HashSet<ApplicationPageAction>();

	public Long getPageid() {
		return pageid;
	}

	public void setPageid(Long pageid) {
		this.pageid = pageid;
	}

	public String getPagename() {
		return pagename;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
	}

	public boolean isHasDeleted() {
		return hasDeleted;
	}

	public void setHasDeleted(boolean hasDeleted) {
		this.hasDeleted = hasDeleted;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public List<ApplicationPage> getChilds() {
		return childs;
	}

	public void setChilds(List<ApplicationPage> childs) {
		this.childs = childs;
	}

	public String getParentPageName() {
		return parentPageName;
	}

	public void setParentPageName(String parentPageName) {
		this.parentPageName = parentPageName;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public Set<ApplicationPageAction> getActions() {
		return actions;
	}

	public void setActions(Set<ApplicationPageAction> actions) {
		this.actions = actions;
	}

}
