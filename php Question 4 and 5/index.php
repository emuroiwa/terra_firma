<?php
	require('open.php');

    $sql="SELECT
            m.Meter_name,
            HOUR(md.t_stamp) meterHour,
            SUM(md.kwh) totalKWP,
            SUM(md.cost) totalCost,
            MAX(md.kwh) maxKWP,
            MIN(md.kwh) minKWP
        FROM
            `meter` AS m
        INNER JOIN meter_data AS md ON m.meter_id = md.meter_id
        GROUP BY md.meter_id,HOUR(md.t_stamp);";

	$qry = mysqli_query($mysqli,$sql);
    $results = mysqli_fetch_all($qry,MYSQLI_ASSOC);
    $json_encode(array_column($click, 'count'),JSON_NUMERIC_CHECK);
    $json = json_encode($results,JSON_NUMERIC_CHECK);
   
  


	// /* Getting demo_click table data */
	// $sql = "SELECT SUM(numberofclick) as count FROM demo_click 
	// 		GROUP BY YEAR(created_at) ORDER BY created_at";
	// $click = mysqli_query($mysqli,$sql);
	// $click = mysqli_fetch_all($click,MYSQLI_ASSOC);
	// $click = json_encode(array_column($click, 'count'),JSON_NUMERIC_CHECK);


?>


<!DOCTYPE html>
<html>
<head>
	<title>HighChart</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
	<script src="https://code.highcharts.com/highcharts.js"></script>
</head>
<body>


<script type="text/javascript">


$(function () { 


    var data_viewer1 = <?php echo $viewer1; ?>;
    var data_viewer = <?php echo $viewer; ?>;
    var col = <?php echo $col; ?>;

console.log(data_viewer1)
    $('#container').highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: 'Yearly Website Ratio'
        },
        xAxis: {
            categories: col
        },
        yAxis: {
            title: {
                text: 'Rate'
            }
        },
        series: [{
            name: 'View',
            data: data_viewer
        }]
    });
});


</script>


<div class="container">
	<br/>
	<h2 class="text-center">Highcharts php mysql json example</h2>
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default">
                <div class="panel-heading">Dashboard</div>
                <div class="panel-body">
                    <div id="container"></div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>