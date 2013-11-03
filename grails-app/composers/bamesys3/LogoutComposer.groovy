package bamesys3

import javax.security.auth.login.LoginContext;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.*
import org.zkoss.zul.*

class LogoutComposer extends zk.grails.Composer {
	
    def afterCompose = { window ->
		session.getAttribute("username")
		session.removeAttribute("username")
		session.invalidate()
		Executions.sendRedirect("index.zul")
    }
}
