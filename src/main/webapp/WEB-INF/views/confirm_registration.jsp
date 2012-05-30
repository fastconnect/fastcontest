<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Confirm Registration</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.0.1/jquery.mobile-1.0.1.min.css" />
	<script type="text/javascript"
        src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.5.3/modernizr.min.js"></script>
    <script type="text/javascript"
        src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
    <script type="text/javascript"
        src="http://code.jquery.com/mobile/1.0.1/jquery.mobile-1.0.1.min.js"></script>
</head>
<body>
	<div data-role="page" id="game">

		<div data-role="header">
			<h1>Fastcontest</h1>
		</div>
		<!-- /header -->

		<div data-role="content">
            <h2>A mail has been sent to your email address: ${user.email}, please check your password.</h2>
            <h2>You can replay the contest of the current day until 17:30.</h2>
            <h3 style="color:#880016">Pay attention: This mail maybe identified as spam by several mail servers.</h3>

			<a href="login">Go login!</a>
		</div>
		<!-- /content -->

	</div>
	<!-- /page -->
	
</body>
</html>
