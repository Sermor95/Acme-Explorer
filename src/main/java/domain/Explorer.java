
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Explorer extends Actor {

	//Attributes

	private Collection<CreditCard>		creditCard;

	//Relationships

	private Collection<Story>			story;
	private Collection<Application>		application;
	private Finder						finder;
	private Collection<Contact>			contact;
	private Collection<SurvivalClass>	survivalClass;


	//Getters

	@NotNull
	@Valid
	@OneToMany(mappedBy = "explorer")
	public Collection<Story> getStory() {
		return this.story;
	}

	@NotNull
	@Valid
	@OneToMany(mappedBy = "explorer")
	public Collection<Application> getApplication() {
		return this.application;
	}

	@Valid
	@OneToOne(optional = true)
	public Finder getFinder() {
		return this.finder;
	}

	@NotNull
	@Valid
	@ElementCollection
	public Collection<CreditCard> getCreditCard() {
		return this.creditCard;
	}

	@NotNull
	@Valid
	@OneToMany(mappedBy = "explorer")
	public Collection<Contact> getContact() {
		return this.contact;
	}
	@Valid
	@ElementCollection
	public Collection<SurvivalClass> getSurvivalClass() {
		return this.survivalClass;
	}

	//Setters

	public void setStory(final Collection<Story> story) {
		this.story = story;
	}

	public void setApplication(final Collection<Application> application) {
		this.application = application;
	}

	public void setFinder(final Finder finder) {
		this.finder = finder;
	}

	public void setCreditCard(final Collection<CreditCard> creditCard) {
		this.creditCard = creditCard;
	}

	public void setContact(final Collection<Contact> contact) {
		this.contact = contact;
	}
	public void setSurvivalClass(final Collection<SurvivalClass> survivalClass) {
		this.survivalClass = survivalClass;
	}

}
