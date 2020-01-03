<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<style>
	#updateImageBox > img {
		height: 100px;
		margin: 3px;
	}
	#imagebox {
		overflow: hidden;
	}
	.thumb-box {
		margin: 3px;
		float: left;
		position: relative;
	}
	.thumb-box > a {
		position: absolute;
	    padding: 3px;
	    background: white;
	    border-radius: 10px;
	    right: 3px;
	    top: 4px;
	    color: #DF4D4D;
	    font-weight: bold;
	    box-shadow: 2px 2px 2px grey;
	    font-size: 11px;
	    width: 20px;
	    height: 20px;
	    text-align: center;
	    cursor: pointer;
	}
</style>
<script>
	$(function(){
		$("form").submit(function(){
			if($("input[name='title']").val() == "") {
				alert("제목을 입력하세요")
				return false;
			}
			if($("textarea[name='content']").val() == "") {
				alert("내용을 입력하세요")
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
					<h3 class="box-title">MODIFY BOARD</h3>
				</div>
				<form action="modifyPage" method="post" enctype="multipart/form-data">
					<div class="box-body">
						<input type="hidden" name="bno" value="${boardVO.bno}">
						<input type="hidden" name="page" value="${cri.page}">
						<div class="form-group">
							<label>Title</label>
							<input type="text" name="title" class="form-control" placeholder="Enter Title" value="${boardVO.title}">
						</div>
						<div class="form-group">
							<label>Content</label>
							<textarea rows="5" cols="50" class="form-control" name="content" placeholder="Enter Content">${boardVO.content}</textarea>
						</div>
						<div class="form-group">
							<label>Writer</label>
							<input type="text" name="writer" class="form-control" placeholder="Enter Writer" value="${boardVO.writer}" readonly="readonly">
						</div>
						<div class="form-group">
							<label>Image files</label>
							<input type="file" name="imageFiles" class="form-control" multiple="multiple">
							<div id="updateImageBox"></div>
						</div>
						<div class="form-group">
							<label>Added files</label>
							<div id="imagebox">
								<c:forEach var="img" items="${boardVO.files}">
									<div class="thumb-box">
										<img src="${pageContext.request.contextPath}/upload/displayFile?fileName=${img}">
										<a class="delA" data-src="${img}">Ⅹ</a>								
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class='box-footer'>
						<input type="submit" value="SAVE" class='btn btn-warning'>
						<div class='btn btn-primary'><a href="listPage?page=${cri.page}" style="color:white;">CANCEL</a></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script>
	$("input[type='file']").change(function(){
		$("#updateImageBox").empty();
		
		var file = $(this)[0].files;
		var reader = null;
		
		for(var i=0; i<file.length; i++) {
			reader = new FileReader();
			
			reader.readAsDataURL(file[i]);
			reader.onload = function(e){
				var $img = $("<img>").attr("src", e.target.result); 
				$("#updateImageBox").append($img);
			}
		}
	})//file.change  
	
	$(".delA").click(function(){
		var fileName = $(this).attr("data-src");
		var $input = $("<input>").attr("type", "hidden").attr("name", "delFiles").val(fileName);
		$("#imagebox").append($input);
		$(this).parent().remove();
	})
	
	
	
</script>


<%@ include file="../include/footer.jsp"%>