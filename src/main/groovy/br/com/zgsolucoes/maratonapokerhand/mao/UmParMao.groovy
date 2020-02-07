package br.com.zgsolucoes.maratonapokerhand.mao

class UmParMao extends Mao {

	@Override
	ResultadoMao reconhecerMao(final MaoHelper maoHelper) {
		return maoHelper.grupoExiste && maoHelper.qtdPrimeiraCombinacao == 2 ?
			   new ResultadoMao(Categoria.UM_PAR, maoHelper.primeiraCombinacao) : null
	}
}
