package fr.fastconnect.quizz.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.fastconnect.quizz.dao.ParticipationDao;
import fr.fastconnect.quizz.model.UserContestParticipation;


@Controller
public class ResultsController {

	private static final int MAX_RESULT = 1000;
	private static final int BATCH_SIZE = 50;
	
	@Resource(name="participationDao")
	private ParticipationDao participationDao;

	@RequestMapping(value = "/results")
	public String finalRes(final HttpServletRequest request, final Model model) 
	{    
		final List<UserContestParticipation> contestParticipations = participationDao.getBestResult(MAX_RESULT, BATCH_SIZE, true);
		
		model.addAttribute("contestParticipations", contestParticipations);
		model.addAttribute("size", contestParticipations.size());
		
		return "results";
	}

}
