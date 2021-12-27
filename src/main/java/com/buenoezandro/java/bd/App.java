package com.buenoezandro.java.bd;

import java.sql.Connection;

public class App {
	
	public static void main(String[] args) {
		Connection connection = Conexao.createConnection();
		Conexao.createTable(connection);
		MedicoBean.insertData(connection);
	}
}
