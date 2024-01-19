package br.com.projects.FipeExplorer.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeneralDTO(
    @JsonAlias("codigo") String code, 
    @JsonAlias("nome") String name) {
}
