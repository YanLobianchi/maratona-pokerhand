package br.com.zgsolucoes.maratonapokerhand.mao

class FullHouseMao extends Mao {

	@Override
	ResultadoMao reconhecerMao(final MaoHelper maoHelper) {
		return maoHelper.grupoExiste && maoHelper.grupoTemDuasCombinacoes &&
					   maoHelper.qtdPrimeiraCombinacao == 3 && maoHelper.qtdSegundaCombinacao == 2 ?
			   new ResultadoMao(Categoria.FULL_HOUSE, maoHelper.primeiraCombinacao + maoHelper.segundaCombinacao) : null
	}
}
