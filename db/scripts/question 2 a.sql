SELECT
	*
FROM
	`meter` AS m
INNER JOIN meter_data AS md ON m.meter_id = md.meter_id;