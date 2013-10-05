package bamesys

class Pessoas {

    int id
	String nome
	
	static hasMany = [lancamentosFuturos:LancamentosFuturos]
	
	static constraints = {
		id unique:true
		nome nullable:false		
    }
	
	static mapping = {
		id generator: "increment"
	}
	
	String toString() {
		nome
	}
}
