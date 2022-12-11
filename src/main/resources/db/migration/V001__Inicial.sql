CREATE SCHEMA auditoria AUTHORIZATION postgres;

create table auditoria.compras_auditoria (
    id uuid not null,
    revisao int4 not null,
    tipo int2,
    data timestamp,
    chave_de_acesso varchar(255),
    url varchar(255),
    total numeric(19, 2),
    supermercado_id uuid,
    criado timestamp,
    primary key (id, revisao)
);

create table auditoria.itens_auditoria (
    id uuid not null,
    revisao int4 not null,
    tipo int2,
    compra_id uuid,
    produto_id uuid,
    qtd numeric(19, 2),
    valor numeric(19, 2),
    primary key (id, revisao)
);

create table auditoria.produtos_auditoria (
    id uuid not null,
    revisao int4 not null,
    tipo int2,
    ean varchar(255),
    nome varchar(255),
    unidade_id uuid,
    criado timestamp,
    primary key (id, revisao)
);

create table auditoria.revinfo (
    rev int4 not null,
    revtstmp int8,
    primary key (rev)
);

create table auditoria.supermercados_auditoria (
    id uuid not null,
    revisao int4 not null,
    tipo int2,
    nome varchar(255),
    cnpj varchar(255),
    inscricao_estadual varchar(255),
    informacoes_complementares TEXT,
    criado timestamp,
    primary key (id, revisao)
);

create table auditoria.unidades_auditoria (
    id uuid not null,
    revisao int4 not null,
    tipo int2,
    descricao varchar(255),
    criado timestamp,
    primary key (id, revisao)
);
create sequence hibernate_sequence start 1 increment 1;

create table compras (
    id uuid not null,
    data timestamp not null,
    chave_de_acesso varchar(255) not null,
    url varchar(255) not null,
    total numeric(19, 2) not null,
    supermercado_id uuid,
    criado timestamp,
    primary key (id)
);

create table itens (
    id uuid not null,
    compra_id uuid,
    produto_id uuid,
    qtd numeric(19, 2) not null,
    valor numeric(19, 2) not null,
    primary key (id)
);

create table produtos (
    id uuid not null,
    ean varchar(255) not null,
    nome varchar(255) not null,
    unidade_id uuid,
    criado timestamp,
    primary key (id)
);

create table supermercados (
    id uuid not null,
    nome varchar(255) not null,
    cnpj varchar(255) not null,
    inscricao_estadual varchar(255) not null,
    criado timestamp,
    informacoes_complementares TEXT not null,
    primary key (id)
);

create table unidades (
    id uuid not null,
    descricao varchar(255) not null,
    criado timestamp,
    primary key (id)
);

alter table auditoria.compras_auditoria
   add constraint FKfrjardqc11dpjvgwxn9flhr3k
   foreign key (revisao)
   references auditoria.revinfo;

alter table auditoria.itens_auditoria
   add constraint FKsxe0dlwb4el6moalbsvyeuvpa
   foreign key (revisao)
   references auditoria.revinfo;

alter table auditoria.produtos_auditoria
   add constraint FK50be0sifh6hxek03mu2vwovb6
   foreign key (revisao)
   references auditoria.revinfo;

alter table auditoria.supermercados_auditoria
   add constraint FK2qdefigjyy3358lisbg2g0ctp
   foreign key (revisao)
   references auditoria.revinfo;

alter table auditoria.unidades_auditoria
   add constraint FKfxqdmpra69m2qm0enaxcow1l7
   foreign key (revisao)
   references auditoria.revinfo;

alter table compras
   add constraint FKimvim3lcu6saf8mvx0leg1tlh
   foreign key (supermercado_id)
   references supermercados;

alter table itens
   add constraint FKolx1s20dfj30c6rdwpsr0g4be
   foreign key (compra_id)
   references compras;

alter table itens
   add constraint FKr016ru8uflu796o6kx0igbnxj
   foreign key (produto_id)
   references produtos;

alter table produtos
   add constraint FKcgto38lvx0saawa86eh4upi6r
   foreign key (unidade_id)
   references unidades;
