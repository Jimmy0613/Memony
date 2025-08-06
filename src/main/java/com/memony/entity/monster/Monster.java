package com.memony.entity.monster;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int seq;

    private String regionMajor;

    private String regionMinor;

    private int level;

    private String modifier;

    private String name;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String note;

}
