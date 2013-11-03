package bamesys3

import javax.security.auth.login.LoginContext;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.*
import org.zkoss.zul.*

class HomeComposer extends zk.grails.Composer {
	
	Div barraInfo
	
    def afterCompose = { window ->
		if (session.getAttribute("username")==null)
		{
				Executions.sendRedirect("index.zul")
			}else{
			barraInfo.append{
				label(style="color:#666666; font-style:italic;", value:"Logado como: "+session.getAttribute("username"))
			}
		}
	}
}