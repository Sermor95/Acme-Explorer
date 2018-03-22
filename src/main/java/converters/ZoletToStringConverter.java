
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Zolet;

@Component
@Transactional
public class ZoletToStringConverter implements Converter<Zolet, String> {

	@Override
	public String convert(final Zolet a) {
		String result;

		if (a == null)
			result = null;
		else
			result = String.valueOf(a.getId());

		return result;
	}
}
