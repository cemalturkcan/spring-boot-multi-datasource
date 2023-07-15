package com.gsc.demo.bar.domain.shoe;

import jakarta.persistence.*;

@Entity
public class Shoe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="bar_id_seq")
    @SequenceGenerator(name="bar_id_seq", sequenceName="bar_id_seq", allocationSize=1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BAR")
    private String bar;


    public Shoe(Long id, String bar) {
        this.id = id;
        this.bar = bar;
    }

    public Shoe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }
}
