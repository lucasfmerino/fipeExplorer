package br.com.projects.FipeExplorer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.projects.FipeExplorer.main.Main;

@SpringBootApplication
public class FipeExplorerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(FipeExplorerApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.displayMenu();
	}

}
