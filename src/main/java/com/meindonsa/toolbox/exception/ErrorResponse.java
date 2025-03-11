package com.meindonsa.toolbox.exception;

import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@XmlRootElement(name = "error")
public class ErrorResponse {
    private String message;
    private List<String> details;
}
