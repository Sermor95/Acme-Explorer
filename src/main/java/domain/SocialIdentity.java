
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
public class SocialIdentity extends DomainEntity {

	//Attributes

	private String	nick;
	private String	socialNetwork;
	private String	link;
	private String	photo;

	//Relationships

	private Actor	actor;


	//Getters

	@NotBlank
	public String getNick() {
		return this.nick;
	}

	@NotBlank
	public String getSocialNetwork() {
		return this.socialNetwork;
	}

	@NotBlank
	@URL
	public String getLink() {
		return this.link;
	}

	@URL
	public String getPhoto() {
		return this.photo;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actor getActor() {
		return this.actor;
	}

	//Setters

	public void setNick(final String nick) {
		this.nick = nick;
	}

	public void setSocialNetwork(final String socialNetwork) {
		this.socialNetwork = socialNetwork;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	public void setActor(final Actor actor) {
		this.actor = actor;
	}
}
