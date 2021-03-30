package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private CorsoDAO corso;
	private StudenteDAO studente;
	private List<Corso> c;
	private List<Studente> s;
	
	public Model() {
		corso = new CorsoDAO();
		c = new ArrayList<Corso>();
		studente = new StudenteDAO();
	}
	
	public List<Corso> getCorsi() {
		c.add(new Corso("", 0, "", 0));
		c.addAll(corso.getTuttiICorsi());
		return c;
	}
	
	public String completamento(Integer matricola) {
		return studente.getStudente(matricola);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso cors){
		return corso.getStudentiIscrittiAlCorso(cors);
	}
	
	public List<Corso> getCorsiStudenti(Integer matricola){
		return studente.getCorsiStudenti(matricola);
	}
	
	public boolean isIscritto(Integer matricola, Corso cors) {
		return corso.isIscritto(matricola, cors);
	}
	public int iscriviStudenteACorso(Integer matricola, Corso cors) {
		return corso.iscriviStudenteACorso(matricola, cors);
	}
}
