package com.example.backend.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String key;
    private String value;
    @ManyToMany
    private List<Member> members;
}
