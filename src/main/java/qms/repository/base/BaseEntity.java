package qms.repository.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Author: Feng
 * Data entity base class which will define 4 common fields, use Auditing funciton to generate value
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) 
public abstract class BaseEntity implements Serializable  {

	private static final long serialVersionUID = 1L;
    @CreatedBy
    @Column(name = "createdBy", nullable = false, length = 32, updatable = false)   
    //@JsonIgnore
    private String createdBy;
    
    @CreatedDate
    @Column(name = "createdDate", nullable = false)
    //@JsonIgnore
    private Date createdDate;
    
    @LastModifiedBy
    @Column(name = "modifiedBy", nullable = false, length = 32)
    //@JsonIgnore
    private String modifiedBy;
    
    @LastModifiedDate
    @Column(name = "modifiedDate")
    //@JsonIgnore
    private Date modifiedDate;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
