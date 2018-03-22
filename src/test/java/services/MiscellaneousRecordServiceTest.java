
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
import domain.MiscellaneousRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MiscellaneousRecordServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private MiscellaneousRecordService	miscellaneousRecordService;


	//Tests

	@Test
	public void testCreateMiscellaneousRecord() {
		//Setting up the authority to execute services.
		this.authenticate("ranger1");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.
		final MiscellaneousRecord miscellaneousRecord = this.miscellaneousRecordService.create(3026);

		miscellaneousRecord.setTitle("testTitle");
		miscellaneousRecord.setLink("http://link.com");
		miscellaneousRecord.setComments("");

		//Saving entity to database and confirming it exists with findAll().
		final MiscellaneousRecord saved = this.miscellaneousRecordService.save(miscellaneousRecord);

		final Collection<MiscellaneousRecord> miscellaneousRecords = this.miscellaneousRecordService.findAll();
		Assert.isTrue(miscellaneousRecords.contains(saved));
	}

	@Test
	public void testListDeleteMiscellaneousRecord() {
		//Setting up the authority to execute services.
		this.authenticate("ranger1");

		//We retrieve a list of all MiscellaneousRecords, and obtain the Id of one of them.
		Collection<MiscellaneousRecord> miscellaneousRecords = this.miscellaneousRecordService.findAll();
		final int id = miscellaneousRecords.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final MiscellaneousRecord miscellaneousRecord = this.miscellaneousRecordService.findOne(id);
		Assert.notNull(miscellaneousRecord);

		//Using delete() to delete the entity we retrieved.
		this.miscellaneousRecordService.delete(miscellaneousRecord);

		//Verifying the entity has been removed from the database.
		miscellaneousRecords = this.miscellaneousRecordService.findAll();
		Assert.isTrue(!miscellaneousRecords.contains(miscellaneousRecord));
	}
}
