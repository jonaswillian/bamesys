package bamesys_lucas

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.*
import org.zkoss.zul.*

import bamesys_lucas.PessoaJuridica;

class PessoaJuridicaComposer extends zk.grails.Composer {

	
	
	
	@Wire
	Div wLista
	
	@Wire
	Div wCadastro
	
	@Wire
	Listbox lstPJ
	
	
	
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
					println(pessoaj.representante)
					listcell(label: pessoaj.representante)
					listcell(label: pessoaj.atendente)
					listcell(label: ""){
						/*hlayout{
							toolbarbutton(label: '      ', image: "/images/skin/editar.png",
								onClick: { e-> this.editarUsuario(item);
								} )
							toolbarbutton(label: '      ', image: "/images/skin/excluir.png",
								onClick: { e-> this.excluirUsuario(item);
								} )
							toolbarbutton(label: '      ', image: "/images/skin/movimentacoes.png",
								onClick: { e-> this.movimentacoesUsuario(item);
								} )
						}*/
					}
				}
			}
		}
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
	/*	id.value=0
		nome.value=""
		login.value=""
		senha.value=""
		confirmarSenha.value=""
		cmbGrupo.selectedIndex=0
		nome.focus()*/
		wCadastro.visible = true
	}
	
	
	
}
