package fr.epita.quiz.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import fr.epita.quiz.datamodel.MCQQuestion;

public class MCQQuestionCSVDAO {

	private static final String DATA_FILE = Configuration.getValueFromKey("data.file");
	private static final String TOPIC_DELIMITER_SPLIT = "\\|\\|\\|";
	private static final String TOPIC_DELIMITER = "|||";
	private static final String COLUMN_DELIMITER = ";";

	public void create(MCQQuestion mcqQuestion) {

		String formatted = String.valueOf(mcqQuestion.getId()) + COLUMN_DELIMITER;
		formatted += String.valueOf(mcqQuestion.getDifficulty()) + COLUMN_DELIMITER;
		formatted += mcqQuestion.getQuestion() + COLUMN_DELIMITER;
		String[] topics = mcqQuestion.getTopics();
		for (int i = 0; i < topics.length; i++) {
			formatted += topics[i] + TOPIC_DELIMITER;
		}

		// System.out.println("formatted");
		// System.out.println(formatted);

		// TODO write the formatted string in a file
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(DATA_FILE, true))) {
			writer.println(formatted);
		} catch (FileNotFoundException e) {
			// TODO custom exceotion to be writtern
			e.printStackTrace();
		}

	}

	protected void printToFile(String message) {
		System.out.println(message);

	}

	public List<MCQQuestion> readAll() {

		List<MCQQuestion> results = new ArrayList<MCQQuestion>();
		// TODO read all the "line" values from the file
		List<String> lines;
		try {
			lines = Files.readAllLines(new File(DATA_FILE).toPath());
			for (String line : lines) {

				String[] parts = line.split(COLUMN_DELIMITER);// mige harja didi ; hast yani ye khune az array tamom
																// shode.
				// System.out.println(Arrays.asList(parts));
				Long id = Long.valueOf(parts[0]);
				Integer difficulty = Integer.valueOf(parts[1]);
				String question = parts[2];
				String[] readTopics = parts[3].split(TOPIC_DELIMITER_SPLIT);

				MCQQuestion reconstructed = new MCQQuestion();
				reconstructed.setDifficulty(difficulty);
				reconstructed.setId(id);
				reconstructed.setQuestion(question);
				reconstructed.setTopics(readTopics);
				results.add(reconstructed);
				results.add(reconstructed);

			}

		} catch (IOException e) {
			// TODO consider making a custom exception
			e.printStackTrace();
		}

		return results;

	}

	public MCQQuestion getById(long l) {
		List<MCQQuestion> list = readAll();
		return list.stream().filter(entry -> entry.getId() == 1).collect(Collectors.toList()).get(0);

	}

}
