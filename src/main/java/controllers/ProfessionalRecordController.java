
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import services.ProfessionalRecordService;
import domain.Curriculum;
import domain.ProfessionalRecord;

@Controller
@RequestMapping("professionalRecord")
public class ProfessionalRecordController extends AbstractController {

	//Services

	@Autowired
	private ProfessionalRecordService	professionalRecordService;
	@Autowired
	private CurriculumService			curriculumService;


	//Display

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int professionalRecordId) {
		ModelAndView result;
		ProfessionalRecord professionalRecord;

		professionalRecord = this.professionalRecordService.findOne(professionalRecordId);

		result = new ModelAndView("professionalRecord/display");
		result.addObject("professionalRecord", professionalRecord);
		result.addObject("requestURI", "professionalRecord/display.do");

		return result;
	}
	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int curriculumId) {
		final ModelAndView result;
		Collection<ProfessionalRecord> professionalRecords;
		Curriculum curriculum;
		curriculum = this.curriculumService.findOne(curriculumId);
		professionalRecords = curriculum.getProfessionalRecord();

		result = new ModelAndView("professionalRecord/list");
		result.addObject("professionalRecords", professionalRecords);
		result.addObject("requestURI", "professionalRecord/list.do");

		return result;
	}
}
