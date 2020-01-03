<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<style>
	div.btn-warning {
		float: right;
		margin-top: 5px;
	}
	div.pager-wrap {
		width: 100%;
		text-align: center;
	}
</style>

<section class="content">
	<div class="row">
		<div class="col-sm-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">LIST ALL</h3>
				</div>
				<div class="box-body">
					<table class="table table-hover table-bordered">
						<thead>
							<tr>
								<th style="width: 10px; text-align:center;">BNO</th>
								<th>TITLE</th>
								<th>WRITER</th>
								<th style="width: 150px;">REGDATE</th>
								<th style="width: 40px;">VIEWCNT</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="b" items="${list}">
								<tr>
									<td style="text-align:center;">${b.bno}</td>
									<td><a href="read?bno=${b.bno}">${b.title}</a></td>
									<td>${b.writer}</td>
									<td><fmt:formatDate value="${b.regdate}" pattern="yyyy-MM-dd HH:mm"/></td>
									<td style="text-align:center;"><span class="badge bg-red">${b.viewcnt}</span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<div class="pager-wrap">
						<div class="btn btn-warning"><a href="register" style="color:white;">REGIST</a></div>
						<ul class="pagination pager">
						  <li class="previous"><a href="#">◀</a></li>
						  <li class="active"><a href="#">1</a></li>
						  <li><a href="#">2</a></li>
						  <li><a href="#">3</a></li>
						  <li><a href="#">4</a></li>
						  <li><a href="#">5</a></li>
						  <li class="next"><a href="#">▶</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="../include/footer.jsp"%>