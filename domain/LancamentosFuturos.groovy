package bamesys

class LancamentosFuturos {

    int id
	Date data
	Float valor
	String tipo
	Pessoas pessoa
	
	static belongsTo=[pessoa:Pessoas]
	static hasOne = [pessoa:Pessoas]
	
    static constraints = {
		id unique:true
		data min:(new java.util.Date()), nullable:false
		valor nullable:false
		tipo (inList:["Débito","Crédito"])
		pessoa nullable:false
    }
	
	static mapping={
		id generator: "increment"
	}
	
	String toString() {
		valor
	}
}
