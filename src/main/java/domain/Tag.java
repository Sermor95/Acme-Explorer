
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Tag extends DomainEntity {

	//Attributes

	private String					name;

	//Relationships

	private Collection<TagValue>	tagValue;


	//Getters

	@NotBlank
	public String getName() {
		return this.name;
	}

	@NotNull
	@Valid
	@OneToMany(mappedBy = "tag")
	public Collection<TagValue> getTagValue() {
		return this.tagValue;
	}

	//Setters

	public void setName(final String name) {
		this.name = name;
	}

	public void setTagValue(final Collection<TagValue> tagValue) {
		this.tagValue = tagValue;
	}

}
