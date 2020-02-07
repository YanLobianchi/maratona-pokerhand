package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.ValorCarta

class RoyalFlushMao extends Mao {

	@Override
	ResultadoMao reconhecerMao(final MaoHelper maoHelper) {
		return maoHelper.cartasEmSequencia && maoHelper.cartasMesmoNaipe &&
					   maoHelper.cartasEmSequencia == maoHelper.cartasMesmoNaipe && maoHelper.cartasEmSequencia.last().valorCarta == ValorCarta.AS ?
			   new ResultadoMao(Categoria.ROYAL_FLUSH, maoHelper.cartasEmSequencia) : null
	}
}
