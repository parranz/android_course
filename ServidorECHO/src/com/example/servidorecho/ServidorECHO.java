package com.example.servidorecho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorECHO {

	public static void main(String args[]) {
		try {
			ServerSocket sk = new ServerSocket(7);
			while (true) {
				Socket cliente = sk.accept();
				BufferedReader entrada = new BufferedReader(
						new InputStreamReader(cliente.getInputStream()));
				PrintWriter salida = new PrintWriter(new OutputStreamWriter(
						cliente.getOutputStream()), true);
				String datos = entrada.readLine();
				salida.println(datos);
				cliente.close();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}