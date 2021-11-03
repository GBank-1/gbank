--Clientes
insert into ibm.cliente (nome, email, data_cadastro) VALUES ('Helis', 'email@email.com', CURRENT_TIMESTAMP());
insert into ibm.cliente (nome, email, data_cadastro) VALUES ('Sara Calbez', 'email@email.com', CURRENT_TIMESTAMP());

--Agencia
insert into ibm.agencia (codigo) VALUES ('0100');

--Conta
insert into ibm.conta (ativa, numero, saldo, cliente_id) VALUES (true, 7890001, 99900, 1);
insert into ibm.conta (ativa, numero, saldo, cliente_id) VALUES (true, 7890002, 100100, 2);

--Historico
insert into ibm.historico_transferencia (data, realizada, tipo, valor, origem_id, destino_id) VALUES (CURRENT_TIMESTAMP()
, true,'DEBITADA', 100, 1, 2);
insert into ibm.historico_transferencia (data, realizada, tipo, valor, origem_id, destino_id) VALUES (CURRENT_TIMESTAMP()
, true,'RECEBIDA', 100, 1, 2);
