package com.meindonsa.toolbox.view;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FcmRequest {

    @NotBlank
    private String title;
    private String message;

    private List<String> targetTokens;
}
