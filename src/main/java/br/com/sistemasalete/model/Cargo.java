package br.com.sistemasalete.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Cargo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCargo;
	@Column(length = 50)
	private String nomeCargo;
	@Lob
	private String descricao;
	@Temporal(TemporalType.DATE)
	private Calendar dataInic = Calendar.getInstance();
	@Temporal(TemporalType.DATE)
	private Calendar dataFinal = Calendar.getInstance();

	@ManyToOne
	@JoinColumn(name = "idMembro")
	private Membro membro;

	public int getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String Descricao) {
		this.descricao = Descricao;
	}

	public Calendar getDataInic() {
		return dataInic;
	}

	public void setDataInic(Calendar dataInic) {
		this.dataInic = dataInic;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}

}