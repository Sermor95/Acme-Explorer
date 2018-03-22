
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import services.EndorserRecordService;
import domain.Curriculum;
import domain.EndorserRecord;

@Controller
@RequestMapping("endorserRecord")
public class EndorserRecordController extends AbstractController {

	//Services

	@Autowired
	private EndorserRecordService	endorserRecordService;
	@Autowired
	private CurriculumService		curriculumService;


	//Display

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int endorserRecordId) {
		ModelAndView result;
		EndorserRecord endorserRecord;

		endorserRecord = this.endorserRecordService.findOne(endorserRecordId);

		result = new ModelAndView("endorserRecord/display");
		result.addObject("endorserRecord", endorserRecord);
		result.addObject("requestURI", "endorserRecord/display.do");

		return result;
	}
	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int curriculumId) {
		final ModelAndView result;
		Collection<EndorserRecord> endorserRecords;
		Curriculum curriculum;
		curriculum = this.curriculumService.findOne(curriculumId);
		endorserRecords = curriculum.getEndorserRecord();

		result = new ModelAndView("endorserRecord/list");
		result.addObject("endorserRecords", endorserRecords);
		result.addObject("requestURI", "endorserRecord/list.do");

		return result;
	}
}
