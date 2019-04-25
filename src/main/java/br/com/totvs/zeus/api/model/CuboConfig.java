package br.com.totvs.zeus.api.model;


import br.com.totvs.summer.persistence.bean.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name="cubo_config")
public class CuboConfig extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String source;

    public CuboConfig(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }
}