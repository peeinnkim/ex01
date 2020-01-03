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
				</div>
				<div class='box-footer'>
					<div class='btn btn-warning'><a href="modifyPage?bno=${boardVO.bno}&page=${cri.page}" style="color:white;">MODIFY</a></div>
					<div class='btn btn-danger'><a href="removePage?bno=${boardVO.bno}&page=${cri.page}" style="color:white;" id="delA">REMOVE</a></div>
					<div class='btn btn-primary'><a href="listPage?page=${cri.page}" style="color:white;">LIST</a></div>
				</div>
				
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog modal-sm">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Modal Header</h4>
							</div>
							<div class="modal-body">
								<p>This is a small modal.</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</section>

<%@ include file="../include/footer.jsp"%>