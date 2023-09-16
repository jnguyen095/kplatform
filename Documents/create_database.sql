CREATE USER kang WITH CREATEDB REPLICATION PASSWORD '123456';
ALTER USER kang WITH superuser;
CREATE DATABASE "KPlatform"  WITH OWNER = kang  ENCODING = 'UTF8' TABLESPACE = pg_default CONNECTION LIMIT = -1;

create schema KPlatform;

set SEARCH_PATH to KPlatform;

CREATE TABLE UserGroup (
	UserGroupID BIGSERIAL NOT NULL PRIMARY KEY,
	Code VARCHAR NOT NULL,
	groupName VARCHAR NOT NULL,

	UNIQUE(Code)
);
insert into UserGroup(Code, groupName) values('SYSADMIN', 'System Admin');
insert into UserGroup(Code, groupName) values('ADMIN', 'Admin');
insert into UserGroup(Code, groupName) values('CASHIER', 'CASHIER');
insert into UserGroup(Code, groupName) values('BARTENDER', 'Bartender');
insert into UserGroup(Code, groupName) values('WAITRESS', 'Waitress');
insert into UserGroup(Code, groupName) values('USER', 'User');

CREATE TABLE us3r (
	Us3rID BIGSERIAL NOT NULL PRIMARY KEY,
	UserGroupID BIGINT NOT NULL REFERENCES UserGroup(UserGroupID),
	UserName VARCHAR NOT NULL UNIQUE,
	Password VARCHAR NOT NULL,
	Email VARCHAR(125),
	Phone VARCHAR(125),
	Status BOOLEAN NOT NULL DEFAULT TRUE,
	CreatedDate TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	ModifiedDate TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE AreaName (
	AreaNameId BIGSERIAL NOT NULL PRIMARY KEY,
	AreaName VARCHAR NOT NULL,
	Status BOOLEAN NOT NULL,
	CreatedDate TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	ModifiedDate TIMESTAMP WITHOUT TIME ZONE,

	UNIQUE(AreaName)
);

CREATE TABLE TableName (
	TableNameId BIGSERIAL NOT NULL PRIMARY KEY,
	AreaNameId BIGINT NOT NULL REFERENCES AreaName(AreaNameId),
	TableName VARCHAR NOT NULL,
	Status BOOLEAN NOT NULL,
	CreatedDate TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	ModifiedDate TIMESTAMP WITHOUT TIME ZONE,

	UNIQUE(TableName)
);

alter table TableName add column AreaNameId BIGINT NOT NULL REFERENCES AreaName(AreaNameId);
