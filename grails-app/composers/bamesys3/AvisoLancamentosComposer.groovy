package bamesys

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.*
import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.*;
import org.zkoss.zul.*
class AvisoLancamentosComposer extends zk.grails.Composer {
	@Wire
	Window winAviso;

	@Listen("onClick = #btnFechar")
	public void closeModal(Event e) {
		//println winAviso.properties
		winAviso.detach();
	}
}
