package com.punto929.gdc.entity.dto;

import lombok.Builder;

@Builder
public record SignupRequest(
    String username,
    String email,
    String password,
    String firstName,
    String lastName
) {
}
