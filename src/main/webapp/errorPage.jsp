<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Sorry! Something went wrong</title>
<link rel="icon" href="images/errorFavicon.png" type="image/x-icon">


<style>
@import
	url('https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&family=Roboto&display=swap');

* {
	margin: 0px;
	padding: 0px;
	box-sizing: border-box;
}

body {
	background-color: #B0D4F4;
	height: 100vh;
	width: 100vw;
	display: flex;
	justify-content: center;
	align-items: center;
	font-family: 'Poppins';
}

main {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

img {
	height: 400px;
}

h1 {
	margin-top: 30px;
	font-size: 23px;
}

button {
	font-weight: bold;
	margin-top: 20px;
	font-size: 17px;
	height: 35px;
	width: 150px;
	border: 1px solid black;
	border-radius: 5px;
	outline: none;
	background-color: #6099ca;
    cursor: pointer;
    transition: 0.2s;
}
button:hover {
	background-color: #1d609b;
}
</style>
</head>
<body>
	<main>
		<img src="images/404error.svg" alt="ErrorPage">
		<h1>Sorry!!! Something went wrong</h1>
		
<!-- 		shows the exception type -->
		<%= exception %>
		<div>
			<a href="index.jsp"><button>Home</button></a>
			<a href="registerPage.jsp"><button>Register</button></a>
		</div>
	</main>
</body>

</html>