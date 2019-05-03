package br.com.totvs.cubo.api.model.response;

import br.com.totvs.cubo.api.model.Cubo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

public class CuboWSResponse implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 7621208790908355515L;

    @JsonInclude
    private Integer id;

    @JsonInclude
    private String titulo;

    @JsonInclude
    private String descricao;

    @JsonInclude
    private String consulta;

    @JsonInclude
    private String stringConexao;

    @JsonInclude
    private String arquivo;

    @JsonInclude
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date createAt;

    public CuboWSResponse(Integer id, String titulo, String descricao, String consulta, String stringConexao, String arquivo, Date createAt) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.consulta = consulta;
        this.stringConexao = stringConexao;
        this.arquivo = arquivo;
        this.createAt = createAt;
    }

    public CuboWSResponse(Cubo cubo) {
        this.id = cubo.getId();
        this.titulo = cubo.getTitulo();
        this.descricao = cubo.getDescricao();
        this.consulta = cubo.getConsulta();
        this.stringConexao = cubo.getStringConexao();
        this.arquivo = cubo.getArquivo();
        this.createAt = cubo.getCreateAt();
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
