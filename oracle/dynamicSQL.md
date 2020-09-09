```sql
CREATE OR REPLACE PROCEDURE

select_member ( search_word in varchar2, sort_type in varchar2, out_cursor out SYS_REFCURSOR)

IS
v_query varchar(1000);
v_search_word MEMBER.email%TYPE := '%' || search_word || '%';

BEGIN
v_query :=    'select m.no, m.email, m.password, m.birthday, m.score, utl_raw.cast_to_varchar2(dbms_lob.substr(m.profile, dbms_lob.getlength(m.profile))) profile ,m.gender, m.nickname, m.regdate,
(select count(mLog.no) from MEMBER_LOG mLog where m.no = mLog.member_no and mLog.action = '||'login'||') as countVisits ,
(select count(c.no) from COMMENTS c where  m.no = c.member_no) as countComments,
(select count(a.no) from  ARTICLE a where m.no = a.member_no) as countArticles' || CHR(13);
v_query := v_query || ' from MEMBER m' || CHR(13);

IF search_word IS NOT NULL THEN
v_query := v_query || ' where m.email like '||''''||v_search_word||''''|| CHR(13);
END IF;

IF sort_type = 'latestMember' THEN
v_query := v_query ||  ' order by m.no DESC';
ELSIF sort_type = 'hardMember' THEN
v_query := v_query || ' order by (countVisits+countComments+countArticles) DESC, m.no DESC';
END IF;

    BEGIN

    -- CURSOR를 리턴 받을때
    EXECUTE IMMEDIATE v_query
        USING search_word, sort_type;
    END;

END;
/
```

```sql
SQL> var out_cursor refcursor;
SQL> exec select_member('gmail', 'latestMember', :out_cursor);
SQL> print out_cursor
exec select_member('AAAA@gmail.com', 'latestMember', :out_cursor);
```

```sql
CREATE OR REPLACE PROCEDURE select_member( search_word in varchar2, sort_type in varchar2, out_cursor out SYS_REFCURSOR)

IS
v_query varchar(1000);
v_search_word MEMBER.email%TYPE := '%'||search_word||'%';

BEGIN
v_query :=    'select m.no, m.email, m.password, m.birthday, m.score, utl_raw.cast_to_varchar2(dbms_lob.substr(m.profile, dbms_lob.getlength(m.profile))) profile ,m.gender, m.nickname, m.regdate, (select count(mLog.no) from MEMBER_LOG mLog where m.no = mLog.member_no and mLog.action = '||'''login'''||') as countVisits, (select count(c.no) from COMMENTS c where  m.no = c.member_no) as countComments, (select count(a.no) from  ARTICLE a where m.no = a.member_no) as countArticles' || CHR(13);
v_query := v_query || ' from MEMBER m' || CHR(13);

IF search_word IS NOT NULL THEN
v_query := v_query || ' where m.email like '||''''||v_search_word||''''|| CHR(13);
END IF;

IF sort_type = 'latestMember' THEN
v_query := v_query ||  ' order by m.no DESC';
ELSIF sort_type = 'hardMember' THEN
v_query := v_query || ' order by (countVisits+countComments+countArticles) DESC, m.no DESC';
END IF;
	OPEN out_cursor FOR v_query;
END;
/
```



```sql
select y.*
from(
  select rownum as r, x.*
	from(
    select m.no, m.email, m.password, m.birthday, m.score, utl_raw.cast_to_varchar2(dbms_lob.substr(m.profile, dbms_lob.getlength(m.profile))) profile ,m.gender, m.nickname, m.regdate,
(select count(mLog.no) from MEMBER_LOG mLog where m.no = mLog.member_no and mLog.action = 'login') as countVisits ,
(select count(c.no) from COMMENTS c where  m.no = c.member_no) as countComments,
(select count(a.no) from  ARTICLE a where m.no = a.member_no) as countArticles
from MEMBER m 
where m.email like '%gmail%'
order by (countVisits+countComments+countArticles) DESC, m.no DESC) x
) y
where y.r BETWEEN 1 AND 2;
```

```sql
select count(no)
from MEMBER 
where m.email like '%gmail%';
```

