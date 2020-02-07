package br.com.zgsolucoes.maratonapokerhand.mao

class Grupo {

	BigInteger primeiraCombinacao = 0
	BigInteger segundaCombinacao = 0

	Grupo(BigInteger primeiraCombinacao, BigInteger segundaCombinacao) {
		this.primeiraCombinacao = primeiraCombinacao
		this.segundaCombinacao = segundaCombinacao
	}

	Boolean existe(){
		return primeiraCombinacao > 1 && possuiGrupo2()
	}

	Boolean possuiGrupo2() {
		return segundaCombinacao > 1
	}
}
