<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	/**
	* @Class Name : policyList.jsp
	* @Description : policyList 화면
	* @Modification Information
	*
	*   수정일         수정자                   수정내용
	*  ------------    -----------    ---------------------------
	*  2017.11.20     최초 생성
	*
	* author 김주영 사원
	* since 2017.11.20 
	*
	*/
%>
<script>
var table = null;

	function fn_init() {
		table = $('#policyTable').DataTable({
			scrollY : "250px",
			searching : false,
			deferRender : true,
			scrollX: true,
			columns : [
				{ data : "", defaultContent : "", targets : 0, orderable : false, checkboxes : {'selectRow' : true}}, 
				{ data : "", className : "dt-center", defaultContent : ""}, 
				{ data : "", className : "dt-center", defaultContent : ""}, 
				{ data : "", className : "dt-center", defaultContent : ""}, 
				{ data : "", className : "dt-center", defaultContent : ""}, 
				{ data : "", className : "dt-center", defaultContent : ""}, 
				{ data : "", className : "dt-center", defaultContent : ""}, 
				{ data : "", className : "dt-center", defaultContent : ""}, 
				{ data : "", className : "dt-center", defaultContent : ""}
	
			 ]
		});
		
		table.tables().header().to$().find('th:eq(0)').css('min-width', '20px');
		table.tables().header().to$().find('th:eq(1)').css('min-width', '40px');
		table.tables().header().to$().find('th:eq(2)').css('min-width', '50px');
		table.tables().header().to$().find('th:eq(3)').css('min-width', '100px');
		table.tables().header().to$().find('th:eq(4)').css('min-width', '100px');
		table.tables().header().to$().find('th:eq(5)').css('min-width', '100px');
		table.tables().header().to$().find('th:eq(6)').css('min-width', '100px');
		table.tables().header().to$().find('th:eq(7)').css('min-width', '80px');
		table.tables().header().to$().find('th:eq(8)').css('min-width', '100px');
	
	    $(window).trigger('resize');
	    
		//더블 클릭시
		$('#policyTable tbody').on('dblclick', 'tr', function() {
	
		});
	}
	$(window.document).ready(function() {
		fn_init();
	});

	/* 조회 버튼 클릭시*/
	function fn_select() {

	}

	/* 등록 버튼 클릭시*/
	function fn_insert() {
		var popUrl = "/popup/policyRegForm.do"; // 서버 url 팝업경로
		var width = 920;
		var height = 680;
		var left = (window.screen.width / 2) - (width / 2);
		var top = (window.screen.height /2) - (height / 2);
		var popOption = "width="+width+", height="+height+", top="+top+", left="+left+", resizable=no, scrollbars=yes, status=no, toolbar=no, titlebar=yes, location=no,";
		window.open(popUrl,"",popOption);
	}

	/* 수정 버튼 클릭시*/
	function fn_update() {

	}

	/* 삭제 버튼 클릭시*/
	function fn_delete() {

	}
</script>
<!-- contents -->
<div id="contents">
	<div class="contents_wrap">
		<div class="contents_tit">
			<h4>보안정책관리<a href="#n"><img src="../images/ico_tit.png" class="btn_info" /></a></h4>
			<div class="infobox">
				<ul>
					<li>보안정책관리설명</li>
				</ul>
			</div>
			<div class="location">
				<ul>
					<li>보안정책관리</li>
					<li class="on">보안정책관리</li>
				</ul>
			</div>
		</div>
		<div class="contents">
			<div class="cmm_grp">
				<div class="btn_type_01">
					<span class="btn" onclick="fn_select();"><button>조회</button></span>
					<span class="btn" onclick="fn_insert();"><button>등록</button></span>
					<span class="btn" onclick="fn_update();"><button>수정</button></span>
					<span class="btn" onclick="fn_delete();"><button>삭제</button></span>
				</div>
				<div class="sch_form">
					<table class="write">
						<caption>검색 조회</caption>
						<colgroup>
							<col style="width: 100px;" />
							<col style="width: 200px;" />
							<col style="width: 100px;" />
							<col style="width: 200px;" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row" class="t9">정책이름</th>
								<td><input type="text" class="txt t2" id="policyName" /></td>
								<th scope="row" class="t9">정책상태</th>
								<td><select class="select t5" id="policyStatus">
										<option value="Active">Active</option>
								</select></td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="overflow_area">
					<table id="policyTable" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th width="20"></th>
								<th width="40">No</th>
								<th width="50">정책이름</th>
								<th width="100">정책설명</th>
								<th width="100">정책상태</th>
								<th width="100">등록자</th>
								<th width="100">등록일시</th>
								<th width="80">수정자</th>
								<th width="100">수정일시</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- // contents -->
