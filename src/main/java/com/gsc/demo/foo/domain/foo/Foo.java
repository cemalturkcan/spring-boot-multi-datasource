package com.gsc.demo.foo.domain.foo;

import jakarta.persistence.*;

@Entity
public class Foo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="foo_id_seq")
    @SequenceGenerator(name="foo_id_seq", sequenceName="foo_id_seq", allocationSize=1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FOO")
    private String foo;

    public Foo(Long id, String foo) {
        this.id = id;
        this.foo = foo;
    }

    public Foo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }
}
