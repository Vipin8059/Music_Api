package com.geekster.Music.Streaming.Api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpOutput {

    private boolean signUpStatus;

    private String signUpStatusMessage;
}
