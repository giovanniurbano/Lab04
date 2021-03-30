package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> boxCorsi;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextArea txtRisultato;

	private Model model;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	String Smatricola = txtMatricola.getText();
    	Integer matricola = 0;
    	
    	try {
    		matricola = Integer.parseInt(Smatricola);
    	}
    	catch(NumberFormatException nfe) {
    		txtRisultato.setText("Inserire un numero");
    		return;
    	}
    	if(this.model.completamento(matricola).compareTo("") == 0) {
    		txtNome.clear();
        	txtCognome.clear();
    		txtRisultato.setText("Studente non esistente");
    		return;
    	}
    	txtRisultato.clear();
    	for(Corso c : this.model.getCorsiStudenti(matricola)) {
    		txtRisultato.appendText(c.toString() + "\n");
    	}
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	Corso corso = boxCorsi.getValue();
    	if(corso == null || corso.getNome().compareTo("")==0) {
    		txtRisultato.setText("Selezionare un corso");
    		return;
    	}
    	txtRisultato.clear();
    	for(Studente s : this.model.getStudentiIscrittiAlCorso(corso)) {
    		txtRisultato.appendText(s.toString() + "\n");
    	}
    }
    
    @FXML
    void doCompletamento(ActionEvent event) {
    	String Smatricola = txtMatricola.getText();
    	Integer matricola = 0;
    	
    	try {
    		matricola = Integer.parseInt(Smatricola);
    	}
    	catch(NumberFormatException nfe) {
    		txtRisultato.setText("Inserire un numero");
    		return;
    	}
    	
    	if(this.model.completamento(matricola).compareTo("") != 0) {
    		String info[] = this.model.completamento(matricola).split(" ");
	    	txtNome.setText(info[0]);
	    	txtCognome.setText(info[1]);
    	}
    	else {
    		txtNome.clear();
        	txtCognome.clear();
    		txtRisultato.setText("Studente non esistente");
    		return;
    	}
    }
    
    @FXML
    void doIscrivi(ActionEvent event) {
    	Corso c = boxCorsi.getValue();
    	String Smatricola = txtMatricola.getText();
    	Integer matricola = 0;
    	try {
    		matricola = Integer.parseInt(Smatricola);
    	}
    	catch(NumberFormatException nfe) {
    		txtRisultato.setText("Inserire un numero");
    		return;
    	}
    	if(this.model.completamento(matricola).compareTo("") == 0) {
    		txtNome.clear();
        	txtCognome.clear();
    		txtRisultato.setText("Studente non esistente");
    		return;
    	}

    	if(this.model.isIscritto(matricola, c))
    		txtRisultato.setText("Studente già iscritto al corso");
    	else {
    		int is = this.model.iscriviStudenteACorso(matricola, c);
    		if(is > 0)
    			txtRisultato.setText("Studente iscritto con successo");
    		else
    			txtRisultato.setText("Errore iscrizione");
    			
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	boxCorsi.getSelectionModel().clearSelection();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtRisultato.clear();
    }

    @FXML
    void initialize() {
        assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
    }

	public void setModel(Model m) {
		this.model = m;
		this.boxCorsi.getItems().addAll(this.model.getCorsi());
		
	}
}
