package bamesys

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.*
import org.zkoss.zul.*

import bamesys.LancamentosFuturos;

class LancamentosFuturosComposer extends zk.grails.Composer {

	@Wire
	Listbox lstDados
	@Wire
	Listbox lstPessoa
	@Wire
	Listbox lstTipo
	
	@Wire
	Intbox id
	@Wire
	Doublebox valor
	@Wire
	Datebox dataLancamento
	@Wire
	Label lblErro
	
	
	@Listen("onDoubleClick = #lstDados")
	void alterar(Event e) {
		LancamentosFuturos lancamento = e.target.selectedItem.value
		id.value=lancamento.id
		valor.value=lancamento.valor
		lstTipo.selectedItem=lstTipo.getItems().find({it.value == lancamento.tipo})
		dataLancamento.value=lancamento.data
		lstPessoa.selectedItem=lstPessoa.getItems().find({it.value.id == lancamento.pessoa.id})
	}
	
	def afterCompose = { window ->

		listarLancamentosFuturos()
		listarPessoa()

	}
	
	@Listen("onClick = #btnNovo,#btnCancelar")
	void adicionar() {
		id.value=0
		valor.value=0
		lstTipo.selectedIndex=0
		dataLancamento.value=new Date()
		lstPessoa.selectedIndex=0
		valor.focus()
		lblErro.value=""
	}

	
	void excluir(Listitem item) {
		Messagebox.show("Confirma?", "Confirma��o", Messagebox.OK | Messagebox.IGNORE  | Messagebox.CANCEL, Messagebox.QUESTION,
			new EventListener() {
				void onEvent(Event evt) throws InterruptedException {
					if (evt.getName().equals("onOK")) {
						LancamentosFuturos lancamento=item.value
						lancamento.delete(flush:true)
						item.detach()
					}
				}
			}
		)
	}
	
	void listarLancamentosFuturos() {
		lstDados.items.clear()
		
		lstDados.append {
			
			if (lstDados.heads.size() == 0) {
				listhead(sizable:true){
					listheader(label: "ID")
					listheader(label: "Valor")
					listheader(label: "Tipo")
					listheader(label: "Data do Lan�amento")
					listheader(label: "Afetado")
					listheader(label: "")
				}
			}
			
			LancamentosFuturos.list().each{ lancamento ->
				listitem(value: lancamento) { item ->
					listcell(label: lancamento.id)
					listcell(label: lancamento.valor)
					listcell(label: lancamento.tipo)
					listcell(label: lancamento.data)
					listcell(label: lancamento.pessoa)
					listcell(label: ""){
						hlayout{
							toolbarbutton(label: 'Excluir', image: "/images/skin/database_delete.png", onClick: {
								e-> this.excluir(item);
								} )
						}
					}
				}
			}
		}
		
	}
	
	void listarPessoa() {
		lstPessoa.append {
			listhead(sizable:true){
				listheader(label: "ID")
				listheader(label: "Nome")
			}
			Pessoas.list().each{ pessoa ->
				listitem(value: pessoa) {
					listcell(label: pessoa.nome)
				}
			}
		}
	}
	
	@Listen("onClick = #btnSalvar")
	void salvar() {
		LancamentosFuturos lancamento=LancamentosFuturos.get(id.value)
		if (lancamento == null) lancamento=new LancamentosFuturos()
		lancamento.id=id.value
		lancamento.valor=valor.value
		lancamento.tipo=lstTipo.selectedItem.value
		lancamento.pessoa=lstPessoa.selectedItem.value
		
		lancamento.data=dataLancamento.value
		if (!lancamento.hasErrors() && lancamento.save(flush:true)) {
			Messagebox.show("Lançamento cadastrado com sucesso")
			listarLancamentosFuturos()
			adicionar()
		}else {
			String x=""
			lancamento.errors.allErrors.each{
				x+=""+message(error:it)
			}
			lblErro.value=x
		}
	}

	
}
