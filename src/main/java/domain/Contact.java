
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Contact extends DomainEntity {

	//Attributes

	private String		name;
	private String		email;
	private String		phone;

	//Relationships

	private Explorer	explorer;


	//Getters

	@NotBlank
	public String getName() {
		return this.name;
	}

	@Email
	public String getEmail() {
		return this.email;
	}

	public String getPhone() {
		return this.phone;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Explorer getExplorer() {
		return this.explorer;
	}

	//Setters

	public void setName(final String name) {
		this.name = name;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public void setExplorer(final Explorer explorer) {
		this.explorer = explorer;
	}
}
