SELECT
	m.Meter_name,HOUR(md.t_stamp),SUM(md.kwh),SUM(md.cost)
FROM
	`meter` AS m
INNER JOIN meter_data AS md ON m.meter_id = md.meter_id
GROUP BY md.meter_id,HOUR(md.t_stamp);