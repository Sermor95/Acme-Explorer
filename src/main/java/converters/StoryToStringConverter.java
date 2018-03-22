
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Story;

@Component
@Transactional
public class StoryToStringConverter implements Converter<Story, String> {

	@Override
	public String convert(final Story s) {
		String result;

		if (s == null)
			result = null;
		else
			result = String.valueOf(s.getId());

		return result;
	}
}
