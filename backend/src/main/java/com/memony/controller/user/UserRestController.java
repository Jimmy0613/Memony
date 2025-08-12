package com.memony.controller.user;

import com.memony.dto.user.CharacterDto;
import com.memony.entity.user.User;
import com.memony.service.user.CharacterService;
import com.memony.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    private final CharacterService characterService;

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}/characters")
    public List<CharacterDto> getCharacters(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return characterService.getCharacters(user);
    }
}
