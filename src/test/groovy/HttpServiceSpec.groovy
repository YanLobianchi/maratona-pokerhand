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
		String jsessionid = HttpServiceHelp.obtenhaJSessionId(response)
		List response2 = httpAcces.sendRequest(urlRequisicao2, 'GET', HttpServiceHelp.obtenhaCookie(response, jsessionid))
		List response3 = httpAcces.sendRequest(urlRequisicao3, 'GET', HttpServiceHelp.obtenhaCookie(response2, jsessionid))

		then:
		response2
		response3
		response
	}

    void 'testa obtem quantidade de rodadas'() {
		setup:
		HttpService httpService = new HttpService()

		when:
        List response = httpService.sendSimpleRequest()

		then:
		response.size() == 4724
	}

}
