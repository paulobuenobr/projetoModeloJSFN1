package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQueries({
	@NamedQuery(name="Pessoa.findByName", query="select p from Pessoa p where p.nome = :nome"),
	@NamedQuery(name="Pessoa.findAll", query="select p from Pessoa p order by p.nome"),
	@NamedQuery(name="Pessoa.findById", query="select p from Pessoa p where p.id = :id")
})
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nome;
	
	@Column
	private Long cpf;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;

	@Column
	private Boolean maiorIdade;

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Boolean getMaiorIdade() {
		return maiorIdade;
	}

	public void setMaiorIdade(Boolean maiorIdade) {
		this.maiorIdade = maiorIdade;
	}

	// Mapeamento muitos para um
	@ManyToOne
	@JoinColumn(name="id_cidade")
	private Cidade cidade;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	// TODO: implementar equals() e hashCode()
}
