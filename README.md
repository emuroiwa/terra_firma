<h4>Installation</h4>
Restore the MySQL db named terra_firma<br>
Clone the repo and run grails run-app
<hr>
<h4>A bit of explanation</h4>
Question 1

Point 2.
There were Foreign Key relationships on table meter_daily_summary.meter_id and meter_data.id referencing meter.meter_id.
However constraint was not possible to add on existing data because data was not match i.e meter 4222 did not exist in meter table

Question 3
Create a grails 3 project using the above database with basic CRUD functionality for each of the 4 tables.
There are 3 tables
