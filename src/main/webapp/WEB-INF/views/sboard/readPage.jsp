<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<style>
	.btn > a {display: block;}
</style>

<script>
	$(function(){
		$("#delA").click(function(){
			var result = confirm("정말 삭제하시겠습니까?");
			
			if(result ==  true) {
				return true;
			} else {
				return false;
			}
		})
	})
</script>

<section class="content">
	<div class="row">
		<div class="col-sm-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<div class="box-body">
					<div class="form-group">
						<label>Title</label>
						<input type="text" name="title" class="form-control" placeholder="Enter Title" value="${boardVO.title}" readonly="readonly" style="background-color:white;">
					</div>
					<div class="form-group">
						<label>Content</label>
						<textarea rows="5" cols="50" class="form-control" name="content" placeholder="Enter Content" readonly="readonly" style="background-color:white;">${boardVO.content}</textarea>
					</div>
					<div class="form-group">
						<label>Writer</label>
						<input type="text" name="writer" class="form-control" placeholder="Enter Writer" value="${boardVO.writer}" readonly="readonly" style="background-color:white;">
					</div>
					<div class="form-group">
						<label>Image Files</label>
						<div>
							<c:forEach var="file" items="${boardVO.files}">
								<img src="${pageContext.request.contextPath}/upload/displayFile?fileName=${file}">
							</c:forEach>
						</div>
					</div>
				</div>
				<div class='box-footer'>
					<c:if test="${Auth == boardVO.writer}">
						<div class='btn btn-warning'><a href="modifyPage?bno=${boardVO.bno}&page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}" style="color:white;">MODIFY</a></div>
						<div class='btn btn-danger'><a href="removePage?bno=${boardVO.bno}&page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}" style="color:white;" id="delA">REMOVE</a></div>
					</c:if>
					<div class='btn btn-primary'><a href="listPage?page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}" style="color:white;">LIST</a></div>
				</div>
			</div>
		</div>
	</div>
	
	<div class='row'>
		<div class="col-sm-12">
			<div class="box box-success">
				<div class="box-header">
					<h3 class="box-title">Add New Reply</h3>
				</div>
				<div class="box-body">
					<label>Writer</label>
					<input type="text" class="form-control" id="newReplyer" value="${Auth}">
					<label>Reply Text</label>
					<input type="text" class="form-control" id="newReplyText">
				</div>
				<div class="box-footer">
					<button class="btn btn-warning" id="btnAddReply">Add Reply</button>
				</div>
			</div>
		</div>
	</div>
	
	<ul class="timeline">
		<li class="time-label" id="repliesDiv"> <!-- list button -->
			<span class="bg-green">Replies List[<small class="rCnt">${boardVO.replycnt}</small>]</span>
		</li>
	</ul>
	
	<div class="text-center">
		<ul id="pagination" class="pagination pagination-sm no-margin pager">
			<!-- <li><a href="#"></a></li> -->
		</ul>
	</div>
</section>

<div id="modifyModal" class="modal modal-primary fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content -->
		<div class='modal-content'>
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">rno번호 들어갈 곳</h4>
			</div>
		</div>
		<div class="modal-body">
			<p>
				<input type="text" id="replytext" class="form-control">
			</p>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-info" id="btnModSave">Modify</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		</div>
	</div>
</div>


