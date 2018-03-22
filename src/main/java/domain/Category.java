
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	//Attributes

	private String					name;

	//Relationships

	private Category				parent;
	private Collection<Category>	children;
	private Collection<Trip>		trip;


	//Getters

	@NotBlank
	public String getName() {
		return this.name;
	}

	@Valid
	@NotNull
	@OneToMany
	public Collection<Trip> getTrip() {
		return this.trip;
	}

	@ManyToOne(optional = true)
	public Category getParent() {
		return this.parent;
	}

	@ElementCollection
	public Collection<Category> getChildren() {
		return this.children;
	}

	//Setters

	public void setName(final String name) {
		this.name = name;
	}

	public void setTrip(final Collection<Trip> trip) {
		this.trip = trip;
	}

	public void setParent(final Category parent) {
		this.parent = parent;
	}

	public void setChildren(final Collection<Category> children) {
		this.children = children;
	}

}
