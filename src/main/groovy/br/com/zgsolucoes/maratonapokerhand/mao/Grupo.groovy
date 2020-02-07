package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta

class Grupo {

	List<Carta> primeiraCombinacao
	List<Carta> segundaCombinacao

	Grupo(List<Carta> primeiraCombinacao, List<Carta> segundaCombinacao) {
		this.primeiraCombinacao = primeiraCombinacao
		this.segundaCombinacao = segundaCombinacao
	}

	Boolean existe(){
		return qtdPrimeiraCombinacao > 1 && possuiGrupo2()
	}

	Boolean possuiGrupo2() {
		return qtdSegundaCombinacao > 1
	}

	BigInteger getQtdPrimeiraCombinacao() {
		return primeiraCombinacao.size()
	}

	BigInteger getQtdSegundaCombinacao() {
		return segundaCombinacao.size()
	}
}
