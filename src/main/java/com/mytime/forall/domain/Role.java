package com.mytime.forall.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_roles")
@Data
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
@Column(name = "name_role")
    private String namerole;
@ManyToMany(mappedBy = "roles")
private Set<User> users;
    public Role(Integer id) {
        this.id = id;
    }

    public Role(Integer id, String namerole) {
        this.id = id;
        this.namerole = namerole;
    }

    public Role(String namerole) {
        this.namerole = namerole;
    }

    @Override
    public String getAuthority() {
        return getNamerole();
    }

}
