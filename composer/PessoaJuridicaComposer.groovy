package bamesys_lucas

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.*
import org.zkoss.zul.*

import bamesys_lucas.*;

class PessoaJuridicaComposer extends zk.grails.Composer {

	
	@Wire
	Div wLista
	
	@Wire
	Div wCadastro
	
	@Wire
	Listbox lstPJ
	
	@Wire
	Intbox id
	
	@Wire
	Textbox filial
	
	@Wire
	Textbox representante
	
	@Wire
	Textbox atendente
	
	@Wire
	Textbox razaosocial
	
	@Wire
	Textbox cnpj
	
	@Wire
	Textbox fantasia
	
	@Wire
	Textbox ie
	
	@Wire
	Textbox im
	
	@Wire
	Textbox endereco
	
	@Wire
	Intbox numero
	
	@Wire
	Textbox complemento
	
	@Wire
	Textbox bairro
	
	@Wire
	Textbox cep
	
	@Wire
	Textbox cidade
	
	@Wire
	Textbox estado
	
	@Wire
	Textbox telefone
	
	@Wire
	Textbox fax
	
	@Wire
	Textbox email
	
	@Wire
	Textbox website
	
	@Wire
	Textbox contador
	
	@Wire
	Textbox crc
	
	@Wire
	Textbox telefonecontador
	
	@Wire
	Textbox atividadeprincipal
	
	@Wire
	Intbox porcentagemvendas
	
	@Wire
	Textbox  faturamentoultimomes
	
	@Wire
	Textbox faturamentoultimos12meses
	
	@Wire
	Textbox nomecontato
	
	@Wire
	Textbox cargocontato
	
	@Wire
	Textbox sociedade
	
	@Wire
	Datebox dataconstituicao
	
	@Wire
	Textbox capital
	
	@Wire
	Datebox dataultimaalteracao
	
	@Wire
	Intbox nsocios
	
	@Wire
	Combobox possuifiliais
	
	@Wire
	Intbox nfiliais
	
	@Wire
	Intbox nfuncionarios
	
	@Wire
	Intbox id_representante1
	
	@Wire
	Intbox id_representante2
	
	@Wire
	Intbox id_representante3
	
	@Wire
	Textbox assinatura
	
	@Wire
	Textbox cotista1
	
	@Wire
	Textbox documento1
	
	@Wire
	Intbox porcentagem1
	
	@Wire
	Textbox valor1
	
    @Wire
	Textbox cotista2
	
	@Wire
	Textbox documento2
	
	@Wire
	Intbox porcentagem2
	
	@Wire
	Textbox valor2
	
	@Wire
	Textbox cotista3
	
	@Wire
	Textbox documento3
	
	@Wire
	Intbox porcentagem3
	
	@Wire
	Textbox valor3
	
	@Wire
	Intbox nbanco
	
	@Wire
	Textbox nomebanco
	
	@Wire
	Textbox nagencia
	
	@Wire
	Textbox contacorrente
	
	@Wire
	Datebox dataabertura
	
	@Wire
	Textbox telefonebanco
	
	@Wire
	Textbox ref1
	
	@Wire
	Textbox docref1
	
	@Wire
    Textbox foneref1
	
	@Wire
	Textbox ref2
	
	@Wire
	Textbox docref2
	
	@Wire
	Textbox foneref2
	
	@Wire
	Textbox ref3
	
	@Wire
	Textbox docref3
	
	@Wire
	Textbox foneref3
	
	@Wire
	Textbox ref4
	
	@Wire
	Textbox docref4
	
	@Wire
	Textbox foneref4
	
	
	@Wire
	Textbox login
	
	@Wire
	Textbox senha
	
	@Wire
	Textbox senharestrita
	
		
	
	
	
	
	
	
    def afterCompose = { window ->
		wCadastro.visible = false
		wLista.visible = true
		listarPJ()
    }

	void listarPJ(){
		lstPJ.items.clear()
		
		lstPJ.append {
			PessoaJuridica.list().each{ pessoaj ->
				listitem(value: pessoaj) { item ->
					listcell(label: pessoaj.id)
					listcell(label: pessoaj.filial)
					listcell(label: pessoaj.representante)
					listcell(label: pessoaj.atendente)
					listcell(label: ""){
						hlayout{
							toolbarbutton(label: 'Editar', image: "/images/skin/database_edit.png",
								onClick: { e-> this.editarPessoa(item);
								} )
							toolbarbutton(label: 'Excluir', image: "/images/skin/database_delete.png",
								onClick: { e-> this.excluirPJ(item);
								} )
							
						}
					}
				}
			}
		}
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
		possuifiliais.selectedItem=possuifiliais.items.find({it.value == upj.possuifiliais})
		
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
	
	
	
	
	
	@Listen("onClick = #pj_btn_cancelar")
	void retornar() {
		wCadastro.visible = false
		wLista.visible = true
		listarPJ()
	}
	
	@Listen("onClick = #btnNovo")
	void adicionar() {
		wLista.visible = false
		id.value=0
		filial.value=""
		representante.value=""
        atendente.value=""
		razaosocial.value=""
		cnpj.value=null
		fantasia.value=""
		ie.value=null
		im.value=null
		endereco.value=""
		numero.value=null
		complemento.value=""
		bairro.value=""
		cidade.value=""
		estado.value=""
		cep.value=""
		website.value=""
		telefone.value=null
		fax.value=null
		email.value=""
		contador.value=""
		crc.value=""
		telefonecontador.value=null
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
		possuifiliais.selectedIndex=0
					
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
		
		
		
		
		filial.focus()
		wCadastro.visible = true
		
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
		pj.possuifiliais= possuifiliais.value
				
			
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
		
		
				
		if (!pj.hasErrors() && pj.save(flush:true)) {
			Messagebox.show("Dados cadastrados!")
			//listarIngrediente()
			adicionar()
			
		}else {
			String x=""
			pj.errors.allErrors.each{
				x+=""+message(error:it)
				
			}
			print("X="+x)
			Messagebox.show("Dados não foram cadastrados!")
		}
	}
	
	
	
}
