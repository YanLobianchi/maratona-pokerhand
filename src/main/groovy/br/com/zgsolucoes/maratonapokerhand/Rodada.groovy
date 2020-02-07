package br.com.zgsolucoes.maratonapokerhand

import br.com.zgsolucoes.maratonapokerhand.mao.Mao
import br.com.zgsolucoes.maratonapokerhand.mao.ResultadoMao
import br.com.zgsolucoes.maratonapokerhand.model.Carta
import br.com.zgsolucoes.maratonapokerhand.model.Jogador

class Rodada {

	BigInteger codigo
	List<Carta> cartas
	List<Jogador> jogadores

	Rodada(BigInteger codigo, List<Carta> cartas, List<Jogador> jogadores, List<Mao> maos) {
		this.codigo = codigo
		this.cartas = cartas
		this.jogadores = jogadores

		calculeMelhoresMaos(maos)
	}

	void calculeMelhoresMaos(List<Mao> maos) {
		for (Jogador jogador in jogadores) {
			for (Mao mao in maos) {
				jogador.melhorMao = mao.reconhecerMao(jogador.extrairMaoHelper(cartas))
			}
		}
	}


	List<ResultadoMao> resutados() {
		return jogadores.melhorMao
	}

}
