
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
import services.EndorserRecordService;
import controllers.AbstractController;
import domain.EndorserRecord;
import domain.Ranger;

@Controller
@RequestMapping("endorserRecord/ranger")
public class EndorserRecordRangerController extends AbstractController {

	//Services

	@Autowired
	private EndorserRecordService	endorserRecordService;

	@Autowired
	private ActorService			actorService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<EndorserRecord> endorserRecords;

		endorserRecords = this.endorserRecordService.findAll();

		result = new ModelAndView("endorserRecord/list");
		result.addObject("endorserRecords", endorserRecords);
		result.addObject("requestURI", "endorserRecord/ranger/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		EndorserRecord endorserRecord;
		final Ranger ranger = (Ranger) this.actorService.findByPrincipal();
		endorserRecord = this.endorserRecordService.create(ranger.getCurriculum().getId());
		result = this.createEditModelAndView(endorserRecord);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int endorserRecordId) {
		final ModelAndView result;
		EndorserRecord endorserRecord;

		endorserRecord = this.endorserRecordService.findOne(endorserRecordId);
		Assert.notNull(endorserRecord);
		result = this.createEditModelAndView(endorserRecord);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final EndorserRecord endorserRecord, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(endorserRecord);
		else
			try {
				this.endorserRecordService.save(endorserRecord);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(endorserRecord, "endorserRecord.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final EndorserRecord endorserRecord, final BindingResult binding) {
		ModelAndView result;

		try {
			this.endorserRecordService.delete(endorserRecord);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(endorserRecord, "endorserRecord.commit.error");
		}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final EndorserRecord endorserRecord) {
		ModelAndView result;

		result = this.createEditModelAndView(endorserRecord, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final EndorserRecord endorserRecord, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("endorserRecord/edit");
		result.addObject("endorserRecord", endorserRecord);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "endorserRecord/ranger/edit.do");

		return result;

	}
}
