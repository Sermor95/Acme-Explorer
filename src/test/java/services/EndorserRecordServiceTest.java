
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
import domain.EndorserRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EndorserRecordServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private EndorserRecordService	endorserRecordService;


	//Tests

	@Test
	public void testCreateEndorserRecord() {

		//Setting up the authority to execute services.

		this.authenticate("ranger1");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.

		final EndorserRecord endorserRecord = this.endorserRecordService.create(3026);

		endorserRecord.setName("Antoine");
		endorserRecord.setEmail("antcarmar@us.es");
		endorserRecord.setPhone("648459574");
		endorserRecord.setProfile("https://stackoverflow.com");
		endorserRecord.setComments("My friend Antonio is a noob.");

		//Saving entity to database and confirming it exists with findAll().
		final EndorserRecord saved = this.endorserRecordService.save(endorserRecord);

		final Collection<EndorserRecord> endorserRecords = this.endorserRecordService.findAll();
		Assert.isTrue(endorserRecords.contains(saved));
	}

	@Test
	public void testListDeleteEndorserRecord() {

		//Setting up the authority to execute services.
		this.authenticate("ranger1");

		//We retrieve a list of all contacts, and obtain the Id of one of them.
		Collection<EndorserRecord> endorserRecords = this.endorserRecordService.findAll();
		final int id = endorserRecords.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final EndorserRecord endorserRecord = this.endorserRecordService.findOne(id);
		Assert.notNull(endorserRecord);

		//Using delete() to delete the entity we retrieved.
		this.endorserRecordService.delete(endorserRecord);

		//Verifying the entity has been removed from the database.
		endorserRecords = this.endorserRecordService.findAll();
		Assert.isTrue(!endorserRecords.contains(endorserRecord));
	}
}
