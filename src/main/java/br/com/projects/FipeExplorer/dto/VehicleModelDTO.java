package br.com.projects.FipeExplorer.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleModelDTO(@JsonAlias("modelos")List<GeneralDTO> models) {
}
