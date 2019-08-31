# Parte I

Utilize as informa��es a seguir para criar um controle automatizado de uma cl�nica m�dia. Sabese que essa cl�nica deseja ter um controle semanal (2a a 6a feira) das consultas realizadas. 
A cada dia podem ser realizadas no m�ximo tr�s consultas para cada m�dico. 

Considere que s�o cadastrados apenas 3 m�dicos e 15 pacientes.

PACIENTE (COD_PACIENTE, NOME, ENDERECO, TELEFONE)

MEDICO (COD_MEDICO, NOME, TELEFONE)

CONSULTA (NUM_CONSULTA, DIA_SEMANA, M�S, ANO, HORA, COD_MEDICO, COD_PACIENTE)


-	a. Construa, usando classes, os tipos necess�rios para armazenar os PACIENTES,
		MEDICOS e CONSULTAS.
-	b. Usando os tipos do item anterior, implemente um procedimento para cadastrar
	um paciente. Este procedimento deve garantir que n�o haver� mais de um
	paciente com o mesmo c�digo. O nome de cada paciente deve ser lido da tela
	(entrada padr�o). As informa��es dos pacientes devem ser salvas em um arquivo
	bin�rio em disco chamado �pacientes.dat� (ver c�digo no final do enunciado)
-	c. Fa�a um procedimento para cadastro dos m�dicos. Siga as mesmas instru��es do
	item b (o arquivo bin�rio deve se chamar �medicos.dat�).
-	d. Implemente um procedimento que cadastre uma consulta como ela � descrita no
	enunciado do problema. Lembre-se que as consultas s� podem ser marcadas de
	2a a 6a feira. Lembre-se tamb�m que cada m�dico s� pode atender dois pacientes
	por dia. Ao final de um dia, o sistema deve permitir salvar o hist�rico de todas as
	consultas em um arquivo bin�rio (�consultas.dat�). Este arquivo deve manter um
	hist�rico de todas as consultas do sistema.
-	e. Implemente uma fun��o que receba o nome de um m�dico e um determinado dia
	da semana; percorra os dados cadastrados e imprima na tela quantas consultas
	est�o agendadas para este m�dico neste determinado dia.
-	f. Seu programa deve permitir a gera��o de relat�rio de consultas. Deve haver a
	op��o para gerar, na tela e em arquivo texto (�relat�rio.txt�) de todos as consultas
	do sistema. Deve haver tamb�m a op��o para saber as consultas de um
	determinado dia somente. As informa��es do relat�rio devem conter todas as
	informa��es de uma consulta. 