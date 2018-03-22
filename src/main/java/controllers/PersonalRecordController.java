
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PersonalRecordService;
import domain.PersonalRecord;

@Controller
@RequestMapping("personalRecord")
public class PersonalRecordController extends AbstractController {

	//Services

	@Autowired
	private PersonalRecordService	personalRecordService;


	//Display

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int personalRecordId) {
		ModelAndView result;
		PersonalRecord personalRecord;

		personalRecord = this.personalRecordService.findOne(personalRecordId);

		result = new ModelAndView("personalRecord/display");
		result.addObject("personalRecord", personalRecord);
		result.addObject("requestURI", "personalRecord/display.do");

		return result;
	}
}
