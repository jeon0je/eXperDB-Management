<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="../../cmmn/commonLocale.jsp"%>
<%
	/**
	* @Class Name : keyManageRegReForm.jsp
	* @Description : keyManageRegReForm 화면
	* @Modification Information
	*
	*   수정일         수정자                   수정내용
	*  ------------    -----------    ---------------------------
	*  2018.01.08     최초 생성
	*
	* author 변승우 대리
	* since 2018.01.08
	*
	*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>암복호화 키 수정</title>

<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<link rel = "stylesheet" type = "text/css" media = "screen" href = "<c:url value='/css/dt/jquery.dataTables.min.css'/>"/>
<link rel = "stylesheet" type = "text/css" media = "screen" href = "<c:url value='/css/dt/dataTables.jqueryui.min.css'/>"/> 
<link rel="stylesheet" type="text/css" href="<c:url value='/css/dt/dataTables.colVis.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/dt/dataTables.checkboxes.css'/>"/>

<script src ="/js/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src ="/js/jquery/jquery-ui.js" type="text/javascript"></script>
<script src="/js/jquery/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="/js/dt/dataTables.jqueryui.min.js" type="text/javascript"></script>
<script src="/js/dt/dataTables.colResize.js" type="text/javascript"></script>
<script src="/js/dt/dataTables.checkboxes.min.js" type="text/javascript"></script>	
<script src="/js/dt/dataTables.colVis.js" type="text/javascript"></script>	
<script type="text/javascript" src="/js/common.js"></script>

<!-- 달력을 사용 script -->
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/calendar.js"></script>
</head>
<style>
.cmm_bd .sub_tit > p{
	padding:0 8px 0 33px;
	line-height:24px;
	background:url(../images/popup/ico_p_2.png) 8px 48% no-repeat;
}
</style>
<script>
$(window.document).ready(function() {
	fn_init();
	
	$("#copyBin").attr('value', 'false');
	$("#renew").attr('value', 'false');
	
	$("#renewInsert").css("display", "none");
	 
	$.datepicker.setDefaults({
		dateFormat : 'yy-mm-dd',
		minDate: "+730d",
		changeYear: true,
	});
	$("#datepicker3").datepicker();

	$("#cipherAlgorithmCode").attr("disabled", "disabled");
	
	fn_historyCryptoKeySymmetric();

});

var renewalhistoryTable = null;

function fn_init(){
	renewalhistoryTable = $('#renewalhistoryTable').DataTable({
		scrollY : "85px",
		searching : false,
		paging: false,
		scrollX: true,
		bSort: false,
		columns : [
		{ data : "no", className : "dt-center", defaultContent : ""},
		{ data : "version", className : "dt-right", defaultContent : ""},
		{ data : "keyStatusName", defaultContent : ""},
		{ data : "validEndDateTime", defaultContent : ""},
		{ data : "createName", defaultContent : ""},
		{ data : "createDateTime", defaultContent : ""},
		{ data : "updateName", defaultContent : ""},
		{ data : "updateDateTime", defaultContent : ""},
		{ data : "binuid", defaultContent : "", visible: false },
		{ data : "binstatuscode", defaultContent : "", visible: false }
		]
	});
	
	renewalhistoryTable.tables().header().to$().find('th:eq(0)').css('min-width', '25px');
	renewalhistoryTable.tables().header().to$().find('th:eq(1)').css('min-width', '50px');
	renewalhistoryTable.tables().header().to$().find('th:eq(2)').css('min-width', '80px');
	renewalhistoryTable.tables().header().to$().find('th:eq(3)').css('min-width', '130px');
	renewalhistoryTable.tables().header().to$().find('th:eq(4)').css('min-width', '80px');
	renewalhistoryTable.tables().header().to$().find('th:eq(5)').css('min-width', '130px');
	renewalhistoryTable.tables().header().to$().find('th:eq(6)').css('min-width', '80px');
	renewalhistoryTable.tables().header().to$().find('th:eq(7)').css('min-width', '130px');
	renewalhistoryTable.tables().header().to$().find('th:eq(8)').css('min-width', '0px');
	renewalhistoryTable.tables().header().to$().find('th:eq(9)').css('min-width', '0px');
    $(window).trigger('resize');
}


$(function() {
	$("#renew").on('change', function() {
		  if ($(this).is(':checked')) {
		    $(this).attr('value', 'true');
		  } else {
		    $(this).attr('value', 'false');
		  }
		  
		  var renew = $("#renew").val();
		  
		 if(renew == "true"){
			$("#renewInsert").css("display", "");
		 }else{
			 $("#renewInsert").css("display", "none");
		 }
	});
	
	
	$("#copyBin").on('change', function() {
		  if ($(this).is(':checked')) {
		    $(this).attr('value', 'true');
		  } else {
		    $(this).attr('value', 'false');
		  }	 
	});
});


function fn_keyManagementModify(){
	
	var datas = renewalhistoryTable.rows().data();
	
	var datasArr = new Array();	
	for(var i=0; i<datas.length; i++){
		var rows = new Object();
		rows["binuid"] = renewalhistoryTable.rows().data()[i].binuid;	
		rows["binstatuscode"] = renewalhistoryTable.rows().data()[i].binstatuscode;	
		rows["validEndDateTime"] = renewalhistoryTable.rows().data()[i].validEndDateTime;	
		datasArr.push(rows);
	}

	
 $.ajax({
		url : "/updateCryptoKeySymmetric.do", 
	  	data : {
	  		keyUid : $('#keyUid').val(),
	  		resourceUid : $('#resourceUid').val(),
	  		resourceName: $('#resourceName').val(),
	  		cipherAlgorithmCode : $('#cipherAlgorithmCode').val(),
	  		resourceNote : $('#resourceNote').val(),
	  		validEndDateTime : $('#datepicker3').val().substring(0,10),
	  		renew : $("#renew").val(),
	  		copyBin : $("#copyBin").val(),
	  		historyCryptoKeySymmetric : JSON.stringify(datasArr),
	  	},
		dataType : "json",
		type : "post",
		beforeSend: function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	     },
		error : function(xhr, status, error) {
			if(xhr.status == 401) {
				alert("<spring:message code='message.msg02' />");
				top.location.href = "/";
			} else if(xhr.status == 403) {
				alert("<spring:message code='message.msg03' />");
				top.location.href = "/";
			} else {
				alert("ERROR CODE : "+ xhr.status+ "\n\n"+ "ERROR Message : "+ error+ "\n\n"+ "Error Detail : "+ xhr.responseText.replace(/(<([^>]+)>)/gi, ""));
			}
		},
		success : function(data) {
			if(data.resultCode == "0000000000"){
				alert('<spring:message code="message.msg84" />')
				fn_historyCryptoKeySymmetric();
			}else if(data.resultCode == "8000000002"){
				alert("<spring:message code='message.msg05' />");
				top.location.href = "/";
			}else if(data.resultCode == "8000000003"){
				alert(data.resultMessage);
				location.href = "/securityKeySet.do";
			}else{
				alert(data.resultMessage +"("+data.resultCode+")");	
			}	
		}
	});	
}

function fn_historyCryptoKeySymmetric(){
	$.ajax({
		url : "/historyCryptoKeySymmetric.do", 
	  	data : {
	  		keyUid : $('#keyUid').val(),
	  	},
		dataType : "json",
		type : "post",
		beforeSend: function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	     },
		error : function(xhr, status, error) {
			if(xhr.status == 401) {
				alert("<spring:message code='message.msg02' />");
				top.location.href = "/";
			} else if(xhr.status == 403) {
				alert("<spring:message code='message.msg03' />");
				top.location.href = "/";
			} else {
				alert("ERROR CODE : "+ xhr.status+ "\n\n"+ "ERROR Message : "+ error+ "\n\n"+ "Error Detail : "+ xhr.responseText.replace(/(<([^>]+)>)/gi, ""));
			}
		},
		success : function(data) {
			if(data.resultCode == "0000000000"){
				renewalhistoryTable.clear().draw();
				renewalhistoryTable.rows.add(data.list).draw();
			}else if(data.resultCode == "8000000002"){
				alert("<spring:message code='message.msg05' />");
				top.location.href = "/";
			}else if(data.resultCode == "8000000003"){
				alert(data.resultMessage);
				location.href = "/securityKeySet.do";
			}else{
				alert(data.resultMessage +"("+data.resultCode+")");		
			}				
		}
	});
}
</script>
<body>
	<div class="pop_container">
		<div class="pop_cts">
			<p class="tit"><spring:message code="encrypt_key_management.Encryption_Key_Modify"/></p>
				<table class="write">
					<caption><spring:message code="encrypt_key_management.Encryption_Key_Modify"/></caption>
					<colgroup>
						<col style="width: 150px;" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row" class="ico_t1"><spring:message code="encrypt_key_management.Key_Name"/></th>
							<td><input type="text" class="txt" name="resourceName" id="resourceName" readonly="readonly" value="${resourceName}" /></td>
						</tr>
						<tr>
							<th scope="row" class="ico_t1"><spring:message code="encrypt_key_management.Encryption_Algorithm"/></th>
							<td>
								<select class="select t5" id="cipherAlgorithmCode" name="cipherAlgorithmCode" >
										<c:forEach var="result" items="${result}" varStatus="status">
											<option value="<c:out value="${result.sysCode}"/>" <c:if test="${result.sysCode == cipherAlgorithmCode }">selected="selected"</c:if>><c:out value="${result.sysCodeName}"/></option>
										</c:forEach> 
								</select>
							</td>
						</tr>
						<tr>
							<th scope="row" class="ico_t1"><spring:message code="encrypt_key_management.Description"/></th>
							<td><input type="text" class="txt" name="resourceNote" id="resourceNote"  value="${resourceNote}" maxlength="100" onkeyup="fn_checkWord(this,100)" placeholder="100<spring:message code='message.msg188'/>"/></td>
						</tr>
						
						<tr>
							<td></td>
							<td><div class="inp_chk"><input type="checkbox" id="renew" name="renew" >
								<label for="renew"></label><spring:message code="encrypt_key_management.Update_Add_Binaries"/></div></td>								
						</tr>
						
						<tr id="renewInsert">
							<th scope="row" class="ico_t1"><spring:message code="encrypt_key_management.Expiration_Date"/></th>
							<td>
								<div class="calendar_area big">
									<a href="#n" class="calendar_btn">달력열기</a> <input type="text" class="calendar" id="datepicker3" >
								</div>
							</td>
							<td><div class="inp_chk"><input type="checkbox" id="copyBin" name="copyBin">
								<label for="copyBin"></label><spring:message code="encrypt_key_management.Copy_Binary"/></div></td>
						</tr>	
						<input type="hidden" id="keyUid" name="keyUid" value="${keyUid}">
						<input type="hidden" id="resourceUid" name="resourceUid" value="${keyUid}">
					</tbody>
				</table>
				<div class="cmm_bd">
					<div class="sub_tit">
						<p><spring:message code="encrypt_key_management.Update_History"/></p>
					</div>
					<table id="renewalhistoryTable" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th width="25"><spring:message code="common.no" /></th>										
								<th width="50"><spring:message code="encrypt_key_management.Version"/></th>
								<th width="80"><spring:message code="encrypt_key_management.Status"/></th>
								<th width="130"><spring:message code="encrypt_key_management.Expiration_Date"/></th>
								<th width="80"><spring:message code="common.register" /></th>
								<th width="130"><spring:message code="common.regist_datetime" /></th>
								<th width="80"><spring:message code="common.modifier" /></th>
								<th width="130"><spring:message code="common.modify_datetime" /></th>
								<th width="0"></th>
								<th width="0"></th>
							</tr>
						</thead>
					</table>											
				</div>	
				<div class="btn_type_02">
					<a href="#n" class="btn" onclick="fn_keyManagementModify();"><span><spring:message code="common.save"/></span></a>
					<a href="#n" class="btn" onclick="window.close();"><span><spring:message code="common.cancel" /></span></a>
				</div>
		</div>
	</div>
</body>
</html>