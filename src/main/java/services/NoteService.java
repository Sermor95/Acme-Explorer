
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import domain.Auditor;
import domain.Manager;
import domain.Note;
import domain.Trip;

@Service
@Transactional
public class NoteService {

	//Managed repository --------------------------------

	@Autowired
	private NoteRepository	noteRepository;

	//Supporting services --------------------------------

	@Autowired
	private ActorService	actorService;

	@Autowired
	private TripService		tripService;


	//Simple CRUD methods --------------------------------

	public Note create() {

		final Note note = new Note();
		note.setMoment(new Date(System.currentTimeMillis() - 1));

		final Auditor auditor = (Auditor) this.actorService.findByPrincipal();
		note.setAuditor(auditor);
		note.setTrip(this.tripService.findAll().iterator().next());

		return note;
	}

	public Collection<Note> findAll() {
		return this.noteRepository.findAll();
	}

	public Note findOne(final int id) {
		Assert.notNull(id);

		return this.noteRepository.findOne(id);
	}

	public Note save(final Note note) {
		Assert.notNull(note);
		final Manager manager = note.getTrip().getManager();

		//Assertion that the user modifying this note has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == note.getAuditor().getId() || this.actorService.findByPrincipal().getId() == manager.getId());

		if (note.getReply() != null)
			note.setReplyMoment(new Date(System.currentTimeMillis() - 1));

		return this.noteRepository.save(note);
	}

	//Notes cannot be deleted in the system, this method exists for testing purposes only.
	public void delete(final Note note) {
		Assert.notNull(note);

		//Assertion that the user deleting this note has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == note.getAuditor().getId());

		this.noteRepository.delete(note);
	}

	public Collection<Note> notesOfTrips(final Manager m) {
		final Collection<Note> result = new ArrayList<Note>();
		final Collection<Trip> trips = this.tripService.findAll();

		for (final Trip t : trips)
			if (t.getManager().equals(m))
				if (!t.getNote().isEmpty())
					result.addAll(t.getNote());

		return result;
	}
}
