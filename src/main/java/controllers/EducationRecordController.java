
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import services.EducationRecordService;
import domain.Curriculum;
import domain.EducationRecord;

@Controller
@RequestMapping("educationRecord")
public class EducationRecordController extends AbstractController {

	//Services

	@Autowired
	private EducationRecordService	educationRecordService;
	@Autowired
	private CurriculumService		curriculumService;


	//Display

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int educationRecordId) {
		ModelAndView result;
		EducationRecord educationRecord;

		educationRecord = this.educationRecordService.findOne(educationRecordId);

		result = new ModelAndView("educationRecord/display");
		result.addObject("educationRecord", educationRecord);
		result.addObject("requestURI", "educationRecord/display.do");

		return result;
	}
	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int curriculumId) {
		final ModelAndView result;
		Collection<EducationRecord> educationRecords;
		Curriculum curriculum;
		curriculum = this.curriculumService.findOne(curriculumId);
		educationRecords = curriculum.getEducationRecord();

		result = new ModelAndView("educationRecord/list");
		result.addObject("educationRecords", educationRecords);
		result.addObject("requestURI", "educationRecord/list.do");

		return result;
	}
}
