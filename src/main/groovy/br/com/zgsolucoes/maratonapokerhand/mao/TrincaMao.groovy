package br.com.zgsolucoes.maratonapokerhand.mao

class TrincaMao extends Mao {

	@Override
	ResultadoMao reconhecerMao(final MaoHelper maoHelper) {
		return maoHelper.grupoExiste && maoHelper.qtdPrimeiraCombinacao == 3 ?
			   new ResultadoMao(Categoria.TRINCA, maoHelper.primeiraCombinacao) : null
	}
}
