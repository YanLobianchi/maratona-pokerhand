import br.com.zgsolucoes.maratonapokerhand.service.HttpService
import spock.lang.Specification

class HttpServiceSpec extends Specification {

	void 'testa resultado requisicao'() {
		setup:
		HttpService httpAcces = new HttpService()
		String url = 'http://172.22.1.41:8080/poker-game/arquivo/index'

		when:
		List response = httpAcces.sendRequest(url, 'GET')

		then:
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
