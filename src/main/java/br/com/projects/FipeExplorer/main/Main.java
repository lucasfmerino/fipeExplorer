package br.com.projects.FipeExplorer.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.projects.FipeExplorer.dto.GeneralDTO;
import br.com.projects.FipeExplorer.dto.VehicleDTO;
import br.com.projects.FipeExplorer.dto.VehicleModelDTO;
import br.com.projects.FipeExplorer.service.ConsumeAPI;
import br.com.projects.FipeExplorer.service.DataConverter;

public class Main {

    private Scanner sc = new Scanner(System.in);
    private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumeAPI consume = new ConsumeAPI();
    private DataConverter converter = new DataConverter();

    public void displayMenu() {

        
        var menu = """
                
        *** OPÇÕES ***

        1 - Carro
        2 - Moto
        3 - Caminhão

        Digite a opção desejada:
                """;

        System.out.println(menu);

        String option = "";
        String url = null;

        // BUSCAR LISTA DE MARCAS
        while (!option.equals("1") && !option.equals("1") && !option.equals("3")) {
            option = sc.nextLine();
            
            switch (option) {
                case "1":
                    url = BASE_URL + "carros/marcas";
                    break;
                
                case "2":
                    url = BASE_URL + "motos/marcas";
                    break;
                
                case "3":
                    url = BASE_URL + "caminhoes/marcas";
                    break;
                
                default:
                    System.out.println("Digite uma opção válida.");;
            }
        }

        System.out.println(url);
        var json = consume.getApiData(url);
        System.out.println(json);

        var brands = converter.getList(json, GeneralDTO.class);

        brands.stream()
            .sorted(Comparator.comparing(GeneralDTO::code))
            .forEach(System.out::println);

        // BUSCAR LISTA DE MODELOS:
        System.out.println("Informe o código da marca: ");
        var brandCode = sc.nextLine();

        url += "/" + brandCode + "/modelos";
        json = consume.getApiData(url);

        var vehicleModels = converter.getData(json, VehicleModelDTO.class);

        System.out.println("\nModelos:");
        vehicleModels.models().stream()
            .sorted(Comparator.comparing(GeneralDTO::code))
            .forEach(System.out::println);

        // FILTRAR MODELOS POR NOME:
        System.out.println("\nDigite o nome do modelo para realizar a busca: ");
        var modelName = sc.nextLine();

        List<GeneralDTO>  modelsFound = vehicleModels.models().stream()
            .filter(m -> m.name().toLowerCase().contains(modelName.toLowerCase()))
            .collect(Collectors.toList());

        System.out.println("\nModelos encontrados: ");
        modelsFound.forEach(System.out::println);


        // 
        System.out.println("\nInforme o código do modelo:");
        var modelCode = sc.nextLine();

        url += "/" + modelCode + "/anos";
        json = consume.getApiData(url);

        List<GeneralDTO> modelYears = converter.getList(json, GeneralDTO.class);
        List<VehicleDTO> vehicles = new ArrayList<>();

        for (int i = 0; i < modelYears.size(); i++) {
            var vehicleUrl = url + "/" + modelYears.get(i).code();
            json = consume.getApiData(vehicleUrl);
            VehicleDTO vehicleDTO = converter.getData(json, VehicleDTO.class);
            vehicles.add(vehicleDTO);
        }

        System.out.println("\n Avaliações: ");
        vehicles.forEach(System.out::println);

    }
}
