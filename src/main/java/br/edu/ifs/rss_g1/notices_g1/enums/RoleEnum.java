package br.edu.ifs.rss_g1.notices_g1.enums;

import lombok.Getter;

public enum RoleEnum {
    ADMIN(1),
    USER(2);

    @Getter
    private int code;

    RoleEnum(int code) {
        this.code = code;
    }

    public static RoleEnum valueOf(int code) {
        for (RoleEnum value : RoleEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("role não encontrado");
    }

}