```sql
CREATE OR REPLACE FUNCTION total_member ( search_word in varchar2) RETURN NUMBER IS
 		 v_search_word MEMBER.email%TYPE := '%'||search_word||'%';
     v_sql VARCHAR2(1000);
     v_returnValue NUMBER;
BEGIN
 
    BEGIN
 
        v_sql := ' select count(no) '||CHR(13);
        v_sql := v_sql ||'  from MEMBER '||CHR(13);
       	IF search_word IS NOT NULL THEN
           v_sql := v_sql ||' where email like '||''''||v_search_word||''''||CHR(13);
        END IF;
                  
        EXECUTE IMMEDIATE v_sql INTO v_returnValue;
 
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        v_returnValue := ' ';
      WHEN OTHERS THEN
        v_returnValue := ' ';
    END;
    
    RETURN v_returnValue;
    
END;
/

```



```sql
CREATE OR REPLACE PROCEDURE select_member( search_word in varchar2, sort_type in varchar2, start in number, end in number, out_cursor out SYS_REFCURSOR)

IS
v_query varchar(1000);
v_search_word MEMBER.email%TYPE := '%'||search_word||'%';

BEGIN
v_query := 'select y.*' || CHR(13);
v_query := v_query || ' from( select rownum as r, x.* from( select m.no, m.email, m.password, m.birthday, m.score, utl_raw.cast_to_varchar2(dbms_lob.substr(m.profile, dbms_lob.getlength(m.profile))) profile ,m.gender, m.nickname, m.regdate, (select count(mLog.no) from MEMBER_LOG mLog where m.no = mLog.member_no and mLog.action = '||'''login'''||') as countVisits , (select count(c.no) from COMMENTS c where  m.no = c.member_no) as countComments, (select count(a.no) from  ARTICLE a where m.no = a.member_no) as countArticles' || CHR(13);
v_query := v_query || ' from MEMBER m ' || CHR(13);

IF search_word IS NOT NULL THEN
v_query := v_query || ' where m.email like '||''''||v_search_word||''''|| CHR(13);
END IF;

IF sort_type = 'latestMember' THEN
v_query := v_query || ' order by m.no DESC) x) y' || CHR(13);
ELSIF sort_type = 'hardMember' THEN
v_query := v_query || ' order by (countVisits+countComments+countArticles) DESC, m.no DESC) x) y' || CHR(13);
END IF;
v_query := v_query || ' where y.r BETWEEN '|| start_page ||' AND ' || end_page;
	OPEN out_cursor FOR v_query;
END;
/
```



```sql
PROCEDURE select_member( search_word in varchar2, sort_type in varchar2, start_page in number, end_page in number, out_cursor out SYS_REFCURSOR)

IS
v_query varchar(1000);
v_search_word MEMBER.email%TYPE := '%'||search_word||'%';

BEGIN
v_query := 'select y.*' || CHR(13);
v_query := v_query || ' from( select rownum as r, x.* from( select m.no, m.email, m.password, m.birthday, m.score, utl_raw.cast_to_varchar2(dbms_lob.substr(m.profile, dbms_lob.getlength(m.profile))) profile ,m.gender, m.nickname, m.regdate, (select count(mLog.no) from MEMBER_LOG mLog where m.no = mLog.member_no and mLog.action = '||'''login'''||') as countVisits , (select count(c.no) from COMMENTS c where  m.no = c.member_no) as countComments, (select count(a.no) from  ARTICLE a where m.no = a.member_no) as countArticles' || CHR(13);
v_query := v_query || ' from MEMBER m ' || CHR(13);

IF search_word IS NOT NULL THEN
v_query := v_query || ' where m.email like '||''''||v_search_word||''''|| CHR(13);
END IF;

IF sort_type = 'latestMember' THEN
v_query := v_query || ' order by m.no DESC) x) y' || CHR(13);
ELSIF sort_type = 'hardMember' THEN
v_query := v_query || ' order by (countVisits+countComments+countArticles) DESC, m.no DESC) x) y' || CHR(13);
END IF;
v_query := v_query || ' where y.r BETWEEN '|| start_page ||' AND ' || end_page;

	OPEN out_cursor FOR v_query;
END;


```



```sql
SQL> var out_cursor refcursor;
SQL> exec select_contents('arena', NULL, NULL,NULL,NULL,NULL, 1, 10, 'latest', :out_cursor);
SQL> print out_cursor
```



