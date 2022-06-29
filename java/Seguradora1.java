package Seguradora;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Seguradora1 {

	private ArrayList<Documento> Documentos;


	public Seguradora1( ) {
		this.Documentos = new ArrayList<Documento>();
	}

	public void adicionaDocumento(Documento mani) {
		this.Documentos.add(mani);
	}

	public void listarDocumentos() {
		for(Documento mani:Documentos) {
			System.out.println(mani.toString());
		}
		System.out.println("Total = " + this.Documentos.size() + " Documentos listados com sucesso!\n");
	}
	
	public void excluirDocumento(Documento mani) {
		if (this.Documentos.contains(mani)) {
			this.Documentos.remove(mani);
			System.out.println("[Documento " + mani.toString() + "excluido com sucesso!]\n");
		}
		else
			System.out.println("Documento inexistente!\n");
	}

	public void excluirDocumentos() {
		Documentos.clear();
		System.out.println("Documentos excluidos com sucesso!\n");
	}
	public void gravarDocumentos()  {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\Documentos.dat"));
			for(Documento mani:Documentos) {
				outputStream.writeObject(mani);
			}
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (outputStream != null ) {
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void recuperarDocumentos() {
		ObjectInputStream inputStream = null;
		try {
			inputStream	= new ObjectInputStream (new FileInputStream ("c:\\temp\\Documentos.dat"));
			Object obj = null;
			while((obj = inputStream.readObject ()) != null) {
				if (obj instanceof Apolice)  
					this.Documentos.add((Apolice)obj);
				else if (obj instanceof Minuta)  
					this.Documentos.add((Minuta)obj);
			}
		}catch (EOFException ex) {     
			System.out.println ("Fim do arquivo");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (inputStream != null ) {
					inputStream.close();
					System.out.println("Documentos recuperados com sucesso!\n");
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		Seguradora1 seg  = new Seguradora1();

		Apolice segurado1    = new Apolice("Segurado1",    3, "Segurado1");
		Apolice segurado2 = new Apolice("Segurado2", 7, "Segurado2");
		Minuta  tomador1      = new Minuta ("Tomador1",  2, "Banco 1");
		Minuta  tomador2     = new Minuta ("Tomador2", 5, "Banco 2");
		seg.adicionaDocumento(segurado1);
		seg.adicionaDocumento(segurado2);
		seg.adicionaDocumento(tomador1);
		seg.adicionaDocumento(tomador2);
		seg.listarDocumentos();
		seg.gravarDocumentos();
		seg.excluirDocumento(tomador2);
		seg.listarDocumentos();
		seg.excluirDocumentos();
		seg.listarDocumentos();
		seg.recuperarDocumentos();
		seg.listarDocumentos();
	}

}
