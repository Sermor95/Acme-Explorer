
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TagValueRepository;
import domain.TagValue;
import domain.Trip;

@Service
@Transactional
public class TagValueService {

	//ManagedRepository

	@Autowired
	private TagValueRepository	valueRepository;

	//Supporting services

	@Autowired
	private TagService			tagService;

	@Autowired
	private TripService			tripService;


	//Simple CRUD methods

	public TagValue create(final int tagId) {
		final TagValue t = new TagValue();

		t.setTrip(new ArrayList<Trip>());
		t.setTag(this.tagService.findOne(tagId));

		return t;
	}

	public TagValue findOne(final int id) {
		Assert.notNull(id);

		return this.valueRepository.findOne(id);
	}

	public Collection<TagValue> findAll() {
		return this.valueRepository.findAll();
	}

	public TagValue save(final TagValue t) {
		Assert.notNull(t);

		return this.valueRepository.save(t);
	}

	public void delete(final TagValue tv) {
		Assert.notNull(tv);

		for (final Trip t : tv.getTrip()) {
			final Collection<TagValue> tvs = t.getTagValue();
			tvs.remove(tv);
			t.setTagValue(tvs);
			this.tripService.saveTag(t);
		}

		this.valueRepository.delete(tv);
	}
}
