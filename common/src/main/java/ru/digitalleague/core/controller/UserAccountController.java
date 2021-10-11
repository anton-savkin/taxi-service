package ru.digitalleague.core.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.core.model.UserAccountEntity;
import ru.digitalleague.core.service.UserAccountService;

@RestController
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping("/registration")
    @ApiOperation(value="Контроллер для работы с аккаунтами пользователями")
    public ResponseEntity<UserAccountEntity> registration(
            @RequestBody UserAccountEntity userAccountEntity) {

        UserAccountEntity accountEntity = userAccountService.registration(userAccountEntity);

        return ResponseEntity.ok(accountEntity);
    }
}
