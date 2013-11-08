package bamesys

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.*
import org.zkoss.zul.*
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

class UsuariosComposer extends zk.grails.Composer {
	private Intbox id
	private Textbox nome, login, senha, confirmarSenha, bdboxBuscaUsuario
	private Combobox cmbGrupo
	private Div winCadastro, winLista, barraInfo
	private Listbox lstUsuarios
	private Label lblLogin

	private String loginaux
	Window win = new Window()

	def afterCompose = { window ->
		if ( (session.getAttribute("username")==null) || (session.getAttribute("grupo")!="administrador") ) {
			Executions.sendRedirect("index.zul")
		}else{
			win = (Window)Executions.createComponents("avisoLancamentos.zul", null, null);
			listarLancamentos()
			win.doModal();
			
			barraInfo.append{
				label(style="color:#666666; font-style:italic;", value:"Logado como: "+session.getAttribute("username"))
			}

			winCadastro.visible = false
			winLista.visible = true
			listarUsuarios()
		}
	}

	void listarLancamentos(){
		Listbox lstLancamentos = new Listbox()

		win.appendChild(lstLancamentos)

		def dataHoje = new Date()
		def dataLancamentosFuturos = new Date() + 8

		def lista = LancamentosFuturos.findAll("from LancamentosFuturos l where l.data >= '"+dataHoje.format("yyyy-MM-dd")+"' and l.data <= '"+dataLancamentosFuturos.format("yyyy-MM-dd")+"'")

		lstLancamentos?.items?.clear()

		lstLancamentos?.append {
			if (lstLancamentos?.heads?.size() == 0) {
				listhead(sizable:true){
					listheader(label: "Data", sort: "auto")
					listheader(label: "Valor", sort: "auto")
					listheader(label: "Tipo", sort: "auto")
				}
			}

			lista.each{ LancamentosFuturos lst  ->
				listitem(value: lst) { item ->
					listcell(label: lst.data.format("dd/MM/yyyy"))
					listcell(label: lst.valor)
					listcell(label: lst.tipo)
				}
			}
		}
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
							onClick: { e->
								this.editarUsuario(item);
							} )
							toolbarbutton(label: '      ', image: "/images/excluir.png",
							onClick: { e->
								this.excluirUsuario(item);
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
		cmbGrupo.selectedItem = cmbGrupo.items.find({
			it.value == user.grupo
		})
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


	@Listen("onClick = #btnCancelar")
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

		if(!verificarLoginExistente() && !verificarSeCamposEstaoVazios() && !verificarSenhaComConfirmacaoSenha()){
			usuario.id=id.value
			usuario.login=login.value
			usuario.nome=nome.value
			usuario.grupo=cmbGrupo?.selectedItem?.value

			if(usuario.senha != senha.value) usuario.senha=senha.value.encodeAsSHA256()

			if (!usuario.hasErrors() && usuario.save(flush:true)) {
				Executions.sendRedirect("usuarios.zul")
				Clients.showNotification("Usuário Cadastrado", "info", null, null, 4000);
			}
			else {
				Clients.showNotification("Problemas no cadastro. Verifique os campos e tente novamente", "error", null, null, 4000);
			}
		}
		else {
			Clients.showNotification("Problemas no cadastro. Verifique os campos e tente novamente", "error", null, null, 5000);
		}
	}

	@Listen("onChange = #login")
	boolean verificarLoginExistente(){
		def logins = Usuario.findAllByLogin(login.value)

		if((logins.size() > 0) && (loginaux != login.value)){
			lblLogin.value = "Login já utilizado, tente outro"
			return true
		}else{
			lblLogin.value = ""
			return false
		}
	}

	boolean verificarSenhaComConfirmacaoSenha(){
		if(senha.value!=confirmarSenha.value) {
			return true
		}
		return false
	}

	boolean verificarSeCamposEstaoVazios(){
		if(nome.value.isEmpty() || login.value.isEmpty() || senha.value.isEmpty() || confirmarSenha.value.isEmpty()) {
			return true
		}
		return false
	}
}