```sql
Last login: Mon Aug 10 16:12:01 on ttys001

The default interactive shell is now zsh.
To update your account to use zsh, please run `chsh -s /bin/zsh`.
For more details, please visit https://support.apple.com/kb/HT208050.
MacBook-Pro:~ kimdabin$ cd playdata/
MacBook-Pro:playdata kimdabin$ ls
algorithm-study	pre-education	test.txt	test03.txt
docs		test-workspace	test02.txt	workspace
MacBook-Pro:playdata kimdabin$ vi testP.txt
MacBook-Pro:playdata kimdabin$ cat -n testP.txt 
     1	CREATE OR REPLACE PROCEDURE select_contents( tab in varchar2, search_word in varchar2 , start_year in number, start_quarter in number, end_year in number, end_quarter in number, start_page in number, end_page in number, sort_type in varchar2, out_cursor out SYS_REFCURSOR)
     2	
     3	IS
     4	v_query varchar(1000);
     5	v_search_word COMPANY.STOCK_CODE%TYPE := ''''||search_word||'''';
     6	
     7	BEGIN
     8	v_query := 'select tb2.* from( select rownum r, tb1.* from('||CHAR(13);
     9	
    10	IF tab = 'finance' THEN
    11	v_query := v_query || ' select i.name "industryName", comp.company "company",comp.stock_listed "stockListed",comp.capital "capital", comp.facevalue "facevalue", comp.currency "currency" ,f.no "financeNo", f.year "year", f.quarter "quarter", comp.stock_code "stockCode", f.account_code "accountCode", fc.account_nm "accountName", fc.label_kor "labelKor", fc.sj_div "sjDiv", NVL(f.account_value,0) "accountValue"
    12	from finance f, finance_cate fc, company comp, industry i
    13	where comp.industry_no=i.no and comp.stock_code = f.stock_code and f.account_code = fc.account_id ' || CHAR(13);
    14	
    15		IF search_word IS NOT NULL THEN
    16		v_query:= v_query || ' and comp.stock_code LIKE '||v_search_word||CHAR(13);
    17		END IF;
    18		
    19		IF start_year IS NOT NULL AND start_quarter IS NOT NULL AND end_year IS NOT NULL AND end_quarter IS NOT NULL THEN
    20		v_query:= v_query ||' and f.year BETWEEN'|| start_year ||' AND '|| end_year ||' and f.quarter BETWEEN '|| start_quarter ||' AND '|| end_quarter || CHAR(13);
    21		END IF;
    22		
    23	v_query:= v_query || ' order by f.year desc, f.quarter desc ) tb1) tb2 '||CHAR(13);
    24	
    25	ELSIF tab = 'arena' THEN
    26	v_query:= v_query || ' select a.no "no", company "company", i.name "industry" , m.nickname "nickname", m.no "memberNo" ,title "title", a.stock_code "stockCode", a.regdate "regdate",
    27	(select count(p.no) from pick p where p.article_no = a.no and p.opinion='Y') "buyingCount",
    28	(select count(p.no) from pick p where p.article_no = a.no and p.opinion='N') "sellCount", 
    29	(select count(comm.no) from comments comm where comm.article_no = a.no) "commentsCount"
    30	from article a, company c, industry i, member m
    31	where a.stock_code = c.stock_code and m.no = a.member_no and i.no = c.industry_no '||CHAR(13);
    32	
    33		IF search_word IS NOT NULL THEN
    34		v_query:= v_query || ' and c.stock_code LIKE '||v_search_word||CHAR(13);
    35		END IF;
    36		
    37		IF sort_type = 'latest' THEN
    38		v_query := v_query || ' order by no DESC) tb1) tb2 ' || CHR(13);
    39		ELSIF sort_type = 'hot' THEN
    40		v_query := v_query || ' order by "buyingCount"+"sellCount"+"commentsCount" DESC, "no" DESC) tb1) tb2 ' || CHR(13);
    41		END IF;
    42	
    43	END IF;
    44	v_query := v_query || ' where tb2.r BETWEEN '|| start_page ||' AND ' || end_page;
    45	OPEN out_cursor FOR v_query;
    46	END;
    47	/
MacBook-Pro:playdata kimdabin$ ls
algorithm-study	pre-education	test.txt	test03.txt	workspace
docs		test-workspace	test02.txt	testP.txt
MacBook-Pro:playdata kimdabin$ rm -rf testP.txt 
MacBook-Pro:playdata kimdabin$ vi testP.txt
MacBook-Pro:playdata kimdabin$ cat -n testP.txt 
     1	CREATE OR REPLACE PROCEDURE select_contents( tab in varchar2, search_word in varchar2 , start_year in number, start_quarter in number, end_year in number, end_quarter in number, start_page in number, end_page in number, sort_type in varchar2, out_cursor out SYS_REFCURSOR)
     2	
     3	IS
     4	v_query varchar(1000);
     5	v_search_word COMPANY.STOCK_CODE%TYPE := ''''||search_word||'''';
     6	
     7	BEGIN
     8	v_query := 'select tb2.* from( select rownum r, tb1.* from('||CHAR(13);
     9	
    10	IF tab = 'finance' THEN
    11	v_query := v_query || ' select i.name "industryName", comp.company "company",comp.stock_listed "stockListed",comp.capital "capital", comp.facevalue "facevalue", comp.currency "currency" ,f.no "financeNo", f.year "year", f.quarter "quarter", comp.stock_code "stockCode", f.account_code "accountCode", fc.account_nm "accountName", fc.label_kor "labelKor", fc.sj_div "sjDiv", NVL(f.account_value,0) "accountValue"
    12	from finance f, finance_cate fc, company comp, industry i
    13	where comp.industry_no=i.no and comp.stock_code = f.stock_code and f.account_code = fc.account_id ' || CHAR(13);
    14	
    15		IF search_word IS NOT NULL THEN
    16		v_query:= v_query || ' and comp.stock_code LIKE '||v_search_word||CHAR(13);
    17		END IF;
    18		
    19		IF start_year IS NOT NULL AND start_quarter IS NOT NULL AND end_year IS NOT NULL AND end_quarter IS NOT NULL THEN
    20		v_query:= v_query ||' and f.year BETWEEN'|| start_year ||' AND '|| end_year ||' and f.quarter BETWEEN '|| start_quarter ||' AND '|| end_quarter || CHAR(13);
    21		END IF;
    22		
    23	v_query:= v_query || ' order by f.year desc, f.quarter desc ) tb1) tb2 '||CHAR(13);
    24	
    25	ELSIF tab = 'arena' THEN
    26	v_query:= v_query || ' select a.no "no", company "company", i.name "industry" , m.nickname "nickname", m.no "memberNo" ,title "title", a.stock_code "stockCode", a.regdate "regdate",
    27	(select count(p.no) from pick p where p.article_no = a.no and p.opinion='||''''||'Y'||''''||') "buyingCount",
    28	(select count(p.no) from pick p where p.article_no = a.no and p.opinion='||''''||'N'||''''||') "sellCount", 
    29	(select count(comm.no) from comments comm where comm.article_no = a.no) "commentsCount"
    30	from article a, company c, industry i, member m
    31	where a.stock_code = c.stock_code and m.no = a.member_no and i.no = c.industry_no '||CHAR(13);
    32	
    33		IF search_word IS NOT NULL THEN
    34		v_query:= v_query || ' and c.stock_code LIKE '||v_search_word||CHAR(13);
    35		END IF;
    36		
    37		IF sort_type = 'latest' THEN
    38		v_query := v_query || ' order by no DESC) tb1) tb2 ' || CHR(13);
    39		ELSIF sort_type = 'hot' THEN
    40		v_query := v_query || ' order by "buyingCount"+"sellCount"+"commentsCount" DESC, "no" DESC) tb1) tb2 ' || CHR(13);
    41		END IF;
    42	
    43	END IF;
    44	v_query := v_query || ' where tb2.r BETWEEN '|| start_page ||' AND ' || end_page;
    45	OPEN out_cursor FOR v_query;
    46	END;
    47	/
MacBook-Pro:playdata kimdabin$ vi testP.txt 

        END IF;

v_query:= v_query || ' order by f.year desc, f.quarter desc ) tb1) tb2 '||CHAR(13);

ELSIF tab = 'arena' THEN
v_query:= v_query || ' select a.no "no", company "company", i.name "industry" , m.nickname "nickname", m.no "memberNo" ,title "title", a.stock_code "stockCode", a.regdate "regdate",
(select count(p.no) from pick p where p.article_no = a.no and p.opinion='||''''||'Y'||''''||') "buyingCount",
(select count(p.no) from pick p where p.article_no = a.no and p.opinion='||''''||'N'||''''||') "sellCount",
(select count(comm.no) from comments comm where comm.article_no = a.no) "commentsCount"
from article a, company c, industry i, member m
where a.stock_code = c.stock_code and m.no = a.member_no and i.no = c.industry_no '||CHAR(13);

        IF search_word IS NOT NULL THEN
        v_query:= v_query || ' and c.stock_code LIKE '||v_search_word||CHAR(13);
        END IF;

        IF sort_type = 'latest' THEN
        v_query := v_query || ' order by no DESC) tb1) tb2 ' || CHR(13);
        ELSIF sort_type = 'hot' THEN
        v_query := v_query || ' order by "buyingCount"+"sellCount"+"commentsCount" DESC, "no" DESC) tb1) tb2 ' || CHR(13);
        END IF;

END IF;
v_query := v_query || ' where tb2.r BETWEEN '|| start_page ||' AND ' || end_page;
OPEN out_cursor FOR v_query;
END;
/

```



