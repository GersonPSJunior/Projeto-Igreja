package br.com.sistemasalete.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Frequencia implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idFrequencia;
	private int qtdMembros;
	private int qtdVisitas;
	@OneToOne(mappedBy = "frequencia")
	private Culto culto;

	public int getIdFrequencia() {
		return idFrequencia;
	}

	public void setIdFrequencia(int idFrequencia) {
		this.idFrequencia = idFrequencia;
	}

	public int getQtdMembros() {
		return qtdMembros;
	}

	public void setQtdMembros(int qtdMembros) {
		this.qtdMembros = qtdMembros;
	}

	public int getQtdVisitas() {
		return qtdVisitas;
	}

	public void setQtdVisitas(int qtdVisitas) {
		this.qtdVisitas = qtdVisitas;
	}

	public void newCulto() {
		this.culto = new Culto();
	}

}