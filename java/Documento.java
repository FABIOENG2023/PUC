package Seguradora;

import java.io.Serializable;

public abstract class Documento implements Serializable {

	private static final long serialVersionUID = 1L;
	private   String Segurado;
	private   int VigenciaEmDias;
	private   String Tomador;
	protected String especie;
	
	public Documento(String Segurado, int VigenciaEmDias, String Tomador) {
		this.Segurado = Segurado;
		this.VigenciaEmDias = VigenciaEmDias;
		this.Tomador = Tomador;
	}
	public String toString() {
		String retorno = "";
		retorno += "Segurado: "     + this.Segurado     + "\n";
		retorno += "VigenciaEmDias: "    + this.VigenciaEmDias    + " dias\n";
		retorno += "Tomador: "     + this.Tomador     + "\n";
		retorno += "Especie: "  + this.tipoDocumento + "\n";
		retorno += "Tipo Documento: "  + emitir()       + "\n";
		return retorno;
	}
	public abstract String emitir();
}
