import br.com.zgsolucoes.maratonapokerhand.Rodada
import br.com.zgsolucoes.maratonapokerhand.model.Carta
import br.com.zgsolucoes.maratonapokerhand.service.HttpService
import br.com.zgsolucoes.maratonapokerhand.service.HttpServiceHelp
import spock.lang.Specification

class HttpServiceSpec extends Specification {

	void 'testa resultado requisicao'() {
		setup:
		HttpService httpAcces = new HttpService()
		String urlRequisicao1 = 'http://172.22.1.41:8080/poker-game/arquivo/index'
		String urlRequisicao2 = 'http://172.22.1.41:8080/poker-game/arquivo/arquivos?code=HH20161020'
		String urlRequisicao3 = 'http://172.22.1.41:8080/poker-game/arquivo/arquivo?id=SEgyMDE2MTAyMCBUMTcwMjY3MDA0OSBObyBMaW1pdCBIb2xkJ2VtICQwLDIzICsgJDAsMDIudHh0LnhtbA=='

		when:
		List response = httpAcces.sendRequest(urlRequisicao1, 'GET')
		String jsessionid = response.get(1).find('JSESSIONID=[^\\}\\;]+')
		List response2 = httpAcces.sendRequest(urlRequisicao2, 'GET', HttpServiceHelp.obtenhaCookie(response, jsessionid))
		List response3 = httpAcces.sendRequest(urlRequisicao3, 'GET', HttpServiceHelp.obtenhaCookie(response2, jsessionid))

		then:
		response2
		response3
		response
	}

    void 'testa obtem quantidade de rodadas'() {

	}

    void 'testa '() {
		setup:
		HttpService httpService = new HttpService()

		when:
        List response = httpService.buscaJogosSite()

        then:
        response.size() == 4724
    }

    void 'teste obtenção de rodadas'() {
        setup:
        String text = """<poker>
        <h0>
        <board>2s Tc 9d 9c 7h</board>
        <p0>Ah Qc</p0>
        <p1>4c Kd</p1>
        </h0>
        <h1>
        <board>Ah 6h 2s 5h 7d</board>
        <p0>9h Jd</p0>
        <p1>Qc 6s</p1>
        </h1>
        <h2>
        <board>Jh 5h 9h 8h 8c</board>
        <p0>2c 3c</p0>
        <p1>Qd 4d</p1>
        </h2>
        <h3>
        <board>6c 6h 3c 4s 5s</board>
        <p0>8c Kc</p0>
        <p1>3d Ah</p1>
        </h3>
        </poker>"""

        when:
        text

        then:
        List<String> rodadasRegex = text.findAll('(?s)<h\\d+>.+?<\\/h\\d+>')

        List<Rodada> listaStringRodadas = new ArrayList<>()

        for (String rodada in rodadasRegex) {

            List<Carta> listaCartaMesa = []

            String cartasDaMesaRegex = rodadasRegex[0].find('(?<=<board>).+(?=<\\/board>)')

            List<String> listStringCartasMesa = cartasDaMesaRegex.split(' ')

            for (String cartaMesa in listStringCartasMesa) {

                cartaMesa
            }

            List<String> jogadores = rodadasRegex[0].findAll('<p\\d+>.+<\\/p\\d+>')
            List<String> cartaJogadores = []
            for (String jogador in jogadores) {
                cartaJogadores.add(jogador.find('(?<=>)[^<]+'))
            }

            rodada
            cartaJogadores
        }

        text
    }

}
