package fr.epita.quiz.tests;

import java.util.ArrayList;
import java.util.Arrays;

import fr.epita.quiz.datamodel.MCQQuestion;

public class TestMCQQuestionSerialization {

	private static final String TOPIC_DELIMITER = "\\|\\|\\|";
	private static final String COLUMN_DELIMITER = ";";
	static int i;

	public static void main(String[] args) {
		MCQQuestion mcqQuestion = new MCQQuestion();
		mcqQuestion.setDifficulty(3);
		mcqQuestion.setQuestion("what can we do with JDK?");
		mcqQuestion.setTopics(new String[] { "java", "compilation", "environments" });
		mcqQuestion.setId(1l);
		TestMCQQuestionSerialization t = null;
		System.out.println(t.i);
		// formating to csv
		// id;difficulty;question_content;topic1|t2|t3
		String formatted = String.valueOf(mcqQuestion.getId()) + COLUMN_DELIMITER;
		formatted += String.valueOf(mcqQuestion.getDifficulty()) + COLUMN_DELIMITER;
		formatted += mcqQuestion.getQuestion() + COLUMN_DELIMITER;
		String[] topics = mcqQuestion.getTopics();
		for (int i = 0; i < topics.length; i++) {
			formatted += topics[i] + TOPIC_DELIMITER;

		}
		System.out.println("formatted");
		System.out.println(formatted);

		// reconstruction phase

		String[] parts = formatted.split(COLUMN_DELIMITER);// mige harja didi ; hast yani ye khune az array tamom shode.
		System.out.println(Arrays.asList(parts));
		Long id = Long.valueOf(parts[0]);
		Integer difficulty = Integer.valueOf(parts[1]);
		String question = parts[2];
		String[] readTopics = parts[3].split(TOPIC_DELIMITER);

		MCQQuestion reconstructed = new MCQQuestion();
		reconstructed.setDifficulty(difficulty);
		reconstructed.setId(id);
		reconstructed.setQuestion(question);
		reconstructed.setTopics(readTopics);

	}

}
