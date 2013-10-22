package bamesys

class Usuario {	
	int id
	String nome
	String grupo
	String login
	String senha
	
	static constraints = {
		nome nullable:false
		grupo nullable:false, inlist:["Administrador", "Corretor","Funcionário","Gerente","Operador"]
		login nullable:false, unique: true
		senha nullable:false
    }
	
	static mapping= {
		table 'tb_usuarios'
		version false
		id generator:"native"		
	}
}