package br.com.projects.FipeExplorer.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleDTO(
    @JsonAlias("Valor") String value,
    @JsonAlias("Marca") String vehicleBrand,
    @JsonAlias("Modelo") String vehicleModel,
    @JsonAlias("AnoModelo") Integer vehicleYear,
    @JsonAlias("Combustivel") String fuelType) {
}
