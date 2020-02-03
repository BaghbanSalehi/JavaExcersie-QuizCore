package fr.epita.quiz.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.quiz.datamodel.MCQQuestion;

public class MCQQuestionJDBCDAO {

	public int create(MCQQuestion mcqQuestion) {

		PreparedStatement preparedStatement;
		try (Connection connection = getConnection()) {
			preparedStatement = connection
					.prepareStatement("INSERT INTO MCQQUESTIONS(QUESTION, DIFFICULTY) VALUES(?,?)");
			// 2ta ? ro jaygozin mikoni (1,string) yani ? aval jash un str biad
			preparedStatement.setString(1, mcqQuestion.getQuestion());
			preparedStatement.setInt(2, mcqQuestion.getDifficulty());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	public List<MCQQuestion> search(String contentInQuestion, int searchedDifficulty) {

		try (Connection connection = getConnection()) {
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT ID,QUESTION,DIFFICULTY FROM MCQQUESTIONS\r\n" + "WHERE"
							+ "   QUESTION LIKE ?" + "   AND DIFFICULTY < ?");
			prepareStatement.setString(1, "%" + contentInQuestion + "%");
			prepareStatement.setInt(2, searchedDifficulty);

			ResultSet result = prepareStatement.executeQuery();
			// result dar ane vahed faghat 1 line ro dare kole dastano nadare
			List<MCQQuestion> resultList = new ArrayList<>();
			while (result.next()) {
				int difficulty = result.getInt("DIFFICULTY");
				int id = result.getInt("ID");
				String content = result.getString("QUESTION");
				MCQQuestion currenQuestion = new MCQQuestion();
				currenQuestion.setDifficulty(difficulty);
				currenQuestion.setId(Long.valueOf(id));
				currenQuestion.setQuestion(content);
				resultList.add(currenQuestion);

			}
			return resultList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();

	}

	private static Connection getConnection() throws SQLException {
		// to connect to the database you need 4 information :
		// the url:TODO : change hardcoded values by values rom the configuration
		String url = "jdbc:h2:mem:test";
		// the user :
		String user = "sa";

		// the password
		String password = "test";

		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}

}
