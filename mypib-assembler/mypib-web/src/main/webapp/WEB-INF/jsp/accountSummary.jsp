<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript"
	src="/mypib-web/resources/js/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
</script>
</head>

<body>
	<button name="MyButton" onclick="getBalance()">Click Here To Check Your Account Balance - Ajax</button> <br/><br/><br/>

	<span id="balance"></span>

	<script type="text/javascript">

		function getBalance() {
			jq(function() {
				// Call a URL and pass two arguments
				// Also pass a call back function
				// See http://api.jquery.com/jQuery.post/
				// See http://api.jquery.com/jQuery.ajax/
				// You might find a warning in Firefox: Warning: Unexpected token in attribute selector: '!' 
				// See http://bugs.jquery.com/ticket/7535
				jq.post("/mypib-web/accountSummary.htm", function(data) {
					// data contains the result
					// Assign result to the balance id
					jq("#balance").replaceWith(
							'Your account balace is INR : <span id="balance"><b>'
									+ data + '</b></span>');
				});
			});
		}
	</script>
</body>
</html>