insert into customer(email,name,password) values('deyse.joaquim@gmail.com','deyse joaquim','123');
insert into bid_master (id) select id from customer where email = 'deyse.joaquim@gmail.com';

insert into lottery(id,max_number,min_number,name) values(1,15,20,'Lotofacil') on conflict do nothing;
insert into lottery(id,max_number,min_number,name) values(2,6,15,'Megasena') on conflict do nothing;


insert into lottery_price(id,amount,value,lottery_id) values(1,15, 250,1) on conflict do nothing;
insert into lottery_price(id,amount,value,lottery_id) values(2,16, 4000,1) on conflict do nothing;
insert into lottery_price(id,amount,value,lottery_id) values(3,17, 34000,1) on conflict do nothing;
insert into lottery_price(id,amount,value,lottery_id) values(4,18, 204000,1) on conflict do nothing;
insert into lottery_price(id,amount,value,lottery_id) values(5,19, 969000,1) on conflict do nothing;
insert into lottery_price(id,amount,value,lottery_id) values(6,20, 3876000,1) on conflict do nothing;

insert into lottery_price(id,amount,value,lottery_id) values(7,6, 450,2) on conflict do nothing;
insert into lottery_price(id,amount,value,lottery_id) values(8,7, 3150,2) on conflict do nothing;
insert into lottery_price(id,amount,value,lottery_id) values(9,8, 12600,2) on conflict do nothing;
insert into lottery_price(id,amount,value,lottery_id) values(10,9, 37800,2) on conflict do nothing;
insert into lottery_price(id,amount,value,lottery_id) values(11,10, 94500,2) on conflict do nothing;
insert into lottery_price(id,amount,value,lottery_id) values(12,11, 207900,2) on conflict do nothing;
insert into lottery_price(id,amount,value,lottery_id) values(13,12, 415800,2) on conflict do nothing;
insert into lottery_price(id,amount,value,lottery_id) values(14,13, 772200,2) on conflict do nothing;
insert into lottery_price(id,amount,value,lottery_id) values(15,14, 1351350,2) on conflict do nothing;
insert into lottery_price(id,amount,value,lottery_id) values(16,15, 2252250,2) on conflict do nothing;


insert into jackpot (id, date,description,lottery_id) values(1,now()+ interval '1 day','normal',1) on conflict do nothing;
