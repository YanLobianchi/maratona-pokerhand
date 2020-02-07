package br.com.zgsolucoes.maratonapokerhand.model

class ResultadoRodada {

	List<Jogador> jogadores
	Ranking ranking

	ResultadoRodada(Ranking ranking, Jogador... jogadores) {
		this.jogadores = jogadores
		this.ranking = ranking
	}
}
