package bamesys3

import javax.security.auth.login.LoginContext;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.*
import org.zkoss.zul.*


class PessoasFisicasComposer extends zk.grails.Composer {

    @Wire
	Button btnNovo, btnCancelar, btnSalvar

	@Wire
	Listbox lstPf
	
	@Wire
	Combobox cmbFilial, cmbUfNaturalidade, cmbSexo, cmbUfEmissao, cmbEstadoCivil, cmbEstado, cmbTipoResidencia, cmbEstadoEmpresa 

	@Wire
	Panel pCadastroPf

	@Wire
	Textbox txtRepresentante, txtAtendente, txtNome, txtCpf, txtNaturalidade, txtNacionalidade, txtRg, txtEmissor, txtNomeConjuge, txtCpfConjuge,
	txtNomePai, txtNomeMae, txtEndereco, txtNumero, txtComplemento, txtBairro, txtCidade, txtCep, txtEscolaridade, txtTempoResidenciaAtual,
	txtTempoResidenciaAnterior, txtEmail, txtTelefone, txtCelular, txtEmpresa, txtCnpj, txtMatricula, txtCargo, txtSecao, txtEnderecoEmpresa,
	txtNumeroEmpresa, txtComplementoEmpresa, txtBairroEmpresa, txtCidadeEmpresa, txtCepEmpresa, txtTelefoneEmpresa, txtRamalEmpresa, txtNBanco, txtBanco, 
	txtAgencia, txtContaCorrente, txtTelefoneBanco, txtNomeRef1, txtFoneRef1, txtNomeRef2, txtFoneRef2, txtNomeRef3, txtFoneRef3
	
	@Wire
	Intbox intNDependentes, intDiaPagamento, intDiaVale, intDiaRenda, intCodigo
	
	@Wire
	Decimalbox dcValorResidencia, dcValorPagamento, dcValorVale, dcValorRenda, dcValorTotal
	
	@Wire
	Datebox dbDataNascimento, dbDataEmissao, dbDataNascConjuge, dbDataAdmissao, dbDataAbertura
	
	@Wire
	Div divCadastro, divLista, divNovo, barraInfo

	def afterCompose = { window ->
		if (session.getAttribute("username")==null)
		{
				Executions.sendRedirect("index.zul")
			}else{
			barraInfo.append{
				label(style="color:#666666; font-style:italic;", value:"Logado como: "+session.getAttribute("username"))
			}
		}
		divCadastro.visible = false
		divLista.visible = true
		carregarGrid()
	}

	@Listen ("onClick = #btnNovo")
	void mostrarCadastro() {
		divLista.visible = false
		divCadastro.visible = true
		txtRepresentante.setFocus(true)
		intCodigo.value=0
		cmbFilial.selectedIndex=0
		txtRepresentante.value=""
		txtAtendente.value=""
		txtNome.value=""
		txtCpf.value=""
		dbDataNascimento.value=""
		txtNaturalidade.value=""
		cmbUfNaturalidade.selectedIndex=0
		txtNacionalidade.value=""
		cmbSexo.selectedIndex=0
		txtRg.value=""
		dbDataEmissao.value=""
		txtEmissor.value=""
		cmbUfEmissao.selectedIndex=0
		cmbEstadoCivil.selectedIndex=0
		intNDependentes.value=0
		txtNomeConjuge.value=""
		dbDataNascConjuge.value=""
		txtCpfConjuge.value=""
		txtNomePai.value=""
		txtNomeMae.value=""
		txtEndereco.value=""
		txtNumero.value=""
		txtComplemento.value=""
		txtBairro.value=""
		txtCidade.value=""
		cmbEstado.selectedIndex=0
		txtCep.value=""
		txtEscolaridade.value=""
		cmbTipoResidencia.selectedIndex=0
		dcValorResidencia.value=0
		txtTempoResidenciaAnterior.value=""
		txtTempoResidenciaAtual.value=""
		txtEmail.value=""
		txtTelefone.value=""
		txtCelular.value=""
		txtEmpresa.value=""
		txtCnpj.value=""
		txtMatricula.value=""
		dbDataAdmissao.value=""
		txtCargo.value=""
		txtSecao.value=""
		txtEnderecoEmpresa.value=""
		txtNumeroEmpresa.value=""
		txtComplementoEmpresa.value=""
		txtBairroEmpresa.value=""
		txtCidadeEmpresa.value=""
		cmbEstadoEmpresa.selectedIndex=0
		txtCepEmpresa.value=""
		txtTelefoneEmpresa.value=""
		txtRamalEmpresa.value=""
		intDiaPagamento.value=0
		dcValorPagamento.value=0
		intDiaVale.value=0
		dcValorVale.value=0
		intDiaRenda.value=0
		dcValorRenda.value=0
		dcValorTotal.value=0
		txtNBanco.value=""
		txtBanco.value=""
		txtAgencia.value=""
		txtContaCorrente.value=""
		dbDataAbertura.value=""
		txtTelefoneBanco.value=""
		txtNomeRef1.value=""
		txtFoneRef1.value=""
		txtNomeRef2.value=""
		txtFoneRef2.value=""
		txtNomeRef3.value=""
		txtFoneRef3.value=""
		
	}

