package fr.fastconnect.quizz.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.fastconnect.quizz.dao.ContestDao;
import fr.fastconnect.quizz.dao.ParticipationDao;
import fr.fastconnect.quizz.dao.UserDao;
import fr.fastconnect.quizz.model.Contest;
import fr.fastconnect.quizz.model.Question;
import fr.fastconnect.quizz.model.User;
import fr.fastconnect.quizz.model.UserAnswer;
import fr.fastconnect.quizz.model.UserContestParticipation;

@Controller
public class QcmController 
{
	private static final Log logger = LogFactory.getLog(HomeController.class);
	
	@Resource(name="contestDao")
	private ContestDao contestDao;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="participationDao")
	private ParticipationDao participationDao;
	
	private User authenticate(final HttpServletRequest request, final HttpSession session)
	{
		final String usermail, password;
		
		if (request.getParameter("j_username")!=null){
			usermail = request.getParameter("j_username");
			session.setAttribute("j_username", usermail);
		}
		else{
			usermail = String.valueOf(session.getAttribute("j_username"));
		}
		
		if (request.getParameter("j_password")!=null){
			password = request.getParameter("j_password");
			session.setAttribute("j_password", password);
		}
		else{
			password = String.valueOf(session.getAttribute("j_password"));
		}
		String hashedPassword;
		try {
			hashedPassword = HomeController.hashPassword(password.trim());
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getStackTrace());
			hashedPassword = password;
		}
		return userDao.findFirstByEmailPassword(usermail.trim(), hashedPassword);
	}

	@RequestMapping(value = "/qcm")
	public String qcm(final HttpServletRequest request, final Model model, final HttpSession session)
	{
		logger.info("Contest starts, let's go!");
		
		final User user = authenticate(request, session);
		if (user == null){
			model.addAttribute("errorMessage", "Sorry, wrong password!");
			return "login";
		}

		final int contestId = Contest.getCurrentContestId();
		final Contest contest = contestDao.findFirstById(contestId);
		
		if (contest == null){
			model.addAttribute("errorMessage", "No contest available!");
			return "qcm";
		}
		
		//check if she/he has already take part in the current contest
		final UserContestParticipation participation = participationDao.findFirstByUserContest(user.getEmail(), contestId);
		
		model.addAttribute("contest", contest);
		model.addAttribute("user", user);
		model.addAttribute("participation", participation);
		
		return "qcm";
	}
	
	@RequestMapping(value = "/submission")
	public String next(final HttpServletRequest request, final Model model, final HttpSession session) 
	{	
		final User user = authenticate(request, session);

		if (user == null){
			model.addAttribute("errorMessage", "Bad authentication!");
			return "login";
		}

		UserContestParticipation participation;
		
		final int contestId = Integer.valueOf(request.getParameter("contestId"));
		final String email = request.getParameter("email");

		participation = participationDao.findFirstByUserContest(email, contestId); 
		if (participation==null) {participation=new UserContestParticipation();}

		participation.setEmail(email);
		participation.setContestId(contestId);
		participation.setFirstName(request.getParameter("firstname"));
		participation.setLastName(request.getParameter("lastname"));
		participation.setBirthday(request.getParameter("birthday"));

		participation.setAnswers(new ArrayList<UserAnswer>());
		participation.generateLuckyPoint();

		//set answers
		int cpt = 0;
		int score = 0;

		final Contest contest = contestDao.findFirstById(contestId);
		for (Question q : contest.getQuestions()) {
			final String answer = request.getParameter("answer-" + cpt);
			cpt++;

			UserAnswer userAnswer = new UserAnswer();
			userAnswer.setAnswer(answer);
			userAnswer.setQuestion(q.getQuestionId());

			logger.info(q.getQuestion() + ": " + answer);
    
			if(q.getCorrectAnswer() == null){
				userAnswer.setCorrect(true);
			}
			else {
				userAnswer.setCorrect(q.getCorrectAnswer().equalsIgnoreCase(answer));
				if(userAnswer.isCorrect()){
				score++;
				}
			}
			participation.getAnswers().add(userAnswer);       
		}
		participation.setScore(score);

		participationDao.save(participation);

		return "submission";
	}
	
}
