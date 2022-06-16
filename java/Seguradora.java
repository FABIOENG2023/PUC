package Seguradora;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Seguradora {
	private ArrayList<Documento> Documentos;

	public Seguradora() {
		this.Documentos = new ArrayList<Documento>();
	}
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Apolice leApolice (){

		String [] valores = new String [3];
		String [] SeguradoVal = {"Segurado", "VigenciaEmDias", "Tomador"};
		valores = leValores (SeguradoVal);

		int VigenciaEmDias = this.retornaInteiro(valores[1]);

		Apolice Apolice = new Apolice (valores[0],VigenciaEmDias,valores[2]);
		return Apolice;
	}

	public Minuta leMinuta (){

		String [] valores = new String [3];
		String [] SeguradoVal = {"Segurado", "VigenciaEmDias", "Tomador"};
		valores = leValores (SeguradoVal);

		int VigenciaEmDias = this.retornaInteiro(valores[1]);

		Minuta Minuta = new Minuta (valores[0],VigenciaEmDias,valores[2]);
		return Minuta;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); 
			return true;
		} catch (NumberFormatException e) { 
			return false;
		}
	}
	public int retornaInteiro(String entrada) { 
		int numInt;

		
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaDocumentos (ArrayList<Documento> Documentos){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\Seguradora.dados"));
			for (int i=0; i < Documentos.size(); i++)
				outputStream.writeObject(Documentos.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Documento> recuperaDocumentos (){
		ArrayList<Documento> DocumentosTemp = new ArrayList<Documento>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\Seguradora.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Documento) {
					DocumentosTemp.add((Documento) obj);
				}   
			}          
		} catch (EOFException ex) { 
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com Documentos NÃO existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return DocumentosTemp;
		}
	}

	public void menuSeguradora (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Seguradora\n" +
					"Opções:\n" + 
					"1. Entrar Documentos\n" +
					"2. Exibir Documentos\n" +
					"3. Limpar Documentos\n" +
					"4. Gravar Documentos\n" +
					"5. Recuperar Documentos\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:
				menu = "Entrada de Documentos\n" +
						"Opções:\n" + 
						"1. Minuta\n" +
						"2. Apolice\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: Documentos.add((Documento)leMinuta());
				break;
				case 2: Documentos.add((Documento)leApolice());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Documento para entrada NÃO escolhido!");
				}

				break;
			case 2: 
				if (Documentos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com Documentos primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < Documentos.size(); i++)	{
					dados += Documentos.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: 
				if (Documentos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com Documentos  primeiramente");
					break;
				}
				Documentos.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: 
				if (Documentos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com Documentos  primeiramente");
					break;
				}
				salvaDocumentos(Documentos);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: 
				Documentos = recuperaDocumentos();
				if (Documentos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo Seguradora");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		Seguradora seg = new Seguradora ();
		seg.menuSeguradora();

	}

}