```sql
SQL> var con_cursor refcursor;
finance NULL 2020 1 2020 1 1 10 NULL
arena null 0 0 0 0 1 10 latest
arena null 0 0 0 0 1 10 latest
arena null 0 0 0 0 1 10 latest
finance 035720 2018 3 2018 2 1 10 latest
SQL> exec select_contents('finance', '035720' , 2018, 3, 2018, 2, 1, 10, 'latest' , :con_cursor);
exec select_contents('arena', NULL , 0, 0, 0, 0, 1, 10, 'latest' , :con_cursor);
SQL> print con_cursor
```



```sql
CREATE OR REPLACE PROCEDURE select_contents( tab in varchar2, search_word in varchar2 , start_year in number, start_quarter in number, end_year in number, end_quarter in number, start_page in number, end_page in number, sort_type in varchar2, con_cursor out SYS_REFCURSOR)

IS
v_query varchar2(4000);
v_count number;
v_search_word COMPANY.STOCK_CODE%TYPE := search_word;

BEGIN	
v_query := 'select tb2.* from( select rownum r, tb1.*, COUNT(*) OVER () '|| CHR(34)||'total'|| CHR(34)||' from ( '||CHR(13);

IF tab = 'finance' THEN
v_query := v_query || ' select i.name '|| CHR(34)||'name'|| CHR(34)||' , comp.company '|| CHR(34)||'company'|| CHR(34)||', comp.stock_listed '||CHR(34)||'stockListed'||CHR(34)||' ,  comp.capital '||CHR(34)||'capital'||CHR(34)||' ,  comp.facevalue '||CHR(34)||'facevalue'|| CHR(34)||' , comp.currency '||CHR(34)||'currency'||CHR(34)||' , f.no '|| CHR(34)||'no'|| CHR(34)||', f.year '|| CHR(34)||'year'|| CHR(34)||', f.quarter AS '|| CHR(34)||'quarter'|| CHR(34)||' , comp.stock_code AS '|| CHR(34)||'stockCode'|| CHR(34)||', f.account_code AS '|| CHR(34)||'accountCode'|| CHR(34)||', fc.account_nm '|| CHR(34)||'accountNm'|| CHR(34)||', fc.label_kor '|| CHR(34)||'labelKor'|| CHR(34)||', fc.sj_div '|| CHR(34)||'sjDiv'|| CHR(34)||', NVL(f.account_value,0) '|| CHR(34)||'accountValue'|| CHR(34)||CHR(13);

v_query := v_query ||' from finance f, finance_cate fc, company comp, industry i '|| CHR(13);
v_query := v_query || ' where comp.industry_no = i.no and comp.stock_code = f.stock_code and f.account_code = fc.account_id ' || CHR(13);
	IF search_word IS NOT NULL THEN
	v_query:= v_query || ' and comp.stock_code LIKE '||''''||v_search_word||''''|| CHR(13);
	END IF;
	
	IF start_year > 0 AND start_quarter > 0 AND end_year > 0 AND end_quarter > 0 THEN
	v_query:= v_query ||' AND f.year BETWEEN '||end_year||' AND '||start_year||' AND f.quarter BETWEEN '||end_quarter||' AND '||start_quarter|| CHR(13);
	END IF;
	
v_query:= v_query || ' order by f.year desc, f.quarter desc ) tb1) tb2 '||CHR(13);

ELSIF tab = 'arena' THEN
v_query:= v_query || ' select a.no '|| CHR(34)||'no'|| CHR(34)||' , c.company '|| CHR(34)||'company'||CHR(34)||' , i.name ' ||CHR(34)||'industry'||CHR(34)||', m.nickname '||CHR(34)||'nickname'||CHR(34)||' , m.no '|| CHR(34)||'memberNo'||CHR(34)||' , a.title '||CHR(34)||'title'||CHR(34)||' , a.stock_code '||CHR(34)||'stockCode'||CHR(34)||' , a.regdate '||CHR(34)||'regdate'||CHR(34)||' ,
 (select count(p.no) from pick p where p.article_no = a.no and p.opinion='||''''||'Y'||''''||') '||CHR(34)||'buyingCount'||CHR(34)||' ,
 (select count(p.no) from pick p where p.article_no = a.no and p.opinion='||''''||'N'||''''||') '||CHR(34)||'sellCount'||CHR(34)||' , 
(select count(comm.no) from comments comm where comm.article_no = a.no) '||CHR(34)||'commentsCount'||CHR(34)||' from article a, company c, industry i, member m 
where a.stock_code = c.stock_code and m.no = a.member_no and i.no = c.industry_no '||CHR(13);

	IF search_word IS NOT NULL THEN
	v_query:= v_query || ' and c.stock_code LIKE '||''''||v_search_word||''''||CHR(13);
	END IF;
	
	IF sort_type = 'latest' THEN
	v_query := v_query || ' order by a.no DESC) tb1) tb2 ' || CHR(13);
	
	ELSIF sort_type = 'hot' THEN
	v_query := v_query || ' order by '||CHR(34)||'buyingCount'||CHR(34)||' + '||CHR(34)||'sellCount'||CHR(34)||' + '||CHR(34)||'commentsCount'||CHR(34)||' DESC, '||CHR(34)||'no'||CHR(34)||' DESC) tb1) tb2 ' || CHR(13);
	END IF;

END IF;
v_query := v_query || ' where tb2.r BETWEEN '|| start_page ||' AND ' || end_page;
	OPEN con_cursor FOR v_query;
END;
/
```





