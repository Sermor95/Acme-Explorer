
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
import domain.ProfessionalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ProfessionalRecordServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private ProfessionalRecordService	professionalRecordService;


	//Tests

	@Test
	public void testCreateListProfessionalRecord() {
		//Setting up the authority to execute services.
		this.authenticate("ranger1");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.
		final ProfessionalRecord professionalRecord = this.professionalRecordService.create(3026);

		professionalRecord.setCompany("Test S.A");

		final Date periodStart = new GregorianCalendar(2015, Calendar.DECEMBER, 15).getTime();
		final Date periodEnd = new GregorianCalendar(2017, Calendar.JANUARY, 20).getTime();

		professionalRecord.setPeriodStart(periodStart);
		professionalRecord.setPeriodEnd(periodEnd);
		professionalRecord.setRole("personalRecord");
		professionalRecord.setAttachment("https://www.att.com/");
		professionalRecord.setComments("personalRecord");

		//Saving entity to database and confirming it exists with findAll().
		final ProfessionalRecord saved = this.professionalRecordService.save(professionalRecord);

		final Collection<ProfessionalRecord> professionalRecords = this.professionalRecordService.findAll();
		Assert.isTrue(professionalRecords.contains(saved));
	}

	@Test
	public void testListDeleteProfessionalRecord() {
		//Setting up the authority to execute services.
		this.authenticate("ranger1");

		//We retrieve a list of all PersonalRecords, and obtain the Id of one of them.
		Collection<ProfessionalRecord> professionalRecords = this.professionalRecordService.findAll();
		final int id = professionalRecords.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final ProfessionalRecord professionalRecord = this.professionalRecordService.findOne(id);
		Assert.notNull(professionalRecord);

		//Using delete() to delete the entity we retrieved.
		this.professionalRecordService.delete(professionalRecord);

		//Verifying the entity has been removed from the database.
		professionalRecords = this.professionalRecordService.findAll();
		Assert.isTrue(!professionalRecords.contains(professionalRecord));
	}
}
