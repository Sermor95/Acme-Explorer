
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.EducationRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EducationRecordServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private EducationRecordService	educationRecordService;


	//Tests

	@Test
	public void testCreateEducationRecord() {

		//Setting up the authority to execute services.

		this.authenticate("ranger1");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.

		final EducationRecord educationRecord = this.educationRecordService.create(3026);

		educationRecord.setDiploma("Este es el diploma");
		final Date periodStart = new GregorianCalendar(2010, Calendar.FEBRUARY, 11).getTime();
		final Date periodEnd = new GregorianCalendar(2015, Calendar.MARCH, 11).getTime();

		educationRecord.setPeriodStart(periodStart);
		educationRecord.setPeriodEnd(periodEnd);
		educationRecord.setInstitution("ETSII");
		educationRecord.setAttachment("https://stackoverflow.com");
		educationRecord.setComments("It´s amazing.");

		//Saving entity to database and confirming it exists with findAll().
		final EducationRecord saved = this.educationRecordService.save(educationRecord);

		final Collection<EducationRecord> educationRecords = this.educationRecordService.findAll();
		Assert.isTrue(educationRecords.contains(saved));
	}

	@Test
	public void testListDeleteEducationRecord() {

		//Setting up the authority to execute services.
		this.authenticate("ranger1");

		//We retrieve a list of all contacts, and obtain the Id of one of them.
		Collection<EducationRecord> educationRecords = this.educationRecordService.findAll();
		final int id = educationRecords.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final EducationRecord educationRecord = this.educationRecordService.findOne(id);
		Assert.notNull(educationRecord);

		//Using delete() to delete the entity we retrieved.
		this.educationRecordService.delete(educationRecord);

		//Verifying the entity has been removed from the database.
		educationRecords = this.educationRecordService.findAll();
		Assert.isTrue(!educationRecords.contains(educationRecord));
	}
}