---

```sql
CREATE OR REPLACE PROCEDURE select_contents( tab in varchar2, search_word in varchar2 , start_year in number, start_quarter in number, end_year in number, end_quarter in number, start_page in number, end_page in number, sort_type in varchar2, out_cursor out SYS_REFCURSOR)

IS
v_query varchar(4000);
v_search_word COMPANY.STOCK_CODE%TYPE := ''''||search_word||'''';

BEGIN
v_query := 'select tb2.* from( select rownum r, tb1.* from('||CHR(13);

IF tab = 'finance' THEN
v_query := v_query || ' select i.name AS '|| CHR(34)||'industryName'|| CHR(34)||' , comp.company AS '|| CHR(34)||'company'|| CHR(34)||', comp.stock_listed AS '|| CHR(34)||'stockListed'|| CHR(34)||', comp.capital AS '|| CHR(34)||'capital'|| CHR(34)||' ,  comp.facevalue AS '|| CHR(34)||'facevalue'|| CHR(34)||', comp.currency AS '|| CHR(34)||'currency'|| CHR(34)||' , f.no AS '|| CHR(34)||'financeNo'|| CHR(34)||', f.year AS '|| CHR(34)||'year'|| CHR(34)||', f.quarter AS '|| CHR(34)||'quarter'|| CHR(34)||' , comp.stock_code AS '|| CHR(34)||'stockCode'|| CHR(34)||', f.account_code AS '|| CHR(34)||'accountCode'|| CHR(34)||', fc.account_nm '|| CHR(34)||'accountName'|| CHR(34)||', fc.label_kor '|| CHR(34)||'labelKor'|| CHR(34)||', fc.sj_div '|| CHR(34)||'sjDiv'|| CHR(34)||', NVL(f.account_value,0) '|| CHR(34)||'accountValue'|| CHR(34)||'
 from finance f, finance_cate fc, company comp, industry i
where comp.industry_no=i.no and comp.stock_code = f.stock_code and f.account_code = fc.account_id ' || CHR(13);

	IF search_word IS NOT NULL THEN
	v_query:= v_query || ' and comp.stock_code LIKE '||v_search_word||CHR(13);
	END IF;
	
	IF start_year > 0 AND start_quarter > 0 AND end_year > 0 AND end_quarter > 0 THEN
	v_query:= v_query ||' and f.year BETWEEN'|| start_year ||' AND '|| end_year ||' and f.quarter BETWEEN '|| start_quarter ||' AND '|| end_quarter || CHR(13);
	END IF;
	
v_query:= v_query || ' order by f.year desc, f.quarter desc ) tb1) tb2 '||CHR(13);

END IF;

IF tab = 'arena' THEN
v_query:= v_query || ' select a.no "no", c.company '|| CHR(34)||'company'||CHR(34)||' , i.name ' ||CHR(34)||'industry'||CHR(34) ||', m.nickname '||CHR(34)||'nickname'||CHR(34)||' , m.no '|| CHR(34)||'memberNo'||CHR(34)||' , a.title '||CHR(34)||'title'||CHR(34)||' , a.stock_code '||CHR(34)||'stockCode'||CHR(34)||', a.regdate '||CHR(34)||'regdate'||CHR(34)||' ,
(select count(p.no) from pick p where p.article_no = a.no and p.opinion='||''''||'Y'||''''||') '||CHR(34)||'buyingCount'||CHR(34)||' ,
(select count(p.no) from pick p where p.article_no = a.no and p.opinion='||''''||'N'||''''||') '||CHR(34)||'sellCount'||CHR(34)||' , 
(select count(comm.no) from comments comm where comm.article_no = a.no) '||CHR(34)||' commentsCount'||CHR(34)||' from article a, company c, industry i, member m
where a.stock_code = c.stock_code and m.no = a.member_no and i.no = c.industry_no '||CHR(13);

	IF search_word IS NOT NULL THEN
	v_query:= v_query || ' and c.stock_code LIKE '||v_search_word||CHR(13);
	END IF;
	
	IF sort_type = 'latest' THEN
	v_query := v_query || ' order by no DESC) tb1) tb2 ' || CHR(13);
	ELSIF sort_type = 'hot' THEN
	v_query := v_query || ' order by '||CHR(34)||'buyingCount'||CHR(34)||' + '||CHR(34)||'sellCount'||CHR(34)||' + '||CHR(34)||'commentsCount'||CHR(34)||' DESC, '||CHR(34)||'no'||CHR(34)||' DESC) tb1) tb2 ' || CHR(13);
	END IF;

END IF;
v_query := v_query || ' where tb2.r BETWEEN '|| start_page ||' AND ' || end_page;

	OPEN out_cursor FOR v_query;

END;
/
```



