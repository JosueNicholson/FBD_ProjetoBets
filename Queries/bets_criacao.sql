CREATE TABLE USERS(
	IDUSER SERIAL,
	NAMEUSER VARCHAR(45),
	CONSTRAINT USERS_PK_1 PRIMARY KEY (IDUSER)
);

CREATE TABLE TEAMS(
	IDTEAM SERIAL,
	NAMETEAM VARCHAR(45),
	SHORTNAME VARCHAR(5),
	CONSTRAINT TEAMS_PK PRIMARY KEY(IDTEAM)
);

CREATE TABLE MATCHES(
	IDMATCH SERIAL,
	IDHOMETEAM INTEGER,
	IDAWAYTEAM INTEGER,
	STATUS VARCHAR(15),
	WINNER INTEGER,
	CONSTRAINT MATCHES_PK_1 PRIMARY KEY (IDMATCH),
	CONSTRAINT MATCHES_FK_1 FOREIGN KEY (IDHOMETEAM) REFERENCES TEAMS (IDTEAM),
	CONSTRAINT MATCHES_FK_2 FOREIGN KEY (IDAWAYTEAM) REFERENCES TEAMS (IDTEAM)
);

CREATE TABLE BETS(
	IDBET INTEGER,
	IDUSER INTEGER,
	CONSTRAINT BETS_PK_1 PRIMARY KEY(IDBET),
	CONSTRAINT BETS_FK_1 FOREIGN KEY(IDUSER) REFERENCES USERS(IDUSER)
);

CREATE TABLE SHOTS(
	IDSHOT SERIAL,
	IDBET INTEGER,
	IDMATCH INTEGER,
	WINNER INTEGER,
	CONSTRAINT SHOTS_PK_1 PRIMARY KEY(IDSHOT, IDBET),
	CONSTRAINT SHOTS_FK_1 FOREIGN KEY(IDBET) REFERENCES BETS(IDBET) ON DELETE CASCADE,
	CONSTRAINT SHOTS_FK_2 FOREIGN KEY(IDMATCH) REFERENCES MATCHES(IDMATCH) ON DELETE CASCADE
);
--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
CREATE OR REPLACE FUNCTION DEL_DEPT_USER_FUNC() RETURNS TRIGGER AS
$$
BEGIN
	DELETE FROM BETS B WHERE B.IDUSER=OLD.IDUSER;
	RETURN OLD;
END;
$$
LANGUAGE PLPGSQL;

CREATE TRIGGER DEL_DEPT_USER BEFORE DELETE ON USERS
FOR EACH ROW EXECUTE PROCEDURE DEL_DEPT_USER_FUNC();
--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
CREATE OR REPLACE FUNCTION DEL_DEPT_BETS_FUNC() RETURNS TRIGGER AS
$$
BEGIN
	DELETE FROM SHOTS S WHERE S.IDBET=OLD.IDBET;
	RETURN OLD;
END;
$$
LANGUAGE PLPGSQL;

CREATE TRIGGER DEL_DEPT_BETS BEFORE DELETE ON BETS
FOR EACH ROW EXECUTE PROCEDURE DEL_DEPT_BETS_FUNC();
--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
CREATE OR REPLACE FUNCTION DEL_DEPT_MATCH_FUNC() RETURNS TRIGGER AS
$$
BEGIN
	DELETE FROM SHOTS S WHERE S.IDMATCH=OLD.IDMATCH;
	RETURN OLD;
END;
$$
LANGUAGE PLPGSQL;

CREATE TRIGGER DEL_DEPT_MATCH BEFORE DELETE ON MATCHES
FOR EACH ROW EXECUTE PROCEDURE DEL_DEPT_MATCH_FUNC();
--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
CREATE OR REPLACE FUNCTION DEL_DEPT_TEAM_FUNC() RETURNS TRIGGER AS
$$
BEGIN
	DELETE FROM MATCHES M WHERE M.IDHOMETEAM=OLD.IDTEAM OR M.IDAWAYTEAM=OLD.IDTEAM;
	RETURN OLD;
