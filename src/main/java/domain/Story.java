
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Story extends DomainEntity {

	//Attributes
	private String		title;
	private String		text;
	private String		attachments;

	//Relationships

	private Trip		trip;
	private Explorer	explorer;


	//Getters

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	@NotBlank
	public String getText() {
		return this.text;
	}

	@NotBlank
	public String getAttachments() {
		return this.attachments;
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

	//Setters

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public void setAttachments(final String attachments) {
		this.attachments = attachments;
	}

	public void setTrip(final Trip trip) {
		this.trip = trip;
	}

	public void setExplorer(final Explorer explorer) {
		this.explorer = explorer;
	}

}