	@Listen ("onClick = #btnCancelar")
	void mostrarGrid() {
		lstPf.setVisible(true)
		btnNovo.setVisible(true)
		
	}
	
	@Listen ("onClick = #btnSalvar")
	void salvar()
	{
		PessoaFisica pf=PessoaFisica.get(intCodigo.value)
		if (pf == null) pf=new PessoaFisica()
		pf.id = intCodigo.value
		pf.filial = cmbFilial.selectedItem.value
		pf.representante = txtRepresentante.value
		pf.atendente = txtAtendente.value
		pf.nome = txtNome.value
		pf.cpf = txtCpf.value
		pf.datanasc=dbDataNascimento.value
		pf.naturalidade=txtNaturalidade.value
		pf.ufnaturalidade=cmbUfNaturalidade.selectedItem.value
		pf.nacionalidade=txtNacionalidade.value
		pf.sexo=cmbSexo.selectedItem.value
		pf.rg=txtRg.value
		pf.dataadmissao=dbDataEmissao.value
		pf.emissor=txtEmissor.value
		pf.ufemissao=cmbUfEmissao.selectedItem.value
		pf.estadocivil=cmbEstadoCivil.selectedItem.value
		pf.ndependentes=intNDependentes.value
		pf.nomeconjuge=txtNomeConjuge.value
		pf.datanascconjuge=dbDataNascConjuge.value
		pf.cpfconjuge=txtCpfConjuge.value
		pf.nomepai=txtNomePai.value
		pf.nomemae=txtNomeMae.value
		pf.endereco=txtEndereco.value
		pf.numero=txtNumero.value
		pf.complemento=txtComplemento.value
		pf.bairro=txtBairro.value
		pf.cidade=txtCidade.value
		pf.estado=cmbEstado.selectedItem.value
		pf.cep=txtCep.value
		pf.escolaridade=txtEscolaridade.value
		pf.tiporesidencia=cmbTipoResidencia.selectedItem.value
		pf.valorresidencia=dcValorResidencia.value
		pf.temporesidenciaanterior=txtTempoResidenciaAnterior.value
		pf.temporesidenciaatual=txtTempoResidenciaAtual.value
		pf.email=txtEmail.value
		pf.telefone=txtTelefone.value
		pf.celular=txtCelular.value
		pf.empresa=txtEmpresa.value
		pf.cnpj=txtCnpj.value
		pf.matricula=txtMatricula.value
		pf.dataadmissao=dbDataAdmissao.value
		pf.cargo=txtCargo.value
		pf.secao=txtSecao.value
		pf.enderecoempresa=txtEnderecoEmpresa.value
		pf.numeroempresa=txtNumeroEmpresa.value
		pf.complementoempresa=txtComplementoEmpresa.value
		pf.bairroempresa=txtBairroEmpresa.value
		pf.cidadeempresa=txtCidadeEmpresa.value
		pf.ufempresa=cmbEstadoEmpresa.selectedItem.value
		pf.cepempresa=txtCepEmpresa.value
		pf.telefonemepresa=txtTelefoneEmpresa.value
		pf.ramalempresa=txtRamalEmpresa.value
		pf.diapagamento=intDiaPagamento.value
		pf.valorpagamento=dcValorPagamento.value
		pf.diavale=intDiaVale.value
		pf.valorvale=dcValorVale.value
		pf.diarenda=intDiaRenda.value
		pf.outrarenda=dcValorRenda.value
		pf.valortotal=dcValorTotal.value
		pf.nbanco=txtNBanco.value
		pf.nomebanco=txtBanco.value
		pf.nagencia=txtAgencia.value
		pf.contacorrente=txtContaCorrente.value
		pf.dataabertura=dbDataAbertura.value
		pf.telefonebanco=txtTelefoneBanco.value
		pf.nomeref=txtNomeRef1.value
		pf.telefoneref=txtFoneRef1.value
		pf.nomeref2=txtNomeRef2.value
		pf.telefoneref2=txtFoneRef2.value
		pf.nomeref3=txtNomeRef3.value
		pf.telefoneref3=txtFoneRef3.value
				
		pf.save()
		mostrarGrid()
		carregarGrid()
		
	}
	
