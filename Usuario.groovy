package bamesys
class Usuario {
	int id
	String nome
	String grupo
	String login
	String senha
	
	static constraints = {
		id nullable:false, unique:true
		nome nullable:false
		grupo nullable:false
		login nullable:false
		senha nullable:false
    }
	
	static mapping= {
		id generator:"native"
	}
}
