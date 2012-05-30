<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Admin</title>
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
	<div data-role="page">

		<div data-role="header">
			<h1>Fastcontest - Admin</h1>
		</div>
		<!-- /header -->

		<div data-hole="content">
			<h3>Current contest: ${currentContestId}</h3>
			<h3>Total contests: ${contestCount}</h3>
			<h3>Registered users: ${userCount}</h3>
			<h3>Total participations: ${participationCount}</h3>

			<HR />			
			
			<form action="admin" method="get">
				<input type="hidden" name="generate_contest" value="yes" />
				<input type="submit"
					<c:if test="${contestCount!=0}">disabled="disabled"</c:if>
				value="Generate contests!"/>
			</form>
			
			<form action="admin" method="post">
				<input type="hidden" name="clear_contest" value="yes" />
				<input type="submit"
					<c:if test="${contestCount==0}">disabled="disabled"</c:if>
				value="Clear contests!"/>
			</form>
			
			<form action="admin" method="post">
				<input type="radio" required="required" name = "contestId" id="contest0" value="0" <c:if test="${currentContestId==0}">checked="checked"</c:if> />
				<label for="contest0">No contest</label>
				<input type="radio" required="required" name = "contestId" id="contest1" value="1" <c:if test="${currentContestId==1}">checked="checked"</c:if> />
				<label for="contest1">Contest 1</label>
				<input type="radio" required="required" name = "contestId" id="contest2" value="2" <c:if test="${currentContestId==2}">checked="checked"</c:if> />
				<label for="contest2">Contest 2</label>
				
				<input type="submit" value="Choose current contest!" />
			</form>
			
			<form action="results" method="get">
				<input type="submit" value="Show results!"/>
			</form>
			
			<div>
				<c:forEach items="${contests}" var="contest" varStatus="contestStatus">
					<HR />
					<h2>Contest ID: ${contest.contestId}</h2>
					
					<c:forEach items="${contest.questions}" var="question" varStatus="questionStatus">
						<h3>${question.question}</h3>
						<ul>
							<c:forEach items="${question.answers}" var="answer" varStatus="answerStatus">
								<li>${answer}</li>
							</c:forEach>
						</ul>
					</c:forEach>
					
				</c:forEach>
			</div>
			
		</div>
		<!-- /content -->

	</div>
	<!-- /page -->

</body>
</html>
