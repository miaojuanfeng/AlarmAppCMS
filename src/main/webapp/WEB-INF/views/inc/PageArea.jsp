<div class="page-area">
	<span class="btn btn-sm btn-default">${totalRecord}</span>
	<c:if test="${totalRecord > 0}">
	<span class="pagination-area">
		<c:if test="${page-1 > 1}">
			<a href="<c:url value="/cms/${classLower}/select/1"></c:url>" class="btn btn-sm btn-primary">&lt;&lt;</a>
		</c:if>
		<c:if test="${page != 1}">
			<a href="<c:url value="/cms/${classLower}/select/${page-1}"></c:url>" class="btn btn-sm btn-primary">&lt;</a>
		</c:if>
		<c:if test="${page-1 > 0}">
			<a href="<c:url value="/cms/${classLower}/select/${page-1}"></c:url>" class="btn btn-sm btn-primary">${page-1}</a>
		</c:if>
		<a href="<c:url value="/cms/${classLower}/select/${page}"></c:url>" class="btn btn-sm btn-primary disabled">${page}</a>
		<c:if test="${page+1 <= totalPage}">
			<a href="<c:url value="/cms/${classLower}/select/${page+1}"></c:url>" class="btn btn-sm btn-primary">${page+1}</a>
		</c:if>
		<c:if test="${page != totalPage}">
			<a href="<c:url value="/cms/${classLower}/select/${page+1}"></c:url>" class="btn btn-sm btn-primary">&gt;</a>
		</c:if>
		<c:if test="${page+1 < totalPage}">
			<a href="<c:url value="/cms/${classLower}/select/${totalPage}"></c:url>" class="btn btn-sm btn-primary">&gt;&gt;</a>
		</c:if>
	</span>
	</c:if>
</div>