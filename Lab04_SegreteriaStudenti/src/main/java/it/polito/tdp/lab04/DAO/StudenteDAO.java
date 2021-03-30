package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public String getStudente(Integer matricola) {

		final String sql = "SELECT nome, cognome FROM studente WHERE matricola = ?";
		String res = "";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				
				res = nome + " " + cognome;
				
				System.out.println(res);
			}

			conn.close();
			
			return res;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	public List<Corso> getCorsiStudenti(Integer matricola) {
		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
				+ "FROM iscrizione i, corso c "
				+ "WHERE c.codins = i.codins "
				+ "AND i.matricola = ? "
				+ "ORDER BY c.codins";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("c.codins");
				int numeroCrediti = rs.getInt("c.crediti");
				String nome = rs.getString("c.nome");
				int periodoDidattico = rs.getInt("c.pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

}
