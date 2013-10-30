package bamesys_lucas

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.*
import org.zkoss.zul.*
import org.zkoss.zk.ui.select.annotation.*
import org.zkoss.zk.ui.util.Clients;

import bamesys_lucas.*;

class PessoaJuridicaComposer extends zk.grails.Composer {

	
	Div wLista, wCadastro, wCons, wBotoes
	
	Listbox lstPJ
	
	Intbox id, porcentagemvendas, nsocios, nfiliais, nfuncionarios, id_representante1, id_representante2, id_representante3
	Intbox porcentagem1, porcentagem2, porcentagem3, nbanco, numero
	
	Textbox filial, representante, atendente, razaosocial, cnpj, fantasia, ie, im, endereco, complemento 
	Textbox bairro, cep, cidade, estado, telefone, fax, email, website, contador, crc, telefonecontador, atividadeprincipal
	Textbox faturamentoultimomes, faturamentoultimos12meses, nomecontato, cargocontato, sociedade, capital
	Textbox assinatura, cotista1, cotista2, cotista3, documento1, documento2, documento3, valor1, valor2, valor3, nomebanco
	Textbox nagencia, contacorrente, telefonebanco, ref1, ref2, ref3, ref4, docref1, docref2, docref3, docref4
	Textbox foneref1, foneref2, foneref3, foneref4, login, senha, senharestrita,bdboxBusca
	
	Datebox dataconstituicao, dataultimaalteracao, dataabertura
	
	Combobox cmbpossuifiliais
   	
	Textbox  confilial,conrepresentante, conatendente, conrazaosocial, concnpj, confantasia
	Label conie, conim, conendereco, connumero
	
	
	
	
	
    def afterCompose = { window ->
		wCadastro.visible = false
		wLista.visible = true
		wCons.visible = false
		wBotoes.visible = false
		listarPJ()
    }

	void listarPJ(){
		
		def lista = PessoaJuridica.createCriteria().list {
		      like("filial", "%"+bdboxBusca.text+"%")
		    }
		
		lstPJ.items.clear()
		
		lstPJ.append {
			lista.each{ pessoaj ->
				listitem(value: pessoaj) { item ->
					listcell(label: pessoaj.id)
					listcell(label: pessoaj.filial)
					listcell(label: pessoaj.representante)
					listcell(label: pessoaj.atendente)
					listcell(label: ""){
						hlayout{
							toolbarbutton(label: 'Visualizar', image: "/images/visualizar.png",
								onClick: { e-> this.visualPessoa(item);
								} )
							toolbarbutton(label: 'Editar', image: "/images/editar.png",
								onClick: { e-> this.editarPessoa(item);
								} )
							toolbarbutton(label: 'Excluir', image: "/images/excluir.png",
								onClick: { e-> this.excluirPJ(item);
								} )
							
						}
					}
				}
			}
		}
	}
	
	
	
	void visualPessoa(Listitem item) {
		PessoaJuridica upj=item.value
		
		id.value = upj.id
		filial.value= upj.filial
		representante.value=upj.representante
		
		atendente.value= upj.atendente
		razaosocial.value= upj.razaosocial
		cnpj.value= upj.cnpj
		fantasia.value=upj.fantasia
		ie.value=upj.ie
		im.value=upj.im
		endereco.value=upj.endereco
		numero.value=upj.numero
		complemento.value=upj.complemento
		bairro.value=upj.bairro
		cidade.value=upj.cidade
		estado.value=upj.estado
		cep.value=upj.cep
		website.value=upj.website
		telefone.value=upj.telefone
		fax.value=upj.fax
		email.value=upj.email
		contador.value=upj.contador
		crc.value=upj.crc
		telefonecontador.value=upj.telefonecontador
		atividadeprincipal.value=upj.atividadeprincipal
		porcentagemvendas.value=upj.porcentagemvendas
		faturamentoultimomes.value=upj.faturamentoultimomes
		faturamentoultimos12meses.value=upj.faturamentoultimos12meses
		nomecontato.value=upj.nomecontato
		cargocontato.value=upj.cargocontato
		sociedade.value=upj.sociedade
		dataconstituicao.value=upj.dataconstituicao
		capital.value=upj.capital
		dataultimaalteracao.value=upj.dataultimaalteracao
		nsocios.value=upj.nsocios
		cmbpossuifiliais.selectedItem=cmbpossuifiliais.items.find({it.value == upj.possuifiliais})
		
		nfiliais.value=upj.nfiliais
		nfuncionarios.value=upj.nfuncionarios
		id_representante1.value=upj.id_representante1
		id_representante2.value=upj.id_representante2
		id_representante3.value=upj.id_representante3
		assinatura.value=upj.assinatura
		cotista1.value=upj.cotista1
		documento1.value=upj.documento1
		porcentagem1.value=upj.porcentagem1
		valor1.value=upj.valor1
		
		cotista2.value=upj.cotista2
		documento2.value=upj.documento2
		porcentagem2.value=upj.porcentagem2
		valor2.value=upj.valor2
		
		cotista3.value=upj.cotista3
		documento3.value=upj.documento3
		porcentagem3.value=upj.porcentagem3
		valor3.value=upj.valor3
		
		nbanco.value=upj.nbanco
		nomebanco.value=upj.nomebanco
		nagencia.value=upj.nagencia
		contacorrente.value=upj.contacorrente
		dataabertura.value=upj.dataabertura
		telefonebanco.value=upj.telefonebanco
	
		ref1.value=upj.ref1
		docref1.value=upj.docref1
		foneref1.value=upj.foneref1
		
		ref2.value=upj.ref2
		docref2.value=upj.docref2
		foneref2.value=upj.foneref2
		
		ref3.value=upj.ref3
		docref3.value=upj.docref3
		foneref3.value=upj.foneref3
		
		ref4.value=upj.ref4
		docref4.value=upj.docref4
		foneref4.value=upj.foneref4
			
		login.value=upj.login
		senha.value=upj.senha
		senharestrita.value=upj.senharestrita
		
				
		wCadastro.visible = true
		wLista.visible = false
		wCons.visible = false
		wBotoes.visible = false
	}
	
	
	