END;
$$
LANGUAGE PLPGSQL;

CREATE TRIGGER DEL_DEPT_TEAM BEFORE DELETE ON TEAMS
FOR EACH ROW EXECUTE PROCEDURE DEL_DEPT_TEAM_FUNC();
--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
CREATE OR REPLACE FUNCTION VERIFY_WINNER_SHOT_FUNC() RETURNS TRIGGER AS
$$
BEGIN
	IF NEW.WINNER>=0 AND NEW.WINNER<=2 THEN
		RETURN NEW;
	ELSE
		RAISE EXCEPTION 'Esse Palpite Só Aceita Campo "Winner" Igual a 0, 1 ou 2';
		RETURN NULL;
	END IF;
END;
$$
LANGUAGE PLPGSQL;

CREATE TRIGGER VERIFY_WINNER_SHOT BEFORE INSERT OR UPDATE ON SHOTS
FOR EACH ROW EXECUTE PROCEDURE VERIFY_WINNER_SHOT_FUNC();

--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
CREATE OR REPLACE FUNCTION PREVENT_DUPLICATE_SHOT_FUNC() RETURNS TRIGGER AS
$$
DECLARE
	QTD_SHOTS INT;
BEGIN
	SELECT COUNT(*) INTO QTD_SHOTS FROM SHOTS S WHERE S.IDBET=NEW.IDBET AND IDMATCH=NEW.IDMATCH;
	IF QTD_SHOTS < 1 THEN
		RETURN NEW;
	ELSE
		RAISE EXCEPTION 'Você não pode duplicar palpites em uma mesma aposta!';
		RETURN NULL;
	END IF;
END;
$$
LANGUAGE PLPGSQL;

CREATE TRIGGER PREVENT_DUPLICATE_SHOT BEFORE INSERT ON SHOTS
FOR EACH ROW EXECUTE PROCEDURE PREVENT_DUPLICATE_SHOT_FUNC();
--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
CREATE OR REPLACE FUNCTION VERIFY_STATUS_WINNER_FUNC() RETURNS TRIGGER AS
$$
BEGIN
	IF NEW.STATUS='Agendada' AND NEW.WINNER<>-1 THEN
		RAISE EXCEPTION 'Partida "Agendada" Só Aceita Campo "Winner" Igual a -1';
		RETURN NULL;
	ELSIF NEW.STATUS='Finalizada' AND NEW.WINNER=-1 THEN
		RAISE EXCEPTION 'Partida "Finalizada" Só Aceita Campo "Winner" Diferente de -1';
		RETURN NULL;
	ELSIF NEW.STATUS='Finalizada' AND NEW.WINNER BETWEEN 0 AND 2 THEN
		RETURN NEW;
	ELSE 
		RAISE EXCEPTION 'Partida "Finalizada" Só Aceita Campo "Winner" Igual a 0, 1 ou 2';
		RETURN NULL;
	END IF;
END;
$$
LANGUAGE PLPGSQL;

CREATE TRIGGER VERIFY_STATUS_WINNER BEFORE UPDATE ON MATCHES
FOR EACH ROW EXECUTE PROCEDURE VERIFY_STATUS_WINNER_FUNC();
--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
CREATE OR REPLACE FUNCTION NO_REPEAT_TEAM_FUNC() RETURNS TRIGGER AS
$$
BEGIN
	IF NEW.IDHOMETEAM=NEW.IDAWAYTEAM THEN
		RAISE EXCEPTION 'Os Times não podem ser iguais!';
		RETURN NULL;
	ELSE RETURN NEW;
	END IF;
END;
$$
LANGUAGE PLPGSQL;

CREATE TRIGGER NO_REPEAT_TEAM BEFORE INSERT OR UPDATE ON MATCHES
FOR EACH ROW EXECUTE PROCEDURE NO_REPEAT_TEAM_FUNC();
--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
CREATE OR REPLACE FUNCTION GETALLMATCHES() RETURNS TABLE (
	IDMATCH INTEGER,
	IDHOMETEAM INTEGER,
	IDAWAYTEAM INTEGER,
	STATUS VARCHAR(15),
	WINNER INTEGER,
	NAMEHOMETEAM VARCHAR(45),
	NAMEAWAYTEAM VARCHAR (45),
	SHORTNAMEHOMETEAM VARCHAR(45),
	SHORTNAMEAWAYTEAM VARCHAR (45)) AS
