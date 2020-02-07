package br.com.zgsolucoes.maratonapokerhand.model

import br.com.zgsolucoes.maratonapokerhand.mao.MaoHelper
import br.com.zgsolucoes.maratonapokerhand.mao.ResultadoMao

class Jogador implements Comparable<Jogador> {
	List<Carta> cartas
	MaoHelper maoHelper
	ResultadoMao melhorMao


	Jogador(List<Carta> cartas) {

		this.cartas = cartas
	}


	void adicionarCartas(List<Carta> cartasMesa) {
		cartas.addAll(cartasMesa)
	}

	@Override
	int compareTo(Jogador o) {
		return 0
	}

}
