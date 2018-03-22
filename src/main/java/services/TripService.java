
package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TripRepository;
import domain.Application;
import domain.Audit;
import domain.Manager;
import domain.Note;
import domain.Sponsorship;
import domain.Stage;
import domain.TagValue;
import domain.Trip;
import domain.Zolet;

@Service
@Transactional
public class TripService {

	//Managed repository

	@Autowired
	private TripRepository			tripRepository;

	//Supporting services

	@Autowired
	private LegalTextService		legalTextService;

	@Autowired
	private RangerService			rangerService;

	@Autowired
	private ConfigurationService	configurationService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private TagValueService			tagValueService;


	//Simple CRUD methods

	public Trip create() {
		final Trip t = new Trip();

		t.setTicker(this.generateTicker());

		t.setLegalText(this.legalTextService.findAll().iterator().next());
		t.setNote(new ArrayList<Note>());
		t.setAudit(new ArrayList<Audit>());
		t.setStage(new ArrayList<Stage>());
		t.setTagValue(new ArrayList<TagValue>());
		t.setRanger(this.rangerService.findAll().iterator().next());
		final Manager m = (Manager) this.actorService.findByPrincipal();
		t.setManager(m);
		t.setApplication(new ArrayList<Application>());
		t.setSponsorship(new ArrayList<Sponsorship>());
		t.setZolets(new ArrayList<Zolet>());

		return t;
	}

	public Trip findOne(final int id) {
		Assert.notNull(id);

		return this.tripRepository.findOne(id);
	}

	public Collection<Trip> findAll() {
		return this.tripRepository.findAll();
	}

	public Trip save(final Trip t) {
		Assert.notNull(t);

		//Generation of random ticker and computation of price based on stages.
		t.setPrice(this.computePrice(t));

		//Business rule: startDate must be later than publicationDate, and earlier than endDate.
		Assert.isTrue(t.getStartDate().after(t.getPublicationDate()));
		Assert.isTrue(t.getEndDate().after(t.getStartDate()));

		//Business rule: a trip cannot be modified after its publication date.
		Assert.isTrue(t.getPublicationDate().after(new Date(System.currentTimeMillis())));

		//Business rule: a trip can only reference a legal text in final mode.
		Assert.isTrue(t.getLegalText().isFinalMode());

		//Assertion that the user modifying this trip has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == t.getManager().getId());

		final Trip saved = this.tripRepository.save(t);

		return saved;
	}

	//Special save method for tags, which can be deleted at any time.
	public Trip saveTag(final Trip t) {
		Assert.notNull(t);

		final Trip saved = this.tripRepository.save(t);

		return saved;
	}

	public void delete(final Trip t) {
		Assert.notNull(t);

		//Business rule: a trip cannot be deleted after its publication date.
		Assert.isTrue(t.getPublicationDate().after(new Date(System.currentTimeMillis())));

		//Assertion that the user deleting this trip has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == t.getManager().getId());

		this.tripRepository.delete(t);
	}

	public Trip cancel(final Trip t) {
		Assert.notNull(t);

		//Business rule: a trip can only be cancelled when it has been published and it hasn't started.
		final Date now = new Date(System.currentTimeMillis());
		Assert.isTrue(t.getPublicationDate().before(now) && t.getStartDate().after(now));

		//Assertion that the user cancelling this trip has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == t.getManager().getId());

		final Trip saved = this.tripRepository.save(t);

		return saved;
	}

	//Other methods

	public double computePrice(final Trip t) {
		double price = 0.0;
		final Collection<Stage> stages = t.getStage();

		//Not necessary to solve with a query, as the stages are already stored in memory.
		if (!stages.isEmpty()) {
			for (final Stage s : stages) {
				System.out.println(s.getPrice());
				price += s.getPrice();
			}
			price += (price * (this.configurationService.findAll().iterator().next().getVat() / 100.0));
		}

		return price;
	}

	//Generates the first half of the unique tickers.
	private String generateNumber() {
		final Date date = new Date();
		final DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
		final String convertido = fecha.format(date);

		final String[] campos = convertido.split("/");
		final String año = campos[0].trim().substring(2, 4);
		final String mes = campos[1].trim();
		final String dia = campos[2].trim();

		final String res = año + mes + dia;
		return res;
	}

	//Generates the second half of the unique tickers.
	private String generateString() {
		String cadenaAleatoria = "";
		final int longitud = 4;
		final Random r = new Random();
		int i = 0;
		while (i < longitud) {
			final int rnd = r.nextInt(255);
			final char c = (char) (rnd);
			if ((c >= 'A' && c <= 'z' && Character.isLetter(c))) {
				cadenaAleatoria += c;
				i++;
			}
		}
		return cadenaAleatoria;
	}

	//Generates both halves of the unique ticker and joins them with a dash.
	public String generateTicker() {
		final String res = this.generateNumber() + "-" + this.generateString();
		return res;
	}

	//Selects an sponsorship at random, if any.
	public Sponsorship selectRandomSponsorship(final int id) {
		final Collection<Sponsorship> sponsorships = this.findOne(id).getSponsorship();
		if (sponsorships.isEmpty())
			return null;
		else {
			final Random rnd = new Random();
			final int i = rnd.nextInt(sponsorships.size());
			return (Sponsorship) sponsorships.toArray()[i];
		}
	}

	//Finds all trips that contain a keyword in their ticker, title or description.
	public Collection<Trip> findByKeyword(final String word) {
		final Collection<Trip> res = new ArrayList<Trip>();

		for (final Trip t : this.findAll())
			if (t.getTicker().contains(word) || t.getTitle().contains(word) || t.getDescription().contains(word))
				res.add(t);
		return res;
	}

	public Collection<Trip> findByTagValue(final int tagValueId) {
		final Collection<Trip> res = new ArrayList<Trip>();
		final TagValue tagValue = this.tagValueService.findOne(tagValueId);
		for (final Trip t : this.findAll())
			if (t.getTagValue().contains(tagValue))
				res.add(t);
		return res;
	}

	//The average, the minimum, the maximum, and the standard deviation of the number of applications per trip.
	public Double[] minMaxAvgStddevApplicationsPerTrip() {
		return this.tripRepository.minMaxAvgStddevApplicationsPerTrip();
	}

	//The average, the minimum, the maximum, and the standard deviation of the price of the trips.
	public Double[] minMaxAvgStddevPricePerTrip() {
		return this.tripRepository.minMaxAvgStddevPricePerTrip();
	}

	//The ratio of trips that have been cancelled versus the total number of trips that have been organised.
	public Double ratioTripsCancelled() {
		return this.tripRepository.ratioTripsCancelled();
	}

	//The listing of trips that have got at least 10% more applications than the average, ordered by number of applications.
	public Collection<Trip> tripsWithAboveAverageApplications() {
		return this.tripRepository.tripsWithAboveAverageApplications();
	}

	//The minimum, the maximum, the average, and the standard deviation of the number of notes per trip.
	public Double[] minMaxAvgStddevNotesPerTrip() {
		return this.tripRepository.minMaxAvgStddevNotesPerTrip();
	}

	//The minimum, the maximum, the average, and the standard deviation of the number of audit records per trip.
	public Double[] minMaxAvgStddevAuditsPerTrip() {
		return this.tripRepository.minMaxAvgStddevAuditsPerTrip();
	}

	//The ratio of trips with an audit record.
	public Double ratioTripsWithAudit() {
		return this.tripRepository.ratioTripsWithAudit();
	}
}