	void editarPessoa(Listitem item) {
		PessoaJuridica upj=item.value
		
		id.value = upj.id
		filial.value= upj.filial
		representante.value=upj.representante
		
		atendente.value= upj.atendente
		razaosocial.value= upj.razaosocial
		cnpj.value= upj.cnpj
		fantasia.value=upj.fantasia
		ie.value=upj.ie
		im.value=upj.im
		endereco.value=upj.endereco
		numero.value=upj.numero
		complemento.value=upj.complemento
		bairro.value=upj.bairro
		cidade.value=upj.cidade
		estado.value=upj.estado
		cep.value=upj.cep
		website.value=upj.website
		telefone.value=upj.telefone
		fax.value=upj.fax
		email.value=upj.email
		contador.value=upj.contador
		crc.value=upj.crc
		telefonecontador.value=upj.telefonecontador
		atividadeprincipal.value=upj.atividadeprincipal
		porcentagemvendas.value=upj.porcentagemvendas
		faturamentoultimomes.value=upj.faturamentoultimomes
		faturamentoultimos12meses.value=upj.faturamentoultimos12meses
		nomecontato.value=upj.nomecontato
		cargocontato.value=upj.cargocontato
		sociedade.value=upj.sociedade
		dataconstituicao.value=upj.dataconstituicao
		capital.value=upj.capital
		dataultimaalteracao.value=upj.dataultimaalteracao
		nsocios.value=upj.nsocios
		cmbpossuifiliais.selectedItem=cmbpossuifiliais.items.find({it.value == upj.possuifiliais})
		
		nfiliais.value=upj.nfiliais
		nfuncionarios.value=upj.nfuncionarios
		id_representante1.value=upj.id_representante1
		id_representante2.value=upj.id_representante2
		id_representante3.value=upj.id_representante3
		assinatura.value=upj.assinatura
		cotista1.value=upj.cotista1
		documento1.value=upj.documento1
		porcentagem1.value=upj.porcentagem1
		valor1.value=upj.valor1
		
		cotista2.value=upj.cotista2
		documento2.value=upj.documento2
		porcentagem2.value=upj.porcentagem2
		valor2.value=upj.valor2
		
		cotista3.value=upj.cotista3
		documento3.value=upj.documento3
		porcentagem3.value=upj.porcentagem3
		valor3.value=upj.valor3
		
		nbanco.value=upj.nbanco
		nomebanco.value=upj.nomebanco
		nagencia.value=upj.nagencia
		contacorrente.value=upj.contacorrente
		dataabertura.value=upj.dataabertura
		telefonebanco.value=upj.telefonebanco
	
		ref1.value=upj.ref1
		docref1.value=upj.docref1
		foneref1.value=upj.foneref1
		
		ref2.value=upj.ref2
		docref2.value=upj.docref2
		foneref2.value=upj.foneref2
		
		ref3.value=upj.ref3
		docref3.value=upj.docref3
		foneref3.value=upj.foneref3
		
		ref4.value=upj.ref4
		docref4.value=upj.docref4
		foneref4.value=upj.foneref4
			
		login.value=upj.login
		senha.value=upj.senha
		senharestrita.value=upj.senharestrita
		
				
		wCadastro.visible = true
		wBotoes.visible = true
		wLista.visible = false
		wCons.visible = false
	}
	
	
	void excluirPJ(Listitem item) {
		Messagebox.show("Deseja realmente excluir?", "Confirmação", Messagebox.OK | Messagebox.IGNORE  | Messagebox.CANCEL, Messagebox.QUESTION,
			new EventListener() {
				void onEvent(Event evt) throws InterruptedException {
					if (evt.getName().equals("onOK")) {
						PessoaJuridica pesj = item.value						
						pesj.delete(flush:true);
						item.detach()
					}
				}
			}
		)
	}
	
