
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Trip extends DomainEntity {

	//Attributes

	private String					ticker;
	private String					title;
	private String					description;
	private double					price;
	private String					requirements;
	private Date					publicationDate;
	private Date					startDate;
	private Date					endDate;
	private String					reason;

	//Relationships

	private Ranger					ranger;
	private Collection<Note>		note;
	private Collection<Audit>		audit;
	private Collection<Stage>		stage;
	private LegalText				legalText;
	private Collection<TagValue>	tagValue;
	private Manager					manager;
	private Collection<Application>	application;
	private Collection<Sponsorship>	sponsorship;
	private Collection<Zolet>		zolets;


	//Getters

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "\\d{2}\\d{2}\\d{2}-\\w{4}")
	public String getTicker() {
		return this.ticker;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	@Min(0)
	public double getPrice() {
		return this.price;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getPublicationDate() {

		return this.publicationDate;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getStartDate() {
		return this.startDate;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getEndDate() {
		return this.endDate;
	}

	public String getReason() {
		return this.reason;
	}

	@NotBlank
	public String getRequirements() {
		return this.requirements;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "trip")
	public Collection<Note> getNote() {
		return this.note;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "trip")
	public Collection<Audit> getAudit() {
		return this.audit;
	}

	@Valid
	@NotNull
	@OneToMany
	public Collection<Stage> getStage() {
		return this.stage;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public LegalText getLegalText() {
		return this.legalText;
	}

	@Valid
	@NotNull
	@ManyToMany(mappedBy = "trip")
	public Collection<TagValue> getTagValue() {
		return this.tagValue;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Ranger getRanger() {
		return this.ranger;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Manager getManager() {
		return this.manager;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "trip")
	public Collection<Application> getApplication() {
		return this.application;
	}

	@Valid
	@NotNull
	@OneToMany
	public Collection<Sponsorship> getSponsorship() {
		return this.sponsorship;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "trip")
	public Collection<Zolet> getZolets() {
		return this.zolets;
	}

	//Setters

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	public void setPublicationDate(final Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public void setReason(final String reason) {
		this.reason = reason;
	}

	public void setRequirements(final String requirements) {
		this.requirements = requirements;
	}

	public void setNote(final Collection<Note> note) {
		this.note = note;
	}

	public void setAudit(final Collection<Audit> audit) {
		this.audit = audit;
	}

	public void setStage(final Collection<Stage> stage) {
		this.stage = stage;
	}

	public void setLegalText(final LegalText legalText) {
		this.legalText = legalText;
	}

	public void setTagValue(final Collection<TagValue> tagValue) {
		this.tagValue = tagValue;
	}

	public void setRanger(final Ranger ranger) {
		this.ranger = ranger;
	}

	public void setManager(final Manager manager) {
		this.manager = manager;
	}

	public void setApplication(final Collection<Application> application) {
		this.application = application;
	}

	public void setSponsorship(final Collection<Sponsorship> sponsorship) {
		this.sponsorship = sponsorship;
	}

	public void setZolets(final Collection<Zolet> zolets) {
		this.zolets = zolets;
	}

}
