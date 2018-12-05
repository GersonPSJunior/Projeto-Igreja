package br.com.sistemasalete.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Culto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCulto;
	@Column(length = 100)
	private String nomeIgreja;
	@Column(length = 75)
	private String pastor;
	@Temporal(TemporalType.DATE)
	private Calendar dataCulto = Calendar.getInstance();
	private String diaSemana;
	@Temporal(TemporalType.TIME)
	private Date hora;
	private int qtdConversoes;
	private int qtdVisita;
	private int qtdTotPresente;
	private double totDizimo;
	private double totOfertasGerais;
	private double totOfertasMissoes;
	private double outrasEntradas;
	private double totArrecadacao;
	@ManyToOne
	@JoinColumn(name = "idIgreja")
	private Igreja igreja;
	@OneToOne(cascade = CascadeType.ALL)
	private Frequencia frequencia;

	public int getIdCulto() {
		return idCulto;
	}

	public void setIdCulto(int idCulto) {
		this.idCulto = idCulto;
	}

	public String getPastor() {
		return pastor;
	}

	public void setPastor(String pastor) {
		this.pastor = pastor;
	}

	public Calendar getDataCulto() {
		return dataCulto;
	}

	public void setDataCulto(Calendar dataCulto) {
		this.dataCulto = dataCulto;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public int getQtdConversoes() {
		return qtdConversoes;
	}

	public void setQtdConversoes(int qtdConversoes) {
		this.qtdConversoes = qtdConversoes;
	}

	public int getQtdVisita() {
		return qtdVisita;
	}

	public void setQtdVisita(int qtdVisita) {
		this.qtdVisita = qtdVisita;
	}

	public int getQtdTotPresente() {
		return qtdTotPresente;
	}

	public void setQtdTotPresente(int qtdTotPresente) {
		this.qtdTotPresente = qtdTotPresente;
	}

	public double getTotDizimo() {
		return totDizimo;
	}

	public void setTotDizimo(double totDizimo) {
		this.totDizimo = totDizimo;
	}

	public double getTotOfertasGerais() {
		return totOfertasGerais;
	}

	public void setTotOfertasGerais(double totOfertasGerais) {
		this.totOfertasGerais = totOfertasGerais;
	}

	public double getTotOfertasMissoes() {
		return totOfertasMissoes;
	}

	public void setTotOfertasMissoes(double totOfertasMissoes) {
		this.totOfertasMissoes = totOfertasMissoes;
	}

	public double getOutrasEntradas() {
		return outrasEntradas;
	}

	public void setOutrasEntradas(double outrasEntradas) {
		this.outrasEntradas = outrasEntradas;
	}

	public double getTotArrecadacao() {
		return totArrecadacao;
	}

	public void setTotArrecadacao(double totArrecadacao) {
		this.totArrecadacao = totArrecadacao;
	}

	public String getNomeIgreja() {
		return nomeIgreja;
	}

	public void setNomeIgreja(String nomeIgreja) {
		this.nomeIgreja = nomeIgreja;
	}

	public void newFrequencia() {
		this.frequencia = new Frequencia();
	}

}