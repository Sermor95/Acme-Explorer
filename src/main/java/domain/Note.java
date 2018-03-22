
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

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Note extends DomainEntity {

	//Attributes

	private Date	moment;
	private String	remark;
	private String	reply;
	private Date	replyMoment;

	//Relationships

	private Auditor	auditor;
	private Trip	trip;


	//Getters

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	@NotBlank
	public String getRemark() {
		return this.remark;
	}

	public String getReply() {
		return this.reply;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getReplyMoment() {
		return this.replyMoment;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Auditor getAuditor() {
		return this.auditor;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Trip getTrip() {
		return this.trip;
	}

	//Setters

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setRemark(final String remark) {
		this.remark = remark;
	}

	public void setReply(final String reply) {
		this.reply = reply;
	}

	public void setReplyMoment(final Date replyMoment) {
		this.replyMoment = replyMoment;
	}

	public void setAuditor(final Auditor auditor) {
		this.auditor = auditor;
	}

	public void setTrip(final Trip trip) {
		this.trip = trip;
	}
}
