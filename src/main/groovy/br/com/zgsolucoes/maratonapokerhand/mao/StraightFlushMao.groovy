package br.com.zgsolucoes.maratonapokerhand.mao

class StraightFlushMao extends Mao {

	@Override
	ResultadoMao reconhecerMao(final MaoHelper maoHelper) {
		return maoHelper.cartasEmSequencia == maoHelper.cartasMesmoNaipe ? new ResultadoMao(Categoria.STRAIGHT_FLUSH, maoHelper.cartasEmSequencia) : null
	}
}
