package bamesys

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.*
import org.zkoss.zul.*

class MostrarLancamentosFuturosComposer extends zk.grails.Composer {

	@Wire
	Listbox lstDados
	
	//def lancamentosFuturos = LancamentosFuturos.createCriteria().list{
	//		order("data","desc")
	//}
	
    def afterCompose = { window ->
        listarLancamentosFuturos()
    }
	
	void listarLancamentosFuturos() {
		lstDados.items.clear()
		int cont = 0
		
		lstDados.append {
			
			if (lstDados.heads.size() == 0) {
				listhead(sizable:true){
					listheader(label: "Data do Lançamento")
					listheader(label: "Tipo")
					listheader(label: "Valor")					
				}
			}
			LancamentosFuturos.createCriteria().list {order("data","desc")}.each{ lancamento ->
				if (cont < 3){
					listitem(value: lancamento) { item ->
						listcell(label: lancamento.data)
						listcell(label: lancamento.tipo)
						listcell(label: lancamento.valor)
					}
					cont++
				}
			}
		}
		
	}
}
