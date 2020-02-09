<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="abc">
	<div id="popupContact" class="popupdiv">
		<form action="#" id="popform" method="post" name="popform">
		<img id="close" src="images/3.png" onclick ="div_hide()">
		<h2><spring:message code="label.email.l1l2"/></h2>
		<hr>
			<input id="email" name="email"  type="text" class="email">
			<input id="name" name="name"  type="text" class="email">
			
			<textarea id="msg" name="message" ></textarea>
			<a href="javascript:%20check_empty()" id="submit" title="<spring:message code="label.smtp.error"/>"><spring:message code="label.send"/></a>
		</form>
	</div>
</div>