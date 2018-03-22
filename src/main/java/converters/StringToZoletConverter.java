
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ZoletRepository;
import domain.Zolet;

@Component
@Transactional
public class StringToZoletConverter implements Converter<String, Zolet> {

	@Autowired
	ZoletRepository	zoletRepository;


	@Override
	public Zolet convert(final String s) {
		Zolet result;
		int id;

		try {
			if (StringUtils.isEmpty(s))
				result = null;
			else {
				id = Integer.valueOf(s);
				result = this.zoletRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
