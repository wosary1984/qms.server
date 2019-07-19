package qms.repository.portal;

import java.util.ArrayList;
import java.util.List;

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
public class ApplicationPage {

	@Id
	@Column(name = "c_page_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pageid;

	@Column(name = "c_page_name", unique = true, nullable = true)
	String pagename;

	@Column(name = "c_has_deleted", nullable = true)
	boolean hasDeleted;

	@Column(name = "c_sequence_number", nullable = false)
	String sequenceNumber;

	@Column(name = "c_path", nullable = false)
	String path;

	@Column(name = "c_icon", nullable = false)
	String icon;

	@Column(name = "c_privilege", nullable = false)
	String privilege;

	@Column(name = "c_parent_pageid")
	Long parentPageid;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "parentPageid")
	List<ApplicationPage> childs = new ArrayList<ApplicationPage>();

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

	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
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

	public Long getParentPageid() {
		return parentPageid;
	}

	public void setParentPageid(Long parentPageid) {
		this.parentPageid = parentPageid;
	}

	public List<ApplicationPage> getChilds() {
		return childs;
	}

	public void setChilds(List<ApplicationPage> childs) {
		this.childs = childs;
	}

}
