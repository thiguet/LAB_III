# Parte I

Utilize as informações a seguir para criar um controle automatizado de uma clínica média. Sabese que essa clínica deseja ter um controle semanal (2a a 6a feira) das consultas realizadas. 
A cada dia podem ser realizadas no máximo três consultas para cada médico. 

Considere que são cadastrados apenas 3 médicos e 15 pacientes.

PACIENTE (COD_PACIENTE, NOME, ENDERECO, TELEFONE)

MEDICO (COD_MEDICO, NOME, TELEFONE)

CONSULTA (NUM_CONSULTA, DIA_SEMANA, MÊS, ANO, HORA, COD_MEDICO, COD_PACIENTE)


-	a. Construa, usando classes, os tipos necessários para armazenar os PACIENTES,
		MEDICOS e CONSULTAS.
-	b. Usando os tipos do item anterior, implemente um procedimento para cadastrar
	um paciente. Este procedimento deve garantir que não haverá mais de um
	paciente com o mesmo código. O nome de cada paciente deve ser lido da tela
	(entrada padrão). As informações dos pacientes devem ser salvas em um arquivo
	binário em disco chamado “pacientes.dat” (ver código no final do enunciado)
-	c. Faça um procedimento para cadastro dos médicos. Siga as mesmas instruções do
	item b (o arquivo binário deve se chamar “medicos.dat”).
-	d. Implemente um procedimento que cadastre uma consulta como ela é descrita no
	enunciado do problema. Lembre-se que as consultas só podem ser marcadas de
	2a a 6a feira. Lembre-se também que cada médico só pode atender dois pacientes
	por dia. Ao final de um dia, o sistema deve permitir salvar o histórico de todas as
	consultas em um arquivo binário (“consultas.dat”). Este arquivo deve manter um
	histórico de todas as consultas do sistema.
-	e. Implemente uma função que receba o nome de um médico e um determinado dia
	da semana; percorra os dados cadastrados e imprima na tela quantas consultas
	estão agendadas para este médico neste determinado dia.
-	f. Seu programa deve permitir a geração de relatório de consultas. Deve haver a
	opção para gerar, na tela e em arquivo texto (“relatório.txt”) de todos as consultas
	do sistema. Deve haver também a opção para saber as consultas de um
	determinado dia somente. As informações do relatório devem conter todas as
	informações de uma consulta. 