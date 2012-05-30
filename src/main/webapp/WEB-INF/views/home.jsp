<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Home</title>
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
			<h1>Welcome to FastContest!</h1>
			<p>
				<a href="#cgu">Rules of the game</a>
			</p>
			<div style="text-align:center"><img src="resources/fc-logo.png" alt="" /></div>
            
			<form action="register" method="get">
				<input type="submit" value="New player!" />
			</form>
			
			<form action="login" method="get">
				<input type="submit" value="Login!" />
			</form>
		</div>
		<!-- /content -->

	</div>
	<!-- /page -->

	<div data-role="page" id="cgu">

		<div data-role="header">
			<h1>Rules of the game</h1>
		</div>
		<!-- /header -->

		<div data-role="content">
			<p>
				<a href="#game" data-direction="reverse">Back to the game</a>
			</p>
			<p>
				<a href="http://devoxxfrquizz.fastconnect.fr">R�glement en fran&ccedil;ais</a>
			</p>
			<p>
FastConnect organise les Mercredi 18 Avril et Vendredi 20 Avril une animation par jour, sous forme d'un quizz de 10 questions, ouvert � tous les devoxxiens, de 8h30 � 17h30.
Une liseuse �lectronique Kindle d'Amazon est � gagner � travers un tirage au sort parmi les meilleures r�ponses.
Les participants au jeu s'enregistrent � l'URL devoxxquizz.fastconnect.fr et peuvent enregistrer leurs r�ponses pendant toute la dur�e du jeu.
			</p>
			<p>
Chaque devoxxien ne peut jouer qu'une seule fois en utilisant une seule adresse e-mail.
			</p>
			<p>
A l'issue de la p�riode du jeu, un tirage au sort est effectu� parmi les meilleures r�ponses.
Ce tirage au sort se tiendra � 18h le Mercredi 18 Avril et le Vendredi 20 Avril.
Il permettra de d�terminer quel sera le grand gagnant du Kindle.
Des lots de consolation seront offerts aux 2�me et 3�me place.
			</p>
			<p>
Le gagnant du Kindle se verra demander sa carte d'identit� afin de v�rifier qu'il est effectivement le gagnant.
			</p>
			<h2>D�roulement du Quizz&nbsp;:</h2>
			<p>
1�) <b>Enregistrement et obtention d'un mot de passe pour participer au quizz</b><br/>
Les devoxxiens d�sirant participer au quizz FastContest s'enregistrent en ligne.
Ils vont alors recevoir un mot de passe par mail qui permet d'acc�der � sa session, et de saisir ou modifier les r�ponses pendant toute la dur�e du quizz.
			</p>
			<p>
2�) <b>Participation au quizz</b><br/>
Le quizz est ouvert au jeu toute la journ�e de 8h30 � 17h30.
Les participants peuvent entrer, modifier leurs r�ponses tout au long de la journ�e.
			</p>
			<p>
&Agrave; partir de la fin de la p�riode d'ouverture du jeu, c'est � dire � partir de 17h30, il n'est plus possible de modifier ses r�ponses.
			</p>
			<p>
3�) <b>Tirage au sort</b><br/>
Apr�s que l'ensemble des devoxxiens d�sirant participer aient tent� leur chance en r�pondant aux questions du quizz de 8h30 � 17h30.
Un tirage au sort parmi les meilleures r�ponses se tiendra � 18h, et permettra de d�terminer chaque jour (Mercredi et Vendredi) le gagnant du Kindle.
Des lots de consolation seront offerts aux 2�me et 3�me places.
			</p>
			<p>
				<a href="#game" data-direction="reverse">Back to the game</a>
			</p>
		</div>
	</div>
	<!-- /page -->

</body>
</html>