$$
BEGIN
	RETURN QUERY
	SELECT M.IDMATCH, M.IDHOMETEAM, M.IDAWAYTEAM, M.STATUS, M.WINNER, HTEAM.NAMETEAM, ATEAM.NAMETEAM, HTEAM.SHORTNAME, ATEAM.SHORTNAME
			FROM MATCHES M, (SELECT * FROM TEAMS T) AS HTEAM, (SELECT * FROM TEAMS) AS ATEAM
			WHERE HTEAM.IDTEAM=M.IDHOMETEAM AND ATEAM.IDTEAM=M.IDAWAYTEAM;
	RETURN;
END;
$$
LANGUAGE PLPGSQL;
SELECT * FROM GETALLMATCHES() GAM, SHOTS S WHERE S.IDBET=3 AND S.IDMATCH=GAM.IDMATCH;
--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
CREATE OR REPLACE FUNCTION GETMATCHBYTEAMID(IDTEAM_PARAM INT) RETURNS TABLE (
	IDMATCH INTEGER,
	IDHOMETEAM INTEGER,
	IDAWAYTEAM INTEGER,
	STATUS VARCHAR(15),
	WINNER INTEGER,
	NAMEHOMETEAM VARCHAR(45),
	NAMEAWAYTEAM VARCHAR (45),
	SHORTNAMEHOMETEAM VARCHAR(45),
	SHORTNAMEAWAYTEAM VARCHAR (45)) AS
$$
BEGIN
	RETURN QUERY
	SELECT M.IDMATCH, M.IDHOMETEAM, M.IDAWAYTEAM, M.STATUS, M.WINNER, HTEAM.NAMETEAM, ATEAM.NAMETEAM, HTEAM.SHORTNAME, ATEAM.SHORTNAME
			FROM MATCHES M, (SELECT * FROM TEAMS T) AS HTEAM, (SELECT * FROM TEAMS) AS ATEAM
			WHERE HTEAM.IDTEAM=M.IDHOMETEAM AND ATEAM.IDTEAM=M.IDAWAYTEAM AND (HTEAM.IDTEAM=IDTEAM_PARAM OR ATEAM.IDTEAM=IDTEAM_PARAM);
	RETURN;
END;
$$
LANGUAGE PLPGSQL;
-- SELECT * FROM GETMATCHBYTEAMID(2)
--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
CREATE OR REPLACE FUNCTION GETMATCHBYMATCHID(IDMATCH_PARAM INT) RETURNS TABLE (
	IDMATCH INTEGER,
	IDHOMETEAM INTEGER,
	IDAWAYTEAM INTEGER,
	STATUS VARCHAR(15),
	WINNER INTEGER,
	NAMEHOMETEAM VARCHAR(45),
	NAMEAWAYTEAM VARCHAR (45),
	SHORTNAMEHOMETEAM VARCHAR(45),
	SHORTNAMEAWAYTEAM VARCHAR (45)) AS
$$
BEGIN
	RETURN QUERY
	SELECT M.IDMATCH, M.IDHOMETEAM, M.IDAWAYTEAM, M.STATUS, M.WINNER, HTEAM.NAMETEAM, ATEAM.NAMETEAM, HTEAM.SHORTNAME, ATEAM.SHORTNAME
			FROM MATCHES M, (SELECT * FROM TEAMS T) AS HTEAM, (SELECT * FROM TEAMS) AS ATEAM
			WHERE HTEAM.IDTEAM=M.IDHOMETEAM AND ATEAM.IDTEAM=M.IDAWAYTEAM AND M.IDMATCH=IDMATCH_PARAM;
	RETURN;
END;
$$
LANGUAGE PLPGSQL;
--SELECT * FROM GETMATCHBYMATCHID(14)