var topNav='';
topNav+=' <section>';
topNav+='<nav class="mainTabCtrl clearfix">';
topNav+='	<ul>';
topNav+='		<li id="Policy_Property_Search"><a href="./policyProp"><spring:message code="label.ftdpropertysearch"/></a><span><spring:message code="label.ftdpropertysearch"/></span></li>';
topNav+='		<li id="Policy_Auto_Search"><a href="./policyAuto"><spring:message code="label.ftdpolicysearch"/></a><span><spring:message code="label.ftdpolicysearch"/></span></li>';
topNav+='		<li id="Policy_Search"><a href="./policySearch"><spring:message code="label.ftdlifepolicysearch"/></a><span><spring:message code="label.ftdlifepolicysearch"/></span></li>';
topNav+='		<li id="Claim_Search"><a href="./claimSearch"><spring:message code="label.ftdlifeclaimsearch"/></a><span><spring:message code="label.ftdlifeclaimsearch"/></span></li>';
topNav+='	</ul>';
topNav+='</nav>';
topNav+='   </section>';
document.write(topNav);