package br.com.zgsolucoes.maratonapokerhand.model

class ResultadoRodada {

	Rodada rodada
	List<Jogador> jogadores
	Ranking ranking

	ResultadoRodada(Rodada rodada, Ranking ranking, Jogador... jogadores) {
		this.rodada = rodada
		this.jogadores = jogadores
		this.ranking = ranking
	}
}
