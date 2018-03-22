
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConfigurationRepository;
import domain.Configuration;
import domain.Location;

@Service
@Transactional
public class ConfigurationService {

	//Managed repository

	@Autowired
	private ConfigurationRepository	configurationRepository;


	//Simple CRUD methods

	public Configuration create() {

		final Configuration c = new Configuration();
		c.setBanner("http://creek-tours.com/wp-content/uploads/Kenya-Tanzania-Family-Safari-banner.jpg");
		c.setCompanyName("Tanzanika");
		c.setCountryCode("+34");
		final Location l = new Location();
		l.setName("Localización por defecto");
		l.setGpsCoordinates("+90, -180");
		c.setDefaultLocation(l);
		c.setVat(21.00);
		c.setSpamWord("viagra,cialis,sex,jes extender");
		c.setWelcomeEN("Tanzanika is an adventure company that makes your explorer's dreams true.");
		c.setWelcomeES("Tanzanika es la empresa de aventuras que hará tus sueños de explorador realidad.");
		c.setCacheHours(1);
		c.setCacheResults(10);

		return c;
	}

	public Collection<Configuration> findAll() {
		return this.configurationRepository.findAll();
	}

	public Configuration findOne(final int id) {
		Assert.notNull(id);

		return this.configurationRepository.findOne(id);
	}

	public Configuration save(final Configuration configuration) {
		Assert.notNull(configuration);

		return this.configurationRepository.save(configuration);
	}

	public void delete(final Configuration configuration) {
		Assert.notNull(configuration);

		this.configurationRepository.delete(configuration);
	}

	//Other methods

	public boolean isSpam(final String s) {
		final Configuration c = this.findAll().iterator().next();
		boolean isSpam = false;

		final String[] words = c.getSpamWord().split("\\,");
		for (final String e : words)
			if (s.contains(e))
				isSpam = true;

		return isSpam;
	}
}
