<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

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
				<form action="modifyPage" method="post">
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

<%@ include file="../include/footer.jsp"%>