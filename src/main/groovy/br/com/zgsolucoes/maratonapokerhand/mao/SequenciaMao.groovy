package br.com.zgsolucoes.maratonapokerhand.mao

class SequenciaMao extends Mao {

	@Override
	ResultadoMao reconhecerMao(final MaoHelper maoHelper) {
		return maoHelper.cartasEmSequencia ? new ResultadoMao(Categoria.SEQUENCIA, maoHelper.cartasEmSequencia) : null
	}
}
