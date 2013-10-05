package bamesys

import org.zkoss.zul.*
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.*

class PessoasFisicasComposer extends zk.grails.Composer {

	@Wire
	Button btnAdicionar, btnCancelar, btnSalvar

	@Wire
	Listbox lstDados
	
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

	def afterCompose = { window ->
		carregarGrid()
	}

	@Listen ("onClick = #btnAdicionar")
	void mostrarCadastro() {
		lstDados.setVisible(false)
		pCadastroPf.setVisible(true)
		txtRepresentante.setFocus(true)
		btnAdicionar.setVisible(false)
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
		pCadastroPf.setVisible(false)
		lstDados.setVisible(true)
		btnAdicionar.setVisible(true)
		
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
				
		pf.save()
		mostrarGrid()
		carregarGrid()
		
	}
	
	@Listen("onDoubleClick = #lstDados")
	void editarPf(Event e) {
		lstDados.setVisible(false)
		pCadastroPf.setVisible(true)
		txtRepresentante.setFocus(true)
		btnAdicionar.setVisible(false)
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
		lstDados.items.clear()

		lstDados.append {

			if (lstDados.heads.size() == 0) {
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
