package bamesys_lucas


class PessoaJuridica {

	int idpj
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
	Date datacadastro
	Date dataatualizacao
	
		
	
	static hasOne=[pessoafisica:PessoaFisica]
	
	
	static mapping={
		table 'tb_pessoajuridica'
		version false
		idpj column: 'id_pj',generator: 'increment', sqlType: 'tinyint'
			
		
	}
	
    static constraints = {
    }
}
