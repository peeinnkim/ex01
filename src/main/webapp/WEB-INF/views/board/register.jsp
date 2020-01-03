<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<script>
	$(function(){
		$("#divCancel").click(function(){
			location.href="listAll";
		})
		
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
		})
	})
</script>

<section class="content">
	<div class="row">
		<div class="col-sm-12">
			<div class="box box-primary">
			
				<div class="box-header">
					<h3 class="box-title">REGISTER BOARD</h3>
				</div>
			
				<form action="register" method="post">
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
							<input type="text" name="writer" class="form-control" placeholder="Enter Writer">
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


<%@ include file="../include/footer.jsp"%>