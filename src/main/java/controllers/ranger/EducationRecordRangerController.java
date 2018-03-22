
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

import services.ActorService;
import services.EducationRecordService;
import controllers.AbstractController;
import domain.EducationRecord;
import domain.Ranger;

@Controller
@RequestMapping("educationRecord/ranger")
public class EducationRecordRangerController extends AbstractController {

	//Services

	@Autowired
	private EducationRecordService	educationRecordService;

	@Autowired
	private ActorService			actorService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<EducationRecord> educationRecords;

		educationRecords = this.educationRecordService.findAll();

		result = new ModelAndView("educationRecord/list");
		result.addObject("educationRecords", educationRecords);
		result.addObject("requestURI", "educationRecord/ranger/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		EducationRecord educationRecord;
		final Ranger ranger = (Ranger) this.actorService.findByPrincipal();
		educationRecord = this.educationRecordService.create(ranger.getCurriculum().getId());
		System.out.println("curriculum: " + educationRecord.getCurriculum());
		result = this.createEditModelAndView(educationRecord);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int educationRecordId) {
		final ModelAndView result;
		EducationRecord educationRecord;

		educationRecord = this.educationRecordService.findOne(educationRecordId);
		Assert.notNull(educationRecord);
		result = this.createEditModelAndView(educationRecord);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final EducationRecord educationRecord, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println("Error: " + binding.getAllErrors());
			result = this.createEditModelAndView(educationRecord);
		} else
			try {
				this.educationRecordService.save(educationRecord);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(educationRecord, "educationRecord.commit.error");
			}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final EducationRecord educationRecord, final BindingResult binding) {
		ModelAndView result;

		try {
			this.educationRecordService.delete(educationRecord);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(educationRecord, "educationRecord.commit.error");
		}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final EducationRecord educationRecord) {
		ModelAndView result;

		result = this.createEditModelAndView(educationRecord, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final EducationRecord educationRecord, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("educationRecord/edit");
		result.addObject("educationRecord", educationRecord);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "educationRecord/ranger/edit.do");

		return result;

	}
}
