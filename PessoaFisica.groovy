package bamesys

class PessoaFisica {
	
	int id
	String filial
	String representante
	String atendente
	String nome
	String cpf
	Date datanasc
	String naturalidade
	String ufnaturalidade
	String nacionalidade
	String sexo
	String rg
	Date dataemissao
	String emissor
	String ufemissao
	String estadocivil
	int ndependentes
	String nomeconjuge
	Date datanascconjuge
	String cpfconjuge
	String nomepai
	String nomemae
	String endereco
	String numero
	String complemento
	String bairro
	String cidade
	String estado
	String cep
	String escolaridade
	String tiporesidencia
	double valorresidencia
	String temporesidenciaatual
	String temporesidenciaanterior
	String email
	String telefone
	String celular
	String empresa
	String cnpj
	String matricula
	Date dataadmissao
	String cargo
	String secao
	String enderecoempresa
	String numeroempresa
	String complementoempresa
	String bairroempresa
	String cidadeempresa
	String ufempresa
	String cepempresa
	String telefonemepresa
	String ramalempresa
	int diapagamento
	double valorpagamento
	int diavale
	double valorvale
	double outrarenda
	String descricaorenda
	double diarenda
	double valortotal
	String nbanco
	String nomebanco
	String nagencia
	String contacorrente
	Date dataabertura
	String telefonebanco
	String nomeref
	String telefoneref
	String nomeref2
	String telefoneref2
	String nomeref3
	String telefoneref3
	

    static constraints = {
		id (unique:true, nullable:false)
		filial (nullable:false)
		representante (nullable:false)
		atendente (nullable:false)
		nome (nullable:false)
		cpf (nullable:false)
		datanasc max:(new java.util.Date()),nullable:false
		naturalidade (nullable:false)
		ufnaturalidade (inList:["AC","AP","AL","AM","BA","CE","DF","ES","GO","MA","MG","MT","MS","PR","PB","PA","PE","PI","RJ","RN","RS","RO","RR","SC","SE","SP","TO"])
		nacionalidade (nullable:false)
		sexo (inList:["M","F"])
		rg (nullable:false)
		dataemissao max:(new java.util.Date()),nullable:false
		emissor (nullable:false)
		ufemissao (inList:["AC","AP","AL","AM","BA","CE","DF","ES","GO","MA","MG","MT","MS","PR","PB","PA","PE","PI","RJ","RN","RS","RO","RR","SC","SE","SP","TO"])
		estadocivil (nullable:false)
		ndependentes (display:true)
		nomeconjuge (display:true)
		datanascconjuge max:(new java.util.Date())
		cpfconjuge (display:true)
		nomepai (display:true)
		nomemae (display:true)
		endereco (display:true, nullable:false)
		numero (display:true, nullable:false)
		complemento (display:true, nullable:false)
		bairro (display:true, nullable:false)
		cidade (display:true, nullable:false)
		estado (inList:["AC","AP","AL","AM","BA","CE","DF","ES","GO","MA","MG","MT","MS","PR","PB","PA","PE","PI","RJ","RN","RS","RO","RR","SC","SE","SP","TO"])
		cep (display:true, nullable:false)
		escolaridade (display:true)
		tiporesidencia (inList:["Própria","Alugada","Financiada","Outro"])
		valorresidencia (display:true)
		temporesidenciaatual (display:true)
		temporesidenciaanterior (display:true)
		email (email:true)
		telefone (display:true)
		celular (display:true)
		empresa (display:true)
		cnpj (display:true)
		matricula (display:true)
		dataadmissao max:(new java.util.Date())
		cargo (display:true)
		secao (display:true)
		enderecoempresa (display:true)
		numeroempresa (display:true)
		complementoempresa (display:true)
		bairroempresa (display:true)
		cidadeempresa (display:true)
		ufempresa (inList:["AC","AP","AL","AM","BA","CE","DF","ES","GO","MA","MG","MT","MS","PR","PB","PA","PE","PI","RJ","RN","RS","RO","RR","SC","SE","SP","TO"])
		cepempresa (display:true)
		telefonemepresa (display:true)
		ramalempresa (display:true)
		diapagamento (display:true)
		valorpagamento (display:true)
		diavale (display:true)
		valorvale (display:true)
		outrarenda (display:true)
		descricaorenda (display:true)
		diarenda (display:true)
		valortotal (display:true)
		nbanco (display:true)
		nomebanco (display:true)
		nagencia (display:true)
		contacorrente (display:true)
		dataabertura max:(new java.util.Date())
		telefonebanco (display:true)
		nomeref (display:true)
		telefoneref (display:true)
		nomeref2 (display:true)
		telefoneref2 (display:true)
		nomeref3 (display:true)
		telefoneref3 (display:true)
    }
}
