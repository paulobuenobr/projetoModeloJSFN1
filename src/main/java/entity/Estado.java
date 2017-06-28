package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Entity
// Queries de pesquisa de cidade por nome e de pesquisa de todas as cidades
@NamedQueries({
	@NamedQuery(name="Estado.findByName", query="select e from Estado e where e.nome = :nome"),
	@NamedQuery(name="Estado.findAll", query="select e from Estado e order by e.nome"),
	@NamedQuery(name="Estado.findById", query="select e from Estado e where e.id = :id")
})
public class Estado implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nome;
	
//	// Mapeamento um para muitos
//	@OneToMany(mappedBy="estado", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Cidade> cidades;

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

//	public List<Cidade> getCidades() {
//		return cidades;
//	}
//
//	public void setCidades(List<Cidade> cidades) {
//		this.cidades = cidades;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		//result = prime * result + ((cidades == null) ? 0 : cidades.hashCode());
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
		Estado other = (Estado) obj;
		if (id == other.id && nome.equals(other.nome)) {
			return true;
		}
		return true;
	}	
}
