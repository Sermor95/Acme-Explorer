
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsorship extends DomainEntity {

	//Attributes

	private String		url;
	private String		link;
	private CreditCard	creditcard;

	//Relationships

	private Sponsor		sponsor;


	//Getters

	@NotBlank
	@URL
	public String getUrl() {
		return this.url;
	}

	@NotBlank
	@URL
	public String getLink() {
		return this.link;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Sponsor getSponsor() {
		return this.sponsor;
	}

	@NotNull
	@Valid
	public CreditCard getCreditcard() {
		return this.creditcard;
	}

	//Setters

	public void setUrl(final String url) {
		this.url = url;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	public void setSponsor(final Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	public void setCreditcard(final CreditCard creditcard) {
		this.creditcard = creditcard;
	}

}
