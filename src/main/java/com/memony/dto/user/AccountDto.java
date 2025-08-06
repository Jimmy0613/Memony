package com.memony.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountDto {
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("character_list")
    private List<CharacterDto> characters;
}
