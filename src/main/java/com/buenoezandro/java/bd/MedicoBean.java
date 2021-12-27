package com.buenoezandro.java.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class MedicoBean {
	private static Logger logger = Logger.getLogger(Conexao.class.toString());
	
	private MedicoBean() {
	}

	public static void insertData(Connection connection) {
		String insert = """
				INSERT INTO Medicos (
				nome,
				sobreNome,
				altura,
				anoNascimento,
				crm,
				salario)
				VALUES (?, ?, ?, ?, ?, ?);
				""";

		try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
			preparedStatement.setString(1, "Ezandro");
			preparedStatement.setString(2, "Bueno");
			preparedStatement.setFloat(3, 1.82F);
			preparedStatement.setInt(4, 1983);
			preparedStatement.setString(5, "123000");
			preparedStatement.setFloat(6, 25.000F);

			int r = preparedStatement.executeUpdate();
			logger.log(Level.INFO, "Status: {0}, dados gravados com sucesso!", String.valueOf(r));
		} catch (SQLException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "Erro: {0}", "Não foi possível inserir os dados na tabela!");
		}
	}
}
