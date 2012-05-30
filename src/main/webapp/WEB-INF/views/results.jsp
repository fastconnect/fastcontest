<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"	href="http://code.jquery.com/mobile/1.0.1/jquery.mobile-1.0.1.min.css" />
	<script type="text/javascript"
        src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.5.3/modernizr.min.js"></script>
    <script type="text/javascript"
        src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
    <script type="text/javascript"
        src="http://code.jquery.com/mobile/1.0.1/jquery.mobile-1.0.1.min.js"></script>
    <link rel="stylesheet"    type="text/css" href="tableStyle.css" />
        
</head>
<body>
	<div data-role="page">

		<div data-role="header">
			<h1>Fastcontest - Final Results (TOP 10)</h1>
		</div>
		<!-- /header -->
		
	<!-- /content -->
	
	
	<h3>Voici donc la liste des meilleurs participants aux diff&eacute;rents concours FastConnect.</h3>
	<h4>Au total ${size} participant(s).</h4>
		
		<div data-role="content" style="margin-left:20px">
			<table style="border: 1px solid black; border-collapse:collapse;">
				<tr>
					<th style="background-color: 98CEF1; color:white;border: 1px solid black; height:50px;padding-left: 10px; padding-right: 10px;">Prénom</th>
					<th style="background-color: 98CEF1; color:white;border: 1px solid black; height:50px;padding-left: 10px; padding-right: 10px;">Nom</th>
					<th style="background-color: 98CEF1; color:white;border: 1px solid black; height:50px;padding-left: 10px; padding-right: 10px;">N° Concours</th>			
					<th style="background-color: 98CEF1; color:white;border: 1px solid black; height:50px;padding-left: 10px; padding-right: 10px;">email</th>			
					<th style="background-color: 51AEEA; color:white;border: 1px solid black; height:50px;padding-left: 10px; padding-right: 10px;">Score</th>
					<th style="background-color: 98CEF1; color:white;border: 1px solid black; height:50px;padding-left: 10px; padding-right: 10px;">LuckPoint</th>
				</tr>
							
				<c:forEach items="${contestParticipations}" var="contestParticipation">
					<tr>
						<td style="border:1px solid black; text-align:left; vertical-align:bottom; padding:15px;">${contestParticipation.firstName}</td>
						<td style="border:1px solid black; text-align:left; vertical-align:bottom; padding:15px;">${contestParticipation.lastName}</td>
						<td style="border:1px solid black; text-align:center; vertical-align:bottom; padding:15px;">${contestParticipation.contestId}</td>
						<td style="border:1px solid black; text-align:center; vertical-align:bottom; padding:15px;">${contestParticipation.email}</td>
						<td style="border:1px solid black; text-align:center; vertical-align:bottom; padding:15px;">${contestParticipation.score}</td>
						<td style="border:1px solid black; text-align:center; vertical-align:bottom; padding:15px;">${contestParticipation.luckyPoint}</td>
						
					</tr>
				</c:forEach>
			</table>
					
			<form action="admin" method="post">
				<input type="submit" value="Return to the admin page."/>
			</form>
											
		</div>
		
	</div>
	
	<!-- /page -->

</body>
</html>
