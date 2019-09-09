package qms.repository.person;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import qms.repository.base.BaseEntity;

@Entity
@Table(name = "T_PERSON")
public class Person extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2932505360982104887L;

	@Id
	@Column(name = "c_personid")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PERSON_ID_GENERATOR")
	@TableGenerator(name = "PERSON_ID_GENERATOR", table = "T_ID_GENERATOR", pkColumnName = "pk_name", valueColumnName = "pk_value", pkColumnValue = "PERSON_PK", allocationSize = 1)
	private Long personid;


	@Column(name = "c_identityno")
	String identityno;

	@Column(name = "c_firstname")
	String firstname;

	@Column(name = "c_lastname")
	String lastname;
	
	@Column(name = "c_fullname")
	String fullname;

	@Column(name = "c_gender")
	String gender;

	@Column(name = "c_birthdate")
	private String birthdate;

	@Column(name = "c_birthplace")
	private String birthplace;

	@Column(name = "c_username", unique = true, nullable = true)
	private String username;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "c_portrait", columnDefinition = "BLOB", nullable = true)
	private byte[] portrait;

	public Long getPersonid() {
		return personid;
	}

	public void setPersonid(Long personid) {
		this.personid = personid;
	}

	public String getIdentityno() {
		return identityno;
	}

	public void setIdentityno(String identityno) {
		this.identityno = identityno;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getPortrait() {
		return portrait;
	}

	public void setPortrait(byte[] portrait) {
		this.portrait = portrait;
	}
}
