
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;
import domain.Trip;

@Service
@Transactional
public class FinderService {

	//Managed repository

	@Autowired
	private FinderRepository	finderRepository;

	//Supporting services

	@Autowired
	private TripService			tripService;


	//Simple CRUD methods

	public Finder create() {

		final Finder f = new Finder();
		f.setKeyWord(null);
		f.setMaxPrice(null);
		f.setMinPrice(null);
		f.setDateStart(null);
		f.setDateEnd(null);

		f.setTrip(new ArrayList<Trip>());

		return f;
	}

	public Finder findOne(final int id) {
		Assert.notNull(id);

		return this.finderRepository.findOne(id);
	}

	public Collection<Finder> findAll() {
		return this.finderRepository.findAll();
	}

	public Finder save(final Finder f) {
		Assert.notNull(f);

		//If all fields of the finder are null, the finder returns the entire listing of available trips.
		if (f.getKeyWord() == null && f.getMinPrice() == null && f.getMaxPrice() == null && f.getDateStart() == null && f.getDateEnd() == null)
			f.setTrip(this.tripService.findAll());

		final Finder saved = this.finderRepository.save(f);

		return saved;
	}

	public void delete(final Finder f) {
		Assert.notNull(f);

		this.finderRepository.delete(f);
	}

	public Collection<Trip> find(final Finder finder) {
		final Collection<Trip> trips = new ArrayList<Trip>();

		for (final Trip t : this.tripService.findAll()) {
			if (!finder.getKeyWord().isEmpty())
				if (t.getTitle().contains(finder.getKeyWord()) || t.getDescription().contains(finder.getKeyWord()) || t.getTicker().contains(finder.getKeyWord()))
					if (!trips.contains(t))
						trips.add(t);
			if (finder.getMinPrice() != null && finder.getMaxPrice() != null)
				if (t.getPrice() > finder.getMinPrice() && t.getPrice() < finder.getMaxPrice()) {
					if (!trips.contains(t))
						trips.add(t);
				} else if (t.getPrice() < finder.getMinPrice() || t.getPrice() > finder.getMaxPrice())
					if (trips.contains(t))
						trips.remove(t);
			if (finder.getDateStart() != null && finder.getDateEnd() != null)
				if (t.getStartDate().after(finder.getDateStart()) && t.getEndDate().before(finder.getDateEnd())) {
					if (!trips.contains(t))
						trips.add(t);
				} else if (t.getStartDate().before(finder.getDateStart()) || t.getEndDate().after(finder.getDateEnd()))
					if (trips.contains(t))
						trips.remove(t);
			if (!finder.getKeyWord().isEmpty())
				if (!(t.getTitle().contains(finder.getKeyWord()) || t.getDescription().contains(finder.getKeyWord()) || t.getTicker().contains(finder.getKeyWord())))
					if (trips.contains(t))
						trips.remove(t);
		}

		finder.setTrip(trips);
		return finder.getTrip();
	}
}
