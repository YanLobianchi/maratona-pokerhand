package br.com.zgsolucoes.maratonapokerhand.model

import br.com.zgsolucoes.maratonapokerhand.mao.MaoHelper
import br.com.zgsolucoes.maratonapokerhand.mao.ResultadoMao

class Jogador implements Comparable<Jogador> {
	private MaoHelper maoHelper
	List<Carta> cartas
	ResultadoMao melhorMao


	Jogador(List<Carta> cartas) {
		this.cartas = cartas
	}

	MaoHelper extrairMaoHelper(List<Carta> cartasMesa) {
		if (!maoHelper) {
			maoHelper = new MaoHelper(cartas + cartasMesa)
		}
		return maoHelper
	}

	@Override
	int compareTo(Jogador o) {
		return 0
	}

}
