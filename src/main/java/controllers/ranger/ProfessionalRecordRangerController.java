
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
import services.ProfessionalRecordService;
import controllers.AbstractController;
import domain.ProfessionalRecord;
import domain.Ranger;

@Controller
@RequestMapping("professionalRecord/ranger")
public class ProfessionalRecordRangerController extends AbstractController {

	//Services

	@Autowired
	private ProfessionalRecordService	professionalRecordService;

	@Autowired
	private ActorService				actorService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<ProfessionalRecord> professionalRecords;

		professionalRecords = this.professionalRecordService.findAll();

		result = new ModelAndView("professionalRecord/list");
		result.addObject("professionalRecords", professionalRecords);
		result.addObject("requestURI", "professionalRecord/ranger/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		ProfessionalRecord professionalRecord;
		final Ranger ranger = (Ranger) this.actorService.findByPrincipal();
		professionalRecord = this.professionalRecordService.create(ranger.getCurriculum().getId());
		result = this.createEditModelAndView(professionalRecord);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int professionalRecordId) {
		final ModelAndView result;
		ProfessionalRecord professionalRecord;

		professionalRecord = this.professionalRecordService.findOne(professionalRecordId);
		Assert.notNull(professionalRecord);
		result = this.createEditModelAndView(professionalRecord);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ProfessionalRecord professionalRecord, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(professionalRecord);
		else
			try {
				this.professionalRecordService.save(professionalRecord);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(professionalRecord, "professionalRecord.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final ProfessionalRecord professionalRecord, final BindingResult binding) {
		ModelAndView result;

		try {
			this.professionalRecordService.delete(professionalRecord);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(professionalRecord, "professionalRecord.commit.error");
		}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final ProfessionalRecord professionalRecord) {
		ModelAndView result;

		result = this.createEditModelAndView(professionalRecord, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ProfessionalRecord professionalRecord, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("professionalRecord/edit");
		result.addObject("professionalRecord", professionalRecord);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "professionalRecord/ranger/edit.do");

		return result;

	}
}
