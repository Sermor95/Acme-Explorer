
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.RangerRepository;
import domain.Ranger;

@Component
@Transactional
public class StringToRangerConverter implements Converter<String, Ranger> {

	@Autowired
	RangerRepository	rangerRepository;


	@Override
	public Ranger convert(final String s) {
		Ranger result;
		int id;

		try {
			if (StringUtils.isEmpty(s))
				result = null;
			else {
				id = Integer.valueOf(s);
				result = this.rangerRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
