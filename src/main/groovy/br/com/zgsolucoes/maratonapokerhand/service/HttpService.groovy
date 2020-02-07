package br.com.zgsolucoes.maratonapokerhand.service

import com.google.gson.Gson
import org.apache.http.HttpHeaders
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.client.methods.RequestBuilder
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils

class HttpService {


	List<String> sendRequest(String url, String method, Map<String, Object> params = null) {
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(2000)
				.setSocketTimeout(3000)
				.build()
		HttpUriRequest request = null

		if (params) {
			StringEntity entity = new StringEntity(new Gson().toJson(params), "UTF-8")
			request = RequestBuilder.create(method)
					.setConfig(requestConfig)
					.setUri(url)
					.setHeader(HttpHeaders.CONTENT_TYPE, "text/html")
					.addHeader('Cookie', "state=${params.getAt('state')}; JSESSIONID=${params.getAt('JSESSIONID')}")
					.setEntity(entity)
					.build()
		} else {
			request = RequestBuilder.create(method)
					.setConfig(requestConfig)
					.setUri(url)
					.build()
		}

		String req = "REQUEST:" + "\n" + request.getRequestLine() + "\n" + "Headers: " +
				request.getAllHeaders() + "\n" + EntityUtils.toString() + "\n"
		HttpClientBuilder.create().build().withCloseable { httpClient ->

			httpClient.execute(request).withCloseable { response ->

				String res = "RESPONSE:" + "\n" + response.getStatusLine() + "\n" + "Headers: " +
						response.getAllHeaders() + "\n" +
						(response.getEntity() != null ? EntityUtils.toString(response.getEntity()) : "") + "\n"
				return Arrays.asList(req, res)
			}
		}
	}

	List<String> buscaJogosSite(){

		List<String> jogos = []
		URL urlBase = new URL(HttpServiceHelp.urlBase+'/index')
		HttpURLConnection primeiraRequisicao = (HttpURLConnection) urlBase.openConnection()
		primeiraRequisicao.setRequestMethod('GET')

		String resultadoPrimeiraReq = (String) primeiraRequisicao.content.text
		List<String> codigosObtidos = HttpServiceHelp.obtenhaLinksJogos(resultadoPrimeiraReq) //resultadoPrimeiraReq.findAll('(?<=code=)[^"]+')
		String sessionId = HttpServiceHelp.obtenhaSession(primeiraRequisicao)
		HttpURLConnection sessaoAtual = primeiraRequisicao

		for(String codigo in codigosObtidos){
			URL url2 = new URL(HttpServiceHelp.urlBase+"/arquivos?code=${codigo}")
			HttpURLConnection segundaRequisicao = (HttpURLConnection) url2.openConnection()
			HttpServiceHelp.setaParamsRequiscao(segundaRequisicao, sessaoAtual, sessionId)
			sessaoAtual = segundaRequisicao

			String resultadoSegundaReq = (String) segundaRequisicao.content.text
			List<String> pastasObtidas = HttpServiceHelp.obtenhaLinksArquivos(resultadoSegundaReq) //resultadoSegundaReq.findAll('(?<=arquivo\\?id=)[^"]+')

			for(String pasta in pastasObtidas){
				URL url3 = new URL(HttpServiceHelp.urlBase+"/arquivo?id=${pasta}")
				HttpURLConnection terceiraRequisicao = (HttpURLConnection) url3.openConnection()
				HttpServiceHelp.setaParamsRequiscao(terceiraRequisicao, sessaoAtual, sessionId)
				sessaoAtual = terceiraRequisicao

				String resultadoTerceiraReq = (String) terceiraRequisicao.content.text
				jogos.add(resultadoTerceiraReq)

				if (jogos.size() == 5){
					break
				} else{
					continue
				}
			}
		}
        return jogos
	}

	List<String> obtenhaRodadas() {

	}
}
