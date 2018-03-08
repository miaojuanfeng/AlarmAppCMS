<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Dashboard</title>

		<%@ include file="inc/MetaArea.jsp" %>

		<script>
		$(function(){

			/* chart1 */
			var chart1 = echarts.init(document.getElementById('chart1'));
			var option = {
				title: {
					text: ''
				},
				tooltip: {},
				legend: {
					data: ['下載量1','下載量2']
				},
				xAxis: {
					data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一', '十二']
				},
				yAxis: {},
				series: [
					{
						name: '下載量1',
						type: 'bar',
						itemStyle: {normal: {color:'rgba(88,151,251,0.7)'}},
						data: [500, 2000, 3600, 1000, 1000, 2000, 500, 2000, 3600, 1000, 1000, 2000]
					},
					{
						name: '下載量2',
						type: 'bar',
						itemStyle: {normal: {color:'rgba(00,151,251,0.7)'}},
						data: [2500, 1800, 1100, 1500, 3000, 500, 500, 2500, 3300, 800, 2000, 3000]
					}
				]
			};
			chart1.setOption(option);

			/* chart2 */
			var chart2 = echarts.init(document.getElementById('chart2'));
			option = {
				title : {
					text: '訪問來源',
					subtext: '純屬虛構',
					x:'center'
				},
				tooltip : {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				legend: {
					orient: 'vertical',
					left: 'left',
					data: ['直接訪問','郵件營銷','聯盟廣告','視頻廣告','搜尋引擎']
				},
				series : [{
					name: '訪問來源',
					type: 'pie',
					radius : '55%',
					center: ['50%', '60%'],
					data:[
						{value:335, name:'直接訪問'},
						{value:310, name:'郵件營銷'},
						{value:234, name:'聯盟廣告'},
						{value:135, name:'視頻廣告'},
						{value:1548, name:'搜尋引擎'}
					],
					itemStyle: {
						emphasis: {
							shadowBlur: 10,
							shadowOffsetX: 0,
							shadowColor: 'rgba(0, 0, 0, 0.5)'
						}
					}
				}]
			};
			chart2.setOption(option);

			/* resize */
			$(window).on('resize', function(){
				chart1.resize();
				chart2.resize();
			});
		});
		</script>
	</head>

	<body>

		<%@ include file="inc/HeaderArea.jsp" %>

	

		




		<div class="content-area">

			<div class="container-fluid">
				<div class="row">

					<h2 class="col-sm-12">Dashboard</h2>

					<div class="content-column-area col-md-12 col-sm-12">
						<div class="fieldset">

							<div class="row">
								<div class="col-md-3 col-sm-6 bottom-buffer-10">
									<div class="dashboard-box-top">
										<i class="glyphicon glyphicon-stats pull-left"></i>
										<span class="pull-right">
											<div class="summary text-right">2,000,000</div>
											<div class="text-right">Booking total</div>
										</span>
										<div class="clearfix"></div>
									</div>
									<div class="dashboard-box-bottom">
										<span class="pull-left">VIEW MORE</span>
										<span class="pull-right"><i class="glyphicon glyphicon-chevron-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</div>
								<div class="col-md-3 col-sm-6 bottom-buffer-10">
									<div class="dashboard-box-top">
										<i class="glyphicon glyphicon-stats pull-left"></i>
										<span class="pull-right">
											<div class="summary text-right">125.3</div>
											<div class="text-right">Booking avg / per day</div>
										</span>
										<div class="clearfix"></div>
									</div>
									<div  class="dashboard-box-bottom">
										<span class="pull-left">VIEW MORE</span>
										<span class="pull-right"><i class="glyphicon glyphicon-chevron-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</div>
								<div class="col-md-3 col-sm-6 bottom-buffer-10">
									<div class="dashboard-box-top">
										<i class="glyphicon glyphicon-stats pull-left"></i>
										<span class="pull-right">
											<div class="summary text-right">100</div>
											<div class="text-right">Number of client / per day</div>
										</span>
										<div class="clearfix"></div>
									</div>
									<div class="dashboard-box-bottom">
										<span class="pull-left">VIEW MORE</span>
										<span class="pull-right"><i class="glyphicon glyphicon-chevron-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</div>
								<div class="col-md-3 col-sm-6 bottom-buffer-10">
									<div class="dashboard-box-top">
										<i class="glyphicon glyphicon-stats pull-left"></i>
										<span class="pull-right">
											<div class="summary text-right">80,000</div>
											<div class="text-right">Income / per day</div>
										</span>
										<div class="clearfix"></div>
									</div>
									<div class="dashboard-box-bottom">
										<span class="pull-left">VIEW MORE</span>
										<span class="pull-right"><i class="glyphicon glyphicon-chevron-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>

							<div class="list-area no-overflow-x">
								<div class="row">
									<div class="col-md-5 col-sm-12" style="margin-bottom:10px;">
										<h4 class="corpcolor-font"><i class="glyphicon glyphicon-bullhorn"> Chart</i></h4>
										<!-- <div id="visualization" class="chart-area"></div> -->
										<div id="chart1" class="chart-area"></div>
									</div>
									<div class="col-md-7 col-sm-12" style="margin-bottom:10px;">
										<h4 class="corpcolor-font"><i class="glyphicon glyphicon-bullhorn"> Notification</i></h4>
										<table class="table table-striped table-bordered">
											<thead>
												<tr>
													<th>Customer name</th>
													<th>Quotation #</th>
													<th>Sales order #</th>
													<th>Purchase order #</th>
													<th>Invoice #</th>
													<th>Delivery note #</th>
												</tr>
											</thead>
											<tbody>
												<?php for($i=0; $i<5; $i++){ ?>
												<tr>
													<td>Eddie Chu</td>
													<td><a href="#">QO17060001</a></td>
													<td><a href="#">SO17060001</a></td>
													<td><a href="#">PO17060001</a></td>
													<td><a href="#">IN17060001</a></td>
													<td><a href="#">DN17060001</a></td>
												</tr>
												<?php } ?>
											</tbody>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-md-5 col-sm-12" style="margin-bottom:10px;">
										<h4 class="corpcolor-font"><i class="glyphicon glyphicon-bullhorn"> Chart</i></h4>
										<!-- <div id="visualization2" class="chart-area"></div> -->
										<div id="chart2" class="chart-area"></div>
									</div>
									<div class="col-md-7 col-sm-12" style="margin-bottom:10px;">
										<h4 class="corpcolor-font"><i class="glyphicon glyphicon-bullhorn"> Notification</i></h4>
										<table class="table table-striped table-bordered">
											<thead>
												<tr>
													<th>Customer name</th>
													<th>Quotation #</th>
													<th>Sales order #</th>
													<th>Purchase order #</th>
													<th>Invoice #</th>
													<th>Delivery note #</th>
												</tr>
											</thead>
											<tbody>
												<?php for($i=0; $i<5; $i++){ ?>
												<tr>
													<td>Eddie Chu</td>
													<td><a href="#">QO17060001</a></td>
													<td><a href="#">SO17060001</a></td>
													<td><a href="#">PO17060001</a></td>
													<td><a href="#">IN17060001</a></td>
													<td><a href="#">DN17060001</a></td>
												</tr>
												<?php } ?>
											</tbody>
										</table>
									</div>
								</div>
							</div> <!-- list-area -->	

						</div>
					</div>
				</div>
			</div>

		</div>

		






		<%@ include file="inc/FooterArea.jsp" %>

	</body>
</html>