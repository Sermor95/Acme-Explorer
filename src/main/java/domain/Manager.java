
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Actor {

	//Relationships

	private Collection<SurvivalClass>	survivalClass;
	private Collection<Trip>			trip;
	private Collection<Zolet>			zolets;


	//Getters

	@Valid
	@NotNull
	@OneToMany(mappedBy = "manager")
	public Collection<SurvivalClass> getSurvivalClass() {
		return this.survivalClass;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "manager")
	public Collection<Trip> getTrip() {
		return this.trip;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "manager")
	public Collection<Zolet> getZolets() {
		return this.zolets;
	}

	//Setters

	public void setSurvivalClass(final Collection<SurvivalClass> survivalClass) {
		this.survivalClass = survivalClass;
	}

	public void setTrip(final Collection<Trip> trip) {
		this.trip = trip;
	}

	public void setZolets(final Collection<Zolet> zolets) {
		this.zolets = zolets;
	}

}
