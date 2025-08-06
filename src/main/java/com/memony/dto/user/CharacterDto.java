package com.memony.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDto {

    String ocid;

    @JsonProperty("character_name")
    private String name;

    @JsonProperty("character_class")
    private String job;

    @JsonProperty("character_level")
    private int level;

    @JsonProperty("world_name")
    private String world;
}
