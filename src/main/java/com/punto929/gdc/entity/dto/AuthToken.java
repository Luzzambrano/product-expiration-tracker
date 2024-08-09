package com.punto929.gdc.entity.dto;

import lombok.Builder;

@Builder
public record AuthToken(
    String accessToken,
    String refreshToken,
    Long expires
) {
}
