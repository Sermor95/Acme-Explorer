
package controllers.auditor;

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
import services.AuditService;
import services.TripService;
import controllers.AbstractController;
import domain.Audit;
import domain.Auditor;
import domain.Trip;

@Controller
@RequestMapping("audit/auditor")
public class AuditAuditorController extends AbstractController {

	//Services

	@Autowired
	private AuditService	auditService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private TripService		tripService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Audit> audits;

		audits = ((Auditor) this.actorService.findByPrincipal()).getAudit();

		result = new ModelAndView("audit/list");
		result.addObject("audits", audits);
		result.addObject("requestURI", "audit/auditor/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int varId) {
		final ModelAndView result;
		Audit audit;
		final Trip trip = this.tripService.findOne(varId);

		audit = this.auditService.create();
		audit.setTrip(trip);
		result = this.createEditModelAndView(audit);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int varId) {
		final ModelAndView result;
		final Audit audit = this.auditService.findOne(varId);
		Assert.notNull(audit);
		if (audit.isFinalMode())
			result = new ModelAndView("redirect:/audit/auditor/list.do");
		else
			result = this.createEditModelAndView(audit);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Audit audit, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(audit);
		else
			try {
				this.auditService.save(audit);
				result = new ModelAndView("redirect:/audit/auditor/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(audit, "audit.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Audit audit, final BindingResult binding) {
		ModelAndView result;

		try {
			this.auditService.delete(audit);
			result = new ModelAndView("redirect:/audit/auditor/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(audit, "audit.commit.error");
		}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Audit audit) {
		ModelAndView result;

		result = this.createEditModelAndView(audit, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Audit audit, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("audit/edit");
		result.addObject("audit", audit);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "audit/auditor/edit.do");

		return result;

	}
}
