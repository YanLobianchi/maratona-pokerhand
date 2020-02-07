package br.com.zgsolucoes.maratonapokerhand.model


import br.com.zgsolucoes.maratonapokerhand.mao.Mao

import java.util.stream.Collectors

class Rodada {

	Long codigo
	List<Carta> cartas
	List<Jogador> jogadores

	Rodada(Long codigo, List<Carta> cartas, List<Jogador> jogadores) {
		this.codigo = codigo
		this.cartas = cartas
		this.jogadores = jogadores
	}

	ResultadoRodada obtenhaResultado() {
		if (!jogadores) {
			return null
		}

		final List<Jogador> jogadoresOrdenados = jogadores.stream().sorted().collect(Collectors.toList())
		final Jogador jogadorComMaiorPontuacao = jogadoresOrdenados.last()

		final List<Jogador> empatados = []

		for (int i = jogadoresOrdenados.size() - 1; i > -1; i--) {
			final Jogador jogadorAnterior = jogadoresOrdenados[i + 1]
			final Jogador jogadorAtual = jogadoresOrdenados[i]
			if (jogadorAnterior <=> jogadorAtual == 0) {
				empatados.addAll(jogadorAnterior, jogadorAtual)
			}
		}

		return !empatados.empty && empatados.first() == jogadorComMaiorPontuacao ?
			   new ResultadoRodada(this, Ranking.EMPATE, (Jogador[]) empatados.toArray()) :
			   new ResultadoRodada(this, Ranking.VITORIA, jogadorComMaiorPontuacao)
	}

	void calculeMelhoresMaos(List<Mao> maos) {
		for (Jogador jogador in jogadores) {
			for (Mao mao in maos) {
				jogador.melhorMao = mao.reconhecerMao(jogador.extrairMaoHelper(cartas))
			}
		}
	}


}
