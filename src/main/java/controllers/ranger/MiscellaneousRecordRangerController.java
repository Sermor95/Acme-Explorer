
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
import services.MiscellaneousRecordService;
import controllers.AbstractController;
import domain.MiscellaneousRecord;
import domain.Ranger;

@Controller
@RequestMapping("miscellaneousRecord/ranger")
public class MiscellaneousRecordRangerController extends AbstractController {

	//Services

	@Autowired
	private MiscellaneousRecordService	miscellaneousRecordService;

	@Autowired
	private ActorService				actorService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<MiscellaneousRecord> miscellaneousRecords;

		miscellaneousRecords = this.miscellaneousRecordService.findAll();

		result = new ModelAndView("miscellaneousRecord/list");
		result.addObject("miscellaneousRecords", miscellaneousRecords);
		result.addObject("requestURI", "miscellaneousRecord/ranger/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		MiscellaneousRecord miscellaneousRecord;
		final Ranger ranger = (Ranger) this.actorService.findByPrincipal();
		miscellaneousRecord = this.miscellaneousRecordService.create(ranger.getCurriculum().getId());
		result = this.createEditModelAndView(miscellaneousRecord);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int miscellaneousRecordId) {
		final ModelAndView result;
		MiscellaneousRecord miscellaneousRecord;

		miscellaneousRecord = this.miscellaneousRecordService.findOne(miscellaneousRecordId);
		Assert.notNull(miscellaneousRecord);
		result = this.createEditModelAndView(miscellaneousRecord);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final MiscellaneousRecord miscellaneousRecord, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(miscellaneousRecord);
		else
			try {
				this.miscellaneousRecordService.save(miscellaneousRecord);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(miscellaneousRecord, "miscellaneousRecord.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final MiscellaneousRecord miscellaneousRecord, final BindingResult binding) {
		ModelAndView result;

		try {
			this.miscellaneousRecordService.delete(miscellaneousRecord);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(miscellaneousRecord, "miscellaneousRecord.commit.error");
		}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final MiscellaneousRecord miscellaneousRecord) {
		ModelAndView result;

		result = this.createEditModelAndView(miscellaneousRecord, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final MiscellaneousRecord miscellaneousRecord, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("miscellaneousRecord/edit");
		result.addObject("miscellaneousRecord", miscellaneousRecord);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "miscellaneousRecord/ranger/edit.do");

		return result;

	}
}