```sql
CREATE OR REPLACE PROCEDURE select_contents( tab in varchar2, search_word in varchar2 , start_year in number, start_quarter in number, end_year in number, end_quarter in number, start_page in number, end_page in number, sort_type in varchar2, out_cursor out SYS_REFCURSOR)

IS
v_query varchar(1000);
v_search_word COMPANY.STOCK_CODE%TYPE := ''''||search_word||'''';

BEGIN
v_query := 'select tb2.* from( select rownum r, tb1.* from('||CHR(13);

IF tab = 'arena' THEN
v_query:= v_query || ' select a.no "no", c.company '|| CHR(34)||'company'||CHR(34)||' , i.name ' ||CHR(34)||'industry'||CHR(34) ||', m.nickname '||CHR(34)||'nickname'||CHR(34)||' , m.no '|| CHR(34)||'memberNo'||CHR(34)||' , a.title '||CHR(34)||'title'||CHR(34)||' , a.stock_code '||CHR(34)||'stockCode'||CHR(34)||', a.regdate '||CHR(34)||'regdate'||CHR(34)||' ,
(select count(p.no) from pick p where p.article_no = a.no and p.opinion='||''''||'Y'||''''||') '||CHR(34)||'buyingCount'||CHR(34)||' ,
(select count(p.no) from pick p where p.article_no = a.no and p.opinion='||''''||'N'||''''||') '||CHR(34)||'sellCount'||CHR(34)||' , 
(select count(comm.no) from comments comm where comm.article_no = a.no) '||CHR(34)||' commentsCount'||CHR(34)||' from article a, company c, industry i, member m
where a.stock_code = c.stock_code and m.no = a.member_no and i.no = c.industry_no '||CHR(13);

	IF search_word IS NOT NULL THEN
	v_query:= v_query || ' and c.stock_code LIKE '||v_search_word||CHR(13);
	END IF;
	
	IF sort_type = 'latest' THEN
	v_query := v_query || ' order by no DESC) tb1) tb2 ' || CHR(13);
	ELSIF sort_type = 'hot' THEN
	v_query := v_query || ' order by '||CHR(34)||'buyingCount'||CHR(34)||' + '||CHR(34)||'sellCount'||CHR(34)||' + '||CHR(34)||'commentsCount'||CHR(34)||' DESC, '||CHR(34)||'no'||CHR(34)||' DESC) tb1) tb2 ' || CHR(13);
	END IF;

END IF;
v_query := v_query || ' where tb2.r BETWEEN '|| start_page ||' AND ' || end_page;

	OPEN out_cursor FOR v_query;

END;
/
```





