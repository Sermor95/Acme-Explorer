
package controllers.ranger;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PersonalRecordService;
import controllers.AbstractController;
import domain.PersonalRecord;

@Controller
@RequestMapping("personalRecord/ranger")
public class PersonalRecordRangerController extends AbstractController {

	//Services

	@Autowired
	private PersonalRecordService	personalRecordService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<PersonalRecord> personalRecords;

		personalRecords = this.personalRecordService.findAll();

		result = new ModelAndView("personalRecord/list");
		result.addObject("personalRecords", personalRecords);
		result.addObject("requestURI", "personalRecord/ranger/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		PersonalRecord personalRecord;

		personalRecord = this.personalRecordService.create(0);
		result = this.createEditModelAndView(personalRecord);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int personalRecordId) {
		final ModelAndView result;
		PersonalRecord personalRecord;

		personalRecord = this.personalRecordService.findOne(personalRecordId);
		Assert.notNull(personalRecord);
		result = this.createEditModelAndView(personalRecord);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final PersonalRecord personalRecord, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(personalRecord);
		else
			try {
				this.personalRecordService.save(personalRecord);
				result = new ModelAndView("redirect:/curriculum/ranger/display.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(personalRecord, "personalRecord.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final PersonalRecord personalRecord, final BindingResult binding) {
		ModelAndView result;

		try {
			this.personalRecordService.delete(personalRecord);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(personalRecord, "personalRecord.commit.error");
		}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final PersonalRecord personalRecord) {
		ModelAndView result;

		result = this.createEditModelAndView(personalRecord, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final PersonalRecord personalRecord, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("personalRecord/edit");
		result.addObject("personalRecord", personalRecord);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "personalRecord/ranger/edit.do");

		return result;

	}
}
