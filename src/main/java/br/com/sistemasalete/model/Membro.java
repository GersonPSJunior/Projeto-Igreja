package br.com.sistemasalete.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
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
public class Membro implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idMembro;
	@Column(length = 75, nullable = false)
	private String nome;
	@Temporal(TemporalType.DATE)
	private Calendar nascimento = Calendar.getInstance();
	@Column(length = 1)
	private String sexo;
	@Column(length = 75)
	private String endereco;
	@Column(length = 4)
	private int numero;
	@Column(length = 15)
	private String complemento;
	@Column(length = 25)
	private String bairro;
	@Column(length = 10)
	private String cep;
	@Column(length = 50)
	private String email;
	@Column(length = 20)
	private String fone;
	@Column(length = 100)
	private String nomePai;
	@Column(length = 100)
	private String nomeMae;
	@Column(length = 25)
	private String nacionalidade;
	@Column(length = 20)
	private String estCivil;
	@Column(length = 20)
	private String rg;
	@Column(length = 20)
	private String cpf;
	private String grauInstr;
	private String areaForm;
	private String profissao;
	private String salario;
	@Temporal(TemporalType.DATE)
	private Calendar dataBatAgua = Calendar.getInstance();
	private String igrejaBat;
	private String pastorBat;

	@OneToMany(mappedBy = "membro", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cargo> cargo;

	@ManyToOne
	@JoinColumn(name = "idIgreja")
	private Igreja igreja;

	@ManyToOne
	@JoinColumn(name = "idMunicipio")
	private Municipio municipio;

	public int getIdMembro() {
		return idMembro;
	}

	public void setIdMembro(int idMembro) {
		this.idMembro = idMembro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Calendar getNascimento() {
		return nascimento;
	}

	public void setNascimento(Calendar nascimento) {
		this.nascimento = nascimento;
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getEstCivil() {
		return estCivil;
	}

	public void setEstCivil(String estCivil) {
		this.estCivil = estCivil;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getGrauInstr() {
		return grauInstr;
	}

	public void setGrauInstr(String grauInstr) {
		this.grauInstr = grauInstr;
	}

	public String getAreaForm() {
		return areaForm;
	}

	public void setAreaForm(String areaForm) {
		this.areaForm = areaForm;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public String getIgrejaBat() {
		return igrejaBat;
	}

	public void setIgrejaBat(String igrejaBat) {
		this.igrejaBat = igrejaBat;
	}

	public String getPastorBat() {
		return pastorBat;
	}

	public void setPastorBat(String pastorBat) {
		this.pastorBat = pastorBat;
	}

	public Calendar getDataBatAgua() {
		return dataBatAgua;
	}

	public void setDataBatAgua(Calendar dataBatAgua) {
		this.dataBatAgua = dataBatAgua;
	}
}