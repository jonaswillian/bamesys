package bamesys

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.*
import org.zkoss.zul.*
import org.zkoss.zk.ui.util.Clients;

class UsuariosComposer extends zk.grails.Composer {		
	Intbox id
	Textbox nome, login, senha, confirmarSenha, bdboxBuscaUsuario
	Combobox cmbGrupo
	Div winCadastro, winLista
	Listbox lstUsuarios
	Label lblLogin
	
	String loginaux
	
	
	def afterCompose = { window ->
		winCadastro.visible = false
		winLista.visible = true
		listarUsuarios()
	}
	
	void listarUsuarios(){
		def lista = Usuario.createCriteria().list {
			like("nome", "%"+bdboxBuscaUsuario.text+"%")
		}
		
		
		lstUsuarios.items.clear()
		
		lstUsuarios.append {			
			lista.each{ user ->
				listitem(value: user) { item ->
					listcell(label: user.id)
					listcell(label: user.nome)
					listcell(label: user.grupo)
					listcell(label: user.login)
					listcell(label: ""){
						hlayout{
							toolbarbutton(label: '      ', image: "/images/editar.png",
								onClick: { e-> this.editarUsuario(item);
								} )
							toolbarbutton(label: '      ', image: "/images/excluir.png",
								onClick: { e-> this.excluirUsuario(item);
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
		login.text=user.login
		senha.value=user.senha
		confirmarSenha.value=user.senha
		cmbGrupo.selectedItem = cmbGrupo.items.find({ it.value == user.grupo})
		nome.focus()
		loginaux = login.text
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
	
	@Listen("onChanging = #bdboxBuscaUsuario")
	void buscaUsuarios(InputEvent e){
		bdboxBuscaUsuario.text = e.value
		listarUsuarios()
	}
	
	
	@Listen("onClick = #btnCancelar, #btnVoltar")
	void retornar() {		
		Executions.sendRedirect("usuarios.zul")
	}
	
	
	@Listen("onClick = #btnNovo")
	void adicionar() {		
		winLista.visible = false
		winCadastro.visible = true
		nome.focus()
		id.value=0
		nome.value=""
		login.value=""
		senha.value=""
		confirmarSenha.value=""
		cmbGrupo.selectedIndex=0
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
			
		if (verificarSeCamposEstaoVazios() && verificarSenhaComConfirmacaoSenha() && !usuario.hasErrors() && usuario.save(flush:true)) {
			Clients.showNotification("Usuário Cadastrado", "info", null, null, 2000);
		}else {
			Clients.showNotification("Problemas no cadastro. Verifique os campos e tente novamente", "error", null, null, 2000);
		}
	}
	
	@Listen("onChange = #login")
	void verificarLoginExistente(){		
		def logins = Usuario.findAllByLogin(login.value)	
		
		if((logins.size() > 0) && (loginaux != login.value)){
			lblLogin.value = "Login já utilizado, tente outro"
		}else{
			lblLogin.value = ""
		}		
	}
	
	boolean verificarSenhaComConfirmacaoSenha(){
		if(senha.value!=confirmarSenha.value) {
			return false
		}
		return true
	}
	
	boolean verificarSeCamposEstaoVazios(){
		if(nome.value.isEmpty() || login.value.isEmpty() || senha.value.isEmpty()) {
			return false
		}
		return true
	}
}
