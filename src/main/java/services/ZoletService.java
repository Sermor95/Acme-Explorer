
package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ZoletRepository;
import domain.Manager;
import domain.Zolet;

@Service
@Transactional
public class ZoletService {

	//Managed repository ---------------------------------

	@Autowired
	private ZoletRepository	zoletRepository;

	//Supporting services --------------------------------

	@Autowired
	private ActorService	actorService;


	//Simple CRUD methods --------------------------------

	public Zolet create() {

		final Zolet zolet = new Zolet();

		zolet.setCode(this.generateCode());
		final Manager m = (Manager) this.actorService.findByPrincipal();
		zolet.setManager(m);
		return zolet;
	}

	public Collection<Zolet> findAll() {
		return this.zoletRepository.findAll();
	}

	public Zolet findOne(final int id) {
		Assert.notNull(id);
		return this.zoletRepository.findOne(id);
	}

	public Zolet save(final Zolet zolet) {
		Assert.notNull(zolet);

		Assert.isTrue(zolet.getMoment() == null);

		Assert.isTrue(zolet.getGauge() >= 1 && zolet.getGauge() <= 3);
		//Assertion that the user modifying this audit has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == zolet.getManager().getId());
		Assert.notNull(zolet.getTrip());
		return this.zoletRepository.save(zolet);
	}

	public void delete(final Zolet zolet) {
		Assert.notNull(zolet);

		//Assertion that the user deleting this audit has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == zolet.getManager().getId());

		this.zoletRepository.delete(zolet);
	}

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
		final int longitud = 2;
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

	//Generates both halves of the unique code and joins them with a dash.
	public String generateCode() {
		final String res = this.generateString() + this.generateNumber() + this.generateString();
		return res;
	}

	//Other methods

	//Query 1
	public Double ratioTripsWithAtLeastOneZolet() {
		return this.zoletRepository.ratioTripsWithAtLeastOneZolet();
	}

	//Query 2
	public Manager findManagerWithMoreZolets() {
		return this.zoletRepository.findManagerWithMoreZolets();
	}
}
