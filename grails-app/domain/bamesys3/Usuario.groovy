package bamesys3

class Usuario {	
	int id
	String nome
	String grupo
	String login
	String senha
	
	static constraints = {
		nome nullable:false
		grupo nullable:false, inlist:["Administrador", "Corretor","Funcionário","Gerente","Operador"]
		login nullable:false
		senha nullable:false
    }
	
	static mapping= {
		id generator:"native"
	}
}