package fr.fastconnect.quizz.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

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
import fr.fastconnect.quizz.model.QuestionType;

@Controller
public class AdminController 
{
	private static final Log logger = LogFactory.getLog(AdminController.class);

	@Resource(name="contestDao")
	private ContestDao contestDao;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="participationDao")
	private ParticipationDao participationDao;
		
	private boolean authenticate(final HttpServletRequest request, final HttpSession session)
	{
		final String admin_user, admin_pass;
		
		if (request.getParameter("j_username")!=null){
			admin_user = request.getParameter("j_username");
			session.setAttribute("j_username", admin_user);
		}
		else{
			admin_user = String.valueOf(session.getAttribute("j_username"));
		}
		
		if (request.getParameter("j_password")!=null){
			admin_pass = request.getParameter("j_password");
			session.setAttribute("j_password", admin_pass);
		}
		else{
			admin_pass = String.valueOf(session.getAttribute("j_password"));
		}
		return "admin".equalsIgnoreCase(admin_user) && "rockandroll".equalsIgnoreCase(admin_pass);
	}
	
	@RequestMapping(value = "/admin_login")
	public String adminLogin()
	{
		return "admin_login";
	}

	@RequestMapping(value = "/admin")
	public String admin(final HttpServletRequest request, final Model model, final HttpSession session)
	{
		if (!authenticate(request, session))
		{
			model.addAttribute("errorMessage", "Admin authentification failed.");
			return "admin_login";
		}
		
		if (request.getParameter("contestId")!=null)
		{
			Contest.setCurrentContestId(Integer.valueOf(request.getParameter("contestId")));
		}
		
		if ("yes".equalsIgnoreCase(request.getParameter("clear_contest")))
		{
			logger.info("Clear the contests.");
			Contest.setCurrentContestId(0);
			contestDao.removeAll();
		}
		else if ("yes".equalsIgnoreCase(request.getParameter("generate_contest")))
		{
			logger.info("Generate new contests.");
			final List<Contest> allContests = generateContests();
			contestDao.saveAll(allContests);
			model.addAttribute("contests", allContests);
		}
		else
		{
			model.addAttribute("contests", contestDao.findAll());
		}
		
		model.addAttribute("currentContestId", Contest.getCurrentContestId());
		model.addAttribute("contestCount", contestDao.count());
		model.addAttribute("userCount", userDao.count());
		model.addAttribute("participationCount", participationDao.count());
		
		return "admin";
	}
	
	private static final String CONTESTS_PATH="contests.txt";	
	private static List<Contest> generateContests()
	{
		List<Contest> allContests = new LinkedList<Contest>();
		int currentContest = 1;
		
		try
		{
			InputStream istream = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONTESTS_PATH);
			BufferedReader br = new BufferedReader(new InputStreamReader(istream));
			
			String strLine;
			Contest contest=null;
			Question question=null;
			List<Question> questions=null;
			List<String> answers=null;
			
			while ((strLine = br.readLine()) != null)
			{	
				if (strLine.startsWith("contest:"))
				{
					contest = new Contest();
					questions = new LinkedList<Question>();
					contest.setContestId(currentContest++);
					contest.setQuestions(questions);
					
					allContests.add(contest);
				}
				else if (strLine.startsWith("question:"))
				{
					question = new Question();
					contest.getQuestions().add(question);
					
					question.setQuestionType(QuestionType.Choice);
					question.setQuestion(strLine.split(":", 2)[1].trim());
					
					answers = new LinkedList<String>();
					question.setAnswers(answers);
				}
				else if (strLine.startsWith("proposition:")){
					answers.add(strLine.split(":", 2)[1].trim());
				}
				else if (strLine.startsWith("right answer:")){
					question.setCorrectAnswer(strLine.split(":", 2)[1].trim());
				}
			}
			br.close();
		} catch (Exception e){
			logger.info(e.getMessage());  
		}
		
		return allContests;
	}

}
