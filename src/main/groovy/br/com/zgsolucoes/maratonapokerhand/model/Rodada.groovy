package br.com.zgsolucoes.maratonapokerhand.model


import br.com.zgsolucoes.maratonapokerhand.mao.Mao
import br.com.zgsolucoes.maratonapokerhand.mao.ResultadoMao

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

	ResultadoRodada obtenhaResultado(List<Mao> maos) {
		if (!jogadores) {
			return null
		}

		calculeMelhoresMaos(maos)

		final List<Jogador> jogadoresOrdenados = jogadores.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList())
		final Jogador jogadorComMaiorPontuacao = jogadoresOrdenados.first()

		final List<Jogador> empatados = []

		for (int i = 1; i < jogadoresOrdenados.size(); i++) {
			final Jogador jogadorAnterior = jogadoresOrdenados[i - 1]
			final Jogador jogadorAtual = jogadoresOrdenados[i]
			if (jogadorAnterior <=> jogadorAtual == 0) {
				empatados.addAll(jogadorAnterior, jogadorAtual)
			}
		}

		return !empatados.empty && empatados.first() == jogadorComMaiorPontuacao ?
			   new ResultadoRodada(this, Ranking.EMPATE, (Jogador[]) empatados.toArray()) :
			   new ResultadoRodada(this, Ranking.VITORIA, jogadorComMaiorPontuacao)
	}

	private void calculeMelhoresMaos(List<Mao> maos) {
		try {
			for (final Jogador jogador : jogadores) {
				if (!jogador.melhorMao) {
					for (final Mao mao : maos) {
						final ResultadoMao melhorMao = mao.reconhecerMao(jogador.extrairMaoHelper(cartas))
						if (melhorMao && (!jogador.melhorMao || melhorMao > jogador.melhorMao)) {
							jogador.melhorMao = melhorMao
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace()
		}
	}


}
