
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Ranger extends Actor {

	//Relationships

	private Curriculum			curriculum;
	private Collection<Trip>	trip;


	//Getters

	@Valid
	@NotNull
	@OneToMany(mappedBy = "ranger")
	public Collection<Trip> getTrip() {
		return this.trip;
	}

	@Valid
	@OneToOne(optional = true)
	public Curriculum getCurriculum() {
		return this.curriculum;
	}

	//Setters

	public void setTrip(final Collection<Trip> trip) {
		this.trip = trip;
	}

	public void setCurriculum(final Curriculum curriculum) {
		this.curriculum = curriculum;
	}

}
