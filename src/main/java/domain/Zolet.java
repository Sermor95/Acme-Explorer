
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Zolet extends DomainEntity {

	//Attributes

	private String	code;
	private String	title;
	private String	description;
	private Integer	gauge;
	private Date	moment;

	//Relationships

	private Manager	manager;
	private Trip	trip;


	//Getters

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "\\w{2}\\d{2}\\d{2}\\d{2}\\w{2}")
	public String getCode() {
		return this.code;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	@Range(min = 1, max = 3)
	public Integer getGauge() {
		return this.gauge;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	@Valid
	@ManyToOne(optional = false)
	public Manager getManager() {
		return this.manager;
	}

	@Valid
	@ManyToOne(optional = false)
	public Trip getTrip() {
		return this.trip;
	}

	//Setters

	public void setCode(final String code) {
		this.code = code;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setGauge(final Integer gauge) {
		this.gauge = gauge;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setManager(final Manager manager) {
		this.manager = manager;
	}

	public void setTrip(final Trip trip) {
		this.trip = trip;
	}

}
