package br.com.zgsolucoes.maratonapokerhand.model

import br.com.zgsolucoes.maratonapokerhand.mao.Mao
import br.com.zgsolucoes.maratonapokerhand.model.Carta
import br.com.zgsolucoes.maratonapokerhand.model.Jogador

class Rodada {

	BigInteger codigo
	List<Carta> cartas
	List<Jogador> jogadores

	Rodada(BigInteger codigo, List<Carta> cartas, List<Jogador> jogadores) {
		this.codigo = codigo
		this.cartas = cartas
		this.jogadores = jogadores
	}

	ResultadoRodada obtenhaResultado() {

	}

	void calculeMelhoresMaos(List<Mao> maos) {
		for (Jogador jogador in jogadores) {
			for (Mao mao in maos) {
				jogador.melhorMao = mao.reconhecerMao(jogador.extrairMaoHelper(cartas))
			}
		}
	}


}
