package com.memony.service.user;

import ch.qos.logback.core.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.memony.client.NexonApiClient;
import com.memony.dto.user.AccountDto;
import com.memony.dto.user.CharacterDto;
import com.memony.dto.user.CharacterListResponse;
import com.memony.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CharacterService {
    private final NexonApiClient nexonApiClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<CharacterDto> getCharacters(User user) {
        String apiKey = user.getNexonApiKey();
        String mainWorldName = user.getMainWorldName();

        List<CharacterDto> allCharacters = new ArrayList<>();
        if (StringUtils.isEmpty(apiKey) || StringUtils.isEmpty(mainWorldName)) {
            return allCharacters;
        }

        String result = nexonApiClient.getCharacters(apiKey);

        try {
            CharacterListResponse response = objectMapper.readValue(result, CharacterListResponse.class);

            for (AccountDto account : response.getAccountList()) {
                allCharacters.addAll(
                        account.getCharacters().stream()
                        .filter(character -> mainWorldName.equals(character.getWorld()))
                        .toList()
                );
            }

            return allCharacters;
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }
}
