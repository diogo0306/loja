package br.com.gestao.view.service.webservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("D:\\Apresentação Evandro Tenorio.pdf");
		FileInputStream fileInputStream = new FileInputStream(file);
		byte[] byteVar = IOUtils.toByteArray(fileInputStream);
		
		FileTransfererImplService fileTransferer = new FileTransfererImplService();
		FileTransferer fileTransferer2 = fileTransferer.getFileTransfererImplPort();
		fileTransferer2.uploadArquivoClinicaPDF("CARDIO CLINICA","Nome Arquivo.pdf", byteVar);
		

	}

}