---

```sql
select tb2.*
from(
select rownum r, tb1.*
from(
select f.no financeNO, f.year financeYEAR, f.quarter financeQuarter, f.stock_code financeStockCode, f.account_code financeAccountCode, fc.account_nm financeAccountName, fc.label_kor financeCateLabelKor, fc.sj_div financeCateSjDiv, f.account_value financeAccountValue, f.regdate financeRegdate, comp.*
from finance f, (select c.stock_code companyStockCode, c.company company, c.industry_no companyIndustryNo, i.name industryName, c.stock_listed companyStockListed, c.capital companyCapital, c.facevalue companyFacevalue, c.currency companyCurrency, c.tel companyTel, 
c.address companyAddress, c.regdate companyRegdate
from company c, industry i 
where c.industry_no = i.no) comp, finance_cate fc
where comp.companyStockCode = f.stock_code and f.account_code = fc.account_id
  and company LIKE '삼성'
order by f.year desc, f.quarter desc) tb1) tb2
where tb2.r BETWEEN 1 AND 10;
```





https://calendar.google.com/calendar/embed?src=g6emd2bta95icnqn94mhkkml1k%40group.calendar.google.com&ctz=Asia%2FSeoul





```tex
https://www.googleapis.com/calendar/v3/calendars/${calendarID}/events?orderBy=startTime&singleEvents=true&timeMax=${maxTime}&timeMin=${minTime}&key=${googleKey}

출처: https://solbel.tistory.com/509 [개발자의 끄적끄적]

g6emd2bta95icnqn94mhkkml1k
AIzaSyC4wo9XL58Z0UsmYTHQbzYyXJwBczVKTB4

https://www.googleapis.com/calendar/v3/calendars/g6emd2bta95icnqn94mhkkml1k/events?orderBy=startTime&singleEvents=true&timeMax=${maxTime}&timeMin=${minTime}&key=AIzaSyC4wo9XL58Z0UsmYTHQbzYyXJwBczVKTB4
```

