package br.com.totvs.cubo.api.model;


import br.com.totvs.summer.persistence.bean.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cubo")
public class Cubo extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String titulo;

    @Column
    private String descricao;

    @Column
    private String consulta;

    @Column(name = "string_conexao")
    private String stringConexao;

    @Column
    private String arquivo;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    public Cubo(String titulo, String descricao, String consulta, String stringConexao, String arquivo, Date createAt) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.consulta = consulta;
        this.stringConexao = stringConexao;
        this.arquivo = arquivo;
        this.createAt = createAt;
    }

    public Cubo(Integer id, String titulo, String descricao, String consulta, String stringConexao, String arquivo, Date createAt) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.consulta = consulta;
        this.stringConexao = stringConexao;
        this.arquivo = arquivo;
        this.createAt = createAt;
    }

    public Cubo() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getStringConexao() {
        return stringConexao;
    }

    public void setStringConexao(String stringConexao) {
        this.stringConexao = stringConexao;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}