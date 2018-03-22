
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import services.MiscellaneousRecordService;
import domain.Curriculum;
import domain.MiscellaneousRecord;

@Controller
@RequestMapping("miscellaneousRecord")
public class MiscellaneousRecordController extends AbstractController {

	//Services

	@Autowired
	private MiscellaneousRecordService	miscellaneousRecordService;
	@Autowired
	private CurriculumService			curriculumService;


	//Display

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int miscellaneousRecordId) {
		ModelAndView result;
		MiscellaneousRecord miscellaneousRecord;

		miscellaneousRecord = this.miscellaneousRecordService.findOne(miscellaneousRecordId);

		result = new ModelAndView("miscellaneousRecord/display");
		result.addObject("miscellaneousRecord", miscellaneousRecord);
		result.addObject("requestURI", "miscellaneousRecord/display.do");

		return result;
	}
	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int curriculumId) {
		final ModelAndView result;
		Collection<MiscellaneousRecord> miscellaneousRecords;
		Curriculum curriculum;
		curriculum = this.curriculumService.findOne(curriculumId);
		miscellaneousRecords = curriculum.getMiscellaneousRecord();

		result = new ModelAndView("miscellaneousRecord/list");
		result.addObject("miscellaneousRecords", miscellaneousRecords);
		result.addObject("requestURI", "miscellaneousRecord/list.do");

		return result;
	}
}
