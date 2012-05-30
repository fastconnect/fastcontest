<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Register</title>
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
            
			<c:if test="${errorMessage != null}"><div data-role="content">
				Sorry, we have encountered the following error: ${errorMessage}
			</div></c:if>

			<form action="confirm_registration" method="get">
				<div>
					Email: <input type="email" required="required" name="email" />
				</div>
				
				<input type="submit" value="Register!" />
			</form>
		</div>
		<!-- /content -->
		
		<p>
			<a href="home">Return to the home page.</a>
        </p>
        
	</div>
	<!-- /page -->

	
</body>
</html>
