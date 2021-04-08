package myhibernate.onedirection.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "company_detail")
public class CompanyDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="company_detail_id")
	private Integer companyDetailId;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="created")
	private Date created;
	
	@Column(name="country")
	private String country;

	public Integer getCompanyDetailId() {
		return companyDetailId;
	}

	public void setCompanyDetailId(Integer companyDetailId) {
		this.companyDetailId = companyDetailId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "CompanyDetail [companyDetailId=" + companyDetailId + ", created=" + created + ", country=" + country
				+ "]";
	}	
	
}
