
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class SurvivalClass extends DomainEntity {

	//Attributes

	private String					title;
	private String					description;
	private Date					moment;

	//Relationships

	private Location				location;
	private Manager					manager;
	private Trip					trip;
	private Collection<Explorer>	explorers;


	//Getters

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getMoment() {
		return this.moment;
	}

	@NotNull
	@Valid
	public Location getLocation() {
		return this.location;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Manager getManager() {
		return this.manager;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Trip getTrip() {
		return this.trip;
	}

	@Valid
	@OneToMany()
	//¿NO SERÍA ELEMENT COLLECTION?
	public Collection<Explorer> getExplorers() {
		return this.explorers;
	}
	//Setters

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setLocation(final Location location) {
		this.location = location;
	}

	public void setManager(final Manager manager) {
		this.manager = manager;
	}

	public void setTrip(final Trip trip) {
		this.trip = trip;
	}
	public void setExplorers(final Collection<Explorer> explorers) {
		this.explorers = explorers;
	}

}
