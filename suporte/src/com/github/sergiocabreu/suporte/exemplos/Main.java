package com.github.sergiocabreu.suporte.exemplos;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.github.sergiocabreu.suporte.string.InterpolacaoString;

public class Main {

	
	public static void main(String[] args) throws IOException {

		Path caminho = Paths.get(System.getProperty("user.home"), "Downloads/poupanca.csv");
		
		Stream<String> linhas = Files.lines(caminho, StandardCharsets.ISO_8859_1);

		Stream<String> sql = linhas.map( linha -> {
			return monta(linha);
		});
		
		sql.forEach( System.out::println);
		
		linhas.close();
	}
	
	public static String monta(String linha) {
		
		if (linha.length() == 36 ) {
			
			String[] parametros = linha.split(";");
			
			InterpolacaoString i = new InterpolacaoString();
			i.append("INSERT INTO poupanca (datainicio,datafim,rendimentoregraantiga,rendimentoregranova) VALUES (':inicio', ':fim', :antiga, :nova);");
			i.add("inicio", parametros[0]).add("fim", parametros[1]).add("antiga", parametros[2].replaceAll(",", ".")).add("nova", parametros[3].replaceAll(",", "."));
			
			return i.interpolacao();
		
		} else {
			return "";
		}
		
	}
	

}
