package com.buenoezandro.java.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class Conexao {

	private static final String URL = "jdbc:mysql://localhost:3306/javadb?createDatabaseIfNotExist=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private static Connection connection = null;
	private static Logger log = Logger.getLogger(Conexao.class.toString());

	private Conexao() {
	}

	public static Connection createConnection() {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				log.info("Conexão efetuada com sucesso!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Erro ao conectar no banco de dados!");
		}
		return connection;
	}

	public static void createTable(Connection connection) {
		String create = """
				CREATE TABLE IF NOT EXISTS Medicos(
				 id int(11) NOT NULL AUTO_INCREMENT,
				 nome varchar(30) NOT NULL,
				 sobreNome varchar(30) NOT NULL,
				 altura float(14,2) DEFAULT NULL,
				 anoNascimento int(11) NOT NULL,
				 crm varchar(10) NOT NULL,
				 salario float(14,2) DEFAULT NULL,
				 PRIMARY KEY (`id`))
				 ENGINE=InnoDB DEFAULT CHARSET=utf8;""";

		try (Statement statement = connection.createStatement()) {
			statement.execute(create);
			log.info("Tabela criada com sucesso no banco de dados!");
		} catch (SQLException e) {
			log.info("Erro ao criar a tabela no banco de dados!");
		}
	}
}
