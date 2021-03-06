-- CREATE TABLES
--create table public.teste (
--	id  bigserial not null, 
--	name varchar(255) not null, 
--	ufstate varchar(2) not null, 
--	primary key (id)
--);
create table public.city (
	id  bigserial not null, 
	name varchar(255) not null, 
	ufstate varchar(2) not null, 
	primary key (id)
);

create table public.kitchen (
	id  bigserial not null, 
	name varchar(255) not null, 
	primary key (id)
);

create table public.order (
	id  bigserial not null, 
	code varchar(255) not null, 
	subtotal numeric(19, 2), 
	deliveryfee numeric(19, 2), 
	totalprice numeric(19, 2), 
	status varchar(255),
	zipcode varchar(255), 
	street varchar(255), 
	complement varchar(255), 
	number varchar(255), 
	district varchar(255), 
	idcity int8, 
	idpaymentmethod int8 not null, 
	idrestaurant int8 not null, 
	idsystemuser int8 not null, 
	confirmedat TIMESTAMP, 
	deliveryat TIMESTAMP, 
	createdat TIMESTAMP not null, 
	canceledat TIMESTAMP, 
	primary key (id)
);

create table public.orderitem (
	id  bigserial not null, 
	unitprice numeric(19, 2), 
	quantity int4 not null, 
	totalprice numeric(19, 2), 
	note varchar(255), 
	idorder int8 not null, 
	idproduct int8 not null, 
	primary key (id)
);

create table public.paymentmethod (
	id  bigserial not null, 
	description varchar(255) not null, 
	primary key (id)
);

create table public.permission (
	id  bigserial not null, 
	name varchar(255) not null, 
	description varchar(255) not null, 
	primary key (id)
);

create table public.product (
	id  bigserial not null, 
	name varchar(255) not null, 
	description varchar(255) not null, 
	price numeric(19, 2) not null, 
	active boolean not null, 
	idrestaurant int8 not null, 
	primary key (id)
);

create table public.restaurant (
	id  bigserial not null, 
	name varchar(255) not null,
	deliveryfee numeric(19, 2) not null, 
	street varchar(255), 
	number varchar(255), 
	complement varchar(255), 
	district varchar(255), 
	zipcode varchar(255), 
	active boolean not null default true,
	open boolean not null default true,
	idcity int8, 
	idkitchen int8 not null, 
	createdat TIMESTAMP not null, 
	updatedat TIMESTAMP not null,
	primary key (id)
);

create table public.restaurantpayment (
	idrestaurant int8 not null, 
	idpaymentmethod int8 not null,
	primary key (idrestaurant, idpaymentmethod)
);

create table public.role (
	id  bigserial not null, 
	name varchar(255) not null, 
	primary key (id)
);

create table public.rolepermission (
	idrole int8 not null, 
	idpermission int8 not null,
	primary key (idrole, idpermission)
);

create table public.state (
	id  bigserial not null, 
	name varchar(255) not null, 
	uf varchar(2) not null, 
	primary key (id)
);

create table public.systemuser (
	id  bigserial not null, 
	name varchar(255) not null, 
	email varchar(255) not null, 
	password varchar(255) not null, 
	createdat TIMESTAMP not null, 
	primary key (id)
);

create table public.systemuserrole (
	idsystemuser int8 not null,
	idrole int8 not null,
	primary key (idsystemuser, idrole)
);

create table public.restaurantsystemuser (
	idrestaurant int8 not null,
	idsystemuser int8 not null,
	primary key (idrestaurant, idsystemuser)
);

create table public.productimage (
	id bigserial NOT NULL,
	idproduct int8 NOT NULL,
	filename varchar(150) NOT NULL,
	description varchar(150) NOT NULL,
	contenttype varchar(80) NOT NULL,
	size int8 NOT NULL,
	PRIMARY KEY (id)
);

create table oauth_client_details (
  client_id varchar(255),
  resource_ids varchar(256),
  client_secret varchar(256),
  scope varchar(256),
  authorized_grant_types varchar(256),
  web_server_redirect_uri varchar(256),
  authorities varchar(256),
  access_token_validity integer,
  refresh_token_validity integer,
  additional_information varchar(4096),
  autoapprove varchar(256),
  primary key (client_id)
);


-- CREATE CONSTRAINTS
alter table public.order add constraint UK_CODE_ORDER unique (code);

alter table public.state add constraint UK_UF_STATE unique (uf);

alter table public.systemuser add constraint UK_EMAIL_USERSYSTEM unique (email);

alter table public.city add constraint FK_STATE_CITY foreign key (ufstate) references public.state(uf);

alter table public.order add constraint FK_CITY_ORDER foreign key (idcity) references public.city;
alter table public.order add constraint FK_PAYMENT_ORDER foreign key (idpaymentmethod) references public.paymentmethod;
alter table public.order add constraint FK_RESTAURANT_ORDER foreign key (idrestaurant) references public.restaurant;
alter table public.order add constraint FK_SYSTEMUSER_ORDER foreign key (idsystemuser) references public.systemuser;

alter table public.orderitem add constraint UK_ORDER_PRODUCT_ORDERITEM unique (idorder, idproduct);
alter table public.orderitem add constraint FK_ORDER_ORDERITEM foreign key (idorder) references public.order;
alter table public.orderitem add constraint FK_PRODUCT_ORDERITEM foreign key (idproduct) references public.product;

alter table public.product add constraint FK_RESTAURANT_PRODUCT foreign key (idrestaurant) references public.restaurant;

alter table public.restaurant add constraint FK_CITY_RESTAURANT foreign key (idcity) references public.city;
alter table public.restaurant add constraint FK_KITCHEN_RESTAURANT foreign key (idkitchen) references public.kitchen;

alter table public.restaurantpayment add constraint FK_PAYMENT_RESTAURANTPAYMENT foreign key (idpaymentmethod) references public.paymentmethod;
alter table public.restaurantpayment add constraint FK_RESTAURANT_RESTAURANTPAYMENT foreign key (idrestaurant) references public.restaurant;

alter table public.rolepermission add constraint FK_PERMISSION_ROLEPERMISSION foreign key (idpermission) references public.permission;
alter table public.rolepermission add constraint FKROLE_ROLEPERMISSION foreign key (idrole) references public.role;

alter table public.systemuserrole add constraint FK_SYSTEMYSER_SYSTEMYSERROLE foreign key (idsystemuser) references public.systemuser;
alter table public.systemuserrole add constraint FK_ROLE_SYSTEMYSERROLE foreign key (idrole) references public.role;

alter table public.restaurantsystemuser add constraint FK_RESTAURANT_RESTAURANTSYSTEMUSER foreign key (idrestaurant) references public.restaurant;
alter table public.restaurantsystemuser add constraint FK_SYSTEMYSER_RESTAURANTSYSTEMUSER foreign key (idsystemuser) references public.systemuser;

alter table public.productimage add constraint FK_ID_PRODUCT foreign key (idproduct) references public.product;
