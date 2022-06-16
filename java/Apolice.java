package Seguradora;

public class Apolice extends Documento {

	private static final long serialVersionUID = 1L;

	public String emitir(){
		return "Emitir a Apolice e Envio para SUSEP";
	}
	public Apolice(String Segurado, int VigenciaEmDias, String Tomador) {
		super(Segurado, VigenciaEmDias, Tomador);
		this.tipoDocumento= "Apolice";
	}
}
