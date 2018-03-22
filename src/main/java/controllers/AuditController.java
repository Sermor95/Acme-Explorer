
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuditService;
import services.TripService;
import domain.Audit;
import domain.Trip;

@Controller
@RequestMapping("audit")
public class AuditController extends AbstractController {

	//Services

	@Autowired
	private AuditService	auditService;

	@Autowired
	private TripService		tripService;


	//List

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int varId) {
		final ModelAndView result;
		Collection<Audit> audits;

		final Trip trip = this.tripService.findOne(varId);
		audits = trip.getAudit();

		result = new ModelAndView("audit/list");
		result.addObject("audits", audits);
		result.addObject("requestURI", "audit/list.do");

		return result;
	}
	//Display

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int varId) {
		ModelAndView result;
		Audit audit;

		audit = this.auditService.findOne(varId);

		result = new ModelAndView("audit/display");
		result.addObject("audit", audit);
		result.addObject("requestURI", "audit/display.do");

		return result;
	}

}
