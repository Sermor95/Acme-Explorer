
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Application extends DomainEntity {

	//Attributes

	private Date		made;
	private String		comments;
	private String		reason;
	private CreditCard	creditCard;

	//Relationships

	private Status		status;
	private Trip		trip;
	private Explorer	explorer;


	//Getters

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMade() {
		return this.made;
	}

	@NotNull
	@Valid
	public Status getStatus() {
		return this.status;
	}

	public String getComments() {
		return this.comments;
	}

	public String getReason() {
		return this.reason;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Trip getTrip() {
		return this.trip;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Explorer getExplorer() {
		return this.explorer;
	}

	@Valid
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	//Setters

	public void setMade(final Date made) {
		this.made = made;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	public void setReason(final String reason) {
		this.reason = reason;
	}

	public void setTrip(final Trip trip) {
		this.trip = trip;
	}

	public void setExplorer(final Explorer explorer) {
		this.explorer = explorer;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

}
