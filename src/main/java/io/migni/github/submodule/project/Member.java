package io.migni.github.submodule.project;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    protected Member() {}

    public Member(final String name) {
        this.name = name;
    }

    public Long id() {
        return this.id;
    }

}
