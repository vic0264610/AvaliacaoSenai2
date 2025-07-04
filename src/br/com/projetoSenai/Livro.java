package br.com.projetoSenai;

public class Livro {
private String titulo;
private String autor;
private boolean disponivel;

public Livro(String titulo, String autor, boolean disponivel) {
	  this.titulo = titulo;
      this.autor = autor;
      this.disponivel = true;
  
}

public String getTitulo() {
	return titulo;
}

public String getAutor() {
	return autor;
}

public boolean isDisponivel() {
    return disponivel;
}

public void emprestar() {
    disponivel = false;
}

public void devolver() {
    disponivel = true;
}

@Override
public String toString() {
    return "Livro: " + titulo;
}

}