	@Listen("onChanging = #bdboxBusca")
	void buscaUsuarios(InputEvent e){
			bdboxBusca.text = e.value
			listarPJ()
	}
	
	
	/*
	@Listen("onClick = #pj_btn_cancelar")
	void retornarOLD() {
		wCadastro.visible = false
		wLista.visible = true
		wCons.visible = false
		listarPJ()
	}
	*/
	@Listen("onClick = #btnNovo")
	void adicionar() {
		
		
		wCadastro.visible = true
		wBotoes.visible = true
		wLista.visible = false
		wCons.visible = false
		
		filial.focus()
		id.value=0
		filial.value=""
		representante.value=""
        atendente.value=""
		razaosocial.value=""
		cnpj.value=""
		fantasia.value=""
		ie.value=""
		im.value=""
		endereco.value=""
		numero.value=null
		complemento.value=""
		bairro.value=""
		cidade.value=""
		estado.value=""
		cep.value=""
		website.value=""
		telefone.value=""
		fax.value=null
		email.value=""
		contador.value=""
		crc.value=""
		telefonecontador.value=""
		atividadeprincipal.value=""
		porcentagemvendas.value=null
		faturamentoultimomes.value=""
		faturamentoultimos12meses.value=""
		nomecontato.value=""
		cargocontato.value=""
		sociedade.value=""
		dataconstituicao.value=null
		capital.value=""
		dataultimaalteracao.value=null
		nsocios.value=null
		cmbpossuifiliais.selectedIndex=0
					
		nfiliais.value=null
		nfuncionarios.value=null
		id_representante1.value=null
		id_representante2.value=null
		id_representante3.value=null
		assinatura.value=""
		cotista1.value=""
		documento1.value=null
		porcentagem1.value=null
		valor1.value=""
		
		cotista2.value=""
		documento2.value=""
		porcentagem2.value=null
		valor2.value=null
		
		cotista3.value=""
		documento3.value=""
		porcentagem3.value=null
		valor3.value=null
		
		nbanco.value=null
		nomebanco.value=""
		nagencia.value=null
		contacorrente.value=""
		dataabertura.value=null
		telefonebanco.value=""
	
		ref1.value=""
		docref1.value=""
		foneref1.value=""
		
		ref2.value=""
		docref2.value=""
		foneref2.value=""
		
		ref3.value=""
		docref3.value=""
		foneref3.value=""
		
		ref4.value=""
		docref4.value=""
		foneref4.value=""
			
		login.value=""
		senha.value=""
		senharestrita.value=""
		
		
		
		
		
		
	}
	
	
	@Listen("onClick = #pj_btn_salvar")
	void salvar() {
		PessoaJuridica pj = PessoaJuridica.get(id.value)
		
		if (pj == null) pj=new PessoaJuridica()
		pj.id = id.value			
		pj.filial = filial.value
		pj.representante = representante.value
        pj.atendente = atendente.value
		pj.razaosocial = razaosocial.value
		pj.cnpj = cnpj.value
		pj.fantasia = fantasia.value
		pj.ie = ie.value
		pj.im = im.value
		pj.endereco = endereco.value
		pj.numero = numero.value
		pj.complemento = complemento.value
		pj.bairro = bairro.value
		pj.cidade = cidade.value
		pj.estado = estado.value
		pj.cep = cep.value
		pj.website = website.value
		pj.telefone = telefone.value
		pj.fax = fax.value
		pj.email = email.value
		pj.contador = contador.value
		pj.crc = crc.value
		pj.telefonecontador = telefonecontador.value
		pj.atividadeprincipal = atividadeprincipal.value
		pj.porcentagemvendas = porcentagemvendas.value
		pj.faturamentoultimomes = faturamentoultimomes.value
		pj.faturamentoultimos12meses = faturamentoultimos12meses.value
		pj.nomecontato = nomecontato.value
		pj.cargocontato = cargocontato.value
		pj.sociedade = sociedade.value
		pj.dataconstituicao = dataconstituicao.value
		pj.capital = capital.value
		pj.dataultimaalteracao = dataultimaalteracao.value
		pj.nsocios = nsocios.value
		pj.possuifiliais= cmbpossuifiliais.selectedItem.value
				
			
		pj.nfiliais = nfiliais.value
		pj.nfuncionarios = nfuncionarios.value
		pj.id_representante1 = id_representante1.value
		pj.id_representante2 = id_representante2.value
		pj.id_representante3 = id_representante3.value
		pj.assinatura = assinatura.value
		pj.cotista1 = cotista1.value
		pj.documento1 = documento1.value
		pj.porcentagem1 = porcentagem1.value
		pj.valor1 = valor1.value
		
		pj.cotista2 = cotista2.value
		pj.documento2 = documento2.value
		pj.porcentagem2 = porcentagem2.value
		pj.valor2 = valor2.value
		
		pj.cotista3 = cotista3.value
		pj.documento3 = documento3.value
		pj.porcentagem3 = porcentagem3.value
		pj.valor3 = valor3.value
		
		pj.nbanco = nbanco.value
		pj.nomebanco = nomebanco.value
		pj.nagencia = nagencia.value
		pj.contacorrente = contacorrente.value
		pj.dataabertura = dataabertura.value
		pj.telefonebanco = telefonebanco.value
	
		pj.ref1 = ref1.value
		pj.docref1 = docref1.value
		pj.foneref1 = foneref1.value
		
		pj.ref2 = ref2.value
		pj.docref2 = docref2.value
		pj.foneref2 = foneref2.value
		
		pj.ref3 = ref3.value
		pj.docref3 = docref3.value
		pj.foneref3 = foneref3.value
		
		pj.ref4 = ref4.value
		pj.docref4 = docref4.value
		pj.foneref4 = foneref4.value
			
		pj.login = login.value
		pj.senha = senha.value
		pj.senharestrita = senharestrita.value
		
		
				
						
			if (verificarSeCamposEstaoVazios() && !pj.hasErrors() && pj.save(flush:true)) {
									
				Clients.showNotification("Pessoa Jurídica Cadastrada", "info", null, null, 2000);
				adicionar()
			}else {
	           Clients.showNotification("Problemas no cadastro. Verifique os campos obrigatórios e tente novamente!", "error", null, null, 2000);
			}
			
					
	}
	
	
	
	
	boolean verificarSeCamposEstaoVazios(){
		    if(filial.value.isEmpty() || representante.value.isEmpty() 
				|| atendente.value.isEmpty() || razaosocial.value.isEmpty() 
				|| cnpj.value.isEmpty() || cnpj.value.trim().replace(".","").replace("-","").replace("/","").replace("_","").length() < 14 || fantasia.value.isEmpty() || ie.value.isEmpty() || ie.value.trim().replace(".","").replace("_","").length() < 12
				|| im.value.isEmpty() || im.value.trim().replace(".","").replace("-","").replace("_","").length() < 8 || endereco.value.isEmpty() || bairro.value.isEmpty() 
				|| cep.value.isEmpty() || estado.value.isEmpty() || telefone.value.isEmpty() || telefone.value.trim().replace("(","").replace(")","").replace("_","").replace("-","").length() < 10
				|| cidade.value.isEmpty() || login.value.isEmpty() || senha.value.isEmpty() || cep.value.trim().replace("-","").replace("_","").length() < 8
				|| senharestrita.value.isEmpty() || numero.value == null)
			 {  
			
		      return false
		    }
		      return true
		   }
	
	
	
		 
	
