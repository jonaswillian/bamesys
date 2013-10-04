package bamesys_lucas


class PessoaJuridica {

	int id
	String filial
	String representante
	String atendente
	String razaosocial
	String cnpj
	String fantasia
	String ie
	String im
	String endereco
	int numero
	String complemento
	String bairro
	String cidade
	String estado
	String cep
	String website
	String telefone
	String fax
	String email
	String contador
	String crc
	String telefonecontador
	String atividadeprincipal
	int porcentagemvendas
	String faturamentoultimomes
	String faturamentoultimos12meses
	String nomecontato
	String cargocontato
	String sociedade
	Date dataconstituicao
	String capital
	Date dataultimaalteracao
    int nsocios
	String possuifiliais
	int nfiliais
	int nfuncionarios
	int id_representante1
	int id_representante2
	int id_representante3
	String assinatura
	String cotista1
	String documento1
	int porcentagem1
	String valor1
	String cotista2
	String documento2
	int porcentagem2
	String valor2
	String cotista3
	String documento3
	int porcentagem3
	String valor3
	int nbanco
	String nomebanco
	String nagencia
	String contacorrente
	Date dataabertura
	String telefonebanco
	String ref1
	String docref1
	String foneref1
	String ref2
	String docref2
	String foneref2
	String ref3
	String docref3
	String foneref3
	String ref4
	String docref4
	String foneref4
	String login
	String senha
	String senharestrita
	
		
	
	//static hasOne=[pessoafisica:PessoaFisica]
	
	
	static mapping={
		table 'tb_pessoajuridica'
		version false
		id generator: 'increment', sqlType: 'tinyint'
			
		
	}
	
    static constraints = {
		id (unique:true, nullable:false)
		filial (nullable:false, blank: false)
		representante (nullable:false, blank: false)
		atendente (nullable:false, blank: false)
		razaosocial (nullable:false, blank: false)
		cnpj (nullable:false , blank: false)
		fantasia (nullable:false , blank: false)
		ie (nullable:false , blank: false)
		im (nullable:false , blank: false)
		endereco (nullable:false ,blank: false)
		numero (nullable:false)
		complemento (nullable:true)
		website (nullable:true)
		fax (nullable:true)
		email (nullable:false) 
		atividadeprincipal (nullable:true)  
		telefone  (nullable:false)
		porcentagemvendas (nullable:true) 
		faturamentoultimomes (nullable:true)
		faturamentoultimos12meses (nullable:true)
		nomecontato (nullable:true)
		cargocontato (nullable:true)
		contador (nullable:true)
		sociedade (nullable:true)
		dataconstituicao (nullable:true)
		dataultimaalteracao (nullable:true)
		crc (nullable:true)
		telefonecontador (nullable:true)
		nsocios (nullable:true ,blank: true)
		capital (nullable:true)
		possuifiliais (nullable:true,inlist:["Sim", "Não"])
		nfiliais (nullable:true)
		nfuncionarios (nullable:true)
		id_representante1 (nullable:true)
		id_representante2 (nullable:true)
		id_representante3 (nullable:true)
		assinatura (nullable:true)
		cotista1 (nullable:true)
		documento1 (nullable:true)
		porcentagem1 (nullable:true)
		valor1 (nullable:true)
		cotista2 (nullable:true)
		documento2 (nullable:true)
		porcentagem2 (nullable:true)
		valor2 (nullable:true)
		cotista3 (nullable:true)
		documento3 (nullable:true)
		porcentagem3 (nullable:true)
		valor3 (nullable:true)
		nbanco (nullable:true)
		nomebanco (nullable:true)
		nagencia (nullable:true)
		contacorrente (nullable:true)
		dataabertura (nullable:true)
		telefonebanco (nullable:true)
		ref1 (nullable:true)
		docref1 (nullable:true)
		foneref1 (nullable:true)
		ref2 (nullable:true)
		docref2 (nullable:true)
		foneref2 (nullable:true)
		ref3 (nullable:true)
		docref3 (nullable:true)
		foneref3 (nullable:true)
		ref4 (nullable:true)
		docref4 (nullable:true)
		foneref4 (nullable:true)
		
		
		
		
		
    }
}
