package br.com.zgsolucoes.maratonapokerhand

import br.com.zgsolucoes.maratonapokerhand.mao.Mao
import br.com.zgsolucoes.maratonapokerhand.model.Carta
import br.com.zgsolucoes.maratonapokerhand.model.Jogador

class Rodada {

	BigInteger codigo
	List<Carta> cartas
	List<Jogador> jogadores

	void calculeMelhoresMaos(List<Mao> maos) {
		for (Jogador jogador in jogadores) {
			jogador.adicionarCartas(cartas)
			for (mao in maos) {
				jogador.melhorMao = mao.reconhecerMao(jogador.maoHelper)
			}
		}
	}
}
