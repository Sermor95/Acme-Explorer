
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
public class Auditor extends Actor {

	//Relationships

	private Collection<Note>	note;
	private Collection<Audit>	audit;


	//Getters

	@Valid
	@NotNull
	@OneToMany(mappedBy = "auditor")
	public Collection<Note> getNote() {
		return this.note;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "auditor")
	public Collection<Audit> getAudit() {
		return this.audit;
	}

	//Setters

	public void setNote(final Collection<Note> note) {
		this.note = note;
	}

	public void setAudit(final Collection<Audit> audit) {
		this.audit = audit;
	}

}
