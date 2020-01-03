<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<style>
	#imagebox > img {
		width: 100px;
		height: 100px;
		margin: 2px 5px 2px 0;
	}
</style>

<section class="content">
	<div class="row">
		<div class="col-sm-12">
			<div class="box box-primary">
			
				<div class="box-header">
					<h3 class="box-title">REGISTER BOARD</h3>
				</div>
			
				<form action="registerPage" method="post" enctype="multipart/form-data">
					<div class="box-body">
						<div class="form-group">
							<label>Title</label>
							<input type="text" name="title" class="form-control" placeholder="Enter Title">
						</div>
						<div class="form-group">
							<label>Content</label>
							<textarea rows="5" cols="50" class="form-control" name="content" placeholder="Enter Content"></textarea>
						</div>
						<div class="form-group">
							<label>Writer</label>
							<input type="text" name="writer" class="form-control" placeholder="Enter Writer" value="${Auth}">
						</div>
						<div class="form-group">
							<label>Image files</label>
							<input type="file" name="imageFiles" class="form-control" multiple="multiple">
							<div id="imagebox"></div>
						</div>
					</div><!-- box-body -->
					<div class='box-footer'>
						<input type="submit" value="SUBMIT" class="btn btn-primary">					
						<div class="btn btn-primary" id="divCancel">CANCEL</div>					
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

	<script>
		$("#divCancel").click(function(){
			location.href="listPage";
		})//cancel
		
		$("form").submit(function(){
			if($("input[name='title']").val() == "") {
				alert("제목을 입력하세요")
				return false;
			}
			if($("textarea[name='content']").val() == "") {
				alert("내용을 입력하세요")
				return false;
			}
			if($("input[name='writer']").val() == "") {
				alert("작성자를 입력하세요")
				return false;
			}
		})//form.submit
		
		
		$("input[type='file']").change(function(){
			$("#imagebox").empty();
			
			var file = $(this)[0].files;
			var reader = null;
			
			for(var i=0; i<file.length; i++) {
				reader = new FileReader();
				
				reader.readAsDataURL(file[i]);
				reader.onload = function(e){
					var $img = $("<img>").attr("src", e.target.result); 
					$("#imagebox").append($img);
				}
			}
		})//file.change
	</script>


<%@ include file="../include/footer.jsp"%>