	@Listen("onClick = #btnCancelar, #btnVoltar")
	    void retornar() {
	           Executions.sendRedirect("pessoaJuridica.zul")
	     }
	
	
	
}






/*
 -- 
Depois que você tem o encontro verdadeiro com Cristo, sua vida não mais será a mesma... 
Sim, nós temos a Alegria da Salvação! Em todos os momentos o Senhor é digno de toda adoração!!

Porque Deus amou ao mundo de tal maneira que deu o seu Filho unigênito, para que todo o que nele crê não pereça, 
mas tenha a vida eterna. João 3.16
Mas Deus, sendo rico em Misericórdia, por causa do grande Amor com que nos amou, e estando nós mortos em nossos delitos, 
nos deu vida juntamente com Cristo, pela Graça sois salvos. Efésios 2.4-5
Porque isto é o meu Sangue, que é derramado em favor de muitos para o Perdão dos pecados. Mateus 26.28a

O Evangelho confronta todas as pessoas como pecadores e oferece PERDÃO de pecados e VIDA ETERNA a todos aqueles 
que dão as costas para o seu pecado e creem em CRISTO. Não importa quão bom ou mau você tenha sido. 
Não importa de onde você é ou de qual contexto religioso você vem. Se você se ARREPENDER de seu pecado e CRER em CRISTO, 
você será SALVO.
 */
	
		
		
