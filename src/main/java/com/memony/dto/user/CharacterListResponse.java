package com.memony.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterListResponse {

    @JsonProperty("account_list")
    private List<AccountDto> accountList;
}
