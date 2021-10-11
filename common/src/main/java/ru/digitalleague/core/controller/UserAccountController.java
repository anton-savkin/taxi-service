package ru.digitalleague.core.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/welcome")
    public String welcome() {

        return "Welcome to all authorities";
    }

    @GetMapping("/admin-info")
    public String printAdminInfo() {

        return "Some administration info";
    }

    @GetMapping("/manager-info")
    public String printManagerInfo() {

        return "Some management info";
    }
}
