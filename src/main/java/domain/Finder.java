
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	//Attributes

	private String				keyWord;
	private Double				minPrice;
	private Double				maxPrice;
	private Date				dateStart;
	private Date				dateEnd;

	//Relationships

	private Collection<Trip>	trip;


	//Getters

	public String getKeyWord() {
		return this.keyWord;
	}

	public Double getMinPrice() {
		return this.minPrice;
	}

	public Double getMaxPrice() {
		return this.maxPrice;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getDateStart() {
		return this.dateStart;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getDateEnd() {
		return this.dateEnd;
	}

	@Valid
	@NotNull
	@ManyToMany
	public Collection<Trip> getTrip() {
		return this.trip;
	}

	//Setters

	public void setKeyWord(final String keyWord) {
		this.keyWord = keyWord;
	}

	public void setMinPrice(final Double minPrice) {
		this.minPrice = minPrice;
	}

	public void setMaxPrice(final Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public void setDateStart(final Date dateStart) {
		this.dateStart = dateStart;
	}

	public void setDateEnd(final Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public void setTrip(final Collection<Trip> trip) {
		this.trip = trip;
	}

}
