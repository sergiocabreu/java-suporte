package com.github.sergiocabreu.suporte.string;

import java.util.HashMap;
import java.util.Map;

public class InterpolacaoString {

	private String texto;
	private Map<String, String> parametros;
	
	public InterpolacaoString() {
		parametros = new HashMap<String, String>();
		this.texto = "";
	}
	
	public InterpolacaoString(String texto) {
		parametros = new HashMap<String, String>();
		this.texto = texto;
	}

	public String getTexto() {
		return texto; 
	}
	
	public String interpolacao() {
		String resultado = texto;
		
		for (Map.Entry<String, String> entry : parametros.entrySet()) {
			
			String local = resultado.replace(":"+entry.getKey(), entry.getValue()); 
			resultado = local;
		}
		
		return resultado;
	}

	public String append(String texto) {
		
		setTexto(getTexto() + texto);
		
		return getTexto();
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public InterpolacaoString add(String chave, String valor) {
		parametros.put(chave, valor);
		
		return this;
	}
	
	public void addParametro(String chave, String valor) {
		parametros.put(chave, valor);
	}

}
