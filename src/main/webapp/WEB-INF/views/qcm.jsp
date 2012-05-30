<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>QCM</title>
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
	<div data-role="page" id="gameQcm">

		<div data-role="header">
			<h1>Fastcontest</h1>
		</div>
		<!-- /header -->

		<div data-role="content">
            
			<c:if test="${errorMessage != null}"><div data-role="content">
				Sorry, we have encountered the following error: ${errorMessage}
			</div></c:if>

			<c:if test="${contest != null}"><form action="submission" method="post">
				
				<div>
					<input type="hidden" name="email" value="${user.email}" />
					<input type="hidden" name="contestId" value="${contest.contestId}" />
					<c:if test="${participation!=null}"> 
						<input type="hidden" name="participationId" value="${participation.participationId}" />
					</c:if>
				</div>
				
				
				<div>
					First name: <input type="text" required="required" name="firstname" value="${participation.firstName}"/>
				</div>
				<div>
					Last name: <input type="text" required="required" name="lastname" value="${participation.lastName}" />
				</div>
				<div>
					Birthday (dd-MM-yyyy): <input type="date" pattern="[-0-9]+" required="required" name="birthday" value="${participation.birthday}"/>
				</div>

				<HR /><BR />

				<% int qnum = 1; %>
				<c:forEach items="${contest.questions}" var="question" varStatus="questionStatus">
					<div>
						<%=qnum%>. ${question.question}
						<input type="hidden"
							value="${questionStatus.index}" />
					</div>

					<c:if test="${question.questionType == 'Choice'}">
						<div data-role="fieldcontain">
							<fieldset data-role="controlgroup">
								<c:forEach items="${question.answers}" var="answer" varStatus="status">
									<input type="radio" required="required" name="answer-${questionStatus.index}"
										id="answer-${questionStatus.index}-${status.index}"
										value="${answer}"
										<c:choose>
											<c:when test = "${(participation!=null) and participation.answers[questionStatus.index].answer==answer}">checked="checked"</c:when>
											<c:otherwise></c:otherwise>
										</c:choose>
									/>
									<label for="answer-${questionStatus.index}-${status.index}">${answer}</label>
								</c:forEach>
							</fieldset>
						</div>
					</c:if>

					<c:if test="${question.questionType == 'Text'}">
						<div data-role="fieldcontain">
							<input type="text" name="answer-${questionStatus.index}"
								id="answer-${questionStatus.index}" value="" />
						</div>
					</c:if>

					<c:if test="${question.questionType == 'Email'}">
						<div data-role="fieldcontain">
							<input type="email" name="answer-${questionStatus.index}"
								id="answer-${questionStatus.index}" value="" />
						</div>
					</c:if>

					<% qnum += 1; %>
				</c:forEach>

				<input type="submit" value="Done!" />
			</form></c:if>
		</div>
		<!-- /content -->

	</div>
	<!-- /page -->

	<div data-role="page" id="cguQcm">

		<div data-role="header">
			<h1>Règles du jeux</h1>
		</div>
		<!-- /header -->
	</div>
	<!-- /page -->

</body>
</html>
