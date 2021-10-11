package ru.digitalleague.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@ApiModel(value="Модель прав пользователя")
public class AuthorityEntity implements GrantedAuthority {
    @ApiModelProperty("Идентификатор прав пользователя")
    private Long id;

    @ApiModelProperty("Идентификатор пользователя")
    private Long userAccountId;

    @ApiModelProperty("Название прав пользователя")
    private String authority;
}
