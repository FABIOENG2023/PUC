package Seguradora;

public class Minuta extends Documento {

	private static final long serialVersionUID = 1L;

	public String emitir(){
		return "Imprime um PDF Rascunho escrito Minuta";
	}
	public Minuta(String Segurado, int VigenciaEmDias, String Tomador) {
		super(Segurado, VigenciaEmDias, Tomador);
		this.tipoDocumento= "Minuta";
	}
}
