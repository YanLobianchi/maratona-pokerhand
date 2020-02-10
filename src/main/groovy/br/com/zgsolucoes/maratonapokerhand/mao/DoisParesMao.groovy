package br.com.zgsolucoes.maratonapokerhand.mao

class DoisParesMao extends Mao {

	@Override
	ResultadoMao reconhecerMao(final MaoHelper maoHelper) {
		return maoHelper.grupoTemDuasCombinacoes && maoHelper.qtdPrimeiraCombinacao == 2 && maoHelper.qtdSegundaCombinacao == 2 ?
			   new ResultadoMao(Categoria.DOIS_PARES, maoHelper.primeiraCombinacao + maoHelper.segundaCombinacao) : null
	}
}
