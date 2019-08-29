SELECT
	m.Meter_name,DATE(md.t_stamp),SUM(md.kwh),MAX(md.kwh)
FROM
	`meter` AS m
INNER JOIN meter_data AS md ON m.meter_id = md.meter_id
GROUP BY md.meter_id,DATE(md.t_stamp);