package br.com.zgsolucoes.maratonapokerhand.mao

class FullHouseMao extends Mao {

	@Override
	ResultadoMao reconhecerMao(final MaoHelper maoHelper) {
		return maoHelper.grupoExiste && maoHelper.grupoTemDuasCombinacoes &&
					   maoHelper.qtdPrimeiraCombinacao == 2 && maoHelper.qtdSegundaCombinacao ?
			   new ResultadoMao(Categoria.FULL_HOUSE, maoHelper.primeiraCombinacao + maoHelper.segundaCombinacao) : null
	}
}