	@Listen("onDoubleClick = #lstPf")
	void editarPf(Event e) {
		lstPf.setVisible(false)
		txtRepresentante.setFocus(true)
		btnNovo.setVisible(false)
		PessoaFisica pf = e.target.selectedItem.value
		intCodigo.value=pf.id
		cmbFilial.selectedItem.value=pf.filial
		txtRepresentante.value = pf.representante
		txtAtendente.value = pf.atendente
		txtNome.value=pf.nome
		txtCpf.value = pf.cpf
		dbDataNascimento.value=pf.datanasc
		txtNaturalidade.value=pf.naturalidade
		cmbUfNaturalidade.selectedItem.value=pf.ufnaturalidade
		txtNacionalidade.value=pf.nacionalidade
		cmbSexo.selectedItem.value=pf.sexo
		txtRg.value=pf.rg
		dbDataEmissao.value=pf.dataadmissao
		txtEmissor.value=pf.emissor
		cmbUfEmissao.selectedItem.value=pf.ufemissao
		cmbEstadoCivil.selectedItem.value=pf.estadocivil
		intNDependentes.value=pf.ndependentes
		txtNomeConjuge.value=pf.nomeconjuge
		dbDataNascConjuge.value=pf.datanascconjuge
		txtCpfConjuge.value=pf.cpfconjuge
		txtNomePai.value=pf.nomepai
		txtNomeMae.value=pf.nomemae
		txtEndereco.value=pf.endereco
		txtNumero.value=pf.numero
		txtComplemento.value=pf.complemento
		txtBairro.value=pf.bairro
		txtCidade.value=pf.cidade
		cmbEstado.selectedItem.value=pf.estado
		txtCep.value=pf.cep
		txtEscolaridade.value=pf.escolaridade
		cmbTipoResidencia.selectedItem.value=pf.tiporesidencia
		dcValorResidencia.value=pf.valorresidencia
		txtTempoResidenciaAnterior.value=pf.temporesidenciaanterior
		txtTempoResidenciaAtual.value=pf.temporesidenciaatual
		txtEmail.value=pf.email
		txtTelefone.value=pf.telefone
		txtCelular.value=pf.celular
		txtEmpresa.value=pf.empresa
		txtCnpj.value=pf.cnpj
		txtMatricula.value=pf.matricula
		dbDataAdmissao.value=pf.dataadmissao
		txtCargo.value=pf.cargo
		txtSecao.value=pf.secao
		txtEnderecoEmpresa.value=pf.enderecoempresa
		txtNumeroEmpresa.value=pf.numeroempresa
		txtComplementoEmpresa.value=pf.complementoempresa
		txtBairroEmpresa.value=pf.bairroempresa
		txtCidadeEmpresa.value=pf.cidadeempresa
		cmbEstadoEmpresa.selectedItem.value=pf.ufempresa
		txtCepEmpresa.value=pf.cepempresa
		txtTelefoneEmpresa.value=pf.telefonemepresa
		txtRamalEmpresa.value=pf.ramalempresa
		intDiaPagamento.value=pf.diapagamento
		dcValorPagamento.value=pf.valorpagamento
		intDiaVale.value=pf.diavale
		dcValorVale.value=pf.valorvale
		intDiaRenda.value=pf.diarenda
		dcValorRenda.value=pf.outrarenda
		dcValorTotal.value=pf.valortotal
		txtNBanco.value=pf.nbanco
		txtBanco.value=pf.nomebanco
		txtAgencia.value=pf.nagencia
		txtContaCorrente.value=pf.contacorrente
		dbDataAbertura.value=pf.dataabertura
		txtTelefoneBanco.value=pf.telefonebanco
		txtNomeRef1.value=pf.nomeref
		txtFoneRef1.value=pf.telefoneref
		txtNomeRef2.value=pf.nomeref2
		txtFoneRef2.value=pf.telefoneref2
		txtNomeRef3.value=pf.nomeref3
		txtFoneRef3.value=pf.telefoneref3
	}

	void carregarGrid() {
		lstPf.items.clear()

		lstPf.append {

			if (lstPf.heads.size() == 0) {
				listhead(sizable:true){
					listheader(label: "ID")
					listheader(label: "Nome")
					listheader(label: "Telefone")
					listheader(label: "")
				}
			}

			PessoaFisica.list().each{ pf ->
				listitem(value: pf) { item ->
					listcell(label: pf.id)
					listcell(label: pf.nome)
					listcell(label: pf.telefone)
					listcell(label: ""){
							hlayout{
								toolbarbutton(label: ' ', image: "/images/skin/editar.png",
								onClick: { e-> this.editarPf(item);
								} )
								
							}
					}
				}
			}
		}
	}
}
