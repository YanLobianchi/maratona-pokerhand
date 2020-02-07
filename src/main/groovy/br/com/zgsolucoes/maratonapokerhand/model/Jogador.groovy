package br.com.zgsolucoes.maratonapokerhand.model

import br.com.zgsolucoes.maratonapokerhand.mao.MaoHelper
import br.com.zgsolucoes.maratonapokerhand.mao.ResultadoMao

import java.util.stream.Collectors

class Jogador implements Comparable<Jogador> {
	private MaoHelper maoHelper
	List<Carta> cartas
	ResultadoMao melhorMao
	Carta maiorCarta

	Jogador(List<Carta> cartas) {
		this.cartas = cartas.stream().sorted().collect(Collectors.toList())
		this.maiorCarta = cartas.last()
	}

	MaoHelper extrairMaoHelper(List<Carta> cartasMesa) {
		if (!maoHelper) {
			maoHelper = new MaoHelper(cartas + cartasMesa)
		}
		return maoHelper
	}

	@Override
	int compareTo(Jogador o) {
		if (melhorMao?.categoria > o.melhorMao?.categoria) {
			return 1
		}
		if (melhorMao?.categoria < o.melhorMao?.categoria) {
			return -1
		}
		return maiorCarta > o.maiorCarta ? 1 : (maiorCarta < o.maiorCarta ? -1 : 0)
	}

}
