package bamesys3

import javax.security.auth.login.LoginContext;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.*
import org.zkoss.zul.*


class IndexComposer extends zk.grails.Composer {

	Div botLogin	
	Textbox txtLogin, txtSenha
	
    def afterCompose = { window ->
        // initialize components here
    }
	

	@Listen("onClick = #botLogin")
	void logar()
	{
		String login = txtLogin.value
		String senha = txtSenha.value.encodeAsSHA256()
		
		def lista = Usuario.createCriteria().list
		{
			eq("login",login)
			eq("senha",senha)
		}
		
		if (lista.size()>0)
		{
			session.setAttribute("username", login)
			Executions.sendRedirect("home.zul")
		}
		else
		{
			Messagebox.show("Dados de login incorretos","Erro",Messagebox.OK, Messagebox.ERROR)
		}
	}
}


