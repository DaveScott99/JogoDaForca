package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Jogo {

	static String palavraSecreta = "";
	static String palavraAdivinhada = "";

	static String caminhoArquivo = "";

	static String letrasUsadas = "";

	static Scanner sc = new Scanner(System.in);

	static int maximoTentativas = 10;

	public static void criarArquivoDePalavras() {

		System.out.printf("%nVocê escolheu criar um novo arquivo de palavras.%n");

		System.out.print("Digite o caminho para salvar o arquivo: ");
		caminhoArquivo = sc.next();

		System.out.print("Quantas palavras irá digitar? ");
		int n = sc.nextInt();

		String[] palavras = new String[n];

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {

			for (String linha : palavras) {
				System.out.print("Digite a palavra: ");
				linha = sc.next();
				bw.write(linha);
				bw.newLine();
			}

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}

	public static void carregarPalavraSecreta() {

		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {

			String linhaArquivo = br.readLine();

			while (linhaArquivo != null) {

				String[] palavra = linhaArquivo.split(",");
				int indice = (int) (Math.random() * palavra.length);
				palavraSecreta = palavra[indice].toUpperCase();

				linhaArquivo = br.readLine();
			}

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}

	public static void tituloJogo() {
		System.out.println("***********************************");
		System.out.println("    Bem vindo ao jogo da Forca!   ");
		System.out.println("***********************************");
		System.out.println();
	}

	public static void menuJogo() {

		System.out.printf("-> Para jogar selecione criar um novo arquivo de palavras <-%n%n");

		System.out.println("(1) Jogar");
		System.out.println("(2) Criar um novo arquivo com palavras");
		System.out.println("(3) Sair");

		System.out.println();

		System.out.print("Escolha: ");

		int escolha = sc.nextInt();

		if (escolha == 1) {
			jogo();
		} else if (escolha == 2) {
			criarArquivoDePalavras();
			menuJogo();
		} else if (escolha == 3) {
			System.out.println("Que pena fica pra uma próxima então.");
			System.exit(0);
		} else {
			System.out.printf("%nOpção inválida! Comece novamente.%n");
			menuJogo();
		}

	}

	public static void novoJogo() {

		int novoJogo = 1;

		do {

			switch (novoJogo) {
			case 1:
				menuJogo();
				break;
			case 2:
				System.out.println("Obrigado por jogar!!");
				System.exit(0);
				break;
			}

		} while (novoJogo == 1);

	}

	public static void jogo() {

		carregarPalavraSecreta();

		for (int i = 0; i < palavraSecreta.length(); i++) {

			palavraAdivinhada += "_";

			for (int tentativas = 0;; tentativas++) {

				if (tentativas == maximoTentativas) {
					System.out.printf("Puxa, você for enforcado!%n A palavra era '%s'%n", palavraSecreta);
					System.out.println("    _______________      ");
					System.out.println("   /               \\     ");
					System.out.println("  /                 \\    ");
					System.out.println(" /                   \\ ");
					System.out.println(" |    XXXX    XXXX    |  ");
					System.out.println(" |    XXXX    XXXX    |  ");
					System.out.println(" |    XXX      XXX    |   ");
					System.out.println(" |                    |   ");
					System.out.println(" \\__       XXX     __/   ");
					System.out.println("   |\\      XXX    /|     ");
					System.out.println("   | |           |  |     ");
					System.out.println("   | I I I I I I I  |     ");
					System.out.println("   |  I I I I I I   |     ");
					System.out.println("   \\_             _/     ");
					System.out.println("     \\_         _/       ");
					System.out.println("       \\_______/         ");
					System.exit(0);
				}

				System.out.print("Qual a letra? ");
				char chuteInput = sc.next().charAt(0);

				char chute = Character.toUpperCase(chuteInput);

				if (letrasUsadas.indexOf(chute) >= 0) {
					System.out.printf("Voce já tentou a letra %c.%n%n", chute);
				} else {
					letrasUsadas += chute;

					if (palavraSecreta.indexOf(chute) >= 0) {
						palavraAdivinhada = "";

						for (int j = 0; j < palavraSecreta.length(); j++) {
							palavraAdivinhada += letrasUsadas.indexOf(palavraSecreta.charAt(j)) >= 0
									? palavraSecreta.charAt(j)
									: "_";
						}

						if (palavraAdivinhada.contains("_")) {
							System.out.println();
							System.out.println(palavraAdivinhada);
							System.out.println();
						} else {
							System.out.println("Parabens! A palavra adivinhada era " + palavraAdivinhada);

							System.out.println("     .-=========-.     ");
							System.out.println("     \\'-=======-'/    ");
							System.out.println("     _|   .=.   |_     ");
							System.out.println("    ((|  {{1}}  |))   ");
							System.out.println("     \\|   /|\\   |/   ");
							System.out.println("      \\__ '`' __/      ");
							System.out.println("        _`) (`_          ");
							System.out.println("      _/_______\\_      ");
							System.out.println("     /___________\\    ");

							System.exit(0);
						}
					} else {
						System.out.printf("%nA letra %c não existe na palavra.%n%n", chute);
					}

				}

			}

		}

	}

}
