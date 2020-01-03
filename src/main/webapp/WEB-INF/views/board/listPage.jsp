<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<style>
	div.btn-warning {
		float: right;
		margin-top: 5px;
	}
	select.perPageNum-wrap {
		float: left;
		margin-top: 5px;
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
									<td><a href="readPage?bno=${b.bno}&page=${pageMaker.cri.page}">${b.title}</a></td>
									<td>${b.writer}</td>
									<td><fmt:formatDate value="${b.regdate}" pattern="yyyy-MM-dd HH:mm"/></td>
									<td style="text-align:center;"><span class="badge bg-red">${b.viewcnt}</span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div><!-- box-body -->
				<div class="box-footer">
					<div class="text-center">
						<select class="perPageNum-wrap">
							<option>5개씩 보기</option>
							<option selected="selected">10개씩 보기</option>
							<option>15개씩 보기</option>
							<option>20개씩 보기</option>
						</select>
						
						<div class="btn btn-warning"><a href="register" style="color:white;">REGIST</a></div>
						
						<ul class="pagination pager">
							<c:if test="${pageMaker.prev == true}">
								<li class="previous"><a href="listPage?page=${pageMaker.startPage-1}">◀</a></li>
							</c:if>
							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
								<li ${idx == pageMaker.cri.page? 'class=active': ''}><a href="listPage?page=${idx}">${idx}</a></li>
							</c:forEach>
							<c:if test="${pageMaker.next == true}">
								<li class="next"><a href="listPage?page=${pageMaker.endPage+1}">▶</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="../include/footer.jsp"%>