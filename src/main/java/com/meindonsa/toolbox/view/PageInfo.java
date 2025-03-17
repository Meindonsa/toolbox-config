package com.meindonsa.toolbox.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {
    private int size;
    private int index;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long total;
}
