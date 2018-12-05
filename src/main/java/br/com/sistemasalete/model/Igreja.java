package br.com.sistemasalete.model;

import java.io.Serializable;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Igreja implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idIgreja;
	private String razaoSocial;
	private int cnpj;
	private String endereco;
	@Column(length = 4)
	private int numero;
	@Column(length = 15)
	private String complemento;
	@Column(length = 25)
	private String nomeMunicipio;
	@Column(length = 20)
	private int fone1;
	@Column(length = 20)
	private int fone2;
	private String categora;
	@Temporal(TemporalType.DATE)
	private Date dataCad;
	@Temporal(TemporalType.DATE)
	private Date dataFun;
	@Column(length = 75)
	private String email;
	@ManyToOne
	@JoinColumn(name = "idMunicipio")
	private Municipio municipio;
	@OneToMany(mappedBy = "igreja", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Membro> membro;
	@OneToMany(mappedBy = "igreja", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Culto> culto;

	public int getIdIgreja() {
		return idIgreja;
	}

	public void setIdIgreja(int idIgreja) {
		this.idIgreja = idIgreja;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public int getFone1() {
		return fone1;
	}

	public void setFone1(int fone1) {
		this.fone1 = fone1;
	}

	public int getFone2() {
		return fone2;
	}

	public void setFone2(int fone2) {
		this.fone2 = fone2;
	}

	public String getCategora() {
		return categora;
	}

	public void setCategora(String categora) {
		this.categora = categora;
	}

	public Date getDataCad() {
		return dataCad;
	}

	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}

	public Date getDataFun() {
		return dataFun;
	}

	public void setDataFun(Date dataFun) {
		this.dataFun = dataFun;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

}