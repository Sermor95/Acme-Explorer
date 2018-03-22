
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Note;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class NoteServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private NoteService	noteService;


	//Tests

	@Test
	public void testCreateNote() {
		//Setting up the authority to execute services.
		this.authenticate("auditor2");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.
		final Note note = this.noteService.create();

		note.setRemark("This is a remark.");

		//Saving entity to database and confirming it exists with findAll().
		final Note saved = this.noteService.save(note);

		final Collection<Note> notes = this.noteService.findAll();
		Assert.isTrue(notes.contains(saved));
	}

	//	@Test
	//	public void testListDeleteNote() {
	//		//Setting up the authority to execute services.
	//		this.authenticate("auditor1");
	//
	//		//We retrieve a list of all notes, and obtain the Id of one of them.
	//		Collection<Note> notes = this.noteService.findAll();
	//		final int id = notes.iterator().next().getId();
	//
	//		//Using findOne() to retrieve a particular entity and verifying it.
	//		final Note note = this.noteService.findOne(id);
	//		Assert.notNull(note);
	//
	//		//Using delete() to delete the entity we retrieved.
	//		this.noteService.delete(note);
	//
	//		//Verifying the entity has been removed from the database.
	//		notes = this.noteService.findAll();
	//		Assert.isTrue(!notes.contains(note));
	//	}
}
