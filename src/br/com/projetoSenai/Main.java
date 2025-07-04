package br.com.projetoSenai;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Livro> livros = new ArrayList<>();
        Map<String, Queue<String>> filaEspera = new HashMap<>();

        boolean executando = true;
        while (executando) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Cadastrar novo livro");
            System.out.println("2. Mostrar livros disponíveis");
            System.out.println("3. Emprestar livro");
            System.out.println("4. Devolver livro");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título do livro: ");
                    String tituloCadastro = scanner.nextLine();

                    System.out.print("Digite o autor do livro: ");
                    String autorCadastro = scanner.nextLine();

                    livros.add(new Livro(tituloCadastro, autorCadastro));
                    System.out.println("Livro cadastrado com sucesso!\n");
                    break;

                case 2:
                    System.out.println("\n--- Livros Disponíveis ---");
                    boolean algumDisponivel = false;
                    for (Livro livro : livros) {
                        if (livro.isDisponivel()) {
                            System.out.println(livro);
                            algumDisponivel = true;
                        }
                    }
                    if (!algumDisponivel) {
                        System.out.println("Nenhum livro disponível no momento.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o título do livro: ");
                    String tituloEmprestimo = scanner.nextLine();
                    System.out.print("Digite seu nome: ");
                    String usuario = scanner.nextLine();

                    boolean encontrado = false;
                    for (Livro livro : livros) {
                        if (livro.getTitulo().equalsIgnoreCase(tituloEmprestimo)) {
                            encontrado = true;
                            if (livro.isDisponivel()) {
                                livro.emprestar();
                                System.out.println(usuario + " pegou o livro: " + tituloEmprestimo);
                            } else {
                                System.out.println("Livro indisponível. Adicionando " + usuario + " à fila de espera.");
                                filaEspera.putIfAbsent(tituloEmprestimo, new LinkedList<>());
                                filaEspera.get(tituloEmprestimo).add(usuario);
                            }
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Livro não encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o título do livro a devolver: ");
                    String tituloDevolucao = scanner.nextLine();

                    for (Livro livro : livros) {
                        if (livro.getTitulo().equalsIgnoreCase(tituloDevolucao)) {
                            livro.devolver();
                            Queue<String> fila = filaEspera.get(tituloDevolucao);
                            if (fila != null && !fila.isEmpty()) {
                                String proximo = fila.poll();
                                livro.emprestar();
                                System.out.println("Livro emprestado automaticamente para: " + proximo);
                            } else {
                                System.out.println("Livro devolvido e agora disponível.");
                            }
                            break;
                        }
                    }
                    break;

                case 5:
                    executando = false;
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
