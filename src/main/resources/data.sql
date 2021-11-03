--Clientes
insert into Cliente (nome, sobrenome, password) VALUES ('Helis', 'Freitas', 'hudusaduhasdhasuh');
insert into Cliente (nome, sobrenome, password) VALUES ('Sara', 'Calbez', 'hudusaduhasdhasuh');


--Agencia
insert into Agencia (codigo) VALUES ('0100');

--Conta
insert into Conta (ativa, numero, saldo, cliente_id) VALUES (true, 7890001, 99900, 1);
insert into Conta (ativa, numero, saldo, cliente_id) VALUES (true, 7890002, 100100, 2);

--Historico
insert into historico_transferencia (data, realizada, tipo, valor, origem_id, destino_id) VALUES (CURRENT_TIMESTAMP()
, true,'DEBITADA', 100, 1, 2);
insert into historico_transferencia (data, realizada, tipo, valor, origem_id, destino_id) VALUES (CURRENT_TIMESTAMP()
, true,'RECEBIDA', 100, 1, 2);
