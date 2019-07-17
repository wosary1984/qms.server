--- id generator
insert  into t_id_generator(id, pk_name, pk_value) VALUES (1,'USER_PK', 1000); 
insert  into t_id_generator(id, pk_name, pk_value) VALUES (2,'PERSON_PK', 1000); 

--initial admin user
insert  into t_user(c_userId, c_username, c_password,c_locked) values(3,'feng','$2a$10$trT3.R/Nfey62eczbKEnueTcIbJXW.u1ffAo/XfyLpofwNDbEB86O',false);
insert  into t_user(c_userId, c_username, c_password,c_locked) values(1,'user','$2a$10$trT3.R/Nfey62eczbKEnueTcIbJXW.u1ffAo/XfyLpofwNDbEB86O',false);
insert  into t_user(c_userId, c_username, c_password,c_locked) values(2,'bruce','$2a$10$trT3.R/Nfey62eczbKEnueTcIbJXW.u1ffAo/XfyLpofwNDbEB86O',true);
