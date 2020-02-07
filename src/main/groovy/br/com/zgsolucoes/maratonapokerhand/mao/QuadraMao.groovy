package br.com.zgsolucoes.maratonapokerhand.mao

class QuadraMao extends Mao {

	@Override
	ResultadoMao reconhecerMao(final MaoHelper maoHelper) {
		return maoHelper.grupoExiste && maoHelper.qtdPrimeiraCombinacao == 4 ?
			   new ResultadoMao(Categoria.QUADRA, maoHelper.primeiraCombinacao) : null
	}
}
