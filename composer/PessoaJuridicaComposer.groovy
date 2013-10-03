package bamesys_lucas

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.Event
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
							toolbarbutton(label: '      ', image: "/images/skin/database_edit.png",
								onClick: { e-> this.editarPessoa(item);
								} )
							toolbarbutton(label: '        ', image: "/images/skin/database_delete.png",
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
		//id.value=user.id
		//nome.value=user.nome
		//login.value=user.login
		//senha.value=user.senha
		//confirmarSenha.value=user.senha
		//cmbGrupo.selectedItem = cmbGrupo.items.find({ it.value == user.grupo})
		
		winCadastro.visible = true
		winLista.visible = false
	}
	
	
	void excluirPJ(Listitem item) {
		Messagebox.show("Deseja realmente excluir?", "Confirmação", Messagebox.OK | Messagebox.IGNORE  | Messagebox.CANCEL, Messagebox.QUESTION,
			new EventListener() {
				void onEvent(Event evt) throws InterruptedException {
					if (evt.getName().equals("onOK")) {
						PessoaJuridica pessoaj = item.value
						
						pessoaj.delete(flush:true)
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
	/*	website.value=""
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
		dataconstituicao.value=""
		capital.value=""
		dataultimaalteracao.value=""
		nsocios.value=null
		possuifiliais.selectedIndex=0
		*/	
			/*
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
		
		
		
		
		*/
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
			//adicionar()
			
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
