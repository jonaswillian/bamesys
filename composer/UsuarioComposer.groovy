package bamesys

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.*
import org.zkoss.zul.*

class UsuarioComposer extends zk.grails.Composer {		
	@Wire
	Intbox id
	
	@Wire
	Textbox nome
	
	@Wire
	Combobox cmbGrupo
	
	@Wire
	Textbox login
	
	@Wire
	Textbox senha
	
	@Wire
	Textbox confirmarSenha
	
	@Wire
	Div winCadastro
	
	@Wire
	Div winLista
	
	@Wire
	Listbox lstUsuarios
	
	def afterCompose = { window ->
		winCadastro.visible = false
		winLista.visible = true
		listarUsuarios()
	}
	
	void listarUsuarios(){
		lstUsuarios.items.clear()
		
		lstUsuarios.append {			
			Usuario.list().each{ user ->
				listitem(value: user) { item ->
					listcell(label: user.id)
					listcell(label: user.nome)
					listcell(label: user.grupo)
					listcell(label: user.login)
					listcell(label: ""){
						hlayout{
							toolbarbutton(label: '      ', image: "/images/skin/editar.png",
								onClick: { e-> this.editarUsuario(item);
								} )
							toolbarbutton(label: '      ', image: "/images/skin/excluir.png",
								onClick: { e-> this.excluirUsuario(item);
								} )
							toolbarbutton(label: '      ', image: "/images/skin/movimentacoes.png",
								onClick: { e-> this.movimentacoesUsuario(item);
								} )
						}
					}
				}
			}
		}
	}
	
	void editarUsuario(Listitem item) {
		Usuario user=item.value
		id.value=user.id
		nome.value=user.nome
		login.value=user.login
		senha.value=user.senha
		confirmarSenha.value=user.senha
		cmbGrupo.selectedItem = cmbGrupo.items.find({ it.value == user.grupo})
		nome.focus()
		winCadastro.visible = true
		winLista.visible = false
	}
	
	void excluirUsuario(Listitem item) {
		Messagebox.show("Deseja realmente excluir este usuário?", "Confirmação", Messagebox.OK | Messagebox.IGNORE  | Messagebox.CANCEL, Messagebox.QUESTION,
			new EventListener() {
				void onEvent(Event evt) throws InterruptedException {
					if (evt.getName().equals("onOK")) {
						Usuario user=item.value
						user.delete(flush:true)
						item.detach()
					}
				}
			}
		)
	}
	
	void movimentacoesUsuario(Listitem item) {
	
	}
	
	@Listen("onClick = #btnCancelar")
	void retornar() {		
		winCadastro.visible = false
		winLista.visible = true
		listarUsuarios()
	}
	
	@Listen("onClick = #btnNovo")
	void adicionar() {
		winLista.visible = false
		id.value=0
		nome.value=""
		login.value=""
		senha.value=""
		confirmarSenha.value=""
		cmbGrupo.selectedIndex=0		
		nome.focus()		
		winCadastro.visible = true
	}	
		
	@Listen("onClick = #btnSalvar")
	void salvar() {
		Usuario usuario=Usuario.get(id.value)
		if (usuario == null) usuario=new Usuario()
		usuario.id=id.value
		usuario.login=login.value
		usuario.nome=nome.value
		
		//SHA1
		//if(usuario.senha != senha.value) usuario.senha=senha.value.encodeAsSHA1()
		
		//SHA256
		if(usuario.senha != senha.value) usuario.senha=senha.value.encodeAsSHA256()
		
		usuario.grupo=cmbGrupo.selectedItem.value								
			
		if (senhaValida() && !usuario.hasErrors() && usuario.save(flush:true)) {
			Messagebox.show("Usuário cadastrado com sucesso")
			retornar()							
		}else {
			Messagebox.show("Problemas no cadastro de usuário. \nVerifique os campos e tente novamente")
		}
	}
	
	boolean senhaValida(){
		if(senha.value!=confirmarSenha.value) {
			return false
		}
		return true
	}
}
