package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Entity
// Queries de pesquisa de cidade por nome e de pesquisa de todas as cidades
@NamedQueries({
	@NamedQuery(name="Cidade.findByName", query="select c from Cidade c where c.nome = :nome"),
	@NamedQuery(name="Cidade.findAll", query="select c from Cidade c order by c.nome"),
	@NamedQuery(name="Cidade.findById", query="select c from Cidade c where c.id = :id"),
	@NamedQuery(name="Cidade.findAllByEstadoId", query="select c from Cidade c where c.estado.id = :idEstado")
})
public class Cidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nome;
	
	// Mapeamento um para muitos
//	@OneToMany(mappedBy="cidade", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Pessoa> pessoas;

	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;
	
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

//	public List<Pessoa> getPessoas() {
//		return pessoas;
//	}
//
//	public void setPessoas(List<Pessoa> pessoas) {
//		this.pessoas = pessoas;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		//result = prime * result + ((pessoas == null) ? 0 : pessoas.hashCode());
		return result;
	}

	// M�todo equals sobrescrito para validar dados de selectOneMenu
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (id == other.id && nome.equals(other.nome)) {
			return true;
		}
		return true;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}	
}
