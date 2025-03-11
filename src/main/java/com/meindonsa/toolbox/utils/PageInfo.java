package com.meindonsa.toolbox.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {

    private int index;
    private int size;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long total;
}
