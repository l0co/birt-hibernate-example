BIRT + HIBERNATE example
======================

Example application of BIRT usage with Hibernate data sources (using POJO data source), based on
[clibernate](https://github.com/l0co/clibernate) project.

More information and approach description is here:
http://lifeinide.blogspot.com/2014/12/birt-with-hibernate-using-pojo-s.html.

## BIRT report

Birt report workspace is in `/eclipse` directory. Start BIRT 4.4.1 designer (Luna) and select this directory
as the workspace to start playing around the report.

## Building and testing

1. `mvn package` builds everything with libraries and the `/eclipse/report/company.rptdesign` built-in.
2. `java -jar birtexample-1.0-SNAPSHOT.jar` displays general help.
3. `java -jar birtexample-1.0-SNAPSHOT.jar birtExample` displays BIRT service help.

## Example

1. `java -jar birtexample-1.0-SNAPSHOT.jar -a 10 birtExample` adds 10 random companies to DB.
2. `java -jar birtexample-1.0-SNAPSHOT.jar -l birtExample` lists objects from database.
3. `java -jar birtexample-1.0-SNAPSHOT.jar -r companies.pdf birtExample` renders the report from template using
your data from the database.