<!-- HandleBars -->
<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<script id="template" type="text/x-handlebars-template">
{{#list}}
<li class="replyLi" data-rno="{{rno}}">
	<i class="fa fa-comments bg-blue"></i>
	<div class="timeline-item">
		<span class="time">
			<i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
		</span>
		<h3 class="timeline-header"><strong>{{rno}}</strong> - {{replyer}}</h3>
		<div class="timeline-body">{{replytext}}</div>
		<div class="timeline-footer">
			<a class="btn btn-primary btn-xs replyMod" data-toggle="modal" data-target="#modifyModal">modify</a>
			<a class="btn btn-danger btn-xs replyDel">delete</a>
		</div>
	</div>
</li>
{{/list}}
</script>
<script>
	var nPage = 1;
	
	/*------댓글 기능------*/
	Handlebars.registerHelper("prettifyDate", function(dd){
		var date = new Date(dd);
		var year = date.getFullYear();
		var month = date.getMonth();
		var d = date.getDate();
		
		return year + "/" + month + "/" + d;
	})
	
	//페이지 리스트 만들기 메소드
	function getPageList(page) {
		$.ajax({
			url:"${pageContext.request.contextPath}/replies/${boardVO.bno}/" + page,
			type: "get",
			dataType: "json",  
			success: function(res){
				console.log(res);
				
				$(".replyLi").remove();
				
				var source = $("#template").html();
				var func = Handlebars.compile(source);
				var str = func(res);
				
				//댓글 리스트 가져오기
				$(".timeline").append(str);
				
				//페이지네이션 지우기
				$("#pagination").empty();
				
				//make a page-maker
				var startPage = res.pageMaker.startPage;
				var endPage = res.pageMaker.endPage;
				
				for(var i=startPage; i<=endPage; i++) {
					var $li = $("<li>");
					var $a = $("<a>").attr("href", "#").attr("data-page", i).append(i);
					$li.append($a);
					
					if(i == page) {
						$li.addClass("active");
					}
					
					$("#pagination").append($li);
				}
				
				if(res.pageMaker.prev == true) {
					var $li = $("<li>").addClass("previous");
					var $a = $("<a>").attr("href", "#").attr("data-page", res.pageMaker.startPage-1).append("◀");
					$li.append($a);
					$("#pagination").prepend($li);
				}
				
				if(res.pageMaker.next == true) {
					var $li = $("<li>").addClass("next");
					var $a = $("<a>").attr("href", "#").attr("data-page", res.pageMaker.endPage+1).append("▶");
					$li.append($a);
					$("#pagination").append($li);
				}
				
				$(".rCnt").text(res.pageMaker.totalCount);
				
			},
			error: function(e){
				console.log(e);
			}
		})
	}
	
	$("#repliesDiv").click(function(){
		getPageList(1);
	})
	
	
	$(document).on("click", "#pagination a", function(){
		var page = $(this).attr("data-page");
		nPage = page;
		getPageList(page);
	})

	$("#btnAddReply").click(function(){
		var bno = "${boardVO.bno}";
		var replyer = $("#newReplyer").val();
		var replytext = $("#newReplyText").val();
		
		var data = JSON.stringify({
			bno: bno, 
			replyer: replyer, 
			replytext: replytext
		});
		
		$.ajax({
			url: "${pageContext.request.contextPath}/replies/",
			type: "post",
			data: data,
			headers: {"Content-Type":"application/json"},
			dataType: "text",
			success: function(res){
				console.log(res);
				$("#newReplyer").val("");
				$("#newReplyText").val("");
				alert("등록되었습니다.");
				getPageList(nPage);
				
			},
			error: function(e){
				console.log(e);
			}
		})
	})
	
	$(document).on("click", ".replyDel", function(){
		var rno = $(this).parents(".replyLi").attr("data-rno");
		var result = confirm("정말 삭제하시겠습니까?");
		
		if(result == true) {
			$.ajax({
				url: "${pageContext.request.contextPath}/replies/" + rno,
				type: "delete",
				dataType: "text",
				success: function(res){
					console.log(res);
					if(res == "success") {
						alert("삭제되었습니다.");
						getPageList(nPage);
					}
				},
				error: function(e){
					console.log(e);
				}
			}) 
			
		} else {
			return false;
		}
	})
	
	$(document).on("click", ".replyMod", function(){
		var rno = $(this).parents(".replyLi").attr("data-rno");
		var content = $(this).parent().prev(".timeline-body").text();
		
		$("#replytext").val(content);
		$(".modal-title").text(rno);
	})
	    
	$(document).on("click", "#btnModSave", function(){
		var content = $("#replytext").val();
		var rno = $(".modal-title").text();
		
		$.ajax({
			url: "${pageContext.request.contextPath}/replies/" + rno,
			type: "put",
			data: JSON.stringify({replytext: content}),
			dataType: "text",
			headers: {"Content-Type":"application/json"},
			success: function(res){
				console.log(res);
				
				if(res == "success") {
					alert("수정되었습니다.");
					$("#modifyModal").modal("hide");
					getPageList(nPage);
				}
			},
			error: function(e){
				console.log(e);
			}
		})
	})
	
	
</script>



<%@ include file="../include/footer.jsp"%>