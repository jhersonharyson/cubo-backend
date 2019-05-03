package br.com.totvs.cubo.api.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.totvs.summer.persistence.bean.AbstractEntity;

@Entity
@Table(name="demanda")
public class Demanda extends AbstractEntity{

	private static final long serialVersionUID = -7062588380407974025L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String codigo;
	
	@Column
	private String descricao;
	
	@Column(name="usuario_cadastro")
	private Long usuarioCadastro;
	
	@Column(name="data_cadastro")
	private String dataCadastro;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contrato_id", referencedColumnName="id")
	private Contrato contrato;
	
	public Demanda() {}
	
	public Demanda(Long id, String codigo, String descricao, Long usuarioCadastro, String dataCadastro) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.usuarioCadastro = usuarioCadastro;
		this.dataCadastro = dataCadastro;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Long usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Demanda other = (Demanda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public Object getId() {
		return id;
	}
	
}