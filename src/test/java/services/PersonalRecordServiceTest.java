
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
import domain.PersonalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class PersonalRecordServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private PersonalRecordService	personalRecordService;


	//Tests

	@Test
	public void testCreateListPersonalRecord() {
		//Setting up the authority to execute services.
		this.authenticate("ranger1");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.
		final PersonalRecord personalRecord = this.personalRecordService.create(3026);

		personalRecord.setName("personalRecord");
		personalRecord.setPhoto("http://jpg.com");
		personalRecord.setEmail("test@gmail.com");
		personalRecord.setPhone("654213243");
		personalRecord.setProfile("http://prof.com");

		//Saving entity to database and confirming it exists with findAll()		
		final PersonalRecord saved = this.personalRecordService.save(personalRecord);
		final PersonalRecord bbdd = this.personalRecordService.findOne(saved.getId());
		Assert.notNull(bbdd);
	}
	@Test
	public void testListDeletePersonalRecord() {
		//Setting up the authority to execute services.
		this.authenticate("ranger1");

		//We retrieve a list of all PersonalRecords, and obtain the Id of one of them.
		final Collection<PersonalRecord> personalRecords = this.personalRecordService.findAll();
		final int id = personalRecords.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final PersonalRecord personalRecord = this.personalRecordService.findOne(id);
		Assert.notNull(personalRecord);

		//Using delete() to delete the entity we retrieved.
		this.personalRecordService.delete(personalRecord);

		//Verifying the entity has been removed from the database.

		final PersonalRecord bbdd = this.personalRecordService.findOne(personalRecord.getId());
		Assert.isNull(bbdd);
	}
}