```sql
select tb2.*
from(
select rownum r, tb1.*
from(
select i.name "industryName", comp.company "company",comp.stock_listed "stockListed",comp.capital "capital", comp.facevalue "facevalue", comp.currency "currency" ,f.no "financeNo", f.year "year", f.quarter "quarter", f.stock_code "stockCode", f.account_code "accountCode", fc.account_nm "accountName", fc.label_kor "labelKor", fc.sj_div "sjDiv", NVL(f.account_value,0) "accountValue"
from finance f, finance_cate fc, company comp, industry i
where comp.industry_no=i.no and comp.stock_code = f.stock_code and f.account_code = fc.account_id
  and comp.company LIKE '%삼성%'
order by f.year desc, f.quarter desc) tb1) tb2
where tb2.r BETWEEN 1 AND 10;
```





```js
<select id="startQuarter" form="searchForm" name="startQuarter">
<@ _.each(terms, function(term){   @>
<@ if(){ @>  <@ } @>
<option value="<@=quarter @>"><@=quarter @></option>
<@ })@>
</select> <span> 분기 </span> 
<span style="margin: 0 5px;"><i class="fas fa-money-bill-wave"></i></span> 

<select id="endYear" form="searchForm" name="endYear">
<@ _.each(years, function(year){   @>
	<option value="<@=year @>"><@=year @></option>
<@ })@>
</select> <span> 년 </span> 
<select id="endQuarter" form="searchForm" name="endQuarter">
<@ _.each(quarters, function(quarter){   @>
<option value="<@=quarter @>"><@=quarter @></option>
<@ })@>
</select> <span> 분기 </span>
```

org.springframework.jdbc.core.simple.SimpleJdbcCall@76841f52