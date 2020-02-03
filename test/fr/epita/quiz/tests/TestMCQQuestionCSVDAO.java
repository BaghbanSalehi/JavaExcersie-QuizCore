package fr.epita.quiz.tests;

import java.io.FileNotFoundException;
import java.util.List;

import fr.epita.quiz.datamodel.MCQQuestion;
import fr.epita.quiz.services.MCQQuestionCSVDAO;

public class TestMCQQuestionCSVDAO {

	public static void main(String[] args) throws Exception {

		// given
		MCQQuestionCSVDAO dao = new MCQQuestionCSVDAO();

		MCQQuestion mcqQuestion = new MCQQuestion();
		mcqQuestion.setDifficulty(3);
		mcqQuestion.setQuestion("what can we do with JDK?");
		mcqQuestion.setTopics(new String[] { "java", "compilation", "environments" });
		mcqQuestion.setId(1l);
		MCQQuestion mcqQuestion2 = new MCQQuestion();
		mcqQuestion2.setDifficulty(3);
		mcqQuestion2.setQuestion("what is CSV?");
		mcqQuestion2.setTopics(new String[] { "csv", "file", "data" });
		mcqQuestion2.setId(21l);
		// when
		dao.create(mcqQuestion);
		dao.create(mcqQuestion2);

		// then
		MCQQuestion readQuestion = dao.getById(1);
		boolean success = readQuestion.getId().equals(mcqQuestion.getId());
		System.out.println("s?" + success);

	}
}
