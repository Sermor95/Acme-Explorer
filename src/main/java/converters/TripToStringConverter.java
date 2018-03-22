
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Trip;

@Component
@Transactional
public class TripToStringConverter implements Converter<Trip, String> {

	@Override
	public String convert(final Trip t) {
		String result;

		if (t == null)
			result = null;
		else
			result = String.valueOf(t.getId());

		return result;
	}
}
