
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.SurvivalClassRepository;
import domain.SurvivalClass;

@Component
@Transactional
public class StringToSurvivalClassConverter implements Converter<String, SurvivalClass> {

	@Autowired
	SurvivalClassRepository	survivalClassRepository;


	@Override
	public SurvivalClass convert(final String s) {
		SurvivalClass result;
		int id;

		try {
			if (StringUtils.isEmpty(s))
				result = null;
			else {
				id = Integer.valueOf(s);
				result = this.survivalClassRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
