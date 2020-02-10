package br.com.zgsolucoes.maratonapokerhand.mao

class FlushMao extends Mao {

	@Override
	ResultadoMao reconhecerMao(final MaoHelper maoHelper) {
		return maoHelper.cartasMesmoNaipe &&
					   maoHelper.cartasEmSequencia != maoHelper.cartasMesmoNaipe ?
			   new ResultadoMao(Categoria.FLUSH, maoHelper.cartasMesmoNaipe) : null
	}
}
